package internal.presentation;

import java.util.Scanner;

public class PlayerInput {
    private static final Scanner STDIN = new Scanner(System.in);
    private static final String INPUT_YES[] = { "Y", "y" };
    private static final String INPUT_NO[] = { "N", "n" };

    public boolean getPlayerInput() {
        String input = STDIN.next();
        Messages.showNewLine();
        if (!isCorrectString(input)) {
            Messages.showWithNewLine(Messages.ENTER_Y_OR_N_WARN);
            Messages.showNewLine();
            return getPlayerInput();
        }
        return isYes(input);
    }

    private boolean isCorrectString(String input) {
        for (String inputYes : INPUT_YES) {
            if (input.equals(inputYes)) {
                return true;
            }
        }
        for (String inputNo : INPUT_NO) {
            if (input.equals(inputNo)) {
                return true;
            }
        }
        return false;
    }

    private boolean isYes(String input) {
        for (String inputYes : INPUT_YES) {
            if (input.equals(inputYes)) {
                return true;
            }
        }
        return false;
    }
}
