public class RomanNumerals {

    public static String convert(int number) {
        String roman = "";
        for(int i = 0; i < number; i++) {
            roman = roman + "I";
        }
        return roman;
    }
}
