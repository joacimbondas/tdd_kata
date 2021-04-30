package ax.ha.tdd.chess.pieces;

import ax.ha.tdd.chess.Chessboard;
import ax.ha.tdd.chess.Coordinates;
import ax.ha.tdd.chess.Player;
import ax.ha.tdd.chess.exceptions.InvalidMovementException;

/**
 *  Stub class in order to output a correct chessfield.
 *  This class should be deleted after implementations are done.
 */
public class ChessPieceStub extends ChessPiece {
    private final String symbol;

    public ChessPieceStub(final String symbol,
                          final Player player,
                          final Coordinates location) {
        super(player, location, symbol);
        this.symbol = symbol;
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public boolean canMove(Chessboard chessboard, Coordinates destination) {
        throw new UnsupportedOperationException("canMove has/will not be implemented in stub class");
    }

    @Override
    public boolean canCatch(Chessboard chessboard, Coordinates destination) {
        return false;
    }

    @Override
    public void checkLookup(Chessboard chessboard) {

    }
}