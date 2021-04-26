import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class StringCalculator {
    private Logger logger;
    private static final Pattern pattern = Pattern.compile("scalc '(.*[\\r\\n]*.*)'");
    public StringCalculator(Logger logger) {
        this.logger = logger;
    }
    public StringCalculator() {

    }
    public static void main(String... args) {
        System.out.println("Welcome to string calculator");
        System.out.println("Type scalc and the numbers you want to add");
        Scanner scanner = new Scanner(System.in);

        StringCalculator calc = new StringCalculator();
        while(true){
            String number = scanner.nextLine();
            if("".equals(number)){
                break;
            }
            while(!pattern.matcher(number).matches()) {
                number += "\r\n" + scanner.nextLine();
            }
            final String calculatorInput = number.replaceAll("[a-z' ]", "");
            System.out.println("The result is "+ calc.add(calculatorInput));
        }



    }

    public int add(String numbers) {

        if (numbers == null || numbers.isEmpty()) {
            return 0;
        }
        String delimiter = ",|\r\n";

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
                    delimiter = delimiter + "|" + str;
                }
                String cleanupInput = "//" + cleanupString + "\r\n";
                numbers = numbers.replace(cleanupInput, "");
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

        List<Integer> intList = Arrays.stream(numbers.split(delimiter))
                .filter(number -> !"".equals(number))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());

        for (int i = 0; i < intList.size(); i++) {
            if (intList.get(i) >= 1000) {
                log(intList.get(i));
                intList.set(i, 0);
            }
        }

        return intList.stream()
                .reduce(0, Integer::sum);
    }


    public void log(Integer number) {
        logger.log(number);
    }

}
