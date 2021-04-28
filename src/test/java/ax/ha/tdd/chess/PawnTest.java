package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PawnTest {

    private Chessboard chessboard;
    private Pawn pawn;
    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.WHITE, new Coordinates('b',2));
        chessboard.addPiece(pawn);
    }

    @Test
    public void canMove_givenEmptyBoardWithPawn_expectAbleToMoveForward() {
        Assertions.assertTrue(pawn.canMove(chessboard, new Coordinates('b',3)));
    }
    @Test
    public void canMove_givenWhitePawn_unableToMoveBackward() {
        Assertions.assertFalse(pawn.canMove(chessboard, new Coordinates('b',1)));
    }
    @Test
    public void canMove_givenBlackPawn_ableToMoveForward() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        Assertions.assertTrue(pawn.canMove(chessboard, new Coordinates('b',6)));
    }
    @Test
    public void canMove_givenBlackPawn_unableToMoveBackward() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        Assertions.assertFalse(pawn.canMove(chessboard, new Coordinates('b',8)));
    }
    @Test
    public void canMove_givenBlackPawnFirstMove_ableToMoveTwoSteps() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        Assertions.assertTrue(pawn.canMove(chessboard, new Coordinates('b',5)));
    }
    @Test
    public void canMove_givenBlackPawnNotFirstMove_unableToMoveTwoSteps() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        pawn.move(chessboard, new Coordinates('b',6));
        Assertions.assertFalse(pawn.canMove(chessboard, new Coordinates('b',4)));
    }
    @Test
    public void canMove_givenBlackPawnNotFirstMove_ableToMoveOneStep() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        pawn.move(chessboard, new Coordinates('b',5));
        Assertions.assertTrue(pawn.canMove(chessboard, new Coordinates('b',4)));
    }
}
