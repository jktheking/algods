package edu.algods.stack;

public class SNode<T extends Comparable<T>> {
	
     private T data;
     private SNode<T> next;
     
     
	public SNode(T data) {
		super();
		this.data = data;
	}
	
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	public SNode<T> getNext() {
		return next;
	}
	public void setNext(SNode<T> next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return data.toString();
	}

}
