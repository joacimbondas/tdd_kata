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
        for (int i = 0; i < arabicNumbersList.size(); i++) {
            if (arabicNumbersList.get(i) == number) {
                return romanNumbersList.get(i);
            }
        }

        for (int i = 0; i < number; i++) {
            roman = roman + "I";
        }


        return roman;
    }
}
