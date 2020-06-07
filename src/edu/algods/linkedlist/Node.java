package edu.algods.linkedlist;

public class Node<T extends Comparable<T>> {
	
	private final T data;
	private Node<T> next;
	
	Node(T data){
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	@Override
	public String toString() {
		return data.toString();
	}

	public T getData() {
		return data;
	}


}
