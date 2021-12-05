package edu.algo.algebra;

import edu.algo.algointro.BitwiseUtils;

/**
 * link : https://cp-algorithms.com/algebra/binary-exp.html
 * 
 * <pre>
 * 
 * a^n 
 * 
 * For even n : a^12 = a^1100 = a^8 * a^4 * a^0 * a^0   = a^8 * a^4
 * For odd  n : a^13 = a^1101  = a^8 * a^4 * a^0 * a^1  = a^8 * a^4 * a^1
 * 
 * Since the number n has exactly ⌊log2n⌋+1 digits in base 2. So we only need to 
 * perform O(logn) multiplications, if we know the powers a^1, a^2, a^4, a^8, …, a⌊logn⌋
 * 
 * Now, we only need to know a fast way to compute the power in sequence. This is easy to do
 * as element in the sequence is just the square of the previous element. 
 * 
 * 3^1 = 3
 * 3^2 = (3^1)^2 = 9
 * 3^4 = (3^2)^2 = 81
 * 3^8 = (3^4)^2 = 6561
 * 
 * Recursive expression:
 * 
 *   a^n  = 1 ; if n == 0
 *   a^n  = (a^n/2)^2;  if n is even and greater than zero
 *   a^n  = (a^(n-1)/2)^2 * a ;   if n is odd and greater than zero
 *   
 *   Note : Integer division for odd n/2 is same as (n-1)/2
 * 
 * </pre>
 * 
 * 
 */
public class BinaryExponentiation {

	public static void main(String[] args) {

		System.out.println(binaryPowerIterative(3, 13));
	}

	/**
	 * <pre>
	 * Hypothesis :  binaryPowerRecursive(a,p)   : a^p
	 * Substitution: binaryPowerRecursive(a,p/2) : a^p/2
	 * 
	 * Induction: (a^p/2)^2 = a^p for even power
	 *          : (a^p/2)^2 * a = a^p for odd power
	 *          
	 * Base Case : a^p == 1 ; if p==0
	 * </pre>
	 * 
	 * <pre>
	 * Time Complexity : logn times binaryPowerRecursive method is invoked and logn
	 * times 'result' multiplication
	 *                 : logn + logn 
	 *                 :O(logn)
	 * </pre>
	 */
	public static int binaryPowerRecursive(int a, int p) {

		if (p == 0) {
			return 1;
		}
		int result = binaryPowerRecursive(a, p / 2);

		if ((p & 1) == 0) {
			// square the result
			result = result * result;
		} else {
			result = result * result * a;
		}

		return result;

	}

	/**
	 * <pre>
	 *  
	 * a^13 = a^1101  = a^8 * a^4 * a^0 * a^1   = a^8 * a^4 * a^1;
	 * OBSERVATION_1 : We are multiplying only those power terms where binary bit is 1.
	 * 
	 * OBSERVATION_2: To calculate the power term a^4, we need to calculate the power term
	 * a^2 though it is not used in calculation of a^13. Means we must calculate the power
	 * term for each bit position irrespective of whether bit is 0 or 1.
	 * 
	 * Q: how to count set bit positions ?
	 * We can right-shift the bits of a given number one by one and checks whether the number
	 * becomes odd or not.
	 * 
	 * </pre>
	 * 
	 * Time Complexity: we have to compute logn powers of 'a' and then have to do at
	 * most logn multiplications to get the final answer from them.
	 * 
	 * : logn + logn = O(logn)
	 */
	public static int binaryPowerIterative(int a, int p) {

		int res = 1;
		
		while (p > 0) {

			// need to multiply only those power terms where binary bit is 1; as per
			// OBSERVATION_1
			if (!BitwiseUtils.isEven(p)) {
				res = res * a;
			}
			// for each bit shift we need to double the previous 'a'; as per OBSERVATION_2.
			a = a * a;

			// bit iteration using right-shift
			p = p >> 1;
		}

		return res;
	}

}
