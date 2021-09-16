package edu.ds.tree.segmenttree;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;

/**
 * Here we need not to implement lazy propagation because addition operation is
 * not order dependent, so we can keep multiple range updates summation on
 * nodes.
 * 
 * Effectively, we are able to combine multiple range update operations
 * into one composite addition operation as addition operation is associative. Also, because of
 * commutative property order of addition does not matter.
 * 
 * 
 * 
 * --original elements are present at leaf node
 * 
 * --intermediate nodes are void
 * 
 * Note : Intermediate nodes represents addition operation.
 * 
 */
public class RangeUpdateAdditionSTImpl5 implements RangeUpdateST<Integer> {

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

	public RangeUpdateAdditionSTImpl5(int[] elements) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new int[2 * powerOf2InputSize - 1];
		root_tr = elements.length - 1;
		buildSumSegmentTree(elements, tree, bfsIndexStart, root_tl, root_tr);

	}

	/**
	 * 
	 * Since the operator is addition, so void node can be represented as ZERO. void
	 * node can be filled with ZERO either in forward trip or return trip of
	 * recursion.
	 */
	private void buildSumSegmentTree(int input[], int tree[], int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			tree[bfsIndex] = input[tl];
		} else {
			int tm = (tl + tr) / 2;
			tree[bfsIndex] = 0;
			buildSumSegmentTree(input, tree, 2 * bfsIndex + 1, tl, tm);
			buildSumSegmentTree(input, tree, 2 * bfsIndex + 2, tm + 1, tr);
		}

	}

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
	 * Since its the range update, so we need to evaluate all the three cases
	 * 
	 */
	@Override
	public void update(Integer data, int left, int right) {
		update(data, left, right, bfsIndexStart, root_tl, root_tr);
	}

	private void update(Integer data, int ul, int ur, int bfsIndex, int tl, int tr) {
		// CASE1 : when tree node lies completely outside the query range
		if (tr < ul || ur <= tl) {
			return;
		}

		// CASE2: when tree node lies completely inside the query range, we should
		// update the node
		if (ul <= tl && tr < ur) {
			tree[bfsIndex] += data;
			return;
		}

		// CASE3: when tree node lies partially inside the query range
		int tm = (tl + tr) / 2;
		update(data, ul, ur, 2 * bfsIndex + 1, tl, tm);
		update(data, ul, ur, 2 * bfsIndex + 2, tm + 1, tr);
	}

	@Override
	public Integer query(int index) {

		return query(index, bfsIndexStart, root_tl, root_tr);
	}

	/***
	 * We need to do binary search using node range to find out the element at given
	 * input index. In return trip of recursion we should apply the addition
	 * operation.
	 * 
	 */
	private int query(int index, int bfsIndex, int tl, int tr) {

		if (tl == tr) {
			return tree[bfsIndex];
		}

		int tm = (tl + tr) / 2;

		if (index <= tm) {
			// go left
			return tree[bfsIndex] + query(index, 2 * bfsIndex + 1, tl, tm);
		} else {
			// go right
			return tree[bfsIndex] + query(index, 2 * bfsIndex + 2, tm + 1, tr);
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RangeUpdateAdditionSTImpl5 [tree=").append(Arrays.toString(tree)).append("]");
		return builder.toString();
	}

}
