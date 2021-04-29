package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.Bishop;
import ax.ha.tdd.chess.pieces.King;
import ax.ha.tdd.chess.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CastlingTest {
    private Chessboard chessboard;
    private Rook rook;
    private King king;
    CastlingValidator castlingValidator;
    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        rook = new Rook(Player.WHITE, new Coordinates('a',1));
        king = new King(Player.WHITE, new Coordinates('d', 1), chessboard);
        chessboard.addPiece(rook);
        chessboard.addPiece(king);
        castlingValidator = new CastlingValidator(rook, king, chessboard);
    }
    @Test
    public void castlingLegal() {
        Assertions.assertTrue(castlingValidator.validate());
        Assertions.assertEquals(king.getLocation(), new Coordinates('b',1));
        Assertions.assertEquals(rook.getLocation(), new Coordinates('c',1));

    }
    @Test
    public void castlingIllegalIfNotInStartPosition() {
        king.move(chessboard, new Coordinates('e', 1));
        Assertions.assertFalse(castlingValidator.validate());
    }
    @Test
    public void castlingIllegalIfKingMovedAndReturnedToStartPosition() {
        king.move(chessboard, new Coordinates('e', 1));
        Assertions.assertNotEquals(king.getLocation(), king.getStartCoordinates());
        king.move(chessboard, new Coordinates('d', 1));
        Assertions.assertFalse(castlingValidator.validate());
        Assertions.assertEquals(king.getLocation(), king.getStartCoordinates());
    }
    @Test
    public void castlingIllegalIfRookMovedAndReturnedToStartPosition() {
        rook.move(chessboard, new Coordinates('a', 5));
        Assertions.assertNotEquals(rook.getLocation(), rook.getStartCoordinates());
        rook.move(chessboard, new Coordinates('a', 1));
        Assertions.assertFalse(castlingValidator.validate());
        Assertions.assertEquals(rook.getLocation(), rook.getStartCoordinates());
    }
    @Test
    public void castlingIllegalIfPathIsObstructed() {
        Bishop bishop = new Bishop(Player.WHITE, new Coordinates('c', 1));
        chessboard.addPiece(bishop);
        castlingValidator = new CastlingValidator(rook, king, chessboard);
        Assertions.assertFalse(castlingValidator.validate());
    }
    @Test
    public void castlingIllegalIfPathIsThreatened() {
        Rook rook1 = new Rook(Player.BLACK, new Coordinates('b', 8));
        chessboard.addPiece(rook1);
        castlingValidator = new CastlingValidator(rook, king, chessboard);
        Assertions.assertFalse(castlingValidator.validate());
        Assertions.assertEquals(king.getLocation(), new Coordinates('d', 1));
        Assertions.assertEquals(rook.getLocation(), new Coordinates('a', 1));

    }

}
