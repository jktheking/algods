package edu.algo.permcomb;

/**
 * <pre>
 * https://introcs.cs.princeton.edu/java/23recursion/
 * 
 *Efficient Algorithm:
 * BR HEAPS ALGO : 
 * https://en.wikipedia.org/wiki/Heap%27s_algorithm
 * https://www.baeldung.com/java-array-permutations
 * 
 * SJT :
 * </pre>
 */
public interface PermutationQuestion {
	
	PermutationQuestion INSTANCE  = new PermutationSolution();
	
	
	void printPermutationsOfString(String input, String output);

    void printPermutationsOfArray1(char[] input, char[] output, int outPos);

    void printPermutationsOfArray2(char[] input, int position);

    void printPermutationsOfArray3(char[] input, int n);
	
    void printPermutationUsingSJT1(int[] permutation);
    
    void printPermutationUsingSJT2(int[] permutation);
    
    void printPerumutationUsingBRHeap1(int[] permutation);
    
    void printPerumutationUsingBRHeap2(int[] permutation);
	

	/**
	 * Generate permutations with only non repeatable adjacent swaps allowed.
	 * 
	 * Given a string on length N. You can swap only the adjacent elements and each
	 * element can be swapped at most once. Given these rules, find the no of
	 * permutations of the string that can be generated after performing the swaps
	 * as mentioned. Multiple non-duplicate adjacent swaps allowed.
	 *  
	 * Allowed strategy is powerSet.
	 * 
	 * 
	 */
	void permutationsWithAdjacentNonRepeatableSwaps1(String input);

	/**
	 * Generate permutations with only non repeatable adjacent swaps allowed using
	 * pascal identity.
	 */
	void permutationsWithAdjacentNonRepeatableSwaps2(String input);

	/**
	 * Generate permutations with only non repeatable adjacent swaps allowed using
	 * pascal identity expansion.
	 */
	void permutationsWithAdjacentNonRepeatableSwaps3(String input);

	/**
	 * Question: Get next greater number using the same digits
	 * 
	 * or
	 * 
	 * next lexicographic permutation
	 */
	void printNextLexicographicPermutation(String input);

	/**
	 * Generate kth lexicographic permutation.
	 */
	void printKthLexicographicPermutation(String input, int k);

	/**
	 * Question: Print all permutations in lexicographic order using brute force.
	 */
	void printAllPermutationsInLexicographicOrder1(String inputString);

	/**
	 * Question: Print all permutations in lexicographic order.
	 */
	void printAllPermutationsInLexicographicOrder2(String inputString);

	/**
	 * Question: Print all permutations in lexicographic order.
	 */
	void printAllPermutationsInLexicographicOrder3(String inputString);

}
