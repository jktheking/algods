package edu.algo.arraystring;

import java.util.List;

public interface ArrayAndStringProblems {

	ArrayAndStringProblems INSTANCE = new ArrayAndStringSolutions();

	boolean isWordVerifiableTypedUsingFaultyKeyboard(String typedWord, String actualWord);

	/**
	 * <pre>
	 * 1. Assume you have an array of length 'n' initialized with all 0's and are
	 * given 'q' queries to update.
	 *  
	 * 2. Each query is represented as a triplet:[startIndex, endIndex, inc] 
	 * which increments each element of subarray A[startIndex ... endIndex]
	 *  (startIndex and endIndex inclusive) with inc.
	 * 
	 * 3. Return the modified array after all 'q' queries were executed.
	 * 
	 * </pre>
	 */
	void rangeUpdateUsingPrefixSumInOrderN(int[] input, int[][] updateQueries);

	/**
	 * Example :
	 * 
	 * even-number: mid of 6 is 3
	 * 
	 * odd number : mid of 7 is 4
	 * 
	 */
	int getMidValueUsingSingleIntegerDivision(int number);

	/**
	 * 1. Given n non-negative integers a1, a2, ..., an.
	 * 
	 * 2. Each represents a point at coordinate (i, ai).
	 * 
	 * 3. 'n' vertical lines are drawn such that the two endpoints of the line i is
	 * at (i, ai) and (i, 0).
	 * 
	 * 4. Find two lines, which, together with the x-axis forms a container, such
	 * that the container contains the most water.
	 * 
	 * 5. (Not volume because we are working with 2D so amount of water is basically
	 * Area). Note : that you may not slant the container.
	 * 
	 * 6. Vertical lines are placed on horizontal axis one unit apart.
	 */
	int calculateMaxAreaBetweenLines(int[] lineHeights);

	/**
	 * 1. Given an integer array 'nums' sorted in non-decreasing order.
	 * 
	 * 2. Return an array of the squares of each number sorted in non-decreasing
	 * order.
	 */
	void squareOfIntegerInSortedArray(int[] input);

	/**
	 * 1. Give an array of size 'n'.
	 * 
	 * 2. Find Majority element and print it(if exist), otherwise print "No Majority
	 * Element exist".
	 * 
	 * 3. Majority element-> if frequency of an element is more than n/2, then that
	 * element is majority element.
	 * 
	 * 3. Note : solve the problem in linear time and in O(1) space.
	 * 
	 */
	void majorityElementUsingBoyerMooreVoting(int[] input);

	/**
	 * 1. Given an integer array of size 'n'.
	 * 
	 * 2. Find all elements that appear more than n / 3 times and return it in an
	 * arraylist.
	 * 
	 * 3. Note : solve the problem in linear time and in O(1) space.
	 * 
	 */
	void nBy3majorityElementUsingBoyerMooreVoting(int[] input);

	/**
	 * To use BoyerMooreVoting algorithm, we need to pick variable pairs for
	 * majority element and its vote. If the size of k in "n/k"-majority is large
	 * then we need to assign large number of variables to hold majority_element and
	 * its vote, so space complexity is dependent on k. Thus. space complexity for
	 * "n/k" majority element = O(2k = O(k)
	 * 
	 * So, better approach would be to use hash-map to keep the frequencies where k
	 * is large.
	 * 
	 * 
	 */
	default void majorityElementGeneralApproachUsingHashMap(int[] input) {
	}

	/***
	 * ZERO should be given special treatment.
	 * 
	 */
	int[] productOfArrayExceptSelfUsingDivision(int[] input);

	int[] productOfArrayExceptSelfWithoutUsingDivision(int[] input);

	/**
	 *
	 * Question: You are given an integer array arr (not necessarily distinct). You
	 * have to split the array into some number of "chunks" (partitions) in such a
	 * way that if we individually sort each chunk and concatenate them, the result
	 * will be a sorted array. Your task is to list the most number of chunks that
	 * could have been made.
	 * 
	 **/
	List<String> getMaxSortablePartitionBruteForce(int input[]);

	/**
	 *
	 * Question: You are given an integer array arr (not necessarily distinct). You
	 * have to split the array into some number of "chunks" (partitions) in such a
	 * way that if we individually sort each chunk and concatenate them, the result
	 * will be a sorted array. Your task is to list the most number of chunks that
	 * could have been made.
	 * 
	 **/
	List<String> getMaxSortablePartition(int input[]);

	/**
	 * You are given an integer array arr that is a permutation of [0, 1, ...,
	 * arr.length - 1]. You have to split the array into some number of "chunks"
	 * (partitions) in such a way that if we individually sort each chunk and
	 * concatenate them, the result will be a sorted array. Your task is to get the
	 * most number of chunks that could have been made.
	 * 
	 * Note: Shuffling of chunks is not allowed.
	 * 
	 */
	List<String> getMaxSortablePartitionOnPermuted0ToNMinus1Elements(int[] input);

	/**
	 * <pre>
	 * 1. Given an integer array nums.
	 * 2. Partition it into two (contiguous) subarrays left and right so that:
	 *   a. Every element in left is less than or equal to every element in right.
	 *   b. Left and right are non-empty.
	 *   c. Left has the smallest possible size.
	 * 3. Return the length of left after such a partitioning.
	 * NOTE : Test cases are generated such that partitioning exists.
	 * 
	 * </pre>
	 */
	int getFirstSortablePartitionIndex(int[] input);

	/**
	 * 1. A string 's' of lowercase English letters is given.
	 * 
	 * 2. We want to partition this string into as many parts as possible so that
	 * each letter appears in at most one part.
	 * 
	 * 3. Return a list of integers representing the size of these parts.
	 * 
	 * input = "ababcbacadefegdehijhklij"
	 * 
	 * Explanation: The partition is "ababcbaca", "defegde", "hijhklij". This is a
	 * partition so that each letter appears in at most one part. A partition like
	 * "ababcbacadefegde", "hijhklij" is incorrect, because it splits 'input' into
	 * less parts.
	 */
	List<String> getPartitionLabels(String input);

	/**
	 * 1. Given an unsorted array 'arr'.
	 * 
	 * 2. Reorder it in-place such that : arr[0] <= arr[1] >= arr[2] <= arr[3] . . .
	 *
	 * 3. Please sort the array in place and do not define additional arrays.
	 * 
	 * 4. Allowed Time Complexity : O(n)
	 * 
	 */
	void WiggleSort_I(int[] input);

	/**
	 * <pre>
	 * 1. Given an unsorted array 'arr'.
	 * 2. Reorder it in-place such that : arr[0] <= arr[1] >= arr[2] <= arr[3] . . .
	 * 3. Please sort the array in place and do not define additional arrays.
	 * 4. Allowed Time Complexity : nlogn
	 * 5. Allowed Space Complexity: n
	 * 
	 * Algo Strategy: 
	 * Step1: sort the array in nlogn
	 * Step2: take two pointer first and last, place first on 0th index and last on 1th index
	 *           and do first++, last--
	 * </pre>
	 * 
	 */
	default void WiggleSort_II(int[] input) {

	}

	/**
	 * 1. Given an integer array 'arr'
	 * 
	 * 2. Find three numbers whose product is maximum and return the maximum
	 * product.
	 * 
	 */
	int maxProductOfAny3Number(int[] input);

}
