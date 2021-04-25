package edu.algods.queue;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueKarumanchiSolutions<T extends Comparable<T>> implements QueueKarumanchiQuestions<T> {

	/***
	 * 
	 * We have to use stack, so that element would get reversed on pop.
	 * 
	 */
	@Override
	public void reverseQueue(Queue<T> queue) {

		Deque<T> stack = new ArrayDeque<>();

		while (!queue.isEmpty())
			stack.push(queue.poll());
		while (!stack.isEmpty())
			queue.offer(stack.pop());

	}

	/**
	 * 
	 * Alogo:
	 * 
	 * 1. offer : <br>
	 * push the incoming element in offerStack
	 * 
	 * 2. poll : <br>
	 * if pollStack is empty: pop all the elements from offerStack and push into
	 * pollStack, and then return the top element of pollStack<br>
	 * 
	 * if pollStack is not empty : pop and return the element
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Override
	public Queue<T> implementQueueUsingTwoStacks() {

		return new Queue<T>() {

			final Deque<T> offerStack = new ArrayDeque<>();
			final Deque<T> pollStack = new ArrayDeque<>();

			@Override
			public void offer(T t) {
				offerStack.push(t);

			}

			@Override
			public T poll() {
				if (isEmpty())
					throw new RuntimeException("QUEUE IS EMPTY");

				if (pollStack.isEmpty()) {
					while (!offerStack.isEmpty())
						pollStack.push(offerStack.pop());
				}

				return pollStack.pop();
			}

			@Override
			public int size() {
				return pollStack.size() + offerStack.size();
			}

			@Override
			public T peek() {
				if (isEmpty())
					return null;

				if (pollStack.isEmpty()) {
					while (!offerStack.isEmpty())
						pollStack.push(offerStack.pop());
				}
				return pollStack.peek();

			}

			@Override
			public boolean isEmpty() {

				return offerStack.isEmpty() && pollStack.isEmpty();
			}

			@Override
			public boolean isFull() {
				throw new UnsupportedOperationException();

			}
			
			@Override
			public String toString() {
				return "{ pollStack:"+pollStack.toString() + "; offerStack:" + offerStack.toString() + " }";
			}
		};

	}

}
