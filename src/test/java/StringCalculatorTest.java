import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    public void beforeEach() {
        calculator = new StringCalculator();
    }

    @Test
    public void testEmptyStringReturnsZero() {
        Assertions.assertEquals(0, calculator.add(""));
    }

    @Test
    public void testAdditionOfThreeNumbers() { Assertions.assertEquals(6, calculator.add("1,2,3")); }

    @Test
    public void testLargeAmountsOfNumbers() {
        Assertions.assertEquals(10, calculator.add("1,2,3,4"));
        Assertions.assertEquals(10, calculator.add("1,2,3,4"));
        Assertions.assertEquals(100, calculator.add("1,2,3,4,4,3,2,1,10,20,30,20"));
    }

    @Test
    public void testInputWithNewlineAndCommas() {
        Assertions.assertEquals(10, calculator.add("1,2\n3,4"));
        Assertions.assertEquals(10, calculator.add("1\n2\n3,4"));
        Assertions.assertEquals(20, calculator.add("1,2\n3,4\n4\n3,2,1"));
    }

    @Test public void testInputWithCustomDelimiter() {
        Assertions.assertEquals(10, calculator.add("//;\n1;2;3;4"));
        Assertions.assertEquals(10, calculator.add("//i,1i2i3i4"));
    }

    @Test
    public void testInputWithNegativeNumber() {
        Assertions.assertThrows(RuntimeException.class, () -> calculator.add("-1,2"));
        Assertions.assertThrows(RuntimeException.class, () -> calculator.add("//;-1;2;3"));
        Assertions.assertThrows(RuntimeException.class, () -> calculator.add("-1,2,3\n4,5"));
    }
}
