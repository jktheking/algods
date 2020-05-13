package edu.pattern.print;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Given half-height h, and a string s output a rhombus pattern with a spiral in
 * it formed using the characters in the string. Direction of the spiral is
 * clockwise inward.
 * 
 * 
 * input height h = 3, String s = abcdefghijklmnopqrstuvwxyz
 * 
 * 
 * 
 */
public class SpiralClockwisePattern {
	
	private static Pattern inputValidator = Pattern.compile("[-0-9a-z]+");

	public static void main(String[] arg) {

		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		if (1 <= t && t <= 1000) {

			for (int n = 0; n <= t; n++) {
				final int h = sc.nextInt();
				final int l = sc.nextInt();
				final String inputString = sc.next();

				if ((1 <= h && h <= 1000) && (1 <= l && l <= 1000) && inputValidator.matcher(inputString).matches()) {
					printPattern(h, inputString);
				}

			}
		}
		sc.close();

	}

	private static void printPattern(final int h, final String inputString) {

		final char[] input = inputString.toCharArray();

		// All the printable characters are stored in a square matrix, formed by using
		// height, dimension is always
		// odd i.e. 2n-1
		final int squareMatrixDimension = 2 * h - 1;

		char[][] patternMatrix = new char[squareMatrixDimension][squareMatrixDimension];

		// code to fill the square matrix with clockwise spiral elements
		int elementcounter = 0;
		for (int k = 0; k < h; k++) {

			// fills left and top edge
			for (int i = h - 1, j = 0 + k; i >= 0 && j < h; i--, j++) {
				patternMatrix[i][j] = input[elementcounter % input.length];
				elementcounter++;
			}

			// fills right and top edge
			for (int i = 1 + k, j = h; i < h && j < squareMatrixDimension; i++, j++) {
				patternMatrix[i][j] = input[elementcounter % input.length];
				elementcounter++;
			}

			// fills right and bottom edge
			for (int i = h, j = squareMatrixDimension - 2 - k; i < squareMatrixDimension && j >= h - 1; i++, j--) {
				patternMatrix[i][j] = input[elementcounter % input.length];
				elementcounter++;
			}

			// fills left and bottom edge
			for (int i = squareMatrixDimension - 2 - k, j = h - 2; i >= h && j > 0; i--, j--) {
				patternMatrix[i][j] = input[elementcounter % input.length];
				elementcounter++;
			}

		}

		// prints the pattern
		for (int i = 0; i < squareMatrixDimension; i++) {
			for (int j = 0; j < squareMatrixDimension; j++) {
				if (patternMatrix[i][j] == Character.MIN_VALUE) {
					System.out.print(" ");
				} else {
					System.out.print(patternMatrix[i][j]);
				}

			}
			System.out.print("\n");
		}

	}

}
