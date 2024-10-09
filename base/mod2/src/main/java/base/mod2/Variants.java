package base.mod2;

public enum Variants {
    ROCK, PAPER, SCISSORS;

    public Variants loosesTo() {
        return switch (this) {
            case ROCK -> PAPER;
            case PAPER -> SCISSORS;
            case SCISSORS -> ROCK;
        };
    }

}
