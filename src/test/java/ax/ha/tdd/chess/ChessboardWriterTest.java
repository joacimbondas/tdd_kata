package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.ChessPieceStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ChessboardWriterTest {



    @Test
    public void print_emptyChessboard_printsOkay() {
        final String expectedChessboard= 
                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n\n" +
                                            
                " *    *    *    *    *    *    *    * \n";
        Assertions.assertEquals(expectedChessboard, new ChessboardWriter().print(new Chessboard()));
    }

    @Test
    public void print_chessboardWithSinglePawn_printsOkay() {
        final String expectedChessboard=
                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *   B-P   *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n\n" +

                " *    *    *    *    *    *    *    * \n";
        Chessboard chessboard = new Chessboard();
        chessboard.addPiece(new ChessPieceStub("P", Player.BLACK, new Coordinates(2, 4)));
        Assertions.assertEquals(expectedChessboard, new ChessboardWriter().print(chessboard));
    }

    @Test
    public void print_fullChessboard_printsOkay() {
        final String expectedChessboard=
               "B-R  B-K  B-B  B-K  B-Q  B-B  B-K  B-R\n\n" +
                                            
               "B-P  B-P  B-P  B-P  B-P  B-P  B-P  B-P\n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               " *    *    *    *    *    *    *    * \n\n" +
                                            
               "W-P  W-P  W-P  W-P  W-P  W-P  W-P  W-P\n\n" +
                                            
               "W-R  W-K  W-B  W-K  W-Q  W-B  W-K  W-R\n";
        final Chessboard chessboard = Chessboard.fullBoard();

        Assertions.assertEquals(expectedChessboard, new ChessboardWriter().print(chessboard));
    }
}
