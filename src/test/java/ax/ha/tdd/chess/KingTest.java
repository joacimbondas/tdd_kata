package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.Bishop;
import ax.ha.tdd.chess.pieces.King;
import ax.ha.tdd.chess.pieces.Knight;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KingTest {
    private Chessboard chessboard;
    private King king;
    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        king = new King(Player.WHITE, new Coordinates('d', 4), chessboard);
        chessboard.addPiece(king);
    }
    @Test
    public void canMove_givenEmptyBoardWithKing_expectAbleToMoveAccordingToRules() {
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('d',5)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('d',3)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('c',3)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('c',4)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('c',5)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('e',3)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('e',4)));
        Assertions.assertTrue(king.canMove(chessboard, new Coordinates('e',5)));
    }
    @Test
    public void canMove_givenWhiteKing_unableToMoveWhenBreakingRules() {
        Assertions.assertFalse(king.canMove(chessboard, new Coordinates('b',4)));
        Assertions.assertFalse(king.canMove(chessboard, new Coordinates('f',4)));
        Assertions.assertFalse(king.canMove(chessboard, new Coordinates('d',6)));
        Assertions.assertFalse(king.canMove(chessboard, new Coordinates('c',6)));
    }
    @Test
    public void canMove_givenWhiteKing_expectNotBeingAbleToMoveToOccupiedSquare() {
        Knight knight2 = new Knight(Player.WHITE, new Coordinates('c',5));
        chessboard.addPiece(knight2);
        Assertions.assertFalse(king.canMove(chessboard, knight2.getLocation()));
    }
    @Test
    public void canCatch_givenWhiteKing_expectAbleToCatchBlack() {
        Knight knight2 = new Knight(Player.BLACK, new Coordinates('c',5));
        chessboard.addPiece(knight2);
        Assertions.assertTrue(king.canCatch(chessboard, knight2.getLocation()));
    }
    @Test
    public void canCatch_givenWhiteKing_expectUnableToCatchKing() {
        King king2 = new King(Player.BLACK, new Coordinates('c',5), chessboard);
        chessboard.addPiece(king2);
        Assertions.assertFalse(king.canCatch(chessboard, king2.getLocation()));
    }
}
