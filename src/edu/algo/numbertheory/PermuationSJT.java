package edu.algo.numbertheory;

import java.util.Arrays;

/**
 * Permutation cyclic notation and even odd permutation:
 * https://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm
 * https://introcs.cs.princeton.edu/java/23recursion/JohnsonTrotter.java.html
 * 
 * 
 * 
 * Even-Odd permutation: <br/>
 * -------------------------
 *
 * <pre>
 * 1 2 3 4 5 Original
 *
 * 3 4 2 1 5 A Permutation
 *
 * Cycle: 1-->3-->2-->4-->1
 * Cycle: 5
 *
 * Cyclical Notation: (1 3 2 4) (5)
 *
 * Cyclical Notation using Transposition: (1 3) (3 2) (2 4)
 *
 * Explanation: We get (1 3) (3 2) (2 4) (5), but we can omit 5 as it is at same position.
 *
 * If cyclical notation using transposition contains even groups then it
 * is called even permutation otherwise odd permutation.
 *
 * Johnson trotter algorithm can be used to generate even-odd permutation. For more details
 * refer {@link https://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm}
 *
 * </pre>
 *
 * SJT Algorithm: <br/>
 * -----------------
 *
 * It is a minimal change algorithm wherein any two successive permutations are
 * obtained from each other by a single adjacent transposition (adjacent swaps).
 *
 * Time Complexity: It can be implemented to run in O(n!)
 *
 * Jargons in SJT Algorithm: <br/>
 * -------------------------------
 *
 * Directed Integer: Each element has associated direction, left or right.
 *
 * Mobile: A directed integer is said to be mobile if it is greater than its
 * immediate neighbor in the direction it is pointing to
 *
 * Pseudo Code: <br/>
 * -----------------
 *
 * <pre>
 * While permutation p has a mobile element:
 * --- 1) Find the largest mobile element 'k'
 * --- 2) Swap 'k' with the adjacent element it is pointing to.
 * --- 3) Reverse direction of all elements greater than 'k'
 * --- 4) Post reverse the input array represents a new permutation.
 * </pre>
 *
 *
 */
public class PermuationSJT {

	public static void main(String[] args) {
		int[] permutation = {0,1,2,3};
		printPermutations1(permutation);
	}

	
	public static void printPermutations2(int[] permutation) {
	//TODO: first need to understand inverse permutation,(google, youtube)	
	//TODO: https://introcs.cs.princeton.edu/java/23recursion/JohnsonTrotter.java.html
	
		
	}
	
	
	public static void printPermutations1(int[] permutation) {
		final int[] direction = new int[permutation.length];
		System.out.println(Arrays.toString(permutation));
		// we are assuming permutation array contains all the elements in increasing
		// order.
		Arrays.fill(direction, -1);
		
		int  largestMobileIdx = findLargestMobileElementIdx(permutation, direction);
	    //until we have mobile element 
		while(largestMobileIdx!=-1) {
			int directedNeighbourIdx = largestMobileIdx + direction[largestMobileIdx];
			
			int largestMobileElement =  permutation[largestMobileIdx];
			
			swap(permutation, direction, largestMobileIdx, directedNeighbourIdx);
			
			reverseDirection(permutation, direction, largestMobileElement);
			
			System.out.println(Arrays.toString(permutation));
			
			largestMobileIdx = findLargestMobileElementIdx(permutation, direction);
		}

	}

	private static int findLargestMobileElementIdx(int[] permutation, int[] direction) {

		int largestMobileIdx = -1;

		for (int i = 0; i < permutation.length; i++) {

			int directedNeighbourIdx = direction[i] + i;
			// check if the directedNeighbourIdx is valid or not
			if (directedNeighbourIdx < 0 || directedNeighbourIdx >= permutation.length) {
				continue;
			}

			// mobile element
			if (permutation[i] > permutation[directedNeighbourIdx]) {
				// largest mobile index
				if (largestMobileIdx == -1 || permutation[i] > permutation[largestMobileIdx]) {
					largestMobileIdx = i;
				}
			}

		}

		return largestMobileIdx;

	}

	private static void swap(int[] permutation, int[] direction, int i, int j) {
		int temp = permutation[i];
		permutation[i] = permutation[j];
		permutation[j] = temp;
		
		
		temp = direction[i];
		direction[i]=direction[j];
		direction[j]= temp;

	}

	private static void reverseDirection(int[] permutation, int[] direction, int largestMobileElement) {
		for (int i = 0; i < permutation.length; i++) {
			if (permutation[i] > largestMobileElement)
				direction[i] = -direction[i];

		}

	}

}
