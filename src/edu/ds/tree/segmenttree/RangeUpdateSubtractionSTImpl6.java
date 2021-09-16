package edu.ds.tree.segmenttree;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;

/**
 * <pre>
 * Lazy propagation :  https://cp-algorithms.com/data_structures/segment_tree.html : Section "Assignment on segments"
 * 
 * Context : 
 * In general, query operation happens in bottom up approach, means if operator is ordered then we
 * need to apply the operands in there occurrence order. 
 * We keep the actual data on leaf node and other range-update-operands on intermediate nodes.
 * In case of range-update-operands order collision, we need to push-down(propagate) the old operand to left and right child so that operand occurrence order remain intact.
 * 
 * To keep the complexity of update and query to log(n), we apply lazy propagation i.e. lazy push-down of operands. 
 * 
 * This push-down operation is piggy-backed on implicit branching steps of update and query, we don't explicitly push-down the operands. 
 * This is why the name is Lazy Propagation.
 * 
 * Summarizing we get: for any queries (a modification or reading query) during the descent along the tree we should always
 * push information from the current vertex into both of its children. 
 * We can understand this in such a way, that when we descent the tree we apply delayed modifications, but exactly as much as necessary
 * so not to degrade the complexity of O(logn).
 * 
 * 
 * Lazy propagation in case of subtraction: 
 * 
 * Here we need to implement lazy propagation because subtraction operation is
 * order dependent i.e  a - b - c  NOT_EQUQL_TO  b -a - c
 * e.g a = 10, b=5, c=2
 * a - b - c = 3
 * b - a - c = -7
 * 
 * 
 * --original elements are present at leaf node
 * 
 * --intermediate nodes are void
 * 
 * Note : Intermediate nodes represents subtraction operation.
 * 
 * </pre>
 * 
 */
public class RangeUpdateSubtractionSTImpl6 implements RangeUpdateST<Integer> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final Integer[] tree;
	// represents right range of root-node of segment tree
	private final int root_tr;
	// represents left range of root-node of segment tree
	private final int root_tl = 0;
	private final int bfsIndexStart = 0;

	public RangeUpdateSubtractionSTImpl6(int[] elements) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new Integer[2 * powerOf2InputSize - 1];
		root_tr = elements.length - 1;
		buildSumSegmentTree(elements, tree, bfsIndexStart, root_tl, root_tr);

	}

	/**
	 * 
	 * Since the operator is subtraction, so void node can be represented as null.
	 * 
	 */
	private void buildSumSegmentTree(int input[], Integer tree[], int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			tree[bfsIndex] = input[tl];
		} else {
			int tm = (tl + tr) / 2;
			tree[bfsIndex] = null;
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

	/**
	 * push down the operand from current node to left and right child.
	 */
	private void pushDownRangeUpdateOperand(int bfsIndex, int tl , int tr) {

		// intermediate node contains range-update-operand
		//when we are at intermediate node
		if (tree[bfsIndex] != null && tl != tr) {
			
            int mid = (tl +tr)/2;
			
            int leftChildBfsIndex = 2 * bfsIndex + 1;
            
			int rightChildBfsIndex = 2 * bfsIndex + 2;
    		
			//if intermediate-left-child  is void
			if (tree[leftChildBfsIndex] == null) {
				tree[leftChildBfsIndex] = tree[bfsIndex];
			} else {
				//if intermediate-left-child is leaf node
				if(tl == mid) {
					tree[leftChildBfsIndex] -=tree[bfsIndex];
				}else {
					// if intermediate-left-child is non-leaf and already contains range-update-operand
					tree[leftChildBfsIndex] = createCompositeOperand(tree[leftChildBfsIndex], tree[bfsIndex]);
				}
				
			}

			
			//if intermediate-right-child  is void
			if (tree[rightChildBfsIndex] == null) {
				tree[rightChildBfsIndex] = tree[bfsIndex];
			} else {
				//if intermediate-right-child is leaf node
				if(mid+1 == tr) {
					tree[rightChildBfsIndex] -=tree[bfsIndex];
				}else {
					// if intermediate-right-child is non-leaf and already contains range-update-operand
					tree[rightChildBfsIndex] = createCompositeOperand(tree[rightChildBfsIndex], tree[bfsIndex]);
				}
				
			}

			// post push-down un-mark the parent node
			tree[bfsIndex] = null;
		}

	}

	private int createCompositeOperand(int oldOperand, int currentOperand) {
		return oldOperand + currentOperand;
	}

	private void update(Integer data, int ul, int ur, int bfsIndex, int tl, int tr) {
		
		// CASE1 : when tree node lies completely outside the query range
		if (tr < ul || ur <= tl) {
			return;
		}
		
		//push down marks the non-null intermediate nodes to null
		pushDownRangeUpdateOperand(bfsIndex, tl, tr);

		// CASE2: when tree node lies completely inside the query range, we should
		// update the node
		if (ul <= tl && tr < ur) {
			if(tl==tr) {
				tree[bfsIndex]  -= data;
			}else {
				//intermediate node will always be null because of above call of pushDownRangeUpdateOperand() 
				tree[bfsIndex] = data;
			}
		
			return;
		}

	
		// CASE3: when tree node lies partially inside the query range
		int tm = (tl + tr) / 2;
		update(data, ul, ur, 2 * bfsIndex + 1, tl, tm);
		update(data, ul, ur, 2 * bfsIndex + 2, tm + 1, tr);
	}

	@Override
	public Integer query(int index) {

		//return queryWithoutPushDown(index, bfsIndexStart, root_tl, root_tr);
		
		return queryWithPushDown(index, bfsIndexStart, root_tl, root_tr);
	}

	/***
	 *We need to do binary search using node range to reach at tl==tr, 
	 *we apply the intermediate node range-update-operand with leaf node in return trip.
	 * 
	 */
	private int queryWithoutPushDown(int index, int bfsIndex, int tl, int tr) {

		if (tl == tr) {
			return tree[bfsIndex];
		}

		int result;
		int tm = (tl + tr) / 2;
		if (index <= tm) {
			// go left
			result = queryWithoutPushDown(index, 2 * bfsIndex + 1, tl, tm);

		} else {
			// go right
			result = queryWithoutPushDown(index, 2 * bfsIndex + 2, tm + 1, tr);
		}

		if (tree[bfsIndex] != null) {
			result -= tree[bfsIndex];
		}
		return result;
	}
	
	
	/***
	 * We need to do binary search using node range to reach at tl==tr
	 * 
	 * *we apply the intermediate node range-update-operand with leaf node in forward trip.
	 */
	private int queryWithPushDown(int index, int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			return tree[bfsIndex];
		}
		pushDownRangeUpdateOperand(bfsIndex, tl, tr);
		int tm = (tl + tr) / 2;
		if (index <= tm) {
			// go left
			return  queryWithPushDown(index, 2 * bfsIndex + 1, tl, tm);

		} else {
			// go right
			return queryWithPushDown(index, 2 * bfsIndex + 2, tm + 1, tr);
		}

	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RangeUpdateSubtractionSTImpl6 [tree=").append(Arrays.toString(tree)).append("]");
		return builder.toString();
	}

}
