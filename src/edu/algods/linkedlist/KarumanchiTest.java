package edu.algods.linkedlist;

public class KarumanchiTest {

	
	public static void main(String[] args) {
		
		//findNthNodeFromEnd();
		//detectLoopUsingHashingTechnique();
		
		detectLoopUsingFloydCycleFinding();
		
		//findLenghtOfTheLoopUsingFloydCycleFinding();
		
	}
	
	
	private static void findNthNodeFromEnd() {
		System.out.println("--findNthNodeFromEnd--");
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(1);
		integerList.insert(2);
		integerList.insert(3);
		integerList.insert(4);
		integerList.insert(5);
		integerList.insert(6);
		
		KarumanchiQuestions<Integer> questions = integerList;
		
		System.out.println(questions.findNthNodeFromEndUsingSizeOfList(4));
		System.out.println(questions.findNthNodeInSingleScanFromEndWithoutUsingSizeOfList(4));
	}
	
	
	private static void detectLoopUsingHashingTechnique() {
		System.out.println("--detectLoopUsingHashingTechnique--");
		
		 Node<Integer> node1 = new Node<Integer>(1);
		 Node<Integer> node4 = new Node<Integer>(4);
		
		LinkedList<Integer> integerList = new LinkedList<>();
		
		 integerList.insert(node1);
		 integerList.insert(2);
		 integerList.insert(3);
		 integerList.insert(node4);
		 integerList.insert(5);
		 integerList.insert(6);
		 
		 node1.setNext(node4);
		 
		
		System.out.println(integerList.traverse());
		
		KarumanchiQuestions<Integer> questions = integerList;
		
		System.out.println(questions.detectLoopUsingHashingTechnique());
	}
	
	
	private static void detectLoopUsingFloydCycleFinding() {
		System.out.println("--detectLoopUsingFloydCycleFinding--");
		
		 Node<Integer> node1 = new Node<Integer>(1);
		 Node<Integer> node4 = new Node<Integer>(4);
		
		LinkedList<Integer> integerList = new LinkedList<>();
		
		 integerList.insert(node1);
		 integerList.insert(2);
		 integerList.insert(3);
		 integerList.insert(5);
		 integerList.insert(6);
		 integerList.insert(7);
		 integerList.insert(8);
		 integerList.insert(node4);
		 integerList.insert(9);
		 integerList.insert(2);
		 integerList.insert(3);
		 
		 node1.setNext(node4);
		 
		
		System.out.println(integerList.traverse());
		
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(questions.detectLoopUsingFloydCycleFinding());
		
		
	}
	
	
	private static void findLenghtOfTheLoopUsingFloydCycleFinding() {
		System.out.println("--findLenghtOfTheLoopUsingFloydCycleFinding--");
		
		 Node<Integer> node1 = new Node<Integer>(1);
		 Node<Integer> node4 = new Node<Integer>(4);
		
		LinkedList<Integer> integerList = new LinkedList<>();
		
		 integerList.insert(node1);
		 integerList.insert(2);
		 integerList.insert(3);
		 integerList.insert(node4);
		 integerList.insert(5);
		 integerList.insert(6);
		 
		 node1.setNext(node4);
		 
		
		System.out.println(integerList.traverse());
		
		KarumanchiQuestions<Integer> questions = integerList;
		
		System.out.println(questions.findLenghtOfTheLoopUsingFloydCycleFinding());
	}
	

}
