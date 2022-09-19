package br.com.tiagopedroso.moonprobe.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProbeTest {

    @Test
    void using_All_Args_Constructor_with_correct_values_Should_return_expected_coord_and_orientation() {
        //Given
        var name = "Sun Rover";
        var x = 1;
        var y = 2;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.NORTH;
        var commandSequence = new CommandSequence("LMLMLMLMM");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 1;
        var expectedY = 3;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.NORTH;

        //When
        probe.moveUntilLastCommand();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void using_No_Args_Constructor_with_correct_values_Should_return_expected_coord_and_orientation() {
        //Given
        var name = "Sun Rover";
        var x = 1;
        var y = 2;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.NORTH;
        var commandSequence = new CommandSequence("LMLMLMLMM");

        var probe = new Probe();
        probe.setName(name);
        probe.setCoord(coord);
        probe.setOrientation(orientation);
        probe.setCommandSequence(commandSequence);

        var expectedX = 1;
        var expectedY = 3;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.NORTH;

        //When
        probe.moveUntilLastCommand();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }


    @Test
    void using_Limits_Args_Constructor_with_correct_values_Should_return_expected_coord_and_orientation() {
        //Given
        var name = "Sun Rover";
        var x = 1;
        var y = 2;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.NORTH;
        var commandSequence = new CommandSequence("LMLMLMLMM");

        var probe = new Probe(name, widthLimit, heightLimit, orientation);
        probe.coord.setPositions(x, y);
        probe.setCommandSequence(commandSequence);

        var expectedX = 1;
        var expectedY = 3;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.NORTH;

        //When
        probe.moveUntilLastCommand();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void testing_commandSequence_scenario_MMRMMRMRRML() {
        //Given
        var name = "Sun Rover";
        var x = 3;
        var y = 3;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.EAST;
        var commandSequence = new CommandSequence("MMRMMRMRRML");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 5;
        var expectedY = 1;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.NORTH;

        //When
        probe.moveUntilLastCommand();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void testing_commandSequence_scenario_LRLRMM() {
        //Given
        var name = "Sun Rover";
        var x = 3;
        var y = 3;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.SOUTH;
        var commandSequence = new CommandSequence("LRLRMM");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 3;
        var expectedY = 1;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.SOUTH;

        //When
        probe.moveUntilLastCommand();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void testing_commandSequence_scenario_LRLRMM_three_times() {
        //Given
        var name = "Sun Rover";
        var x = 3;
        var y = 3;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.SOUTH;
        var commandSequence = new CommandSequence("LRLRMM");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 3;
        var expectedY = 0;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.SOUTH;

        //When
        System.out.println("-----------------------------");
        probe.moveUntilLastCommand();
        System.out.println(probe.getMovementDebugging());

        System.out.println("-----------------------------");
        probe.resetMovements();
        probe.moveUntilLastCommand();
        System.out.println(probe.getMovementDebugging());

        System.out.println("-----------------------------");
        probe.resetMovements();
        probe.moveUntilLastCommand();
        System.out.println(probe.getMovementDebugging());


        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;


        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void testing_commandSequence_scenario_LMLMLMLMM_three_times() {
        //Given
        var name = "Sun Rover";
        var x = 1;
        var y = 2;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.NORTH;
        var commandSequence = new CommandSequence("LMLMLMLMM");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 1;
        var expectedY = 5;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.NORTH;

        //When
        System.out.println("-----------------------------");
        probe.moveUntilLastCommand();
        System.out.println(probe.getMovementDebugging());

        System.out.println("-----------------------------");
        probe.resetMovements();
        probe.moveUntilLastCommand();
        System.out.println(probe.getMovementDebugging());

        System.out.println("-----------------------------");
        probe.resetMovements();
        probe.moveUntilLastCommand();
        System.out.println(probe.getMovementDebugging());


        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;


        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void testing_commandSequence_scenario_LMLMLMLMM_with_moveBack() {
        //Given
        var name = "Sun Rover";
        var x = 1;
        var y = 2;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.NORTH;
        var commandSequence = new CommandSequence("LMLMLMLMM");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 1;
        var expectedY = 2;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.NORTH;

        //When
        probe.moveUntilLastCommand();
        probe.moveBack();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }

    @Test
    void testing_commandSequence_scenario_LMLMLMLL_with_moveBack_Should_return_last_position_but_different_orientation() {
        //Given
        var name = "Sun Rover";
        var x = 1;
        var y = 2;
        var widthLimit = 5;
        var heightLimit = 5;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var orientation = Orientation.NORTH;
        var commandSequence = new CommandSequence("LMLMLMLL");

        var probe = new Probe(name, coord, orientation, commandSequence);

        var expectedX = 0;
        var expectedY = 1;
        var expectedCoord = new FitCoord(expectedX, expectedY, widthLimit, heightLimit);
        var expectedOrientation = Orientation.WEST;

        //When
        probe.moveUntilLastCommand();
        probe.moveBack();
        var resultCoord = probe.coord;
        var resultOrientation = probe.orientation;

        System.out.println(probe.getMovementDebugging());

        //Then
        assertEquals(expectedX, resultCoord.getX());
        assertEquals(expectedY, resultCoord.getY());
        assertEquals(expectedOrientation, resultOrientation);
        assertEquals(expectedCoord, resultCoord);
    }


}