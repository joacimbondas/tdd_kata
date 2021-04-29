package ax.ha.tdd.chess;

import ax.ha.tdd.chess.pieces.ChessPiece;
import ax.ha.tdd.chess.pieces.King;

import java.util.ArrayList;

public class WinningConditionChecker {
    private static ArrayList<ChessPiece> pieces;
    private static King king;
    public static WinningState checkState(Chessboard chessboard, Player player) {
        pieces = new ArrayList<>();
        setAllPiecesList(chessboard);
        chessboard.checkLookup();
        if(checkCheck(chessboard, player)){
            return WinningState.CHECK;
        }
        if(checkCheckmate(chessboard, player)){
            return WinningState.CHECKMATE;
        }
        return WinningState.PLAYING;
    }
    public static boolean checkCheck(Chessboard chessboard, Player player) {
        for(ChessPiece c : pieces) {
            if(c.isCheck()) {
                king = (King)c;
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
    public static boolean checkCheckmate(Chessboard chessboard, Player player) {
        King king;
        if(player.equals(Player.WHITE)) {
            king = chessboard.getKingW();
        }
        else{
            king = chessboard.getKingB();
        }

        if(king!=null) {
            Coordinates oldPos = king.getLocation();
            ArrayList<Coordinates> escapeRoutes = king.tryToEscape(chessboard);
            for(Coordinates c: escapeRoutes) {
                king.move(chessboard, c);
                chessboard.checkLookup();
                if(!checkCheck(chessboard, player)) {
                    king.move(chessboard, oldPos);
                    return false;
                }
            }
            return true;
        }
        return false;
    }


}