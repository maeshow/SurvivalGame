package internal.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import internal.domain.entity.Player;

public class PlayerStatusManager {
    private static final int MAX_HP = 100;
    private static final int MIN_HP = 0;
    private static final int DEFAULT_HP = 50;
    private static final int DEFAULT_TIPS_COUNT = 0;

    private List<Player> players;

    public PlayerStatusManager() {
        players = new ArrayList<>();
        createPlayer();
    }

    public void createPlayer() {
        players.add(new Player(DEFAULT_HP, DEFAULT_TIPS_COUNT));
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public void addHitPoint(int index, int hitPoint) {
        Player player = players.get(index);
        int newHitPoint = player.hitPoint() + hitPoint;
        if (MAX_HP <= newHitPoint) {
            newHitPoint = MAX_HP;
        }
        player.hitPoint(newHitPoint);
    }

    public void removeHitPoint(int index, int hitPoint) {
        Player player = players.get(index);
        int newHitPoint = player.hitPoint() - hitPoint;
        if (newHitPoint <= MIN_HP) {
            newHitPoint = MIN_HP;
            setDeath(index);
        }
        player.hitPoint(newHitPoint);
    }

    public void countTips(int index) {
        Player player = players.get(index);
        int count = player.tipsCount() + 1;
        player.tipsCount(count);
    }

    public void setDeath(int index) {
        Player player = players.get(index);
        player.isAlive(false);
    }
}
