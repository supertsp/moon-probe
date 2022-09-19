package br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CelestialAreaCreate {

    Long id;
    String name;
    Integer width;
    Integer height;
    LocalDateTime created = LocalDateTime.now();

}
