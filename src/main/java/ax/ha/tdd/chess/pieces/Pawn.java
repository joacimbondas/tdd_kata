package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

public class Pawn extends ChessPiece{
    Coordinates startCoordinate;
    public Pawn(Player player, Coordinates location) {
        super(player, location);
    }

    public void move(Chessboard chessboard, Coordinates destination) {
        if(canMove(chessboard,destination)||canCatch(chessboard,destination)) {
            chessboard.removePiece(this);
            this.setLocation(destination);
            chessboard.addPiece(this);
        }
    }
    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        boolean incrementingYAxis = this.getPlayer() == Player.BLACK;
        if((this.location.getYCoordinates()-destination.getYCoordinates()==1 && !incrementingYAxis) ||
                (destination.getYCoordinates()-this.location.getYCoordinates()==1 && incrementingYAxis)) {
            if(Math.abs(this.location.getXCoordinates()-destination.getXCoordinates())==1) {
                if(chessboard.getPiece(destination)!=null&& !chessboard.getPiece(destination).getPlayer().equals(this.player)) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public String getSymbol() {
        return "P";
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        boolean incrementingYAxis = this.getPlayer() == Player.BLACK;
        if(this.location.getYCoordinates() > destination.getYCoordinates() && incrementingYAxis) {
            return false;
        }
        if(this.location.getYCoordinates() < destination.getYCoordinates() && !incrementingYAxis) {
            return false;
        }
        if(chessboard.getPiece(destination)!=null){
            return false;
        }
        if(!this.location.equals(this.startCoordinates) && Math.abs(this.location.getYCoordinates() - destination.getYCoordinates())>1) {
            return false;
        }
        if(Math.abs(this.location.getYCoordinates() - destination.getYCoordinates())>2) {
            return false;
        }
        return true;
    }
}
