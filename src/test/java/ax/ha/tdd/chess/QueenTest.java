package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.Bishop;
import ax.ha.tdd.chess.pieces.King;
import ax.ha.tdd.chess.pieces.Queen;
import ax.ha.tdd.chess.pieces.Rook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QueenTest {
    private Chessboard chessboard;
    private Queen queen;

    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        queen = new Queen(Player.WHITE, new Coordinates('d', 4));
        chessboard.addPiece(queen);
    }
    @Test
    public void canMove_givenEmptyBoardWithQueen_expectAbleToMoveAccordingToRules() {
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('c', 3)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('b', 2)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('a', 1)));

        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('e', 5)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('f', 6)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('g', 7)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('h', 8)));


        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('c', 5)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('b', 6)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('a', 7)));

        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('e', 3)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('f', 2)));
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('g', 1)));

        for(int i = 1; i < 8; i++) {
            Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('d', i)));
            Assertions.assertTrue(queen.canMove(chessboard, new Coordinates(i, 4)));
        }


    }
    @Test
    public void canMove_givenEmptyBoardWithQueen_expectUnableToMoveWhenBreakingRules() {
        Assertions.assertFalse(queen.canMove(chessboard, new Coordinates('c', 6)));
        Assertions.assertFalse(queen.canMove(chessboard, new Coordinates('b', 3)));
        Assertions.assertFalse(queen.canMove(chessboard, new Coordinates('h', 5)));
    }

    @Test
    public void canMove_givenWhiteQueen_expectNotBeingAbleToMoveToOccupiedSquare() {
        Bishop bishopFriendly = new Bishop(Player.WHITE, new Coordinates('g', 7));
        chessboard.addPiece(bishopFriendly);
        Assertions.assertFalse(queen.canMove(chessboard, new Coordinates('h', 8)));
    }
    @Test
    public void canMove_givenWhiteQueen_expectBeingAbleToMoveToEmptySquareBeforeOccupiedSquare() {
        Bishop bishopFriendly = new Bishop(Player.WHITE, new Coordinates('h', 8));
        chessboard.addPiece(bishopFriendly);
        Assertions.assertTrue(queen.canMove(chessboard, new Coordinates('g', 7)));
    }
    @Test
    public void canCatch_givenWhiteQueen_expectAbleToCatchBlack() {
        Bishop bishop1 = new Bishop(Player.BLACK, new Coordinates('c',5));
        chessboard.addPiece(bishop1);
        Assertions.assertTrue(queen.canCatch(chessboard, bishop1.getLocation()));
    }
    @Test
    public void canCatch_givenWhiteQueen_expectUnableToCatchWhite() {
        Bishop bishop1 = new Bishop(Player.WHITE, new Coordinates('c',5));
        chessboard.addPiece(bishop1);
        Assertions.assertFalse(queen.canCatch(chessboard, bishop1.getLocation()));
    }
    @Test
    public void canMove_givenQueen_unableToMoveWhenPathIsBlocked() {
        Rook rook1 = new Rook(Player.WHITE, new Coordinates('d',6));
        chessboard.addPiece(rook1);
        Assertions.assertFalse(queen.canMove(chessboard, new Coordinates('d',7)));
    }
    @Test
    public void canCatch_givenQueen_ableToCatchVertically() {
        Rook rook1 = new Rook(Player.BLACK, new Coordinates('d',3));
        chessboard.addPiece(rook1);
        Assertions.assertTrue(queen.canCatch(chessboard, rook1.getLocation()));
    }
    @Test
    public void canCatch_givenQueen_ableToCatchHorizontally() {
        Rook rook1 = new Rook(Player.BLACK, new Coordinates('a',4));
        chessboard.addPiece(rook1);
        Assertions.assertTrue(queen.canCatch(chessboard, rook1.getLocation()));
    }
    @Test
    public void canCatch_givenQueen_unableToCatchVerticallyWithFriendlyInTheWay() {
        Rook rook1 = new Rook(Player.BLACK, new Coordinates('d',1));
        Rook friendlyRook = new Rook(Player.WHITE, new Coordinates('d',2));
        chessboard.addPiece(rook1);
        chessboard.addPiece(friendlyRook);
        Assertions.assertFalse(queen.canCatch(chessboard, rook1.getLocation()));
    }
    @Test
    public void canCatch_givenQueen_unableToCatchHorizontallyWithFriendlyInTheWay() {
        Rook rook1 = new Rook(Player.BLACK, new Coordinates('h',4));
        Rook friendlyRook = new Rook(Player.WHITE, new Coordinates('g',4));
        chessboard.addPiece(rook1);
        chessboard.addPiece(friendlyRook);
        Assertions.assertFalse(queen.canCatch(chessboard, rook1.getLocation()));
    }
    @Test
    public void canCatch_givenWhiteQueen_expectUnableToCatchKing() {
        King king2 = new King(Player.BLACK, new Coordinates('a',1), chessboard);
        chessboard.addPiece(king2);
        Assertions.assertFalse(queen.canCatch(chessboard, king2.getLocation()));
    }
}
