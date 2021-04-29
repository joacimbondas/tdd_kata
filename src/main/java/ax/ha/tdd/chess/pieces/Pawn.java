package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Pawn extends ChessPiece{
    Coordinates startCoordinate;
    ArrayList<Coordinates> allowedMovesList;
    AllowedMoves allowedMoves;
    private final String symbol = "P";
    public Pawn(Player player, Coordinates location) {
        super(player, location);
        startCoordinate = location;
        allowedMoves = new AllowedMoves();
        allowedMoves.setStartCoordinate(startCoordinate);
        allowedMoves.setPlayer(this.player);
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
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        return allowedMovesList.contains(destination) && allowedMoves.pathIsClear();
    }

    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        if (allowedMovesList.contains(destination) && !allowedMoves.pathIsClear()) {
            return !chessboard.getPiece(allowedMoves.getObstacle()).getPlayer().equals(this.player) &&
                    !chessboard.getPiece(allowedMoves.getObstacle()).getSymbol().equals("K");
        }
        return false;
    }
    @Override
    public void checkLookup(Chessboard chessboard) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setAllowedMovesList(symbol);
        for(Coordinates c : allowedMoves.getAllowedMovesList()) {
            allowedMoves.setDestination(c);
            allowedMoves.pathIsClear();
        }
    }
}
