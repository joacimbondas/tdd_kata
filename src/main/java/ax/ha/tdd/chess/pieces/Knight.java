package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Knight extends ChessPiece {
    private final String symbol = "k";
    private final AllowedMoves allowedMoves;
    public Knight(Player player, Coordinates location) {
        super(player, location);
        allowedMoves = new AllowedMoves();
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public void move(Chessboard chessboard, Coordinates destination) {
        if(canMove(chessboard,destination)) {
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
        }
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

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        return allowedMoves.getAllowedMovesList().contains(destination) && allowedMoves.pathIsClear();
    }
}
