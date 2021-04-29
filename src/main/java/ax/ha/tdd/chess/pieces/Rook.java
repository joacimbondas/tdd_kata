package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class Rook extends ChessPiece {
    private Coordinates catchCoord;
    private final String symbol = "R";
    private final AllowedMoves allowedMoves;

    public Rook(Player player, Coordinates location) {
        super(player, location);
        this.allowedMoves = new AllowedMoves();
        allowedMoves.setPlayer(player);

    }



    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {

        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        return allowedMovesList.contains(destination) && allowedMoves.pathIsClear();
    }

    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        ArrayList<Coordinates> allowedMovesList = allowedMoves.getAllowedMovesList();
        if (allowedMovesList.contains(destination) && !allowedMoves.pathIsClear()) {
            return !chessboard.getPiece(allowedMoves.getObstacle()).getPlayer().equals(this.player) &&
                    !chessboard.getPiece(allowedMoves.getObstacle()).getSymbol().equals("K");
        }
        return false;
    }

    @Override
    public void checkLookup(Chessboard chessboard) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setAllowedMovesList(symbol);
        for (Coordinates c : allowedMoves.getAllowedMovesList()) {
            allowedMoves.setDestination(c);
            allowedMoves.pathIsClear();
        }
    }
    public boolean castling(Chessboard chessboard, King king) {
        int xCoord = this.getLocation().getXCoordinates();
        int yCoord = this.getLocation().getYCoordinates();
        boolean isLegal = false;
        if(this.location==this.startCoordinates && king.location==king.startCoordinates && !king.hasMoved && !this.hasMoved) {
           isLegal = true;
            if(this.location.getXCoordinates()<king.location.getXCoordinates()) {

                for(int i = 1; i <= 3; i++){
                    if(chessboard.getPiece(new Coordinates(xCoord+i, yCoord))!=null){
                        isLegal = false;
                    }
                }
                if(isLegal){
                    this.move(chessboard, new Coordinates(xCoord+3, yCoord));
                    king.move(chessboard, new Coordinates(king.location.getXCoordinates()-2, king.location.getYCoordinates()));
                }
            }
            else if (this.location.getXCoordinates()>king.location.getXCoordinates()) {
                for(int i = 2; i < 0; i--) {
                    if(chessboard.getPiece(new Coordinates(xCoord-i, yCoord))!=null){
                        isLegal = false;
                    }
                }
                if(isLegal){
                    this.move(chessboard, new Coordinates(this.getLocation().getXCoordinates()-2, this.getLocation().getYCoordinates()));
                    king.move(chessboard, new Coordinates(king.location.getXCoordinates()+2, king.location.getYCoordinates()));
                }

            }

        }
        return isLegal;
    }
}
