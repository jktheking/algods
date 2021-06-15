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
public class SumSegmentTree1 implements SegmentTree<Integer> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final int[] tree;
	private final int root_tr;
	private final int root_tl = 0;
	private final int bfsIndexStart = 0;

	SumSegmentTree1(int[] elements, boolean isFull) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new int[2*powerOf2InputSize -1];
		if (isFull) {
			root_tr = powerOf2InputSize -1;
			buildFullSumSegmentTree(elements,powerOf2InputSize);
		} else {
			root_tr = elements.length -1;
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
	 * This recursion employ the bottom-up approach.
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

		// CASE 1 : If node-segment lies completely outside the query-segment
		if (tr < ql || qr <= tl) {
			return 0;
		}

		// CASE 2 : If node-segment lies completely inside the query-segment
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

}
