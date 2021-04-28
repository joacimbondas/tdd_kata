package ax.ha.tdd.chess.pieces;

public enum PieceType {
    PAWN("P"),
    ROOK("R"),
    KNIGHT("K"),
    BISHOP("B"),
    QUEEN("Q"),
    KING("K");

    private final String symbol;

    PieceType(String symbol) {
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }
}
