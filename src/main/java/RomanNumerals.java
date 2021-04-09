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

        for (int i = 1; i < arabicNumbers.length; i++) {
            if (number == arabicNumbers[i]) {
                roman = roman + romanNumbers[i];
                return roman;
            } else if (number < 4 && number > 0) {
                for (int j = 0; j < number; j++) {
                    roman = roman + "I";
                }
                return roman;
            }
            //if between 50 and 10
            else if (number < arabicNumbers[i - 1] && number > arabicNumbers[i]) {
                //difference = 1
                int difference = arabicNumbers[i - 1] - number;
                if (number == 9) {
                    roman = roman + "IX";
                    return roman;
                } else if (difference == arabicNumbers[i]) {
                    roman = roman + romanNumbers[i] + romanNumbers[i - 1];
                    return roman;
                } else {
                    number = number - (arabicNumbers[i - 1] - arabicNumbers[i]);
                    if (number < 5) {
                        roman = roman + romanNumbers[i];
                    } else {
                        roman = roman + romanNumbers[i] + romanNumbers[i - 1];
                    }

                    return converterHelper(number, roman);
                }
            } else if (number > 1000) {
                roman = roman + "M";
                number -= 1000;
                return converterHelper(number, roman);
            }


        }

        return roman;
    }

}
