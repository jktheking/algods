package edu.algo.arraystring;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.algo.algointro.BitwiseUtils;
import edu.algo.sort.inversecount.InversionCount;
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

		// INSTANCE.WiggleSort_I(new int[] { 3, 5, 2, 1, 6, 4 });
		// INSTANCE.WiggleSort_I(new int[] { 1, 5, 1, 1, 6, 4 });

		// INSTANCE.maxProductOfAny3Number(new int[] { 6, 5, 3, 7, 4, 1, 2 });
		// INSTANCE.maxProductOfAny3Number(new int[] { -6, -5, -3, -7, -4, -1, -2 });
		// INSTANCE.maxProductOfAny3Number(new int[] { -9, -8, 7, 1, 2, 6, 3, 4, 5, 7
		// });

		int point = 17;
		// System.out.println(INSTANCE.getMinnimumJumpsToReachAPointOnXaxis1(point));
		// System.out.println(INSTANCE.getMinnimumJumpsToReachAPointOnXaxis2(point));

		// System.out.println(INSTANCE.countOfSubarraysWithBoundedMax(new int[] { 7, 1,
		// 1, 4, 3 }, 2, 3));
		// System.out.println(INSTANCE.countOfSubarraysWithBoundedMax(new int[] { 2, 1,
		// 1, 4 }, 2, 3));

		// int[][] matrix = { { 11, 12, 13 }, { 21, 22, 23 }, { 31, 32, 33 } };
		// INSTANCE.inplaceTransposeOfNXNArray(matrix);

		// INSTANCE.printShortestUnsortedContiguousSubarray3(new int[]
		// {2,6,4,8,10,9,15});

		// INSTANCE.printShortestUnsortedContiguousSubarray2(new int[] { 1, 2, 3, 6, 7,
		// 4, 15, 38, 11, 12, 16 });

		// INSTANCE.printShortestUnsortedContiguousSubarray2(new int[] { 2, 4, 6, 4, 8,
		// 10, 9, 10, 15});

		int[][] toRotate = { //
				{ 11, 12, 13, 14 }, //
				{ 21, 22, 23, 24 }, //
				{ 31, 32, 33, 34 }, //
				{ 41, 42, 43, 44 }//
		};
		// INSTANCE.inplaceRotateMatrixBy90DegreeClockwise(toRotate);

		// INSTANCE.reverseVowelOfAString("Suddenly");
		// INSTANCE.reverseVowelOfAString("appropriate");

		int[][] friends = { //
				{ 1, 0, 0, 0, 1 }, //
				{ 0, 0, 0, 0, 0 }, //
				{ 0, 0, 1, 0, 0 }//
		};
		// System.out.println(INSTANCE.getTotalDistanceTravelledToReachBestMeetingPoint(friends));

		// System.out.println(INSTANCE.getSummationOfTwoStrings("9321", "897"));
		String op1 = "000";
		String op2 = "00001";
		// System.out.println("product:"+op1+"*"+op2+"="+INSTANCE.getProductOfTwoString(op1,op2));

		// testSortArrayByParity();

		// INSTANCE.printMergableIntervals(new int[][] { { 1, 3 }, { 2, 4 }, { 6, 8 }, {
		// 10, 14 }, { 7, 9 } });

		// System.out.println(Arrays.toString(INSTANCE.getSieveOfEratosthenes(6)));

		// INSTANCE.printSieveOfEratosthenesInRange(22, 51);

		// INSTANCE.printProductOfTwoComplexNumber("2 + 3i", "2 - 4i");

		// System.out.println(INSTANCE.isValidPalindromeByIgnoringAtMostOneChar("abca"));
		// System.out.println(INSTANCE.isValidPalindromeByIgnoringAtMostOneChar("aca"));
		// System.out.println(INSTANCE.isValidPalindromeByIgnoringAtMostOneChar("abc"));

		// INSTANCE.calulateCarFleets(12,new int[] { 10, 8, 0, 5, 3 }, new int[] { 2, 4,
		// 1, 1, 3 });
		// INSTANCE.calulateCarFleets(10, new int[] { 3 }, new int[] { 3 });
		// INSTANCE.calulateCarFleets(100, new int[] { 0, 2, 4 }, new int[] { 4, 2, 1
		// });

		// testMaxNumberFromSingleSwap();
		//
		//		INSTANCE.smallestRangeCoveringElementsOfKLists1(
		//				List.of(List.of(4, 10, 15, 24, 26), List.of(0, 9, 12, 20), List.of(5, 18, 22, 30)));
		//		INSTANCE.smallestRangeCoveringElementsOfKLists1(List.of(List.of(1, 2, 3), List.of(1, 2, 3), List.of(1, 2, 3)));

//		System.out.println(INSTANCE.minDominoRotationsForEqualRow1(new int[] { 2, 1, 2, 4, 2, 2 },
//				new int[] { 5, 2, 6, 2, 3, 2 }));
//		System.out.println(
//				INSTANCE.minDominoRotationsForEqualRow1(new int[] { 3, 5, 1, 2, 3 }, new int[] { 3, 6, 3, 3, 4 }));
//		System.out.println(INSTANCE.minDominoRotationsForEqualRow2(new int[] { 2, 1, 2, 4, 2, 2 },
//				new int[] { 5, 2, 6, 2, 3, 2 }));
//		System.out.println(
//				INSTANCE.minDominoRotationsForEqualRow2(new int[] { 3, 5, 1, 2, 3 }, new int[] { 3, 6, 3, 3, 4 }));

	}

	private static void testMaxNumberFromSingleSwap() {
		INSTANCE.maxNumberFromSingleSwap("2736");
		INSTANCE.maxNumberFromSingleSwap("1234");
		INSTANCE.maxNumberFromSingleSwap("54321");
		INSTANCE.maxNumberFromSingleSwap("9984123594");
		INSTANCE.maxNumberFromSingleSwap("998877665544337772");
	}

	private static void testSortArrayByParity() {
		INSTANCE.sortArrayByParity(new int[] { 9, 3, 8, 7, 6, 2, 3 });
		INSTANCE.sortArrayByParity(new int[] { 2, 4, 6, 5, 3, 1 });
		INSTANCE.sortArrayByParity(new int[] { 2, 4, 6 });
		INSTANCE.sortArrayByParity(new int[] { 3, 5, 5, 7 });

		INSTANCE.sortArrayByParity(new int[] { 3, 5, 5, 7, 2 });
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
	public List<String> getMaxSortablePartitionsBruteForce(int[] input) {

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
	public List<String> getMaxSortablePartitions(int[] input) {

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
	public List<String> getMaxSortablePartitionsOnPermutedZeroToNMinusOneElements(int[] input) {

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

	/***
	 * 
	 * k = n*(n+1)/2
	 * 
	 * roughly, k ~= n^2, means n = sqrt(k)
	 * 
	 * Time-complexity : O(sqrt(k))
	 * 
	 * 
	 */
	@Override
	public int getMinnimumJumpsToReachAPointOnXaxis1(int x) {

		int n = 0;
		int k = 0;
		// stop the loop when difference of of k-x becomes even
		while (k < x || !BitwiseUtils.isEven(k - x)) {
			k = calculate1toNthSum(++n);

		}
		return n;
	}

	int calculate1toNthSum(int n) {
		return n * (n + 1) / 2;
	}

	/***
	 * 
	 * Time-complexity : O(sqrt(k))
	 * 
	 * 
	 */
	@Override
	public int getMinnimumJumpsToReachAPointOnXaxis2(int x) {
		int sumOf1toN = 0;
		int n = 0;
		while (sumOf1toN < x || !BitwiseUtils.isEven(sumOf1toN - x)) {
			n++;
			sumOf1toN += n;
		}

		return n;
	}

	/**
	 * <pre>
	 * array :[a,b,c,d] 
	 * sub-arrays: [a], [a,b], [a,b,c], [a,b,c,d], [b], [b,c],[b,c,d] ,[c] ,[c,d], [d] 
	 * 
	 * count of sub-arrays: n*(n+1)/2
	 * 
	 * Count of sub-arrays using a particular element in [a,b,c,d]:
	 * 1. sub-array using 1st  element i.e. 'a' : [a]                         ==>count & window-size : 1 
	 * 2. sub-array using 2nd  element i.e. 'b' : [a,b] [b]                   ==>count & window-size : 2 
	 * 2. sub-array using 3rd  element i.e. 'c' : [a,b,c] [b,c] [c]           ==>count & window-size : 3
	 * 2. sub-array using 4th  element i.e. 'd' : [a,b,c,d] [b,c,d] [c,d] [d] ==>count & window-size : 4
	 * 
	 * total count : 1+2+3+4 = 10 ==> summation_of_window_size
	 * 
	 * Algorithm Strategy :
	 * 
	 * 1. A sub-array calculation  window is bounded by two break-points. Break-point is any
	 * value which is greater than 'right' range.
	 * 
	 * 2. Since, sub-arrays are contiguous so, we should always count sub-arrays afresh
	 *  when we found a new window bounded by two break-points. 
	 *  
	 * 3. Counting sub-arrays within a window: 
	 *    CASE A: when the window element lies in the range[left, right] both inclusive:
	 *     --The count of sub-arrays including the element will be equal to current-window-size.
	 *    CASE B: when the window element is smaller than the window's left-range: 
	 *     --The count of sub-arrays including the element will be equal to the  previous-window-size.
	 *     
	 * Example1: nums = [2,1,1,4,3], left = 2, right = 3 
	 * Here: count of sub-array for window [2,1,1] 
	 * At element '2'     : as it is in range[2,3] so count-of-sub-arrays = 1 (current-window-size)
	 *       
	 * At element 1st '1' : count-of-sub-arrays = 1 as previous window-size is 1.
	 *    Since '1' cannot be used to start a new sub-array, so we can use '1' only to extend the
	 *    previous-sub-arrays here we have only [2] so we get it to [2,1].
	 *
	 * At element 2nd '1'  : count-of-sub-arrays = 1 as previous window-size is 1.
	 *    Since '1' cannot be used to start a new sub-array, so we can use '1' only to extend the
	 *    previous-sub-arrays here we have only [2,1] so we get it to [2,1,1]
	 *         
	 *       
	 * Example2: nums = [7,1,1,4,3], left = 2, right = 3 
	 * Here: count of sub-array for window [1,1] 
	 * At element 1st '1' : 0 , as it is starting of window itself, and is less than left-range
	 * At element 2nd '1' : 0, as previous window-size is 0.
	 * 
	 * </pre>
	 *
	 * Time Complexity: O(n)
	 * 
	 * @see StackKarumanchiQuestions#printAllPossibleSubGridsForMatrix(String[][])
	 * 
	 **/
	@Override
	public int countOfSubarraysWithBoundedMax(int[] nums, int left, int right) {

		int windowStart = -1;

		int totalSubArrayCount = 0;
		int windowSize = 0;

		for (int i = 0; i < nums.length; i++) {

			if (nums[i] > right) {
				// break-point found so sub-array calculation does not happen here
				windowStart = i;
				continue;
			}

			if (nums[i] >= left) {
				// nums[i] is in range of [left, right] both inclusive
				windowSize = (i - windowStart);
			}

			totalSubArrayCount += windowSize;
		}

		return totalSubArrayCount;
	}

	/**
	 * Space Complexity : O(1)
	 * 
	 * Strategy: swap the upper-diagonal element with lower-diagonal element.
	 * 
	 **/
	@Override
	public void inplaceTransposeOfNXNArray(int[][] matrix) {
		System.out.println("before trasnpose:");
		printMatrix(matrix);
		// vertical traversal
		for (int col = 0; col < matrix.length; col++) {
			for (int row = 0; row < col; row++) {
				// swap
				int temp = matrix[row][col];
				matrix[row][col] = matrix[col][row];
				matrix[col][row] = temp;

			}
		}
		System.out.println("post transpose:");
		printMatrix(matrix);

	}

	private void printMatrix(int[][] matrix) {
		System.out.println();
		for (int row = 0; row < matrix.length; row++) {
			System.out.print("[");
			for (int col = 0; col < matrix[0].length; col++) {
				if (col == matrix[0].length - 1) {
					System.out.print(matrix[row][col] + "]");
				} else {
					System.out.print(matrix[row][col] + ",");
				}
			}
			System.out.println();
		}

	}

	/**
	 * <pre>
	 * Inversion: two elements a[i] and a[j] form an inversion if a[i] > a[j] and i < j 
	 * 
	 * Algorithm: 
	 * input              :[2,6,4,8,10,9,15]
	 * solution sub-array :[6, 4, 8, 10, 9]
	 * 
	 * How to get StartIndex  and endIndex of solution sub-array ?
	 * 
	 * STEP1: find out the smallest inversion element in input array while scanning the
	 * array from left the right
	 * Example :Inversions in input array [2,6,4,8,10,9,15] while scanning from left to right are
	 *  (6,4)-> 4 and (10,9)-> 9. So the smallest_inversion is 4. 
	 * 
	 *STEP2: get the first insertion position of smallest_inversion in input array from left side. 
	 *  This insertion position represents the startIndex. Insertion position of 4 is index 1.
	 *     
	 *STEP3: find out the largest inversion element in input array while scanning the
	 * array from right to left
	 * Example :Inversions in input array [2,6,4,8,10,9,15] while scanning from right to left are
	 *  (9,10) ->10 and (4,6)-> 6. So the largest inversion is 10. 
	 *  
	 *STEP2: get the first insertion position of largest_invserion in input array from right side. 
	 *  This insertion position represents the endIndex.  Insertion position of 10 is index 5.
	 * 
	 *NOTE: We can get the smallest_inversion and largest_inversion in single scan as the elements 
	 *of inversion pairs are same.
	 * </pre>
	 * 
	 * @see InversionCount
	 * 
	 *      Time-Complexity : O(n)
	 */
	@Override
	public void printShortestUnsortedContiguousSubarray2(int[] nums) {

		int smallestInversion = Integer.MAX_VALUE;
		int largestInversion = Integer.MIN_VALUE;

		for (int i = 1; i < nums.length; i++) {
			// inversion pair found
			if (nums[i] < nums[i - 1]) {

				smallestInversion = Math.min(smallestInversion, nums[i]);
				largestInversion = Math.max(largestInversion, nums[i - 1]);
			}
		}

		int start = 0;
		int end = nums.length - 1;

		while (nums[start] <= smallestInversion || nums[end] >= largestInversion) {
			if (nums[start] <= smallestInversion) {
				start++;
			}

			if (nums[end] >= largestInversion) {
				end--;
			}
		}

		// print the sub-array
		for (int i = start; i <= end; i++) {
			System.out.print(nums[i] + ",");
		}
	}

	/**
	 * <pre>
	 *  
	 * Algorithm approach:
	 *
	 * STEP1: smallest_inversion and its insertion_position both are calculated
	 * simultaneously in single scan.
	 * 
	 * Le't assume smallest_inversion = last_element and try to form the valid inversion pair with
	 * other elements while scanning the input from right end.
	 *  
	 * IF (current_element, smallest_inversion) is a valid inversion :
	 *  then insertion_position of  smallest_inversion is the current index
	 * 
	 * IF (current_element, smallest_inversion) is  not valid inversion : 
	 * means our previously assumed  smallest_inversion was wrong and current_element is
	 * smaller, so let's consider the the current_element as new smallest_inversion
	 * 

	 * 
	 * STEP2: largest_inversion and its insertion_position both are calculated
	 * simultaneously in single scan.
	 * 
	 *  Le't assume largest_inversion = first_element and try to form the valid inversion pair with
	 * other elements while scanning the input from left end.
	 *
	 *IF (largest_inversion, current_element) is a valid inversion :
	 *  then insertion_position of  largest_inversion is the current index
	 * 
	 * IF (largest_inversion, current_element) is not a valid inversion :
	 * means our previously assumed  largest_inversion was wrong and current_element is
	 * greater, so let's consider the the current_element as new largest_inversion
	 * </pre>
	 * 
	 * Time-Complexity : O(n)
	 */
	@Override
	public void printShortestUnsortedContiguousSubarray3(int[] nums) {

		// since insertion_position for smallest_inversion will lie at left side so
		// we need to scan the array from end.
		int smallestInversion = nums[nums.length - 1];
		int start = 0;
		for (int i = nums.length - 2; i >= 0; i--) {
			// we are able to form the inversion with current_element
			if (nums[i] > smallestInversion) {
				start = i;
			} else {
				smallestInversion = nums[i];
			}
		}

		int largestInversion = nums[0];
		int end = nums.length - 1;
		for (int i = 1; i < nums.length; i++) {

			// we are able to form the inversion with current_element
			if (largestInversion > nums[i]) {
				end = i;
			} else {
				largestInversion = nums[i];
			}
		}

		// print the sub-array
		for (int i = start; i <= end; i++) {
			System.out.print(nums[i] + ",");
		}

	}

	@Override
	public void inplaceRotateMatrixBy90DegreeClockwise(int[][] matrix) {

		inplaceTransposeOfNXNArray(matrix);

		// reverse each row of transposed matrix
		for (int i = 0; i < matrix.length; i++) {
			reverseIntArrary(matrix[i]);
		}

		System.out.println("90 degree rotation matrix");
		printMatrix(matrix);

	}

	private void reverseIntArrary(int[] input) {
		// input.length - 2, avoid self-swap if any.
		for (int i = 0; i <= (input.length - 2) >> 1; i++) {
			int temp = input[i];
			input[i] = input[input.length - 1 - i];
			input[input.length - 1 - i] = temp;
		}

	}

	@Override
	public void reverseVowelOfAString(String inputStr) {
		System.out.println("input:" + inputStr);
		char[] input = inputStr.toCharArray();
		int l = 0;
		int r = input.length - 1;
		while (l < r) {
			boolean lVowel = isVowel(input[l]);
			boolean rVowel = isVowel(input[r]);
			if (lVowel && rVowel) {
				swap(input, l, r);
				l++;
				r--;
				continue;
			}
			if (!lVowel) {
				l++;
			}
			if (!rVowel) {
				r--;
			}
		}

		System.out.println("result:" + String.valueOf(input));

	}

	private boolean isVowel(char c) {
		return (c == 'a' || c == 'A' || c == 'e' || c == 'E' || c == 'i' || c == 'I' || c == 'o' || c == 'O' || c == 'u'
				|| c == 'U');

	}

	private void swap(char[] input, int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;

	}

	/**
	 * 'Manhattan Distance', where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
	 * 
	 * Note : For any point in matrix row value represents X coordinate and column
	 * value represents y coordinate.
	 * 
	 * @see BestMeetingPoint.pdf
	 */
	@Override
	public int getTotalDistanceTravelledToReachBestMeetingPoint(int[][] friends) {

		List<Integer> xCooridanteList = new ArrayList<>();
		List<Integer> yCooridanteList = new ArrayList<>();

		// get all the xCoordinates in sorted order
		for (int row = 0; row < friends.length; row++) {
			for (int col = 0; col < friends[0].length; col++) {
				if (friends[row][col] == 1) {
					xCooridanteList.add(row);
				}

			}

		}

		// get all the yCoordinates in sorted order
		for (int col = 0; col < friends[0].length; col++) {
			for (int row = 0; row < friends.length; row++) {
				if (friends[row][col] == 1) {
					yCooridanteList.add(col);
				}

			}

		}

		int medianXcoordinate = xCooridanteList.get(xCooridanteList.size() / 2);
		int medianYcoordinate = yCooridanteList.get(yCooridanteList.size() / 2);

		System.out.println("best meeting point:(" + medianXcoordinate + "," + medianYcoordinate + ")");

		// Manhattan distance:
		int totalDistance = 0;
		for (int x : xCooridanteList) {
			totalDistance += Math.abs(x - medianXcoordinate);
		}

		for (int y : yCooridanteList) {
			totalDistance += Math.abs(y - medianYcoordinate);
		}

		return totalDistance;
	}

	@Override
	public String getSummationOfTwoStrings(String str1, String str2) {

		String output = "";
		int i = str1.length() - 1;
		int j = str2.length() - 1;

		int carry = 0;

		while (i >= 0 || j >= 0 || carry != 0) {

			int digit1 = i >= 0 ? str1.charAt(i) - '0' : 0;
			int digit2 = j >= 0 ? str2.charAt(j) - '0' : 0;

			int sum = digit1 + digit2 + carry;

			int outputDigit = sum % 10;
			output = outputDigit + output;
			carry = sum / 10;

			i--;
			j--;
		}

		return output;
	}

	/**
	 * 
	 * product can have at max (str1.length + str2.length) digits.
	 * 
	 */
	@Override
	public String getProductOfTwoString(String operand1Str, String operand2Str) {

		int[] productHolder = new int[operand1Str.length() + operand2Str.length()];

		int op2Idx = operand2Str.length() - 1;

		while (op2Idx >= 0) {

			int op2 = operand2Str.charAt(op2Idx) - '0';

			int carry = 0;
			int op1Idx = operand1Str.length() - 1;
			int productIdx = operand1Str.length() + op2Idx;

			while (op1Idx >= 0 || carry != 0) {

				int op1 = op1Idx >= 0 ? operand1Str.charAt(op1Idx) - '0' : 0;

				int mult = productHolder[productIdx] + carry + op1 * op2;
				productHolder[productIdx] = mult % 10;
				carry = mult / 10;

				op1Idx--;
				productIdx--;
			}

			op2Idx--;
		}

		String result = Arrays.stream(productHolder).dropWhile(e -> e == 0).mapToObj(String::valueOf)
				.collect(Collectors.joining());

		if (result.isEmpty()) {
			result = result + "0";
		}

		return result;

	}

	/***
	 * <pre>
	 * To partition the array by even odd, we need to pick some partitioning algorithm.
	 * 
	 * Algorithm Strategy: 
	 * - we need to maintain odd elements window.
	 * CASE A: while moving the window ahead if next element is odd, 
	 *   then we will increase the size of odd-window by 1.
	 * CASE B: while moving the window ahead if the next element is even,
	 *   then we will swap the first element of odd-window with the 'even-element'.
	 *   This swap operation will not affect the size of window. Just the odd-window
	 *   will move one position ahead.
	 * 
	 * </pre>
	 */
	@Override
	public void sortArrayByParity(int[] input) {

		int oddWindowStartIdx = 0;
		int oddWindowEndIdx = 0;

		while (oddWindowEndIdx < input.length) {

			if (BitwiseUtils.isEven(input[oddWindowEndIdx])) {

				if (oddWindowStartIdx < oddWindowEndIdx) {
					swap(input, oddWindowStartIdx, oddWindowEndIdx);
				}

				oddWindowStartIdx++;
				oddWindowEndIdx++;
			} else {

				oddWindowEndIdx++;

			}
		}

		System.out.println(Arrays.toString(input));
	}

	/**
	 * <pre>
	 * If we sort the intervals on startPoint then next interval will fall into any of the 3 cases:
	 * 1. non-intersecting interval:
	 *   startPoint of next interval is greater/equal to endPoint of 1st interval
	 * 2. partially intersecting interval:
	 * 3. next interval lies completely within the first interval
	 * 
	 * </pre>
	 */
	@Override
	public void printMergableIntervals(int[][] intervals) {
		printMatrix(intervals);
		Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));

		List<int[]> mergedIntervals = new ArrayList<>();
		mergedIntervals.add(intervals[0]);

		for (int i = 1; i < intervals.length; i++) {

			int[] tailInterval = mergedIntervals.get(mergedIntervals.size() - 1);
			int[] nextInterval = intervals[i];
			// case: next-interval-start is greater/equal to previous-interval-end
			if (nextInterval[0] >= tailInterval[1]) {
				mergedIntervals.add(nextInterval);
			} else {
				tailInterval[1] = Math.max(tailInterval[1], nextInterval[1]);

			}
		}
		System.out.print("Result: ");
		for (int[] x : mergedIntervals) {
			System.out.print(Arrays.toString(x) + ", ");
		}
		System.out.println("\n-------------");

	}

	/**
	 * 
	 * To find all the prime numbers less than or equal to a given integer n by
	 * Eratosthenes' method:
	 * 
	 * 1. Create a list of consecutive integers from 2 through n: (2, 3, 4, ..., n).
	 * 
	 * 2. Initially, let p equal 2, the smallest prime number.
	 *
	 * 3.Enumerate the multiples of p by counting in increments of p from 2p to n,
	 * and mark them in the list (these will be 2p, 3p, 4p, ...; the p itself should
	 * not be marked).
	 * 
	 * 4.Find the smallest number in the list greater than p that is not marked. If
	 * there was no such number, stop. Otherwise, let p now equal this new number
	 * (which is the next prime), and repeat from step 3.
	 * 
	 * 5.When the algorithm terminates, the numbers remaining not marked in the list
	 * are all the primes below n.
	 * 
	 * Note: As a refinement, it is sufficient to mark the numbers in step 3
	 * starting from p^2, as all the smaller multiples of p will have already been
	 * marked at that point.
	 * 
	 * <pre>
	 * How to find if a number is prime or not ?
	 * --If a number is completely divisible by any other number (except of 1 and self),
	 *  then such a number is composite-number not a prime.
	 * 
	 * Why do we need to just iterate till root(n)?
	 * 
	 * Since any composite number can be represented by p*q = composite_number, 
	 * Let's assume p >= root(number);   
	 *    then q must be  <= root(number) and the number will be completely divisible by 'q'. 
	 *    
	 * So we just need to iterate the loop from 2 till q(which is <= root(number)) for primality test.
	 * 
	 *
	 * 
	 * Time complexity: outer for loop iterates  2 to root(n); inner loop only iterates for prime number.
	 * inner loop for 2 = n/2 (number  of multiples of 2)
	 *                3 = n/3
	 *                5 = n/5
	 *                7 = n/7
	 *                -------
	 *                n = n/root(n)
	 *                
	 *  n/2 + n/3 + n/5 + n/7 + ...+ n/root(n)   
	 *  =n*(1/2 + 1/3 + 1/5 + 1/7 + ...+ 1/root(n)) = n*loglogn; 
	 *  summation of inverse of prime Number till root(n) = loglogn
	 *                       
	 * Time Complexity :  count of non-prime +  n*loglogn                   
	 *                 : n*loglogn
	 * </pre>
	 * 
	 * CONCLUSION STATEMENT: If we have given any number n. Now if we start placing
	 * multiple of all the prime numbers from 2 to root(n); on number line till n,
	 * then we will have only space left for un-discovered prime numbers.
	 * 
	 * In other words,multiples of consecutive prime numbers forms the natural
	 * number sequence.
	 * 
	 * Implementation Strategy:
	 * 
	 * Step1: we will generate all the prime numbers between 2 to root(n)
	 * implicitly.
	 * 
	 * Step2: we will mark all the multiples of prime number as non-prime in given
	 * range.
	 * 
	 **/
	@Override
	public int[] getSieveOfEratosthenes(int n) {

		// TODO: can be optimized if we consider prime as false and non-prime as true.
		// Here we consider prime as true and non-prime as false. Create the sieve of
		// Eratosthenes, and mark all as prime initially.
		n = n + 1;
		BitSet sieve = new BitSet(n);
		sieve.set(2, n, true);

		// loop iterates till root(n)
		for (int p = 2; p * p <= n; p++) {

			// if a number is marked prime
			// at p==2, all the even numbers greater than 2 are getting unmarked.
			if (sieve.get(p)) {
				// mark the multiple of p as non-prime starting from 2p, 3p, 4p 5p... till n
				// as per optimization j starts with p^2 instead of 2p.
				for (int j = p * p; j <= n; j = j + p) {
					sieve.clear(j);
				}
			}

		}

		return sieve.stream().toArray();

	}

	/**
	 * 
	 * Algorithm strategy will be based on following statement:
	 * 
	 * CONCLUSION STATEMENT: If we have given any number n. Now if we start placing
	 * multiple of all the prime numbers from 2 to root(n); on number line till n,
	 * then we will have only space left for un-discovered prime numbers.
	 *
	 * Implementation Strategy:
	 *
	 * Step1: we will generate all the prime numbers between 2 to root(n)
	 * explicitly.
	 * 
	 * Step2: we will mark all the multiples of prime number as non-prime in given
	 * range.
	 * 
	 */
	@Override
	public void printSieveOfEratosthenesInRange(int start, int end) {

		int[] primesTillrootOfEnd = getSieveOfEratosthenes((int) Math.sqrt(end));

		System.out.println("primesTillrootOfEnd:" + Arrays.toString(primesTillrootOfEnd));
		// here false means prime; and true means non-prime
		final int range = (end - start) + 1;
		BitSet sieve = new BitSet(range);

		for (int i = 0; i < primesTillrootOfEnd.length; i++) {
			int prime = primesTillrootOfEnd[i];

			// calculate the first multiple of prime in range start - end.
			int kthFactor = start / prime;
			if (start % prime != 0) {
				kthFactor += 1;
			}

			int firstMultiple = kthFactor * prime;

			int index = firstMultiple - start;
			// if firstMutiple itself is prime, then escape it
			// and go to next multiple of prime
			if (firstMultiple == prime)
				index += prime;

			while (index < range) {
				sieve.set(index);
				index = index + prime;
			}

		}
		sieve.flip(0, range);
		int[] result = sieve.stream().map(i -> start + i).toArray();
		System.out.println(Arrays.toString(result));
	}

	/**
	 * <pre>
	 * num1 = a + b*i
	 * num2 = c + d*i
	 * 
	 * num1* num2 = (a + b*i) + (c + d*i)  = a*c +a*d*i + b*i*c + b*i*d*i
	 *            = (a*c + b*d*i*i) + (a*d + b*c)*i 
	 *  
	 *  num1* num2 = (a*c - b*d) + (a*d + b*c)*i
	 * 
	 * </pre>
	 */
	@Override
	public void printProductOfTwoComplexNumber(String num1, String num2) {
		String[] num1Tokens = null;
		if (num1.contains("+")) {
			num1Tokens = num1.split(Pattern.quote("+"));
		} else {
			num1Tokens = num1.split(Pattern.quote("-"));
		}

		String[] num2Tokens = null;
		if (num2.contains("+")) {
			num2Tokens = num2.split(Pattern.quote("+"));
		} else {
			num2Tokens = num2.split(Pattern.quote("-"));
		}

		int a = Integer.valueOf(num1Tokens[0].trim());
		int c = Integer.valueOf(num2Tokens[0].trim());

		int b = Integer.valueOf(num1Tokens[1].replace("i", "").trim());
		int d = Integer.valueOf(num2Tokens[1].replace("i", "").trim());

		int realPart = (a * c - b * d);
		int imaginaryPart = (a * d + b * c);

		System.out.println(realPart + " + " + imaginaryPart + "i");
	}

	@Override
	public boolean isValidPalindromeByIgnoringAtMostOneChar(String input) {
		int l = 0;
		int r = input.length() - 1;
		while (l < r) {
			if (input.charAt(l) != input.charAt(r)) {
				return isPalindrome(input, l + 1, r) || isPalindrome(input, l, r - 1);
			}
			l++;
			r--;
		}

		return true;
	}

	private boolean isPalindrome(String input, int l, int r) {
		while (l < r) {
			if (input.charAt(l) != input.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}

	/**
	 * 
	 * Since cars need to be placed on their respective positions(like on
	 * number-line), so cars need to be sorted by their positions.
	 * 
	 * 
	 */
	@Override
	public int calulateCarFleets(int target, int[] position, int[] speed) {
		//

		final record Car(int position, double timeToTarget) {
		}
		;

		Car[] carArray = new Car[position.length];

		for (int i = 0; i < position.length; i++) {
			carArray[i] = new Car(position[i], ((double) target - position[i]) / speed[i]);

		}

		Arrays.sort(carArray, Comparator.comparing(Car::position));

		// consider last car as a fleet.
		int carFleetCount = 1;
		for (int i = carArray.length - 1; i > 0; i--) {

			if (carArray[i - 1].timeToTarget <= carArray[i].timeToTarget) {
				// post collision, faster car i.e. (i-1)th car will be represented by slower car
				// i.e. ith car
				carArray[i - 1] = carArray[i];
			} else {
				carFleetCount++;
			}

		}

		System.out.println(carFleetCount);
		return carFleetCount;
	}

	/**
	 * example "889977665544337772"
	 * 
	 * <pre>
	 * Algo strategy: 
	 * 
	 * nonSwappableWindow : contiguous elements greater than the max-element 
	 * seen so far. 
	 * 
	 * swappableWindow : contiguous elements smaller than the max-element 
	 * seen so far. 
	 * 
	 * max candidate element =  maximum element from the previous nonSwappableWindow
	 * 
	 * 
	 * We will scan the elements from end of array:
	 * 
	 * CASE A: If we encounter nonSwappableWindow: 
	 *    --keep on updating the nonSwappableWindowMax and  nonSwappableWindowMaxIdx.
	 *CASE B: If we encounter  swappableWindow :
	 *    -- mark the swappable window start with current position.
	 *    -- mark the  maxCandidateIdx with previous  nonSwappableWindowMaxIdx.
	 * 
	 * </pre>
	 * 
	 * 
	 */
	@Override
	public void maxNumberFromSingleSwap(String num) {

		char[] input = num.toCharArray();

		int swappableWindowStartIdx = -1;

		int nonSwappableWindowMax = Integer.MIN_VALUE;
		int nonSwappableWindowMaxIdx = -1;

		int maxCandidateIdx = -1;

		for (int i = input.length - 1; i >= 0; i--) {
			// 9987654372
			int current = (input[i] - '0');

			if (current > nonSwappableWindowMax) {
				nonSwappableWindowMax = current;
				nonSwappableWindowMaxIdx = i;
			} else if (current < nonSwappableWindowMax) {

				swappableWindowStartIdx = i;
				// updating time-and-again but it is impact less.
				maxCandidateIdx = nonSwappableWindowMaxIdx;
			}
		}

		if (swappableWindowStartIdx != -1 && maxCandidateIdx != -1)
			swap(input, swappableWindowStartIdx, maxCandidateIdx);

		System.out.println(String.valueOf(input));

	}

	/**
	 * <pre>
	 * Algo strategy: 
	 * 
	 * 
	 * Step1: Create a Pointer record to hold the list-index, num-index and num.
	 * Step2: Create the list of size k to hold the pointers to prepare the range.
	 * 
	 * Step3: Initialize the rangePointers for comparison:
	 * ---pick first elements from each k list.
	 * ---find max and min element and create the maxPointer and minPointer
	 * ---calculate the range gap = (max - min).
	 *  
	 * Step4: pick the next element from minPointer list and repeat the Step3 to compare with 
	 * previously calculated gap.
	 * 
	 * 
	 * Time Complexity: 
	 * 
	 * -- Time taken to calculate the minPointer and maxPointer is  K 
	 *     as length of rangePointerList is K.
	 * 
	 * -- Lets assume total count of elements are n(including all the lists) 
	 * 
	 * -- Since we are calculating the minPointer and maxPointer for almost all the elements, so:
	 * 
	 *   Time Complexity : n * k
	 * 
	 * 
	 * </pre>
	 * 
	 **/
	@Override
	public int[] smallestRangeCoveringElementsOfKLists1(List<List<Integer>> nums) {

		final record Pointer(int listIdx, int numIdx, int num) {
		}

		List<Pointer> rangePointers = new ArrayList<>();

		// insert the 0th element of each list to rangePointers
		for (int i = 0; i < nums.size(); i++) {
			rangePointers.add(new Pointer(i, 0, nums.get(i).get(0)));
		}

		int gap = Integer.MAX_VALUE;
		int minGapRange[] = new int[2];

		// this loop iterates for almost all the elements
		while (true) {

			Pointer minPointer = Collections.min(rangePointers, Comparator.comparing(Pointer::num));
			Pointer maxPointer = Collections.max(rangePointers, Comparator.comparing(Pointer::num));

			int currentGap = maxPointer.num - minPointer.num;
			if (currentGap < gap) {
				gap = currentGap;
				minGapRange[0] = minPointer.num;
				minGapRange[1] = maxPointer.num;
			}

			List<Integer> moveAheadList = nums.get(minPointer.listIdx);
			// next element in moveAheadList is out of bound then break the loop.
			if (minPointer.numIdx + 1 == moveAheadList.size()) {
				break;
			}

			Pointer nextPointer = new Pointer(minPointer.listIdx, minPointer.numIdx + 1,
					moveAheadList.get(minPointer.numIdx + 1));
			rangePointers.set(minPointer.listIdx, nextPointer);

		}

		System.out.println(Arrays.toString(minGapRange));
		return minGapRange;
	}

	/**
	 * <pre>
	 * Time Complexity:
	 * 
	 * min calculation: since priority queue is used and offer takes logk time.
	 *                : O(logK) 
	 * 
	 * max calculation: constant time as it is piggy-backed on element iteration.
	 * 
	 * element iteration : O(n) 
	 * 
	 * 
	 * Total time Complexity: O(n*logK)
	 * </pre>
	 * 
	 */
	@Override
	public int[] smallestRangeCoveringElementsOfKLists2(List<List<Integer>> nums) {

		final record Pointer(int listIdx, int numIdx, int num) {
		}

		Queue<Pointer> rangePointerQueue = new PriorityQueue<>(Comparator.comparing(Pointer::num));

		Pointer maxPointer = null;

		// insert the 0th element of each list to rangePointers
		for (int i = 0; i < nums.size(); i++) {
			Pointer elementPointer = new Pointer(i, 0, nums.get(i).get(0));
			rangePointerQueue.offer(elementPointer);

			if (maxPointer == null || elementPointer.num > maxPointer.num) {
				maxPointer = elementPointer;
			}
		}

		int gap = Integer.MAX_VALUE;
		int minGapRange[] = new int[2];

		// this loop iterates for almost all the elements
		while (true) {

			// poll is constant time operation of priority-queue
			Pointer minPointer = rangePointerQueue.poll();

			int currentGap = maxPointer.num - minPointer.num;
			if (currentGap < gap) {
				gap = currentGap;
				minGapRange[0] = minPointer.num;
				minGapRange[1] = maxPointer.num;
			}

			List<Integer> moveAheadList = nums.get(minPointer.listIdx);
			// next element in moveAheadList is out of bound then break the loop.
			if (minPointer.numIdx + 1 == moveAheadList.size()) {
				break;
			}

			Pointer nextPointer = new Pointer(minPointer.listIdx, minPointer.numIdx + 1,
					moveAheadList.get(minPointer.numIdx + 1));

			if (nextPointer.num > maxPointer.num) {
				maxPointer = nextPointer;
			}

			rangePointerQueue.offer(nextPointer);

		}

		System.out.println(Arrays.toString(minGapRange));
		return minGapRange;
	}

	/**
	 * <pre>
	 * Algo Strategy:
	 * 
	 * Which all elements should be consider for equal row ?
	 * 
	 * Any domino top-bottom pair can be consider for equal row.
	 * 
	 * Total possibilities for equal row :
	 * 
	 * On top 2 possibilities: 
	 *       -- domino-top itself
	 *       -- domino bottom
	 * 
	 *On bottom 2 possibilities: 
	 *       -- domino-bottom itself
	 *       -- domino top
	 * </pre>
	 * 
	 * Time Complexity: O(n)
	 * 
	 */
	@Override
	public int minDominoRotationsForEqualRow1(int[] tops, int[] bottoms) {

		// assign num1 or num2 with any domino pair.
		int num1 = tops[0];
		int num2 = bottoms[0];

		int case1SwapCount = 0;
		int case2SwapCount = 0;

		int case3SwapCount = 0;
		int case4SwapCount = 0;

		for (int i = 0; i < tops.length; i++) {

			// CASE1: num1 stays at top
			if (case1SwapCount != -1) {

				if (tops[i] != num1 && bottoms[i] != num1) {
					case1SwapCount = -1;

				} else if (tops[i] != num1 && bottoms[i] == num1) {
					case1SwapCount++;
				}
			}

			// CASE2: num1 stays at bottom
			if (case2SwapCount != -1) {

				if (tops[i] != num1 && bottoms[i] != num1) {
					case2SwapCount = -1;

				} else if (bottoms[i] != num1 && tops[i] == num1) {
					case2SwapCount++;
				}
			}

			// CASE3: num2 stays at bottom
			if (case3SwapCount != -1) {

				if (tops[i] != num2 && bottoms[i] != num2) {
					case3SwapCount = -1;

				} else if (bottoms[i] != num2 && tops[i] == num2) {
					case3SwapCount++;
				}
			}

			// CASE4: num2 stays at top
			if (case4SwapCount != -1) {

				if (tops[i] != num2 && bottoms[i] != num2) {
					case4SwapCount = -1;

				} else if (tops[i] != num2 && bottoms[i] == num2) {
					case4SwapCount++;
				}
			}

		}

		return IntStream.of(case1SwapCount, case2SwapCount, case3SwapCount, case4SwapCount).filter(i -> i != -1).min()
				.orElseGet(() -> -1);

	}

	/**
	 * <pre>
	 * 
	 * 
	 * OBSERVATION_1: At max 2 element domino can exits because any one of the element
	 *  of top-bottom pair has to be in domino formation.
	 * 
	 * OBSERVATION_2 : If both tops and  bottoms are already domino(means equal row) 
	 *  then there will be 0 swaps and two domino solutions.
	 *       Example: tops = [2,2,2,2,2,2]  bottoms = [1,1,1,1,1,1]
	 *       
	 *OBSERVATION_3 : In all other cases there can only be two possibilities:
	 *  -- ZERO solution exists
	 *      
	 *      Example: tops = [2,3,4,2,2,2]  bottoms = [1,1,5,1,1,1]
	 *      
	 *  -- or  SINGLE element domino/TWO element domino can exist.
	 *  
	 *      Example of Single element domino: tops = [2,1,2,4,2,2]  bottoms =[5,2,6,2,3,2]
	 *       domino1: tops[2,2,2,2,2,2]  bottoms[5,1,6,4,3,2]  : total swaps = 2
	 *       domino2: tops[5,1,6,4,3,2]  bottoms[2,2,2,2,2,2]  : total swaps = 3
	 *       
	 *      Example of two element domino: tops = [3,4,3,4,3,4]  bottoms =[4,3,4,3,4,3] 
	 *       domino1: tops[3,3,3,3,3,3]  bottoms[4,4,4,4,4,4]  : total swaps = 3
	 *       domino2: tops[4,4,4,4,4,4]  bottoms[3,3,3,3,3,3]  : total swaps = 3
	 *       
	 * 
	 * Alog strategy: 
	 * 
	 * STEP1: We need to find out which element is forming domino ?
	 * 
	 *  IF(top_count(i)  + bottom_count(i) - sameTopBottomCount(i) == array_size)  
	 *     ==> means ith element can form domino
	 *                                                               
	 *   NOTE: When 2 distinct elements are forming domino then swap counts are same 
	 *   for both the elements. Since we need minimum swap domino so we can pick either of the solution element.
	 *  
	 *   
	 *STEP2: need to count minimum swaps
	 *   
	 *       array_size -  Math.Max(top_count(i) - bottom_count(i))
	 * 
	 * </pre>
	 * 
	 * Time Complexity: O(n)
	 */
	@Override
	public int minDominoRotationsForEqualRow2(int[] tops, int[] bottoms) {

		final int MAX_VALUE_OF_DOMINO = 6;

		int[] topCount = new int[MAX_VALUE_OF_DOMINO + 1];
		int[] bottomCount = new int[MAX_VALUE_OF_DOMINO + 1];
		int[] sameTopBottomCount = new int[MAX_VALUE_OF_DOMINO + 1];

		// prepare the frequency table
		for (int i = 0; i < tops.length; i++) {

			topCount[tops[i]]++;

			bottomCount[bottoms[i]]++;

			if (tops[i] == bottoms[i]) {
				sameTopBottomCount[tops[i]]++;
			}
		}

		int minSwaps = -1;
		for (int i = 1; i < MAX_VALUE_OF_DOMINO + 1; i++) {

			// STEP1 which element is forming domino
			if (topCount[i] + bottomCount[i] - sameTopBottomCount[i] == tops.length) {
				// STEP2: need to count minimum swaps
				minSwaps = tops.length - Math.max(topCount[i], bottomCount[i]);
			}
		}
		return minSwaps;
	}

}
