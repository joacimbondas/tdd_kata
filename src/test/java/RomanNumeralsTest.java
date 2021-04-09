import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsTest {

    @Test
    public void convert_givenTwo_expectII() {
        assertEquals("II", RomanNumerals.convert(2));
    }
}
