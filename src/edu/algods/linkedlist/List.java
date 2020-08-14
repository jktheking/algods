package edu.algods.linkedlist;

public interface List<T extends Comparable<T>> {
	/**
	 * Always insert at the beginning
	 * */
	void insert(T data);
	
	void insert(Node<T> node);
	
	int size();
	
	boolean remove(T data);
	
	String traverse();
	
	String traverseRecursively();
	
	boolean insertAfter(T search, T data);
	
	boolean insertBefore(T search, T data);
	

}
