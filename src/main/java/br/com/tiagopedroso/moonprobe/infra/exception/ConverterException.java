package br.com.tiagopedroso.moonprobe.infra.exception;

import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.util.Arrays;

@Getter
@Setter
public class ConverterException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 6080367934703451525L;

    private String message;

    public static final String MESSAGE_NULL_OBJECT = "\"The %s \"%s\" is null! :o\"";

    public ConverterException(String message) {
        super(message);
        this.message = message;
    }

    public <T> ConverterException(String objectType, Class<T> errorGeneratingClass) {
        this(MESSAGE_NULL_OBJECT.formatted(MESSAGE_NULL_OBJECT, objectType, errorGeneratingClass.getSimpleName()));
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
