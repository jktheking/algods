package edu.algods.linkedlist;

public interface List<T> {
	/**
	 * Always insert at the beginning
	 * */
	void insert(T data);
	
	int size();
	
	boolean remove(T data);
	
	String traverse();
    
	String traverseRecursively();
	
	boolean insertAfter(T search, T data);
	
	boolean insertBefore(T search, T data);
	

}
