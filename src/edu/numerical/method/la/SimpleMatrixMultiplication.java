package edu.numerical.method.la;

import java.util.Random;

/**
 * A particular index value for output matrix C:<br/>
 * <b> C(i,j) = &Sigma;<sub>k</sub>A(i,k)* B(k,j)
 * 
 * <pre>
 *  1. Will iterate over the result Matrix(C) to fill the each position, so needs two loop
 *  2. Summation of right side need one extra loop.
 *  
 *  Since 3 loops are involved so time complexity: O(n^3)
 * </pre>
 * 
 */

public class SimpleMatrixMultiplication {

	// dimensions:
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
		System.out.println("\nC matrix:\n");
		multiplyMatrix();
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

	private static void multiplyMatrix() {

		// lets iterate the C matrix with i and j index
		for (int i = 0; i < A_ROW_DIMENSION; i++) {
			for (int j = 0; j < B_COLUMN_DIMENSION; j++) {

				// number of terms in summation is equal to COMMON_DIMENSION
				for (int k = 0; k < COMMON_DIMENSION; k++) {
					/**
					 * <pre>
					 * In case of matrix A for a given i, k is traversing the continuous memory allocation
					 * A[i]:[0,1,2,..]
					 * 
					 * But, in case of matrix B for a given j, k is traversing the column, so k is
					 * not traversing the continuous memory, it is going through
					 * multiple rows.
					 * B[0][j]
					 * B[1][j]
					 * B[2][j]
					 *  .....
					 *  .....
					 * </pre>
					 */
					C[i][j] = C[i][j] + A[i][k] * B[k][j];
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

}
