package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Bishop extends ChessPiece {
    private ArrayList<Coordinates> allowedMovesList;
    private ArrayList<Coordinates> upRight;
    private ArrayList<Coordinates> downRight;
    private ArrayList<Coordinates> upLeft;
    private ArrayList<Coordinates> downLeft;
    public Bishop(Player player, Coordinates location) {
        super(player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }

    public void setAllowedMovesList() {
        allowedMovesList = new ArrayList<>();

        upRight = new ArrayList<>();
        downRight = new ArrayList<>();
        upLeft = new ArrayList<>();
        downLeft = new ArrayList<>();
        int xCoord = location.getXCoordinates();
        int yCoord = location.getYCoordinates();
        int x = 0;
        int y = 0;
        while (xCoord + x < 8 && yCoord + y < 8) {
            allowedMovesList.add(new Coordinates(xCoord + x, yCoord + y));
            downRight.add(new Coordinates(xCoord + x, yCoord + y));
            y++;
            x++;
        }
        x = 0;
        y = 0;
        while (xCoord + x < 8 && yCoord - y >= 0) {
            allowedMovesList.add(new Coordinates(xCoord + x, yCoord - y));
            upRight.add(new Coordinates(xCoord + x, yCoord - y));
            y++;
            x++;
        }
        x = 0;
        y = 0;
        while (xCoord - x >= 0 && yCoord - y >= 0) {
            allowedMovesList.add(new Coordinates(xCoord - x, yCoord - y));
            upLeft.add(new Coordinates(xCoord - x, yCoord - y));
            y++;
            x++;
        }
        x = 0;
        y = 0;
        while (xCoord - x >= 0 && yCoord + y < 8) {
            allowedMovesList.add(new Coordinates(xCoord - x, yCoord + y));
            downLeft.add(new Coordinates(xCoord - x, yCoord + y));
            y++;
            x++;
        }

    }

    public boolean pathIsClear(Chessboard chessboard, Coordinates destination) {

        int destX = destination.getXCoordinates();
        int destY = destination.getYCoordinates();
        int locX = this.location.getXCoordinates();
        int locY = this.location.getYCoordinates();
        if (destY < locY && destX > locX) {
            return checkPath(upRight, chessboard, destination);
        }
        if (destY > locY && destX > locX) {
            return checkPath(downRight, chessboard, destination);
        }
        if (destY > locY && destX < locX) {
            return checkPath(downLeft, chessboard, destination);
        }
        if (destY < locY && destX < locX) {
            return checkPath(upLeft, chessboard, destination);
        }
        return true;
    }

    public boolean checkPath(ArrayList<Coordinates> ar, Chessboard chessboard, Coordinates destination) {
        for (Coordinates c : ar) {
            if (!c.equals(this.location)) {
                if(c.equals(destination)){
                    return true;
                }
                if (chessboard.getPiece(c) != null) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        setAllowedMovesList();
        return allowedMovesList.contains(destination) && pathIsClear(chessboard, destination);
    }
    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        setAllowedMovesList();
        return allowedMovesList.contains(destination) && !chessboard.getPiece(destination).getPlayer().equals(this.player);
    }
}
