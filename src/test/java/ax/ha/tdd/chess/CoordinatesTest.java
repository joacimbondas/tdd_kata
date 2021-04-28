package ax.ha.tdd.chess;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CoordinatesTest {

    @Test
    public void equals_givenSameArgument_expectObjectEquals() {
        Assertions.assertEquals(new Coordinates(0, 7), new Coordinates(0, 7));
    }

    @Test
    public void Coordinates_givenAlgebraicNotation_expectMatchingCoordinates() {
        Assertions.assertEquals(new Coordinates('a',1), new Coordinates(0, 7));
        Assertions.assertEquals(new Coordinates('f',3), new Coordinates(5, 5));
        Assertions.assertEquals(new Coordinates('a',8), new Coordinates(0, 0));
        Assertions.assertEquals(new Coordinates('g',8), new Coordinates(6, 0));
    }

    @Test
    public void Coordinates_givenBadAlgebraicNotation_expectIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates('A',1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates('I',1);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates('a',0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates('a',9);
        });
    }


    @Test
    public void Coordinates_givenBadIndexes_expectIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates(-1,1);
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates(0,-1);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates(8,0);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            new Coordinates(0,8);
        });
    }
}