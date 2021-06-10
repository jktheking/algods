package edu.ds.disjointset;

import java.util.Arrays;

/**
 * UNION by Height (also called UNION by Rank): Make the tree with less height a
 * subtree of the tree with more height.
 * 
 * 
 * <pre>
 *  SET1: {0,1,2}  0-->1<--2
 *  SET1: {3,4}    3<--4
 *  SET3: {5,6,7,8}  5<--6<--7<--8
 *  
 *  tree representation with Rank : [1, -1,  1, -1,  3,  -3,  5,  6,  7]
 *                                   0   1   2   3   4    5   6   7   8   
 * 
 * Parent node contains height of the tree as rank.
 * </pre>
 * 
 * 
 * The precise analysis of the performance of a disjoint-set forest is somewhat
 * intricate. However, there is a much simpler analysis that proves that the
 * amortized time for any m Find or Union operations on a disjoint-set forest
 * containing n objects is O(mlog* n), where log* denotes the iterated
 * logarithm.
 * 
 * <pre>
 * Time Complexity in terms of iterative log
 * ------------------------------------------
 * The time complexity required to process m UNION and FIND-SET operations using
 * union-by-rank with path-compression heuristic is O(mlog*n) in the worst
 * case. -- Which may be also said as O(m), as log*n ≤ 5 practically (as
 * otherwise n is more than the number of atoms in universe!!)
 * 
 * Time Complexity in terms of inverse Ackermann Function
 * ------------------------------------------------------
 * 
 * Theorem. [Tarjan 1975] Link-by-size with path compression performs any 
 * intermixed sequence of m ≥ n FIND and n – 1 UNION operations in O(m * α(m, n)) 
 * time, where α(m, n) is a functional inverse of the Ackermann function.
 * 
 * m represents = number of FIND and UNION operations
 * n represents = number of nodes
 * 
 * - time_complexity is inversely proportional to m.
 * - time_complexity is directly proportional to n.
 * 
 * 
 * Theorem. [Tarjan-van Leeuwen 1984] Link-by- { size, rank } combined with
	{ path compression, path splitting, path halving } performs any intermixed
	sequence of m ≥ n find and n – 1 union operations in O(m *  α(m, n)) time.
 * 
 * 
 * </pre>
 * 
 * 
 * 
 * 
 */
public class FastUnionRankAndPathCompression5 implements DisjointSet<Integer> {

	private int[] arr = null;

	@Override
	public void makeSet(int size) {
		arr = new int[size];
		for (int element = 0; element < size; element++)
			arr[element] = -1;
	}

	@Override
	public void union(Integer a, Integer b) {
		int a_rep = find(a);
		int b_rep = find(b);

		int rank_a = Math.abs(arr[a_rep]);
		int rank_b = Math.abs(arr[b_rep]);

		if (rank_a == rank_b) {
			// when height of both the tree are same, then height of representative node
			// should be increased by 1.
			arr[b_rep] = -(rank_b + 1);
		} else if (rank_b < rank_a) {
			// swap the representative so that smaller rank tree is always merged with
			// bigger rank tree.
			int temp = a_rep;
			a_rep = b_rep;
			b_rep = temp;

		}
		// setting a_tree as sub_tree of b_tree
		unionInternal(a_rep, b_rep);
	}

	/**
	 * unionInternal is the actual union operation in case of tree based
	 * representation.
	 * 
	 * TimeComplexity : O(1)
	 * 
	 */
	private void unionInternal(Integer a_rep, Integer b_rep) {
		arr[a_rep] = b_rep;
	}

	/**
	 * FIND operation traverses a list of nodes on the way to the root. We can make
	 * later FIND operations efficient by making each of these vertices point
	 * directly to the root. This process is called path compression. For example,
	 * in the FIND(X) operation, we travel from X to the root of the tree. The
	 * effect of path compression is that every node on the path from X to the root
	 * has its parent changed to the root.
	 * 
	 * Time-Complexity of Single find : O(logn)
	 * 
	 * Time-Complexity of m finds : O(mlogn)
	 * 
	 */
	@Override
	public Integer find(Integer element) {
		if (arr[element] < 0)
			return element;
		arr[element] = find(arr[element]);
		return arr[element];

	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FastUnionRankAndPathCompression5 [arr=").append(Arrays.toString(arr)).append("]");
		return builder.toString();
	}

	
}
