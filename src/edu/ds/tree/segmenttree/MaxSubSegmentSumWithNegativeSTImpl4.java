package edu.ds.tree.segmenttree;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;
import edu.ds.tree.segmenttree.MaxSubSegmentSumWithNegativeSTImpl4.Data;

/**
 * @see Kadane's algorithm
 * 
 */
public class MaxSubSegmentSumWithNegativeSTImpl4 implements RangeQueryST<Data> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final Data[] tree;
	// represents right range of root-node of segment tree
	private final int root_tr;
	// represents left range of root-node of segment tree
	private final int root_tl = 0;
	private final int bfsIndexStart = 0;

	private final boolean flatInitialization;

	MaxSubSegmentSumWithNegativeSTImpl4(int[] elements, boolean flatInitialization) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new Data[2 * powerOf2InputSize - 1];
		root_tr = elements.length - 1;
		this.flatInitialization = flatInitialization;

		buildSegmentTree(elements, tree, bfsIndexStart, root_tl, root_tr);

	}

	private Data buildSegmentTree(int input[], Data tree[], int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			return tree[bfsIndex] = new Data(input[tl], flatInitialization);
		} else {
			int tm = (tl + tr) / 2;
			return tree[bfsIndex] = combine(buildSegmentTree(input, tree, 2 * bfsIndex + 1, tl, tm),
					buildSegmentTree(input, tree, 2 * bfsIndex + 2, tm + 1, tr));
		}

	}

	/**
	 * This combine operation is the heart of this tree.
	 * 
	 * @see MaxSubSegmentSumWithNegative.pdf
	 */
	private Data combine(Data left, Data right) {
		int sum = left.getSum() + right.getSum();
		int maxPrefixSum = Math.max(left.getMaxPrefixSum(), left.getSum() + right.getMaxPrefixSum());
		int maxSuffixSum = Math.max(right.getMaxSuffixSum(), right.getSum() + left.getMaxSuffixSum());
		int maxSum = Math.max(Math.max(left.getMaxSum(), right.getMaxSum()),
				left.getMaxSuffixSum() + right.getMaxPrefixSum());

		return new Data(sum, maxPrefixSum, maxSuffixSum, maxSum);
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

	@Override
	public Data rangeQuery(int left, int right) {
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
	 * CASE 1 : If node-segment lies completely outside the query-segment:  contribution to the final query is ZERO. 
	 * 	 tl--tr [ql,qr)  or [ql,qr) tl--tr
	 * 
	 * CASE 2 : If node-segment lies completely inside the query-segment :  contribution to the final-query is node's value.
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
	private Data rangeQuery(int ql, int qr, int tl, int tr, int bfsIndex) {

		// CASE 1 : If node-segment lies completely outside the query-segment;
		// terminates the recursion
		if (tr < ql || qr <= tl) {
			return null;
		}

		// CASE 2 : If node-segment lies completely inside the query-segment; terminates
		// the recursion
		if (ql <= tl && tr < qr) {
			return tree[bfsIndex];
		}

		int tm = (tl + tr) / 2;
		Data left = rangeQuery(ql, qr, tl, tm, 2 * bfsIndex + 1);
		Data right = rangeQuery(ql, qr, tm + 1, tr, 2 * bfsIndex + 2);

		if (left == null && right == null)
			throw new IllegalStateException("Algo reached at some deadlock!");

		if (left == null) {
			return right;
		}
		if (right == null) {
			return left;
		}
		return combine(left, right);
	}

	@Override
	public void update(Data data, int index) {
		update(data.getSum(), index, root_tl, root_tr, bfsIndexStart);

	}

	private void update(int value, int updateIndex, int tl, int tr, int bfsIndex) {

		if (tl == tr) {
			tree[bfsIndex] = new Data(value, flatInitialization);

		} else {
			int tm = (tl + tr) / 2;
			if (updateIndex <= tm) {
				// go left
				update(value, updateIndex, tl, tm, 2 * bfsIndex + 1);
			} else {
				// go right
				update(value, updateIndex, tm + 1, tr, 2 * bfsIndex + 2);
			}
			tree[bfsIndex] = combine(tree[2 * bfsIndex + 1], tree[2 * bfsIndex + 2]);
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaxSubSegmentSumWithNegativeST4 [tree=").append(Arrays.toString(tree)).append(", root_tr=")
				.append(root_tr).append(", root_tl=").append(root_tl).append(", bfsIndexStart=").append(bfsIndexStart)
				.append("]");
		return builder.toString();
	}

	public class Data {

		private final int sum;
		private final int maxPrefixSum;
		private final int maxSuffixSum;
		private final int maxSum;

		/**
		 * IF flatInitilization is off and input array contains all negative elements
		 * THEN maxSum will always be ZERO.
		 * 
		 */
		public Data(int value, boolean flatInitilization) {
			this.sum = value;
			if (flatInitilization) {
				this.maxPrefixSum = this.maxSuffixSum = this.maxSum = value;
			} else {
				this.maxPrefixSum = this.maxSuffixSum = this.maxSum = Math.max(value, 0);
			}
		}

		public Data(int sum, int maxPrefixSum, int maxSuffixSum, int maxSum) {
			super();
			this.sum = sum;
			this.maxPrefixSum = maxPrefixSum;
			this.maxSuffixSum = maxSuffixSum;
			this.maxSum = maxSum;
		}

		public int getSum() {
			return sum;
		}

		public int getMaxPrefixSum() {
			return maxPrefixSum;
		}

		public int getMaxSuffixSum() {
			return maxSuffixSum;
		}

		public int getMaxSum() {
			return maxSum;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Data [sum=").append(sum).append(", maxPrefixSum=").append(maxPrefixSum)
					.append(", maxSuffixSum=").append(maxSuffixSum).append(", maxSum=").append(maxSum).append("]");
			return builder.toString();
		}

	}

}
