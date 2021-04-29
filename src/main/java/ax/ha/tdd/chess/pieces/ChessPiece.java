package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;
import ax.ha.tdd.chess.exceptions.InvalidMovementException;

public abstract class ChessPiece {

    protected final Player player;

    protected Coordinates location;

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    protected boolean check;
    protected Coordinates startCoordinates;

    public ChessPiece(final Player player,
                      final Coordinates location) {
        this.player = player;
        this.location = location;
        this.startCoordinates = location;
    }
public void move(Chessboard chessboard, Coordinates destination) {
    if(canMove(chessboard, destination)) {
        if(canCatch(chessboard, destination)) {
            chessboard.removePiece(chessboard.getPiece(destination));
        }
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
}
