package edu.algods.stack;

import java.util.EmptyStackException;

public class LinkedListBackedStack<T extends Comparable<T>> implements Stack<T> {
	
	private int size;
	private SNode<T> root;

	/**
	 * O(1)
	 * 
	 * */
	@Override
	public void push(T data) {
		size++;
		if(root==null) {
			root = new SNode<>(data);
		}else {
			SNode<T>  newNode =  new SNode<>(data);
			newNode.setNext(root);
			root = newNode;
		}
	}

	/**
	 * O(1)
	 * 
	 * */
	@Override
	public T pop() {
		if(isEmpty()) throw new EmptyStackException();
		size--;
		T pop = root.getData();
		root = root.getNext();
		return pop;

	}

	/**
	 * O(1)
	 * 
	 * */
	@Override
	public int size() {
		return size;
	}

	/**
	 * O(1)
	 * 
	 * */
	@Override
	public boolean isEmpty() {
		return root==null;
	}

	/**
	 * O(1)
	 * 
	 * */
	@Override
	public T peek() {
		if(isEmpty()) throw new EmptyStackException();
		return root.getData();
	}

}
