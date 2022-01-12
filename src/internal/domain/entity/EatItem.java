package internal.domain.entity;

import java.util.Random;

public class EatItem {
    private static final Random RANDOM = new Random();
    private static final int BOUNDS = 100;
    private static final int START_RANGE = 0;

    private String itemName;
    private int dangerLevel;
    private int expectedHeelingHp;
    private String causeOfDeath;

    public EatItem(
            String name, int danger, int heelHP, String coroner) {
        this.itemName = name;
        this.dangerLevel = danger;
        this.expectedHeelingHp = heelHP;
        this.causeOfDeath = coroner;
    }

    public String getItemName() {
        return itemName;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public int getExpectedHeelingHP() {
        return expectedHeelingHp;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public boolean canEat() {
        int random = RANDOM.nextInt(BOUNDS);
        if (START_RANGE <= random && random <= dangerLevel) {
            return false;
        }
        return true;
    }
}
