import java.util.Objects;

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

        String delimiter = parseDelimiter(input);
        String[] numbers = splitInput(input, delimiter);

        int sum = 0;
        for(String i: numbers) {
            if(Integer.parseInt(i) < 0) { throw new RuntimeException("Negatives not allowed: " + i); }
            if(Integer.parseInt(i) > 1000) { logger.log(Integer.parseInt(i)); }
            sum += Integer.parseInt(i);
        }
        return sum;
    }

    private String[] splitInput(String input, String delimiter) {
        if(!Objects.equals(delimiter, "")) {
            input = input.substring(3)
                    .replace("\n", "")
                    .replace(",","");
            return input.split(delimiter);
        }
        input = input.replace("\n", ",");
        return input.split(",");
    }

    private String parseDelimiter(String input) {
        if(input.contains("//")) {
            return input.charAt(2) + "";
        }
        return "";
    }
}
