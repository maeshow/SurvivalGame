package internal.domain.entity;

public class SurvivalLog {
    private int hitPoint;
    private EatItem eatItem;
    private boolean isEat;

    public SurvivalLog(int hitPoint, EatItem eatItem, boolean isEat) {
        this.hitPoint = hitPoint;
        this.eatItem = eatItem;
        this.isEat = isEat;
    }

    public int hitPoint() {
        return hitPoint;
    }

    public EatItem eatItem() {
        return eatItem;
    }

    public boolean isEat() {
        return isEat;
    }
}
