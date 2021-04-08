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

        String delimiter = "\\s+|,\\s*|\\.\\s*";
        boolean longDelimiter = false;
        if (numbers.startsWith("//")) {

            List<String> matchList = new ArrayList<>();
            Pattern regex = Pattern.compile("\\[.*?\\]");
            Matcher regexMatcher = regex.matcher(numbers);
            while (regexMatcher.find()) {
                matchList.add(regexMatcher.group(0));
                longDelimiter = true;
            }
            if (longDelimiter) {
                delimiter = matchList.get(0);
                String temp = "//" + delimiter + "\n";
                numbers = numbers.replace(temp, "");
            } else {
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

        List<String> as = Arrays.stream(numbers.split(delimiter)).collect(Collectors.toList());
        for(String str:as){

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
