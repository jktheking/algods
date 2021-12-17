package edu.algo.permcomb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CombinationSolution implements CombinationQuestion {

	public static void main(String[] args) {
		// System.out.println(INSTANCE.getGroupCombination(new String[] { "abc", "fg",
		// "hijk" }));
		// INSTANCE.printGroupCombination(new String[] { "abc", "fg", "hijk" });
		// INSTANCE.printTargetSumCombination(new int[] { 1, 2, 3, 4, 5 }, 10);
		// INSTANCE.printCombinationUingIncExcByFixingPos(4, 3);
		// INSTANCE.printCombinationUsingPermutationByFixingInput(4, "ab");
		// INSTANCE.printCombinationUsingPascalIdentityExpansionByFixingPos(4, 2);

		// INSTANCE.print2DCombinationUsingPascalIdentityExpansionByFixingPos(3, 2, 4);
		// INSTANCE.printCombinationUsingPermutationByFixingPos(4, 2);
		//INSTANCE.printCombinationIn2DElongatedArrayUsingPIEByFixingPos(3, 2, 4);
		
		INSTANCE.printNQueenCombinationUsingPIEByFixingPos(2);

	}

	@Override
	public List<String> getGroupCombination(String[] groups) {

		return getGroupCombination(groups, 0);

	}

	/**
	 * <pre>
	 * Get all possible combinations that can be formed by picking atmost one char
	 * from each group.
	 * 
	 * Example ['abc' 'fg', 'hijk']; a single combination contains 3 char one from
	 * each group. e.g. afh, afi
	 * 
	 * Total number of combinations : It is repersented by cartesian product of each
	 * group.
	 * 
	 * Total number of combinations : g1 * g2 * g3 * .. *gn; g represents element
	 * count in the respective group.
	 * 
	 * Example ['abc' 'fg', 'hijk'] : total combinations : 3 * 2 * 4 = 24
	 * 
	 * Tree Structure:
	 * 
	 * At 0th level we will have 3 branches a, b, c
	 * At 1st level, each branch will spawn 2 branches f, g
	 * At 2nd level each branch will spawn 4 branches h, i, j, k
	 * 
	 *
	 * HYPOTHESIS : getGroupCombination([g1, g2, g3]) => total  g1*g2*g3 combinations
	 * 
	 * SUBSTITUTION: collate the group g2, g3; getGroupCombination([g2, g3]) => total  g2*g3 combinations
	 * 
	 * INDUCTION strategy: need to map each element of g1 group, with each element returned
	 * by SUBSTITUTION step.  Means solution is prepared in  post-recursion step.
	 * 
	 * </pre>
	 * 
	 * TimeComplexity : O(g1*g2*g3*..*gn)
	 * 
	 */
	private List<String> getGroupCombination(String[] groups, int idx) {

		if (idx == groups.length) {
			return List.of("");
		}
		List<String> collatedGroupCombs = getGroupCombination(groups, idx + 1);
		char[] firstGroupChars = groups[idx].toCharArray();
		// solution is prepared in post-recursion
		List<String> result = new ArrayList<>();
		for (char c : firstGroupChars) {
			for (String comb : collatedGroupCombs) {
				result.add(c + comb);
			}
		}
		return result;

	}

	/**
	 * <pre>
	 * Example ['abc' 'fg', 'hijk'] : total combinations : 3 * 2 * 4 = 24
	 * 
	 * Tree Structure:
	 * At 0th level we will have 3 branches a, b, c
	 * At 1st level, each branch will spawn 2 branches f, g
	 * At 2nd level each branch will spawn 4 branches h, i, j, k
	 *  
	 * Strategy: We will maintain two variables:
	 * 1. solution_so_far : at every level we will append the options to solution_so_far
	 * 2.  idx : tree level will be maintained by index of groups array.
	 * 
	 * HYPOTHESIS: printGroupCombination(['abc' 'fg'],  "") --> prints all the possible combinations
	 * 
	 * SUBSTITUTION: 
	 * printGroupCombination(['abc', 'fg', 'hijk'],  "a") : appends remaining path to "a"
	 * printGroupCombination(['abc', 'fg', 'hijk'],  "b") : appends remaining path to "b"
	 * printGroupCombination(['abc', 'fg', 'hijk'],  "c") : appends remaining path to "b"
	 * 
	 * INDUCTION: main code will append options of first_group i.e. 'abc' to solution_so_far, 
	 * and remaining path will be append by substitution step. Means solution is prepared in 
	 * pre-recursion step.
	 * 
	 * </pre>
	 * 
	 */
	@Override
	public void printGroupCombination(String[] groups) {
		printGroupCombination(groups, 0, "");
	}

	private void printGroupCombination(String[] groups, int idx, String combination) {
		if (idx == groups.length) {
			System.out.println(combination);
			return;
		}
		char[] groupElements = groups[idx].toCharArray();
		for (int option = 0; option < groupElements.length; option++) {
			printGroupCombination(groups, idx + 1, combination + groupElements[option]);

		}
	}

	/**
	 * Print all possible combinations that can be formed by picking non-rpeated
	 * elements of input array and whose value is equal to targetValue.
	 * 
	 */
	@Override
	public void printTargetSumCombination(int[] input, int targetValue) {

		String inputStr = Arrays.stream(input).mapToObj(String::valueOf).collect(Collectors.joining());

		printTargetSumComUsingPascalIdentityExpansion1(inputStr, 0, 0, "");

		// printTargetSumComUsingPascalIdentityExpansion2(input, "", 0, targetValue, 0);
	}

	private void printTargetSumComUsingPascalIdentityExpansion1(String input, int targetValue, int sumSoFar,
			String combination) {
		if (sumSoFar == targetValue) {
			System.out.println(combination);
		}
		for (int i = 0; i < input.length(); i++) {
			printTargetSumComUsingPascalIdentityExpansion1(input.substring(i + 1), targetValue,
					sumSoFar + (input.charAt(i) - '0'), combination + input.charAt(i));
		}

	}

	private void printTargetSumComUsingPascalIdentityExpansion2(int[] input, String combination, int sumSoFar,
			int targetValue, int idx) {
		if (sumSoFar == targetValue) {
			System.out.println(combination);
		}
		while (idx < input.length) {
			printTargetSumComUsingPascalIdentityExpansion2(input, combination + input[idx], sumSoFar + input[idx],
					targetValue, ++idx);
		}

	}

	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items at 'n' given positions.
	 * 
	 */
	@Override
	public void printCombinationUsingPascalIdentityByFixingPos(int positionCount, int r) {

		printCombinationUingIncExcByFixingPos(positionCount, r, 0, "");
	}

	private void printCombinationUingIncExcByFixingPos(int positionCount, int r, int posToFix, String output) {

		if (positionCount == posToFix) {
			if (r == 0) {
				System.out.println(output);
			}

			return;
		}

		// Exclude
		printCombinationUingIncExcByFixingPos(positionCount, r, posToFix + 1, output + "_");
		// Include
		printCombinationUingIncExcByFixingPos(positionCount, r - 1, posToFix + 1, output + "i");

	}

	/**
	 * print all the possible combinations by placing 'r' distinct items at 'n'
	 * given positions.
	 * 
	 */
	@Override
	public void printCombinationUsingPermutationByFixingInput(int positionCount, String item) {

		char[] output = new char[positionCount];
		Arrays.fill(output, '_');
		printCombinationUsingPermutationByFixingInput(item.toCharArray(), 0, output, -1);
	}

	/**
	 * Algo strategy : Prermuation strategy of fixing input and taking position as
	 * options
	 * 
	 * Note: If we allow only to place the input in lexicographic order, then we
	 * will get combinations from permutation strategy
	 * 
	 * Since, we have fixed the input means input index will advance ahead only at
	 * next level of recursion tree. So passing input_idx as method param.
	 */
	private void printCombinationUsingPermutationByFixingInput(char[] input, int inIdx, char[] output,
			int occupiedPos) {

		if (inIdx == input.length) {
			System.out.println(String.valueOf(output));
			return;
		}

		for (int pos = 0; pos < output.length; pos++) {
			if (pos > occupiedPos) {
				output[pos] = input[inIdx];
				printCombinationUsingPermutationByFixingInput(input, inIdx + 1, output, pos);
				output[pos] = '_';
			}

		}
	}

	/**
	 * print all the possible combinations by placing 'r' distinct items at 'n'
	 * given positions.
	 * 
	 */
	@Override
	public void printCombinationUsingPascalIdentityExpansionByFixingPos(int positionCount, int r) {
		char[] output = new char[positionCount];
		Arrays.fill(output, '_');
		printCombinationUsingPascalIdentityExpansionByFixingPos(output, r, 0);

	}

	/**
	 * Here position is used for both: 1. fixing at each level 2. and trying options
	 * 
	 */
	private void printCombinationUsingPascalIdentityExpansionByFixingPos(char[] output, int r, int posToFix) {
		if (r == 0) {
			System.out.println(String.valueOf(output));
		}
		while (posToFix < output.length) {
			int currentPos = posToFix;
			output[currentPos] = 'i';
			printCombinationUsingPascalIdentityExpansionByFixingPos(output, r - 1, ++posToFix);
			output[currentPos] = '_';
		}
	}

	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items in 2D array.
	 * 
	 * @param r : number of identical items
	 * @parm rows : row count
	 * @parm cols : column count
	 * 
	 */
	@Override
	public void printCombinationIn2DArrayUsingPIEByFixingPos(int rows, int cols, int r) {
		char[][] output = new char[rows][cols];
		for (char[] o : output) {
			Arrays.fill(o, '_');
		}
		print2DCombinationUsingPIEByFixingPos(output, r, 0, 0);
	}

	private void print2DCombinationUsingPIEByFixingPos(char[][] output, int r, int rowPosToFix, int colPosToFix) {
		if (r == 0) {
			printMatrix(output);
		}
		// since we are taking position as options so iterate the output array
		while (rowPosToFix < output.length) {

			int currentRow = rowPosToFix;

			while (colPosToFix < output[0].length) {
				int currentCol = colPosToFix;
				++colPosToFix;
				output[currentRow][currentCol] = 'i';
				print2DCombinationUsingPIEByFixingPos(output, r - 1, rowPosToFix, colPosToFix);
				output[currentRow][currentCol] = '_';
			}

			colPosToFix = 0;
			rowPosToFix++;
		}
	}

	private void printMatrix(char[][] matrix) {
		System.out.println();
		for (int row = 0; row < matrix.length; row++) {
			System.out.println(Arrays.toString(matrix[row]));
		}

	}

	@Override
	public void printCombinationUsingPermutationByFixingPos(int positionCount, int r) {

		String item = "";
		for (int i = 0; i < r; i++) {
			item += "i";
		}
		PermutationQuestion.INSTANCE.printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(positionCount, item);

	}

	@Override
	public void printCombinationIn2DElongatedArrayUsingPIEByFixingPos(int rows, int cols, int r) {

		char[][] output = new char[rows][cols];
		for (char[] o : output) {
			Arrays.fill(o, '_');
		}
		printCombinationIn2DElongatedArrayUsingPIEByFixingPos(output, r, 0);
	}

	/**
	 * <pre>
	 *Pascal_Identity_Expansion(PIE) tree formation strategy: 
	 *
	 *Position is used for both:
	 *1. fixing at tree level
	 *2. tried as options(tree branches)
	 *
	 *Implementation note:
	 *why do we have while -loop and option back-tracking ? 
	 *Becuase positions are tried as options at a particular level of tree.
	 * </pre>
	 * 
	 */
	private void printCombinationIn2DElongatedArrayUsingPIEByFixingPos(char[][] output, int r, int posToFix) {
		if (r == 0) {
			printMatrix(output);
		}
		while (posToFix < output.length * output[0].length) {
			int row = posToFix / output[0].length;
			int col = posToFix % output[0].length;
			output[row][col] = 'i';
			printCombinationIn2DElongatedArrayUsingPIEByFixingPos(output, r - 1, ++posToFix);
			output[row][col] = '_';
		}
	}

	@Override
	public void printNQueenCombinationUsingPIEByFixingPos(int n) {
		char[][] output = new char[n][n];
		for (char[] o : output) {
			Arrays.fill(o, '_');
		}
		printNQueenCombinationUsingPIEByFixingPos(output, n, 0);
	}

	private void printNQueenCombinationUsingPIEByFixingPos(char[][] output, int n, int posToFix) {

		if (n == 0) {
			printMatrix(output);
		}

		while (posToFix < output.length * output[0].length) {
			int row = posToFix / output[0].length;
			int col = posToFix % output[0].length;
			posToFix++;
			if (isValidQueenPlacementByFixingPos(output, row, col)) {
				output[row][col] = 'q';
				printNQueenCombinationUsingPIEByFixingPos(output, n - 1, posToFix);
				output[row][col] = '_';
			}
		}
	}

	/**
	 * <pre>
	 * 1. We will not validate queen placement on next positions as we are 
	 * fixings positions, so next positions will be empty till leaf node 
	 * on the path of euler_tour.
	 * 
	 * 
	 * Remaining validation part on board:  
	 * 1. above-vertical columns
	 * 2. above-left-diagonal rows-cols 
	 * 3. above-right-diagonal rows-cols
	 * 4. horizontal-left columns
	 * 
	 * </pre>
	 */
	public static boolean isValidQueenPlacementByFixingPos(char[][] board, int row, int col) {

		// above vertical columns
		for (int i = row - 1; i >= 0; i--) {
			if (board[i][col] != '_')
				return false;
		}

		// above left-diagonal rows-cols
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] != '_')
				return false;
		}

		// above right-diagonal rows-cols
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] != '_')
				return false;
		}

		// horizontal left
		for (int i = col-1; i >= 0; i--) {
			if (board[row][i] != '_')
				return false;
		}

		return true;
	}

}
