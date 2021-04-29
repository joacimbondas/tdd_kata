package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Knight extends ChessPiece {
    private ArrayList<Coordinates> allowedMovesList;
    private final String symbol = "k";
    public Knight(Player player, Coordinates location) {
        super(player, location);
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

    public void setAllowedMovesList() {
        allowedMovesList = new ArrayList<>();
        allowedMovesList.add(new Coordinates(location.getXCoordinates() + 1, location.getYCoordinates() + 2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() + 1, location.getYCoordinates() - 2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() - 1, location.getYCoordinates() + 2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() - 1, location.getYCoordinates() - 2));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() + 2, location.getYCoordinates() + 1));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() + 2, location.getYCoordinates() - 1));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() - 2, location.getYCoordinates() + 1));
        allowedMovesList.add(new Coordinates(location.getXCoordinates() - 2, location.getYCoordinates() - 1));
    }

    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        setAllowedMovesList();
        if (chessboard.getPiece(destination) == null) {
            return false;
        }
        return !chessboard.getPiece(destination).getPlayer().equals(this.player) && allowedMovesList.contains(destination);
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        setAllowedMovesList();
        if (chessboard.getPiece(destination) != null && chessboard.getPiece(destination).getPlayer().equals(this.player)) {
            return false;
        }
        return allowedMovesList.contains(destination);
    }
}
