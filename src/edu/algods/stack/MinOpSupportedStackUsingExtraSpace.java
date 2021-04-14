package edu.algods.stack;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MinOpSupportedStackUsingExtraSpace<T extends Comparable<T>> implements Stack<T> {

	private int elmentCount;
	private T[] arr;

	// MinOperation stack keeps the min of main stack
	private final Stack<T> minOperationSupportingStack = new ArrayBackedStack<>();

	private static final int DEFAULT_CAPACITY = 13;

	@SuppressWarnings("unchecked")
	public MinOpSupportedStackUsingExtraSpace() {
		super();
		this.arr = (T[]) new Comparable[DEFAULT_CAPACITY];
	}

	@SuppressWarnings("unchecked")
	public MinOpSupportedStackUsingExtraSpace(int initialCapacity) {
		super();
		this.arr = (T[]) new Comparable[initialCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : initialCapacity];
	}

	@Override
	public void push(T data) {

		if (minOperationSupportingStack.isEmpty() || data.compareTo(minOperationSupportingStack.peek()) <= 0) {
			// we need to push the duplicate min as well
			minOperationSupportingStack.push(data);
		}

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

		// need to maintain the minstack as well
		if (minOperationSupportingStack.peek().compareTo(data) == 0) {
			minOperationSupportingStack.pop();
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
	public T getMin() throws EmptyStackException {
		return minOperationSupportingStack.peek();
	}

}
