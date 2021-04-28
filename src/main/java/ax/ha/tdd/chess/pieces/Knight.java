package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Knight extends ChessPiece{
    private ArrayList<Coordinates> allowedMovesList;
    public Knight(Player player, Coordinates location) {
        super(player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }

    public void setAllowedMovesList() {
        allowedMovesList = new ArrayList<>();
        allowedMovesList.add(new Coordinates(location.getXCoordinates()+1, location.getYCoordinates()+2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()+1, location.getYCoordinates()-2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()-1, location.getYCoordinates()+2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()-1, location.getYCoordinates()-2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()+2, location.getYCoordinates()+1));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()+2, location.getYCoordinates()-1));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()-2, location.getYCoordinates()+1));
        allowedMovesList.add(new Coordinates(location.getXCoordinates()-2, location.getYCoordinates()-1));
    }
    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        setAllowedMovesList();
        if(chessboard.getPiece(destination)!=null) {
            return false;
        }
        return allowedMovesList.contains(destination);
    }
}
