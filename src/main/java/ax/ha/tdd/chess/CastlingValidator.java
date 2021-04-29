package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.King;
import ax.ha.tdd.chess.pieces.Rook;

public class CastlingValidator {

    private Rook rook;
    private King king;
    private Chessboard chessboard;

    public CastlingValidator(Rook rook, King king, Chessboard chessboard) {
        this.chessboard = chessboard;
        this.rook = rook;
        this.king = king;
    }

    public boolean validate() {
        if (rook.getLocation() == rook.getStartCoordinates() && king.getLocation() == king.getStartCoordinates() &&
                !king.getHasMoved() && !rook.getHasMoved()) {
            if (rook.getLocation().getXCoordinates() < king.getLocation().getXCoordinates()) {
                return rookToTheLeft();
            }
            else {
                return rookToTheRight();
            }
        }
        return false;
    }

    public boolean rookToTheRight() {
        boolean isLegal = true;
        Coordinates kingOldPos = king.getLocation();
        int xCoord = rook.getLocation().getXCoordinates();
        int yCoord = rook.getLocation().getYCoordinates();

        for (int i = 1; i <= 3; i++) {
            if (chessboard.getPiece(new Coordinates(xCoord - i, yCoord)) != null) {
                isLegal = false;
            }
        }
        for (int i = 1; i < 3; i++) {
            king.castlingMove(chessboard, new Coordinates(king.getLocation().getXCoordinates() + i, king.getLocation().getYCoordinates()));
            chessboard.checkLookup();
            if (king.isCheck()) {
                isLegal = false;
            }
        }
        king.castlingMove(chessboard, kingOldPos);
        king.setCheck(false);
        if (isLegal) {
            rook.castlingMove(chessboard, new Coordinates(xCoord - 3, yCoord));
            king.castlingMove(chessboard, new Coordinates(king.getLocation().getXCoordinates() + 2, king.getLocation().getYCoordinates()));
        }
        return isLegal;
    }

    public boolean rookToTheLeft() {
        boolean isLegal = true;
        Coordinates kingOldPos = king.getLocation();
        int xCoord = rook.getLocation().getXCoordinates();
        int yCoord = rook.getLocation().getYCoordinates();

        for (int i = 1; i <= 2; i++) {
            if (chessboard.getPiece(new Coordinates(xCoord + i, yCoord)) != null) {
                isLegal = false;
            }
        }
        for (int i = 1; i < 3; i++) {
            king.castlingMove(chessboard, new Coordinates(kingOldPos.getXCoordinates() - i, kingOldPos.getYCoordinates()));
            chessboard.checkLookup();
            if (king.isCheck()) {
                isLegal = false;
            }
        }
        king.castlingMove(chessboard, kingOldPos);
        king.setCheck(false);
        if (isLegal) {
            rook.castlingMove(chessboard, new Coordinates(xCoord + 2, yCoord));
            king.castlingMove(chessboard, new Coordinates(king.getLocation().getXCoordinates() - 2, king.getLocation().getYCoordinates()));
        }
        return isLegal;
    }
    public boolean pathIsObstructed() {

        return false;
    }

}
