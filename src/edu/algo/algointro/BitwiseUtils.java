package edu.algo.algointro;

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

	/**
	 * x & (-x) :use to isolate the rightmost 1-bit, producing 0 if none (e.g.
	 * inupt:01011000 output:00001000)
	 * 
	 */
	public static boolean isEven(int x) {

		return (x & 1) == 0;

		// (x&-x) !==1;

	}

	public static void swap(int x, int y) {
		// TODO:
	}

	public static int min(int x, int y) {
		// TODO:

		return 0;

	}

	public static int max(int x, int y) {
		// TODO:

		return 0;
	}

	public static boolean hasOppositeSign(int x, int y) {
		// TODO:
		return false;
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
	 * x & (x - 1): use to turn off the rightmost 1-bit in a word, producing 0 if
	 * none (e.g., input: 01011000 output: 01010000).
	 * 
	 * This can be used to determine if an unsigned integer is a power of 2 or is 0:
	 * apply the formula followed by a 0-test on the result.
	 * 
	 * is_2_toThePowerOf_N<br>
	 * Let's say i is 2^n, then i has only HighestOneBit set. And (i-1) i.e. (2^n
	 * -1) has all bits set except of highestOneBit position.
	 * 
	 * 
	 */
	public static boolean is_2_toThePowerOf_N(int i) {
		if (i <= 0) {
			return false;
		}

		return (i & (i - 1)) == 0;

	}

	/**
	 * x & (x + 1) : use to turn off the trailing 1�s in a word, producing x if none
	 * (e.g., input: 10100111 output:10100000) This can be used to determine if an
	 * unsigned integer is of the form 2^n - 1, 0, or all 1�s: apply the formula
	 * followed by a 0-test on the result.
	 *
	 * 
	 * -1 will have all 1's
	 * 
	 */

	public static boolean is_2_toThePowerOf_N_WholeMinus_1(int i) {
		if (i <= 0) {
			return false;
		}
		return (i & (i + 1)) == 0;

	}

	/**
	 * 
	 * There is no leading zeros in negative number as it's sign bit is 1.
	 * 
	 * There is full size of leading zeros for x==0.
	 * 
	 * 
	 * 2^n can be written as 1<<n
	 * 
	 * This solution is binarySearch based. O(n) = base 2 log of 32 = 5
	 * 
	 *
	 */
	public static int numberOfLeadingZerosWithExplanation(int number) {

		if (number < 0)
			return 0;
		if (number == 0)
			return 32;
		int maxPossibleLeadingZeros = -1;

		// if number>=2^16; number may span 0 to 31 bits, but highest 1 bit will
		// definitely lie somewhere between 16 to 31 bits.
		if (number >= (1 << 16)) {
			final int highest1bitRangeStart = 16;
			final int highest1bitRangeEnd = 31;

			maxPossibleLeadingZeros = highest1bitRangeEnd - highest1bitRangeStart;
			number = number >>> 16;
		}

		// number may span 0 to 16 bits, but highest 1 bit will definitely lie somewhere
		// between 8 to 15 bits.
		if (number >= (1 << 8)) {
			final int highest1bitRangeStart = 8;
			final int highest1bitRangeEnd = 15;

			// if the number is fresh and entered first in this if condition
			if (maxPossibleLeadingZeros == -1) {
				maxPossibleLeadingZeros = 16 + highest1bitRangeEnd - highest1bitRangeStart;
			} else {
				// if this number is not fresh and got treated in upper if.
				maxPossibleLeadingZeros = maxPossibleLeadingZeros - (highest1bitRangeStart - 0);
			}
			number = number >>> 8;
		}

		// number may span 0 to 7 bits, but highest 1 bit will definitely lie somewhere
		// between 4 to 7 bits.
		if (number >= (1 << 4)) {
			final int highest1bitRangeStart = 4;
			final int highest1bitRangeEnd = 7;
			// if the number is fresh and entered first in this if condition
			if (maxPossibleLeadingZeros == -1) {
				maxPossibleLeadingZeros = 16 + 8 + highest1bitRangeEnd - highest1bitRangeStart;
			} else {
				// if this number is not fresh and got treated in upper if.
				maxPossibleLeadingZeros = maxPossibleLeadingZeros - (highest1bitRangeStart - 0);

			}
			number = number >>> 4;
		}

		// number may span 0 to 3 bits, but highest 1 bit will definitely lie somewhere
		// between 2 to 3 bits.
		if (number >= (1 << 2)) {

			final int highest1bitRangeStart = 2;
			final int highest1bitRangeEnd = 3;
			// if the number is fresh and entered first in this if condition
			if (maxPossibleLeadingZeros == -1) {
				maxPossibleLeadingZeros = 24 + 4 + highest1bitRangeEnd - highest1bitRangeStart;
			} else {
				// if this number is not fresh and got treated in upper if.
				maxPossibleLeadingZeros = maxPossibleLeadingZeros - (highest1bitRangeStart - 0);
			}
			number = number >>> 2;
		}

		// number may span 0 to 1 bits

		// if number=2^1 : highest 1 bit is on 1th position
		if (number == 2) {
			if (maxPossibleLeadingZeros == -1) {
				maxPossibleLeadingZeros = 28 + 2;
			} else {
				// if this number is not fresh and got treated in upper if.
				maxPossibleLeadingZeros = maxPossibleLeadingZeros - 1;
			}

		}
		// if number=2^0 : highest 1 bit is on 0th position
		if (number == 1) {
			if (maxPossibleLeadingZeros == -1) {
				maxPossibleLeadingZeros = 30 + 1;
			}
		}

		return maxPossibleLeadingZeros;
	}

	/**
	 * 
	 * There is no leading zeros in negative number as it's sign bit is 1.
	 * 
	 * There is full size of leading zeros for x==0.
	 * 
	 * 
	 * 2^n can be written as 1<<n
	 * 
	 * This solution is binarySearch based. O(n) = base 2 log of 32 = 5
	 * 
	 *
	 */
	public static int numberOfLeadingZeros(int number) {

		if (number <= 0)
			return number == 0 ? 32 : 0;
		int leadingZeros = 31;

		if (number >= (1 << 16)) {
			leadingZeros -= 16;
			number >>>= 16;
		}

		if (number >= (1 << 8)) {
			leadingZeros -= 8;
			number >>>= 8;
		}

		if (number >= (1 << 4)) {
			leadingZeros -= 4;
			number >>>= 4;
		}

		if (number >= (1 << 2)) {
			leadingZeros -= 2;
			number >>>= 2;
		}
		// here if number is fresh then value will be either 2 or 1
		return leadingZeros - (number >>> 1);

	}

	/**
	 * Aim for formula : to get a number with leadingZeros by replacing trailing
	 * zeros with 1s.
	 *
	 * formula : ~n&(n-1)
	 *
	 * e.g. 01010100011000 ---> 00000000000111 00000000000000 ---->11111111111111
	 * 1111111111111 ----->00000000000000
	 * 
	 * 
	 * 
	 * 
	 */
	public static int numberOfTrailingZeros(int n) {
		int formula1 = ~n & (n - 1);
		// final int formula2 = (n & -n) -1;
		// final int formula3 = ~(n|-n);

		/**
		 * 
		 * if(formula1<=0) return formula1 & 32; this can be replaced with
		 * if(formula1==0) { return 0;} and if(formula1==-1) {return 32;}
		 * 
		 */

		if (formula1 <= 0)
			return formula1 & 32;

		return 32 - numberOfLeadingZeros(formula1);

	}

	public static int numberOfTrailingZerosJava(int i) {
		// HD, Count trailing 0's
		i = ~i & (i - 1);
		if (i <= 0)
			return i & 32;
		int n = 1;
		if (i > 1 << 16) {
			n += 16;
			i >>>= 16;
		}
		if (i > 1 << 8) {
			n += 8;
			i >>>= 8;
		}
		if (i > 1 << 4) {
			n += 4;
			i >>>= 4;
		}
		if (i > 1 << 2) {
			n += 2;
			i >>>= 2;
		}
		return n + (i >>> 1);
	}

	/**
	 * 
	 * x & (-x) :use to isolate the rightmost 1-bit, producing 0 if none (e.g.
	 * inupt:01011000 output:00001000)
	 * 
	 */
	public static int lowestOneBit(int num) {
		return num & -num;
	}

	public static int highestOneBit(int num) {
		return 1 << (31 - numberOfLeadingZeros(num));
	}

	/**
	 * Integer.MIN_VALUE = Integer.MAX_VALUE + 1;
	 * 
	 * Integer.MAX_VALUE = 0111111..30 times
	 * 
	 * Integer.MIN_VALUE = 0111111..30 times + 1 = 1000000..30 times
	 * 
	 * 
	 * */
	public static int highestOneBitJava(int i) {
		return i & (Integer.MIN_VALUE >>> numberOfLeadingZeros(i));
	}
	
	
	public static int bitCount(int x) {
		//TODO:
		
		return 0;
	}
	
	public static int reverse(int x) {
		//TODO:
		return 0;
	}
	
	public static int reverseBytes(int x) {
		//TODO:
		return 0;
	}
	
	public boolean isNum_2PowerOfJ_Minus_2PowerOfK(int x) {
		//TODO:
		return false;
	}
	
	 /**
     * Returns the signum function of the specified {@code int} value.  (The
     * return value is -1 if the specified value is negative; 0 if the
     * specified value is zero; and 1 if the specified value is positive.)
     *
     * @param i the value whose signum is to be computed
     * @return the signum function of the specified {@code int} value.
     * @since 1.5
     */
    public static int signum(int i) {
        // HD, Section 2-7
        return (i >> 31) | (-i >>> 31);
    }

}
