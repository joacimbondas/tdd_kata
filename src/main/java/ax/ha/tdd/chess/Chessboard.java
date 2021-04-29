package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.ChessPiece;
import ax.ha.tdd.chess.pieces.ChessPieceStub;
import ax.ha.tdd.chess.pieces.King;
import ax.ha.tdd.chess.pieces.PieceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Chessboard {
    // This could just as easily be replaced with a List or Set,
    // since the ChessPieces right now keep track of their own location.
    // Feel free to change this however you like
    private final ChessPiece[][] board = new ChessPiece[8][8];

    public static Chessboard fullBoard() {
        final Chessboard chessboard = new Chessboard();

        chessboard.withMirroredPiece(PieceType.PAWN.getSymbol(), 1, Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7))
                .withMirroredPiece(PieceType.ROOK.getSymbol(), 0, Arrays.asList(0, 7))
                .withMirroredPiece(PieceType.KNIGHT.getSymbol(), 0, Arrays.asList(1, 6))
                .withMirroredPiece(PieceType.BISHOP.getSymbol(), 0, Arrays.asList(2, 5))
                .withMirroredPiece(PieceType.KING.getSymbol(), 0, Arrays.asList(3))
                .withMirroredPiece(PieceType.QUEEN.getSymbol(), 0, Arrays.asList(4));
        return chessboard;
    }

    public ChessPiece getPiece(final Coordinates coordinates) {
        return board[coordinates.getXCoordinates()][coordinates.getYCoordinates()];
    }

    public void addPiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getXCoordinates()][chessPiece.getLocation().getYCoordinates()] = chessPiece;
    }

    public void removePiece(final ChessPiece chessPiece) {
        board[chessPiece.getLocation().getXCoordinates()][chessPiece.getLocation().getYCoordinates()] = null;
    }

    public void checkLookup(ArrayList<ChessPiece> pieces, King kingw, King kingB) {
        kingB.setCheck(false);
        kingw.setCheck(false);
        for (ChessPiece c : pieces) {
            if(c.isCaught()) {
                removePiece(c);
            }
            else{
                c.checkLookup(this);
            }

        }
    }
    /**
     * Helper method to initialize chessboard with {@link ChessPieceStub}.
     * Basically mirrors all added pieces for both players.
     * When all pieces has been implemented, this should be replaced with the proper implementations.
     *
     * @param pieceType         pieceType
     * @param yCoordinateOffset yCoordinateOffset
     * @param xCoordinates      xCoordinates
     * @return itself, like a builder pattern
     */
    private Chessboard withMirroredPiece(final String pieceType,
                                         final int yCoordinateOffset,
                                         final List<Integer> xCoordinates) {
        xCoordinates.forEach(xCoordinate -> {
            addPiece(new ChessPieceStub(pieceType, Player.BLACK, new Coordinates(xCoordinate, yCoordinateOffset)));
            addPiece(new ChessPieceStub(pieceType, Player.WHITE, new Coordinates(xCoordinate, 7 - yCoordinateOffset)));
        });
        return this;
    }
}
