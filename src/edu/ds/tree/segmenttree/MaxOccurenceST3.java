package edu.ds.tree.segmenttree;

import java.util.Arrays;
import java.util.Map;

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
public class MaxOccurenceST3
implements SegmentTree<Map.Entry<Integer, Integer>>, SegmentTree.MaxSegmentTree<Integer> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final Map.Entry<Integer, Integer>[] tree;
	// represents right range of root-node of segment tree
	private final int root_tr;
	// represents right range of root-node of segment tree
	private final int root_tl = 0;
	private final int bfsIndexStart = 0;

	@SuppressWarnings("unchecked")
	MaxOccurenceST3(int[] elements, boolean isFull) {
		int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
		tree = new Map.Entry[2 * powerOf2InputSize - 1];
		if (isFull) {
			root_tr = powerOf2InputSize - 1;
			buildFullMaxOccurrenceSegmentTree(elements, powerOf2InputSize);
		} else {
			root_tr = elements.length - 1;
			buildMaxOccurrenceSegmentTree(elements, tree, bfsIndexStart, root_tl, root_tr);
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
	 * Since this implementation is max occurrence segment-tree, we can use
	 * Integer.MIN( -ve INFINITY) as the dummy value to extend the input-array if
	 * the size of array is not the power-of-2.
	 */
	private void buildFullMaxOccurrenceSegmentTree(int[] input, int powerOf2InputSize) {
		int[] powerOf2Input = input;
		if (input.length < powerOf2InputSize) {
			powerOf2Input = new int[powerOf2InputSize];

			System.arraycopy(input, 0, powerOf2Input, 0, input.length);
			Arrays.fill(powerOf2Input, input.length, powerOf2InputSize, Integer.MIN_VALUE);

		}
		buildMaxOccurrenceSegmentTree(powerOf2Input, tree, bfsIndexStart, root_tl, root_tr);

	}

	/**
	 * We do not save the node's segment explicitly, rather it is getting calculated
	 * on the fly using the root-node's segment.
	 * 
	 * This recursion employ the bottom-up approach to store the calculated MAX with
	 * Occurrence count at bfsIndex.
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
	 *   buildMaxOccurrenceSegmentTree(bfsIndex, tl, tr)  gives max with occurrence of segment[tl,tr] and stored at bfsIndex.
	 *   
	 *   Substitution :
	 *   tm = (tl + tr) / 2;
	 *   
	 *   max_occurrence_of_left_child = buildMaxOccurrenceSegmentTree(2 * bfsIndex + 1, tl, tm)
	 *   max_occurrence_of_right_child = buildMaxOccurrenceSegmentTree(2 * bfsIndex + 2, tm + 1, tr)
	 *   
	 *   Induction:
	 *   tree[bfsIndex] =  createMaxOccurrenceEntry(max_occurrence_of_left_child, max_occurrence_of_right_child)
	 * 
	 *                 </pre>
	 * 
	 * 
	 */
	private Map.Entry<Integer, Integer> buildMaxOccurrenceSegmentTree(int input[], Map.Entry<Integer, Integer> tree[],
			int bfsIndex, int tl, int tr) {
		if (tl == tr) {
			// when control reaches to leaf-node (when tl==tr)
			return tree[bfsIndex] = Map.entry(input[tl], 1);
		} else {

			int tm = (tl + tr) / 2;
			return tree[bfsIndex] = createMaxOccurrenceEntry(
					buildMaxOccurrenceSegmentTree(input, tree, 2 * bfsIndex + 1, tl, tm),
					buildMaxOccurrenceSegmentTree(input, tree, 2 * bfsIndex + 2, tm + 1, tr));
		}

	}

	private Map.Entry<Integer, Integer> createMaxOccurrenceEntry(Map.Entry<Integer, Integer> a,
			Map.Entry<Integer, Integer> b) {

		int entryCompare = Integer.compare(a.getKey(), b.getKey());

		Map.Entry<Integer, Integer> maxOccurrenceEntry = null;

		// since both the entry has same max, so need to add the occurrences.
		if (entryCompare == 0) {
			maxOccurrenceEntry = Map.entry(a.getKey(), a.getValue() + b.getValue());
		} else if (entryCompare > 0) {
			maxOccurrenceEntry = a;
		} else if (entryCompare < 0) {
			maxOccurrenceEntry = b;
		}

		return maxOccurrenceEntry;

	}

	@Override
	public Map.Entry<Integer, Integer> rangeQuery(int left, int right) {
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
	 * CASE 1 : If node-segment lies completely outside the query-segment: discard the segment. 
	 * 	 tl--tr [ql,qr)  or [ql,qr) tl--tr
	 * 
	 * CASE 2 : If node-segment lies completely inside the query-segment :  contribution to the max with occurrence is node's value.
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
	private Map.Entry<Integer, Integer> rangeQuery(int ql, int qr, int tl, int tr, int bfsIndex) {

		// CASE 1 : If node-segment lies completely outside the query-segment;
		// terminates the recursion
		if (tr < ql || qr <= tl) {
			return Map.entry(Integer.MIN_VALUE, 0);
		}

		// CASE 2 : If node-segment lies completely inside the query-segment; terminates
		// the recursion
		if (ql <= tl && tr < qr) {
			return tree[bfsIndex];
		}

		int tm = (tl + tr) / 2;
		return createMaxOccurrenceEntry(rangeQuery(ql, qr, tl, tm, 2 * bfsIndex + 1),
				rangeQuery(ql, qr, tm + 1, tr, 2 * bfsIndex + 2));

	}

	@Override
	public void update(Map.Entry<Integer, Integer> entry, int index) {
		update(entry.getKey(), index, root_tl, root_tr, bfsIndexStart);
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
			tree[bfsIndex] = Map.entry(data, 1);

		} else {
			int tm = (tl + tr) / 2;
			if (updateIndex <= tm) {
				// go left
				update(data, updateIndex, tl, tm, 2 * bfsIndex + 1);
			} else {
				// go right
				update(data, updateIndex, tm + 1, tr, 2 * bfsIndex + 2);
			}
			tree[bfsIndex] = createMaxOccurrenceEntry(tree[2 * bfsIndex + 1], tree[2 * bfsIndex + 2]);
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MaxOccurenceSegmentTree3 [tree=").append(Arrays.toString(tree)).append("]");
		return builder.toString();
	}

	@Override
	public int getFirstGreaterElement(Integer data, int ql, int qr) {

		if (tree[0].getKey() < data) {
			throw new IllegalArgumentException("given value is out of range of tree.");

		}
		return getIndexOfFirstGreaterElement(data, ql, qr, root_tl, root_tr, bfsIndexStart);
	}

	private int getIndexOfFirstGreaterElement(Integer data, int ql, int qr, int tl, int tr, int bfsIndex) {

		// if node range lies completely outside the query range
		if (tr < ql || tl > qr)
			return Integer.MIN_VALUE;

		// if node range lies completely inside the query range
		if (ql <= tl && tr <= qr) {

			// when the node-sum is less than given-max
			if (tree[bfsIndex].getKey() < data) {
				return Integer.MIN_VALUE;
			}

			if (tl == tr)
				return tl;
		}

		int mid = (tl + tr) / 2;
		int leftSum = getIndexOfFirstGreaterElement(data, ql, qr, tl, mid, 2 * bfsIndex + 1);
		if (leftSum != Integer.MIN_VALUE)
			return leftSum;
		return getIndexOfFirstGreaterElement(data, ql, qr, mid + 1, tr, 2 * bfsIndex + 2);

	}

}
