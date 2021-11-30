package edu.algo.numbertheory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationQuestions {

	public static void main(String[] args) {
		// question1A("12345");
		// question1B("12345");
		// question1C("12345");

		// System.out.println(question2("12345"));
		// System.out.println(question2("1254433210"));
		// System.out.println(question2("1254432210"));

		//System.out.println(question3("1234", 6));
		//question4A("1234");
//		question4A("abc");
		question4B("1234");
	}

	/**
	 * <pre>
	 *  Question:
	 * Generate permutations with only non repeatable adjacent swaps allowed.
	 * 
	 * Given a string on length N. You can swap only the adjacent elements and each
	 * element can be swapped at most once. Given these rules, find the no of
	 * permutations of the string that can be generated after performing the swaps
	 * as mentioned.
	 * 
	 *  Allowed rules to generate next permutation:
	 *  1. multiple non-duplicate adjacent swaps allowed
	 *  
	 *Solution:
	 * Step1 : generate all the pairs
	 * Step2: generate all the 2^n sets of pair i.e powerset.  
	 *    A:  If generated set pairs are non-overlapping then print the permutation.
	 *    B: If generated set contains any overlapping pair then discard the permutation.
	 *       Because it does not represent adjacent swaps.
	 * 
	 * </pre>
	 */
	public static void question1A(String input) {

		List<String> pairs = IntStream.range(1, input.length())
				.mapToObj(i -> "" + input.charAt(i - 1) + input.charAt(i)).collect(Collectors.toList());
		System.out.println("pairs:" + pairs);
		traversePowerSet(input, pairs, new ArrayList<>());

	}

	/**
	 * <pre>
	 *   
	 *Solution: 
	 *Include Step: 
	 * We will pick two non-overlapping adjacent elements at a time as swap pair.
	 *
	 * Exclude Step: exclude single element. 
	 * In 12345, we can create swap pair 23 by excluding 1 So we should not exclude in pair.
	 *
	 * </pre>
	 * 
	 * @see nonRepetableAdjSwapPerm.pdf
	 */
	public static void question1B(String input) {

		nonDupAdjSwapsPermUsingIncExc(input.toCharArray(), 0);
	}

	private static void nonDupAdjSwapsPermUsingIncExc(char[] input, int index) {

		/**
		 * why do we need the condition "(index + 1) >= input.length" ? If during the
		 * include-exclude counting, we left with just single element, then we need to
		 * terminate the include-exclude process because single element cannot form a
		 * valid swap pair.
		 * 
		 * 
		 **/

		if (index >= input.length || (index + 1) >= input.length) {
			// input is exhausted, print the output
			System.out.println(String.valueOf(input));
			return;
		}

		/**
		 * include step
		 * 
		 */
		swap(input, index, index + 1);
		nonDupAdjSwapsPermUsingIncExc(input, index + 2);
		// backtrack because we need the same input for two branches of the
		// recursion-tree.
		swap(input, index, index + 1);

		/**
		 * exclude step
		 * 
		 */

		nonDupAdjSwapsPermUsingIncExc(input, index + 1);
	}

	/**
	 * Here instead of Pascal Identity(i.e. Include-Exclude) will use Pascal
	 * Identity Expansion of Exclude terms into Include Terms.
	 * 
	 * This is more efficient than include exclude based solution. i.e. question1B
	 */
	public static void question1C(String input) {
		nonDupAdjSwapsPermUsingExcludeExpansion(input.toCharArray(), 0);
	}

	private static void nonDupAdjSwapsPermUsingExcludeExpansion(char[] input, int index) {
		System.out.println(String.valueOf(input));
		for (int i = index; i < input.length && (i + 1) < input.length; i++) {
			swap(input, i, i + 1);
			nonDupAdjSwapsPermUsingExcludeExpansion(input, i + 2);
			swap(input, i, i + 1);
		}

	}

	/**
	 * Question: Get next greater number using the same digits or next lexicographic
	 * permutation
	 */
	public static String question2(String inputStr) {
		char[] input = inputStr.toCharArray();
		getNextLexicographicPermutation(input);
		return String.valueOf(input);

	}

	/**
	 * Question: Generate Kth lexicographic order permutation.
	 * 
	 */
	public static String question3(String inputStr, int k) {

		List<Character> charList = inputStr.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());

		return String.valueOf(getKthLexicographicPermutation(charList, k));

	}

	/**
	 * <pre>
	 * Time Complexity : Time to calculate Factorial + Time to run for loop on output array * Time for input.remove()
	 *                 :    O(n)                     +            O(n) * O(n)                     
	 *                 :    O(n) + O(n^2)
	 *                 :    O(n^2)
	 * 
	 * </pre>
	 */
	private static char[] getKthLexicographicPermutation(List<Character> input, int k) {
		char[] output = new char[input.size()];
		
		int blockSize = factorial(input.size() - 1);

		k = k - 1;
		
		for (int i = 0; i < output.length; i++) {
			int blockNumber = k / blockSize;
			
			output[i] = input.get(blockNumber);
			input.remove(blockNumber);
			if (input.size() == 0)break;
			
			k = k % blockSize;
			blockSize = blockSize / input.size();
		}
		return output;
	}

	private static int factorial(int num) {
		int factorial = 1;
		for (int i = num; i > 1; i--) {
			factorial *= i;
		}
		return factorial;
	}

	/**
	 * <pre>
	 * Question: print all permutations in lexicographic order.
	 * 
	 * Time Complexity :  n! * (time to get each next_lexicographic_permutation)
	 *                 :  n! * n
	 *                 :  O(n*n!)
	 * </pre>
	 *
	 * @param inputStr : It should be in sorted order like 1234/abcd
	 * 
	 */
	public static void question4A(String inputStr) {

		char[] input = inputStr.toCharArray();

		final String LARGEST_PERM = String.valueOf(reverseCharArray(inputStr.toCharArray(), 0, inputStr.length() - 1));

		String perm = inputStr;
		System.out.println(perm);
		while (!perm.equals(LARGEST_PERM)) {
			getNextLexicographicPermutation(input);
			perm = String.valueOf(input);
			System.out.println(perm);
		}

	}
	
	/**
	 * Question: print all permutations in lexicographic order.
	 * */
	public static void question4B(String inputStr) {

		List<Character> charList = inputStr.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
		int fact = factorial(inputStr.length());
		
		for(int i=1; i<=fact; i++) {
			System.out.println(String.valueOf(getKthLexicographicPermutation(new ArrayList<>(charList), i)));	
			
		}
			
	}

	/**
	 * next_lexicographic_permutation
	 * 
	 * <pre>
	 * Step1 : Identify Pivot Traverse the array from right side and stop at the
	 * first element which is not in ascending order. Example : 2 is pivot in
	 * 0125330 
	 * Question: Why we are traversing in ascending order from right side ?
	 * : Because of PROPERTY_1. 
	 * 
	 * Step2: Find Successor of Pivot Find the next greater
	 * digit than the Pivot among digits present in right-side of the pivot.
	 * Note : Since right side of the pivot contains all the digits in sorted order, so we
	 * can apply binary serach.
	 * 
	 * Step 3: Swap the Pivot with Successor. Because we want just next greater
	 * element which is only possiple by replacing pivot with successor.
	 * 
	 * Step4: Now reverse sort(descending order from right side) all the elements
	 * lying in right-side of pivot position. 
	 * Note : Since right side of the pivot position is already in sorted order, 
	 * so just need to reverse the element to get them in descending order.
	 * Question: Why do we sort elements lying right-side of pivot position in descending order ? : Because of PROPERTY_1.
	 * </pre>
	 * 
	 * <pre>
	 * TimeComplexity : STEP1 +  STEP2   + STEP3 + STEP4
	 *                : O(n)  +  O(logn) + O(1)  + O(n) 
	 *                : O(n)
	 * </pre>
	 */
	private static void getNextLexicographicPermutation(char[] input) {

		// STEP1: find pivot
		int pivotIndex = Integer.MIN_VALUE;
		for (int i = input.length - 1; i > 0; i--) {
			if (input[i - 1] < input[i]) {
				pivotIndex = i - 1;
				break;
			}
		}

		// pivot not found, means input itself is the largest element using given
		// digits.
		if (pivotIndex == Integer.MIN_VALUE) {
			return;
		}

		// STEP2: find successor using bisect right search
		char targetValue = input[pivotIndex];
		// we are increasing the targetValue to 1, because we want to find the insertion
		// position of successor value
		targetValue += 1;

		int insertionIndexOfTargetValue = bisectRightInDescArray(input, targetValue, pivotIndex + 1, input.length);
		int pivotSuccessorIndex = insertionIndexOfTargetValue - 1;

		// STEP3 :swap pivot with its successor lying in right side
		swap(input, pivotIndex, pivotSuccessorIndex);

		// STEP4: reverse the array element between (pivotIndex, endIndex].
		reverseCharArray(input, pivotIndex + 1, input.length - 1);
	}

	/**
	 * gives rightmost insertion index of targetValue.
	 * 
	 */
	private static int bisectRightInDescArray(char[] input, char targetValue, int left, int right) {

		while (left < right) {

			int mid = (left + right) / 2;

			if (input[mid] < targetValue) {

				right = mid;

			} else if (input[mid] == targetValue) {

				left = mid + 1;
			} else {
				left = mid + 1;
			}
		}

		return left;

	}

	public static char[] reverseCharArray(char[] input, int startIndex, int endIndex) {

		int diff = endIndex - startIndex;
		// diff-1, avoid self-swap if any.
		for (int i = 0; i <= (diff - 1) >> 1; i++) {

			char temp = input[startIndex + i];
			input[startIndex + i] = input[endIndex - i];
			input[endIndex - i] = temp;
		}

		return input;
	}

	private static void traversePowerSet(String originalStr, List<String> inputPairs, List<String> outputPairs) {

		if (inputPairs.isEmpty()) {
			char[] input = originalStr.toCharArray();
			for (String pair : outputPairs) {
				int i = originalStr.indexOf(pair);
				swap(input, i, i + 1);

			}
			System.out.println(
					"input:" + originalStr + " swap pairs:" + outputPairs + " output:" + String.valueOf(input));

			return;

		}

		// by including the pair
		List<String> clonedInputPairs = new ArrayList<>(inputPairs);
		String pair = clonedInputPairs.remove(0);
		if (!containsOveralappingPair(outputPairs, pair)) {
			List<String> clonedOutputPairs = new ArrayList<>(outputPairs);
			clonedOutputPairs.add(pair);
			traversePowerSet(originalStr, clonedInputPairs, clonedOutputPairs);
		}

		// by excluding
		traversePowerSet(originalStr, clonedInputPairs, outputPairs);

	}

	private static void swap(char[] input, int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	private static boolean containsOveralappingPair(List<String> outputPairs, String pair) {
		for (String e : outputPairs) {
			if (e.charAt(1) == pair.charAt(0))
				return true;

		}
		return false;
	}

}
