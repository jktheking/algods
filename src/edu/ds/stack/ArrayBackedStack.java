package edu.ds.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class ArrayBackedStack<T extends Comparable<T>> implements Stack<T> {

	private int elmentCount;
	private T[] arr;

	private static final int DEFAULT_CAPACITY = 13;

	@SuppressWarnings("unchecked")
	public ArrayBackedStack() {
		super();
		this.arr = (T[]) new Comparable[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public ArrayBackedStack(int initialCapacity) {
		super();
		this.arr = (T[]) new Comparable[initialCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : initialCapacity];
	}

	/**
	 * O(n) resize operation occurs by doubling so : 2^(resize operations count) =
	 * capacity of array
	 * 
	 * How many copy operations are there if capacity is n and if we take initial
	 * capacity as 1:
	 * 
	 * n+ n/2 + n/4 + n/8 + ... + 4 + 2 + 1; now take the n as common :
	 * 
	 * n+ n/2 + n/4 + n/8 + ... + 4 + 2 + 1 = n(1 + 1/2 + 1/4 + +1/8 +...+4/n +2/n
	 * +1/n ) = n(1+ (1)) since : 1/2 + 1/4 + +1/8 +...+4/n +2/n +1/n = 1 approx =
	 * 2n
	 * 
	 * 
	 * So, resize operation is O(n)
	 * 
	 * Book Section : 4.5 and 4.6 =============================================== We
	 * call amortized time of a push operation is the average time taken by a push
	 * over the series of operations, that is, T(n)/n. Incremental Strategy: The
	 * amortized time (average time per operation) of a push operation is O(n)
	 * [O(2n)/n] =O(1). Doubling Strategy: In this method, the amortized time of a
	 * push operation is O(1) [O(n)/n].
	 * 
	 * 
	 */
	@Override
	public void push(T data) {

		if (elmentCount == arr.length) {
			resize(2 * elmentCount);
		}
		// add the element and increment the size by 1
		// example stack[0]=data; size==1
		arr[elmentCount++] = data;

	}

	private void resize(int capacity) {
		@SuppressWarnings("unchecked")
		T[] copyStack = (T[]) new Comparable[capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity];
		System.arraycopy(arr, 0, copyStack, 0, elmentCount);
		arr = copyStack;

	}

	/**
	 * 
	 * downsize strategy : if array is 75% empty then downsize it so that new array
	 * is 50% empty.
	 */
	@Override
	public T pop() {
		if (isEmpty())
			throw new EmptyStackException();

		T data = arr[--elmentCount];
		// set the popped to null so that if this position is some reference variable,
		// would mark the
		// referenced object in heap for garbage collection.
		arr[elmentCount] = null;

		// downsize the array when elementCount is less than 25% of array length.
		// no need to check elmentCount <= arr.length/4; as we are changing the array
		// capacity in multiple of DEFAULT_CAPACITY
		if (elmentCount > DEFAULT_CAPACITY && elmentCount == arr.length / 4) {
			resize(arr.length / 2);
		}

		return data;
	}

	@Override
	public int size() {
		return elmentCount;
	}

	@Override
	public boolean isEmpty() {
		return elmentCount == 0;
	}

	@Override
	public T peek() {
		if (isEmpty())
			throw new EmptyStackException();
		return arr[elmentCount - 1];
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArrayBackedStack [arr=").append(Arrays.toString(arr)).append("]");
		return builder.toString();
	}

	@Override
	public T getMin() {

		throw new UnsupportedOperationException("ArrayBackedStack doesnot support this operation");
	}

}
