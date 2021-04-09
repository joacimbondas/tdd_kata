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

    @Test
    public void convert_givenFour_expectIV() {
        assertEquals("IV", RomanNumerals.convert(4));
    }

    @Test
    public void convert_givenSix_expectVI() {
        assertEquals("VI", RomanNumerals.convert(6));
    }

    @Test
    public void convert_givenSeven_expectVII() {
        assertEquals("VII", RomanNumerals.convert(7));
    }
    @Test
    public void convert_givenEight_expectVIII() {
        assertEquals("VIII", RomanNumerals.convert(8));
    }
}
