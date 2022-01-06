package edu.algo.recursion;

import java.util.List;
import java.util.Map;

public interface RecursionQuestionL2 {

	RecursionQuestionL2 INSTANCE = new RecursionSolutionL2();

	/**
	 * <pre>
	 * Print powerset by placing 1 for characters which are absent. If 1 appears in
	 * consecutive order then need to be summed up.
	 * 
	 * Sample Input:  abc
	 * Sample Output: abc, ab1, a1c, a2, 1bc, 1b1, 2c, 3
	 * Power set    : abc, ab_, a_c, a__, _bc, _b_, __c, ___  
	 *              : 000, 001, 010, 011, 100, 101, 110, 111
	 * </pre>
	 */
	void printAbbreviation1(String input);

	void printAbbreviation2(String input);

	/**
	 * You are given a list of words, and allowed set of alphabets(might be
	 * repeating) and score of every alphabet from a to z.
	 * 
	 * 2. You have to find the maximum score of any valid set of words formed by
	 * using the given alphabets.
	 * 
	 * 3. A word can not be used more than one time.
	 * 
	 * 4. Each alphabet can be used only once.
	 * 
	 * 5. It is not necessary to use all the given alphabets.
	 * 
	 */
	int scoreBased01MaxKnapsack(List<String> input, Map<Character, Integer> allowedCharFreqency,
			Map<Character, Integer> score);

	int josephusProbelm(int n, int k);

	void printNumbersInLexicographicOrderUptoN(int n);

	int getMaxGoldFromIsLandsOnMaze(int[][] maze);

	/**
	 * Following are the rules of Sudoku for a player.
	 * 
	 * 1. In all 9 sub matrices 3×3 the elements should be 1-9, without repetition.
	 * 
	 * 2. In all rows there should be elements between 1-9 , without repetition.
	 * 
	 * 3. In all columns there should be elements between 1-9 , without repetition.
	 * 
	 */
	void solveSudoku(int[][] sudokuPuzzle);

	int[][] generateSudoku1(int countOfMissingElements);

	int[][] generateSudoku2(int countOfMissingElements);

	void solveCrossWord(char[][] crossWordPuzzle, List<String> input);

	/**
	 * 1. You are given three strings s1, s2 and s3.
	 * 
	 * 2. First two are supposed to add and form third. s1 + s2 = s3
	 * 
	 * 3. You have to map each individual character to a digit, so that the above
	 * equation holds true.
	 * 
	 * Constraint: 1 <= length of s1,s2,s3 <= 10
	 * 
	 * 4.Each character must be mapped with a number between 0 to 9. Each number can
	 * be used once only. For example - if 9 is mapped to 'a', then it cannot be
	 * mapped to any other letter. There will be one to one mapping..
	 */
	void solveCryptArithmeticPuzzle(String s1, String s2, String s3);

	/**
	 * <pre>
	 * 1. You are given an integer n, which represents n friends numbered from 1 to n.
	 * 2. Each one can remain single or can pair up with some other friend.
	 * 3. You have to print all the configurations in which friends can remain single or can be paired up.
	 * 4. We cannot make 2 different permutations like 1-2 and 2-1. Only 1-2 is valid.
	 * </pre>
	 */
	void printFriendsPairing(int friends);

	/**
	 * 1. You are given two integers n and k, where n represents number of elements
	 * and k represents number of subsets.
	 * 
	 * 2. You have to partition n elements in k subsets and print all such
	 * configurations.
	 * 
	 */
	void printKpartitionSubsets1(int n, int k);

	void printKpartitionSubsets2(int n, int k);

	void printPalindromicPermuation(String input);

	/**
	 * 1. You are given a string of length n.
	 * 
	 * 2. You have to partition the given string in such a way that every partition
	 * is a palindrome.
	 */
	void printPalindormicPartition(String input);

	/**
	 * 1. You are given an array of n distinct integers.
	 * 
	 * 2. You have to divide these n integers into k non-empty subsets such that sum
	 * of integers of every subset is same.
	 * 
	 * 3. If it is not possible to divide, then print "-1".
	 * 
	 */
	void printKpartitionEqualSumSubsets(int[] input, int k);

}
