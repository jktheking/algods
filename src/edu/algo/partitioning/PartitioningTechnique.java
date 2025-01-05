package edu.algo.partitioning;

/**
 * 
 * 
 * */
public class PartitioningTechnique {

	public static void main(String[] args) {

	}

	private static void swap(int[] input, int i, int j) {
		int temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	/**
	 * <pre>
	 * Objective: Place the pivot element at its sorted position in the array.
	 *            Any element in the array can be selected as the pivot.
	 * 
	 * Explanation:
	 * When the pivot is in its correct position, the array is divided into two sections:
	 * - Elements smaller than the pivot are on its left.
	 * - Elements larger than the pivot are on its right.
	 * 
	 * Approach: Two-Pointer Technique
	 * 
	 * lower-partition-boundary: This boundary represents the index immediately before the pivot index.
	 * 
	 * 1. Pointer 'i': represents the "lower-partition-boundary" in array seen so far.
	 *    - 'i' tracks the position for elements smaller than the pivot.
	 *    - After partitioning, the pivot will be placed at index 'i + 1'.
	 * 
	 * 2. Pointer 'j': represents "probe-pointer" scans through the array, comparing each element to the pivot.
	 * 
	 * Note:
	 * - Since we scan from the beginning, we can choose either the first or last element as the pivot.
	 * - Here, we select the last element as the pivot. Initially, the partition boundary is unknown,
	 *   so 'i' starts at -1, meaning the pivot’s initial position will be at 'i + 1' (index 0). 
	 *   C.f. with base case of single element in array
	 * </pre>
	 */

	private static final class LomutoPartition {

		private static void partitionByBoundaryTracking(int[] input, int low, int high) {

			int pivot = input[high];

			int i = low - 1;

			for (int j = low; j < high; j++) {

				// When the probe-pointer 'j' encounters an element smaller than the pivot:
				// Since 'i' indicates the lower-partition-boundary (the index of the last
				// element smaller than the pivot) in the array seen so far, we need to create
				// space for the new smaller element.
				// To do this, we increment 'i', moving the lower-partition-boundary one index
				// forward.
				// We then swap this element with the current element at the probe-pointer,
				// effectively placing the smaller element at the lower-partition-boundary.

				if (input[j] < pivot) {
					i++;
					// to avoid self-swap, we can put one condition ( i != j )
					swap(input, i, j);
				}
			}

			// finally place the pivot on its position
			swap(input, i + 1, high);

		}

	}

	/**
	 * <pre>
	 * Hoare's scheme is another partition algorithm, which is a bit more efficient
	 * than Lomuto’s partition in practice because it makes fewer swaps.
	 * 
	 * 
	 * Algorithm:
	 * - Choose the first element as the pivot. 
	 * - Use two indices: one starting from the left (i) and another from the right (j). 
	 * - Increment i until you find an element greater/equal to the pivot, and 
	 * - decrement j until you find an element smaller/equal to the pivot. 
	 * - Swap those elements. 
	 * - Continue this process until i and j cross each other.
	 *  
	 * Note: post-swap we need to increment 'i' and 'j' so that algorithm can scan the remaining 
	 * elements.          
	 * 
	 * 
	 * Algorithm Characteristics:
	 * 
	 * - 'i' represents the lower-partition-boundary when algorithm is in running state.
	 * - 'j' represents the upper-partition-boundary when algorithm is in running state.
	 * 
	 * -  At the end, j is the partition index, and elements to the 'left' of j 
	 *    are less than or equal to the pivot, and elements to the 'right' of j are
	 *    greater than or equal to pivot.
	 *    
	 * - The pivot isn’t necessarily placed in its final position; 
	 *   it’s only separated from the other elements.
	 * 
	 * 
	 *  corner case tracing: {5,5,5,5,5,5}
	 * -  since we increment i until we find an element greater/equal to the pivot
	 *  so, i will stop on first 5
	 * - since we decrement j until we find an element smaller/equal to the pivot,
	 *  so j will stop on last 5
	 *  - then both the 5 will be swapped. 
	 * - So, we are keep on swapping all the 5's until i crosses j.
	 * 
	 * 
	 * 
	 * </pre>
	 * 
	 */
	private static final class HoarePartition {

		public static int partition(int[] input, int low, int high) {

			int pivot = input[low];

			// since we are using do-while; so we have adjusted the initial value of i/j.
			int i = low - 1;
			int j = high + 1;

			// since we need to increment(i)/decrement(j) within while loop
			// and also need to increment(i)/decrement(j) post swap, for next iteration of
			// algo so it's better if we keep do while;

			while (true) {

				do {
					i++;
				} while (input[i] < pivot);

				do {
					j--;
				} while (input[j] > pivot);

				// before swapping, first check if i has not crossed the j
				if (i >= j) {
					return j;
				}

				// swap
				swap(input, i, j);

			}

		}

	}

}
