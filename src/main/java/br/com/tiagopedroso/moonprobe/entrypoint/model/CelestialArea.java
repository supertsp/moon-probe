package br.com.tiagopedroso.moonprobe.entrypoint.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
/*
Using Serializable: https://docs.spring.io/spring-boot/docs/current/reference/html/data.html#data
 */
public class CelestialArea implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer width;

    @Column(nullable = false)
    Integer height;

    @Column(nullable = false)
    LocalDateTime created;

    //Relationship: side 1
    @OneToMany(mappedBy = "celestialArea", cascade = CascadeType.ALL)
    @EqualsAndHashCode.Exclude
    List<Probe> probes = new ArrayList<>();

    public CelestialArea(Long id, String name, Integer width, Integer height, LocalDateTime created) {
        this.id = id;
        this.name = name;
        this.width = width;
        this.height = height;
        this.created = created;
    }
}
