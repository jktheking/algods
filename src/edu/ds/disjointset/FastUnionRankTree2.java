package edu.ds.disjointset;

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
 *  TimeComplexity for a sequence of m UNIONs and FINDs :  O(mlogn)
 * 
 */
public class FastUnionRankTree2 implements DisjointSet<Integer> {

	private int[] arr = null;

	@Override
	public void makeSet(int size) {
		arr = new int[size];
		for (int element = 0; element < size; element++)
			arr[element] = -1;
	}

	/**
	 * Since we are always merging the smaller tree with larger tree so, the
	 * time-complexity of single find operation will be O(logn).
	 * 
	 * No. of nodes in tree >= 2^rank
	 * 
	 * 
	 * So, A sequence of m UNIONs and FINDs can then still cost O(m logn).
	 * 
	 * 
	 */
	@Override
	public void union(Integer a, Integer b) {
		int a_rep = find(a);
		int b_rep = find(b);

		int rank_a = Math.abs(arr[a_rep]);
		int rank_b = Math.abs(arr[b_rep]);

		if (rank_a == rank_b) {
			// when height of both the tree are same, then height of representative node
			// should be increased by 1.(since we store rank as -ve so need to decrease by 1)
			arr[b_rep] -= arr[b_rep];
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
	 * Time-Complexity of Single find : O(logn)
	 * 
	 * Time-Complexity of m finds : O(mlogn)
	 * 
	 * @see <b>union method for why the time-complexity is logn
	 */
	@Override
	public Integer find(Integer element) {
		return arr[element] < 0 ? element : find(arr[element]);

	}

}
