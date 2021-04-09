import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsTest {

    @Test
    public void convert_givenTwo_expectII() {
        assertEquals("II", RomanNumerals.convert(2));
    }
    @Test
    public void convert_givenThree_expectIII() {
        assertEquals("III", RomanNumerals.convert(3));
    }
    @Test
    public void convert_givenFive_expectV() {
        assertEquals("V", RomanNumerals.convert(5));
    }
}
