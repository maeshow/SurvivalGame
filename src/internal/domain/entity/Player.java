package internal.domain.entity;

public class Player {
    private int hitPoint;
    private int tipsCount;
    private boolean isAlive;

    public Player(int hitPoint, int tipsCount) {
        this.hitPoint = hitPoint;
        this.tipsCount = tipsCount;
        this.isAlive = true;
    }

    public int hitPoint() {
        return hitPoint;
    }

    public void hitPoint(int hitPoint) {
        this.hitPoint = hitPoint;
    }

    public int tipsCount() {
        return tipsCount;
    }

    public void tipsCount(int tipsCount) {
        this.tipsCount = tipsCount;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void isAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }
}
