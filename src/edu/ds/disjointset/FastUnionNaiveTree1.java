package edu.ds.disjointset;

/**
 * 
 * TimeComplexity for a sequence of m UNIONs and FINDs :  O(mn)
 * 
 * */
public class FastUnionNaiveTree1 implements DisjointSet<Integer> {

	private int[] arr = null;

	@Override
	public void makeSet(int size) {
		arr = new int[size];
		for (int element = 0; element < size; element++)
			arr[element] = element;
	}

	/**
	 * 
	 * 
	 * <pre>
	 * TimeComplexity of Single find : O(n)
	 * In case of skewed tree : union(1)-->union(2)-->union(3) ..
	 *  1-->2-->3-->4--5
	 * find(1) has to traverse all the nodes till 5.
	 * </pre>
	 * 
	 * TimeComplexity for m finds : O(mn)
	 * 
	 */
	@Override
	public Integer find(Integer element) {
		return element == arr[element] ? element : find(arr[element]);

	}

	/**
	 * pseudo union depends on time-complexity of find.
	 */
	@Override
	public void union(Integer a, Integer b) {
		int a_rep = find(a);
		int b_rep = find(b);
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

}
