package br.com.tiagopedroso.moonprobe.logic;

import br.com.tiagopedroso.moonprobe.infra.exception.CelestialAreaException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;
import java.util.function.Consumer;


@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class CelestialArea {

    @Getter
    private String name = "";

    @Getter
    private Integer
            width = 0,
            height = 0;

    @Getter
    private List<Probe> probes = new ArrayList<>();

    public CelestialArea(String name, Integer width, Integer height) {
        setName(name);
        setDimensions(width, height);
    }

    private void fixProbePositions() {
        FitCoord coordSelected = null;
        FitCoord coordComparator = null;

        for (int indexSelected = 0; indexSelected < probes.size(); indexSelected++) {
            for (int indexComparator = indexSelected + 1; indexComparator < probes.size(); indexComparator++) {
                coordSelected = probes.get(indexSelected).coord;
                coordComparator = probes.get(indexComparator).coord;

                if (coordSelected.equals(coordComparator)) {
                    probes.get(indexComparator).moveBack();
                }
            }
        }
    }

    private boolean isProbePositionAlreadyOccupied(Probe probe) {
        FitCoord coordSelected = null;
        FitCoord coordComparator = null;

        for (int indexSelected = 0; indexSelected < probes.size(); indexSelected++) {
            for (int indexComparator = indexSelected + 1; indexComparator < probes.size(); indexComparator++) {
                coordSelected = probes.get(indexSelected).coord;
                coordComparator = probes.get(indexComparator).coord;

                if (coordSelected.equals(coordComparator)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean hasAnyProbesStillOnMovement() {
        for (var probe : probes) {
            if (!probe.hasMoreMoves()) {
                return false;
            }
        }

        return true;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(Integer width) {
        if (width >= 0) {
            this.width = width;
        } else {
            this.width = 0;
        }
    }

    public void setHeight(Integer height) {
        if (height >= 0) {
            this.height = height;
        } else {
            this.height = 0;
        }
    }

    public void setDimensions(Integer width, Integer height) {
        setWidth(width);
        setHeight(height);
    }

    public void addProbe(Probe probe) {
        if (probe != null && probe.coord != null) {

            probe.coord.setLimits(width, height);

            if (isProbePositionAlreadyOccupied(probe)) {
                throw new CelestialAreaException(probe.coord, probe.name);
            }

            probes.add(probe);
        }
    }

    public void addAllProbes(List<Probe> probes) {
        probes.forEach(probe -> addProbe(probe));
    }

    public void addAllProbes(Probe... probes) {
        for (var probe : probes) {
            addProbe(probe);
        }
    }

    public void removeProbe(Probe probe) {
        probes.remove(probe);
    }

    public void removeProbeByIndex(int index) {
        probes.remove(index);
    }

    public void removeAllProbes() {
        probes.clear();
    }

    public Probe getProbe(int index) {
        return probes.get(index);
    }

    public void forEachProbe(Consumer<Probe> action) {
        Objects.requireNonNull(action);
        for (Probe e : probes) {
            action.accept(e);
        }
    }

    public int lengthOfProbes() {
        return probes.size();
    }

    public boolean moveProbeByIndex(int index) {
        if (index >= 0 && index < lengthOfProbes()) {
            var hasMove = getProbe(index).move();
            fixProbePositions();
            return hasMove;
        }

        return false;
    }

    public boolean moveProbe(Probe probe) {
        if (probe != null) {
            int index = probes.indexOf(probe);

            if (index > -1) {
                return moveProbeByIndex(index);
            }
        }

        return false;
    }

    public void moveProbesStepByStepUntilLastCommand() {
        Map<Integer, String> probesRemoved = new HashMap<>();

        if (hasAnyProbesStillOnMovement()) {
            for (int index = 0; probesRemoved.size() < probes.size(); index++) {
                index = index >= probes.size() ? 0 : index;

                if (!moveProbeByIndex(index)) {
                    probesRemoved.put(index, getProbe(index).name);
                    System.out.println("\n\n totalMovements of " + getProbe(index).name + " " + getProbe(index).getTotalMovements());
                }
            }
        }
    }

}
