package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;

import java.util.ArrayList;

public class King extends ChessPiece {
    private final String symbol = "K";
    private final AllowedMoves allowedMoves;
    private ArrayList<Coordinates> allowedMovesList;

    private boolean check;

    public King(Player player, Coordinates location, Chessboard chessboard) {
        super(player, location);
        allowedMoves = new AllowedMoves();
        allowedMoves.setPlayer(player);
        check = false;
        if(player.equals(Player.WHITE)){
            chessboard.setKingW(this);
        }
        if(player.equals(Player.BLACK)){
            chessboard.setKingB(this);
        }

    }

    public void castlingMove(Chessboard chessboard, Coordinates destination) {
        chessboard.removePiece(this);
        this.setLocation(destination);
        chessboard.addPiece(this);
        this.hasMoved = true;
    }

    public boolean getHasMoved() {
        return hasMoved;
    }

    public Coordinates getStartCoordinates() {
        return startCoordinates;
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
        return allowedMoves.getAllowedMovesList().contains(destination) && allowedMoves.pathIsClear();
    }

    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setDestination(destination);
        allowedMoves.setAllowedMovesList(symbol);
        if (allowedMoves.getAllowedMovesList().contains(destination) && !allowedMoves.pathIsClear()) {
            return !chessboard.getPiece(allowedMoves.getObstacle()).getPlayer().equals(this.player) &&
                    !chessboard.getPiece(allowedMoves.getObstacle()).getSymbol().equals("K");
        }
        return false;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
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

    public ArrayList<Coordinates> tryToEscape(Chessboard chessboard) {
        allowedMoves.setPosition(location);
        allowedMoves.setChessboard(chessboard);
        allowedMoves.setAllowedMovesList(symbol);
        return allowedMoves.getAllowedMovesList();


    }
}
