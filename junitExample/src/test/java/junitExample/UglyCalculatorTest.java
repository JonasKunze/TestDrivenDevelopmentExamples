package junitExample;

public class UglyCalculatorTest {

	public static void main(final String[] args) {
		Calculator calculator = new Calculator();

		final float maxRelativeError = (float)0.005;

		float radix = 2;
		float expectedValue = calculator.isqrtStdlib(radix);
		float actualValue = calculator.isqrt(radix);
		if (Math.abs(expectedValue - actualValue) > expectedValue * maxRelativeError) {
			System.err.println("isqrt("+radix+") weicht zu stark vom erwarteten Wert ("+expectedValue+") ab: " + actualValue);
		}

		radix = 10000;
		expectedValue = calculator.isqrtStdlib(radix);
		actualValue = calculator.isqrt(radix);
		if (Math.abs(expectedValue - actualValue) > expectedValue * maxRelativeError) {
			System.err.println("isqrt("+radix+") weicht zu stark vom erwarteten Wert ("+expectedValue+") ab: " + actualValue);
		}
		
		radix = (float)1E27;
		expectedValue = calculator.isqrtStdlib(radix);
		actualValue = calculator.isqrt(radix);
		if (Math.abs(expectedValue - actualValue) > expectedValue * maxRelativeError) {
			System.err.println("isqrt(1E64) weicht zu stark vom erwarteten Wert ("+expectedValue+") ab: " + actualValue);
		}

		radix = 0;
		float isqrt = calculator.isqrt(radix);
		if (!Float.isNaN(isqrt)) {
			System.err.println("isqrt(0) gab nicht NaN zurück!");
		}

		try {
			calculator.isqrt(-1);
			System.err.println("isqrt(-1) hat keine Exception geschmissen!");
		} catch (ArithmeticException e) {
		}
		
		System.err.println("Done");
	}
}
