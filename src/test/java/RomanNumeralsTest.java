import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsTest {


    @ParameterizedTest
    @CsvSource({
            "2, II",
            "3,III",
            "4, IV",
            "5, V",
            "6, VI",
            "7, VII",
            "8, VIII",
            "9, IX",
            "49, XLIX",
            "1972, MCMLXXII"
    })
    public void testAllAtOnce(int input, String expected) {
        String actualValue = RomanNumerals.convert(input);
        assertEquals(expected, actualValue);
    }

}
