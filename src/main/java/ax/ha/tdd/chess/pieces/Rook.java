package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

public class Rook extends ChessPiece {
    public Rook(Player player, Coordinates location) {
        super(player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }

    public boolean pathIsClear(Coordinates destination, boolean horizontalMove, Chessboard chessboard) {
        if(horizontalMove) {
            int destX = destination.getXCoordinates();
            int locX = this.location.getXCoordinates();
            if(destX>locX) {
                for(int i = locX+1; i < destX; i++) {
                    if(chessboard.getPiece(new Coordinates(i, this.location.getYCoordinates()))!=null) {
                        return false;
                    }
                }
            } else {
                for(int i = destX; i < locX-1; i++) {
                    if(chessboard.getPiece(new Coordinates(i, this.location.getYCoordinates()))!=null) {
                        return false;
                    }
                }
            }
        } else {
            int destY = destination.getYCoordinates();
            int locY = this.location.getYCoordinates();
            if(destY>locY) {
                for(int i = locY+1; i < destY; i++) {
                    if(chessboard.getPiece(new Coordinates(this.location.getXCoordinates(), i))!=null) {
                        return false;
                    }
                }
            } else {
                for(int i = destY; i < locY-1; i++) {
                    if(chessboard.getPiece(new Coordinates(this.location.getXCoordinates(), i))!=null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        if(this.getLocation().getXCoordinates() != destination.getXCoordinates() && this.getLocation().getYCoordinates() != destination.getYCoordinates()) {
            return false;
        }
        if (this.getLocation().getXCoordinates() == (destination.getXCoordinates())) {
            return pathIsClear(destination, false, chessboard);
        }
        if (this.getLocation().getYCoordinates() == (destination.getYCoordinates())) {
            return pathIsClear(destination, true, chessboard);
        }
        if (chessboard.getPiece(destination) != null) {
            return false;
        }

        return true;
    }
}
