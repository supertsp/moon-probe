package br.com.tiagopedroso.moonprobe.infra.exception.dto;

/**
 * Class imported from https://github.com/supertsp/livraria-online-api
 */

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
public class ItemErrorDto {

    private String field;
    private String message;

    public static ItemErrorDto build(String field, Object message) {
        return new ItemErrorDto(field, message.toString());
    }

}
