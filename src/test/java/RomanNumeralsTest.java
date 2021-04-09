import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsTest {

    @Test
    public void add_givenEmptyString_expectZero() {
        assertEquals("II", RomanNumerals.convert(2));
    }
}
