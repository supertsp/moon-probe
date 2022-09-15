package br.com.tiagopedroso.moonprobe.infra.exception;

/**
 * Class imported from https://github.com/supertsp/livraria-online-api
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class RestFieldItemError {

    private String field;
    private String message;

    public static RestFieldItemError build(String field, Object message) {
        return new RestFieldItemError(field, message.toString());
    }

}
