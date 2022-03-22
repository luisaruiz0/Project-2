import org.junit.Test;

import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
	public void convertToPostfixTest() {
		String output = Calculator.convertToPostfix("a*b/(c-a)+d*e");
		assertEquals("ab*ca-/de*+", output);
	}

    @Test 
	public void theEvaluatePostfix1() {
        Calculator calc = new Calculator();
        String string = "23*42-/56*+";
        assertEquals(33, calc.evaluatePostfix(string));
	}
}
