package edu.algo.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.algo.algebra.BinaryExponentiation;
import edu.algo.algointro.JosephusProblem;
import edu.algo.permcomb.PermutationQuestion;

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

		// INSTANCE.solveCryptArithmeticPuzzle("send", "more", "money");

		// INSTANCE.printFriendsPairing(3);

		// INSTANCE.printKpartionSubsets1(4, 3);
		// INSTANCE.printKpartionSubsets2(4, 3);

		// INSTANCE.printPalindormicPartition("abaaba");

		INSTANCE.printKpartitionEqualSumSubsets(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3);

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
		CrossWord.INSTANCE.solveCrossWordByFixingInput(crossWordPuzzle, input);

	}

	@Override
	public void solveCryptArithmeticPuzzle(String s1, String s2, String s3) {

		Map<Character, Integer> charDigitMapping = new TreeMap<>();

		for (char c : s1.toLowerCase().toCharArray()) {
			charDigitMapping.put(c, -1);
		}

		for (char c : s2.toLowerCase().toCharArray()) {
			charDigitMapping.put(c, -1);
		}

		for (char c : s3.toLowerCase().toCharArray()) {
			charDigitMapping.put(c, -1);
		}

		char[] input = charDigitMapping.keySet().stream().map(String::valueOf).collect(Collectors.joining())
				.toCharArray();

		solveCryptArithmeticByFixingInputAndDigitAsOption(s1, s2, s3, input, 0, charDigitMapping,
				new ArrayList<>(List.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)));

	}

	/**
	 * Strategy: Fixing input i.e. uniqe chars of S1, S2, S3 and taking digits 0-9
	 * as option.
	 * 
	 */
	private void solveCryptArithmeticByFixingInputAndDigitAsOption(String s1, String s2, String s3, char[] input,
			int idx, Map<Character, Integer> charDigitMapping, List<Integer> digitOptions) {

		if (input.length == idx) {
			int s1Num = prepareNumber(s1, charDigitMapping);
			int s2Num = prepareNumber(s2, charDigitMapping);
			int s3Num = prepareNumber(s3, charDigitMapping);
			if (s1Num + s2Num == s3Num) {
				System.out.println(charDigitMapping);
			}
			return;
		}

		for (int i = 0; i < digitOptions.size(); i++) {

			int digit = digitOptions.get(i);

			digitOptions.remove(i);
			charDigitMapping.put(input[idx], digit);

			solveCryptArithmeticByFixingInputAndDigitAsOption(s1, s2, s3, input, idx + 1, charDigitMapping,
					digitOptions);

			// backtrack
			charDigitMapping.put(input[idx], -1);
			digitOptions.add(i, digit);
		}
	}

	private int prepareNumber(String str, Map<Character, Integer> charDigitMapping) {
		int num = 0;
		for (int i = 0; i < str.length(); i++) {
			num += charDigitMapping.get(str.charAt(str.length() - 1 - i))
					* BinaryExponentiation.binaryPowerIterative(10, i);
		}

		return num;
	}

	@Override
	public void printFriendsPairing(int friends) {

		List<Integer> input = IntStream.range(1, friends + 1).boxed().collect(Collectors.toList());
		printFriendsPairing(input, new ArrayList<>());
	}

	/**
	 * <pre>
	 * Note : we are need to generate the combination not permutation
	 * 
	 * Recursion Strategy: Here input is fixed and also taken as options.
	 * 
	 * 1. 1st input is taken out and considered to be fixed at level. By fixing the 1st input
	 *    means at next level(recursion invocation) we will pass remaining input.
	 * 
	 * 2. We are creating options of remaing input using the 1st input.
	 *     OPTION : pairing up 1st input with remaining inputs.
	 *     SPECIAL_OPTION : first input is getting paired up with empty.
	 * 
	 * 
	 * </pre>
	 */
	private void printFriendsPairing(List<Integer> input, List<String> output) {

		if (input.isEmpty()) {
			System.out.println(output);
			return;
		}

		int friendToFix = input.remove(0);

		output.add(String.valueOf(friendToFix));
		printFriendsPairing(input, output);
		output.remove(output.size() - 1);

		for (int i = 0; i < input.size(); i++) {
			int currentFriend = input.get(i);
			String pair = friendToFix + "" + currentFriend;

			output.add(pair);
			input.remove(i);
			printFriendsPairing(input, output);
			input.add(i, currentFriend);
			output.remove(output.size() - 1);
		}

		input.add(0, friendToFix);

	}

	@Override
	public void printKpartitionSubsets1(int n, int k) {
		printKpartionSubsetsByFixingPartitionsUsingPIE(prepareListOfSubsetsUptoSizeK(n, k), 0, "", 0, n, k);

	}

	private void printKpartionSubsetsByFixingPartitionsUsingPIE(List<String> input, int inIdx, String output,
			int outputCharCount, int n, int partionToFix) {

		if (partionToFix == 0) {
			if (outputCharCount == n) {
				System.out.println(output);
			}
			return;
		}

		while (inIdx < input.size()) {

			String currentInput = input.get(inIdx);

			inIdx++;

			if (!isAlreadyPresentInOutput(currentInput, output)) {
				printKpartionSubsetsByFixingPartitionsUsingPIE(input, inIdx, output + currentInput + "|",
						outputCharCount + currentInput.length(), n, partionToFix - 1);

			}

		}

	}

	private boolean isAlreadyPresentInOutput(String input, String output) {
		for (char ch : input.toCharArray()) {
			if (output.contains(ch + "")) {
				return true;
			}

		}
		return false;
	}

	private static List<String> prepareListOfSubsetsUptoSizeK(int n, int k) {
		List<String> accumulator = new ArrayList<>();
		createPowerSetUsingPIE(n, k, "", accumulator, 1);
		return accumulator;
	}

	private static void createPowerSetUsingPIE(int n, int k, String currOutput, List<String> accumulator,
			int posToFix) {

		if (1 <= currOutput.length() && currOutput.length() <= k) {
			accumulator.add(currOutput);
		}

		while (posToFix <= n) {
			createPowerSetUsingPIE(n, k, currOutput + posToFix, accumulator, ++posToFix);
		}

	}

	@Override
	public void printKpartitionSubsets2(int n, int k) {
		String[] output = new String[k];
		Arrays.fill(output, "_");
		printKpartionSubsetsByFixingInput(n, output);

	}

	/**
	 * <pre>
	 * RECURSION STRATEGY: 
	 * 1. Input is getting fixed at each level of tree.
	 * 2. Partitions are tried as option at each level.
	 * 
	 * Note: While fixing a given_input we should treat all the empty partitions 
	 * as same partition to avoid the permutation generation. So we should not
	 * spawn new branch for each empty partition rather just spawn single branch 
	 * against first empty partition.
	 * 
	 * If we wish to generate the permutations then we should spawn separate branch for 
	 * each empty space.
	 * 
	 * &#64;see KPartitionSubsetsByFixingInput.pdf
	 * </pre>
	 * 
	 */
	private void printKpartionSubsetsByFixingInput(int inputToFix, String[] output) {

		if (inputToFix == 0) {
			for (String o : output) {
				if (o.equals("_")) {
					return;
				}
			}
			System.out.println(Arrays.toString(output));
			return;
		}
		for (int i = 0; i < output.length; i++) {
			if (output[i].equals("_")) {
				output[i] = "" + inputToFix;
				printKpartionSubsetsByFixingInput(inputToFix - 1, output);
				output[i] = "_";
				break;
			} else {
				String previousOutStr = output[i];
				output[i] += inputToFix;
				printKpartionSubsetsByFixingInput(inputToFix - 1, output);
				output[i] = previousOutStr;
			}
		}
	}

	@Override
	public void printPalindromicPermuation(String input) {
		PermutationQuestion.INSTANCE.printPalindromicPermuation(input);

	}

	@Override
	public void printPalindormicPartition(String input) {

		printPalindormicPartitionUsingPIE(input, "");
	}

	/**
	 * <pre>
	 * 
	 * RECURSION_STRATEGY:
	 * 
	 * 1. input_palindromic_prefix is getting fixed at each level of tree
	 *      --> so BASE CASE : the tree level where input_palindromic_prefix get exhausted.
	 *      
	 * 2.input_palindromic_prefix is tried as option at each level of tree
	 * 
	 * </pre>
	 * 
	 */
	private void printPalindormicPartitionUsingPIE(String input, String output) {

		if (input.isEmpty()) {
			System.out.println(output);
			return;
		}

		for (int i = 0; i < input.length(); i++) {
			String prefix = input.substring(0, i + 1);
			if (isPalindrome(prefix)) {
				printPalindormicPartitionUsingPIE(input.substring(i + 1), output + "(" + prefix + ")");
			}

		}

	}

	private static boolean isPalindrome(String prefix) {
		int endIndex = prefix.length() - 1;
		// avoid self-check for odd length string.
		for (int i = 0; i <= (endIndex - 1) >> 1; i++) {
			if (prefix.charAt(i) != prefix.charAt(endIndex - i)) {
				return false;
			}

		}

		return true;
	}

	@Override
	public void printKpartitionEqualSumSubsets(int[] input, int k) {
		// need to check edge cases
		if (k >= input.length) {
			System.out.println("-1");
			return;
		}

		// total sum need to be divisible by partition_count, so that each partition
		// will have equal_sum
		int totalSum = IntStream.of(input).sum();
		if (totalSum % k != 0) {
			System.out.println("-1");
			return;
		}

		String[] output = new String[k];
		Arrays.fill(output, "_");
		printKpartitionEqualSumSubsetsByFixingInput(input, 0, output, new int[k], totalSum / k);
	}

	/**
	 * <pre>
	 * RECURSION STRATEGY: 
	 * 1. Input is getting fixed at each level of tree.
	 * 2. Partitions are tried as option at each level.
	 * 
	 * Note: While fixing a given_input we should treat all the empty partitions 
	 * as same partition to avoid the permutation generation. So we should not
	 * spawn new branch for each empty partition rather just spawn single branch 
	 * against first empty partition.
	 * 
	 * If we wish to generate the permutations then we should spawn separate branch for 
	 * each empty space.
	 *
	 * </pre>
	 * 
	 */
	private void printKpartitionEqualSumSubsetsByFixingInput(int[] input, int inputIndxToFix, String[] output,
			int[] partitionSumAccumulator, int partitionSum) {

		if (inputIndxToFix == input.length) {

			for (String o : output) {
				if (o.equals("_")) {
					return;
				}
			}
			
			System.out.println(Arrays.toString(output));

			return;
		}
		for (int i = 0; i < output.length; i++) {
			if (output[i].equals("_")) {
				output[i] = "" + input[inputIndxToFix];
				partitionSumAccumulator[i] = input[inputIndxToFix];
				printKpartitionEqualSumSubsetsByFixingInput(input, inputIndxToFix + 1, output, partitionSumAccumulator,
						partitionSum);
				partitionSumAccumulator[i] = 0;
				output[i] = "_";
				break;
			} else if (partitionSumAccumulator[i] + input[inputIndxToFix] <= partitionSum) {

				partitionSumAccumulator[i] += input[inputIndxToFix];
				String previousOutStr = output[i];
				output[i] += input[inputIndxToFix];

				printKpartitionEqualSumSubsetsByFixingInput(input, inputIndxToFix + 1, output, partitionSumAccumulator,
						partitionSum);
				partitionSumAccumulator[i] -= input[inputIndxToFix];
				output[i] = previousOutStr;
			}
		}

	}

}
