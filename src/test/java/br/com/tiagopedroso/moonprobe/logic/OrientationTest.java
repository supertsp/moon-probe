package br.com.tiagopedroso.moonprobe.logic;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrientationTest {

    @Test
    void when_starts_with_NORTH_and_getNext_with_rotateLikeClock_Should_return_EAST() {
        //Given
        var current = Orientation.NORTH;
        var rotateLikeClock = true;

        //When
        var result = Orientation.getNext(rotateLikeClock, current);

        //Then
        assertEquals(Orientation.EAST, result);
    }

    @Test
    void when_starts_with_WEST_and_getNext_with_rotateLikeClock_Should_return_NORTH() {
        //Given
        var current = Orientation.WEST;
        var rotateLikeClock = true;

        //When
        var result = Orientation.getNext(rotateLikeClock, current);

        //Then
        assertEquals(Orientation.NORTH, result);
    }

    @Test
    void when_starts_with_NORTH_and_getNext_with_not_rotateLikeClock_Should_return_WEST() {
        //Given
        var current = Orientation.NORTH;
        var rotateLikeClock = false;

        //When
        var result = Orientation.getNext(rotateLikeClock, current);

        //Then
        assertEquals(Orientation.WEST, result);
    }

    @Test
    void when_starts_with_SOUTH_and_getNext_with_not_rotateLikeClock_Should_return_EAST() {
        //Given
        var current = Orientation.SOUTH;
        var rotateLikeClock = false;

        //When
        var result = Orientation.getNext(rotateLikeClock, current);

        //Then
        assertEquals(Orientation.EAST, result);
    }

}