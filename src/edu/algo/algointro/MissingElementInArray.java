package edu.algo.algointro;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/bloom-filter-in-java-with-examples/
 * 
 * https://llimllib.github.io/bloomfilter-tutorial/
 * 
 * https://www.baeldung.com/guava-bloom-filter
 * 
 * https://en.wikipedia.org/wiki/Bloom_filter
 * 
 * 
 * 
 * A Bloom filter is a memory-efficient, probabilistic data structure that we
 * can use to answer the question of whether or not a given element is in a set.
 * 
 * There are no false negatives with a Bloom filter, so when it returns false,
 * we can be 100% certain that the element is not in the set.
 * 
 * However, a Bloom filter can return false positives, so when it returns true,
 * there is a high probability that the element is in the set, but we can not be
 * 100% sure.
 * 
 */
public class MissingElementInArray {

	public static void main(String[] args) {

		int input0[] = { 9, 10, 11, 13, 14, 15, 16 };
		int input1[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int input2[] = { 0, 1, 2, 3, 4, 6, 7, 8, 9 };

		System.out.println(findSingleMissingNumberInSortedRange(input0));
		System.out.println(findSingleMissingNumberInSortedRange(input1));
		System.out.println(findSingleMissingNumberInSortedRange(input2));

		System.out.println("-----------missing in unsorted array-------");

		int input3[] = { 16, 11, 10, 15, 14, 13, 9 };
		System.out.println(findSingleMissingNumberInRange(input3, 9, 16));

		System.out.println("-----------missing element using XOR-------");
		findSingleMissingNumberInRangeUsingXOR();
		findSingleMissingNumberInRangeUsingXOROptimized();
	}

	/**
	 * 
	 * Time Complexity O(logn) : binary search based
	 * 
	 * Here we need a sorted array as we are doing binary search.
	 */
	private static int findSingleMissingNumberInSortedRange(int[] input) {

		final int OFFSET = input[0];

		int start = 0;
		int end = input.length - 1;

		int mid = 0;

		while (end - start > 1) {

			mid = (start + end) / 2;

			int expectedValueAtMid = mid + OFFSET;
			int actualValueAtMid = input[mid];

			// search part is in left half
			if (actualValueAtMid > expectedValueAtMid) {
				end = mid;

			} else {
				// search part is in right half
				start = mid;
			}

		}

		// missing element is input[start]+ 1; or input[end]-1;

		if (input[start + 1] == input[start] + 1) {
			return Integer.MIN_VALUE;
		}
		return input[start] + 1;

	}

	/**
	 * 
	 * Time Complexity : O(n)
	 */
	private static int findSingleMissingNumberInRange(int[] input, int first, int last) {
		int sumFrom1toLast = last * (last + 1) / 2;
		int sumFrom1toFirstMinus1 = first * (first - 1) / 2;

		int expectedSum = sumFrom1toLast - sumFrom1toFirstMinus1;

		int actualSum = Arrays.stream(input).sum();

		return expectedSum - actualSum == 0 ? Integer.MIN_VALUE : expectedSum - actualSum;

	}

	/**
	 * For simplicity of implementation we have used range from 1 to n.
	 * 
	 * <pre>
	 * expected_xor : XOR[1,n] =  1^2^3^4^...^n;
	 * actual_xor   :XOR[1,n]~2 = 1^X3^4^....^n;
	 * ------------------------------------------
	 * Since XOR of A with itself is 0, so if we do XOR of expected_xor and actual_xor we will get X as 2.
	 * </pre>
	 * 
	 * TimeComplexity : O(2n)
	 * 
	 * @see : swipe numbers without using extra variable.
	 */
	private static void findSingleMissingNumberInRangeUsingXOR() {
		int[] input = { 1, 3, 4, 5, 6, 7, 8, 9 }; // Here missing is 2 i.e X=2

		int expected_xor = 1;
		for (int i = 2; i <= 9; i++) {
			expected_xor = expected_xor ^ i;
		}

		int actual_xor = input[0];
		for (int i = 1; i < input.length; i++) {
			actual_xor = actual_xor ^ input[i];
		}
		System.out.println("missing element in input:" + Arrays.toString(input) + " " + (expected_xor ^ actual_xor));

	}

	/**
	 * Thanks to {@link https://a3nm.net/blog/xor.html} for this implementation
	 * 
	 * <pre>
	 *   
	 *       formula 1: to calculate XOR[1..n]    
	 *       1 ^ ... ^ n = (n >> 1) & 1 ^ (n&1 ? 1 : n)
	 *       
	 *       formula 2: to calculate XOR[1..n]  
	 *       1 ^ ... ^ n = (n % 4 == 0) ? n : (n % 4 == 1) ? 1 : (n % 4 == 2) ? n + 1 : 0;
	 *       We can prove by induction that  1 ^ ... ^ n  is either n or 1 or n + 1 or 0 depending on n % 4
	 * </pre>
	 */
	private static void findSingleMissingNumberInRangeUsingXOROptimized() {
		int[] input = { 1, 3, 4, 5, 6, 7, 8, 9 }; // Here missing is 2 i.e X=2

		int  n=9;
		int  expected_xor_1_to_n = (n >> 1) & 1 ^ (((n & 1) > 0) ? 1 : n);

		int actual_xor = input[0];
		for (int i = 1; i < input.length; i++) {
			actual_xor = actual_xor ^ input[i];
		}
		System.out.println("missing element in input:" + Arrays.toString(input) + " " + (expected_xor_1_to_n ^ actual_xor));

	}

}
