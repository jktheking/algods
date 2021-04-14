package edu.algods.stack;

public interface Stack<T extends Comparable<T>> {
	
	void push(T data);
	
	T pop();
	
	T peek();
	
	int size();
	
	//only supported in specific implementation.
	T getMin();
	
	boolean isEmpty();
	
	

}
