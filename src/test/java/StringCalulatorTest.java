import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringCalulatorTest {
    @Test
    public void add_givenEmptyString_expectZero() {
        Assertions.assertEquals(0,StringCalculator.add(""));

    }

    @Test
    public void add_givenOne_expectOne() {
        Assertions.assertEquals(1,StringCalculator.add("1"));

    }
    @Test
    public void add_givenOnePlusTwo_expectOne() {
        Assertions.assertEquals(3,StringCalculator.add("1,2"));

    }
}
