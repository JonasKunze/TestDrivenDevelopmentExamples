package junitExample;

public class Calculator {

	float add(final float a, final float b) {
		return a + b;
	}

	float subtract(final float a, final float b) {
		return a - b;
	}

	/**
	 * Yet another reason why comments are to be avoided:
	 * 
	 * multiplies a with b and returns the result
	 * 
	 * @param a
	 *            multiplicator
	 * 
	 * @param b
	 *            multiplicator
	 * @return returns the product of a and b
	 */
	float multiply(final float a, final float b) {
		return a * b;
	}

	float divide(final float a, final float b) {
		return a / b;
	}

	float isqrtStdlib(final float radix) {
		return 1 / ((float) Math.sqrt(radix));
	}

	/**
	 * Fast inverse square root approximation. Code including the comments
	 * are based on the example from 
	 * https://en.wikipedia.org/wiki/Fast_inverse_square_root
	 */
	float isqrt(final float radix) {
		if (radix < 0)
			throw new ArithmeticException("sqrt of negative number");
		if (radix == 0) {
			return 0 / 0.F;
		}

		int i;
		float x2, y;
		float threehalfs = 1.5F;

		x2 = radix * 0.5F;
		y = radix;
		i = Float.floatToIntBits(y); // evil floating point bit level hacking
		i = 0x5f3759df - (i >> 1); // what the fuck?
		y = Float.intBitsToFloat(i);
		y = y * (threehalfs - (x2 * y * y)); // 1st iteration
		// y = y * ( threehalfs - ( x2 * y * y ) ); // 2nd iteration, this can
		// be removed

		return y;
	}

	/**
	 * Run this and start e.g. VisualVM for profiling
	 */
	public static void main(final String[] args) {
		Calculator calculator = new Calculator();
		for (float radix = 0.001f; radix < 1E9; radix += 0.1) {
			calculator.isqrtStdlib(radix);
			calculator.isqrt(radix);
			calculator.add(radix, radix);
			calculator.subtract(radix, radix);
			calculator.multiply(radix, radix);
			calculator.divide(radix, radix);
		}
	}
}
