package br.com.tiagopedroso.moonprobe.infra.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Arrays;

@Getter
@Setter
public class FitCoordException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6080367934703451523L;

    private String message;

    public static final String MESSAGE_OUTSIDE_LIMIT = "The value of %s=%d is outside %s >= 0 && <= %d";
    public static final String MESSAGE_NOT_NEGATIVE_VALUE = "The value of %S=%d cannot be negative";

    public FitCoordException(String message) {
        super(message);
        this.message = message;
    }

    public FitCoordException(String coordName, Integer coordValue, String limitName, Integer limitValue) {
        this(MESSAGE_OUTSIDE_LIMIT.formatted(coordName, coordValue, limitName, limitValue));
    }

    public FitCoordException(String limitName, Integer limitValue) {
        this(MESSAGE_NOT_NEGATIVE_VALUE.formatted(limitName, limitValue));
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
