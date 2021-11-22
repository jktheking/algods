package edu.ds.tree.bit;


/**
 * 
 * link: https://www.topcoder.com/thrive/articles/Binary%20Indexed%20Trees
 * 
 * https://cp-algorithms.com/data_structures/fenwick.html
 * 
 * For "INSANE"
 * 1. rangeUpdate and pointQuery 
 * 2. rangeUpdate and rangeQuery 
 * 
 * Please refer CP algorithm Fenwick tree
 * 
 * */
public interface BIT<T> {
	
	/**
	 * Increase the given index by delta.
	 * 
	 * */
	void pointUpdate(int index, T delta);
	
	 T rangeQuery(int ql, int qr);
	 
	 T pointQuery(int index);
	 
	
	
	 
}
