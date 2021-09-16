package edu.ds.tree.segmenttree;

public interface RangeUpdateST<T> {
	
	void update(T data, int left, int right);
	
	T query(int index);

}
