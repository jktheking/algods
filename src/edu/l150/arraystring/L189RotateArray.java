package l150.arraystring;

public class L189RotateArray {

	public static void main(String[] args) {

		int[] nums = { 1, 2, 3, 4, 5, 6, 7 };
		int k = 3;
		rotateUsingFormula(nums, k);

		int[] nums1 = { -1, -100, 3, 99 };
		int k1 = 2;
		// rotateUsingFormula(nums1, k1);

	}

	/**
	 *
	 * As we know, how we convert 1-d array index to 2-d array index
	 *
	 * row = (index_1d) / 2d_arr[0].length col = (index_1d) % 2d_arr[0].length
	 *
	 * so, the post rotation, it will follow col formula.
	 *
	 * col = (i+k) % n
	 *
	 * <pre>
	 * formula : (i+k)% n = r
	 * i - represents index of array
	 * k - represents rotation count
	 * n - represents size of the array
	 * r - represents index post rotation
	 *
	 * (0+k)%n = k
	 * (1+k)%n = k+1
	 * (2+k)%n = k+2
	 * . .. ... ..
	 * (x+k)%n = 0; when x+k = n
	 * . .. ... ..
	 * (n-1+k)%n = k-1;
	 *
	 * Two cases:
	 * CASE_A: odd sized array
	 * when nums length is odd, lets say array size is 5 and k=2
	 * (i+k)% n = r
	 * (0+2)%5 = 2
	 * (1+2)%5 = 3
	 * (2+2)%5 = 4
	 * (3+2)%5 = 0
	 * (4+2)%5 = 1
	 *
	 * 0 -> 2 -> 4 -> 1 -> 3 -> 0
	 * There is single transitive-iteration-cycle starting at 0.
	 *
	 *
	 *CASE_B: even sized array
	 * when nums length is even, lets say array size is 4 and k=2
	 * (i+k)% n = r
	 * (0+2)%6 = 2
	 * (1+2)%6 = 3
	 * (2+2)%6 = 4
	 * (3+2)%6 = 5
	 * (4+2)%6 = 0
	 * (5+2)%6 = 1
	 *
	 * There are two contiguous transitive-iteration-cycle starting at contiguous indices 0, 1
	 * 0 -> 2 -> 4 -> 0
	 * 1 -> 3 -> 5 -> 1
	 *
	 * (0+3)%6 = 3
	 * (1+3)%6 = 4
	 * (2+3)%6 = 5
	 * (3+3)%6 = 0
	 * (4+3)%6 = 1
	 * (5+3)%6 = 2
	 *
	 * There are 3 contiguous transitive-iteration-cycle starting at contiguous indices 0, 1, 2
	 * 0 -> 3 -> 0
	 * 1 -> 4 -> 0
	 * 2 -> 5 -> 0
	 *
	 *Means for even-sized-array  loop needs to start the transitive-iteration cycles in sequence
	 *until we reach to the total count of elements.
	 *
	 * </pre>
	 *
	 *
	 * This method works by rotating an array in-place by using cyclic replacements.
	 * This approach involves repeatedly moving elements to their correct positions
	 * one step at a time until all elements are in their correct positions.
	 *
	 * For odd-sized array single transitive-iteration covers all the elements of
	 * the array. It starts from the 0th index and ends at 0th index. But for
	 * even-sized array there can be multiple transitive-iterations. So, for
	 * even-sized-array outer loop needs to start the transitive-iteration cycles in
	 * sequence until we reach to the total count of elements.
	 *
	 * Time complexity: O(n), because loop is getting iterated only once, that is we
	 * scan each element once combined inner+outer loop. This can be understood from
	 * count variable.
	 */

	public static void rotateUsingFormula(int[] nums, int k) {
		int n = nums.length;
		k %= n; // to handle cases where k is greater than the length of the array
		int count = 0;

		// loop needs to start the transitive-iteration cycles in sequence until we
		// reach to the total count of elements.
		for (int startIndex = 0; count < n; startIndex++) {
			count = iterateTransitiveCycle(nums, n, k, startIndex, count);
		}

	}

	/**
	 * A transitive cycle ends when it reaches the same index where it started.
	 *
	 */
	private static int iterateTransitiveCycle(int[] nums, final int n, final int k, final int startIndex, int count) {

		int idx = startIndex;
		int nextIdx = Integer.MIN_VALUE;

		int prevElement = nums[idx];

		while (nextIdx != startIndex) {

			nextIdx = (idx + k) % n;

			// rotate the element
			int temp = nums[nextIdx];
			nums[nextIdx] = prevElement;
			prevElement = temp;

			idx = nextIdx;
			count++;
		}

		return count;

	}

}
