package edu.ds.stack;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * Implementation Note:
 * If y<=X, then 
 * let's say there is a value Z = 2*y - X. This Z will always be smaller than X.
 * 
 * PUSH STRATEGY : If current_element to be pushed on the stack is smaller than
 * the min_element, then instead of pushing actual current element we will push the encoded element
 * the difference (2*CURRENT_ELEMENT - MIN_ELEMENT). This calculated difference
 * will help to preserve the previous_min.
 * 
 * Since the current_element to be pushed is smaller than the min_element, means
 * current_elemnt is going to be the min_element post push operation so we can
 * follow the following nomenclature:
 * 
 * CURRENT_MIN --> current_elemt
 * 
 * PREVIOUS_MIN --> min_element
 * 
 * STACK_ELEMENT --> calculated element to be pushed on stack
 * 
 * STACK_ELEMENT = 2*CURRENT_MIN - PREVIOUS_MIN;
 * 
 * 
 * 
 * POP STRATEGY :
 * 
 * If popped element is greater than CURRENT_MIN then do nothing.
 * 
 * Else If popped element is less than equal to CURRENT_MIN  means we got anomaly_flag: apply the below
 * mentioned formula to get the PREVIOUS_MIN
 * 
 * PREVIOUS_MIN = 2*CURRENT_MIN - STACK_ELEMENT
 * 
 *
 * 
 * 
 */
public class MinOpSupportedStackUsingConstantSpace implements Stack<BigDecimal> {

	private int elmentCount;
	private BigDecimal[] arr;

	private BigDecimal minElement;

	private static final int DEFAULT_CAPACITY = 13;

	public MinOpSupportedStackUsingConstantSpace() {
		super();
		this.arr = new BigDecimal[DEFAULT_CAPACITY];
	}

	public MinOpSupportedStackUsingConstantSpace(int initialCapacity) {
		super();
		this.arr = new BigDecimal[initialCapacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : initialCapacity];
	}

	/**
	 * 
	 * 
	 * 
	 * */
	@Override
	public void push(BigDecimal data) {
		if (minElement == null) {
			minElement = data;
		} else if (data.compareTo(minElement) <= 0) {
			// STACK_ELEMENT = 2*CURRENT_MIN - PREVIOUS_MIN;
			BigDecimal stackElement = BigDecimal.valueOf(2).multiply(data).subtract(minElement);
			minElement = data;
			data = stackElement;

		}

		if (elmentCount == arr.length) {
			resize(2 * elmentCount);
		}
		// add the element and increment the size by 1
		// example stack[0]=data; size==1
		arr[elmentCount++] = data;

	}

	private void resize(int capacity) {
		BigDecimal[] copyStack = new BigDecimal[capacity < DEFAULT_CAPACITY ? DEFAULT_CAPACITY : capacity];
		System.arraycopy(arr, 0, copyStack, 0, elmentCount);
		arr = copyStack;

	}

	@Override
	public BigDecimal pop() {

		if (isEmpty())
			throw new EmptyStackException();

		BigDecimal data = arr[--elmentCount];
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

		if (data.compareTo(minElement) <= 0) {

			// PREVIOUS_MIN = 2*CURRENT_MIN - STACK_ELEMENT
			BigDecimal previousMin = BigDecimal.valueOf(2).multiply(minElement).subtract(data);

			data = minElement;

			minElement = elmentCount == 0 ? null : previousMin;

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
	public BigDecimal peek() {
		if (isEmpty())
			throw new EmptyStackException();
		BigDecimal top = arr[elmentCount - 1];
		return top.compareTo(minElement) <= 0 ? minElement : top;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ArrayBackedStack [arr=").append(Arrays.toString(arr)).append("]");
		return builder.toString();
	}

	@Override
	public BigDecimal getMin() throws EmptyStackException {
		return minElement;
	}

}
