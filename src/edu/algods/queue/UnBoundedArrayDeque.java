package edu.algods.queue;

import java.util.Arrays;

public class UnBoundedArrayDeque<T extends Comparable<T>> implements Deque<T> {

	/**
	 * 
	 * head_element and 'head' will lie at the same index of circular array.
	 * 
	 */
	private int head = 0;

	/**
	 * tail will always be one index ahead of tail_element in circular array
	 */
	private int tail = 0;

	private T[] elements;

	private static final int INITIAL_CAPACITY = 10;

	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words in
	 * an array. Attempts to allocate larger arrays may result in OutOfMemoryError:
	 * Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	@SuppressWarnings("unchecked")
	public UnBoundedArrayDeque(int capacity) {
		this.elements = (T[]) new Comparable[capacity <= 0 ? INITIAL_CAPACITY : capacity];
	}

	public UnBoundedArrayDeque() {
		this(INITIAL_CAPACITY);

	}

	/**
	 * 
	 * 	 
	 * 
	 */
	private void grow() {
		int oldCapacity = elements.length;
		int newCapacity = overflowProtectedNewCapacity(oldCapacity);
		elements = Arrays.copyOf(elements, newCapacity);
		realignHeadElements(oldCapacity, newCapacity);
	}

	/**
	 * Integer size = 32 bits<br>
	 * So possible values = 2^32. Since integer is signed so it is having two equal
	 * parts 2^31 each. <br>
	 * parts calculation 2^32 = 2*2^31 = 2^31 + 2^31
	 * 
	 * -2^31<--------------------------0----------------------------->+(2^31 - 1)
	 * 
	 * -ve range : -1 to -2^31
	 * 
	 * +ve range :0 to 2^31 -1
	 * 
	 * Integer.MAX_VALUE = 2^31 -1
	 * 
	 * MAX_ARRAY_SIZE = (Integer.MAX_VALUE - 8) = 2^31 -1 -8 = (2^31 -9)
	 * 
	 * CONDITION : (x - MAX_ARRAY_SIZE > 0 ) can be true in following two cases :
	 * 
	 * CASE 1: NON OVERFLOW CASE <br>
	 * In this case x is +ve. <br>
	 * MAX_ARRAY_SIZE < x < Integer.MAX_VALUE OR 2^31 -9 < x < 2^31 -1
	 *
	 *
	 * CASE2: OVERFLOW CASE : when x is near to Integer.MIN_VALUE <br>
	 * In this case x is -ve. <br>
	 * 
	 * Means x was intended to be greater than Integer.MAX_VALUE, but has
	 * over-flowed to -ve. <br>
	 * Overflow mathematics : x = Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
	 * 
	 * e.g. Lets add 500 to Integer.MAX_VALUE <br>
	 * int x = Integer.MAX_VALUE + 500 = 2^31 - 1 + 500 <br>
	 * => x = Integer.MIN_VALUE + 499 = -2^31 + 499 <br>
	 * 
	 * Now substitute the value of x in CONDITION (x - MAX_ARRAY_SIZE > 0 )
	 * 
	 * x - MAX_ARRAY_SIZE = -2^31 + 499 - MAX_ARRAY_SIZE = -2^31 + 499 - (2^31 -9) =
	 * 508 <br>
	 * This implies that if the value of x lies near to Integer.MIN_VALUE then
	 * condition may come true.
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Double the capacity if small i.e less than 64; else grow by 50%
	 * 
	 */
	private int overflowProtectedNewCapacity(int oldCapacity) {

		// oldCapacity>>1 ; means integer divison by 2
		int jump = (oldCapacity < 64) ? oldCapacity + 2 : oldCapacity >> 1;

		int newCapacity = oldCapacity + jump;

		// newCapacity - MAX_ARRAY_SIZE > 0 : can be true in two cases
		// case 1 : when newCapacity is valid and lies between MAX_ARRAY_SIZE and
		// Integer.MAX_VALUE
		// case2: when newCapacity has over-flown and becomes larger -ve i.e. near to
		// Integer.MIN_VALUE
		if (newCapacity - MAX_ARRAY_SIZE > 0) {
			if (newCapacity < 0)
				throw new IllegalStateException("Sorry, UnboundedArrayQueue too big");
			newCapacity = Integer.MAX_VALUE;
		}

		return newCapacity;

	}

	/**
	 * Why should we avoid using tail realignment when growth is less than double ?
	 * 
	 * Since tail window moves in backward direction, so calculation will be little
	 * tricky.
	 * 
	 * 
	 * Head realignment: <br>
	 * Head window : start = head; end= oldCapacity.
	 * 
	 * post shift widow location:<br>
	 * 
	 * start = newSpace + head end = newCapacity.
	 * 
	 *
	 * 
	 * newSpace = newCapacity - oldCapacity
	 * 
	 * 
	 * 
	 * 
	 */
	private void realignHeadElements(int oldCapacity, int newCapacity) {
		final int newSpace = newCapacity - oldCapacity;
		final int windowSize = oldCapacity - head;
		System.arraycopy(elements, head, elements, head + newSpace, windowSize);
		int oldHead = head;
		head = head + newSpace;
		for (int i = oldHead; i < head; i++)
			elements[i] = null;
	}

	/**
	 * 
	 * head-add direction is anti-clockwise: 0,n,n-1,n-2,...3,2,1,0,n,n-1,n-2,...
	 */
	@Override
	public void offerFirst(T t) {
		head = head == 0 ? elements.length - 1 : head - 1;
		elements[head] = t;
		if (isFullInteranl())
			grow();

	}

	/**
	 * tail-add direction is clockwise:0,1,2,3,4,5,6,7..n,0,1,2..
	 */
	@Override
	public void offerLast(T t) {
		elements[tail] = t;
		tail = tail == elements.length - 1 ? 0 : tail + 1;
		if (isFullInteranl())
			grow();
	}

	@Override
	public void offer(T t) {
		offerLast(t);

	}

	/**
	 * head remove direction is clockwise:0,1,2,3,4,5.., n, 0, 1,2..
	 * 
	 */
	@Override
	public T pollFirst() {
		if (isEmpty())
			throw new RuntimeException("QUEUE IS EMPTY");

		T t = elements[head];

		elements[head] = null;

		head = head == elements.length - 1 ? 0 : head + 1;

		return t;
	}

	
	@Override
	public T poll() {
		return pollFirst();
	}

	/**
	 * tail-remove direction is anti-clockwise:0,n,n-1,n-2,...3,2,1,0,n,n-1,n-2,...
	 * 
	 */
	@Override
	public T pollLast() {
		if (isEmpty())
			throw new RuntimeException("QUEUE IS EMPTY");

		tail = tail == 0 ? elements.length - 1 : tail - 1;

		T t = elements[tail];
		elements[tail] = null;

		return t;
	}

	
	/**
	 * head remove direction is clockwise:0,1,2,3,4,5.., n, 0, 1,2..
	 * Also, head element lies at head.
	 * 
	 */
	@Override
	public T peekFirst() {
		return isEmpty() ? null : elements[head];
	}

	@Override
	public T peek() {
		return peekFirst();
	}

	/**
	 * tail-remove direction is anti-clockwise:0,n,n-1,n-2,...3,2,1,0,n,n-1,n-2,...
	 * 
	 */
	@Override
	public T peekLast() {
		return isEmpty() ? null : elements[tail == 0 ? elements.length - 1 : tail - 1];

	}

	@Override
	public boolean isEmpty() {
		return head == tail && elements[head] == null;
	}

	private boolean isFullInteranl() {
		return head == tail && elements[head] != null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BoundedArrayQueue [head=").append(head).append(", tail=").append(tail).append(", elements=")
				.append(Arrays.toString(elements)).append("]");
		return builder.toString();
	}

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

	@Override
	public boolean isFull() {
		throw new UnsupportedOperationException();
	}

}
