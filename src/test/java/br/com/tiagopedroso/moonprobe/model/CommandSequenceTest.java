package br.com.tiagopedroso.moonprobe.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandSequenceTest {

    @Test
    void when_create_valid_upper_case_sequence_Should_return_true() {
        //Given
        var sequence = "LMLMLMLMM";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, true);
    }

    @Test
    void when_create_valid_lower_case_sequence_Should_return_true() {
        //Given
        var sequence = "lmlmlmlmm";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, true);
    }

    @Test
    void when_create_valid_char_Should_return_true() {
        //Given
        var sequence = "R";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, true);
    }

    @Test
    void when_create_invalid_char_Should_return_false() {
        //Given
        var sequence = "K";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, false);
    }

    @Test
    void when_create_invalid_sequence_with_wrong_char_in_the_middle_Should_return_false() {
        //Given
        var sequence = "LMLMLKMLMM";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, false);
    }

    @Test
    void when_create_invalid_sequence_with_wrong_char_at_the_beginning_Should_return_false() {
        //Given
        var sequence = "KLMLMLMLMM";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, false);
    }

    @Test
    void when_create_invalid_sequence_with_wrong_char_at_the_end_Should_return_false() {
        //Given
        var sequence = "LMLMLMLMMK";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isValid();

        //Then
        assertEquals(result, false);
    }

    @Test
    void when_getCurrentCommand_after_create_object_Should_return_first_command() {
        //Given
        var sequence = "LMR";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.getCurrentCommand();

        //Then
        assertEquals(result, Command.L);
    }

    @Test
    void when_isFinishedSequence_after_create_object_Should_return_false() {
        //Given
        var sequence = "LMR";
        var commands = new CommandSequence(sequence);

        //When
        var result = commands.isFinishedSequence();

        //Then
        assertEquals(result, false);
    }

    @Test
    void when_getCurrentCommand_after_getNextCommand_Should_return_first_command() {
        //Given
        var sequence = "LMR";
        var commands = new CommandSequence(sequence);

        //When
        commands.getNextCommand();
        var result = commands.getCurrentCommand();

        //Then
        assertEquals(result, Command.L);
    }

    @Test
    void when_hasMoreCommand_in_a_loop_Should_result_in_same_sequence() {
        //Given
        var sequence = "LMR";
        var commands = new CommandSequence(sequence);
        var result = "";

        //When
        while (commands.hasMoreCommand()) {
            result += commands.getNextCommand().name();
        }

        //Then
        assertEquals(result, commands.getSequence());
    }

    @Test
    void when_hasMoreCommand_in_a_loop_with_invalid_sequence_Should_result_in_null_sequence() {
        //Given
        var sequence = "LMRk";
        var commands = new CommandSequence(sequence);
        var result = "";

        //When
        while (commands.hasMoreCommand()) {
            result += commands.getNextCommand().name();
        }

        //Then
        assertEquals(result, "NULLNULLNULLNULL");
    }

    @Test
    void when_isFinishedSequence_after_loop_Should_return_true() {
        //Given
        var sequence = "LMR";
        var commands = new CommandSequence(sequence);

        //When
        while (commands.hasMoreCommand()) {
            commands.getNextCommand();
        }
        var result = commands.isFinishedSequence();


        //Then
        assertEquals(result, true);
    }

    @Test
    void when_restartSequence_after_loop_Should_return_false_for_isFinishedSequence() {
        //Given
        var sequence = "LMR";
        var commands = new CommandSequence(sequence);

        //When
        while (commands.hasMoreCommand()) {
            commands.getNextCommand();
        }
        commands.restartSequence();
        var result = commands.isFinishedSequence();


        //Then
        assertEquals(result, false);
    }

}