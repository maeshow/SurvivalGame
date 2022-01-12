package internal.presentation;

import java.util.List;

import internal.domain.entity.EatItem;
import internal.domain.entity.Player;
import internal.domain.usecase.EatItemGenerator;
import internal.domain.usecase.PlayerStatusManager;
import internal.domain.usecase.SurvivalLogGenerator;

public class SurvivalGame {
    private static final int MAX_DAY = 30;
    private static final int DEFAULT_DAY = 1;
    private static final int PLAYER_INDEX = 0;
    private static final int MIN_HP = 0;
    private static final int REMOVE_HP = 10;
    private static final int MAX_TIPS_COUNT = 3;

    PlayerInput playerInput;
    PlayerStatusManager playerStatusManager;
    SurvivalLogGenerator survivalLogGenerator;
    EatItemGenerator eatItemGenerator;

    public SurvivalGame() {
        playerInput = new PlayerInput();
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

    private void startSurvival() {
        int day = DEFAULT_DAY;
        Player player = playerStatusManager.getPlayer(PLAYER_INDEX);
        List<EatItem> eatItems = eatItemGenerator.getAlms();
        while (isWithinDay(day)) {
            workOfDay(player, day, eatItems);
            if (!isAlive(player)) {
                return;
            }
            day++;
        }
        Messages.showFormattedMessage(Messages.COMPLETE, day - 1);
    }

    private void workOfDay(Player player, int day, List<EatItem> eatItems) {
        EatItem eatItem = eatItems.get(day - 1);
        Messages.showFormattedMessage(Messages.DAY_COUNT, day);
        Messages.showFormattedMessage(Messages.HP, player.hitPoint());
        Messages.showFormattedMessage(Messages.EAT_ITEM_FOUND, eatItem.getItemName(), eatItem.getDangerLevel(),
                eatItem.getExpectedHeelingHP());
        selectIfUseTips(player, day, eatItems);
        selectIfEatItem(player, eatItem);
    }

    private void selectIfUseTips(Player player, int day, List<EatItem> eatItems) {
        if (hasTips(player, day)) {
            Messages.showWithoutNewLine(Messages.TIPS_WAITING_INPUT);
            if (playerInput.getPlayerInput()) {
                playerStatusManager.countTips(PLAYER_INDEX);
                EatItem tips = eatItems.get(day);
                Messages.showFormattedMessage(Messages.EAT_ITEM_TIPS, tips.getItemName(), tips.getDangerLevel(),
                        tips.getExpectedHeelingHP());
            }
        }
    }

    private void selectIfEatItem(Player player, EatItem eatItem) {
        Messages.showWithoutNewLine(Messages.EAT_WAITING_INPUT);
        if (playerInput.getPlayerInput()) {
            boolean canEat = eatItem.canEat();
            if (canEat) {
                int healHp = eatItem.getExpectedHeelingHP();
                playerStatusManager.addHitPoint(PLAYER_INDEX, healHp);
                Messages.showFormattedMessage(Messages.SAFE, healHp);
            }
            if (!canEat) {
                player.hitPoint(MIN_HP);
                Messages.showFormattedMessage(Messages.YOU_DEAD, eatItem.getCauseOfDeath());
            }
            survivalLogGenerator.addLog(player.hitPoint(), eatItem, true);
            return;
        }
        Messages.showWithNewLine(Messages.DAMAGE);
        playerStatusManager.removeHitPoint(PLAYER_INDEX, REMOVE_HP);
        if (!isAlive(player)) {
            Messages.showFormattedMessage(Messages.YOU_DEAD, Messages.HUNGER);
        }
        survivalLogGenerator.addLog(player.hitPoint(), eatItem, false);
    }

    private boolean isWithinDay(int day) {
        if (MAX_DAY < day) {
            return false;
        }
        return true;
    }

    private boolean hasTips(Player player, int day) {
        if (player.tipsCount() < MAX_TIPS_COUNT) {
            if (day < MAX_DAY) {
                return true;
            }
            Messages.showWithNewLine(Messages.TIPS_NOT_FOUND);
        }
        return false;
    }

    private boolean isAlive(Player player) {
        if (player.hitPoint() <= MIN_HP) {
            return false;
        }
        return true;
    }
}
