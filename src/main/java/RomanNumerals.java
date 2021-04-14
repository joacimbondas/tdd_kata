import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RomanNumerals {
    private static Integer[] arabicNumbers = {1000, 500, 100, 50, 10, 5, 1};
    private static String[] romanNumbers = {"M", "D", "C", "L", "X", "V", "I"};

    public static String convert(int number) {
        String roman = "";

        for (int i = 0; i < arabicNumbers.length; i++) {
            if (arabicNumbers[i] == number) {
                return romanNumbers[i];
            }
        }
        if (number < 4 && number > 0) {
            for (int i = 0; i < number; i++) {
                roman = roman + "I";
            }
            return roman;
        }

        return converterHelper(number, roman);
    }

    public static String converterHelper(int number, String roman) {
        if (number == 0) {
            return roman;
        }
        if (number > 1000) {
            number -= 1000;
            roman = roman + romanNumbers[0];
        }
        for (int i = 1; i < arabicNumbers.length; i++) {
            if (number == arabicNumbers[i]) {
                roman = roman + romanNumbers[i];
                return roman;
            } else if (number < 4) {
                for (int j = 0; j < number; j++) {
                    roman = roman + "I";
                }
                return roman;
            } else if (number < arabicNumbers[i - 1] && number > arabicNumbers[i]) {
                if (i % 2 == 0) {
                    if (Math.abs(number - (arabicNumbers[i] * 3)) < Math.abs(number - (arabicNumbers[i - 1] - arabicNumbers[i]))) {
                        roman = roman + romanNumbers[i];
                        number -= arabicNumbers[i];
                    } else {
                        roman = roman + romanNumbers[i] + romanNumbers[i - 1];
                        number -= (arabicNumbers[i - 1] - arabicNumbers[i]);
                    }
                } else {
                    if (Math.abs(number - (arabicNumbers[i] + arabicNumbers[i + 1] * 3)) < Math.abs(number - (arabicNumbers[i - 1] - arabicNumbers[i + 1]))) {
                        roman = roman + romanNumbers[i];
                        number -= arabicNumbers[i];
                        for (int j = 0; j < number - arabicNumbers[i]; j++) {
                            roman = roman + romanNumbers[i + 1];
                            number -= arabicNumbers[i + 1];
                        }
                        return converterHelper(number, roman);
                    } else {
                        roman = roman + romanNumbers[i + 1] + romanNumbers[i - 1];
                        number -= (arabicNumbers[i - 1] - arabicNumbers[i + 1]);
                    }

                }
                return converterHelper(number, roman);
            }


        }

        return roman;
    }

}
