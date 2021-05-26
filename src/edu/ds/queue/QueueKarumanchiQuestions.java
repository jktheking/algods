package edu.ds.queue;

public interface QueueKarumanchiQuestions<T extends Comparable<T>> {

	/**
	 * Problem-1 Give an algorithm for reversing a queue Q. To access the queue, we
	 * are only allowed to use the methods of queue ADT.
	 */
	void reverseQueue(Queue<T> queue);
	
	/**
	 * Problem-2 How can you implement a queue using two stacks?
	 * 
	 * */
	Queue<T> implementQueueUsingTwoStacks();
}
