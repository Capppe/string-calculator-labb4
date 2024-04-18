import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

    private final Logger logger;

    public StringCalculator(){
        logger = new LoggerStub();
    }

    public StringCalculator(Logger logger){
        this.logger = logger;
    }

    public int add(String input) {
        // TODO use your previous implementation, and include the tests too!
        // Also add logging necessary for Labb 4 exercise 1
        if(input.isEmpty()) { return 0; }

        List<String> delimiters = parseDelimiter(input);
        String[] numbers = splitInput(input, delimiters);

        int sum = 0;
        for(String i: numbers) {
            int num = Integer.parseInt(i);
            if(num < 0) { throw new RuntimeException("Negatives not allowed: " + i); }
            if(num > 1000) { logger.log(num); }
            sum += num;
        }
        return sum;
    }

    private String[] splitInput(String input, List<String> delimiters) {
        if(input.startsWith("//") && !input.contains("]")) { input = input.substring(3); }
        if(input.contains("]")) {
            input = input.substring(input.lastIndexOf("]")).substring(1);
        }

        for(String delim : delimiters) {
            input = input.replace(delim, ",");
        }
        if(!Character.isDigit(input.charAt(0)) && input.charAt(0) != '-') { input = input.substring(1); }

        input = input.replace("\n", ",");
        //throw new RuntimeException(input);
        return input.split(",");
    }

    private List<String> parseDelimiter(String input) {
        List<String> delimiters = new ArrayList<>();
        if(!input.startsWith("//")) { delimiters.add(","); return delimiters; }
        input = input.substring(2);

        if(!input.startsWith("[")) { delimiters.add(input.charAt(0) + ""); return delimiters; }
        //input = input.split("\n")[0];
        input = input.substring(0, input.lastIndexOf("]")).substring(1);
        String[] delims = input.split("]\\[");

        return Arrays.asList(delims);
    }
}










