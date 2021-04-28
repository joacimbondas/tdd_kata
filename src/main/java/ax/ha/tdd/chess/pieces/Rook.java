package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

public class Rook extends ChessPiece {
    private Coordinates catchCoord;
    public Rook(Player player, Coordinates location) {
        super(player, location);
    }

    @Override
    public String getSymbol() {
        return null;
    }
    public void move(Chessboard chessboard, Coordinates destination) {
        if(canCatch(chessboard, destination)) {
            chessboard.removePiece(this);
            this.setLocation(catchCoord);
            chessboard.addPiece(this);
        }
        else if(canMove(chessboard,destination)) {
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
        }
    }

    public Coordinates pathIsClear(Coordinates destination, boolean horizontalMove, Chessboard chessboard) {
        if(horizontalMove) {
            int destX = destination.getXCoordinates();
            int locX = this.location.getXCoordinates();
            if(destX>locX) {
                for(int i = locX+1; i <= destX; i++) {
                    if(chessboard.getPiece(new Coordinates(i, this.location.getYCoordinates()))!=null) {
                        return new Coordinates(i, this.location.getYCoordinates());
                    }
                }
            } else {
                for(int i = locX-1; i >= destX; i--) {
                    if(chessboard.getPiece(new Coordinates(i, this.location.getYCoordinates()))!=null) {
                        return new Coordinates(i, this.location.getYCoordinates());
                    }
                }
            }
        } else {
            int destY = destination.getYCoordinates();
            int locY = this.location.getYCoordinates();
            if(destY>locY) {
                for(int i = locY+1; i <= destY; i++) {
                    if(chessboard.getPiece(new Coordinates(this.location.getXCoordinates(), i))!=null) {
                        return new Coordinates(this.location.getXCoordinates(), i);
                    }
                }
            } else {
                for(int i = locY-1; i >= destY; i--) {
                    if(chessboard.getPiece(new Coordinates(this.location.getXCoordinates(), i))!=null) {
                        return new Coordinates(this.location.getXCoordinates(), i);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        if(this.getLocation().getXCoordinates() != destination.getXCoordinates() && this.getLocation().getYCoordinates() != destination.getYCoordinates()) {
            return false;
        }
        if (this.getLocation().getXCoordinates() == (destination.getXCoordinates())) {
            if(pathIsClear(destination, false, chessboard)!=null) {
                return false;
            }
        }
        if (this.getLocation().getYCoordinates() == (destination.getYCoordinates())) {
            if(pathIsClear(destination, true, chessboard)!=null) {
                return false;
            }
        }
        return chessboard.getPiece(destination) == null;
    }
    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        if (this.getLocation().getXCoordinates() == (destination.getXCoordinates())) {
            if(pathIsClear(destination, false, chessboard)!=null) {
                if(!chessboard.getPiece(pathIsClear(destination, false, chessboard)).getPlayer().equals(this.player)) {
                    catchCoord = pathIsClear(destination, false, chessboard);
                    return true;
                } else {
                    return false;
                }
            }
        }
        if (this.getLocation().getYCoordinates() == (destination.getYCoordinates())) {
            if(pathIsClear(destination, true, chessboard)!=null) {
                if(!chessboard.getPiece(pathIsClear(destination, true, chessboard)).getPlayer().equals(this.player)) {
                    catchCoord = pathIsClear(destination, true, chessboard);
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}
