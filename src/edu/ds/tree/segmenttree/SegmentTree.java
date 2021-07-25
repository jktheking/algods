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

	public static interface MaxSegmentTree<T> {
		int getFirstGreaterElement(T data, int ql, int qr);

	}

}
