package edu.algo.numbertheory;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationQuestions {

	public static void main(String[] args) {
		question1A("12345");
		question1B("12345");
	}

	/**
	 * <pre>
	 * Question:
	 * Generate permutations with only adjacent swaps allowed.
	 *  allowed rules to generate next permutation:
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
	private static void question1A(String input) {

		List<String> pairs = IntStream.range(1, input.length())
				.mapToObj(i -> "" + input.charAt(i - 1) + input.charAt(i)).collect(Collectors.toList());

		traversePowerSet(input, pairs, new ArrayList<>());

	}

	/**
	 * <pre>
	 * Question:
	 * Generate permutations with only adjacent swaps allowed.
	 *  allowed rules to generate next permutation:
	 *  1. multiple non-duplicate adjacent swaps allowed
	 *  
	 *  
	 *Solution: 
	 *Include Step: 
	 * We will pick two non-overlapping adjacent elements at a time as swap pair.
	 * Exclude Step: exclude single element. 
	 * In 12345, we can create swap pair 23 by excluding 1 So we should not exclude in pair.
	 *
	 *
	 *&#64;see nonRepetableAdjSwapPerm.pdf
	 * </pre>
	 */
	private static void question1B(String input) {

		nonRepetableAdjSwapPerm(input.toCharArray(), 0);
	}

	private static void nonRepetableAdjSwapPerm(char[] input, int index) {

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
		 * */
		swap(input, index, index + 1);
		nonRepetableAdjSwapPerm(input, index + 2);
		// backtrack because we need the same input for two branches of the
		// recursion-tree.
		swap(input, index, index + 1);
		
		
        /**
         * exclude step
         * 
         * */
		
		nonRepetableAdjSwapPerm(input, index + 1);
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
