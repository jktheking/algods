package edu.algods.queue;

public class KarumanchiTest {

	public static void main(String[] args) {

		//reverseQueue();
		implementQueueUsingTwoStacks();

	}

	private static void reverseQueue() {
		QueueKarumanchiQuestions<Integer> questions = new QueueKarumanchiSolutions<>();
		Queue<Integer> q = new UnBoundedArrayQueue<>(5);
		q.offer(10);
		q.offer(20);
		q.offer(30);
		q.offer(40);
		q.offer(50);
		System.out.println("before reverse:" + q);
		questions.reverseQueue(q);
		System.out.println("after reverse:" + q);

	}

	private static void implementQueueUsingTwoStacks() {
		QueueKarumanchiQuestions<Integer> questions = new QueueKarumanchiSolutions<>();
		App.queue(questions.implementQueueUsingTwoStacks());

	}
	
	

}
