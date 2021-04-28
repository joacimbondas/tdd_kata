package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.Knight;
import ax.ha.tdd.chess.pieces.Pawn;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class KnightTest {
    private Chessboard chessboard;
    private Knight knight;

    @BeforeEach
    public void setup() {
        chessboard = new Chessboard();
        knight = new Knight(Player.WHITE, new Coordinates('e',4));
        chessboard.addPiece(knight);
    }
    @Test
    public void canMove_givenEmptyBoardWithKnight_expectAbleToMoveAccordingToRules() {
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('c',5)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('c',3)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('d',6)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('d',2)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('f',6)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('f',2)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('g',5)));
        Assertions.assertTrue(knight.canMove(chessboard, new Coordinates('g',3)));
    }
    @Test
    public void canMove_givenEmptyBoardWithKnight_expectNotBeingAbleToMoveWhenBreakingRules() {
        Assertions.assertFalse(knight.canMove(chessboard, new Coordinates('c',6)));
        Assertions.assertFalse(knight.canMove(chessboard, new Coordinates('c',2)));
        Assertions.assertFalse(knight.canMove(chessboard, new Coordinates('d',5)));
        Assertions.assertFalse(knight.canMove(chessboard, new Coordinates('d',4)));
    }
    @Test
    public void canMove_givenWhiteKnight_expectNotBeingAbleToMoveToOccupiedSquare() {
        Knight knight2 = new Knight(Player.WHITE, new Coordinates('c',5));
        chessboard.addPiece(knight2);
        Assertions.assertFalse(knight.canMove(chessboard, knight2.getLocation()));
    }
    @Test
    public void canCatch_givenWhiteKnight_expectableToCatchBlack() {
        Knight knight2 = new Knight(Player.BLACK, new Coordinates('c',5));
        chessboard.addPiece(knight2);
        Assertions.assertTrue(knight.canCatch(chessboard, knight2.getLocation()));
    }
}
