package edu.ds.tree.bit;

public interface BinaryIndexedTree<T> {
	
	/**
	 * Increase the given index by delta.
	 * 
	 * */
	void pointUpdate(int index, T delta);
	 T rangeQuery(int ql, int qr);
}
