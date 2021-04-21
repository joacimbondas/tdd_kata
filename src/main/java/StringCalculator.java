import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {


    public static int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }
        String delimiter = ",|\n";

        boolean longDelimiter = false;
        if (numbers.startsWith("//")) {
            List<String> matchList = new ArrayList<>();
            Pattern regex = Pattern.compile("\\[.*?\\]");
            Matcher regexMatcher = regex.matcher(numbers);
            while (regexMatcher.find()) {
                matchList.add(regexMatcher.group());
                longDelimiter = true;
            }
            if (longDelimiter) {
                String cleanupString = "";
                for (String str : matchList) {
                    cleanupString = cleanupString + str;
                    delimiter = delimiter +"|"+ str;
                }
                String cleanupInput = "//" + cleanupString + "\n";
                numbers = numbers.replace(cleanupInput, "");
            }else {
                delimiter = String.valueOf(numbers.charAt(2));
                numbers = numbers.substring(4);
            }

        }
        if (numbers.contains("-") && !delimiter.contains("-")) {
            try {
                throw new NullPointerException("negatives not allowed");
            } catch (NullPointerException e) {
                throw e;
            }
        }

        List<Integer> intList = Arrays.stream(numbers.split(delimiter))
                .filter(number -> !"".equals(number))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for (int i = 0; i < intList.size(); i++) {
            if (intList.get(i) >= 1000) {
                intList.set(i, 0);
            }
        }

        return intList.stream()
                .reduce(0, Integer::sum);
    }
}
