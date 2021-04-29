package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class AllowedMoves {


    private ArrayList<Coordinates> allowedMovesList;
    private Coordinates position;
    private ArrayList<Coordinates> upRight;
    private ArrayList<Coordinates> downRight;
    private ArrayList<Coordinates> upLeft;
    private ArrayList<Coordinates> downLeft;
    private ArrayList<Coordinates> vertical;
    private ArrayList<Coordinates> horizontal;
    private Chessboard chessboard;
    private Coordinates destination;


    private Coordinates startCoordinate;
    private String symbol;

    public void setPlayer(Player player) {
        this.player = player;
    }

    private Player player;

    private Coordinates obstacle;

    public AllowedMoves() {


    }

    public ArrayList<Coordinates> getAllowedMovesList() {
        return allowedMovesList;
    }

    public void setAllowedMovesListStraight() {
        horizontal = new ArrayList<>();
        vertical = new ArrayList<>();
        int xCoord = position.getXCoordinates();
        int yCoord = position.getYCoordinates();
        int x = 0;
        int y = 0;
        while (xCoord - x >= 0) {
            allowedMovesList.add(new Coordinates(xCoord - x, yCoord));
            horizontal.add(new Coordinates(xCoord - x, yCoord));
            x++;
        }
        x = 0;
        while (xCoord + x < 8) {
            allowedMovesList.add(new Coordinates(xCoord + x, yCoord));
            horizontal.add(new Coordinates(xCoord + x, yCoord));
            x++;
        }
        while (yCoord - y >= 0) {
            allowedMovesList.add(new Coordinates(xCoord, yCoord - y));
            vertical.add(new Coordinates(xCoord, yCoord - y));
            y++;
        }
        y = 0;
        while (yCoord + y < 8) {
            allowedMovesList.add(new Coordinates(xCoord, yCoord + y));
            vertical.add(new Coordinates(xCoord, yCoord + y));
            y++;
        }
    }

    public void setAllowedMovesListDiagonal() {

        upRight = new ArrayList<>();
        downRight = new ArrayList<>();
        upLeft = new ArrayList<>();
        downLeft = new ArrayList<>();
        int xCoord = position.getXCoordinates();
        int yCoord = position.getYCoordinates();
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

    public void setAllowedMovesListKing() {
        int[] xCoord = {1, 0, -1};
        int[] yCoord = {1, 0, -1};
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                int x = this.position.getXCoordinates() + xCoord[i];
                int y = this.position.getYCoordinates() + yCoord[j];
                if(x>=0 && x<=7 && y>=0 && y<=7)
                allowedMovesList.add(new Coordinates(this.position.getXCoordinates() + xCoord[i],
                        this.position.getYCoordinates() + yCoord[j]));
            }
        }
    }

    public void setAllowedMovesListKnight() {
        allowedMovesList.add(new Coordinates(position.getXCoordinates() + 1, position.getYCoordinates() + 2));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() + 1, position.getYCoordinates() - 2));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() - 1, position.getYCoordinates() + 2));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() - 1, position.getYCoordinates() - 2));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() + 2, position.getYCoordinates() + 1));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() + 2, position.getYCoordinates() - 1));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() - 2, position.getYCoordinates() + 1));
        allowedMovesList.add(new Coordinates(position.getXCoordinates() - 2, position.getYCoordinates() - 1));
    }

    public boolean pathIsClear() {

        int destX = destination.getXCoordinates();
        int destY = destination.getYCoordinates();
        int locX = this.position.getXCoordinates();
        int locY = this.position.getYCoordinates();
        if (symbol.equals("K") || symbol.equals("k")) {
            ArrayList<Coordinates> arr = new ArrayList<>();
            arr.add(destination);
            return checkPath(arr, chessboard, destination);
        }
        if (symbol.equals("P")) {
            ArrayList<Coordinates> arr = new ArrayList<>();
            arr.add(destination);
            return checkPath(arr, chessboard, destination);
        }
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
        if (destY == locY && destX != locX) {
            return checkPath(horizontal, chessboard, destination);
        }
        if (destY != locY) {
            return checkPath(vertical, chessboard, destination);
        }
        return true;
    }

    public boolean checkPath(ArrayList<Coordinates> ar, Chessboard chessboard, Coordinates destination) {
        for (Coordinates c : ar) {
            if (!c.equals(this.position)) {
                if (chessboard.getPiece(c) != null) {
                    obstacle = c;
                    if (chessboard.getPiece(c).getSymbol().equals("K")) {
                        chessboard.getPiece(c).setCheck(true);
                    }
                    return false;
                }
                if (c.equals(destination)) {
                    return true;
                }

            }
        }
        return true;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public void setChessboard(Chessboard chessboard) {
        this.chessboard = chessboard;
    }

    public void setDestination(Coordinates destination) {
        this.destination = destination;
    }

    public void setAllowedMovesList(String symbol) {
        this.allowedMovesList = new ArrayList<>();
        this.symbol = symbol;
        if (symbol.equals("B")) {
            setAllowedMovesListDiagonal();
        }
        if (symbol.equals("R")) {
            setAllowedMovesListStraight();
        }
        if (symbol.equals("Q")) {
            setAllowedMovesListStraight();
            setAllowedMovesListDiagonal();
        }
        if (symbol.equals("K")) {
            setAllowedMovesListKing();
        }
        if (symbol.equals("k")) {
            setAllowedMovesListKnight();
        }
        if (symbol.equals("P")) {
            setAllowedMovesListPawn();
        }
    }

    private void setAllowedMovesListPawn() {
        boolean incrementingYAxis = this.player == Player.BLACK;
        if (incrementingYAxis && position.getYCoordinates() < 7) {
            if (chessboard.getPiece(new Coordinates(position.getXCoordinates(), position.getYCoordinates() + 1)) == null) {
                allowedMovesList.add(new Coordinates(position.getXCoordinates(), position.getYCoordinates() + 1));
                if (position.equals(startCoordinate) && chessboard.getPiece(new Coordinates(position.getXCoordinates(), position.getYCoordinates() + 2)) == null &&
                        position.getYCoordinates() < 6) {
                    allowedMovesList.add(new Coordinates(position.getXCoordinates(), position.getYCoordinates() + 2));
                }
            }
            if (position.getXCoordinates() < 7) {
                Coordinates temp = new Coordinates(position.getXCoordinates() + 1, position.getYCoordinates() + 1);
                if (chessboard.getPiece(temp) != null && !chessboard.getPiece(temp).getPlayer().equals(this.player)) {
                    allowedMovesList.add(temp);
                }
            }
            if(position.getXCoordinates()>0) {
                Coordinates temp = new Coordinates(position.getXCoordinates() - 1, position.getYCoordinates() + 1);
                if (chessboard.getPiece(temp) != null && !chessboard.getPiece(temp).getPlayer().equals(this.player)) {
                    allowedMovesList.add(temp);
                }
            }

        }
        if (!incrementingYAxis && position.getYCoordinates() > 0) {
            if (chessboard.getPiece(new Coordinates(position.getXCoordinates(), position.getYCoordinates() - 1)) == null) {
                allowedMovesList.add(new Coordinates(position.getXCoordinates(), position.getYCoordinates() - 1));
                if (position.equals(startCoordinate) && chessboard.getPiece(new Coordinates(position.getXCoordinates(), position.getYCoordinates() - 2)) == null
                        && position.getYCoordinates() > 1) {
                    allowedMovesList.add(new Coordinates(position.getXCoordinates(), position.getYCoordinates() - 2));
                }
            }
            if(position.getXCoordinates()<7) {
                Coordinates temp = new Coordinates(position.getXCoordinates() + 1, position.getYCoordinates() - 1);
                if (chessboard.getPiece(temp) != null && !chessboard.getPiece(temp).getPlayer().equals(this.player)) {
                    allowedMovesList.add(temp);
                }
            }
            if(position.getXCoordinates()>0) {
                Coordinates temp = new Coordinates(position.getXCoordinates() - 1, position.getYCoordinates() - 1);
                if (chessboard.getPiece(temp) != null && !chessboard.getPiece(temp).getPlayer().equals(this.player)) {
                    allowedMovesList.add(temp);
                }
            }
        }
    }

    public Coordinates getObstacle() {
        return obstacle;
    }

    public void setStartCoordinate(Coordinates startCoordinate) {
        this.startCoordinate = startCoordinate;
    }
}
