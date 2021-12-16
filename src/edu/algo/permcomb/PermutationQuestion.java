package edu.algo.permcomb;

import java.util.List;

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

	PermutationQuestion INSTANCE = new PermutationSolution();

	void printPermutationOfStringByFixingPos(String input, String output);

	void printPermutationOfArrayByFixingPos(char[] input);

	void printPermuationOfArrayByFixingInput(char[] input);

	void printPermutationOfArrayUsingSwap1ByFixingPos(char[] input);

	void printPermutationOfArrayUsingSwap2ByFixingPos(char[] input);

	/**
	 * 
	 * print premutation of 'r' distinct items by arranging them on 'n' positions.
	 * where r<=n.
	 * 
	 * @param item          : item String represents r chars
	 * @param positionCount : represents 'n' positions
	 * 
	 */
	void printPermutationOfItemInArrayByFixingPos(int positionCount, String item);

	void printPermutationOfItemInArrayByFixingInput(int positionCount, String item);

	/**
	 * <pre>
	 * print premutation of 'r' items(may have duplicates) by arranging them on 'n' positions.
	 * where r<=n.
	 * 
	 *permutation_count_for_distinct_item = nPr
	 *
	 *permutation_count_for_item_having_duplicates = n!/(n-r)!*i1!*i2!  
	 *where i1=duplicate count of item1
	 *      i2=duplicate count of item2
	 *
	 *Example given: positionCount=9; item: "aaabbc",r=6
	 *permutation_count calculation:
	 *n=9,
	 *r=6,
	 *i1 count=3
	 *i2 count=2
	 *i3 count=1
	 *
	 *nPr = n!/(n-r)!*i1!*i2!*i3! =  9!/(9-6)!*3!*2!*1! = 5040
	 *
	 * Implementation Note: When input_count is smaller than position_count, some of the positions 
	 * will be empty in output array. And we want to fix the position and try input as options,means
	 * 'empty' will also be an type of input.
	 * </pre>
	 * 
	 * @param item          : item String represents r chars and can contain
	 *                      duplicate like aab
	 * @param positionCount : represents 'n' positions
	 * 
	 * 
	 */
	void printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(int positionCount, String item);

	void printPermutationOfItemInArrayByHandlingDuplicateAndFixingInput(int positionCount, String item);

	/**
	 * 
	 * print premutation of 'r' distinct items by arranging them in 2D-array. whre r
	 * <= positonCount(rows*cols)
	 * 
	 * @param item : item String represents r chars
	 * @param rows : row count
	 * @parm cols : column count
	 * 
	 */
	void printPermutationOfItemIn2DArrayByFixingPosition(int rows, int cols, String item);

	void printPermutationOfItemIn2DArrayByFixingInput(int rows, int cols, String item);
	
	/**
	 * 
	 * <pre>
	 * 
	 * print premutation of 'r' distinct items by arranging them in 2D-array. whre r
	 * <= positonCount(rows*cols)
	 * 
	 * Implementation Strategy : 2D-array positions need to be mapped to 1D-array positions
	 * 
	 * 2d_row_pos  : 1d_pos/row_count
	 * 2d_col_pos  : 1d_pos%row_count 
	 * 
	 * @param item : item String represents r chars
	 * @param rows : row count
	 * @parm cols : column count
	 * 
	 * </pre>
	 * */
	void printPermutationOfItemIn2DElongatedArrayByFixingInput(int rows, int cols, String item);

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

	List<String> getStairPathPermutation(int targetValue, int... allowedSteps);

	void printStairPathPermutation(int targetValue, int[] allowedSteps, String path);

}
