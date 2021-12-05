package edu.algo.recursion;

import java.util.Arrays;

import edu.algo.algebra.BinaryExponentiation;
import edu.algo.permcomb.CombinationQuestion;
import edu.algo.permcomb.DoubleCountingQuestion;
import edu.algo.permcomb.DoubleCountingSolution;

public class RecursionSolutionL1 implements RecursionQuestionL1 {

	private static final RecursionQuestionL1 INSTANCE = new RecursionSolutionL1();

	public static void main(String[] args) {
		// INSTANCE.printDecreasingIncreasing(5);

		// INSTANCE.factorial(5);

		// INSTANCE.eulerTourOfRecursion(5);

		// INSTANCE.printAToThePowerOfP(2, 5);

		// INSTANCE.printTowerOfHanoiSteps(3);

		// INSTANCE.printElementsOfArray(new int[] { 1, 2, 3, 4, 5 });

		// INSTANCE.printElementsOfArrayInReverse(new int[] { 1, 2, 3, 4, 5 });

		INSTANCE.printMaxElementOfArray(new int[] { 1, 7, 3, 4, 5 });

		INSTANCE.printFirstIndexOfOccurrence(new int[] { 1, 7, 7, 3, 4, 5, 7, 3 }, 7);

		INSTANCE.printLastIndexOfOccurrence(new int[] { 1, 7, 7, 3, 4, 5, 7, 3 }, 7);

		INSTANCE.printAllIndexOfOccurrence(new int[] { 1, 7, 7, 3, 4, 5, 7, 3 }, 7);
	}

	/**
	 * <pre>
	 * Print decreasing sequence then increasing sequence for a given number n
	 * 
	 * e.g. n=4 ; 4 3 2 1 1 2 3 4
	 * 
	 * Hypothesis   : 	printDecreasingIncreasing(n=4)  : 4 3 2 1 1 2 3 4
	 * 
	 * Substitution : 	printDecreasingIncreasing(n=3)   : 3 2 1 1 2 3
	 * 
	 * Induction    :   4  printDecreasingIncreasing(3)  4
	 *              :   n  printDecreasingIncreasing(n-1)  n
	 * </pre>
	 * 
	 */

	@Override
	public void printDecreasingIncreasing(int n) {
		if (n == 0)
			return;
		System.out.print(n + " ");
		printDecreasingIncreasing(n - 1);
		System.out.print(n + " ");
	}

	/**
	 * Calculate a^p using recursion.
	 * 
	 */

	/**
	 * Print output following the Euler tour.
	 * 
	 */
	public void eulerTourOfRecursion(int num) {
		if (num == 0)
			return;

		System.out.println("before br1:" + num);

		// this invocation represents branch1 in recursion tree
		eulerTourOfRecursion(num - 1);

		System.out.println("between br1 and br2:" + num);

		// this invocation represents branch1 in recursion tree
		eulerTourOfRecursion(num - 1);

		System.out.println("after br2:" + num);
	}

	@Override
	public int factorial(int n) {
		if (n < 1)
			return 1;
		else {
			int factorial = n * factorial(n - 1);
			System.out.println(n + "! = " + factorial);
			return factorial;
		}
	}

	@Override
	public void printAToThePowerOfP(int a, int p) {
		System.out.println(BinaryExponentiation.binaryPowerRecursive(a, p));

	}

	@Override
	public void printTowerOfHanoiSteps(int n) {
		toh(n, "T01", "T02", "T03");

	}

	/**
	 * <pre>
	 *
	 * Recursion branch algorithm is based on converting the problem into TWO_DISK_PROCESS
	 * 
	 * How to convert the problem in two disk process ?
	 * Disk_2 : bottom most disk on src_tower can be assumed as disk_2
	 * Disk_1:  All other remaing disks on src_tower can be assumed as  disk_1.
	 * 
	 * Base : we can have two choices for Base Case :
	 *  CHOICE 1 : when n==0; just return
	 *           or
	 * CHOICE 2 : when we have n==1 i.e. single disk, we can directly move the disk from src to des tower.
	 *  
	 *
	 * Hypothesis : toh(4,S,D,H) => can move 4 disks from src_tower to des_tower
	 * using helper_tower
	 * INPUT_STATE : src_tower{DSK4, DSK3, DSK2, DSK1}; des_tower{}; helper_tower{} 
	 * OUTPUT_STATE : src_tower{}; des_tower{DSK4, DSK3, DSK2, DSK1}; helper_tower{}
	 *  
	 * Substitution : toh(3,S,H,D) => can move 3 disks(DSK3, DSK2, DSK1) from 
	 * src_tower to helper_tower using des_tower as auxillary tower
	 * INPUT_STATE  :  src_tower{DSK4, DSK3, DSK2, DSK1}; des_tower{}; helper_tower{} 
	 * OUTPUT_STATE :  src_tower{DSK4}; des_tower{}; helper_tower{DSK3, DSK2, DSK1}
	 * 
	 * Induction :  
	 * 1. move DSK4 from src_tower to  des_tower
	 * STATE : src_tower{}; des_tower{DSK4}; helper_tower{DSK3, DSK2, DSK1}
	 * 2. call the toh(3,H,D,S) : to move 3 disks from helper_tower to des_tower using
	 *  src_tower as auxillary tower.
	 * STATE : src_tower{}; des_tower{DSK4, DSK3, DSK2, DSK1}; helper_tower{}
	 * </pre>
	 */
	private void toh(int diskNum, String src_tower, String des_tower, String helper_tower) {
		if (diskNum == 0) {
			return;
		}
		toh(diskNum - 1, src_tower, helper_tower, des_tower);
		System.out.println(src_tower + " --> " + "DSK" + diskNum + " --> " + des_tower);
		toh(diskNum - 1, helper_tower, des_tower, src_tower);
	}

	@Override
	public void printElementsOfArray(int[] array) {
		printElementsOfArray(array, 0);
	}

	private void printElementsOfArray(int[] array, int idx) {
		if (idx == array.length)
			return;
		System.out.println(array[idx]);
		printElementsOfArray(array, idx + 1);
	}

	@Override
	public void printElementsOfArrayInReverse(int[] array) {
		printElementsOfArrayInReverse(array, array.length - 1);
	}

	private void printElementsOfArrayInReverse(int[] array, int idx) {
		if (idx == -1)
			return;
		System.out.println(array[idx]);
		printElementsOfArrayInReverse(array, idx - 1);
	}

	@Override
	public void printMaxElementOfArray(int[] array) {
		System.out.println(getMaxElementOfArray(array, 0));
	}

	/**
	 * <pre>
	 * 
	 * Assumption: Since 'max' operation needs two operands, so
	 * 1st operand :0th index element
	 * 2nd opreand :we will collate all remaining elements as 2nd operand
	 * 
	 * Hypothesis : getMaxElementOfArray([2,6,4,7,8,9,1,2]) ; gives max among all 8 elements
	 * 
	 * Substitution : getMaxElementOfArray([6,4,7,8,9,1,2]) ; gives max among remaiang 7 elements
	 *              : substituition represents collated operand 
	 *              
	 * Induction :   Math.max(2,getMaxElementOfArray([6,4,7,8,9,1,2]))
	 * 
	 * </pre>
	 */
	private int getMaxElementOfArray(int[] array, int idx) {
		if (idx == (array.length - 1)) {
			return array[idx];
		}

		return Math.max(array[idx], getMaxElementOfArray(array, idx + 1));
	}

	@Override
	public void printFirstIndexOfOccurrence(int[] array, int data) {

		System.out.println(getFirstOccurrenceIndex(array, data, 0));

	}

	/**
	 * <pre>
	 *  1st operand :0th index element
	 *  2nd opreand :we will collate all remaining elements as 2nd operand
	 *  
	 *  Element to be serached : data
	 *  
	 *  Comparisons required to test the first occurrence
	 *  
	 *  1.  IF data is eqaul to 1st_operand
	 *  
	 *  2.  ELSE data is not equal to 1st_operand
	 *        2A: check if index presented by 2nd_operand is +Ve
	 *        2B: or index presented by 2nd_operand is -ve
	 * </pre>
	 * 
	 */
	private int getFirstOccurrenceIndex(int[] array, int data, int idx) {
		// base case
		if (array.length == idx) {
			return -1;
		}

		// comparision with 1st operand
		if (array[idx] == data) {
			return idx;
		}
		return getFirstOccurrenceIndex(array, data, idx + 1);
	}

	@Override
	public void printLastIndexOfOccurrence(int[] array, int data) {
		System.out.println(getLastOccurrence(array, data, array.length - 1));

	}

	/**
	 * <pre>
	 *  1st operand :last element of array
	 *  2nd opreand :we will collate all remaining from 0th-index to 2nd-last-index as 2nd operand
	 *  
	 *  Element to be serached : data
	 *  
	 *  Comparisons required to test the first occurrence
	 *  
	 *  1.  IF data is eqaul to 1st_operand
	 *  
	 *  2.  ELSE data is not equal to 1st_operand
	 *        2A: check if index presented by 2nd_operand is +Ve
	 *        2B: or index presented by 2nd_operand is -ve
	 * </pre>
	 * 
	 */
	private int getLastOccurrence(int[] array, int data, int endIdx) {

		if (endIdx == -1) {
			return -1;
		}

		// comparison with 1st operand.
		if (array[endIdx] == data) {
			return endIdx;
		}
		return getLastOccurrence(array, data, endIdx - 1);

	}

	@Override
	public void printAllIndexOfOccurrence(int[] array, int data) {
		System.out.println(Arrays.toString(getOccurrenceArray(array, data, 0, 0)));
	}

	/**
	 * <pre>
	 * Strategy : Count the occurrence of given data in forward trip, 
	 *            create the solution array in base-case 
	 *            and fill the solution aray in return trip.
	 *            	
	 * Hypothesis: getOccurrenceArray(array, idx=0) : returns solution-array containing occurrence index
	 * Substitution : getOccurrenceArray(array, idx=1) : returns solution-array filled from right-to-left,
	 *                when first_operand is equal to data then solution array will have vacant space 
	 *                towrads left.
	 *   
	 * Induction: if data is equal to first_operand, place the index of first_operand in solution array
	 *            at (occrrenceCount -1)th index 
	 *            else return the same solution arrray.
	 * 
	 * </pre>
	 * 
	 */
	private int[] getOccurrenceArray(int[] array, int data, int idx, int occurrenceCount) {

		if (idx == array.length) {
			return new int[occurrenceCount];
		}

		// within the check we are doing both forward and return trip.
		if (data == array[idx]) {

			int[] solArr = getOccurrenceArray(array, data, idx + 1, occurrenceCount - 1);
			solArr[occurrenceCount] = idx;
			return solArr;
		} else {
			return getOccurrenceArray(array, data, idx + 1, occurrenceCount);
		}
	}

	@Override
	public void printSubsequences(String input) {
		DoubleCountingQuestion.INSTANCE.printPowerSetUsingSubSequence(input);

	}

	@Override
	public void printGroupCombination(String[] groups) {
		CombinationQuestion.INSTANCE.printGroupCombination(groups);

	}

}
