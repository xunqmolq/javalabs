package main.com.labs.lab1;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class StringCalculator {

    public int add(String numbers) throws CalculatorException {
        String delimiterDefault = ",|\n";
        int negative = 0;
        String negativeNumbers = "";
        String[] stringArray;

        numbers = numbers.replaceAll(" ", "");
        if (numbers == "") {
            return 0;
        }
        if (numbers.indexOf("//") != -1) {
            if(numbers.indexOf("\\n")- numbers.indexOf("//") == 3){
                String delimiter = numbers.substring(2,3);
                numbers = numbers.substring(numbers.indexOf("\\n")+2);
                numbers = numbers.replaceAll(Pattern.quote(delimiter),",");
            }else {
                String delimiter = numbers.substring(numbers.indexOf("//") + 2, numbers.indexOf("\\n"));
                numbers = numbers.substring(numbers.indexOf("\\n") + 2);
                if (delimiter.endsWith("]") && delimiter.indexOf("[") == 0) {
                    Pattern pattern = Pattern.compile("\\[(.*?)\\]");
                    Matcher matcher = pattern.matcher(delimiter);

                    while (matcher.find()) {
                        for (int i = 0; i <= matcher.groupCount(); i++) {
                            String m = matcher.group(i);
                            numbers = numbers.replaceAll(Pattern.quote(m), ",");
                        }

                    }
                    numbers = numbers.replaceAll(Pattern.quote(delimiterDefault), ",");
                }
            }
            stringArray = numbers.split(delimiterDefault);
        } else {
            stringArray = numbers.split(delimiterDefault);
        }
        int res = 0;
        for (String elem : stringArray) {
            Integer n;
            try {
                n = Integer.parseInt(elem);
            }catch (NumberFormatException exception){
                throw new CalculatorException("Some values can't be convert into integer");
            }
            if (n < 0) {
                negativeNumbers += String.valueOf(n) + " ";
                negative ++;
            }
            if (n < 1001) {
                res += n;
            }

        }
        if(negative != 0){
            res = -1;
            throw new CalculatorException("Some values less than zero: "+ negativeNumbers);
        }
        return res;
    }
}
