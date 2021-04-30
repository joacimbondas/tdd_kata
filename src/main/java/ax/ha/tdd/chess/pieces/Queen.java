package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Queen extends ChessPiece{
    private final String symbol = "Q";
    public Queen(Player player, Coordinates location) {
        super(player, location, "Q");

    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
