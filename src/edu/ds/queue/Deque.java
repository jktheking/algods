package edu.ds.queue;

/**
 * A linear collection that supports element insertion and removal at both ends.
 * The name deque is short for "double ended queue"and is usually pronounced
 * "deck".
 * 
 */
public interface Deque<T extends Comparable<T>> extends Queue<T> {

	void offer(T t);

	T poll();

	int size();

	T peek();

	boolean isEmpty();

	void offerFirst(T e);

	void offerLast(T e);

	T pollFirst();

	T pollLast();

	T peekFirst();

	T peekLast();

}
