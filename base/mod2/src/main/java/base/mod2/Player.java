package base.mod2;

import java.util.concurrent.ThreadLocalRandom;

public class Player {

    private final Variants variants;

    private final String name;

    public Player() {
        this.variants = Variants.values()[ThreadLocalRandom.current().nextInt(3)];
        this.name = "Bot";
    }

    public Player(Variants variants, String name) {
        this.variants = variants;
        this.name = name;
    }

    public static String whoWins(Player player1, Player player2) {
        if (player1.variants.loosesTo().equals(player2.variants)) {
            return "Победил игрок с именем: %s".formatted(player2.name);
        } else if (player2.variants.loosesTo().equals(player1.variants)) {
            return "Победил игрок с именем: %s".formatted(player1.name);
        } else {
            return "Ничья";
        }
    }

}
