package edu.algo.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface CrossWord {

	void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input);

	void solveCrossWordByFindingStartAndFixingPos(char[][] crossWordPuzzle, List<String> input);

	void solveCrossWordByFixingInput(char[][] crossWordPuzzle, List<String> input);

	void solveCrossWordByFindingStartAndFixingInput(char[][] crossWordPuzzle, List<String> input);

	CrossWord INSTANCE = new CrossWord() {

		@Override
		public void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input) {

			solveCrossWordByFixingPos(crossWordPuzzle, input, 0, new LinkedHashMap<>(), new LinkedHashMap<>());
		}

		/**
		 * Strategy: fixing position and trying all the words horizontally and
		 * vertically as options.
		 * 
		 * Why are we getting duplicate solutions ?
		 * 
		 * Because at a given posToFix, we are just not trying to place the words from
		 * the posToFix and ahead rather we are also trying to place the words on
		 * previous positions by finding the origin. And we know any combinatorics which
		 * place the element on previous_position than the posToFix may generate
		 * duplicates.
		 * 
		 * Why there is no need to clear the currentSolution, if we don't invoke the
		 * currentSolution.clear() still the solution be correct ?
		 * 
		 * Becasue recursio follows the euler_path, so when the control reaches the tip
		 * and backtrack and moves to second branch it just overrides the requisite
		 * words. Since we are printing only when the input.isEmpty() so there will not
		 * be any stale state.
		 * 
		 * But if we clear the currentSolution, it will be little performant as number
		 * of comparision will be reduced for duplicate_check.
		 * 
		 */
		private void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input, int posToFix,
				HashMap<String, Set<Integer>> solutionCache, HashMap<String, Integer> currentSolution) {

			// base case
			if (posToFix == crossWordPuzzle.length * crossWordPuzzle[0].length || input.isEmpty()) {

				if (input.isEmpty() && !checkForDuplicateSolutionAndStoreIfAbsent(solutionCache, currentSolution)) {
					System.out.println("solutionCache:" + solutionCache);
					printMatrix(crossWordPuzzle);
				}
				// TODO: un-comment this line to improve the performance.
				// currentSolution.clear();
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
						// backtrack
						removeWordHorizontally(crossWordPuzzle, row, occupiedCharsInRow);
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
						// backtrack
						removeWordVertically(crossWordPuzzle, col, occupiedCharsInCol);
						input.add(i, word);
					}
				}

				if (!doesCurrentTrySucceed) {
					// donothing and go to next posToFix
					solveCrossWordByFixingPos(crossWordPuzzle, input, posToFix, solutionCache, currentSolution);
				}
			}
		}

		/**
		 * 
		 * This method will produce duplicates, to remove duplicates please add
		 * solutionCache and currentSolution as mentioned in
		 * method#solveCrossWordByFixingPos
		 * 
		 * 
		 */
		@Override
		public void solveCrossWordByFindingStartAndFixingPos(char[][] crossWordPuzzle, List<String> input) {

			List<Integer> candidateHorizontalStartPosList = new ArrayList<>();
			List<Integer> candidateVerticalStartPostList = new ArrayList<>();

			for (int i = 0; i < crossWordPuzzle.length; i++) {
				for (int j = 0; j < crossWordPuzzle[0].length; j++) {

					if (!isDeadCell(crossWordPuzzle, i, j)) {

						if (isValidHorizontalStart(crossWordPuzzle, i, j)) {
							candidateHorizontalStartPosList.add(i * crossWordPuzzle[0].length + j);
						}

						if (isValidVerticalStart(crossWordPuzzle, i, j)) {
							candidateVerticalStartPostList.add(i * crossWordPuzzle[0].length + j);
						}

					}
				}

			}

			solveCrossWordByFixingPos(crossWordPuzzle, input, candidateHorizontalStartPosList, 0,
					candidateVerticalStartPostList, 0);

		}

		private void solveCrossWordByFixingPos(char[][] crossWordPuzzle, List<String> input,
				List<Integer> candidateHorizontalStartPosList, int horizontalPosIdx,
				List<Integer> candidateVerticalStartPostList, int verticalPosIdx) {

			if (horizontalPosIdx == candidateHorizontalStartPosList.size()
					&& verticalPosIdx == candidateVerticalStartPostList.size()) {
				printMatrix(crossWordPuzzle);
				return;

			}

			BitSet occupiedCharsInRow = null;

			BitSet occupiedCharsInCol = null;

			int hr = -1;
			int hc = -1;
			if (horizontalPosIdx < candidateHorizontalStartPosList.size()) {
				occupiedCharsInRow = new BitSet(crossWordPuzzle[0].length);
				hr = candidateHorizontalStartPosList.get(horizontalPosIdx) / crossWordPuzzle[0].length;
				hc = candidateHorizontalStartPosList.get(horizontalPosIdx) % crossWordPuzzle[0].length;

			}

			int vr = -1;
			int vc = -1;
			if (verticalPosIdx < candidateVerticalStartPostList.size()) {
				occupiedCharsInCol = new BitSet(crossWordPuzzle.length);
				vr = candidateVerticalStartPostList.get(verticalPosIdx) / crossWordPuzzle[0].length;
				vc = candidateVerticalStartPostList.get(verticalPosIdx) % crossWordPuzzle[0].length;
			}
			for (int i = 0; i < input.size(); i++) {

				String word = input.get(i);

				if (hr != -1 && canPlaceWordHorizontally(crossWordPuzzle, word, hr, hc)) {

					input.remove(i);
					placeWordHorizontally(crossWordPuzzle, hr, hc, word, occupiedCharsInRow);

					solveCrossWordByFixingPos(crossWordPuzzle, input, candidateHorizontalStartPosList,
							horizontalPosIdx + 1, candidateVerticalStartPostList, verticalPosIdx);
					// backtrack
					removeWordHorizontally(crossWordPuzzle, hr, occupiedCharsInRow);

					input.add(i, word);

				}

				if (vr != -1 && canPlaceWordVertically(crossWordPuzzle, word, vr, vc)) {

					input.remove(i);
					placeWordVertically(crossWordPuzzle, vr, vc, word, occupiedCharsInCol);

					solveCrossWordByFixingPos(crossWordPuzzle, input, candidateHorizontalStartPosList, horizontalPosIdx,
							candidateVerticalStartPostList, verticalPosIdx + 1);
					// backtrack
					removeWordVertically(crossWordPuzzle, vc, occupiedCharsInCol);

					input.add(i, word);

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
						Set<Integer> solutions = new LinkedHashSet<>();
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
				if (crossWordPuzzle[row][startCol + i] == '+') {
					return false;
					// any alphabet is there then compare with the word's corresponding alphabet
				} else if (crossWordPuzzle[row][startCol + i] != '_'
						&& crossWordPuzzle[row][startCol + i] != word.charAt(i)) {
					return false;
				}
			}

			return true;

		}

		private void placeWordHorizontally(char[][] crossWordPuzzle, int row, int startCol, String word,
				BitSet occupiedCharsInRow) {
			for (int i = 0; i < word.length(); i++) {
				if (crossWordPuzzle[row][startCol + i] == '_') {
					crossWordPuzzle[row][startCol + i] = word.charAt(i);
					occupiedCharsInRow.set(startCol + i);
				}
			}
		}

		private void removeWordHorizontally(char[][] crossWordPuzzle, int row, BitSet occupiedCharsInRow) {
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

		private void removeWordVertically(char[][] crossWordPuzzle, int col, BitSet occupiedCharsInCol) {
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

		@Override
		public void solveCrossWordByFixingInput(char[][] crossWordPuzzle, List<String> input) {
			solveCrossWordByFixingInput(crossWordPuzzle, input.toArray(new String[0]), 0);

		}

		private void solveCrossWordByFixingInput(char[][] crossWordPuzzle, String[] input, int inIdx) {

			if (inIdx == input.length) {
				printMatrix(crossWordPuzzle);
				return;
			}

			BitSet occupiedCharsInRow = new BitSet(crossWordPuzzle[0].length);
			BitSet occupiedCharsInCol = new BitSet(crossWordPuzzle.length);

			for (int row = 0; row < crossWordPuzzle.length; row++) {
				for (int col = 0; col < crossWordPuzzle[0].length; col++) {

					if (!isDeadCell(crossWordPuzzle, row, col)) {

						if (canPlaceWordHorizontally(crossWordPuzzle, input[inIdx], row, col)) {

							placeWordHorizontally(crossWordPuzzle, row, col, input[inIdx], occupiedCharsInRow);
							solveCrossWordByFixingInput(crossWordPuzzle, input, inIdx + 1);
							removeWordHorizontally(crossWordPuzzle, row, occupiedCharsInRow);
						}

						if (canPlaceWordVertically(crossWordPuzzle, input[inIdx], row, col)) {

							placeWordVertically(crossWordPuzzle, row, col, input[inIdx], occupiedCharsInCol);
							solveCrossWordByFixingInput(crossWordPuzzle, input, inIdx + 1);
							removeWordVertically(crossWordPuzzle, col, occupiedCharsInCol);
						}

					}
				}
			}

		}

		@Override
		public void solveCrossWordByFindingStartAndFixingInput(char[][] crossWordPuzzle, List<String> input) {

			List<Integer> candidateHorizontalStartPosList = new ArrayList<>();
			List<Integer> candidateVerticalStartPostList = new ArrayList<>();

			for (int i = 0; i < crossWordPuzzle.length; i++) {
				for (int j = 0; j < crossWordPuzzle[0].length; j++) {

					if (!isDeadCell(crossWordPuzzle, i, j)) {

						if (isValidHorizontalStart(crossWordPuzzle, i, j)) {
							candidateHorizontalStartPosList.add(i * crossWordPuzzle[0].length + j);
						}

						if (isValidVerticalStart(crossWordPuzzle, i, j)) {
							candidateVerticalStartPostList.add(i * crossWordPuzzle[0].length + j);
						}

					}
				}

			}

			solveCrossWordByFixingInput(crossWordPuzzle, input.toArray(new String[0]), 0,
					candidateHorizontalStartPosList, candidateVerticalStartPostList);

		}

		private void solveCrossWordByFixingInput(char[][] crossWordPuzzle, String[] input, int inIdx,
				List<Integer> candidateHorizontalStartPosList, List<Integer> candidateVerticalStartPostList) {

			if (inIdx == input.length) {
				printMatrix(crossWordPuzzle);
				return;
			}

			BitSet occupiedCharsInRow = new BitSet(crossWordPuzzle[0].length);
			BitSet occupiedCharsInCol = new BitSet(crossWordPuzzle.length);
			for (int i = 0; i < candidateHorizontalStartPosList.size(); i++) {
				int row = candidateHorizontalStartPosList.get(i) / crossWordPuzzle[0].length;
				int col = candidateHorizontalStartPosList.get(i) % crossWordPuzzle[0].length;
				if (canPlaceWordHorizontally(crossWordPuzzle, input[inIdx], row, col)) {

					placeWordHorizontally(crossWordPuzzle, row, col, input[inIdx], occupiedCharsInRow);
					solveCrossWordByFixingInput(crossWordPuzzle, input, inIdx + 1, candidateHorizontalStartPosList,
							candidateVerticalStartPostList);
					removeWordHorizontally(crossWordPuzzle, row, occupiedCharsInRow);
				}

			}

			for (int i = 0; i < candidateVerticalStartPostList.size(); i++) {
				int row = candidateVerticalStartPostList.get(i) / crossWordPuzzle[0].length;
				int col = candidateVerticalStartPostList.get(i) % crossWordPuzzle[0].length;

				if (canPlaceWordVertically(crossWordPuzzle, input[inIdx], row, col)) {
					placeWordVertically(crossWordPuzzle, row, col, input[inIdx], occupiedCharsInCol);
					solveCrossWordByFixingInput(crossWordPuzzle, input, inIdx + 1, candidateHorizontalStartPosList,
							candidateVerticalStartPostList);
					removeWordVertically(crossWordPuzzle, col, occupiedCharsInCol);
				}
			}

		}

		private boolean isValidHorizontalStart(char[][] crossWordPuzzle, int row, int col) {

			// checking previous position of current col
			if (col != 0 && crossWordPuzzle[row][col - 1] != '+') {
				return false;
			}

			// checking postion after the current col
			if (col + 1 == crossWordPuzzle[0].length || crossWordPuzzle[row][col + 1] == '+') {
				return false;
			}

			return true;
		}

		private boolean isValidVerticalStart(char[][] crossWordPuzzle, int row, int col) {

			// checking above position of current row
			if (row != 0 && crossWordPuzzle[row - 1][col] != '+') {
				return false;
			}

			// checking postion below the current col
			if (row + 1 == crossWordPuzzle[0].length || crossWordPuzzle[row + 1][col] == '+') {
				return false;
			}

			return true;
		}

	};

	public static void main(String[] args) {

		char[][] crossWordBoard = { //
				{ '+', '_', '+' }, //
				{ '_', '_', '_' }, //
				{ '+', '_', '+' },//
		};

		// INSTANCE.solveCrossWordByFixingPos(crossWordBoard, new
		// ArrayList<>(List.of("ant", "and")));
		// INSTANCE.solveCrossWordByFixingInput(crossWordBoard, new
		// ArrayList<>(List.of("ant", "and")));

		// INSTANCE.solveCrossWordByFindingStartAndFixingInput(crossWordBoard, new
		// ArrayList<>(List.of("ant", "and")));

		INSTANCE.solveCrossWordByFindingStartAndFixingPos(crossWordBoard, new ArrayList<>(List.of("ant", "and")));

		List<String> words1 = new ArrayList<>();
		words1.add("snack");
		words1.add("ashes");
		words1.add("aoohoe");
		words1.add("to");
		words1.add("so");
		// sst can be formed without using any empty_space, place
		words1.add("sst");

		char[][] crossWordBoard1 = { //
				{ 's', '+', '+', '_', '+', '+' }, //
				{ '_', '+', '_', '_', '_', '+' }, //
				{ '_', '_', 'o', 'h', 'o', '_' }, //
				{ '_', '+', '+', '_', '+', '+' }, //
				{ '_', '+', '+', '_', '+', '+' },//

		};//

		// INSTANCE.solveCrossWordByFixingPos(crossWordBoard1, words1);
		// INSTANCE.solveCrossWordByFixingInput(crossWordBoard1, words1);
		// INSTANCE.solveCrossWordByFindingStartAndFixingInput(crossWordBoard1, words1);
		INSTANCE.solveCrossWordByFindingStartAndFixingPos(crossWordBoard1, words1);

		char[][] crossWordBoard2 = { //
				{ '_', '_', '_' }, //
				{ '_', '+', '+' }, //
				{ '_', '+', '+' },//
		};

		// INSTANCE.solveCrossWordByFixingPos(crossWordBoard2, new
		// ArrayList<>(List.of("man", "mad")));
		// INSTANCE.solveCrossWordByFixingInput(crossWordBoard2, new
		// ArrayList<>(List.of("man", "mad")));
		// INSTANCE.solveCrossWordByFindingStartAndFixingInput(crossWordBoard2, new
		// ArrayList<>(List.of("man", "mad")));

		INSTANCE.solveCrossWordByFindingStartAndFixingPos(crossWordBoard2, new ArrayList<>(List.of("man", "mad")));

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

		// INSTANCE.solveCrossWordByFixingPos(crossWordBoard3, new
		// ArrayList<>(List.of("delhi", "london", "mumbai")));
		// INSTANCE.solveCrossWordByFixingInput(crossWordBoard3, new
		// ArrayList<>(List.of("delhi", "london", "mumbai")));
		// INSTANCE.solveCrossWordByFindingStartAndFixingInput(crossWordBoard3,
		// new ArrayList<>(List.of("delhi", "london", "mumbai")));

		INSTANCE.solveCrossWordByFindingStartAndFixingPos(crossWordBoard3,
				new ArrayList<>(List.of("delhi", "london", "mumbai")));

		char[][] crossWordBoard4 = { //
				{ '_', '_', '_' }, //
				{ '_', '_', '_' }, //
				{ '_', '_', '_' }, //
		};
		// INSTANCE.solveCrossWordByFixingPos(crossWordBoard4, new
		// ArrayList<>(List.of("abc", "tom", "box", "axe")));
		// INSTANCE.solveCrossWordByFixingInput(crossWordBoard4, new
		// ArrayList<>(List.of("abc", "tom", "box", "axe")));
//		INSTANCE.solveCrossWordByFindingStartAndFixingInput(crossWordBoard4,
//				new ArrayList<>(List.of("abc", "tom", "box", "axe")));

		INSTANCE.solveCrossWordByFindingStartAndFixingPos(crossWordBoard4,
				new ArrayList<>(List.of("abc", "tom", "box", "axe")));

	}

}
