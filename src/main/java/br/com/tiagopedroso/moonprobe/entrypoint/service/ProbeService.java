package br.com.tiagopedroso.moonprobe.entrypoint.service;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.entrypoint.model.Probe;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.ProbeRepository;
import br.com.tiagopedroso.moonprobe.infra.converter.ProbeConverter;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError400Exception;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError404Exception;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static br.com.tiagopedroso.moonprobe.infra.converter.Mapper.copyFieldsToNullsOnly;
import static br.com.tiagopedroso.moonprobe.infra.converter.ProbeConverter.*;

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


}
