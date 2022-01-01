package edu.algo.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrossWord {

	void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input);

	CrossWord INSTANCE = new CrossWord() {

		@Override
		public void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input) {

			solveCrossWordByFixingPos(crossWordPuzzle, input, 0, new HashMap<>(), new HashMap<>());
		}

		/**
		 * Strategy: fixing position and trying all the words horizontally and
		 * vertically as options.
		 * 
		 */
		private void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input, int posToFix,
				HashMap<String, Set<Integer>> solutionCache, HashMap<String, Integer> currentSolution) {

			// base case
			if (posToFix == crossWordPuzzle.length * crossWordPuzzle[0].length || input.isEmpty()) {
				if (input.isEmpty() && !checkForDuplicateSolutionAndStoreIfAbsent(solutionCache, currentSolution)) {
					System.out.println(solutionCache);
					printMatrix(crossWordPuzzle);
				}
				// clear the current solution cache so that, we can try other solution.
				currentSolution.clear();
				return;
			}

			int row = posToFix / crossWordPuzzle[0].length;
			int col = posToFix % crossWordPuzzle[0].length;

			posToFix++;
			// only non-dead cell can be considered for posToFix
			if (isDeadCell(crossWordPuzzle, row, col)) {
				// donothing and go to the nextPosToFix
				solveCrossWordByFixingPos(crossWordPuzzle, input, posToFix, solutionCache, currentSolution);

			} else {

				boolean doesCurrentTrySucceed = false;

				BitSet occupiedCharsInRow = new BitSet(crossWordPuzzle[0].length);
				BitSet occupiedCharsInCol = new BitSet(crossWordPuzzle.length);

				int horizontalOrigin = getHorizontalStartPoint(crossWordPuzzle, row, col);
				int verticalOrigin = getVerticalStartPoint(crossWordPuzzle, row, col);

				// originPos is used to save the current solution, so that duplicate solutions
				// can be avoided to print
				int horizontalOriginPos = row * crossWordPuzzle[0].length + horizontalOrigin;
				int verticalOriginPos = verticalOrigin * crossWordPuzzle[0].length + col;

				for (int i = 0; i < input.size(); i++) {

					String word = input.get(i);

					if (canPlaceWordHorizontally(crossWordPuzzle, word, row, horizontalOrigin)) {
						
						// horizontal position is stored in cache as positive value
						currentSolution.put(word, +horizontalOriginPos);

						doesCurrentTrySucceed = true;
						input.remove(i);
						placeWordHorizontally(crossWordPuzzle, row, horizontalOrigin, word, occupiedCharsInRow);
						
						solveCrossWordByFixingPos(crossWordPuzzle, input, posToFix, solutionCache, currentSolution);
						//backtrack
						removeWordHorizontally(crossWordPuzzle, row, word, occupiedCharsInRow);
						input.add(i, word);
					}

					if (canPlaceWordVertically(crossWordPuzzle, word, verticalOrigin, col)) {
						// vertical position is stored in cache as negative value, and -0 is stored as
						// Integer.MIN_VALUE
						currentSolution.put(word, verticalOriginPos == 0 ? Integer.MIN_VALUE : -verticalOriginPos);

						doesCurrentTrySucceed = true;
						input.remove(i);
						placeWordVertically(crossWordPuzzle, verticalOrigin, col, word, occupiedCharsInCol);
						
						solveCrossWordByFixingPos(crossWordPuzzle, input, posToFix, solutionCache, currentSolution);
						//backtrack
						removeWordVertically(crossWordPuzzle, col, word, occupiedCharsInCol);
						input.add(i, word);
					}
				}

				if (!doesCurrentTrySucceed) {
					// donothing and go to next posToFix
					solveCrossWordByFixingPos(crossWordPuzzle, input, posToFix, solutionCache, currentSolution);
				}
			}
		}

		private boolean checkForDuplicateSolutionAndStoreIfAbsent(Map<String, Set<Integer>> solutionCache,
				Map<String, Integer> currentSolution) {

			boolean isSolutionPresent = true;

			for (Map.Entry<String, Integer> sol : currentSolution.entrySet()) {

				String word = sol.getKey();
				Integer originPos = sol.getValue();

				if (!solutionCache.containsKey(word) || !solutionCache.get(word).contains(originPos)) {
					isSolutionPresent = false;
				}

			}

			// if solution is absent then palce the current solution in cache
			if (!isSolutionPresent) {
				currentSolution.forEach((word, originPos) -> {

					solutionCache.computeIfPresent(word, (k, v) -> {
						v.add(originPos);
						return v;
					});

					solutionCache.computeIfAbsent(word, k -> {
						Set<Integer> solutions = new HashSet<>();
						solutions.add(originPos);
						return solutions;

					});

				});

			}

			return isSolutionPresent;
		}

		private int getHorizontalStartPoint(char[][] crossWordPuzzle, int row, int col) {
			int startCol = 0;
			for (int i = col; i >= 0; i--) {
				if (crossWordPuzzle[row][i] == '+') {
					startCol = i + 1;
					break;
				}
			}
			return startCol;
		}

		private int getVerticalStartPoint(char[][] crossWordPuzzle, int row, int col) {
			int startRow = 0;
			for (int i = row; i >= 0; i--) {
				if (crossWordPuzzle[i][col] == '+') {
					startRow = i + 1;
					break;
				}
			}
			return startRow;
		}

		private boolean isDeadCell(char[][] crossWordPuzzle, int row, int col) {
			return crossWordPuzzle[row][col] == '+';
		}

		private boolean canPlaceWordHorizontally(char[][] crossWordPuzzle, String word, int row, int startCol) {
			// check if word length crosses the wall of board
			if (startCol + word.length() > crossWordPuzzle[0].length) {
				return false;
			}

			// check post word occupancy dead-cell is present or not for the case where word
			// is not ending at last cell
			if (startCol + word.length() < crossWordPuzzle[0].length
					&& crossWordPuzzle[row][startCol + word.length()] != '+') {
				return false;
			}

			for (int i = 0; i < word.length(); i++) {
				// check is dead_cell or not
				if (crossWordPuzzle[row][startCol+i] == '+') {
					return false;
					// any alphabet is there then compare with the word's corresponding alphabet
				} else if (crossWordPuzzle[row][startCol+i] != '_' && crossWordPuzzle[row][startCol+i] != word.charAt(i)) {
					return false;
				}
			}

			return true;

		}

		private void placeWordHorizontally(char[][] crossWordPuzzle, int row, int startCol, String word,
				BitSet occupiedCharsInRow) {
			for (int i = 0; i <  word.length(); i++) {
				if (crossWordPuzzle[row][startCol+ i] == '_') {
					crossWordPuzzle[row][startCol+ i] = word.charAt(i);
					occupiedCharsInRow.set(startCol + i);
				}
			}
		}

		private void removeWordHorizontally(char[][] crossWordPuzzle, int row, String word, BitSet occupiedCharsInRow) {
			for (int i = occupiedCharsInRow.nextSetBit(0); i >= 0; i = occupiedCharsInRow.nextSetBit(i + 1)) {
				crossWordPuzzle[row][i] = '_';
			}
			occupiedCharsInRow.clear();

		}

		private boolean canPlaceWordVertically(char[][] crossWordPuzzle, String word, int startRow, int col) {
			// check if word length crosses the wall of board
			if (startRow + word.length() > crossWordPuzzle.length) {
				return false;
			}

			// check post word occupancy dead-cell is present or not for the case where word
			// is not ending at last cell
			if (startRow + word.length() < crossWordPuzzle.length
					&& crossWordPuzzle[startRow + word.length()][col] != '+') {
				return false;
			}

			for (int i = startRow; i < startRow + word.length(); i++) {
				// check is dead_cell or not
				if (crossWordPuzzle[i][col] == '+') {
					return false;
					// any alphabet is there then compare with the word's corresponding alphabet
				} else if (crossWordPuzzle[i][col] != '_' && crossWordPuzzle[i][col] != word.charAt(i - startRow)) {
					return false;
				}
			}

			return true;

		}

		private void placeWordVertically(char[][] crossWordPuzzle, int startRow, int col, String word,
				BitSet occupiedCharsInCol) {
			for (int i = startRow; i < startRow + word.length(); i++) {
				if (crossWordPuzzle[i][col] == '_') {
					crossWordPuzzle[i][col] = word.charAt(i - startRow);
					occupiedCharsInCol.set(i);
				}
			}
		}

		private void removeWordVertically(char[][] crossWordPuzzle, int col, String word, BitSet occupiedCharsInCol) {
			for (int i = occupiedCharsInCol.nextSetBit(0); i >= 0; i = occupiedCharsInCol.nextSetBit(i + 1)) {
				crossWordPuzzle[i][col] = '_';
			}
			occupiedCharsInCol.clear();

		}

		private void printMatrix(char[][] matrix) {
			System.out.println();
			for (int row = 0; row < matrix.length; row++) {
				System.out.println(Arrays.toString(matrix[row]));
			}
			System.out.println();

		}

	};

	public static void main(String[] args) {

		List<String> words = new ArrayList<>();
		words.add("ant");
		words.add("and");

		char[][] crossWordBoard = { //
				{ '+', '_', '+' }, //
				{ '_', '_', '_' }, //
				{ '+', '_', '+' },//
		};

		INSTANCE.solveCrossWordByFixingPos(crossWordBoard, words);

		List<String> words1 = new ArrayList<>();
		words1.add("snack");
		words1.add("ashes");
		words1.add("aoohoe");
		words1.add("to");
		words1.add("so");
		//sst can be formed without using any empty_space, place 
		words1.add("sst");

		char[][] crossWordBoard1 = { //
				{ 's', '+', '+', '_', '+', '+' }, //
				{ '_', '+', '_', '_', '_', '+' }, //
				{ '_', '_', 'o', 'h', 'o', '_' }, //
				{ '_', '+', '+', '_', '+', '+' }, //
				{ '_', '+', '+', '_', '+', '+' },//

		};//

		INSTANCE.solveCrossWordByFixingPos(crossWordBoard1, words1);

		List<String> words2 = new ArrayList<>();
		words2.add("man");
		words2.add("mad");

		char[][] crossWordBoard2 = { //
				{ '_', '_', '_' }, //
				{ '_', '+', '+' }, //
				{ '_', '+', '+' },//
		};

		INSTANCE.solveCrossWordByFixingPos(crossWordBoard2, words2);
		
		
		char[][] crossWordBoard3 = { //
                { '+', '_', '+', '+', '+', '+', '+', '+', '+', '+' }, //
                { '+', '_', '+', '+', '+', '+', '+', '+', '+', '+' }, //
                { '+', '_', '+', '+', '+', '+', '+', '+', '+', '+' }, //
                { '+', '_', '_', '_', '_', '_', '+', '+', '_', '+' }, //
                { '+', '_', '+', '+', '+', '+', '+', '+', '_', '+' }, //
                { '+', '_', '+', '+', '+', '+', '+', '+', '_', '+' }, //
                { '+', '+', '+', '+', '+', '+', '+', '+', '_', '+' }, //
                { '+', '+', '+', '+', '+', '+', '+', '+', '_', '+' }, //
                { '+', '+', '+', '+', '+', '+', '+', '+', '_', '+' }//
        };//
		
		INSTANCE.solveCrossWordByFixingPos(crossWordBoard3, new ArrayList<>(List.of("delhi",
	             "london", "mumbai")));
		

	}

}
