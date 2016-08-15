package junitExample;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

	private final static float maxRelativeError = 0.005f;
	private Calculator calculator;

	@Before
	public void setUp() {
		calculator = new Calculator();
	}

	@Test
	public void testISqrtWithZeroRadix() {
		float radix = 0;
		float isqrt = calculator.isqrt(radix);
		assertTrue(Float.isNaN(isqrt));
	}
	
	@Test(expected = ArithmeticException.class)
	public void testISqrtWithNegativeRadix() {
		calculator.isqrt(-1);
	}

	@Test
	public void smokeTestIsqrt() {
		for (float radix = 0.001f; radix < 1E6; radix *= 2) {
			testIsqrtWithPositiveRadix(radix);
		}
	}

	private void testIsqrtWithPositiveRadix(float radix) {
		float expectedValue = calculator.isqrtStdlib(radix);
		assertEquals(expectedValue, calculator.isqrt(radix), expectedValue * maxRelativeError);
	}
}
