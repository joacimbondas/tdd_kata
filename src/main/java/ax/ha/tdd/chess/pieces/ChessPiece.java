package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;
import ax.ha.tdd.chess.exceptions.InvalidMovementException;

public abstract class ChessPiece {

    protected final Player player;

    protected Coordinates location;



    protected boolean caught;
    protected boolean threatensKing;

    protected boolean check;
    protected Coordinates startCoordinates;

    public ChessPiece(final Player player,
                      final Coordinates location) {
        this.player = player;
        this.location = location;
        this.startCoordinates = location;
        caught = false;
    }

    public void move(Chessboard chessboard, Coordinates destination) {

        if (canCatch(chessboard, destination)) {
            chessboard.getPiece(destination).setCaught(true);
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
        }
        else if(canMove(chessboard, destination)) {
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
        }
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
    public abstract boolean canMove(final Chessboard chessboard, final Coordinates destination);

    public abstract boolean canCatch(Chessboard chessboard, Coordinates destination);

    public abstract void checkLookup(Chessboard chessboard);

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
