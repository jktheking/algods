package edu.algo.arraystring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.algo.algointro.BitwiseUtils;
import edu.ds.stack.StackKarumanchiQuestions;

public class ArrayAndStringSolutions implements ArrayAndStringProblems {

	public static void main(String[] args) {

		// testIsWordVerifiableTypedUsingFaultyKeyboard();

		// INSTANCE.rangeUpdateUsingPrefixSumInOrderN(new int[] { 0, 0, 0, 0, 0 },
		// new int[][] { { 1, 3, 2 }, { 2, 4, 3 }, { 0, 2, -2 } });

		// System.out.println(INSTANCE.calculateMaxAreaBetweenLines(new int[] { 1, 8, 6,
		// 2, 5, 4, 8, 3, 7 }));

		// INSTANCE.squareOfIntegerInSortedArray(new int[] { -4, -3, -1, 0, 2, 3, 5 });

		// INSTANCE.majorityElementUsingBoyerMooreVoting(new int[] { 7, 7, 2, 4, 7 });

		// INSTANCE.majorityElementUsingBoyerMooreVoting(new int[] { 2, 2, 1, 1 });

		// INSTANCE.nBy3majorityElementUsingBoyerMooreVoting(new int[] { 2, 2, 1, 1, 1,
		// 2, 2 });

		// System.out
		// .println(Arrays.toString(INSTANCE.productOfArrayExceptSelfUsingDivision(new
		// int[] { 1, 2, 3, 4 })));
		// System.out.println(
		// Arrays.toString(INSTANCE.productOfArrayExceptSelfUsingDivision(new int[] { 1,
		// 2, 0, 3, 4 })));
		// System.out.println(
		// Arrays.toString(INSTANCE.productOfArrayExceptSelfUsingDivision(new int[] { 1,
		// 2, 0, 3, 4, 0 })));
		//
		// System.out.println(
		// Arrays.toString(INSTANCE.productOfArrayExceptSelfWithoutUsingDivision(new
		// int[] { 1, 2, 3, 4 })));
		// System.out.println(
		// Arrays.toString(INSTANCE.productOfArrayExceptSelfWithoutUsingDivision(new
		// int[] { 1, 2, 0, 3, 4 })));
		// System.out.println(Arrays
		// .toString(INSTANCE.productOfArrayExceptSelfWithoutUsingDivision(new int[] {
		// 1, 2, 0, 3, 4, 0 })));

		// System.out.println(INSTANCE.getPartitionLabels("ababcbacadefegdehijhklij"));

		// INSTANCE.getMaxSortablePartitionOnPermuted0ToNMinus1Elements(new int[] { 2,
		// 0, 1, 3, 5, 4 });

		// INSTANCE.getMaxSortablePartitionBruteForce(new int[] { 30, 10, 20, 40, 60,
		// 50, 75, 70 });
		// INSTANCE.getMaxSortablePartitionBruteForce(new int[] { 2, 0, 1, 3, 5, 4,0 });
		//
		// INSTANCE.getMaxSortablePartition(new int[] { 30, 10, 20, 40, 60, 50, 75, 70
		// });
		// INSTANCE.getMaxSortablePartition(new int[] { 2, 0, 1, 3, 5, 4 ,0});

		// System.out.println(INSTANCE.getFirstSortablePartitionIndex(new int[] { 2, 0,
		// 1, 3, 5, 4 }));

		//INSTANCE.WiggleSort_I(new int[] { 3, 5, 2, 1, 6, 4 });
		//INSTANCE.WiggleSort_I(new int[] { 1, 5, 1, 1, 6, 4 });
		
		
		INSTANCE.maxProductOfAny3Number(new int[] { 6, 5, 3, 7, 4, 1, 2 });
        INSTANCE.maxProductOfAny3Number(new int[] { -6, -5, -3, -7, -4, -1, -2 });
        INSTANCE.maxProductOfAny3Number(new int[] { -9, -8, 7, 1, 2, 6, 3, 4, 5, 7 });

	}

	private static void testIsWordVerifiableTypedUsingFaultyKeyboard() {

		System.out.println(INSTANCE.isWordVerifiableTypedUsingFaultyKeyboard("baabbccccd", "aabbcc"));

		System.out.println(INSTANCE.isWordVerifiableTypedUsingFaultyKeyboard("aaabbccccd", "aabbcc"));

		System.out.println(INSTANCE.isWordVerifiableTypedUsingFaultyKeyboard("aaabbcccc", "aabbcc"));
	}

	/**
	 * <pre>
	 * Example: actual : aabbcc typed: aaabbcccccd
	 * 
	 * cases to take care:
	 * 
	 * CASE-A: typedWord.length < actualWord.length
	 * 
	 * CASE-B: actualWord got exhausted and typedWord length still in loop.
	 *      need to verify it typedWord contains some invalid char at end.
	 * 
	 * 
	 * </pre>
	 */
	@Override
	public boolean isWordVerifiableTypedUsingFaultyKeyboard(String typedWord, String actualWord) {

		if (typedWord.length() < actualWord.length())
			return false;

		char previousActualChar = Character.MIN_VALUE;

		for (int i = 0, j = 0; i < typedWord.length(); i++) {

			if (j < actualWord.length() && typedWord.charAt(i) == actualWord.charAt(j)) {
				previousActualChar = actualWord.charAt(j);
				j++;
				continue;

			} else if (typedWord.charAt(i) != previousActualChar) {
				return false;
			}

		}
		return true;

	}

	/**
	 * <pre>
	 * Observation: prefix-sum propagate the value of a given-index in array to 
	 * all the next indices.
	 * 
	 * Strategy: 
	 * STEP1 : While performing the range update we mark the addition 'inc' to the startIndex, and mark the 
	 * subtraction 'inc' to endIndex+1. so that while calculating prefix-sum 'inc' will 
	 * get applied in the given range and post-range because of subtraction with 'inc'
	 * will negate the impact.
	 * 
	 * STEP2: post all range updates we will calculate the prefix-sum.
	 * 
	 * 
	 * Time-Complexity: 
	 * 1. each query takes 2-unit to perform the mark step
	 * 2. n time to calculate the prefix-sum
	 * 
	 * Total time-complexity: (2q+n) i.e. O(q+n)
	 * </pre>
	 * 
	 */
	@Override
	public void rangeUpdateUsingPrefixSumInOrderN(int[] input, int[][] updateQueries) {

		System.out.println("input:" + Arrays.toString(input));

		for (int[] updateQuery : updateQueries) {
			System.out.println("range-update-query:" + Arrays.toString(updateQuery));
			rangeUpdateUsingPrefixSumMarker(input, updateQuery[0], updateQuery[1], updateQuery[2]);
		}

		calculatePrefixSum(input);

		System.out.println("result post range-updates:" + Arrays.toString(input));
	}

	private void rangeUpdateUsingPrefixSumMarker(int[] input, int stratIndex, int endIndex, int inc) {
		input[stratIndex] += inc;
		// post-range need to negate the presence of 'inc'
		if (endIndex + 1 < input.length)
			input[endIndex + 1] -= inc;
	}

	private void calculatePrefixSum(int[] input) {
		for (int i = 0; i < input.length - 1; i++) {
			input[i + 1] += input[i];
		}

	}

	@Override
	public int getMidValueUsingSingleIntegerDivision(int number) {
		return (number + 1) / 2;
	}

	/***
	 * Please refer WaterTrappingTwoPointerApproach.pdf
	 *
	 * @see {@link StackKarumanchiQuestions#findTotalRainWaterTrappedOverHistogramUsingTwoPointer2(int[])}
	 * 
	 */
	@Override
	public int calculateMaxAreaBetweenLines(int[] vLines) {
		int i = 0;
		int j = vLines.length - 1;
		int maxArea = 0;
		while (i < j) {
			if (vLines[i] <= vLines[j]) {
				maxArea = Math.max(maxArea, (j - i) * vLines[i]);
				i++;
			} else {
				maxArea = Math.max(maxArea, (j - i) * vLines[j]);
				j--;
			}
		}
		return maxArea;
	}

	/**
	 * <pre>
	 * Since we are talking about sorted integers, so we should think about applying
	 * 'merge-process'
	 * 
	 * [2,3,4,5,6]
	 * 
	 * [-4,-3,-1, 0, 2, 3, 5] 
	 * 
	 * [16, 9, 1, 0, 4, 9,  25] 
	 * <-------- ------------> 
	 * sorted 'l1'  sorted 'l2'     
	 * -------->  <------------
	 * </pre>
	 * 
	 * Time-Complexity : O(n)
	 */
	@Override
	public void squareOfIntegerInSortedArray(int[] input) {
		int output[] = new int[input.length];
		if (input[0] < 0) {
			// two pointer merge-approach of descending ordered sorted list
			int i = 0;
			int j = input.length - 1;
			int leftSquare = input[i] * input[i];
			int rightSquare = input[j] * input[j];
			int k = input.length - 1;
			while (i <= j) {
				if (leftSquare <= rightSquare) {
					output[k] = rightSquare;
					j--;
					rightSquare = input[j] * input[j];
				} else {
					output[k] = leftSquare;
					i++;
					leftSquare = input[i] * input[i];
				}
				k--;
			}

		} else {
			// all values in array is positive,so simply iterate and place the square

			for (int i = 0; i < input.length; i++) {
				output[i] = input[i] * input[i];
			}

		}

		System.out.println(Arrays.toString(output));

	}

	/**
	 * 
	 * 
	 * */
	@Override
	public void majorityElementUsingBoyerMooreVoting(int[] input) {

		int majorityCandidate = getMajorityCandidateElement(input);

		if (majorityCandidate != Integer.MIN_VALUE) {
			int actualVoteCount = 0;
			for (int e : input) {
				if (e == majorityCandidate)
					actualVoteCount++;
			}

			if (actualVoteCount > input.length / 2) {
				System.out.println("majority element:" + majorityCandidate);
				return;
			}

		}
		System.out.println("!!!no majority element exists!!!");

	}

	/**
	 * We will have at max 1 majority element as n%2 can be 0 or 1.
	 * 
	 * ALGO STRATEGY:
	 * 
	 * 1. Gather 2 distinct elements and cancel their votes.
	 * 
	 * 2. Repeat the process for remaining element.
	 * 
	 * CASE: When vote count of majority_candidate_element cancels out to zero by
	 * some next elements then how to pick the next majority_candidate_element ?
	 * 
	 * Two options are there:
	 * 
	 * 1. we can pick the current index element as next majority_candidate_element
	 * where vote count of previous candidate becomes zero.
	 * 
	 * 2. we can pick the next_index_element as next majority_candidate_element
	 * where vote count of previous candidate becomes zero.
	 * 
	 * 
	 */
	private int getMajorityCandidateElement(int[] input) {

		int majorityCandidate = 0;
		int voteCountMajorityCanddiate = 0;
		for (int i = 0; i < input.length; i++) {

			if (voteCountMajorityCanddiate == 0) {
				majorityCandidate = input[i];
				voteCountMajorityCanddiate = 1;
			} else if (input[i] == majorityCandidate) {
				voteCountMajorityCanddiate++;
			} else {
				// this is case where both majorityCandidate and current_element are distinct
				// so we will cancel out their votes, since we have not captured the vote of
				// current element so do-nothing for canceling the vote of current element.
				voteCountMajorityCanddiate--;
			}

		}

		return voteCountMajorityCanddiate > 0 ? majorityCandidate : Integer.MIN_VALUE;
	}

	@Override
	public void nBy3majorityElementUsingBoyerMooreVoting(int[] input) {

		List<Integer> candidates = getNby3majorityCandidateElement(input);

		Iterator<Integer> it = candidates.iterator();

		while (it.hasNext()) {
			int candidate = it.next();
			if (Arrays.stream(input).filter(in -> candidate == in).count() > input.length / 3) {
				System.out.println("majority element:" + candidate);
			} else {
				it.remove();
			}
		}

		if (candidates.isEmpty()) {
			System.out.println("!!!no majority element exists!!!");
		}

	}

	/**
	 * We will have at max 2 majority element as n%3 can be 0 or 1 or 2.
	 * 
	 * ALGO STRATEGY:
	 * 
	 * 1. Gather 3 distinct elements and cancel their votes.
	 * 
	 * 2. Repeat the process for remaining element.
	 * 
	 */
	private List<Integer> getNby3majorityCandidateElement(int[] input) {
		int majCandidate1 = 0;
		int majvoteCount1 = 0;

		int majCandidate2 = 0;
		int majVoteCount2 = 0;

		for (int i = 0; i < input.length; i++) {

			// if-else-if for majvoteCount1 should be together, otherwise majCandidate1 and
			// majCandidate2 may have same value.
			if (majvoteCount1 == 0) {
				majCandidate1 = input[i];
				majvoteCount1 = 1;
			} else if (majCandidate1 == input[i]) {
				majvoteCount1++;
			}

			else if (majVoteCount2 == 0) {
				majCandidate2 = input[i];
				majVoteCount2 = 1;
			} else if (majCandidate2 == input[i]) {
				majVoteCount2++;
			} else {
				// this is the place where all the three majorityCandidate1,
				// majorityCandidate2 and current_element are distinct, so we need to
				// cancels their votes
				majvoteCount1--;
				majVoteCount2--;

			}
		}

		List<Integer> result = new ArrayList<>();
		if (majvoteCount1 > 0)
			result.add(majCandidate1);
		if (majVoteCount2 > 0)
			result.add(majCandidate2);

		return result;
	}

	/**
	 * Space complexity: O(1) Time Complexity : O(1)
	 * 
	 **/
	@Override
	public int[] productOfArrayExceptSelfUsingDivision(int[] input) {
		int zeroCount = 0;

		int mult = 1;
		int zeroElementIndex = -1;
		for (int i = 0; i < input.length; i++) {
			if (input[i] == 0) {
				zeroCount++;
				zeroElementIndex = i;
				if (zeroCount == 2) {
					mult = 0;
					break;
				}
			} else {
				mult *= input[i];
			}
		}

		if (zeroCount == 2) {
			// every element of array will be zero
			Arrays.fill(input, 0);
		} else if (zeroCount == 1) {
			// all positions of array will have zero except zeroElementIndex
			Arrays.fill(input, 0);
			input[zeroElementIndex] = mult;
		} else {
			for (int i = 0; i < input.length; i++)
				input[i] = mult / input[i];

		}
		return input;
	}

	/***
	 * Here we need to employ the concept of prefix-multiplication and
	 * suffix-multiplication.
	 * 
	 * Algo implementation strategy:
	 * 
	 * We need to store either of the prefix-multiplication or suffix-multiplication
	 * the other one will be calculated on-the-fly.
	 * 
	 * Space-complexity: O(n)
	 * 
	 * Time-complexity : O(n)
	 * 
	 */
	@Override
	public int[] productOfArrayExceptSelfWithoutUsingDivision(int[] input) {

		int[] suffixMult = new int[input.length];
		// store the suffix-multiplication

		suffixMult[suffixMult.length - 1] = input[suffixMult.length - 1];
		for (int i = suffixMult.length - 2; i >= 0; i--) {
			suffixMult[i] = suffixMult[i + 1] * input[i];
		}

		int onTheFlyPrefixMult = 1;
		for (int i = 0; i < input.length - 1; i++) {
			int currentInput = input[i];
			input[i] = onTheFlyPrefixMult * suffixMult[i + 1];
			onTheFlyPrefixMult *= currentInput;
		}
		input[input.length - 1] = onTheFlyPrefixMult;

		return input;
	}

	/**
	 * <pre>
	 * Observation:
	 * Example {30,10,20,40,60,50,75,70}; first partition position is 2nd index(20)
	 * For this partition to be sortable maxElement of this partition should be smaller than
	 * the minElement of the next partition.
	 * 
	 * Partition Condition: 
	 * A given candidate-index is considered for partitioning where 'leftMax' including candidate-index 
	 * is smaller than the 'rightMin' excluding the candidate-index.
	 * 
	 *   'leftMax' < 'rightMin'
	 *   
	 *   Time Complexity: for each element we are traversing array to get leftMax and rightMin
	 *                  : O(n^2)
	 * 
	 * </pre>
	 */
	@Override
	public List<String> getMaxSortablePartitionBruteForce(int[] input) {

		List<String> result = new ArrayList<>();

		int partitionStart = 0;
		// note: last partition line will be at secondLast-element
		for (int i = 0; i <= input.length - 2; i++) {
			int leftMax = getLeftMax(partitionStart, i, input);
			int rightMin = getRightMin(i + 1, input);

			if (leftMax < rightMin) {
				// this is partition position
				int partition[] = new int[i + 1 - partitionStart];
				System.arraycopy(input, partitionStart, partition, 0, partition.length);
				result.add(Arrays.toString(partition));

				partitionStart = i + 1;
			}

		}

		int[] lastPartition = new int[input.length - partitionStart];
		System.arraycopy(input, partitionStart, lastPartition, 0, lastPartition.length);
		result.add(Arrays.toString(lastPartition));

		System.out.println(result);
		return result;
	}

	private int getLeftMax(int l, int r, int[] input) {

		int leftMax = Integer.MIN_VALUE;
		while (l <= r) {
			leftMax = Math.max(leftMax, input[l]);
			l++;
		}
		return leftMax;

	}

	private int getRightMin(int l, int[] input) {
		int rightMin = Integer.MAX_VALUE;

		for (int i = input.length - 1; i >= l; i--) {
			rightMin = Math.min(rightMin, input[i]);
		}
		return rightMin;
	}

	/**
	 * <pre>
	 * Algo Strategy: We should pre-compute either of the 'leftMax' or 'rightMax'
	 * and other on-the-fly.
	 * 
	 * Time Complexity : 'n' for per-computation & 'n' for partitioning
	 *                 :O(2n) = O(n)
	 *                 
	 * Space Complexity: O(n) because of  pre-computation
	 * </pre>
	 */
	@Override
	public List<String> getMaxSortablePartition(int[] input) {

		List<String> result = new ArrayList<>();

		// calculate the rightMin array beforeHand
		int[] rightMinArr = new int[input.length];
		rightMinArr[input.length - 1] = input[input.length - 1];
		for (int i = input.length - 2; i >= 0; i--) {
			rightMinArr[i] = Math.min(rightMinArr[i + 1], input[i]);
		}

		int partitionStart = 0;
		int leftMaxOnTheFly = Integer.MIN_VALUE;
		// note: last partition line will be at secondLast-element
		for (int i = 0; i <= input.length - 2; i++) {

			leftMaxOnTheFly = Math.max(leftMaxOnTheFly, input[i]);

			if (leftMaxOnTheFly < rightMinArr[i + 1]) {
				// this is partition position
				int partition[] = new int[i + 1 - partitionStart];
				System.arraycopy(input, partitionStart, partition, 0, partition.length);
				result.add(Arrays.toString(partition));

				partitionStart = i + 1;
			}
		}

		int[] lastPartition = new int[input.length - partitionStart];
		System.arraycopy(input, partitionStart, lastPartition, 0, lastPartition.length);
		result.add(Arrays.toString(lastPartition));
		System.out.println(result);
		return result;
	}

	/**
	 * <pre>
	 * Input property: we have shuffled 0 to n-1 elements.
	 * 
	 * Algo Strategy: we will use pre-computed 'leftMax' and 'rightMin' to decide the partition
	 * position. Partition condition  'leftMax' <= 'rightMin'
	 * 
	 * 1. rightMin:
	 * Note: Index of array can be treated as 'rightMin' because all the indices ahead
	 * of given index is always greater than it.
	 * 
	 * 2.leftMax: we will calculate the leftMax on-the-fly.
	 * 
	 * 
	 * </pre>
	 * 
	 **/
	@Override
	public List<String> getMaxSortablePartitionOnPermuted0ToNMinus1Elements(int[] input) {

		List<String> result = new ArrayList<>();

		int leftMax = Integer.MIN_VALUE;
		int partitionStarting = 0;

		for (int i = 0; i < input.length; i++) {
			leftMax = Math.max(leftMax, input[i]);

			// i is acting as rightMin
			if (leftMax == i) {
				// this is partition position
				int partition[] = new int[i + 1 - partitionStarting];
				System.arraycopy(input, partitionStarting, partition, 0, partition.length);
				result.add(Arrays.toString(partition));

				partitionStarting = i + 1;

			}

		}

		System.out.println(result);
		return result;
	}

	@Override
	public List<String> getPartitionLabels(String input) {

		// STEP1: create map for last seen index.

		Map<Character, Integer> lastSeenMap = new HashMap<>();
		for (int i = 0; i < input.length(); i++) {
			lastSeenMap.put(input.charAt(i), i);
		}

		List<String> partitions = new ArrayList<>();

		int partitionStartIndex = 0;
		int partitionEndIndex = -1;

		for (int i = 0; i < input.length(); i++) {

			if (i == partitionEndIndex) {
				// we got the partition
				partitions.add(input.substring(partitionStartIndex, partitionEndIndex + 1));

				// re-initialization for next partition
				partitionStartIndex = partitionEndIndex + 1;
				partitionEndIndex = -1;
			} else {

				if (partitionEndIndex < lastSeenMap.get(input.charAt(i))) {
					partitionEndIndex = lastSeenMap.get(input.charAt(i));
				}

			}
		}
		return partitions;
	}

	/**
	 * Time Complexity : O(n) Space Complexity: O(1)
	 * 
	 * 2, 0, 1, 3, 5, 4, 0
	 **/
	@Override
	public int getFirstSortablePartitionIndex(int[] input) {

		int candidatePartitionIndex = -1;
		int candidatePartitionLeftMax = Integer.MAX_VALUE;

		int leftMax = Integer.MIN_VALUE;

		for (int i = 0; i <= input.length - 2; i++) {

			leftMax = Math.max(leftMax, input[i]);

			int tempRightMax = input[i + 1];

			// if candidatePartitionLeftMax is smaller than the right-element it
			// means candidatePartitionIndex is valid till now.
			if (candidatePartitionLeftMax < tempRightMax) {
				continue;
			}

			if (leftMax < tempRightMax) {
				candidatePartitionLeftMax = leftMax;
				candidatePartitionIndex = i;
			}
		}

		return candidatePartitionIndex;

	}

	/**
	 * <pre>
	 * post sort:  arr[0] <= arr[1] >= arr[2] <= arr[3] . . . .
	 * Observation:
	 *  Element at even index : smaller than both left and right element
	 *  Element at odd index :  greater than both left and right element
	 * 
	 * Algo strategy: swap un-allowed element
	 * 
	 * Time Complexity: O(n)
	 * Space Complexity: O(1)
	 * </pre>
	 */
	@Override
	public void WiggleSort_I(int[] input) {

		System.out.println("input:" + Arrays.toString(input));

		for (int i = 0; i < input.length - 1; i++) {

			if (BitwiseUtils.isEven(i) && input[i + 1] < input[i]) {
				swap(input, i, i + 1);

			} else if (!BitwiseUtils.isEven(i) && input[i + 1] > input[i]) {
				swap(input, i, i + 1);
			}

		}

		System.out.println("result:" + Arrays.toString(input));

	}

	private void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;

	}

	/**
	 * <pre>
	 * Note: we can have -ve number.
	 * 
	 * OBSERVATIONS:
	 * 
	 * 	1. If all +ves : MAX_PRODUCT = max1 * max2 * max3
	 * 
	 *  2. If all -ves : MAX_PRODUCT = max1 * max2 * max3
	 *  
	 *  3. If both +ves and -ves:
	 *   Sured to have:  max1 as +ve and min1 as -ve.
	 *   
	 *  --> There is a fight between  min1*min2  and  max2*max3 for following cases:
	 *     
	 *     CASE1: if both the products min1*min2 and max2*max3 are -ve
	 *     CASE2: if both the products min1*min2 and max2*max3 are +ve
	 *     CASE2: if any one of the products is -ve between min1*min2 and  max2*max3 
	 *     
	 *  --> winner of this fight will get multiplied with max1 to have the max product.
	 *  It means 'max1' is getting multiplied with either min1*min2 or max2*max3 
	 *
	 * So, final condition of MAX_PRODUCT
	 * 
	 *  MAX_PRODUCT = Math.max(min1*min2*max1,  max1 * max2 * max3)
	 * 
	 * </pre>
	 */
	@Override
	public int maxProductOfAny3Number(int[] input) {

		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;

		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;

		for (int i = 0; i < input.length; i++) {
			if (input[i] < min1) {
				min2 = min1;
				min1 = input[i];
			} else if (input[i] < min2) {
				min2 = input[i];
			}

			if (input[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = input[i];
			} else if (input[i] > max2) {
				max3 = max2;
				max2 = input[i];
			} else if (input[i] > max3) {
				max3 = input[i];
			}
		}

		int prod1 = min1 * min2 * max1;
		int prod2 = max1 * max2 * max3;
		if (prod1 > prod2) {
			System.out.println(min1 + " * " + min2 + " * " + max1 + " = " + prod1);
			return prod1;
		} else {
			System.out.println(max1 + " * " + max2 + " * " + max3 + " = " + prod2);
			return prod2;
		}

	}

}
