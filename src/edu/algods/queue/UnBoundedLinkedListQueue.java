package edu.algods.queue;

/**
 * 
 * [HEAD]-->[]-->[]-->[TAIL]-->
 * 
 * 
 */
public class UnBoundedLinkedListQueue<T extends Comparable<T>> implements Queue<T> {

	private static final class Node<N extends Comparable<N>> {

		private final N data;
		private Node<N> next;

		private Node(N data) {
			this.data = data;
		}

	}

	private int size = 0;

	private Node<T> head = null;
	private Node<T> tail = null;

	@Override
	public void offer(T t) {
		size++;
		Node<T> node = new Node<>(t);
		if (isEmpty()) {
			head = tail = node;
		} else {
			tail.next = node;
			tail = node;
		}

	}

	@Override
	public T poll() {
		if (isEmpty())
			throw new RuntimeException("QUEUE IS EMPTY");
		size--;
		Node<T> node = head;
		// when there is only single element in queue
		if (head == tail) {
			head = tail = null;
		} else {
			head = head.next;
		}
		return node.data;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public T peek() {
		return isEmpty() ? null : head.data;
	}

	@Override
	public boolean isEmpty() {
		// when there is nothing to poll
		return head == null;
	}

	@Override
	public boolean isFull() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		StringBuilder ss = new StringBuilder("[");
		Node<T> temp = head;
		while (temp != null) {
			ss.append(temp.data);
			if (temp.next != null)
				ss.append(", ");
			temp = temp.next;
		}
		ss.append("]");
		return ss.toString();
	}

}
