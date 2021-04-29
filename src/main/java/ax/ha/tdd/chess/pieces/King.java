package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class King extends ChessPiece {
    private final String symbol = "K";
    private final AllowedMoves allowedMoves;
    private ArrayList<Coordinates> allowedMovesList;

    public King(Player player, Coordinates location) {
        super(player, location);
        allowedMoves = new AllowedMoves();
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        return allowedMoves.getAllowedMovesList().contains(destination) && allowedMoves.pathIsClear();
    }

    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        if(allowedMoves.getAllowedMovesList().contains(destination) && !allowedMoves.pathIsClear()) {
            return !chessboard.getPiece(allowedMoves.getObstacle()).getPlayer().equals(this.player) &&
                    !chessboard.getPiece(allowedMoves.getObstacle()).getSymbol().equals("K");
        }
        return false;
    }
}
