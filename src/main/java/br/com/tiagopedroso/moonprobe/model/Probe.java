package br.com.tiagopedroso.moonprobe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Probe {

    public String name = "";
    public FitCoord coord = new FitCoord();
    public Orientation orientation = Orientation.NORTH;

    public CommandSequence commandSequence;

    public Probe(String name, Integer widthLimit, Integer heightLimit, Orientation orientation) {
        setName(name);
        coord = new FitCoord(widthLimit, heightLimit);
        this.orientation = orientation;
        var command = Command.L;
    }




}
