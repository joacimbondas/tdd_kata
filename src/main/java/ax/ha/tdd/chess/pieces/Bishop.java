package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Bishop extends ChessPiece{
    private ArrayList<Coordinates> allowedMovesList;
    public Bishop(Player player, Coordinates location) {
        super(player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }
    public void setAllowedMovesList() {
        allowedMovesList = new ArrayList<>();
        int xCoord = location.getXCoordinates();
        int yCoord = location.getYCoordinates();
        int x = 0;
        int y = 0;
        while(xCoord+x<8 && yCoord+y<8) {
            allowedMovesList.add(new Coordinates(xCoord+x, yCoord+y));
            y++;
            x++;
        }
        x = 0;
        y = 0;
        while(xCoord+x<8 && yCoord-y>=0) {
            allowedMovesList.add(new Coordinates(xCoord+x, yCoord-y));
            y++;
            x++;
        }
        x = 0;
        y = 0;
        while(xCoord-x>=0 && yCoord-y>=0) {
            allowedMovesList.add(new Coordinates(xCoord-x, yCoord-y));
            y++;
            x++;
        }
        x = 0;
        y = 0;
        while(xCoord-x>=0 && yCoord+y<8) {
            allowedMovesList.add(new Coordinates(xCoord-x, yCoord+y));
            y++;
            x++;
        }

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
