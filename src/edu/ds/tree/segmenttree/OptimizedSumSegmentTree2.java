package edu.ds.tree.segmenttree;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;

/**
 * 
 * 1. Segment tree uses just 2 * n memory, not 4 * n like some other
 * implementations offer.
 * 
 * 2. Input array elements are stored in continuous manner starting with index n
 * in tree array.
 * 
 */
public class OptimizedSumSegmentTree2 implements SegmentTree<Integer> {

	/**
	 * BFS indexed segment tree.
	 * 
	 */
	private final int[] tree;
	private final int root_tr;

	OptimizedSumSegmentTree2(int[] elements, boolean isFull) {
		if (isFull) {
			int powerOf2InputSize = getInputArraySizeAsPowerOf2(elements.length);
			root_tr = powerOf2InputSize - 1;
			tree = new int[2 * powerOf2InputSize - 1];
			buildFullSumSegmentTree(elements, powerOf2InputSize);
		} else {
			tree = new int[2 * elements.length - 1];
			root_tr = elements.length - 1;
			buildSumSegmentTree(elements, tree);
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
		buildSumSegmentTree(powerOf2Input, tree);

	}

	/**
	 * We do not save the node's segment explicitly, rather it is getting calculated
	 * on the fly using the root-node's segment.
	 * 
	 * <pre>
	 * Input array will be present in tree array at end.
	 * translation of input_array_index to tree_array_index
	 * 
	 *    tree_array_index = input_array_index + (input.length - 1) 
	 *   or
	 *    tree_array_index = input_array_index + root_tr
	 * 
	 * </pre>
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
	private void buildSumSegmentTree(int input[], int tree[]) {

		// copy input array at end of tree array
		for (int i = 0; i < input.length; i++) {
			tree[i + input.length - 1] = input[i];
		}

		// fill the remaining parent position with sum in tree array
		final int lastEmptyIndexInTreeArray = (tree.length - input.length) - 1;
		for (int parentIndex = lastEmptyIndexInTreeArray; parentIndex >= 0; parentIndex--) {
			tree[parentIndex] = tree[2 * parentIndex + 1] + tree[2 * parentIndex + 2];
		}

	}

	/**
	 * 
	 * <pre>
	 * 
	 * WHEN 'tl' is even then we should include the tl-node value in query-sum and
	 * go to the parent of (tl+1)th node.
	 * Since tl is even so next node is odd, so parent of tl+1 node => parent = (tl+1-1)/2 = tl/2 
	 * For odd child formula => 2*parent + 1 = child  i.e; parent =(child -1)/2; now substitute child with tl+1.
	 * 
	 * WHEN 'tl' is odd then no need to include the value of tl-node in query-sum, and go to the parent of 'tl' node.
	 * 
	 * WHEN 'tr' is even:
	 * Since tr is exclusive in query range, so if tr is even  then we should include the (tr-1)th node value in query-sum and go to the
	 * parent node of (tr-1). 
	 * WHEN 'tr' is odd : then no need to include the value of (tr-1)th node in query-sum, and go to the parent of 'tr' node.
	 * 
	 * </pre>
	 * 
	 * 
	 * tree_array_index = input_array_index + (input.length - 1) 
	 * 
	 * or tree_array_index = input_array_index + root_tr
	 * 
	 * Time Complexity = O(logn)
	 *
	 */
	@Override
	public Integer rangeQuery(int ql, int qr) {

		int result = 0;

		for (int tl = ql + root_tr, tr = qr + root_tr; tl < tr; tl = BitwiseUtils.divideBy2(tl), tr = BitwiseUtils
				.divideBy2(tr)) {

			if (BitwiseUtils.isEven(tl))
				result += tree[tl];

			if (BitwiseUtils.isEven(tr))
				result += tree[--tr];

		}

		return result;
	}

	/**
	 * 
	 * translation of input_array_index to tree_array_index
	 * 
	 * tree_array_index = input_array_index + (input.length - 1)
	 * 
	 * or tree_array_index = input_array_index + root_tr
	 * 
	 * Time-Complexity = O(logn)
	 */
	@Override
	public void update(Integer data, int index) {

		int treeArrayIndex = index + root_tr;

		tree[treeArrayIndex] = data;

		// now change the sum of all the parents
		while (treeArrayIndex > 0) {

			int parentIndex = BitwiseUtils.isEven(treeArrayIndex) ? BitwiseUtils.divideBy2(treeArrayIndex - 2)
					: BitwiseUtils.divideBy2(treeArrayIndex - 1);

			treeArrayIndex = parentIndex;

			tree[parentIndex] = tree[2 * parentIndex + 1] + tree[2 * parentIndex + 2];
		}

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SumSegmentTree [tree=").append(Arrays.toString(tree)).append("]");
		return builder.toString();
	}

}
