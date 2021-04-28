package ax.ha.tdd.chess.exceptions;

public class InvalidMovementException extends RuntimeException {

    public InvalidMovementException(String message) {
        super(message);
    }
}
