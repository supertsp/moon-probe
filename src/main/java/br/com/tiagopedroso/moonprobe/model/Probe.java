package br.com.tiagopedroso.moonprobe.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class Probe {

    @Getter
    @Setter
    public String name = "";

    @Getter
    @Setter
    public FitCoord coord = new FitCoord();

    @Getter
    @Setter
    public Orientation orientation = Orientation.NORTH;

    @Getter
    private CommandSequence commandSequence;

    @Getter
    @EqualsAndHashCode.Exclude
    private int totalMovements = 0;

    @EqualsAndHashCode.Exclude
    private StringBuilder movementDebugging;

    public Probe() {
        startsMovementDebugging();
    }

    public Probe(String name, FitCoord coord, Orientation orientation, CommandSequence commandSequence) {
        setName(name);
        setCoord(coord);
        setOrientation(orientation);
        setCommandSequence(commandSequence);

        startsMovementDebugging();
    }

    public Probe(String name, Integer widthLimit, Integer heightLimit, Orientation orientation) {
        this(name, new FitCoord(widthLimit, heightLimit), orientation, null);
    }

    private void startsMovementDebugging() {
        movementDebugging = new StringBuilder();
        movementDebugging.append("""
                STARTING       -   Moves: %d
                x: %d, y: %d - %s
                    y: %d, x: %d \n
                """.formatted(totalMovements, coord.getX(), coord.getY(), orientation, coord.getY(), coord.getX())
        );
    }

    public boolean move() {
        if (commandSequence == null || !commandSequence.isValid() || !commandSequence.hasMoreCommand()) {
            return false;
        }

        var currentCommand = commandSequence.getNextCommand();

        switch (currentCommand) {
            case R -> orientation = Orientation.getNext(true, orientation);

            case L -> orientation = Orientation.getNext(false, orientation);

            case M -> {
                switch (orientation) {
                    case NORTH -> coord.incrementY(1);

                    case EAST -> coord.incrementX(1);

                    case SOUTH -> coord.incrementY(-1);

                    case WEST -> coord.incrementX(-1);
                }
            }
        }

        totalMovements++;

        movementDebugging.append("""
                Command: %s     -   Moves: %d
                x: %d, y: %d - %s
                    y: %d, x: %d \n          
                """.formatted(currentCommand, totalMovements, coord.getX(), coord.getY(), orientation, coord.getY(), coord.getX())
        );

        return true;
    }

    public void moveUntilLastCommand() {
        if (commandSequence != null && commandSequence.isValid()) {
            while (move());
        }
    }

    public void resetMovements() {
        totalMovements = 0;
        startsMovementDebugging();

        if (commandSequence != null) {
            commandSequence.restartSequence();
        }
    }

    public void setCommandSequence(CommandSequence commandSequence) {
        this.commandSequence = commandSequence;
        resetMovements();
    }

    public String getMovementDebugging() {
        return movementDebugging.toString();
    }


}
