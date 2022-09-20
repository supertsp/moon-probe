package br.com.tiagopedroso.moonprobe.infra.converter;

import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeCreate;
import br.com.tiagopedroso.moonprobe.entrypoint.dto.probe.ProbeResponse;
import br.com.tiagopedroso.moonprobe.entrypoint.model.CelestialArea;
import br.com.tiagopedroso.moonprobe.entrypoint.model.Probe;
import br.com.tiagopedroso.moonprobe.entrypoint.repository.CelestialAreaRepository;
import br.com.tiagopedroso.moonprobe.infra.exception.ConverterException;
import br.com.tiagopedroso.moonprobe.logic.CommandSequence;
import br.com.tiagopedroso.moonprobe.logic.FitCoord;

import java.time.LocalDateTime;

public class ProbeConverter {

    private ProbeConverter() {
    }

    public static Probe convertCreateDtoToModel(ProbeCreate dto) {
        if (dto == null) {
            throw new ConverterException("DTO", ProbeCreate.class);
        }

        return Mapper.copyFields(dto, Probe.class);
    }

    public static Probe convertResponseDtoToModel(ProbeResponse dto, CelestialAreaRepository repository) {
        if (dto == null) {
            throw new ConverterException("DTO", ProbeResponse.class);
        }

        if (repository == null || dto.getCelestialAreaId() == null) {
            return new Probe(
                    dto.getId(),
                    dto.getName(),
                    dto.getX(),
                    dto.getY(),
                    dto.getOrientation(),
                    dto.getCommandSequence(),
                    dto.getCreated(),
                    dto.getTotalMovements(),
                    null
            );
        }

        return new Probe(
                dto.getId(),
                dto.getName(),
                dto.getX(),
                dto.getY(),
                dto.getOrientation(),
                dto.getCommandSequence(),
                dto.getCreated(),
                dto.getTotalMovements(),
                repository.findById(dto.getCelestialAreaId()).get()
        );
    }

    public static Probe convertResponseDtoToModel(ProbeResponse dto, CelestialArea model) {
        if (dto == null) {
            throw new ConverterException("DTO", ProbeResponse.class);
        }

        return new Probe(
                dto.getId(),
                dto.getName(),
                dto.getX(),
                dto.getY(),
                dto.getOrientation(),
                dto.getCommandSequence(),
                dto.getCreated(),
                dto.getTotalMovements(),
                model
        );
    }

    public static ProbeCreate convertModelToCreateDto(Probe model) {
        if (model == null) {
            throw new ConverterException("Model", Probe.class);
        }

        return Mapper.copyFields(model, ProbeCreate.class);
    }

    public static ProbeResponse convertModelToResponseDto(Probe model) {
        if (model == null) {
            throw new ConverterException("Model", Probe.class);
        }

        if (model.getCelestialArea() == null) {
            return new ProbeResponse(
                    model.getId(),
                    model.getName(),
                    model.getX(),
                    model.getY(),
                    model.getOrientation(),
                    model.getCommandSequence(),
                    model.getCreated(),
                    null,
                    model.getTotalMovements()
            );
        }

        return new ProbeResponse(
                model.getId(),
                model.getName(),
                model.getX(),
                model.getY(),
                model.getOrientation(),
                model.getCommandSequence(),
                model.getCreated(),
                model.getCelestialArea().getId(),
                model.getTotalMovements()
        );
    }

    public static br.com.tiagopedroso.moonprobe.logic.Probe convertCreateDtoToLogic(ProbeCreate dto) {
        if (dto == null) {
            throw new ConverterException("DTO", ProbeCreate.class);
        }

        return new br.com.tiagopedroso.moonprobe.logic.Probe(
                dto.getName(),
                new FitCoord(dto.getX(), dto.getY(), dto.getX(), dto.getY()),
                dto.getOrientation(),
                new CommandSequence(dto.getCommandSequence())
        );
    }

    public static br.com.tiagopedroso.moonprobe.logic.Probe convertResponseDtoToLogic(ProbeResponse dto) {
        if (dto == null) {
            throw new ConverterException("DTO", ProbeResponse.class);
        }

        return new br.com.tiagopedroso.moonprobe.logic.Probe(
                dto.getName(),
                new FitCoord(dto.getX(), dto.getY(), dto.getX(), dto.getY()),
                dto.getOrientation(),
                new CommandSequence(dto.getCommandSequence())
        );
    }

    public static br.com.tiagopedroso.moonprobe.logic.Probe convertModelToLogic(Probe model) {
        if (model == null) {
            throw new ConverterException("Model", Probe.class);
        }

        return new br.com.tiagopedroso.moonprobe.logic.Probe(
                model.getName(),
                new FitCoord(model.getX(), model.getY(), model.getX(), model.getY()),
                model.getOrientation(),
                new CommandSequence(model.getCommandSequence())
        );
    }

    public static br.com.tiagopedroso.moonprobe.logic.Probe convertModelToLogic(
            Probe model,
            int widthLimit,
            int heightLimit
    ) {
        if (model == null) {
            throw new ConverterException("Model", Probe.class);
        }

        return new br.com.tiagopedroso.moonprobe.logic.Probe(
                model.getName(),
                new FitCoord(model.getX(), model.getY(), widthLimit, heightLimit),
                model.getOrientation(),
                new CommandSequence(model.getCommandSequence()),
                model.getTotalMovements()
        );
    }

    public static Probe convertLogicToModel(
            br.com.tiagopedroso.moonprobe.logic.Probe logic,
            Long probeId,
            LocalDateTime probeCreated,
            CelestialArea celestialAreaModel
    ) {
        if (logic == null) {
            throw new ConverterException("Logic", ProbeResponse.class);
        }

        return new Probe(
                probeId,
                logic.getName(),
                logic.coord.getX(),
                logic.coord.getY(),
                logic.getOrientation(),
                logic.getCommandSequence().getSequence(),
                probeCreated,
                logic.getTotalMovements(),
                celestialAreaModel
        );
    }

}
