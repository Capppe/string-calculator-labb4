import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringCalculatorCLITests {
    @Test
    public void testEmptyNumberString() {

        String input = "scalc ''\nexit";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        OutputStream outputStream = new ByteArrayOutputStream();

        StringCalculatorCLI calculator = new StringCalculatorCLI(inputStream, outputStream);
        calculator.run();

        assertEquals("Welcome!\nThe result is 0\nExiting...\n", outputStream.toString());
    }

    @Test
    public void testEmptyNumberString2() {
        String input = "scalc ''\nexit";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        StringCalculatorCLI calculator = new StringCalculatorCLI();
        calculator.run();

        assertTrue(outputStream.toString().contains("0\nExiting...\n"));

    }

    @Test
    public void testWelcomeOutput() {
        String input = "scalc ''\nexit";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        StringCalculatorCLI calculator = new StringCalculatorCLI();
        calculator.run();

        assertTrue(outputStream.toString().contains("Welcome!\n"));
    }

    @Test
    public void testSimpleAddition() {
        String input = "scalc '1,2,3'\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        StringCalculatorCLI calculator = new StringCalculatorCLI();
        calculator.run();

        assertTrue(outputStream.toString().contains("The result is 6\n"));
    }

    @Test
    public void testMultiLineInput() {
        String input = "scalc '1,2,3'\nscalc '2,3,4'\nscalc '1,2,1'\nexit\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        StringCalculatorCLI calculator = new StringCalculatorCLI();
        calculator.run();

        assertTrue(outputStream.toString().contains("The result is 6"));
        assertTrue(outputStream.toString().contains("The result is 9"));
        assertTrue(outputStream.toString().contains("The result is 4"));
    }

    @Test
    public void testOwnSeparator() {
        String input = "scalc '//;\n1;2;4'\nexit";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        OutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        StringCalculatorCLI calculator = new StringCalculatorCLI();
        calculator.run();

        assertTrue(outputStream.toString().contains("The result is 7"));
    }

}