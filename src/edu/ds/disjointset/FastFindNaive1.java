package edu.ds.disjointset;

/**
 * 
 *  TimeComplexity for a sequence of m UNIONs and FINDs :  O(mn)
 * 
 * */
public class FastFindNaive1 implements DisjointSet<Integer> {

	private int[] arr = null;

	/**
	 * 
	 * If we have complex data : Map<ComplexData, Index> : value represents the
	 * index of array
	 * 
	 * arr : [0,1,2,3,4] array index : represents individual element or node
	 * 
	 * array value : contains representative of set.
	 */
	@Override
	public void makeSet(int size) {
		arr = new int[size];
		for (int element = 0; element < size; element++)
			arr[element] = element;
	}

	@Override
	public Integer find(Integer element) {
		return arr[element];
	}

	/**
	 * 
	 * Set the representative of a to b.
	 * 
	 * For each union : Time Complexity = O(n) Since we have n elements so maximum
	 * possible unions are : n-1
	 * 
	 * So total time complexity of all the union operations are: O(n*(n-1)) = O(n^2)
	 * 
	 */
	@Override
	public void union(Integer a, Integer b) {
		int a_rep = find(a);
		int b_rep = find(b);
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == a_rep)
				arr[i] = b_rep;
		}

	}
}
