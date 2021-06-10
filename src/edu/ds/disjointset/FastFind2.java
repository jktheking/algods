package edu.ds.disjointset;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * TimeComplexity for a sequence of m UNIONs and FINDs using optimizedUnion :
 * O(mlogn)
 * 
 */
public class FastFind2 implements DisjointSet<Integer> {

	private int[] arr = null;

	Map<Integer, List<Integer>> intermediateUnionSetsMap = new HashMap<>();

	
	@Override
	public void makeSet(int size) {
		arr = new int[size];
		for (int element = 0; element < size; element++) {
			arr[element] = element;
			intermediateUnionSetsMap.put(element, List.of(element));
		}

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
		optimizedUnion(a, b);
	}

	/**
	 * <pre>
	 * If we do union in wrong order as follows : union(0)-->union(1)--> union(2) -->union(3) --> union(n)
	 * 				Time complexity           :      1            2           3           4         n
	 * 
	 * 1+2+3+4 + .. = n(n+1)/2 = n^2
	 * 
	 * Time complexity O(n^2)
	 * </pre>
	 */
	public void unionNaive(Integer a, Integer b) {
		int a_rep = find(a);
		int b_rep = find(b);

		List<Integer> a_repSet = intermediateUnionSetsMap.get(a_rep);
		List<Integer> b_repSet = intermediateUnionSetsMap.get(b_rep);

		for (Integer element : a_repSet) {
			arr[element] = b_rep;
			b_repSet.add(element);
		}

		a_repSet.clear();
	}

	/**
	 * 
	 * Whenever we merge the smaller set to larger set, merge time-complexity for a
	 * particular element is : log(n).
	 * 
	 * 
	 * Since we have total union-operation is : n-1; so total time complexity =
	 * (n-1)logn = O(nlogn).
	 *
	 */
	public void optimizedUnion(Integer a, Integer b) {

		int a_rep = find(a);
		int b_rep = find(b);

		List<Integer> a_repSet = intermediateUnionSetsMap.get(a_rep);
		List<Integer> b_repSet = intermediateUnionSetsMap.get(b_rep);

		// swap the sets, so that we will always iterate the smaller one to merge with
		// larger one
		if (a_repSet.size() > b_repSet.size()) {
			List<Integer> temp = a_repSet;
			a_repSet = b_repSet;
			b_repSet = temp;

		}

		for (Integer element : a_repSet) {
			arr[element] = b_rep;
			b_repSet.add(element);
		}

		a_repSet.clear();

	}
}
