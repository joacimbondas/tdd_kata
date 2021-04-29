package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.King;
import ax.ha.tdd.chess.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastlingTest {
    private Chessboard chessboard;
    private Rook rook;
    private King king;
    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        rook = new Rook(Player.WHITE, new Coordinates('a',1));
        king = new King(Player.WHITE, new Coordinates('d', 1));
        chessboard.addPiece(rook);
    }
    @Test
    public void castlingLegal() {
        Assertions.assertTrue(rook.castling(chessboard, king));
    }
    @Test
    public void castlingIllegalIfNotInStartPosition() {
        king.move(chessboard, new Coordinates('e', 1));
        Assertions.assertFalse(rook.castling(chessboard, king));
    }
    @Test
    public void castlingIllegalIfKingMovedAndReturnedToStartPosition() {
        king.move(chessboard, new Coordinates('e', 1));
        Assertions.assertNotEquals(king.getLocation(), king.getStartCoordinates());
        king.move(chessboard, new Coordinates('d', 1));
        Assertions.assertFalse(rook.castling(chessboard, king));
        Assertions.assertEquals(king.getLocation(), king.getStartCoordinates());
    }
    @Test
    public void castlingIllegalIfRookMovedAndReturnedToStartPosition() {
        rook.move(chessboard, new Coordinates('a', 5));
        Assertions.assertNotEquals(rook.getLocation(), rook.getStartCoordinates());
        rook.move(chessboard, new Coordinates('a', 1));
        Assertions.assertFalse(rook.castling(chessboard, king));
        Assertions.assertEquals(rook.getLocation(), rook.getStartCoordinates());
    }

}
