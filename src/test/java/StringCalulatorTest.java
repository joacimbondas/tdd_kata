import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class StringCalulatorTest {
    private StringCalculator stringCalculator;
    private Logger logger;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        logger = Mockito.mock(Logger.class);
        stringCalculator = new StringCalculator(logger);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restore() {
        System.setOut(originalOut);
    }

    @Test
    public void add_givenEmptyString_expectZero() {
        assertEquals(0, stringCalculator.add(""));
    }

    @Test
    public void add_givenOne_expectOne() {
        assertEquals(1, stringCalculator.add("1"));
    }

    @Test
    public void add_givenOnePlusTwo_expectThree() {
        assertEquals(3, stringCalculator.add("1,2"));
    }

    @Test
    public void add_givenSeveralNumbers_expectEight() {
        assertEquals(8, stringCalculator.add("1,2,3,2"));
    }

    @Test
    public void add_givenOnePlusTwoPlusThreeWithNewline_expectSix() {
        assertEquals(6, stringCalculator.add("1\r\n2,3"));
    }

    @Test
    public void add_setSemicolonDelimiter_expectThree() {
        assertEquals(3, stringCalculator.add("//;\n1;2"));
    }

    @Test
    public void add_negativeNumber_expectException() {

        try {
            stringCalculator.add("1,-2");
        } catch (Exception e) {
            String message = "negatives not allowed";
            assertEquals(message, e.getMessage());
        }

    }

    @Test
    public void add_ignoreThousand_expectTwo() {
        assertEquals(2, stringCalculator.add("1001,2"));
    }

    @Test
    public void add_setLongDelimiter_expectSix() {
        assertEquals(6, stringCalculator.add("//[***]\r\n1***2***3"));
    }

    @Test
    public void add_setSeveralDelimiters_expectSix() {
        assertEquals(6, stringCalculator.add("//[*][%]\r\n1*2%3"));
    }

    @Test
    public void add_setSeveralLongDelimiters_expectSix() {
        assertEquals(6, stringCalculator.add("//[***][%%%]\r\n1***2%%%3"));
    }

    @Test
    public void log_largerThanThousand() {

    }

    @Test
    public void mock_testOneOverThousand() {
        stringCalculator.add("2000,3");
        Mockito.verify(logger, Mockito.times(1)).log(Mockito.eq(2000));
    }

    @Test
    public void mock_testSeveralOverThousand() {
        stringCalculator.add("2000,3,3000,500");
        Mockito.verify(logger, Mockito.atLeastOnce()).log(Mockito.eq(2000));
        Mockito.verify(logger, Mockito.atLeastOnce()).log(Mockito.eq(3000));
    }

    @Test
    public void mock_testNoneOverThousand() {
        stringCalculator.add("900,3");
        Mockito.verify(logger, Mockito.never()).log(Mockito.eq(900));
    }

    @Test
    public void main_testWelcomeAndHelpText() {
        createInputCommand("\r\n");
        StringCalculator.main();
        assertEquals("Welcome to string calculator\r\nType scalc and the numbers you want to add",outContent.toString().trim());
    }
    @Test
    public void main_testResultIs6Output() {

        createInputCommand("scalc '1,2,3'\r\n");
        StringCalculator.main();
        assertEquals("Welcome to string calculator\r\nType scalc and the numbers you want to add\r\nThe result is 6",outContent.toString().trim());
    }
    @Test
    public void main_testWithDelimiter_expect7() {
        createInputCommand("scalc '//[***][%%%]\r\n1***2%%%4'\r\n");
        StringCalculator.main();
        assertEquals("Welcome to string calculator\r\nType scalc and the numbers you want to add\r\nThe result is 7",outContent.toString().trim());
    }


    public void createInputCommand(String... args) {
        final String command = String.join("\r\n", args) +"\r\n";
        final InputStream targetStream = new ByteArrayInputStream(command.getBytes());
        System.setIn(targetStream);

    }


}
