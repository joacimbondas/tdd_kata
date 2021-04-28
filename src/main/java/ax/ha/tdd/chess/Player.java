package ax.ha.tdd.chess;

public enum Player {
    WHITE("W"),
    BLACK("B");

    private String symbol;

    private Player(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
