package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.ChessPiece;

import java.util.ArrayList;

public class WinningConditionChecker {
    private static ArrayList<ChessPiece> pieces;

    public static WinningState checkState(Chessboard chessboard, Player player) {
        pieces = new ArrayList<>();
        setAllPiecesList(chessboard);

        chessboard.checkLookup(pieces);
        if(checkCheck()){
            return WinningState.CHECK;
        }
        return WinningState.PLAYING;
    }
    public static boolean checkCheck() {
        for(ChessPiece c : pieces) {
            if(c.isCheck()) {
                return true;
            }
        }
        return false;
    }
    public static void setAllPiecesList(Chessboard chessboard) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (chessboard.getPiece(new Coordinates(i, j)) != null) {
                    pieces.add(chessboard.getPiece(new Coordinates(i, j)));
                }
            }
        }
    }

}
