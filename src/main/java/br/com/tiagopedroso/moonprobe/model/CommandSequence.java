package br.com.tiagopedroso.moonprobe.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class CommandSequence {

    @Getter
    private final String sequence;

    @Getter
    private final boolean validCommandSequence;

    @EqualsAndHashCode.Exclude
    private int currentIndex = START_INDEX;

    @EqualsAndHashCode.Exclude
    private boolean auxHasMoreCommand = true;

    @EqualsAndHashCode.Exclude
    private static final int START_INDEX = -1;

    public CommandSequence(String sequence) {
        this.sequence = sequence.toUpperCase().trim();
        validCommandSequence = validateCommandSequence();
    }

    private Command getCommand(int index) {
        if (!validCommandSequence) {
            return Command.NULL;
        }

        return Command.valueOf(
                String.valueOf(
                        sequence.charAt(index)
                )
        );
    }

    private void incrementCurrentIndex() {
        currentIndex++;

        if (isFinishedSequence()) {
            auxHasMoreCommand = false;
        }

        if (currentIndex >= sequence.length()) {
            currentIndex = sequence.length() -1;
        }
    }

    private boolean validateCommandSequence() {
        var commands = Command.values();
        var chars = sequence.toCharArray();
        var tempC = ' ';
        var findCommandIntoChar = false;

        for (char c : chars) {
            findCommandIntoChar = false;

            for (int index = 0; index < commands.length; index++) {
                tempC = commands[index].name().charAt(0);

                if (c == tempC) {
                    findCommandIntoChar = true;
                    break;
                }
            }

            if (!findCommandIntoChar) {
                return false;
            }
        }

        return true;
    }

    public void restartSequence() {
        currentIndex = START_INDEX;
        auxHasMoreCommand = true;
    }

    public boolean isFinishedSequence() {
        return (currentIndex + 1) == sequence.length();
    }

    public boolean isValidCommandSequence() {
        return validCommandSequence;
    }

    public Command getCurrentCommand() {
        if (currentIndex == START_INDEX) {
            return getCommand(currentIndex + 1);
        }

        return getCommand(currentIndex);
    }

    public Command getNextCommand() {
        incrementCurrentIndex();
        return getCurrentCommand();
    }

    public boolean hasMoreCommand() {
        return auxHasMoreCommand;
    }

}



