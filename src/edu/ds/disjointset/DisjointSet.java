package edu.ds.disjointset;

public interface DisjointSet<T extends Comparable<T>> {

	void makeSet(int size);

	/**
	 * @return returns the representative (also called leader) of the set that
	 *         contains the element passed in parameter.
	 */
	T find(T element);

	/**
	 * merges the two specified sets (the set in which the element a is located, and
	 * the set in which the element b is located) 
	 * 
	 */
	void union(T a, T b);

}
