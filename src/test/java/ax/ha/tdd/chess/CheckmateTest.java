package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
    ArrayList<ChessPiece> pieces;

    //http://www.chesscorner.com/tutorial/basic/check/checka.gif
    public void setCheckScenario1() {
        pieces = new ArrayList<>();
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

        pieces.add(kingW);
        pieces.add(rookW1);
        pieces.add(pawnW1);
        pieces.add(pawnW2);
        pieces.add(pawnW3);
        pieces.add(bishopW1);
        pieces.add(pawnB1);
        pieces.add(pawnB2);
        pieces.add(pawnB3);
        pieces.add(rookB1);
        pieces.add(bishopB1);
        pieces.add(kingB);
        for (ChessPiece c : pieces) {
            chessboard.addPiece(c);
        }
    }

    @Test
    public void isCheck_checkScenario1_expectTrue() {
        setCheckScenario1();
        chessboard.checkLookup(pieces, kingW, kingB);
        Assertions.assertTrue(kingB.isCheck());
        Assertions.assertFalse(kingW.isCheck());
    }

    @Test
    public void isCheck_checkScenario1Modified_expectFalse() {
        setCheckScenario1();
        chessboard.checkLookup(pieces, kingW, kingB);
        Assertions.assertTrue(kingB.isCheck());
        rookB1.move(chessboard, bishopW1.getLocation());

        chessboard.checkLookup(pieces, kingW, kingB);
        Assertions.assertFalse(kingB.isCheck());
    }

    @Test
    public void isCheck_possibleToCatchOpponentThatThreatensKing_expectTrue() {
        setCheckScenario1();
        ArrayList<ChessPiece> threateningPieces = new ArrayList<>();
        for (ChessPiece c : pieces) {
            c.checkLookup(chessboard);
            if (c.isThreateningKing()) {
                threateningPieces.add(c);
            }
        }
        if (threateningPieces.size() > 1) {
            //better move king
        } else {
            for (ChessPiece c : pieces) {
                if(!c.getPlayer().equals(threateningPieces.get(0).getPlayer())) {
                    c.move(chessboard, threateningPieces.get(0).getLocation());
                }

            }
        }
        chessboard.checkLookup(pieces, kingW, kingB);
        Assertions.assertFalse(kingB.isCheck());
    }

}
