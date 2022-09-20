package br.com.tiagopedroso.moonprobe.entrypoint.model;

import br.com.tiagopedroso.moonprobe.logic.Orientation;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

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
public class Probe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    Integer x;

    @Column(nullable = false)
    Integer y;

    @Column(nullable = false)
    Orientation orientation;

    @Column(nullable = false)
    String commandSequence;

    @Column(nullable = false)
    LocalDateTime created;

    Integer totalMovements;

    //Relationship: side N
    @ManyToOne
    @JoinColumn(name = "celestialAreaId")
    @EqualsAndHashCode.Exclude
    CelestialArea celestialArea;

}