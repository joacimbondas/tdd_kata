package ax.ha.tdd.chess;


import ax.ha.tdd.chess.pieces.Pawn;
import ax.ha.tdd.chess.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RookTest {
    private Chessboard chessboard;
    private Rook rook;
    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        rook = new Rook(Player.WHITE, new Coordinates('a',1));
        chessboard.addPiece(rook);
    }

    @Test
    public void canMove_givenEmptyBoardWithRook_expectAbleToMoveForward() {
        Assertions.assertTrue(rook.canMove(chessboard, new Coordinates('a',5)));
    }
    @Test
    public void canMove_givenEmptyBoardWithRook_expectAbleToMoveHorizontally() {
        Assertions.assertTrue(rook.canMove(chessboard, new Coordinates('c',1)));
    }
    @Test
    public void canMove_givenEmptyBoardWithRook_expectAbleToMoveBackward() {
        rook = new Rook(Player.WHITE, new Coordinates('a',7));
        chessboard.addPiece(rook);
        Assertions.assertTrue(rook.canMove(chessboard, new Coordinates('a',2)));
    }
    @Test
    public void canMove_givenEmptyBoardWithRook_expectAbleToMoveHorizontallyOtherWay() {
        rook = new Rook(Player.WHITE, new Coordinates('e',3));
        chessboard.addPiece(rook);
        Assertions.assertTrue(rook.canMove(chessboard, new Coordinates('b',3)));
    }
    @Test
    public void canMove_givenEmptyBoardWithRook_expectUnableToMoveDiagonally() {
        Assertions.assertFalse(rook.canMove(chessboard, new Coordinates('e',5)));
    }
    @Test
    public void canMove_givenRook_unableToMoveWhenPathIsBlocked() {
        Rook rook1 = new Rook(Player.WHITE, new Coordinates('a',3));
        chessboard.addPiece(rook1);
        Assertions.assertFalse(rook.canMove(chessboard, new Coordinates('a',5)));
    }

}
