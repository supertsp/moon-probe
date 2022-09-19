package br.com.tiagopedroso.moonprobe.entrypoint.service;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
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
                repository.findById(id).orElseThrow(() ->
                        RestError404Exception.build(
                                "id=%d", id
                        )
                )
        );
    }

    public ProbeResponse update(Long id, ProbeCreate dto) {
        final var originalDto = getById(id);
        final var updatedDto = copyFieldsToNullsOnly(originalDto, dto);
        final var logic = convertCreateDtoToLogic(updatedDto);

        if (logic.getCommandSequence().isValid()) {
            dto.setId(id);
            return createOrUpdate(dto);
        }

        throw RestError400Exception.build("The command sequence is invalid");
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Transactional
    public void includeCelestialAreaId(CelestialAreaResponse celestialAreaResponse, Long probeId) {
//        dto.setCelestialAreaId(celestialAreaId);
//        final var model = convertResponseDtoToModel(dto);

        //TODO FAZER MELHORES CONVERSORES NOS CONVERTERS

        var model = repository.findById(probeId).get();
        model.setCelestialArea(
                new CelestialArea(
                        celestialAreaResponse.getId(),
                        celestialAreaResponse.getName(),
                        celestialAreaResponse.getWidth(),
                        celestialAreaResponse.getHeight(),
                        celestialAreaResponse.getCreated()
                )
        );

//        var model = new Probe(
//                dto.getId(),
//                dto.getName(),
//                dto.getX(),
//                dto.getY(),
//                dto.getOrientation(),
//                dto.getCommandSequence(),
//                dto.getCreated(),
//                new CelestialArea(
//                        celestialAreaResponse.getId(),
//                        celestialAreaResponse.getName(),
//                        celestialAreaResponse.getWidth(),
//                        celestialAreaResponse.getHeight(),
//                        celestialAreaResponse.getCreated()
//                )
//        );
        System.out.println("\n\n [before] model P: \n" + model);

        var modelResponse = repository.save(model);

        System.out.println("\n\n [middle] model P: \n" + modelResponse);


        var newModel = repository.findById(probeId).get();

        System.out.println("\n\n [after] model P: \n" + newModel);



//        repository.insertCelestialAreaId(celestialAreaId, dto.getId());
    }

    private ProbeResponse createOrUpdate(ProbeCreate dto) {
        final var model = convertCreateDtoToModel(dto);
        final var savedModel = repository.save(model);
        return convertModelToResponseDto(savedModel);
    }

}
