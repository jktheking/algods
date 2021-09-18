package edu.ds.tree.segmenttree;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;

/**
 * <pre>
 * 
 * TYPE-A segment-tree
 *   query in range : query(left, right) 
 *   single update  : update(index)
 *   
 *   eg: max-segment-tree
 *   
 * TYPE-B segment-tree
 *   update in range : update(left, right) 
 *   single query  : query(index)  
 *   
 *   e.g  range-update-addition-segment-tree
 *   
 * TYPE-C segment-tree
 *   Hybrid segment tree of TYPE-A and TYPE-B
 *   
 *       query in range : query(left, right)
 *       update in range : update(left, right)
 * 
 * </pre>
 * 
 */
public class RangeUpdateAdditionRangeQueryMaxSTImpl7 implements RangeUpdateRangeQueryST<Integer> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final int[] tree;
	private final int[] lazy;
	// represents right range of root-node of segment tree
	private final int root_tr;
	// represents left range of root-node of segment tree
	private final int root_tl = 0;
	private final int bfsIndexStart = 0;

	public RangeUpdateAdditionRangeQueryMaxSTImpl7(int[] elements) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new int[2 * powerOf2InputSize - 1];
		lazy = new int[2 * powerOf2InputSize - 1];
		root_tr = elements.length - 1;
		buildMaxSegmentTree(elements, tree, bfsIndexStart, root_tl, root_tr);

	}

	private int buildMaxSegmentTree(int input[], int tree[], int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			return tree[bfsIndex] = input[tl];
		} else {
			int tm = (tl + tr) / 2;

			return tree[bfsIndex] = Math.max(buildMaxSegmentTree(input, tree, 2 * bfsIndex + 1, tl, tm),
					buildMaxSegmentTree(input, tree, 2 * bfsIndex + 2, tm + 1, tr));

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
	 * push down the operand from current node to left and right child.
	 */
	private void pushDownRangeUpdateOperand(int bfsIndex, int tl, int tr) {
		// no need to do pushDown at leaf node
		if (tl != tr) {
			// push down the value on main tree
			tree[2 * bfsIndex + 1] += lazy[bfsIndex];
			tree[2 * bfsIndex + 2] += lazy[bfsIndex];

			// push down the value on lazy as well
			lazy[2 * bfsIndex + 1] += lazy[bfsIndex];
			lazy[2 * bfsIndex + 2] += lazy[bfsIndex];

			// post push-down un-mark the present lazy node
			lazy[bfsIndex] = 0;
		}

	}

	
	/**
	 * Since its the range update, so we need to evaluate all the three cases
	 * 
	 */
	@Override
	public void update(Integer data, int left, int right) {
		update(data, left, right, bfsIndexStart, root_tl, root_tr);
	}
	/***
	 * Since we are doing pushDown, so need to update the max-tree return trip.
	 */
	private int update(Integer data, int ul, int ur, int bfsIndex, int tl, int tr) {

		// CASE1 : when tree node lies completely outside the query range
		// Since we may have to re-calculate the MAX at parent of this node in return
		// trip, so we should
		// provide actual value of this node though it is outside the query range.
		if (tr < ul || ur <= tl) {
			return tree[bfsIndex];
		}
		pushDownRangeUpdateOperand(bfsIndex, tl, tr);
		
		// CASE2: when tree node lies completely inside the query range, we should
		// update the node
		if (ul <= tl && tr < ur) {
			lazy[bfsIndex] += data;
			return tree[bfsIndex] += data;

		}
		// CASE3: when tree node lies partially inside the query range, recurse down the
		// tree
		int tm = (tl + tr) / 2;

		return tree[bfsIndex]= Math.max(update(data, ul, ur, 2 * bfsIndex + 1, tl, tm),
				update(data, ul, ur, 2 * bfsIndex + 2, tm + 1, tr));
	}

	@Override
	public Integer query(int left, int right) {

		return queryWithPushDown(bfsIndexStart, root_tl, root_tr, left, right);
	}

	/***
	 * Since we are doing query on range so need to follow 3 CASES. 
	 * 
	 * we apply the intermediate node range-update-operand with leaf node in forward trip.
	 */
	private int queryWithPushDown(int bfsIndex, int tl, int tr, int ql, int qr) {

		// CASE 1 : If node-segment lies completely outside the query-segment;
		// terminates the recursion
		// Since this node will not fall into comparison-set of nodes that will give the
		// range-query answer, so we can return ignorable value.
		if (tr < ql || qr <= tl) {
			return Integer.MIN_VALUE;
		}

		pushDownRangeUpdateOperand(bfsIndex, tl, tr);

		// CASE 2 : If node-segment lies completely inside the query-segment; terminates
		// the recursion
		if (ql <= tl && tr < qr) {
			return tree[bfsIndex];
		}

		int tm = (tl + tr) / 2;
		return Math.max(queryWithPushDown(2 * bfsIndex + 1, tl, tm, ql, qr),
				queryWithPushDown(2 * bfsIndex + 2, tm + 1, tr, ql, qr));
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RangeUpdateAdditionRangeQueryMaxSTImpl7 [tree=").append(Arrays.toString(tree)).append(",\n lazy=")
				.append(Arrays.toString(lazy)).append("]");
		return builder.toString();
	}

	

}
