package br.com.tiagopedroso.moonprobe.entrypoint.service;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaResponse;
import br.com.tiagopedroso.moonprobe.infra.converter.CelestialAreaConverter;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError400Exception;
import br.com.tiagopedroso.moonprobe.infra.exception.RestError404Exception;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.CelestialAreaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.tiagopedroso.moonprobe.infra.converter.CelestialAreaConverter.convertCreateDtoToModel;
import static br.com.tiagopedroso.moonprobe.infra.converter.CelestialAreaConverter.convertModelToResponseDto;
import static br.com.tiagopedroso.moonprobe.infra.converter.Mapper.copyFieldsToNullsOnly;

@Service
@AllArgsConstructor
public class CelestialAreaService {

    CelestialAreaRepository repository;
    ProbeService probeService;

    public CelestialAreaResponse create(CelestialAreaCreate dto) {
        //the conversion validates each added Probe
//        convertDtoToLogic(dto);

//        System.out.println("\n\n" + convertResponseDtoToLogic(dto) + "\n\n");


        return createOrUpdate(dto);
    }

    public List<CelestialAreaResponse> list() {
        return repository.findAll().stream()
                .map(CelestialAreaConverter::convertModelToResponseDto)
                .collect(Collectors.toList());
    }

    public CelestialAreaResponse getById(Long id) {
        return convertModelToResponseDto(
                repository.findById(id).orElseThrow(() ->
                        RestError404Exception.build(
                                "id=%d", id
                        )
                )
        );
    }

    public CelestialAreaResponse update(Long id, CelestialAreaCreate dto) {
        final var originalDto = getById(id);
        final var updatedDto = copyFieldsToNullsOnly(originalDto, dto);

        //the conversion validates each added Probe
//        convertResponseDtoToLogic(updatedDto);

        dto.setId(id);
        return createOrUpdate(dto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CelestialAreaResponse createOrUpdate(CelestialAreaCreate dto) {
        final var model = convertCreateDtoToModel(dto);
        final var savedModel = repository.save(model);
        return convertModelToResponseDto(savedModel);
    }

    public CelestialAreaResponse includeProbe(Long celestialAreaId, Long probeId) {
        if (repository.existsById(celestialAreaId)) {
//            final var probeDto = probeService.getById(probeId);
            probeService.includeCelestialAreaId(getById(celestialAreaId), probeId);

            System.out.println("\n\n [after] model C: \n" + repository.findById(celestialAreaId));

            return new CelestialAreaResponse();
        }

        throw RestError400Exception.build("The id=%d is invalid :(", celestialAreaId);
    }
}
