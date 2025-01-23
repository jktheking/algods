package l150.arraystring;

import java.util.Stack;

/**
 * 
 * https://leetcode.com/problems/trapping-rain-water/description/?envType=study-plan-v2&envId=top-interview-150
 * 
 * Given n non-negative integers representing an elevation map where the width
 * of each bar is 1, compute how much water it can trap after raining.
 * 
 * Example 1: Input: height = [0,1,0,2,1,0,1,3,2,1,2,1] Output: 6
 * 
 * Explanation: The above elevation map (black section) is represented by array
 * [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section)
 * are being trapped.
 * 
 * Example 2:
 * 
 * Input: height = [4,2,0,3,2,5] Output: 9
 */
public class L42TrappingRainWater {

	public static void main(String[] args) {

	}

	/**
	 * <pre>
	 * Water trapping logic:
	 * 
	 * Water can be trapped at a position if there are taller bars on both sides of
	 * it (i.e., to the left and right).
	 * 
	 * The amount of water trapped at a particular index is determined by the
	 * difference between min(left-max, right-max) and the height at that index.
	 * 
	 * Pointers: l, r and two variables lMax, rMax
	 * 
	 * Pre-of-two-pointer:
	 * We can maintain two arrays one will store leftMax seen so far and other will store rightMax
	 * seen so far. Now traverse the input array and use the formula min(left-max, right-max) - height[i]
	 * 
	 * Why the Algorithm Works:
	 * -----------------------
	 * 
	 * Left Pointer Consideration: If height[l] < height[r], then the right side has a bar tall enough to contain 
	 * any water above height[l]. In this case, it doesn't matter whether there are bars between height[r] and 
	 * the current left pointer position (l) that are taller or shorter than height[r]. 
	 * Why? Because height[r] guarantees that no water will spill over it.
	 * This means that the water trapped at left depends solely on leftMax, not on rightMax.
	 * We then move the left pointer to the right to examine the next position.
	 * 
	 * Right Pointer Consideration: If height[right] <= height[left], then the left side has a bar tall enough to contain
	 * any water above height[right]. The water trapped at right depends solely on rightMax, not on leftMax.
	 * We then move the right pointer to the left to examine the next position.
	 * 
	 * 
	 * </pre>
	 */
	public int trap2PApproach(int[] height) {

		int l = 0;
		int r = height.length - 1;
		int lmax = 0;
		int rmax = 0;

		int trappedWater = 0;

		while (l < r) {

			// trapping water happen because of lmax
			if (height[l] < height[r]) {

				if (height[l] >= lmax) {
					// adjust the lmax, water cannot be trapped
					lmax = height[l];
				} else {
					// water can be trapped
					trappedWater += lmax - height[l];
				}

				l++;

			} else {
				// trapping water happen because of rmax
				if (height[r] >= rmax) {
					// adjust the rmax, water cannot be trapped
					rmax = height[r];
				} else {
					// water can be trapped
					trappedWater += rmax - height[r];
				}

				r--;
			}
		}

		return trappedWater;

	}

	public static class TrappingRainWaterWithStack {
		public int trap(int[] height) {
			if (height == null || height.length < 3)
				return 0;

			Stack<Integer> stack = new Stack<>();
			int waterTrapped = 0;

			for (int i = 0; i < height.length; i++) {
				// While the current height is greater than the height at the top of the stack
				while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
					int bottom = stack.pop(); // Pop the top (bottom of the valley)
					if (stack.isEmpty())
						break; // No left boundary

					int left = stack.peek(); // Left boundary
					int width = i - left - 1; // Distance between left and current bar
					int boundedHeight = Math.min(height[left], height[i]) - height[bottom];
					waterTrapped += width * boundedHeight; // Add trapped water
				}
				stack.push(i); // Push the current bar onto the stack
			}

			return waterTrapped;
		}

		public static void main(String[] args) {
			TrappingRainWaterWithStack solution = new TrappingRainWaterWithStack();
			int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
			System.out.println(solution.trap(height)); // Output: 6
		}
	}

}
