package edu.algods.algointro;

/**
 * Reference A: Hackers Delight book
 * 
 * 
 * Reference2 : Bit Twiddling Hacks:
 * https://graphics.stanford.edu/~seander/bithacks.html#OperationCounting
 * 
 * 
 * 
 * 
 */
public class BitwiseUtils {

	public static void main(String[] args) {

	}

	public static int multiplyBy2(int i) {
		// left shit, operator multiply the each bit-position by 2.
		return i << 1;
	}

	public static int divideBy2(int i) {
		// right shit, operator divide the each bit-position by 2, signed-right shift
		// operator, fills the vacated bits by sign-bit, so preserves the sign.
		return i >> 1;
	}

	public static int calculatePowerOf2(int power) {
		return 1 << power;
	}

	/**
	 * 
	 * 
	 * Let's say i is 2^n, then i has only HighestOneBit set. And (i-1) i.e. (2^n
	 * -1) has all bits set except of highestOneBit position.
	 */
	public static boolean is_2_toThePowerOf_N(int i) {
		return (i & (i - 1)) == 0;

	}

	/**
	 * Let's say i is (2^n -1), then i has all bits set. And (i+1) i.e. 2^n has only
	 * HighestOneBit set.
	 */
	public static boolean is_2_toThePowerOf_N_WholeMinus_1(int i) {
		return (i & (i + 1)) == 0;

	}

	/**
	 * 
	 * 
	 * */
//	public static int numberOfLeadingZeros(int number) {
//		
//	}

}
