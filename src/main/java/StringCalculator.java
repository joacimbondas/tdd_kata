import java.util.Arrays;

public class StringCalculator {


    public static int add(String numbers) throws NumberFormatException {

        String delimiter = "\\s+|,\\s*|\\.\\s*";

        if (numbers.startsWith("\\")) {
            delimiter = String.valueOf(numbers.charAt(1));
            numbers = numbers.substring(3);
        }
        for (int i = 0; i < numbers.length()-1; i++) {
            if (!Character.isDigit(numbers.charAt(i)) && !Character.isDigit(numbers.charAt(i+1))) {
                double d = Double.parseDouble(numbers);

            }
        }


        return Arrays.stream(numbers.split(delimiter))
                .filter(number -> !"".equals(number))
                .mapToInt(Integer::parseInt).sum();
    }
}
