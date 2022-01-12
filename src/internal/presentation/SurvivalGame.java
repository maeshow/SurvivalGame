package internal.presentation;

import java.util.List;
import java.util.Scanner;

import internal.domain.entity.EatItem;
import internal.domain.entity.Player;
import internal.domain.usecase.EatItemGenerator;
import internal.domain.usecase.PlayerStatusManager;
import internal.domain.usecase.SurvivalLogGenerator;

public class SurvivalGame {
    private static final Scanner STDIN = new Scanner(System.in);
    private static final String INPUT_YES[] = { "Y", "y" };
    private static final String INPUT_NO[] = { "N", "n" };
    private static final int MAX_DAY = 30;
    private static final int DEFAULT_DAY = 1;
    private static final int PLAYER_INDEX = 0;
    private static final int MIN_HP = 0;
    private static final int REMOVE_HP = 10;
    private static final int MAX_TIPS_COUNT = 3;

    private static final String HUNGER = "空腹";

    PlayerStatusManager playerStatusManager;
    SurvivalLogGenerator survivalLogGenerator;
    EatItemGenerator eatItemGenerator;

    public SurvivalGame() {
        playerStatusManager = new PlayerStatusManager();
        survivalLogGenerator = new SurvivalLogGenerator();
        eatItemGenerator = new EatItemGenerator();
        eatItemGenerator.almsGenerate(MAX_DAY);
    }

    public void startGame() {
        Messages.showFormattedMessage(Messages.PROLOGUE);
        startSurvival();
        Messages.showResult(survivalLogGenerator.getLogs());
    }

    public void startSurvival() {
        int day = DEFAULT_DAY;
        Player player = playerStatusManager.getPlayer(PLAYER_INDEX);
        List<EatItem> alms = eatItemGenerator.getAlms();
        while (isWithinDay(day)) {
            Messages.showFormattedMessage(Messages.DAY_COUNT, day);
            Messages.showFormattedMessage(Messages.HP, player.hitPoint());
            EatItem eatItem = alms.get(day);
            Messages.showFormattedMessage(Messages.EAT_ITEM_FOUND, eatItem.getItemName(), eatItem.getDangerLevel(),
                    eatItem.getExpectedHeelingHP());
            if (hasTips(player, day)) {
                Messages.showWithoutNewLine(Messages.TIPS_WAITING_INPUT);
                if (getPlayerInput()) {
                    playerStatusManager.countTips(PLAYER_INDEX);
                    EatItem tips = alms.get(day + 1);
                    Messages.showFormattedMessage(Messages.EAT_ITEM_TIPS, tips.getItemName(), tips.getDangerLevel(),
                            tips.getExpectedHeelingHP());
                }
            }
            Messages.showWithoutNewLine(Messages.EAT_WAITING_INPUT);
            if (getPlayerInput()) {
                if (eatItem.canEat()) {
                    int healHp = eatItem.getExpectedHeelingHP();
                    playerStatusManager.addHitPoint(PLAYER_INDEX, healHp);
                    Messages.showFormattedMessage(Messages.SAFE, healHp);
                    survivalLogGenerator.addLog(player.hitPoint(), eatItem, true);
                    day++;
                    continue;
                }
                survivalLogGenerator.addLog(player.hitPoint(), eatItem, true);
                Messages.showFormattedMessage(Messages.YOU_DEAD, eatItem.getCauseOfDeath());
                return;
            }
            Messages.showWithNewLine(Messages.HUNGER);
            playerStatusManager.removeHitPoint(PLAYER_INDEX, REMOVE_HP);
            if (!isAlive(player)) {
                Messages.showFormattedMessage(Messages.YOU_DEAD, HUNGER);
            }
            survivalLogGenerator.addLog(player.hitPoint(), eatItem, false);
            day++;
        }
        Messages.showFormattedMessage(Messages.COMPLETE, day);
    }

    private boolean isWithinDay(int day) {
        if (MAX_DAY < day) {
            return false;
        }
        return true;
    }

    private boolean getPlayerInput() {
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

    private boolean isAlive(Player player) {
        if (player.hitPoint() <= MIN_HP) {
            return false;
        }
        return true;
    }

    private boolean hasTips(Player player, int day) {
        if (player.tipsCount() < MAX_TIPS_COUNT) {
            if (day < MAX_DAY) {
                return true;
            }
        }
        return false;
    }
}
