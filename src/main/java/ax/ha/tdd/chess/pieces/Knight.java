package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Knight extends ChessPiece {
    private final String symbol = "k";
    public Knight(Player player, Coordinates location) {
        super(player, location, "k");
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

}
