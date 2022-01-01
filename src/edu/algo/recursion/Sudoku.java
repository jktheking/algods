package edu.algo.recursion;



import java.util.Random;

import edu.algo.algointro.RandomGenerator;

public interface Sudoku {

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

	
	Sudoku INSTANCE = new Sudoku() {

		@Override
		public void solveSudoku(int[][] sudokuPuzzle) {
			solveSudoku(sudokuPuzzle, 0);
		}

		/**
		 * STRATEGY: need to fix the postion at each level and try 1-9 as option on each
		 * postion.
		 * 
		 */
		private void solveSudoku(int[][] sudokuPuzzle, int posToFix) {

			// sudoku size is 9 X 9
			if (posToFix == 9 * 9) {
				printMatrix(sudokuPuzzle);
				return;
			}

			// Since sudoku size is 9 X9 so, sudokuPuzzle[0].length == 9
			int row = posToFix / 9;
			int col = posToFix % 9;
			posToFix++;
			if (sudokuPuzzle[row][col] == 0) {

				for (int i = 1; i < 10; i++) {
					if (isValidPlacement(sudokuPuzzle, row, col, i)) {
						sudokuPuzzle[row][col] = i;
						solveSudoku(sudokuPuzzle, posToFix);
						sudokuPuzzle[row][col] = 0;
					}

				}
			} else {
				// donothing and move on to next posToFix
				solveSudoku(sudokuPuzzle, posToFix);
			}

		}

		private boolean isValidPlacement(int[][] sudokuPuzzle, int row, int col, int i) {
			// checking entire row
			for (int c = 0; c < 9; c++) {
				if (sudokuPuzzle[row][c] == i)
					return false;
			}

			// checking entire col
			for (int r = 0; r < 9; r++) {
				if (sudokuPuzzle[r][col] == i)
					return false;
			}
			// checking that 3X3 sudoku block in which the given row,col lies.
			int startingRowOfBlock = (row / 3) * 3;
			int startingColOfBlock = (col / 3) * 3;

			for (int r = startingRowOfBlock; r < startingRowOfBlock + 3; r++) {
				for (int c = startingColOfBlock; c < startingColOfBlock + 3; c++) {

					if (sudokuPuzzle[r][c] == i)
						return false;
				}
			}

			return true;
		}

		@Override
		public int[][] generateSudoku1(int countOfMissingElements) {

			/**
			 * <pre>
			 * the diagonal matrices are independent of other empty matrices initially.
			 * 
			 * Total solution for sudoku  which contains 
			 * 1,2,3
			 * 4,5,6
			 * 7,8,9
			 * on down-right diagonal is 283575.
			 * 
			 * STRATEGY: 
			 *  1. We can pick any of the solution randomly out of 283575
			 *  2. and then remove the k-items randomly
			 * </pre>
			 */
			int[][] initalSudoku = { //
					{ 1, 2, 3, 0, 0, 0, 0, 0, 0 }, //
					{ 4, 5, 6, 0, 0, 0, 0, 0, 0 }, //
					{ 7, 8, 9, 0, 0, 0, 0, 0, 0 }, //
					{ 0, 0, 0, 1, 2, 3, 0, 0, 0 }, //
					{ 0, 0, 0, 4, 5, 6, 0, 0, 0 }, //
					{ 0, 0, 0, 7, 8, 9, 0, 0, 0 }, //
					{ 0, 0, 0, 0, 0, 0, 1, 2, 3 }, //
					{ 0, 0, 0, 0, 0, 0, 4, 5, 6 }, //
					{ 0, 0, 0, 0, 0, 0, 7, 8, 9 } };//

			int randomNthSudoku = new Random().ints(1, (283575 + 1)).findAny().getAsInt();

			int[][] solutionPuzzle = new int[9][9];
			solveSudoku(initalSudoku, 0, randomNthSudoku, solutionPuzzle, new int[1]);

			removeKRanomItemsFromTheSudoku(solutionPuzzle, countOfMissingElements);
			return solutionPuzzle;
		}

		/**
		 * Strategy: fixing position and trying 1-9 as options
		 * 
		 */
		private void solveSudoku(int[][] sudokuPuzzle, int posToFix, int randomNthSudoku, int[][] solutionPuzzle,
				int[] sudokuCounter) {

			if (sudokuCounter[0] == randomNthSudoku)
				return;

			// sudoku size is 9 X 9
			if (posToFix == 9 * 9) {
				// copy the nth sudoku the solutionPuzzle
				sudokuCounter[0] += 1;
				if (sudokuCounter[0] == randomNthSudoku) {
					for (int i = 0; i < 9; i++) {
						System.arraycopy(sudokuPuzzle[i], 0, solutionPuzzle[i], 0, 9);
					}
				}
				return;
			}

			// Since sudoku size is 9 X9 so, sudokuPuzzle[0].length == 9
			int row = posToFix / 9;
			int col = posToFix % 9;
			posToFix++;
			if (sudokuPuzzle[row][col] == 0) {

				for (int i = 1; i < 10; i++) {
					if (isValidPlacement(sudokuPuzzle, row, col, i)) {
						sudokuPuzzle[row][col] = i;
						solveSudoku(sudokuPuzzle, posToFix, randomNthSudoku, solutionPuzzle, sudokuCounter);
						sudokuPuzzle[row][col] = 0;
					}
				}

			} else {
				// donothing and move on to next posToFix
				solveSudoku(sudokuPuzzle, posToFix, randomNthSudoku, solutionPuzzle, sudokuCounter);
			}

		}

		@Override
		public int[][] generateSudoku2(int countOfMissingElements) {

			/**
			 * <pre>
			 * the diagonal matrices are independent of other empty matrices initially.

			 * STRATEGY: 
			 *  1. Fill the 3 diagonal_matrices of size 3X3 with random allowed values.
			 *  2. Count all the possible solutions.
			 *  3. Pick the random solution
			 *  2. and then remove the k-items randomly
			 * </pre>
			 */
			// create the initalSudoku having 3 random diagonal_matrices

			int[][] initialSudoku = new int[9][9];
			// fill diagonal_matrix
			for (int i = 0; i <= 6; i = i + 3) {
				fillSudokuDiagonalMatrixRandomly(initialSudoku, i, i);
			}
			System.out.print("initial_sudoku");
			printMatrix(initialSudoku);
			int[] counter = new int[1];
			solveAndCountSudokuSolutions(initialSudoku, counter, 0);
			// generate the nth random number in the range of 1 to 'counter + 1'.
			int randomNthSudoku = new Random().ints(1, (counter[0] + 1)).findAny().getAsInt();
			System.out.println(
					String.format("\ntotal available sudoku:%s and picking %sth sudoku", counter[0], randomNthSudoku));
			int[][] solutionPuzzle = new int[9][9];
			solveSudoku(initialSudoku, 0, randomNthSudoku, solutionPuzzle, new int[1]);
			printMatrix(solutionPuzzle);
			removeKRanomItemsFromTheSudoku(solutionPuzzle, countOfMissingElements);
			return solutionPuzzle;

		}

		private void solveAndCountSudokuSolutions(int[][] sudokuPuzzle, int[] counter, int posToFix) {

			// sudoku size is 9 X 9
			if (posToFix == 9 * 9) {
				counter[0] = counter[0] + 1;
				return;
			}

			// Since sudoku size is 9 X9 so, sudokuPuzzle[0].length == 9
			int row = posToFix / 9;
			int col = posToFix % 9;
			posToFix++;
			if (sudokuPuzzle[row][col] == 0) {

				for (int i = 1; i < 10; i++) {
					if (isValidPlacement(sudokuPuzzle, row, col, i)) {
						sudokuPuzzle[row][col] = i;
						solveAndCountSudokuSolutions(sudokuPuzzle, counter, posToFix);
						sudokuPuzzle[row][col] = 0;
					}

				}
			} else {
				// donothing and move on to next posToFix
				solveAndCountSudokuSolutions(sudokuPuzzle, counter, posToFix);
			}

		}

		private void fillSudokuDiagonalMatrixRandomly(int[][] sudoku, int initialRow, int initialCol) {

			int[] randomVals = RandomGenerator.getDistinctIntegers(1, 10, 9);
			int i = 0;
			for (int r = initialRow; r < initialRow + 3; r++) {
				for (int c = initialCol; c < initialCol + 3; c++) {
					sudoku[r][c] = randomVals[i++];
				}
			}

		}

		private void removeKRanomItemsFromTheSudoku(int[][] sudoku, int k) {
			// generate the random number between in range 0 to 81(exclusive) cells, then
			// pick the
			// distinct k elements.
			for (int pos : RandomGenerator.getDistinctIntegers(0, 81, k)) {
				int row = pos / 9;
				int col = pos % 9;
				sudoku[row][col] = 0;
			}

		}

		

	};
	
	private static void printMatrix(int[][] matrix) {
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
	
	public static void main(String[] args) {
		
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

		//INSTANCE.solveSudoku(sudokuMutipleSol);

		printMatrix(INSTANCE.generateSudoku1(50));

		 printMatrix(INSTANCE.generateSudoku2(50));


	}

}
