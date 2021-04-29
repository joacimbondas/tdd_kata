package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Queen extends ChessPiece{
    private final AllowedMoves allowedMoves;
    public Queen(Player player, Coordinates location) {
        super(player, location);
        allowedMoves = new AllowedMoves();
    }

    @Override
    public String getSymbol() {
        return null;
    }


    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList("Q");
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        return allowedMovesList.contains(destination) && allowedMoves.pathIsClear();
    }
    public boolean canCatch(Chessboard chessboard, Coordinates destination) {

        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList("Q");
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        if(allowedMovesList.contains(destination) && !allowedMoves.pathIsClear()) {
            return !chessboard.getPiece(allowedMoves.getObstacle()).getPlayer().equals(this.player);
        }
        return false;
    }
}
