package br.com.tiagopedroso.moonprobe.logic;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
public class Probe {

    @Getter
    @Setter
    public String name = "";

    @Getter
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

    @EqualsAndHashCode.Exclude
    private int lastPositionOfX = 0;

    @EqualsAndHashCode.Exclude
    private int lastPositionOfY = 0;

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

    private void capturesMovementDebugging(Command currentCommand) {
        movementDebugging.append("""
                Command: %s     -   Moves: %d
                x: %d, y: %d - %s
                    y: %d, x: %d \n          
                """.formatted(currentCommand, totalMovements, coord.getX(), coord.getY(), orientation, coord.getY(), coord.getX())
        );
    }

    private void updateLastPositions() {
        lastPositionOfX = coord.getX();
        lastPositionOfY = coord.getY();
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
                updateLastPositions();

                switch (orientation) {
                    case NORTH -> coord.incrementY(1);

                    case EAST -> coord.incrementX(1);

                    case SOUTH -> coord.incrementY(-1);

                    case WEST -> coord.incrementX(-1);
                }
            }
        }

        totalMovements++;

        capturesMovementDebugging(currentCommand);

        return true;
    }

    public void moveBack() {
        coord.setPositions(lastPositionOfX, lastPositionOfY);
        totalMovements++;
        capturesMovementDebugging(Command.BACK);
    }

    public void moveUntilLastCommand() {
        if (commandSequence != null && commandSequence.isValid()) {
            while (move()) ;
        }
    }

    public boolean hasMoreMoves() {
        return commandSequence.hasMoreCommand();
    }

    public void resetMovements() {
        totalMovements = 0;
        startsMovementDebugging();

        if (commandSequence != null) {
            commandSequence.restartSequence();
        }
    }

    public void setCoord(FitCoord coord) {
        if (coord != null) {
            this.coord = coord;
            updateLastPositions();
        }
    }

    public void setCommandSequence(CommandSequence commandSequence) {
        this.commandSequence = commandSequence;
        resetMovements();
    }

    public String getMovementDebugging() {
        return movementDebugging.toString();
    }

    @Override
    public String toString() {
        return """
                { "class": "%s", "fields": {
                    "name": %s,
                    "coord": %s,
                    "orientation": %s,
                    "commandSequence": %s,
                    "totalMovements": %d,
                    "lastPositionOfX": %d,
                    "lastPositionOfY": %s
                }}"""
                .formatted(
                        this.getClass().getSimpleName(),
                        name,
                        coord,
                        orientation,
                        commandSequence,
                        totalMovements,
                        lastPositionOfX,
                        lastPositionOfY
                );
    }
}
