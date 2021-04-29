package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class CheckmateTest {
    private Chessboard chessboard;
    Pawn pawnW1;
    Pawn pawnW2;
    Pawn pawnW3;
    Pawn pawnB1;
    Pawn pawnB2;
    Pawn pawnB3;
    Bishop bishopB1;
    Bishop bishopW1;
    Rook rookB1;
    Rook rookW1;
    King kingW;
    King kingB;

    //http://www.chesscorner.com/tutorial/basic/check/checka.gif
    public void setCheckScenario1()
    {
        chessboard = new Chessboard();
        kingW = new King(Player.WHITE, new Coordinates('g', 1));
        rookW1 = new Rook(Player.WHITE, new Coordinates('f', 1));
        pawnW1 = new Pawn(Player.WHITE, new Coordinates('f', 2));
        pawnW2 = new Pawn(Player.WHITE, new Coordinates('g', 3));
        pawnW3 = new Pawn(Player.WHITE, new Coordinates('h', 2));
        bishopW1 = new Bishop(Player.WHITE, new Coordinates('f', 5));
        pawnB1 = new Pawn(Player.BLACK, new Coordinates('a', 7));
        pawnB2 = new Pawn(Player.BLACK, new Coordinates('b', 7));
        pawnB3 = new Pawn(Player.BLACK, new Coordinates('c', 7));
        rookB1 = new Rook(Player.BLACK, new Coordinates('a', 5));
        bishopB1 = new Bishop(Player.BLACK, new Coordinates('e', 8));
        kingB = new King(Player.BLACK, new Coordinates('c', 8));

        chessboard.addPiece(kingW);
        chessboard.addPiece(rookW1);
        chessboard.addPiece(pawnW1);
        chessboard.addPiece(pawnW2);
        chessboard.addPiece(pawnW3);
        chessboard.addPiece(bishopW1);
        chessboard.addPiece(pawnB1);
        chessboard.addPiece(pawnB2);
        chessboard.addPiece(pawnB3);
        chessboard.addPiece(rookB1);
        chessboard.addPiece(bishopB1);
        chessboard.addPiece(kingB);
    }


    @Test
    public void isCheck_checkScenario1_expectTrue() {
        setCheckScenario1();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessboard.getPiece(new Coordinates(i,j))!=null){
                    chessboard.getPiece(new Coordinates(i,j)).checkLookup(chessboard);
                }
            }
        }
        Assertions.assertTrue(kingB.isCheck());
    }
    @Test
    public void isCheck_checkScenario1Modified_expectFalse() {
        setCheckScenario1();
        rookB1.move(chessboard, bishopW1.getLocation());
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(chessboard.getPiece(new Coordinates(i,j))!=null){
                    chessboard.getPiece(new Coordinates(i,j)).checkLookup(chessboard);
                }
            }
        }
        Assertions.assertTrue(kingB.isCheck());
    }

}
