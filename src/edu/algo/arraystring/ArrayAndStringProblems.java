package edu.algo.arraystring;

import java.util.List;

import edu.ds.linkedlist.KarumanchiQuestions;

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
	List<String> getMaxSortablePartitionsBruteForce(int input[]);

	/**
	 *
	 * Question: You are given an integer array arr (not necessarily distinct). You
	 * have to split the array into some number of "chunks" (partitions) in such a
	 * way that if we individually sort each chunk and concatenate them, the result
	 * will be a sorted array. Your task is to list the most number of chunks that
	 * could have been made.
	 * 
	 **/
	List<String> getMaxSortablePartitions(int input[]);

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
	List<String> getMaxSortablePartitionsOnPermutedZeroToNMinusOneElements(int[] input);

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

	/**
	 * <pre>
	 * 1. Given an integer X.
	 * 
	 * 2. The task is to find the minimum number of jumps to reach a point X in the
	 * number line starting from zero.
	 * 
	 * 3. The first jump made can be of length one unit and each successive jump
	 * will be exactly one unit longer than the previous jump in length.
	 * 
	 * 4. It is allowed to go either left or right in each jump.
	 * 
	 * refer : MinimumJumps+i-i.pdf
	 * </pre>
	 */
	int getMinnimumJumpsToReachAPointOnXaxis1(int x);

	int getMinnimumJumpsToReachAPointOnXaxis2(int x);

	/**
	 * <pre>
	 * Given an integer array nums and two integers 'left' and 'right', return the
	 * number of contiguous non-empty subarrays such that the value of the maximum
	 * array element in that subarray is in the range [left, right].
	 * 
	 * The test cases are generated so that the answer will fit in a 32-bit integer.
	 * 
	 * Input: nums = [2,1,4,3], left = 2, right = 3 Output: 3
	 * 
	 * Explanation: There are three subarrays that meet the requirements: [2], [2, 1], [3].
	 * </pre>
	 */
	int countOfSubarraysWithBoundedMax(int[] nums, int left, int right);

	void inplaceTransposeOfNXNArray(int[][] nxn);

	/**
	 * Given an integer array nums, you need to find one continuous subarray that if
	 * you only sort this subarray in ascending order, then the whole array will be
	 * sorted in ascending order.
	 * 
	 * Return the shortest such subarray and output its length.
	 * 
	 * Example:
	 * 
	 * Input: nums = [2,6,4,8,10,9,15] Output: 5
	 * 
	 * Explanation: You need to sort [6, 4, 8, 10, 9] in ascending order to make the
	 * whole array sorted in ascending order.
	 * 
	 */
	default void printShortestUnsortedContiguousSubarray1(int[] nums) {

		// solution: sort the array, and compare with original array
		// get the first diff position as startPoint while scanning from left to right
		// get the first diff position as endPoint while scanning from right to left
	}

	void printShortestUnsortedContiguousSubarray2(int[] nums);

	void printShortestUnsortedContiguousSubarray3(int[] nums);

	/**
	 * 1. You are given an n x n 2D matrix representing an image.
	 * 
	 * 2. rotate the image by 90 degrees (clockwise).
	 * 
	 * 3. You have to rotate the image in-place, which means you have to modify the
	 * input 2D matrix directly.
	 * 
	 * 4. DO NOT allocate another 2D matrix and do the rotation.
	 */
	void inplaceRotateMatrixBy90DegreeClockwise(int[][] matrix);

	void reverseVowelOfAString(String input);

	/**
	 * <pre>
	 * 1. A group of two or more people wants to meet and minimize the total travel
	 * distance.
	 * 
	 * 2. You are given a 2D grid of values 0 or 1, where each 1 marks the home of
	 * someone in the group.
	 * 
	 * 3. Return min distance where distance is calculated using 'Manhattan
	 * Distance', where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
	 * 
	 * Example: Input
	 *
	 * [
	 *	[1,0,0,0,1],
	 *	[0,0,0,0,0],
	 *	[0,0,1,0,0]
	 *  ]
	 *
	 * Output: 6
	 * 
	 * Explanation:
	 * The point (0,2) is an ideal meeting point, as the total travel distance of 2 + 2 + 2 = 6 
	 * is minimal. So return 6.
	 * </pre>
	 */
	int getTotalDistanceTravelledToReachBestMeetingPoint(int[][] personCoordinates);

	/**
	 * 1. Given two non-negative integers, num1 and num2 represented as string.
	 * 
	 * 2. Return the sum of num1 and num2 as a string.
	 * 
	 * 3. You must solve the problem without using any built-in library for handling
	 * large integers (such as BigInteger).
	 * 
	 * 4. You must also not convert the inputs to integers directly.
	 * 
	 * @see KarumanchiQuestions#summationForEqualSizedList(edu.ds.linkedlist.List,
	 *      edu.ds.linkedlist.List)
	 * 
	 */
	String getSummationOfTwoStrings(String str1, String str2);

	String getProductOfTwoString(String str1, String str2);

	/**
	 * 1. Given an array nums of non-negative integers.
	 * 
	 * 2. Arrange elements of array in specific order, all even elements followed by
	 * odd elements. [even -> odd]
	 * 
	 * 3. Preserve the order of Even elements without using extra space.
	 * 
	 * 4. Hence question is order specific so you have to match your output in order
	 * of given output.a
	 * 
	 */
	void sortArrayByParity(int arr[]);

	/**
	 * 1. Question will be provided with "n" Intervals. An Interval is defined as
	 * (sp,ep) i.e. sp --> starting point & ep --> ending point of an Interval
	 * (sp/ep are inclusive). Some Intervals may or maynot overlap eachother.
	 * 
	 * 2. Intervals[i] = [startingPoint,endingPoint] Task is to "Merge all
	 * Overlapping Intervals".
	 * 
	 * Example 1 : Input : [[1,3],[2,4],[6,8],[10,14],[7,9]] Output :
	 * [[1,4],[6,9],[10,14]]
	 * 
	 * Example 2 : Input : [[1,3],[3,15],[6,8],[10,14],[7,9]] Output : [[1,3][3,15]]
	 * 
	 * Example 3 : Input : [[1,3],[5,8],[10,19],[15,20],[9,9]] Output :
	 * [[1,3],[5,8],[9,9],[10,20]]
	 * 
	 * Note : If ending time of interval I1 is same as starting time of I2 then both
	 * will consider as separate Intervals.
	 * 
	 */
	void printMergableIntervals(int[][] intervals);

	int[] getSieveOfEratosthenes(int n);

	void printSieveOfEratosthenesInRange(int start, int end);

	/**
	 * 1. A complex number can be represented as a string on the form "Real +
	 * Imaginary i" where:
	 * 
	 * 1.1 Real is the real part and is an integer in the range [-100, 100].
	 * 
	 * 1.2 Imaginary is the imaginary part and is an integer in the range [-100,
	 * 100]. 1.3 i^2 == -1.
	 * 
	 * 2. Given two complex numbers num1 and num2 as strings, return a string of the
	 * complex number that represents their multiplications.
	 */
	void printProductOfTwoComplexNumber(String c1, String c2);

	/**
	 * 1. Given a string s.
	 * 
	 * 2. Return true if the s can be palindrome after deleting at most one
	 * character from it.
	 * 
	 * s = "abca" by removing c => aba or b => aca we will have valid palindrome.
	 * 
	 * 
	 */
	boolean isValidPalindromeByIgnoringAtMostOneChar(String input);

	/**
	 * https://leetcode.com/problems/car-fleet/
	 * 
	 */
	int calulateCarFleets(int target, int[] position, int[] speed);

	/**
	 * 1. You are given a number in form of String.
	 * 
	 * 2. You can swap two digits at most once to get the maximum valued number in
	 * that string.
	 * 
	 * 3. Return the maximum valued number you can get in form of string.
	 * 
	 * num = "2736" : res = "7236" Explanation : swap 2 and 7 to get maximum values
	 * 
	 */
	void maxNumberFromSingleSwap(String num);

	/**
	 * https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
	 * 
	 * <pre>
	 * You have k lists of sorted integers in non-decreasing order. Find the
	 * smallest range that includes at least one number from each of the k lists.
	 * 
	 * Input: nums = [[4,10,15,24,26],[0, ,12,20],[5,18,22,30]] 
	 * 
	 * Output: [20,24]
	 * </pre>
	 */
	int[] smallestRangeCoveringElementsOfKLists1(List<List<Integer>> nums);

	int[] smallestRangeCoveringElementsOfKLists2(List<List<Integer>> nums);

	/**
	 * 
	 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
	 * 
	 * 
	 * 1. In a row of dominoes, tops[i] and bottoms[i] represent the top and bottom
	 * halves of the ith domino.
	 * 
	 * 2. A domino(Dice Structured) is a tile with two numbers from 1 to 6 - one on
	 * each half of the tile.
	 * 
	 * 3. We may rotate the ith domino, so that tops[i] and bottoms[i] swap values.
	 * 
	 * 4. Return the minimum number of rotations so that all the values in tops are
	 * the same, or all the values in bottoms are the same.
	 * 
	 * 5. If it cannot be done, return -1.
	 */
	public int minDominoRotationsForEqualRow1(int[] tops, int[] bottoms);
	
	public int minDominoRotationsForEqualRow2(int[] tops, int[] bottoms);

}
