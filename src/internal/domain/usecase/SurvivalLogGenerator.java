package internal.domain.usecase;

import java.util.ArrayList;
import java.util.List;

import internal.domain.entity.EatItem;
import internal.domain.entity.SurvivalLog;

public class SurvivalLogGenerator {
    List<SurvivalLog> logs;

    public SurvivalLogGenerator() {
        logs = new ArrayList<>();
    }

    public void addLog(int hitPoint, EatItem eatItem, boolean isEat) {
        logs.add(new SurvivalLog(hitPoint, eatItem, isEat));
    }

    public List<SurvivalLog> getLogs() {
        return logs;
    }
}
