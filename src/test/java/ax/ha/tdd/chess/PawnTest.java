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
    @Test
    public void canMove_givenBlackPawnNotFirstMove_unableToMoveOneStepIfSquareIsOccupied() {
        chessboard = new Chessboard();
        Pawn whitePawn = new Pawn(Player.WHITE, new Coordinates('b',5));
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        chessboard.addPiece(whitePawn);
        Assertions.assertFalse(pawn.canMove(chessboard, new Coordinates('b',5)));
    }
    @Test
    public void canCatch_givenBlackPawnNotFirstMove_ableToCatchWhiteWhenCorrectPosition() {
        chessboard = new Chessboard();
        Pawn whitePawn = new Pawn(Player.WHITE, new Coordinates('c',6));
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        chessboard.addPiece(whitePawn);
        Assertions.assertTrue(pawn.canCatch(chessboard, new Coordinates('c',6)));
    }
    @Test
    public void canCatch_givenBlackPawnNotFirstMove_unableToCatchWhiteBackwards() {
        chessboard = new Chessboard();
        Pawn whitePawn = new Pawn(Player.WHITE, new Coordinates('c',8));
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        chessboard.addPiece(whitePawn);
        Assertions.assertFalse(pawn.canCatch(chessboard, new Coordinates('c',8)));
    }
    @Test
    public void canCatch_givenBlackPawn_unableToCatchEmptySquare() {
        chessboard = new Chessboard();
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        Assertions.assertFalse(pawn.canCatch(chessboard, new Coordinates('c',8)));
    }
    @Test
    public void canCatch_givenBlackPawn_unableToCatchWhiteFurtherAway() {
        chessboard = new Chessboard();
        Pawn whitePawn = new Pawn(Player.WHITE, new Coordinates('d',6));
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        chessboard.addPiece(whitePawn);
        Assertions.assertFalse(pawn.canCatch(chessboard, new Coordinates('d',6)));
    }
    @Test
    public void canCatch_givenWhitePawnNotFirstMove_ableToCatchBlackWhenCorrectPosition() {
        chessboard = new Chessboard();
        Pawn blackPawn = new Pawn(Player.BLACK, new Coordinates('a',3));
        pawn = new Pawn(Player.WHITE, new Coordinates('b',2));
        chessboard.addPiece(pawn);
        chessboard.addPiece(blackPawn);
        Assertions.assertTrue(pawn.canCatch(chessboard, new Coordinates('a',3)));
    }
    @Test
    public void canCatch_givenWhitePawnNotFirstMove_unableToCatchBlackBackwards() {
        chessboard = new Chessboard();
        Pawn blackPawn = new Pawn(Player.BLACK, new Coordinates('a',1));
        pawn = new Pawn(Player.WHITE, new Coordinates('b',2));
        chessboard.addPiece(pawn);
        chessboard.addPiece(blackPawn);
        Assertions.assertFalse(pawn.canCatch(chessboard, new Coordinates('a',1)));
    }
    @Test
    public void canCatch_givenBlackPawn_unableToCatchBlack() {
        chessboard = new Chessboard();
        Pawn blackPawn = new Pawn(Player.BLACK, new Coordinates('c',6));
        pawn = new Pawn(Player.BLACK, new Coordinates('b',7));
        chessboard.addPiece(pawn);
        chessboard.addPiece(blackPawn);
        Assertions.assertFalse(pawn.canCatch(chessboard, new Coordinates('c',6)));
    }
}
