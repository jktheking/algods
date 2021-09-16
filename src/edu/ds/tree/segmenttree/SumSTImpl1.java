package edu.ds.tree.segmenttree;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;

/**
 * 1. bfsIndex is used for locating the index of node in tree-array while doing
 * the tree traversal.
 * 
 * 2. node segment(i.e. tl & tr): represents original array index and is used
 * for binary search condition evaluation.
 * 
 * 
 */
public class SumSTImpl1 implements RangeQueryST<Integer>, RangeQueryST.SumSegmentTree<Integer> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final int[] tree;
	// represents right range of root-node of segment tree
	private final int root_tr;
	// represents left range of root-node of segment tree
	private final int root_tl = 0;
	private final int bfsIndexStart = 0;

	SumSTImpl1(int[] elements, boolean isFull) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new int[2 * powerOf2InputSize - 1];
		if (isFull) {
			root_tr = powerOf2InputSize - 1;
			buildFullSumSegmentTree(elements, powerOf2InputSize);
		} else {
			root_tr = elements.length - 1;
			buildSumSegmentTree(elements, tree, bfsIndexStart, root_tl, root_tr);
		}

	}

	/**
	 * 
	 * <pre>
	 * If segment tree is power-of-2 then we can directly use (2n-1) as the size of segment-tree-array
	 * If segment tree is not the power-of-2, then we can extend the input-array to the power-of-2 using dummy value, 
	 * then use (2n-1). This is asymptotically equal to 4n.
	 * </pre>
	 * 
	 */
	private int getInputArraySizeAsPowerOf2(int inputSize) {
		if (!BitwiseUtils.is_2_toThePowerOf_N(inputSize)) {
			inputSize = getNextPowerOf2(inputSize);
		}
		return inputSize;
	}

	private int getNextPowerOf2(int inputSize) {
		return (Integer.highestOneBit(inputSize) << 1);
	}

	/**
	 * 
	 * Since this implementation is SUM segment-tree, we can use 0 as the dummy
	 * value to extend the input-array if the size of array is not the power-of-2.
	 */
	private void buildFullSumSegmentTree(int[] input, int powerOf2InputSize) {
		int[] powerOf2Input = input;
		if (input.length < powerOf2InputSize) {
			powerOf2Input = new int[powerOf2InputSize];
			System.arraycopy(input, 0, powerOf2Input, 0, input.length);

		}
		buildSumSegmentTree(powerOf2Input, tree, bfsIndexStart, root_tl, root_tr);

	}

	/**
	 * We do not save the node's segment explicitly, rather it is getting calculated
	 * on the fly using the root-node's segment.
	 * 
	 * This recursion employ the bottom-up approach to store the calculated sum at
	 * bfsIndex.
	 * 
	 * @param input    :represents input array
	 * @param tree     :represents segment tree
	 * @param bfsIndex :represents bfs index for segment tree node.
	 * @param tl       :represents left-range of segment owned by a node in the
	 *                 segment-tree.
	 * @param tr       :represents right-range of segment owned by a node in the
	 *                 segment-tree.
	 * 
	 *                 Time complexity : O(n)
	 * 
	 *                 <pre>
	 *   Hypothesis :
	 *   buildSumSegmentTree(bfsIndex, tl, tr)  gives sum of segment[tl,tr] and stored at bfsIndex.
	 *   
	 *   Substitution :
	 *   tm = (tl + tr) / 2;
	 *   
	 *   sum_of_left_child = buildSumSegmentTree(2 * bfsIndex + 1, tl, tm)
	 *   sum_of_right_child = buildSumSegmentTree(2 * bfsIndex + 2, tm + 1, tr)
	 *   
	 *   Induction:
	 *   tree[bfsIndex] = sum_of_left_child + sum_of_right_child;
	 * 
	 *                 </pre>
	 * 
	 * 
	 */
	private int buildSumSegmentTree(int input[], int tree[], int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			// when control reaches to leaf-node (when tl==tr), input-array-element itself
			// represents the sum of segment.
			return tree[bfsIndex] = input[tl];
		} else {
			int tm = (tl + tr) / 2;
			return tree[bfsIndex] = buildSumSegmentTree(input, tree, 2 * bfsIndex + 1, tl, tm)
					+ buildSumSegmentTree(input, tree, 2 * bfsIndex + 2, tm + 1, tr);
		}

	}

	@Override
	public Integer rangeQuery(int left, int right) {
		return rangeQuery(left, right, root_tl, root_tr, bfsIndexStart);
	}

	/**
	 * <b> We do not save the node's segment explicitly, rather it is getting
	 * calculated on the fly using the root-node's segment.
	 * 
	 * This recursion employ the bottom-up approach.
	 * 
	 * <pre>
	 * ALGO :
	 * CASE 1 : If node-segment lies completely outside the query-segment:  contribution to the final sum-query is ZERO. 
	 * 	 tl--tr [ql,qr)  or [ql,qr) tl--tr
	 * 
	 * CASE 2 : If node-segment lies completely inside the query-segment :  contribution to the final-sum-query is node's value.
	 *    [ql tl---tr  qr)
	 *               
	 *    
	 * CASE 3 : If node-segment overlaps partially with the query-segment:  need to traverse deeper until encounters CASE1 or CASE2 .
	 * 	  [ql--tl-- qr)--tr or tl--[ql--tr--qr)  or tl--[ql--qr)--tr
	 * 
	 * CASE1 OR CASE2 terminates the recursion while CASE3 spawns the recursion branches.
	 * 
	 * </pre>
	 * 
	 * 
	 * Time Complexity : O(4logn) = O(logn)
	 * 
	 */
	private int rangeQuery(int ql, int qr, int tl, int tr, int bfsIndex) {

		// CASE 1 : If node-segment lies completely outside the query-segment;
		// terminates the recursion
		if (tr < ql || qr <= tl) {
			return 0;
		}

		// CASE 2 : If node-segment lies completely inside the query-segment; terminates
		// the recursion
		if (ql <= tl && tr < qr) {
			return tree[bfsIndex];
		}

		int tm = (tl + tr) / 2;
		return rangeQuery(ql, qr, tl, tm, 2 * bfsIndex + 1) + rangeQuery(ql, qr, tm + 1, tr, 2 * bfsIndex + 2);

	}

	@Override
	public void update(Integer data, int index) {
		update(data, index, root_tl, root_tr, bfsIndexStart);
	}

	/**
	 * For update we follow binary search.
	 * 
	 * This implementation updates the logn tree nodes in bottom-up approach.
	 * 
	 * Time Complexity : O(logn)
	 */
	private void update(int data, int updateIndex, int tl, int tr, int bfsIndex) {

		if (tl == tr) {
			tree[bfsIndex] = data;

		} else {
			int tm = (tl + tr) / 2;
			if (updateIndex <= tm) {
				// go left
				update(data, updateIndex, tl, tm, 2 * bfsIndex + 1);
			} else {
				// go right
				update(data, updateIndex, tm + 1, tr, 2 * bfsIndex + 2);
			}
			tree[bfsIndex] = tree[2 * bfsIndex + 1] + tree[2 * bfsIndex + 2];
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SumSegmentTree [tree=").append(Arrays.toString(tree)).append("]");
		return builder.toString();
	}

	/**
	 * The task is as follows: for a given value x we have to quickly find smallest
	 * index i such that the sum of the first i elements of the array a[] is greater
	 * or equal to x (assuming that the array a[] only contains non-negative
	 * values).
	 * 
	 * 
	 * index getRightBoundOfRangeUsingRecursion()
	 * 
	 */
	@Override
	public int getRightBoundOfRange(Integer data) {

		if (tree[0] < data) {
			throw new IllegalArgumentException("given value is out of range of tree sum.");

		}
		return getRightBoundOfRangeUsingRecursion(data, bfsIndexStart, root_tl, root_tr);
	}

	/**
	 * 
	 * https://cp-algorithms.com/data_structures/segment_tree.html
	 * 
	 * 
	 * TimeComplexity : logn
	 * 
	 * Also @see: Counting the number of zeros, searching for the k-th zero. In this
	 * problem, segment tree is built on number of zeros such that rangeQuery is for
	 * the number of zeros in a given range. And its opposite is to find the right
	 * bound of range for the k-th zero.
	 * 
	 */
	private int getRightBoundOfRangeUsingRecursion(int givenSum, int bfsIndex, int tl, int tr) {

		if (tl == tr) {
			return tl;
		}

		int tm = (tl + tr) / 2;
		// data is contained in left segment
		if (tree[2 * bfsIndex + 1] >= givenSum) {
			return getRightBoundOfRangeUsingRecursion(givenSum, (2 * bfsIndex + 1), tl, tm);
		} else {
			// go to right segment, and subtract the sum of left segment
			return getRightBoundOfRangeUsingRecursion((givenSum - tree[2 * bfsIndex + 1]), (2 * bfsIndex + 2), tm + 1,
					tr);
		}

	}

	@Override
	public int getRightBoundOfRange(Integer data, int ql, int qr) {

		if (tree[0] < data) {
			throw new IllegalArgumentException("given value is out of range of tree sum.");

		}
		return getRightBoundOfRangeInQueryRange(ql, qr, root_tl, root_tr, bfsIndexStart, new int[] { data });
	}

	/**
	 * 
	 * Solution is comprised of two Algo:<br>
	 * 
	 * ALGO 1: When question is constrained on range, then CASES are on the basis of 
	 * position of tree-node-range with respect to query-range <br>
	 * CASE 1: when node range lies completely outside the query range <br>
	 * CASE 2: when node range lies completely inside the query range <br>
	 * CASE 3: when node range lies partially inside the query range <br>
	 * 
	 * ALGO 2 : CASE 2 from ALGO 1 <br>
	 * 
	 * CASE 2: When a tree node lies completely inside the query range. (ql <= tl &&
	 * tr <= qr)
	 * 
	 * CASE 2A: When node-sum is less than the given-sum, then we need to adjust the
	 * given-sum and terminate the recursion for this branch.
	 * 
	 * <pre>
	 * <code>
	 * 	 if (tree[bfsIndex] < givenSum[0]) { 
	 *     	 	givenSum[0]= givenSum[0] - tree[bfsIndex]; 
	 *     		return INTEGER.MIN; // stops the recursion for this branch 
	 *      }
	 *  </code>
	 * </pre>
	 * 
	 * CASE 2B: When node-sum is greater than the given-sum, we need to branch the
	 * recursion and further check for CASE2A and CASE2B until control reaches to
	 * leaf node.
	 * 
	 * 
	 * 
	 */
	private int getRightBoundOfRangeInQueryRange(int ql, int qr, int tl, int tr, int bfsIndex, int[] givenSum) {

		// if node range lies completely outside the query range
		if (tr < ql || tl > qr)
			return Integer.MIN_VALUE;

		// if node range lies completely inside the query range
		if (ql <= tl && tr <= qr) {

			// when the node-sum is less than given-sum
			if (tree[bfsIndex] < givenSum[0]) {
				givenSum[0] = givenSum[0] - tree[bfsIndex];
				return Integer.MIN_VALUE;
			}

			if (tl == tr)
				return tl;
		}

		int mid = (tl + tr) / 2;
		int leftSumIndex = getRightBoundOfRangeInQueryRange(ql, qr, tl, mid, 2 * bfsIndex + 1, givenSum);
		if (leftSumIndex != Integer.MIN_VALUE)
			return leftSumIndex;
		return getRightBoundOfRangeInQueryRange(ql, qr, mid + 1, tr, 2 * bfsIndex + 2, givenSum);

	}

}
