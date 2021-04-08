import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StringCalculator {


    public static int add(String numbers) {

        String delimiter = "\\s+|,\\s*|\\.\\s*";

        if (numbers.startsWith("\\")) {
            delimiter = String.valueOf(numbers.charAt(1));
            numbers = numbers.substring(3);
        }
        if(numbers.contains("-") && !delimiter.contains("-")){
            try
            {
                throw new NullPointerException("negatives not allowed");
            }
            catch(NullPointerException e)
            {
                throw e; // rethrowing the exception
            }
        }
        for (int i = 0; i < numbers.length()-1; i++) {
            if (!Character.isDigit(numbers.charAt(i)) && !Character.isDigit(numbers.charAt(i+1))) {
                double d = Double.parseDouble(numbers);

            }
        }

        List<Integer> intList = Arrays.stream(numbers.split(delimiter))
                .filter(number -> !"".equals(number))
                .mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        for (int i = 0; i < intList.size(); i++) {
            if(intList.get(i)>=1000){
                intList.set(i,0);
            }
        }
        return intList.stream()
                .reduce(0, Integer::sum);

    }
}
