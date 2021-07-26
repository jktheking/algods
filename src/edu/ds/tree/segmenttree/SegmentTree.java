package edu.ds.tree.segmenttree;

/**
 * links :
 * 
 * https://cp-algorithms.com/data_structures/segment_tree.html
 * 
 * https://codeforces.com/blog/entry/15890
 * 
 * https://codeforces.com/blog/entry/18051
 * 
 * 
 * Q1 : Given is range [l,r) and to find is : sum/min/max/lcm/gcd/.. Q2 : Given
 * is sum/min/max/lcm/gcd/.. and to find is upper bound of range or both of the
 * bound.
 * 
 */
public interface SegmentTree<T> {

	T rangeQuery(int left, int right);

	void update(T data, int index);

	public static interface SumSegmentTree<T> {

		int getRightBoundOfRange(T data, int ql, int qr);

		int getRightBoundOfRange(T data);

	}

	/**
	 * We need to find the first greater element than 'data' while traversing the
	 * given array in given range(ql, qr).
	 */
	public static interface MaxSegmentTree<T> {
		int getFirstGreaterElement(T data, int ql, int qr);

	}
	
	/**
	 * We need to find the smallest element  greater than 'data' in given the given range(ql, qr).
	 */
	public static interface MergeSortSegmentTree<T>{
		int getSmallestGreaterElement(T data, int ql, int qr);
	}

}
