package edu.ds.tree.segmenttree;

public interface RangeUpdateRangeQueryST<T> {
	
	void update(T data, int left, int right);
	
	T query(int left, int right);


}
