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

}
