package br.com.tiagopedroso.moonprobe.model;

import br.com.tiagopedroso.moonprobe.infra.exception.FitCoordException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class FitCoordTest {

    @Test
    void using_No_Args_Constructor_Should_return_fields_equals_zero() {
        //Given
        var coord = new FitCoord();

        //When
        //Then
        assertEquals(coord.getX(), 0);
        assertEquals(coord.getY(), 0);
        assertEquals(coord.getWidthLimit(), 0);
        assertEquals(coord.getHeightLimit(), 0);
    }

    @Test
    void using_All_Args_Constructor_with_correct_values_Should_return_fields_with_same_values() {
        //Given
        var x = 3;
        var y = 1;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);

        //When
        //Then
        assertEquals(coord.getX(), x);
        assertEquals(coord.getY(), y);
        assertEquals(coord.getWidthLimit(), widthLimit);
        assertEquals(coord.getHeightLimit(), heightLimit);
    }

    @Test
    void given_correct_values_to_setters_Should_return_fields_with_same_values() {
        //Given
        var x = 8;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord();

        //When
        coord.setWidthLimit(widthLimit);
        coord.setHeightLimit(heightLimit);
        coord.setX(x);
        coord.setY(y);

        //Then
        assertEquals(coord.getX(), x);
        assertEquals(coord.getY(), y);
        assertEquals(coord.getWidthLimit(), widthLimit);
        assertEquals(coord.getHeightLimit(), heightLimit);
    }

    @Test
    void using_Limits_Args_Constructor_with_correct_values_Should_return_fields_with_same_values() {
        //Given
        var x = 0;
        var y = 0;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(widthLimit, heightLimit);

        //When
        //Then
        assertEquals(coord.getX(), x);
        assertEquals(coord.getY(), y);
        assertEquals(coord.getWidthLimit(), widthLimit);
        assertEquals(coord.getHeightLimit(), heightLimit);
    }

    @Test
    void given_widthLimit_value_less_then_0_on_Limits_Args_Constructor_Should_throw_error() {
        //Given
        var widthLimit = -1;
        var heightLimit = 8;

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> new FitCoord(widthLimit, heightLimit)
        );
    }

    @Test
    void given_heightLimit_value_less_then_0_on_Limits_Args_Constructor_Should_throw_error() {
        //Given
        var widthLimit = 10;
        var heightLimit = -1;

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> new FitCoord(widthLimit, heightLimit)
        );
    }

    @Test
    void given_x_value_greater_then_widthLimit_on_All_Args_Constructor_Should_throw_error() {
        //Given
        var x = 13;
        var y = 1;
        var widthLimit = 10;
        var heightLimit = 8;
        var message = FitCoordException.MESSAGE_OUTSIDE_LIMIT.formatted("x", x, "widthLimit", widthLimit);

        //When
        var error = assertThrows(FitCoordException.class,
                () -> new FitCoord(x, y, widthLimit, heightLimit)
        );

        //Then [Note: optional step]
        assertEquals(message, error.getMessage());
    }

    @Test
    void given_x_value_less_then_0_on_All_Args_Constructor_Should_throw_error() {
        //Given
        var x = -1;
        var y = 1;
        var widthLimit = 10;
        var heightLimit = 8;

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> new FitCoord(x, y, widthLimit, heightLimit)
        );
    }

    @Test
    void given_x_value_greater_then_widthLimit_Should_throw_error_and_x_equals_zero() {
        //Given
        var x = 13;
        var y = 1;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(widthLimit, heightLimit);


        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> coord.setX(x)
        );

        assertEquals(coord.getX(), 0);
    }

    @Test
    void given_x_value_less_then_0_Should_throw_error_and_x_equals_zero() {
        //Given
        var x = -1;
        var y = 1;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(widthLimit, heightLimit);

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> coord.setX(x)
        );

        assertEquals(coord.getX(), 0);
    }

    @Test
    void given_y_value_greater_then_heightLimit_on_All_Args_Constructor_Should_throw_error() {
        //Given
        var x = 10;
        var y = 10;
        var widthLimit = 10;
        var heightLimit = 8;

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> new FitCoord(x, y, widthLimit, heightLimit)
        );
    }

    @Test
    void given_y_value_less_then_0_on_All_Args_Constructor_Should_throw_error() {
        //Given
        var x = 0;
        var y = -10;
        var widthLimit = 10;
        var heightLimit = 8;

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> new FitCoord(x, y, widthLimit, heightLimit)
        );
    }

    @Test
    void given_y_value_greater_then_heightLimit_Should_throw_error_and_y_equals_zero() {
        //Given
        var x = 10;
        var y = 10;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(widthLimit, heightLimit);


        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> coord.setY(y)
        );

        assertEquals(coord.getY(), 0);
    }

    @Test
    void given_y_value_less_then_0_Should_throw_error_and_y_equals_zero() {
        //Given
        var x = 10;
        var y = -1;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(widthLimit, heightLimit);

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> coord.setY(y)
        );

        assertEquals(coord.getY(), 0);
    }

    @Test
    void given_widthLimit_value_less_then_0_Should_throw_error_and_widthLimit_equals_zero() {
        //Given
        var widthLimit = -1;
        var heightLimit = 8;
        var coord = new FitCoord();

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> coord.setWidthLimit(widthLimit)
        );

        assertEquals(coord.getWidthLimit(), 0);
    }

    @Test
    void given_heightLimit_value_less_then_0_Should_throw_error_and_heightLimit_equals_zero() {
        //Given
        var widthLimit = 10;
        var heightLimit = -1;
        var coord = new FitCoord();

        //When
        //Then
        assertThrows(FitCoordException.class,
                () -> coord.setHeightLimit(heightLimit)
        );

        assertEquals(coord.getHeightLimit(), 0);
    }

    @Test
    void given_correct_x_increment_value_Should_return_x_plus_increment() {
        //Given
        var x = 5;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var increment = 2;

        //When
        coord.incrementX(increment);

        //Then
        assertEquals(coord.getX(), x + increment);
    }

    @Test
    void given_wrong_x_increment_with_negative_value_Should_return_zero() {
        //Given
        var x = 5;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var increment = -10;

        //When
        coord.incrementX(increment);

        //Then
        assertEquals(coord.getX(), 0);
    }

    @Test
    void given_wrong_x_increment_with_positive_value_Should_return_widthLimit() {
        //Given
        var x = 5;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var increment = 10;

        //When
        coord.incrementX(increment);

        //Then
        assertEquals(coord.getX(), widthLimit);
    }


    @Test
    void given_correct_y_increment_value_Should_return_y_plus_increment() {
        //Given
        var x = 5;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var increment = 2;

        //When
        coord.incrementY(increment);

        //Then
        assertEquals(coord.getY(), y + increment);
    }

    @Test
    void given_wrong_y_increment_with_negative_value_Should_return_zero() {
        //Given
        var x = 5;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var increment = -10;

        //When
        coord.incrementY(increment);

        //Then
        assertEquals(coord.getY(), 0);
    }

    @Test
    void given_wrong_y_increment_with_positive_value_Should_return_heightLimit() {
        //Given
        var x = 5;
        var y = 3;
        var widthLimit = 10;
        var heightLimit = 8;
        var coord = new FitCoord(x, y, widthLimit, heightLimit);
        var increment = 10;

        //When
        coord.incrementY(increment);

        //Then
        assertEquals(coord.getY(), heightLimit);
    }

}