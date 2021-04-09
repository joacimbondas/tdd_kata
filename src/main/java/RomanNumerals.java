import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RomanNumerals {

    public static String convert(int number) {
        String roman = "";
        Integer[] arabicNumbers = {1, 5, 10, 50, 100, 500, 1000};
        String[] romanNumbers = {"I", "V", "X", "L", "C", "D", "M"};
        List<Integer> arabicNumbersList = new ArrayList<>(Arrays.asList(arabicNumbers));
        List<String> romanNumbersList = new ArrayList<>(Arrays.asList(romanNumbers));
        int[] allowedMultipleIndex = {0, 2, 4, 6};
        if (number < 4 && number > 0) {
            for (int i = 0; i < number; i++) {
                roman = roman + "I";
            }
        }

        for (int i = 1; i < arabicNumbers.length - 1; i++) {
            if (arabicNumbers[i] == number) {
                return romanNumbers[i];
            } else if (arabicNumbers[i] - arabicNumbers[i - 1] == number) {
                return romanNumbers[i - 1] + romanNumbers[i];
            } else if (arabicNumbers[i] + arabicNumbers[i - 1] == number) {
                return romanNumbers[i] + romanNumbers[i - 1];
            } else if (number < arabicNumbers[i + 1] && number > arabicNumbers[i]) {
                int tempNumber = number - arabicNumbers[i];
                roman = romanNumbers[i];
                for (int j = 0; j < tempNumber; j++) {
                    roman = roman + "I";
                }
            }
        }


        return roman;
    }
}
