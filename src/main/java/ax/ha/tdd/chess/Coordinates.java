package ax.ha.tdd.chess;

import java.util.Objects;

public class Coordinates {
    private final int xCoordinates;
    private final int yCoordinates;

    public Coordinates(int xCoordinates, int yCoordinates) {
        if (xCoordinates < 0 || xCoordinates > 7) {
            throw new IllegalArgumentException("xCoordinates " + xCoordinates + " is not valid");
        }
        if (yCoordinates < 0 || yCoordinates > 7) {
            throw new IllegalArgumentException("yCoordinates " + yCoordinates + " is not valid");
        }

        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
    }

    // In case you want to use Algebraic notation when writing tests instead
    public Coordinates(char letter, int number) {
        if (((int)letter) < ((int)'a') || ((int)letter) > ((int)'h')) {
            throw new IllegalArgumentException("Letter " + letter + " is not valid");
        }
        if (number < 1 || number > 8) {
            throw new IllegalArgumentException("Number " + number + " is not valid");
        }
        this.xCoordinates = ((int)letter) - ((int)'a');
        this.yCoordinates = 8 - number;
    }

    public int getXCoordinates() {
        return xCoordinates;
    }

    public int getYCoordinates() {
        return yCoordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return xCoordinates == that.xCoordinates &&
                yCoordinates == that.yCoordinates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xCoordinates, yCoordinates);
    }

    @Override
    public String toString() {
        return "X:" + xCoordinates +
                ", Y:" + yCoordinates;
    }
}
