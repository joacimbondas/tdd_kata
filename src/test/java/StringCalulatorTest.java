import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalulatorTest {


    @Test
    public void add_givenEmptyString_expectZero() {
        assertEquals(0, StringCalculator.add(""));
    }

    @Test
    public void add_givenOne_expectOne() {
        assertEquals(1, StringCalculator.add("1"));
    }

    @Test
    public void add_givenOnePlusTwo_expectThree() {
        assertEquals(3, StringCalculator.add("1,2"));
    }

    @Test
    public void add_givenSeveralNumbers_expectEight() {
        assertEquals(8, StringCalculator.add("1,2,3,2"));
    }

    @Test
    public void add_givenOnePlusTwoPlusThreeWithNewline_expectSix() {
        assertEquals(6, StringCalculator.add("1\n2,3"));
    }

    @Test
    public void add_setSemicolonDelimiter_expectThree() {
        assertEquals(3, StringCalculator.add("//;\n1;2"));
    }

    @Test
    public void add_negativeNumber_expectException() {

        try {
            StringCalculator.add("1,-2");
        } catch (Exception e) {
            String message = "negatives not allowed";
            assertEquals(message, e.getMessage());
        }

    }

    @Test
    public void add_ignoreThousand_expectTwo() {
        assertEquals(2, StringCalculator.add("1001,2"));
    }

    @Test
    public void add_setLongDelimiter_expectSix() {
        assertEquals(6, StringCalculator.add("//[***]\n1***2***3"));
    }

    @Test
    public void add_setSeveralDelimiters_expectSix() {
        assertEquals(6, StringCalculator.add("//[*][%]\n1*2%3"));
    }
    @Test
    public void add_setSeveralLongDelimiters_expectSix() {
        assertEquals(6, StringCalculator.add("//[***][%%%]\n1***2%%%3"));
    }
}
