package edu.ds.queue;

import java.util.Arrays;

/**
 * 
 * Queue implementation is based on fixed capacity circular array.
 * 
 * We keeps the tail always one ahead of the index of the last element.
 * 
 * 
 * 
 */
public class BoundedArrayQueue<T extends Comparable<T>> implements Queue<T> {

	private int head = 0;
	private int tail = 0;

	private final T[] elements;

	@SuppressWarnings("unchecked")
	public BoundedArrayQueue(int capacity) {

		this.elements = (T[]) new Comparable[capacity];
	}

	/**
	 * O(1)
	 * 
	 * tail always points one position ahead, and we should do modulo on length so
	 * that elements array behave as circular-array.
	 */
	@Override
	public void offer(T t) {
		if (isFull())
			throw new RuntimeException("QUEUE IS FULL");
		elements[tail] = t;
		tail = ++tail % elements.length;

	}

	/**
	 * O(1)
	 */
	@Override
	public T poll() {
		if (isEmpty())
			throw new RuntimeException("QUEUE IS EMPTY");
		T t = elements[head];

		elements[head] = null;

		head = ++head % elements.length;

		return t;
	}

	/**
	 * O(1)
	 * 
	 * 
	 * When tail and head both lies in same circle then (tail - head) is +ve.
	 * 
	 * 
	 * when tail enters in next circle and head still lies in previous circle :
	 * (tail - head) < 0
	 * 
	 *  when queue is full: tail - head = 0.
	 * 
	 * 
	 * 
	 */
	@Override
	public int size() {
		int size = tail - head;
		if (isEmpty()) {
			size = 0;
			// full case and (tail - head) < 0 case.
		} else if (tail - head <= 0) {
			size = elements.length + tail - head;
		}
		return size;

	}

	/**
	 * O(1)
	 * 
	 */
	@Override
	public T peek() {
		return isEmpty() ? null : elements[head];
	}

	@Override
	public boolean isEmpty() {
		return head == tail && elements[head] == null;
	}

	@Override
	public boolean isFull() {
		return head == tail && elements[head] != null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoundedArrayQueue [head=").append(head).append(", tail=").append(tail).append(", elements=")
				.append(Arrays.toString(elements)).append("]");
		return builder.toString();
	}

}
