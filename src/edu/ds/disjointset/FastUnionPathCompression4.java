package edu.ds.disjointset;

/**
 *
 * 
 * <pre>
 *  SET1: {0,1,2}  0-->1<--2
 *  SET1: {3,4}    3<--4
 *  SET3: {5,6,7,8}  5<--6<--7<--8
 *  
 *  tree representation     	  : [1,  1,  1,  3,  3,   5,  5,  6, 7]
 *                                   0   1   2   3   4    5   6   7  8
 * 
 * </pre>
 * 
 * 
 * TimeComplexity for a sequence of m UNIONs and FINDs : O(mlogn)
 * 
 * <pre>
 * 1.  Path compression (with naive linking) can require Sigma(n) time to perform a single UNION or FIND operation, 
 * where n is the number of elements.
 * Pf. The height of the tree is n - 1 after the sequence of union operations: UNION(1, 2), UNION(2, 3), c, UNION(n-1, n).
 * 
 * 
 * 2.  [Tarjan-van Leeuwen 1984] Starting from an empty data structure, path compression (with naive linking) performs any intermixed
 *      sequence of m >= n FIND and n - 1 UNION operations in   O(mlogn) time.
 *
 * Pf. Nontrivial but omitted.
 * 
 * </pre>
 * 
 */
public class FastUnionPathCompression4 implements DisjointSet<Integer> {

	private int[] arr = null;

	@Override
	public void makeSet(int size) {
		arr = new int[size];
		for (int element = 0; element < size; element++)
			arr[element] = element;
	}

	@Override
	public void union(Integer a, Integer b) {
		int a_rep = find(a);
		int b_rep = find(b);
		// setting a_tree as child tree of b_tree
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
		if (arr[element] == element)
			return element;
		arr[element] = find(arr[element]);
		return arr[element];

	}

}
