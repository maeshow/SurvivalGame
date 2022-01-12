package internal.presentation;

public class Messages {
    public static final String PROLOGUE = "あなたは目が冷めたら無人島にいた。 目の前に１枚の手紙がある。 手紙にはこう書かれていた・・・%n「３０日間生き延びたら助けます」%nこうして無人島生活が始まった。%n";

    public static final String DAY_COUNT = "---%d日目---%n";
    public static final String HP = "残りHP：%d%n";
    public static final String EAT_ITEM_FOUND = "%s(危険度：%d％, 回復量：%d)を発見！%n";
    public static final String SAFE = "......大丈夫なようだ...体力が%d回復！%n";
    public static final String YOU_DEAD = "死んでしまった！死因：%s%n";
    public static final String HUNGER = "空腹で10ダメージ受けた！";
    public static final String COMPLETE = "%d日間生き延びた！%n不思議な力で島を出た！%n";

    public static final String WAINTING_INPUT = "食べますか？(Y/N)：";
    public static final String ENTER_Y_OR_N_WARN = "YかNを入力してください";

    public static void showWithNewLine(String message) {
        System.out.println(message);
    }

    public static void showWithoutNewLine(String message) {
        System.out.printf(message);
    }

    public static void showNewLine() {
        System.out.println();
    }

    public static void showFormattedMessage(String message) {
        System.out.format(message);
    }

    public static void showFormattedMessage(String message, int a) {
        System.out.format(message, a);
    }

    public static void showFormattedMessage(String message, String a) {
        System.out.format(message, a);
    }

    public static void showFormattedMessage(String message, String a, int b, int c) {
        System.out.format(message, a, b, c);
    }
}
