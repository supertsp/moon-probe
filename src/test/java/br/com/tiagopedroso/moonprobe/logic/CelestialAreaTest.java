package br.com.tiagopedroso.moonprobe.logic;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class CelestialAreaTest {

    public static List<Probe> probes = new ArrayList<>(4);

    @BeforeAll
    public static void initProbes() {
        probes.add(new Probe(
                        "Sun Rover",
                        new FitCoord(1, 2, 5, 5),
                        Orientation.NORTH,
                        new CommandSequence("RM")
                )
        );

        probes.add(new Probe(
                        "Athena Rover",
                        new FitCoord(3, 3, 5, 5),
                        Orientation.EAST,
                        new CommandSequence("M")
                )
        );

        probes.add(new Probe(
                        "Ranger Rover",
                        new FitCoord(2, 1, 3, 3),
                        Orientation.SOUTH,
                        new CommandSequence("MLLMM")
                )
        );

        probes.add(new Probe(
                        "Flower Rover",
                        new FitCoord(20, 9, 30, 10),
                        Orientation.WEST,
                        new CommandSequence("RRM")
                )
        );
    }


    @Test
    void moveProbesStepByStepUntilLastCommand() {
        var name = "Moon Area";
        var width = 6;
        var height = 6;
        var moonArea = new CelestialArea(name, width, height);

        moonArea.addAllProbes(probes);

        System.out.println("START POSITION");
        moonArea.forEachProbe(probe -> {
            System.out.println("""
                %15s - %d, %d - %s
            """.formatted(
                    probe.name,
                    probe.coord.getX(),
                    probe.coord.getY(),
                    probe.orientation
            ));
        });

        System.out.println("------------------------------");
        System.out.println("END POSITION");

        moonArea.moveProbesStepByStepUntilLastCommand();

        moonArea.forEachProbe(probe -> {
            System.out.println("""
                %15s - %d, %d - %s
            """.formatted(
                probe.name,
                    probe.coord.getX(),
                    probe.coord.getY(),
                    probe.orientation
            ));
        });
    }
}