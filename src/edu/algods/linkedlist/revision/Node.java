package edu.algods.linkedlist.revision;

public class Node<T extends Comparable<T>> {
	
	private T data;
	private Node<T> next;
	private Node<T> randomNext;
	
	Node(T data){
		this.data = data;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}
	
	public T getData() {
		return data;
	}

	public Node<T> getRandomNext() {
		return randomNext;
	}

	public void setRandomNext(Node<T> randomNext) {
		this.randomNext = randomNext;
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public void setData(T data) {
		this.data = data;
	}

}
