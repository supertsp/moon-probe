package br.com.tiagopedroso.moonprobe.entrypoint.dto.probe;

import br.com.tiagopedroso.moonprobe.logic.Orientation;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
public class ProbeResponse {

    Long id;
    String name;
    Integer x;
    Integer y;
    Orientation orientation;
    String commandSequence;
    LocalDateTime created = LocalDateTime.now();
    Long celestialAreaId;

}
