package br.com.tiagopedroso.moonprobe.infra.exception;

import br.com.tiagopedroso.moonprobe.model.FitCoord;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Arrays;

@Getter
@Setter
public class CelestialAreaException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6080367934703451524L;

    private String message;

    public static final String MESSAGE_PROBE_POSITION_ALREADY_OCCUPIED = "the position (%d, %d) of probe \"%s\" is already occupied!";

    public CelestialAreaException(String message) {
        super(message);
        this.message = message;
    }

    public CelestialAreaException(FitCoord coord, String probeName) {
        this(MESSAGE_PROBE_POSITION_ALREADY_OCCUPIED.formatted(coord.getX(), coord.getY(), probeName));
    }

    @Override
    public String toString() {
        var list = Arrays.asList(super.getStackTrace());

        return """
                { "class": "%s", "fields": {
                    "message": "%s",
                    "stackTrace": "%s"
                }"""
                .formatted(
                        this.getClass().getSimpleName(),
                        message,
                        list
                );
    }
}
