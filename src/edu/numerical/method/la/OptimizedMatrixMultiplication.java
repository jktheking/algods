package edu.numerical.method.la;

import java.util.Random;

/**
 * <pre>
 * In Java 2-D Matrix is Array or arrays. So, a particular row lie in a array, whereas a particular column is
 * scattered over multiple arrays.
 * So, iterating over columns are costly in comparison to rows.
 * 
 * So, If we consider each column as column-vector and keep them in matrix of column vector, 
 * then multiplication will be fast.
 * 
 * Row vector, keeps the rows in array of array, where inner array keeps the actual element.
 * row vector:[[1,2,4,5], 
 *            [6,7,8,9],
 *            [1,8,0,9]]
 *      
 *Similarly,               
 *Column vector, keeps the columns in array of array, where inner array keeps the actual column element.
 * col vector:[[1,2,4,5], 
 *            [6,7,8,9],
 *            [1,8,0,9]]
 *            
 * Result of multiplication of Row and Column matrix:
 *   M(11) =  &Sigma;ROW_VEC(1)*COL_VEC(1)
 *   M(12) =  &Sigma;ROW_VEC(1)*COL_VEC(2)
 * 
 * 
 * </pre>
 * 
 */
public class OptimizedMatrixMultiplication {

	private static final int A_ROW_DIMENSION = 4;
	private static final int COMMON_DIMENSION = 3;
	private static final int B_COLUMN_DIMENSION = 2;
	private static final int A[][] = new int[A_ROW_DIMENSION][COMMON_DIMENSION];
	private static final int B[][] = new int[COMMON_DIMENSION][B_COLUMN_DIMENSION];
	private static final int C[][] = new int[A_ROW_DIMENSION][B_COLUMN_DIMENSION];

	public static void main(String[] args) {
		System.out.println("A matrix:\n");
		initializeMatrix(A);
		System.out.println("\nB matrix:\n");
		initializeMatrix(B);
		System.out.println("\nB transpose matrix:\n");
		int COLS_B[][] = transpose(B);

		System.out.println("\n Multiplication matrix:\n");
		multiplyOptimally(A, COLS_B);
	}

	private static void multiplyOptimally(int ROW_VEC[][], int COL_VEC[][]) {

		for (int i = 0; i < A_ROW_DIMENSION; i++) {

			for (int j = 0; j < B_COLUMN_DIMENSION; j++) {

				for (int k = 0; k < COMMON_DIMENSION; k++) {
					/**
					 * <pre>
					 * In case of matrix ROW_VEC for a given i, k is traversing the continuous memory allocation
					 * ROW_VEC[i]:[0,1,2,..]
					 * 
					 * Also, for the matrix COL_VEC for a given j,k is traversing the continuous memory allocation
					 *  COL_VEC[j]:[0,1,2,..]
					 * </pre>
					 */
					C[i][j] = C[i][j] + ROW_VEC[i][k] * COL_VEC[j][k];
				}
			}
		}

		// lets iterate the C matrix with i and j index to print the result
		for (int i = 0; i < A_ROW_DIMENSION; i++) {
			for (int j = 0; j < B_COLUMN_DIMENSION; j++) {
				if (j != B_COLUMN_DIMENSION - 1) {
					System.out.print(C[i][j] + ",");
				} else {
					System.out.print(C[i][j] + "\n");
				}

			}
		}

	}

	private static int[][] transpose(int ROWS[][]) {
		final int t_rows = ROWS[0].length;
		final int t_cols = ROWS.length;
		int t[][] = new int[t_rows][t_cols];

		for (int i = 0; i < t_rows; i++) {
			for (int j = 0; j < t_cols; j++) {
				t[i][j] = ROWS[j][i];
				if (j != t_cols - 1) {
					System.out.print(t[i][j] + ",");
				} else {
					System.out.print(t[i][j] + "\n");
				}
			}

		}
		return t;
	}

	private static void initializeMatrix(int M[][]) {
		int rows = M.length;
		int cols = M[0].length;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				M[i][j] = new Random().nextInt(5);
				if (j != cols - 1) {
					System.out.print(M[i][j] + ",");
				} else {
					System.out.print(M[i][j] + "\n");
				}
			}

		}

	}
}
