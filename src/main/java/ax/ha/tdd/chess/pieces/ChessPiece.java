package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;
import ax.ha.tdd.chess.exceptions.InvalidMovementException;

import java.util.ArrayList;

public abstract class ChessPiece {

    protected final Player player;

    protected Coordinates location;
    protected boolean hasMoved;
    protected boolean caught;
    protected boolean threatensKing;
    protected String symbol;
    protected boolean check;
    protected Coordinates startCoordinates;
    private final AllowedMoves allowedMoves;
    public ChessPiece(final Player player,
                      final Coordinates location, String symbol) {
        this.player = player;
        this.location = location;
        this.startCoordinates = location;
        this.symbol = symbol;
        this.hasMoved = false;
        caught = false;
        this.allowedMoves = new AllowedMoves();
        allowedMoves.setPlayer(player);
        allowedMoves.setStartCoordinate(location);
    }

    public void move(Chessboard chessboard, Coordinates destination) {

        if (canCatch(chessboard, destination)) {
            chessboard.getPiece(destination).setCaught(true);
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
            this.hasMoved = true;
        }
        else if(canMove(chessboard, destination)) {
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
            this.hasMoved = true;
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
    public abstract String getSymbol();

    public Player getPlayer() {
        return player;
    }

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates coordinates) {
        this.location = coordinates;
    }

    public Coordinates getStartCoordinates() {
        return startCoordinates;
    }

    /**
     * Suggestion of design:
     * Checks if the chessPiece can move to a certain destination.
     * I preferred to keep this logic tightly coupled to the pieces instead of the board,
     * since that makes the separation of logic easier.
     *
     * @param chessboard  chessboard
     * @param destination destination
     * @return true if piece can move to the destination
     */
    public boolean canMove(final Chessboard chessboard, final Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        return allowedMovesList.contains(destination) && allowedMoves.pathIsClear();
    }

    //public abstract boolean canCatch(Chessboard chessboard, Coordinates destination);

    public void checkLookup(Chessboard chessboard) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setAllowedMovesList(symbol);
        for (Coordinates c : allowedMoves.getAllowedMovesList()) {
            allowedMoves.setDestination(c);
            allowedMoves.pathIsClear();
        }
    }

    @Override
    public String toString() {
        return getPlayer().name() + " " + getClass().getSimpleName();
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    public boolean isThreateningKing() {
        return threatensKing;
    }

    public void setThreatensKing(boolean threatensKing) {
        this.threatensKing = threatensKing;
    }
    public boolean isCaught() {
        return caught;
    }

    public void setCaught(boolean caught) {
        this.caught = caught;
    }
}
