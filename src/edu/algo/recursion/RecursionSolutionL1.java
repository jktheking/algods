package edu.algo.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.algo.algebra.BinaryExponentiation;
import edu.algo.permcomb.CombinationQuestion;
import edu.algo.permcomb.DoubleCountingQuestion;
import edu.algo.permcomb.PermutationQuestion;
import edu.algo.permcomb.PermutationSolution;

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

		// INSTANCE.printMaxElementOfArray(new int[] { 1, 7, 3, 4, 5 });

		// INSTANCE.printFirstIndexOfOccurrence(new int[] { 1, 7, 7, 3, 4, 5, 7, 3 },
		// 7);

		// INSTANCE.printLastIndexOfOccurrence(new int[] { 1, 7, 7, 3, 4, 5, 7, 3 }, 7);

		// INSTANCE.printAllIndexOfOccurrence(new int[] { 1, 7, 7, 3, 4, 5, 7, 3 }, 7);

		// System.out.println(INSTANCE.getSubsequences("abc"));

		// INSTANCE.printStairPathPermutation(7, 1, 2, 3);

		// System.out.println(INSTANCE.getMazePath1(0, 0, 2, 2));

		// INSTANCE.printMazePath1(0, 0, 2, 2, "00");
		// System.out.println(INSTANCE.getMazePath2(0, 0, 2, 2));

		// INSTANCE.printMazePath2(0, 0, 2, 2, "00");

		// INSTANCE.printEncoding1("1211", "");
		// System.out.println("-------------------------");
		// INSTANCE.printEncoding1("1211", "");

		int[][] maze = new int[][] { { 0, 1, 1 }, { 0, 1, 1 }, { 0, 0, 0 } };

		// INSTANCE.printObstacledMazePath3(maze, 0, 0, 2, 2);

		int[][] maze2 = new int[][] { { 0, 1, 0, 1, 1 }, { 0, 1, 1, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 1, 1 },
				{ 0, 0, 0, 0, 0 } };

		// INSTANCE.printObstacledMazePath3(maze2, 0, 0, 4, 4);

		// INSTANCE.printNQueenAllowedPlacements(5);

		INSTANCE.printKnightTour(5, 2, 2);

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
	public List<String> getSubsequences(String input) {
		return DoubleCountingQuestion.INSTANCE.getPowerSetUsingSubSequence(input);

	}

	@Override
	public List<String> getGroupCombination(String[] groups) {
		return CombinationQuestion.INSTANCE.getGroupCombination(groups);

	}

	@Override
	public List<String> getStairPathPermutation(int targetValue, int... allowedSteps) {
		return PermutationQuestion.INSTANCE.getStairPathPermutation(targetValue, allowedSteps);

	}

	@Override
	public void printStairPathPermutation(int targetValue, int[] allowedSteps, String path) {
		PermutationQuestion.INSTANCE.printStairPathPermutation(targetValue, allowedSteps, path);

	}

	/**
	 * <pre>
	 * Print all the possible maze paths between start_pos to end_pos using right
	 * and down move
	 * 
	 * STRATEGY : same as printStairPathPermutation. 
	 * 
	 * 
	 * Time Complexity: T(n) = T(n-1) + T(n-1) 
	 *                       = 2T(n-1) + 1
	 *                       = O(2^n)
	 *                      
	 * Time Complexity using recursion tree: There are two interanal recursive invocations,
	 * so number of branches at 0th level will be 2. At every step we are decreasing the length by 1.
	 * so at leaf level there will be 2^n branches. 
	 * 
	 * Thus time complexity : 2^0 + 2^1 + ...+ 2^n => 2^(n+1) - 1  => O(2^n)
	 * 
	 * </pre>
	 * 
	 * @see PermutationSolution#getStairPathPermutation1(int, int...)
	 * 
	 */
	@Override
	public List<String> getMazePath1(int startRow, int startCol, int endRow, int endCol) {

		if (startRow > endRow || startCol > endCol) {
			return List.of();
		}

		if (startRow == endRow && startCol == endCol) {
			return List.of("" + endRow + endCol);
		}

		List<String> paths = new ArrayList<>();

		List<String> hPaths = getMazePath1(startRow, startCol + 1, endRow, endCol);
		List<String> vPaths = getMazePath1(startRow + 1, startCol, endRow, endCol);

		for (String p : hPaths) {
			paths.add(startRow + "" + startCol + "-->" + p);
			// paths.add("h"+p);
		}
		for (String p : vPaths) {
			paths.add(startRow + "" + startCol + "-->" + p);
			// paths.add("v"+p);
		}

		return paths;
	}

	@Override
	public void printMazePath1(int startRow, int startCol, int endRow, int endCol, String path) {

		if (startRow > endRow || startCol > endCol) {
			return;
		}

		if (startRow == endRow && startCol == endCol) {
			System.out.println(path);
			return;
		}

		printMazePath1(startRow, startCol + 1, endRow, endCol, path + "-->" + startRow + "" + (startCol + 1));
		printMazePath1(startRow + 1, startCol, endRow, endCol, path + "-->" + (startRow + 1) + "" + startCol);

	}

	/**
	 * <pre>
	 * Print all the possible maze paths between start_pos to end_pos using 
	 * 1. right move of step size  1 to (n-1) uint
	 * 2. down move of step size 1 to (m-1) unit
	 * 3. diagonal move of step size 1 to Math.max((n-1), (m-1)) size
	 * 
	 * Total recursive method invocations : n(for col) + n(for rows) + n(for diagonal)
	 *                                    :3n interanl recursive calls; so at 0 level recursion tree
	 *                                     will have 3n branches. thus at leaf level recursion tree will 
	 *                                     have (3n)^(n) branches
	 *                                    : O((3n)^n )
	 * </pre>
	 */
	@Override
	public List<String> getMazePath2(int startRow, int startCol, int endRow, int endCol) {

		if (startRow > endRow || startCol > endCol) {
			return List.of();
		}

		if (startRow == endRow && startCol == endCol) {
			return List.of("" + endRow + endCol);
		}

		List<String> paths = new ArrayList<>();

		for (int stepSize = 1; stepSize <= endCol; stepSize++) {
			List<String> hPaths = getMazePath2(startRow, startCol + stepSize, endRow, endCol);
			for (String p : hPaths) {
				paths.add(startRow + "" + startCol + "h-->" + p);
			}
		}

		for (int stepSize = 1; stepSize <= endRow; stepSize++) {
			List<String> vPaths = getMazePath2(startRow + stepSize, startCol, endRow, endCol);
			for (String p : vPaths) {
				paths.add(startRow + "" + startCol + "v-->" + p);

			}
		}

		for (int stepSize = 1; stepSize <= Math.max(endRow, endCol); stepSize++) {
			List<String> dPaths = getMazePath2(startRow + stepSize, startCol + stepSize, endRow, endCol);
			for (String p : dPaths) {
				paths.add(startRow + "" + startCol + "d-->" + p);

			}
		}

		return paths;
	}

	@Override
	public void printMazePath2(int startRow, int startCol, int endRow, int endCol, String path) {

		if (startRow > endRow || startCol > endCol) {
			return;
		}

		if (startRow == endRow && startCol == endCol) {
			System.out.println(path);
			return;
		}

		for (int stepSize = 1; stepSize <= endCol; stepSize++) {
			printMazePath2(startRow, startCol + stepSize, endRow, endCol,
					path + "h-->" + startRow + "" + (startCol + stepSize));

		}

		for (int stepSize = 1; stepSize <= endRow; stepSize++) {
			printMazePath2(startRow + stepSize, startCol, endRow, endCol,
					path + "v-->" + (startRow + stepSize) + "" + startCol);
		}

		for (int stepSize = 1; stepSize <= Math.max(endRow, endCol); stepSize++) {
			printMazePath2(startRow + stepSize, startCol + stepSize, endRow, endCol,
					path + "d-->" + (startRow + stepSize) + "" + (startCol + stepSize));
		}

	}

	/***
	 * <pre>
	 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
	 * 'A' -> "1" , 'B' -> "2" , ... ,'Z' -> "26"
	 * To decode an encoded message, all the digits must be grouped then mapped back 
	 * into letters using the reverse of the mapping above (there may be multiple ways). 
	 * For example, "11106" can be mapped into:
	 * "AAJF" with the grouping (1 1 10 6)
	 * "KJF" with the grouping (11 10 6)
	 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' 
	 * since "6" is different from "06".
	 * Given a string s containing only digits, return the number of ways to decode it.
	 * The answer is guaranteed to fit in a 32-bit integer.
	 * </pre>
	 * 
	 */
	@Override
	public void printEncoding1(String input, String encoding) {

		if (input.isEmpty()) {
			System.out.println(encoding);
			return;
		}
		// encoding of first digit of input is given by "encoding +
		// ENCODING.get(singleDigit)" and encodings of
		// remaianing input digits are given by substitution step.
		String firstDigit = input.substring(0, 1);
		if (ENCODING.containsKey(firstDigit)) {
			printEncoding1(input.substring(1), encoding + ENCODING.get(firstDigit));
		}

		if (input.length() < 2) {
			return;
		}
		// encoding of first two digits of input is given by "encoding +
		// ENCODING.get(firstTwoDigit)" and encodings of
		// remaianing input digits are given by substitution step.
		String firstTwoDigit = input.substring(0, 2);
		if (ENCODING.containsKey(firstTwoDigit)) {
			printEncoding1(input.substring(2), encoding + ENCODING.get(firstTwoDigit));
		}

	}

	/**
	 * Print all the possible values formed by using input digits without using the
	 * RecursionQuestionL1#ENCODING map.
	 * 
	 * <pre>
	 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
	 * 'A' -> "1" , 'B' -> "2" , ... ,'Z' -> "26"
	 * To decode an encoded message, all the digits must be grouped then mapped back 
	 * into letters using the reverse of the mapping above (there may be multiple ways). 
	 * For example, "11106" can be mapped into:
	 * "AAJF" with the grouping (1 1 10 6)
	 * "KJF" with the grouping (11 10 6)
	 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' 
	 * since "6" is different from "06".
	 * Given a string s containing only digits, return the number of ways to decode it.
	 * The answer is guaranteed to fit in a 32-bit integer.
	 * </pre>
	 * 
	 * 
	 * @param input : will contains digits like 123476
	 * 
	 */
	@Override
	public void printEncoding2(String input, String encoding) {

		if (input.isEmpty()) {
			System.out.println(encoding);
			return;
		}

		char firstDigitChar = input.charAt(0);
		if (firstDigitChar == '0') {
			return;
		}

		// 6 = '6' - '0'
		int firstDigitVal = firstDigitChar - '0';

		// f = (char)('a' + 5)
		char encVal = (char) ('a' + firstDigitVal - 1);
		printEncoding1(input.substring(1), encoding + encVal);

		if (input.length() < 2) {
			return;
		}

		String firstTwoDigit = input.substring(0, 2);
		int firstTwoDigitVal = Integer.valueOf(firstTwoDigit);

		if (firstTwoDigitVal <= 26) {
			encVal = (char) ('a' + firstTwoDigitVal - 1);
			printEncoding1(input.substring(2), encoding + encVal);
		}

	}

	/**
	 * <pre>
	 * 
	 * Print all the possible maze paths between start_pos to end_pos and avoiding the obstacles on the way
	 * using following allowed moves:
	 * 1. left move of step size  1
	 * 2. right move of step size  1
	 * 3. top move of step size  1
	 * 4. down move of step size 1
	 * Note: obstacle is represented in maze as 1.
	 * </pre>
	 * 
	 * 
	 * 
	 * 
	 */
	@Override
	public void printObstacledMazePath3(int[][] obstacledMaze, int startRow, int startCol, int endRow, int endCol) {

		printObstacledMazePath3(obstacledMaze, new boolean[obstacledMaze.length][obstacledMaze[0].length], startRow,
				startCol, endRow, endCol, "00");
	}

	/***
	 * <pre>
	 * moves order : l,r,t,d
	 * moves represent the options of recursion tree .i.e nothing but branch of the recursion tree.
	 * Level of tree will be represented by row or col reached post recursion invocation.
	 * 
	 * Solution  Notes:
	 * 
	 * NOTE_1. left_move & right_move ; top_move & down_move are opposite
	 * moves. Because of their opposite directions path exploration can go in
	 * infinite loop, so to avoid infinite loop we need to track the visited cells
	 * of maze.
	 * 
	 * NOTE_2. We need to mark the visited-cells to unvisited in return trip of an
	 * exploration, so that the previous exploration would not impact the next
	 * exploration path.
	 * 
	 * Hypothesis: printObstacledMazePath3(startRow, startCol) : prints all the possible path
	 * 
	 * Substitution:
	 * using option_left : printObstacledMazePath3(startRow, startCol - 1,  "l") : append remaining path to "l" 
	 * using option_right: printObstacledMazePath3(startRow, startCol + 1 , "r") : append remaining path to "r" 
	 * using option_top: printObstacledMazePath3(startRow - 1, startCol, "t") : append remaining path to "t" 
	 * using option_down: printObstacledMazePath3(startRow - 1, startCol, "d") : append remaining path to "d" 
	 * 
	 * Induction: main code appends options l,r,t,d to path and remaining path is appended by substitution step.
	 * </pre>
	 **/
	private void printObstacledMazePath3(int[][] obstacledMaze, boolean[][] visitedMaze, int startRow, int startCol,
			int endRow, int endCol, String path) {

		if (startRow < 0 || startCol < 0 || startRow == obstacledMaze.length || startCol == obstacledMaze[0].length
				|| obstacledMaze[startRow][startCol] == 1 || visitedMaze[startRow][startCol]) {
			return;

		}

		if (startRow == endRow && startCol == endCol) {
			System.out.println(path);

		}

		// NOTE_1
		visitedMaze[startRow][startCol] = true;

		printObstacledMazePath3(obstacledMaze, visitedMaze, startRow, startCol - 1, endRow, endCol,
				path + "-l->" + startRow + "" + (startCol - 1));
		printObstacledMazePath3(obstacledMaze, visitedMaze, startRow, startCol + 1, endRow, endCol,
				path + "-r->" + startRow + "" + (startCol + 1));

		printObstacledMazePath3(obstacledMaze, visitedMaze, startRow - 1, startCol, endRow, endCol,
				path + "-t->" + (startRow - 1) + "" + startCol);
		printObstacledMazePath3(obstacledMaze, visitedMaze, startRow + 1, startCol, endRow, endCol,
				path + "-d->" + (startRow + 1) + "" + startCol);
		// NOTE_2
		visitedMaze[startRow][startCol] = false;

	}

	/**
	 * print all the possible allowed combinations of N queens on N X N Matrix.
	 */
	@Override
	public void printNQueenAllowedPlacements(int n) {
		printNQueenAllowedPlacements(new int[n][n], 0);
	}

	/**
	 * <pre>
	 * Recursion tree formation Strategy :
	 * 
	 * In a single row we can place at-most one queen. 
	 * 
	 * Exploration options strategy: In a row a queen can be placed at any column position.
	 * 
	 * Tree Node :  represented by Row
	 * Tree Branch : represented by Columns. 
	 * 
	 * Levels of tree: for each row there will be one level in tree.
	 * 
	 *   Since row, represents level and we can go to next level via recursion call, 
	 *   so need to pass row as method parameter
	 * 
	 * </pre>
	 * 
	 */
	private void printNQueenAllowedPlacements(int[][] board, int row) {

		if (row == board.length) {
			printMatrix(board);
			return;
		}

		for (int col = 0; col < board.length; col++) {
			if (isValidQueenPlacement(board, row, col)) {
				// placing the queen at col of a given row
				board[row][col] = 1;
				printNQueenAllowedPlacements(board, row + 1);
				// need to remove the queen from this col, so that we can try at next col of the
				// same row
				board[row][col] = 0;
			}

		}
	}

	/**
	 * <pre>
	 * 1. We will not validate queen placement in next rows because next rows are
	 * still empty.
	 * 
	 * 2. We will not validate queen placement in current row because we are making
	 * sure that current row will place at-max one queen through backtracking.
	 * 
	 * Remaining validation part on board:  
	 * 1. above vertical columns
	 * 2. above left-diagonal rows-cols 
	 * 3. above right-diagonal rows-cols
	 * 
	 * </pre>
	 */
	private boolean isValidQueenPlacement(int[][] board, int row, int col) {

		// above vertical columns
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col] == 1)
				return false;
		}

		// above left-diagonal rows-cols
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] == 1)
				return false;
		}

		// above right-diagonal rows-cols
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] == 1)
				return false;
		}

		return true;
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
	 * print knight tour from a given starting_point such that it visits all points
	 * on the board without ever going to an already visited point.
	 */
	@Override
	public void printKnightTour(int n, int initialRow, int initialCol) {
		printKnightTour(new int[n][n], initialRow, initialCol, 1);
	}

	/**
	 * <pre>
	 * knight would have at max 8 options for next-move from a given position
	 * 
	 * relative options of move in clockwise direction:
	 *  r-2, col+1
	 *  r-1, col+2 
	 *  r+1, col+2
	 *  r+2, col+1
	 *  
	 *  r+2, col-1
	 *  r+1, col-2 
	 *  r-1, col-2
	 *  r-2, col-1 
	 *  
	 * Recursion Tree formation Strategy:
	 * tree node : will be represented by knight_move
	 * tree branch: will be represented by knight_move options in clock-wise direction mentioned above.
	 * max possible levels : size of the matrix i.e. n*n
	 * 
	 * Since knight_move, represents level and we can go to next level via recursion call, 
	 * so need to pass knight_move as a method parameter
	 * 
	 * </pre>
	 */
	private void printKnightTour(int[][] board, int row, int col, int knight_move) {

		if (row < 0 || col < 0 || row >= board.length || col >= board.length || board[row][col] > 0) {
			return;
		}

		board[row][col] = knight_move;

		if (knight_move == board.length * board.length) {
			printMatrix(board);
		}

		// r-2, col+1
		printKnightTour(board, row - 2, col + 1, knight_move + 1);
		// r-1, col+2
		printKnightTour(board, row - 1, col + 2, knight_move + 1);
		// r+1, col+2
		printKnightTour(board, row + 1, col + 2, knight_move + 1);
		// r+2, col+1
		printKnightTour(board, row + 2, col + 1, knight_move + 1);
		// r+2, col-1
		printKnightTour(board, row + 2, col - 1, knight_move + 1);
		// r+1, col-2
		printKnightTour(board, row + 1, col - 2, knight_move + 1);
		// r-1, col-2
		printKnightTour(board, row - 1, col - 2, knight_move + 1);
		// r-2, col-1
		printKnightTour(board, row - 2, col - 1, knight_move + 1);
		// since we are trying all the moves
		board[row][col] = 0;

	}
}
