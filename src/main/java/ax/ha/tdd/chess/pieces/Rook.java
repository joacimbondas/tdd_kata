package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Rook extends ChessPiece {
    private final String symbol = "R";

    public Rook(Player player, Coordinates location) {
        super(player, location, "R");

    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public Coordinates getStartCoordinates() {
        return startCoordinates;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public void castlingMove(Chessboard chessboard, Coordinates destination) {
        chessboard.removePiece(this);
        this.setLocation(destination);
        chessboard.addPiece(this);
        this.hasMoved = true;
    }
}
