package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.Bishop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Bishoptest {

    private Chessboard chessboard;
    private Bishop bishop;

    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        bishop = new Bishop(Player.WHITE, new Coordinates('d', 4));
        chessboard.addPiece(bishop);
    }

    @Test
    public void canMove_givenEmptyBoardWithBishop_expectAbleToMoveAccordingToRules() {
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('c', 3)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('b', 2)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('a', 1)));

        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('e', 5)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('f', 6)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('g', 7)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('h', 8)));


        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('c', 5)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('b', 6)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('a', 7)));

        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('e', 3)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('f', 2)));
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('g', 1)));

    }

    @Test
    public void canMove_givenEmptyBoardWithBishop_expectUnableToMoveWhenBreakingRules() {
        Assertions.assertFalse(bishop.canMove(chessboard, new Coordinates('c', 4)));
        Assertions.assertFalse(bishop.canMove(chessboard, new Coordinates('b', 4)));
        Assertions.assertFalse(bishop.canMove(chessboard, new Coordinates('d', 8)));
    }

    @Test
    public void canMove_givenWhiteBishop_expectNotBeingAbleToMoveToOccupiedSquare() {
        Bishop bishopFriendly = new Bishop(Player.WHITE, new Coordinates('g', 7));
        chessboard.addPiece(bishopFriendly);
        Assertions.assertFalse(bishop.canMove(chessboard, new Coordinates('h', 8)));
    }
    @Test
    public void canMove_givenWhiteBishop_expectBeingAbleToMoveToEmptySquareBeforeOccupiedSquare() {
        Bishop bishopFriendly = new Bishop(Player.WHITE, new Coordinates('h', 8));
        chessboard.addPiece(bishopFriendly);
        Assertions.assertTrue(bishop.canMove(chessboard, new Coordinates('g', 7)));
    }
    @Test
    public void canCatch_givenWhiteBishop_expectAbleToCatchBlack() {
        Bishop bishop1 = new Bishop(Player.BLACK, new Coordinates('c',5));
        chessboard.addPiece(bishop1);
        Assertions.assertTrue(bishop.canCatch(chessboard, bishop1.getLocation()));
    }
    @Test
    public void canCatch_givenWhiteBishop_expectUnableToCatchWhite() {
        Bishop bishop1 = new Bishop(Player.WHITE, new Coordinates('c',5));
        chessboard.addPiece(bishop1);
        Assertions.assertFalse(bishop.canCatch(chessboard, bishop1.getLocation()));
    }


}
