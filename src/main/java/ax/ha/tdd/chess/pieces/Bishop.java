package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Bishop extends ChessPiece {

    private final String symbol = "B";
    public Bishop(Player player, Coordinates location) {
        super(player, location, "B");
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
