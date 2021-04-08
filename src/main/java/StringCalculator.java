import java.util.Arrays;

public class StringCalculator {


    public static int add(String numbers) throws NumberFormatException {
        for (int i = 0; i < numbers.length(); i++) {
            for (int j = i + 1; j < numbers.length(); j++) {
                if (!Character.isDigit(numbers.charAt(i)) && !Character.isDigit(numbers.charAt(j))) {
                    double d = Double.parseDouble(numbers);
                }
            }
        }

        return Arrays.stream(numbers.split("\\s+|,\\s*|\\.\\s*"))
                .filter(number -> !"".equals(number))
                .mapToInt(Integer::parseInt).sum();
    }
}
