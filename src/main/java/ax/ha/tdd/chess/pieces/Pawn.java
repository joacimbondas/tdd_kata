package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Pawn extends ChessPiece{
    private final String symbol = "P";
    public Pawn(Player player, Coordinates location) {
        super(player, location, "P");

    }



    @Override
    public String getSymbol() {
        return symbol;
    }

}
