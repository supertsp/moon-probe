package br.com.tiagopedroso.moonprobe.infra.converter;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea.CelestialAreaResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.ProbeRepository;
import br.com.tiagopedroso.moonprobe.infra.exception.ConverterException;

import java.util.ArrayList;

public class CelestialAreaConverter {

    private CelestialAreaConverter() {
    }


    public static CelestialArea convertCreateDtoToModel(CelestialAreaCreate dto) {
        if (dto == null) {
            throw new ConverterException("DTO", CelestialAreaCreate.class);
        }

        return Mapper.copyFields(dto, CelestialArea.class);
    }

    public static CelestialArea convertResponseDtoToModel(CelestialAreaResponse dto) {
        if (dto == null) {
            throw new ConverterException("DTO", CelestialAreaResponse.class);
        }

        return Mapper.copyFields(dto, CelestialArea.class);
    }

    public static CelestialAreaCreate convertModelToCreateDto(CelestialArea model) {
        if (model == null) {
            throw new ConverterException("Model", CelestialArea.class);
        }

        return Mapper.copyFields(model, CelestialAreaCreate.class);
    }

    public static CelestialAreaResponse convertModelToResponseDto(CelestialArea model) {
        if (model == null) {
            throw new ConverterException("Model", CelestialArea.class);
        }

        final var dto = new CelestialAreaResponse(
                model.getId(),
                model.getName(),
                model.getWidth(),
                model.getHeight(),
                model.getCreated(),
                null
        );

        if (model.getProbes() != null && model.getProbes().size() > 0) {
            dto.setProbesId(new ArrayList<>(model.getProbes().size()));

            model.getProbes()
                    .forEach(probe ->
                            dto.getProbesId().add(
                                    probe.getId()
                            )
                    );
        }

        return dto;
    }

    public static br.com.tiagopedroso.moonprobe.logic.CelestialArea convertCreateDtoToLogic(CelestialAreaCreate dto) {
        if (dto == null) {
            throw new ConverterException("DTO", CelestialAreaCreate.class);
        }

        return Mapper.copyFields(dto, br.com.tiagopedroso.moonprobe.logic.CelestialArea.class);
    }

    public static br.com.tiagopedroso.moonprobe.logic.CelestialArea convertResponseDtoToLogic(CelestialAreaResponse dto, ProbeRepository repository) {
        if (dto == null) {
            throw new ConverterException("DTO", CelestialAreaResponse.class);
        }

        final var logic = new br.com.tiagopedroso.moonprobe.logic.CelestialArea(
                dto.getName(),
                dto.getWidth(),
                dto.getHeight()
        );

        if (repository != null && dto.getProbesId() != null) {
            repository.findAllById(dto.getProbesId())
                    .forEach(probeModel ->
                            logic.addProbe(
                                    ProbeConverter.convertModelToLogic(probeModel)
                            )
                    );
        }

        return logic;
    }

    public static br.com.tiagopedroso.moonprobe.logic.CelestialArea convertModelToLogic(CelestialArea model) {
        if (model == null) {
            throw new ConverterException("Model", CelestialArea.class);
        }

        final var logic = new br.com.tiagopedroso.moonprobe.logic.CelestialArea(
                model.getName(),
                model.getWidth(),
                model.getHeight()
        );

        if (model.getProbes() != null && model.getProbes().size() > 0) {
            model.getProbes()
                    .forEach(probeModel ->
                            logic.addProbe(
                                    ProbeConverter.convertModelToLogic(probeModel)
                            )
                    );
        }

        return logic;
    }

}
