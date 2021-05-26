package edu.ds.queue;

public interface Queue<T extends Comparable<T>> {
	
	void offer(T t);
	
	T poll();
	
	int size();
	
	T peek();
	
   boolean isEmpty();
   
   boolean isFull();
   

}
