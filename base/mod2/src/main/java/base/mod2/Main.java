package base.mod2;

public class Main {

    public static void main(String[] args) {
        Player bot = new Player();
        Player alex = new Player(Variants.SCISSORS, "Alex");
        System.out.println(Player.whoWins(bot, alex));
    }

}
