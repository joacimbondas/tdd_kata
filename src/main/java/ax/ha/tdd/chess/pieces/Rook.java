package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Rook extends ChessPiece {
    private Coordinates catchCoord;
    private final String symbol = "R";
    private final AllowedMoves allowedMoves;
    public Rook(Player player, Coordinates location) {
        super(player, location);
        this.allowedMoves = new AllowedMoves();
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public void move(Chessboard chessboard, Coordinates destination) {
        if(canCatch(chessboard, destination)) {
            chessboard.removePiece(this);
            this.setLocation(catchCoord);
            chessboard.addPiece(this);
        }
        else if(canMove(chessboard,destination)) {
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
        }
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        return allowedMovesList.contains(destination) && allowedMoves.pathIsClear();
    }
    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        if(allowedMovesList.contains(destination) && !allowedMoves.pathIsClear()) {
            return !chessboard.getPiece(allowedMoves.getObstacle()).getPlayer().equals(this.player) &&
                    !chessboard.getPiece(allowedMoves.getObstacle()).getSymbol().equals("K");
        }
        return false;
    }
}
