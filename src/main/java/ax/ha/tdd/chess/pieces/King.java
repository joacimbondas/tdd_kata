package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class King extends ChessPiece {
    private String symbol = "K";
    private ArrayList<Coordinates> allowedMovesList;

    public King(Player player, Coordinates location) {
        super(player, location);
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    public void createAllowedMovesList() {
        allowedMovesList = new ArrayList<>();
        int[] xCoord = {1, 0, -1};
        int[] yCoord = {1, 0, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                allowedMovesList.add(new Coordinates(this.location.getXCoordinates() + xCoord[i],
                        this.location.getYCoordinates() + yCoord[j]));
            }
        }

    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        createAllowedMovesList();
        if (chessboard.getPiece(destination) != null) {
            return false;
        }
        return allowedMovesList.contains(destination);
    }

    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        createAllowedMovesList();
        if (allowedMovesList.contains(destination) && chessboard.getPiece(destination) != null) {
            return !chessboard.getPiece(destination).getPlayer().equals(this.player);
        }
        return false;
    }
}
