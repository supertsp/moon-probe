package br.com.tiagopedroso.moonprobe.entrypoint.service;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.infra.converter.CelestialAreaConverter;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError400Exception;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError404Exception;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.CelestialAreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.tiagopedroso.moonprobe.infra.converter.CelestialAreaConverter.*;
import static br.com.tiagopedroso.moonprobe.infra.converter.Mapper.copyFieldsToNullsOnly;

@Service
@AllArgsConstructor
public class CelestialAreaService {

    CelestialAreaRepository repository;
    ProbeService probeService;

    CelestialAreaResponse createOrUpdate(CelestialAreaCreate dto) {
        final var model = convertCreateDtoToModel(dto);
        final var savedModel = repository.save(model);
        return convertModelToResponseDto(savedModel);
    }

    CelestialArea findByIdOrThrow(Long id) {
        return repository.findById(id).orElseThrow(() ->
                RestError404Exception.build(
                        "celestialAreaId=%d", id
                )
        );
    }

    public CelestialAreaResponse create(CelestialAreaCreate dto) {
        //the conversion validates logic
        convertCreateDtoToLogic(dto);

        return createOrUpdate(dto);
    }

    public List<CelestialAreaResponse> list() {
        return repository.findAll().stream()
                .map(CelestialAreaConverter::convertModelToResponseDto)
                .collect(Collectors.toList());
    }

    public CelestialAreaResponse getById(Long id) {
        return convertModelToResponseDto(
                findByIdOrThrow(id)
        );
    }

    public CelestialAreaResponse update(Long id, CelestialAreaCreate dto) {
        final var originalDto = getById(id);
        final var updatedDto = copyFieldsToNullsOnly(originalDto, dto);

        //the conversion validates logic
        convertCreateDtoToLogic(updatedDto);

        updatedDto.setId(id);
        return createOrUpdate(updatedDto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public CelestialAreaResponse includeProbe(Long celestialAreaId, Long probeId) {
        if (repository.existsById(celestialAreaId)) {
            probeService.includeCelestialAreaId(
                    findByIdOrThrow(celestialAreaId),
                    probeId
            );

            return getById(celestialAreaId);
        }

        throw RestError400Exception.build("The celestialAreaId=%d is invalid :(", celestialAreaId);
    }

    public ProbeResponse moveProbe(Long id, Long probeId) {
        return null;
    }
}
