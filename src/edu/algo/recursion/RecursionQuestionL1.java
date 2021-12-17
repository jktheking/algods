package edu.algo.recursion;

import java.util.List;
import java.util.Map;

public interface RecursionQuestionL1 {

	Map<String, String> ENCODING = Map.ofEntries(Map.entry("1", "a"), Map.entry("2", "b"), Map.entry("3", "c"),
			Map.entry("4", "d"), Map.entry("5", "e"), Map.entry("6", "f"), Map.entry("7", "g"), Map.entry("8", "h"),
			Map.entry("9", "i"), Map.entry("10", "j"), Map.entry("11", "k"), Map.entry("12", "l"), Map.entry("13", "m"),
			Map.entry("14", "n"), Map.entry("15", "o"), Map.entry("16", "p"), Map.entry("17", "q"),
			Map.entry("18", "r"), Map.entry("19", "s"), Map.entry("20", "t"), Map.entry("21", "u"),
			Map.entry("22", "v"), Map.entry("23", "w"), Map.entry("24", "x"), Map.entry("25", "y"),
			Map.entry("26", "z"));

	/**
	 * Print numbers in decreasing order from n till 1 and then in increasing order
	 * till n.
	 */
	void printDecreasingIncreasing(int n);

	/**
	 * Print factorial of numbers till n
	 */
	int factorial(int n);

	/**
	 * Print output following the euler tour.
	 */
	void eulerTourOfRecursion(int n);

	/**
	 * Calculate a to the power of p
	 */
	void printAToThePowerOfP(int a, int p);

	/**
	 * Print the steps to solve tower of hanoi problem given 'n' discs
	 */
	void printTowerOfHanoiSteps(int n);

	/**
	 * Print elements of array.
	 */
	void printElementsOfArray(int[] array);

	/**
	 * Print elements of array in reverse order.
	 */
	void printElementsOfArrayInReverse(int[] array);

	/**
	 * print max element of array.
	 */
	void printMaxElementOfArray(int[] array);

	void printFirstIndexOfOccurrence(int[] array, int data);

	void printLastIndexOfOccurrence(int[] array, int data);

	void printAllIndexOfOccurrence(int[] array, int data);

	List<String> getSubsequences(String input);

	List<String> getGroupCombination(String[] groups);

	List<String> getStairPathPermutation(int targetValue, int... allowedSteps);

	void printStairPathPermutation(int targetValue, int[] allowedSteps, String path);

	/**
	 * Print all the possible maze paths between start_pos to end_pos using right
	 * and down move
	 * 
	 */
	List<String> getMazePath1(int startRow, int startCol, int endRow, int endCol);

	void printMazePath1(int startRow, int startCol, int endRow, int endCol, String path);

	/**
	 * <pre>
	 * 
	 * Print all the possible maze paths between start_pos to end_pos using 
	 * 1. right move of step size  1 to (n-1) uint
	 * 2. down move of step size 1 to (m-1) unit
	 * 3. diagonal move of step size 1 to Math.max((n-1), (m-1)) size
	 * 
	 * </pre>
	 */
	List<String> getMazePath2(int startRow, int startCol, int endRow, int endCol);

	void printMazePath2(int startRow, int startCol, int endRow, int endCol, String path);

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
	 */
	void printObstacledMazePath3(int[][] obstacledMaze, int startRow, int startCol, int endRow, int endCol);

	/**
	 * Print all the possible RecursionQuestionL1#ENCODING values formed by using
	 * input digits.
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
	void printEncoding1(String input, String encoding);

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
	void printEncoding2(String input, String encoding);

	/**
	 * Print all the possible allowed arrangement of N queens on N X N Matrix.
	 * Since, all the n-queens are identical so it is essentially a problem of
	 * finding the combination of n identical queens on n*n positions.
	 * 
	 */
	void printNQueenAllowedPlacement1(int n);
	void printNQueenAllowedPlacement2(int n);
	void printNQueenAllowedPlacement3(int n);
	void printNQueenAllowedPlacement4(int n);

	/**
	 * print knight tour from a given starting_point such that it visits all points
	 * on the board without ever going to an already visited point.
	 */
	void printKnightTour(int n, int initialRow, int initialCol);
}
