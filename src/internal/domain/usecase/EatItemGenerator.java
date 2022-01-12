package internal.domain.usecase;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import internal.domain.entity.EatItem;

public class EatItemGenerator {
    private static final Random RANDOM = new Random();
    List<EatItem> eatItems;
    List<EatItem> alms;

    public EatItemGenerator() {
        eatItems = new ArrayList<>();
        alms = new ArrayList<>();
        eatItemsGenerate();
    }

    private void eatItemsGenerate() {
        eatItems.add(new EatItem("毒蛇", 15, 30, "毒蛇の毒に負けた"));
        eatItems.add(new EatItem("漂流物（缶詰）", 30, 50, "歯では開けられなかった。歯が全部折れて出血死…"));
        eatItems.add(new EatItem("流木", 8, 20, "バイキンだらけだった…"));
        eatItems.add(new EatItem("落ち葉", 5, 20, "口の中の水分を全部持っていかれた…"));
        eatItems.add(new EatItem("毒々しいキノコ", 10, 30, "笑いが止まらず疲れて死んだ…"));
        eatItems.add(new EatItem("カラフルフルーツ", 5, 30, "種が喉に詰まった…"));
    }

    public void almsGenerate(int maxDay) {
        for (int i = 0; i < maxDay; i++) {
            int index = RANDOM.nextInt(eatItems.size());
            alms.add(eatItems.get(index));
        }
    }

    public List<EatItem> getAlms() {
        return alms;
    }
}
