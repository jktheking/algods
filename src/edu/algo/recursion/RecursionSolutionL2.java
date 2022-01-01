package edu.algo.recursion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import edu.algo.algointro.JosephusProblem;
import edu.algo.algointro.RandomGenerator;

public class RecursionSolutionL2 implements RecursionQuestionL2 {

	public static void main(String[] args) {
		// INSTANCE.printAbbreviation1("abcd");
		// INSTANCE.printAbbreviation2("abcd");

		Map<Character, Integer> characterFreq = new HashMap<>(
				Map.ofEntries(Map.entry('a', 2), Map.entry('b', 2), Map.entry('c', 2), Map.entry('z', 1)));

		Map<Character, Integer> characterScore = Map.ofEntries(Map.entry('a', 1), Map.entry('b', 2), Map.entry('c', 3),
				Map.entry('z', 26));
		// System.out.println(INSTANCE.scoreBased01MaxKnapsack(List.of("abb", "aaz",
		// "acc"), characterFreq, characterScore));

		// System.out.println(INSTANCE.josephusProbelm(4, 3));
		// INSTANCE.printNumbersInLexicographicOrderUptoN(100);

		//
		int[][] goldMine = { //
				{ 1, 0, 0, 0, 1, 1, 1, 0 }, //
				{ 1, 0, 0, 0, 0, 1, 0, 0 }, //
				{ 1, 0, 0, 0, 1, 1, 1, 0 }, //
				{ 1, 0, 0, 0, 1, 0, 1, 0 }, //
				{ 0, 0, 0, 0, 1, 0, 1, 0 }, //
				{ 0, 1, 0, 0, 1, 1, 1, 0 }, //
				{ 0, 1, 0, 0, 0, 1, 0, 0 }, //
				{ 0, 0, 0, 0, 1, 1, 1, 0 } };//

		// System.out.println(INSTANCE.getMaxGoldFromIsLandsOnMaze(goldMine));

		int[][] sudokuUnsolvable = { //
				{ 2, 0, 0, 9, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 6, 0 }, //
				{ 0, 0, 0, 0, 0, 1, 0, 0, 0 }, //
				{ 5, 0, 2, 6, 0, 0, 4, 0, 7 }, //
				{ 0, 0, 0, 0, 0, 4, 1, 0, 0 }, //
				{ 0, 0, 0, 0, 9, 8, 0, 2, 3 }, //
				{ 0, 0, 0, 0, 0, 3, 0, 8, 0 }, //
				{ 0, 0, 5, 0, 1, 0, 0, 0, 0 }, //
				{ 0, 0, 7, 0, 0, 0, 0, 0, 0 } //
		};//

		// INSTANCE.solveSudoku(sudokuUnsolvable);

		int[][] sudokuUniqeSol = { //
				{ 3, 0, 6, 5, 0, 8, 4, 0, 0 }, //
				{ 5, 2, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 8, 7, 0, 0, 0, 0, 3, 1 }, //
				{ 0, 0, 3, 0, 1, 0, 0, 8, 0 }, //
				{ 9, 0, 0, 8, 6, 3, 0, 0, 5 }, //
				{ 0, 5, 0, 0, 9, 0, 6, 0, 0 }, //
				{ 1, 3, 0, 0, 0, 0, 2, 5, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 7, 4 }, //
				{ 0, 0, 5, 2, 0, 6, 3, 0, 0 } };

		// INSTANCE.solveSudoku(sudokuUniqeSol);

		/**
		 * the diagonal matrices are independent of other empty matrices initially.
		 * 
		 * 
		 */
		int[][] sudokuMutipleSol = { //
				{ 1, 2, 3, 0, 0, 0, 0, 0, 0 }, //
				{ 4, 5, 6, 0, 0, 0, 0, 0, 0 }, //
				{ 7, 8, 9, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 1, 2, 3, 0, 0, 0 }, //
				{ 0, 0, 0, 4, 5, 6, 0, 0, 0 }, //
				{ 0, 0, 0, 7, 8, 9, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 1, 2, 3 }, //
				{ 0, 0, 0, 0, 0, 0, 4, 5, 6 }, //
				{ 0, 0, 0, 0, 0, 0, 7, 8, 9 } };//

		int[][] sudokuMutipleSol1 = { //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
				{ 0, 0, 0, 0, 0, 0, 0, 0, 0 } };//

		// INSTANCE.solveSudoku(sudokuMutipleSol);

		// printMatrix(INSTANCE.generateSudoku1(50));

		// printMatrix(INSTANCE.generateSudoku2(50));


	}

	@Override
	public void printAbbreviation1(String input) {
		char[] output = new char[input.length()];
		Arrays.fill(output, '_');
		printAbbreviationUsingPIE(input.toCharArray(), output, 0);

	}

	/**
	 * 
	 * STEP1 :Need to create powerset with empty_placeholder '_'.
	 *
	 * STEP2: While printing we need to count the empty_placeholder.
	 */
	private void printAbbreviationUsingPIE(char[] input, char[] output, int posToFix) {
		// logic to print the abbreviation
		String print = "";
		int emptyCounter = 0;
		for (char ch : output) {
			if (ch == '_') {
				emptyCounter++;
			} else {
				print = emptyCounter == 0 ? print + ch : print + emptyCounter + ch;
				emptyCounter = 0;
			}
		}
		print = emptyCounter == 0 ? print : print + emptyCounter;
		System.out.println(print);

		// core logic
		while (posToFix < output.length) {
			int currentPos = posToFix;
			output[currentPos] = input[currentPos];
			printAbbreviationUsingPIE(input, output, ++posToFix);
			output[currentPos] = '_';
		}

	}

	@Override
	public void printAbbreviation2(String input) {
		printAbbreviationUsingPIE(input.toCharArray(), "", 0);
	}

	/**
	 *
	 * Refer : AbbrerivationUsingPIE_L2.pdf
	 * 
	 */
	private void printAbbreviationUsingPIE(char[] input, String output, int posToFix) {
		// adding end_empty_count to print string.
		System.out.println((input.length - posToFix) == 0 ? output : output + (input.length - posToFix));

		int currentPosToFix = posToFix;

		while (posToFix < input.length) {
			// we are adding before_empty_count to output for next level.
			String newOutput = output;
			if (posToFix - currentPosToFix != 0) {
				newOutput = newOutput + (posToFix - currentPosToFix);
			}
			newOutput = newOutput + input[posToFix];

			printAbbreviationUsingPIE(input, newOutput, ++posToFix);

		}

	}

	@Override
	public int scoreBased01MaxKnapsack(List<String> input, Map<Character, Integer> allowedCharFreqency,
			Map<Character, Integer> charScoreMap) {
		return scoreBased01MaxKnapsackUsingPIE(input, allowedCharFreqency, charScoreMap, new HashMap<>(), 0);
	}

	/**
	 * compare the score of all the branches to get the max and then return to below
	 * level in tree.
	 * 
	 */
	private int scoreBased01MaxKnapsackUsingPIE(List<String> input, Map<Character, Integer> allowedCharFreqency,
			Map<Character, Integer> charScoreMap, Map<String, Integer> output, int posToFix) {

		int outputSetScore = output.values().stream().reduce(0, Integer::sum);
		System.out.println(output + " score :" + outputSetScore);

		// if we want to avoid the backtrack, then need to work with new instance
		while (posToFix < input.size()) {
			String inputStr = input.get(posToFix);
			posToFix++;

			int backtrackIndex = getAllowedIndex(inputStr, allowedCharFreqency);
			if (backtrackIndex == inputStr.length()) {

				output.put(inputStr, calculateScore(inputStr, charScoreMap));
				outputSetScore = Math.max(outputSetScore,
						scoreBased01MaxKnapsackUsingPIE(input, allowedCharFreqency, charScoreMap, output, posToFix));

				// backtrack the output
				output.remove(inputStr);
			}
			// backtrack the allowedCharFreqMap
			backtrackAllowedCharFreqMap(inputStr, backtrackIndex, allowedCharFreqency);
		}
		return outputSetScore;
	}

	private int calculateScore(String inputStr, Map<Character, Integer> charScoreMap) {
		int score = 0;
		for (char ch : inputStr.toCharArray()) {
			score += charScoreMap.get(ch);
		}
		return score;
	}

	private int getAllowedIndex(String inputStr, Map<Character, Integer> allowedCharFreqency) {

		for (int i = 0; i < inputStr.length(); i++) {
			char ch = inputStr.charAt(i);
			int charFreq = allowedCharFreqency.get(ch);

			if (charFreq == 0) {
				return i;
			}

			allowedCharFreqency.put(ch, charFreq - 1);

		}
		return inputStr.length();

	}

	private void backtrackAllowedCharFreqMap(String inputStr, int backtrackPos,
			Map<Character, Integer> allowedCharFreqency) {
		for (int i = 0; i < backtrackPos; i++) {
			allowedCharFreqency.put(inputStr.charAt(i), allowedCharFreqency.get(inputStr.charAt(i)) + 1);
		}
	}

	@Override
	public int josephusProbelm(int n, int k) {

		return JosephusProblem.recursiveJosephus(n, k);
	}

	/**
	 * 
	 * Refer the LexicographicNumbersUptoN.pdf
	 * 
	 */
	@Override
	public void printNumbersInLexicographicOrderUptoN(int n) {
		for (int i = 1; i < 10; i++) {
			printNumbersInDFS(i, n);
		}

	}

	private void printNumbersInDFS(int i, int max) {
		if (i > max)
			return;
		System.out.println(i);
		for (int j = 0; j < 10; j++) {
			// we are increasing the digits at next level
			printNumbersInDFS(i * 10 + j, max);
		}
	}

	@Override
	public int getMaxGoldFromIsLandsOnMaze(int[][] maze) {

		boolean[][] visited = new boolean[maze.length][maze[0].length];

		int maxGoldCount = 0;
		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {

				if (!visited[row][col]) {
					// starting point of all islands
					maxGoldCount = Math.max(maxGoldCount, exploreConnectedComponents(maze, row, col, visited));

				}

			}
		}
		return maxGoldCount;
	}

	/**
	 * 
	 * Strategy: explore the connected cell and mark the ownership, so that other
	 * explorations cannot own the already explored cell.
	 * 
	 * moves order : l,r,t,d
	 * 
	 * How does Backtracking happens for exploreConnectedComponents ?
	 * 
	 * When all the options are exhausted for a given cell then the recursion falls
	 * back to the cell which has invoked it.
	 * 
	 */
	private int exploreConnectedComponents(int[][] maze, int row, int col, boolean[][] visited) {

		if (row < 0 || col < 0 || row == maze.length || col == maze[0].length || visited[row][col]
				|| maze[row][col] == 0) {
			return 0;
		}

		visited[row][col] = true;

		int goldCount = maze[row][col];

		// left neighbour
		goldCount += exploreConnectedComponents(maze, row, col - 1, visited);
		// right neighbour
		goldCount += exploreConnectedComponents(maze, row, col + 1, visited);
		// top neighbour
		goldCount += exploreConnectedComponents(maze, row - 1, col, visited);
		// down neighbour
		goldCount += exploreConnectedComponents(maze, row + 1, col, visited);

		return goldCount;

	}

	@Override
	public void solveSudoku(int[][] sudokuPuzzle) {
		Sudoku.INSTANCE.solveSudoku(sudokuPuzzle);
		
	}

	@Override
	public int[][] generateSudoku1(int countOfMissingElements) {
		return Sudoku.INSTANCE.generateSudoku1(countOfMissingElements);
	}

	@Override
	public int[][] generateSudoku2(int countOfMissingElements) {
		return Sudoku.INSTANCE.generateSudoku2(countOfMissingElements);
	}


	
	@Override
	public void solveCrossWord(char[][] crossWordPuzzle, List<String> input) {
		CrossWord.INSTANCE.solveCrossWordByFixingPos(crossWordPuzzle, input);
		
	}

	

}
