package br.com.tiagopedroso.moonprobe.entrypoint.service;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.entrypoint.model.Probe;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.CelestialAreaRepository;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.ProbeRepository;
import br.com.tiagopedroso.moonprobe.infra.converter.CelestialAreaConverter;
import br.com.tiagopedroso.moonprobe.infra.converter.ProbeConverter;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError400Exception;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError404Exception;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tiagopedroso.moonprobe.infra.converter.Mapper.copyFieldsToNullsOnly;
import static br.com.tiagopedroso.moonprobe.infra.converter.ProbeConverter.*;

@Slf4j
@Service
@AllArgsConstructor
public class ProbeService {

    ProbeRepository repository;

    ProbeResponse createOrUpdate(ProbeCreate dto) {
        final var model = convertCreateDtoToModel(dto);
        final var savedModel = repository.save(model);
        return convertModelToResponseDto(savedModel);
    }

    Probe findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                RestError404Exception.build(
                        "probeId=%d", id
                )
        );
    }

    public ProbeResponse create(ProbeCreate dto) {
        final var logic = convertCreateDtoToLogic(dto);

        if (logic.getCommandSequence().isValid()) {
            dto.setId(null);
            return createOrUpdate(dto);
        }

        throw RestError400Exception.build("The command sequence is invalid");
    }

    public List<ProbeResponse> list() {
        return repository.findAll().stream()
                .map(ProbeConverter::convertModelToResponseDto)
                .collect(Collectors.toList());
    }

    public ProbeResponse getById(Long id) {
        return convertModelToResponseDto(
                findByIdOrThrow(id)
        );
    }

    public ProbeResponse update(Long id, ProbeCreate dto) {
        //keeps CelestialArea inclusion
        final var modelSearched = findByIdOrThrow(id);

        dto.setId(id);
        final var modelCreate = convertCreateDtoToModel(dto);

        final var updatedModel = copyFieldsToNullsOnly(modelSearched, modelCreate);
        final var logic = convertModelToLogic(updatedModel);

        if (logic.getCommandSequence() != null && logic.getCommandSequence().isValid()) {
            return convertModelToResponseDto(repository.save(updatedModel));
        }

        throw RestError400Exception.build("The command sequence is invalid");
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void includeCelestialAreaId(CelestialArea celestialAreaModel, Long probeId) {
        final var model = convertResponseDtoToModel(
                getById(probeId),
                celestialAreaModel
        );

        repository.save(model);
    }


    public void removeCelestialAreaId(CelestialArea celestialAreaModel, Long probeId) {
        final var probeDto = getById(probeId);
        final var model = convertResponseDtoToModel(
                probeDto,
                (CelestialAreaRepository) null
        );

        if (probeDto.getCelestialAreaId() == celestialAreaModel.getId()) {
            repository.save(model);
        } else {
            throw RestError400Exception.build("this probeId=\"%d\" does not belong to celestialAreaId=\"%d\"", probeId, celestialAreaModel.getId());
        }
    }

    public ProbeResponse move(CelestialArea celestialAreaModel, Long probeId) {
        final var model = findByIdOrThrow(probeId);

        if (model.getCelestialArea() != null && model.getCelestialArea().getId() == celestialAreaModel.getId()) {
            final var logic = convertModelToLogic(
                    model,
                    celestialAreaModel.getWidth(),
                    celestialAreaModel.getHeight()
            );

            logic.move();
            System.out.println("Probe Move Debug: \n" + logic.getMovementDebugging());

            final var updatedModel = repository.save(convertLogicToModel(logic, model.getId(), model.getCreated(), celestialAreaModel));

            return convertModelToResponseDto(updatedModel);
        }

        throw RestError400Exception.build("this probeId=\"%d\" does not belong to celestialAreaId=\"%d\"", probeId, celestialAreaModel.getId());
    }

    public ProbeResponse moveAllSequences(CelestialArea celestialAreaModel, Long probeId) {
        final var model = findByIdOrThrow(probeId);

        if (model.getCelestialArea() != null && model.getCelestialArea().getId() == celestialAreaModel.getId()) {
            final var logic = convertModelToLogic(
                    model,
                    celestialAreaModel.getWidth(),
                    celestialAreaModel.getHeight()
            );

            logic.moveUntilLastCommand();
            final var updatedModel = repository.save(convertLogicToModel(logic, model.getId(), model.getCreated(), celestialAreaModel));

            return convertModelToResponseDto(updatedModel);
        }

        throw RestError400Exception.build("this probeId=\"%d\" does not belong to celestialAreaId=\"%d\"", probeId, celestialAreaModel.getId());
    }

    public List<ProbeResponse> moveAllProbesUntilLastSequence(CelestialArea celestialAreaModel) {
        if (celestialAreaModel.getProbes() != null) {
            final var celestialLogic = CelestialAreaConverter.convertModelToLogic(celestialAreaModel);
            celestialLogic.moveProbesStepByStepUntilLastCommand();

            final List<Probe> updatedModelList = new ArrayList<>(celestialAreaModel.getProbes().size());

            for (int index = 0; index < celestialLogic.getProbes().size(); index++) {
                updatedModelList.add(
                        convertLogicToModel(
                                celestialLogic.getProbe(index),
                                celestialAreaModel.getProbes().get(index).getId(),
                                celestialAreaModel.getProbes().get(index).getCreated(),
                                celestialAreaModel)
                );
            }

            final var probesSaved = repository.saveAll(updatedModelList);

            return probesSaved.stream()
                    .map(model -> convertModelToResponseDto(model))
                    .collect(Collectors.toList());
        }

        return new ArrayList<>(0);
    }

}
