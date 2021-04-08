import java.util.Arrays;

public class StringCalculator {
    public static int add(String numbers) {
        return Arrays.stream(numbers.split(","))
                .filter(number->!"".equals(number))
                .mapToInt(Integer::parseInt).sum();
    }
}
