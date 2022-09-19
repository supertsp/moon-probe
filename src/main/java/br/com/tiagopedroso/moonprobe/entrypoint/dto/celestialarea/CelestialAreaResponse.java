package br.com.tiagopedroso.moonprobe.entrypoint.dto.celestialarea;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CelestialAreaResponse {

    Long id;
    String name;
    Integer width;
    Integer height;
    LocalDateTime created;
    List<Long> probesId;

}
