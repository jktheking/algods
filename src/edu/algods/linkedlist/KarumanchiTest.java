package edu.algods.linkedlist;

public class KarumanchiTest {

	public static void main(String[] args) {

		// findNthNodeFromEnd();
		// detectLoopUsingHashingTechnique();

		// detectLoopUsingFloydCycleFinding();

		// findLenghtOfTheLoopUsingFloydCycleFinding();

		//insertInSortedLinkedList();
		
		//reverseLinkList();
		//reverseLinkListRecursively();
		
		//findIntersectionNodeOfTwoListUsingBruteForce();
		//findIntersectionNodeOfTwoListUsingStack();
		
		//findIntersectionNodeUsingDistanceDiffTechnique();
		
		//middleElementOfLinkedList();
		//printListFromEndUsingRecursion();
		
		//isLinkedListEven();
		//mergeTwoSortedList();
		//reverseListInPairRecursively();
		
		//reverseListInPairIteratively();
		
		splitCircularListIntoTwoEqualCircularList();

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
		Node<Integer> node2 = new Node<Integer>(2);
		Node<Integer> node6 = new Node<Integer>(6);

		LinkedList<Integer> integerList = new LinkedList<>();

		integerList.insert(node1);
		integerList.insert(node2);
		integerList.insert(3);
		integerList.insert(4);
		integerList.insert(5);
		integerList.insert(node6);
		integerList.insert(7);
		integerList.insert(8);
		integerList.insert(9);

		node1.setNext(node6);

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

	private static void insertInSortedLinkedList() {

		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		System.out.println(questions.insertInSortedLinkedList(13));
		System.out.println(integerList.traverse());
	}

	private static void reverseLinkList() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.reverseLinkList();
		System.out.println(integerList.traverse());
	}
	
	private static void reverseLinkListRecursively() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.reverseLinkListRecursively();
		System.out.println(integerList.traverse());
	}
	
	private static void findIntersectionNodeOfTwoListUsingBruteForce() {
		LinkedList<Integer> listA = new LinkedList<>();
		LinkedList<Integer> listB = new LinkedList<>();
		
		Node<Integer> intersectionNode = new Node<Integer>(7);
		Node<Integer> startNodeListB = new Node<Integer>(1);
		
		listA.insert(25);
		listA.insert(22);
		listA.insert(17);
		listA.insert(8);
		listA.insert(intersectionNode);
		listA.insert(6);
		listA.insert(5);
		listA.insert(4);
		

	
		listB.insert(25);
		listB.insert(22);
		listB.insert(17);
		listB.insert(8);
		listB.insert(intersectionNode);
		listB.insert(5);
		listB.insert(4);
		listB.insert(3);
		listB.insert(2);
		listB.insert(startNodeListB);
		
		
		KarumanchiQuestions<Integer> questions = listA;
		questions.findIntersectionNodeOfTwoListUsingBruteForce(startNodeListB);
		
	}
	
	
	private static void findIntersectionNodeOfTwoListUsingStack() {
		LinkedList<Integer> listA = new LinkedList<>();
		LinkedList<Integer> listB = new LinkedList<>();
		
		Node<Integer> intersectionNode = new Node<Integer>(7);
		Node<Integer> startNodeListB = new Node<Integer>(1);
		
		Node<Integer> node25 = new Node<Integer>(25);
		Node<Integer> node22 = new Node<Integer>(22);
		Node<Integer> node17 = new Node<Integer>(17);
		Node<Integer> node8 = new Node<Integer>(8);
		
		
		
		listA.insert(node25);
		listA.insert(node22);
		listA.insert(node17);
		listA.insert(node8);
		listA.insert(intersectionNode);
        listA.insert(6);
		
		listA.insert(5); 
		listA.insert(4);
		
		listB.insert(node25);
		listB.insert(node22);
		listB.insert(node17);
		listB.insert(node8);
		listB.insert(intersectionNode);
		listB.insert(5);
		listB.insert(4);
		listB.insert(3);
		listB.insert(2);
		listB.insert(startNodeListB);
		
		
		KarumanchiQuestions<Integer> questions = listA;
		questions.findIntersectionNodeOfTowListUsingStack(startNodeListB);
		
	}
	
	
	private static void findIntersectionNodeUsingDistanceDiffTechnique() {
		LinkedList<Integer> listA = new LinkedList<>();
		LinkedList<Integer> listB = new LinkedList<>();
		
		Node<Integer> intersectionNode = new Node<Integer>(7);
		Node<Integer> startNodeListB = new Node<Integer>(1);
		
		Node<Integer> node25 = new Node<Integer>(25);
		Node<Integer> node22 = new Node<Integer>(22);
		Node<Integer> node17 = new Node<Integer>(17);
		Node<Integer> node8 = new Node<Integer>(8);
		
		
		
		listA.insert(node25);
		listA.insert(node22);
		listA.insert(node17);
		listA.insert(node8);
		listA.insert(intersectionNode);
        listA.insert(6);
		
		listA.insert(5); 
		listA.insert(4);
		
		listB.insert(node25);
		listB.insert(node22);
		listB.insert(node17);
		listB.insert(node8);
		listB.insert(intersectionNode);
		listB.insert(5);
		listB.insert(4);
		listB.insert(3);
		listB.insert(2);
		listB.insert(startNodeListB);
		
		
		KarumanchiQuestions<Integer> questions = listA;
		questions.findIntersectionNodeUsingDistanceDiffTechnique(listB);
		
	}
	
	
	private static void middleElementOfLinkedList(){
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		integerList.insert(4);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(questions.middleElementOfLinkedList());
	}
	
	
	private static void printListFromEndUsingRecursion(){
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		integerList.insert(4);
		System.out.println(integerList.traverse());
		KarumanchiQuestions<Integer> questions = integerList;
		questions.printListFromEndUsingRecursion();
		
	}
	
	
	private static void isLinkedListEven() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		integerList.insert(4);
		System.out.println(integerList.traverse());
		KarumanchiQuestions<Integer> questions = integerList;
		questions.isLinkedListEven();
	}
	
	private static void mergeTwoSortedList() {
		
		LinkedList<Integer> integerListA = new LinkedList<>();
		integerListA.insert(15);
		integerListA.insert(11);
		integerListA.insert(9);
		integerListA.insert(9);
		integerListA.insert(8);
		integerListA.insert(7);
		integerListA.insert(5);
		integerListA.insert(4);
		System.out.println(integerListA.traverse());
		
		LinkedList<Integer> integerListB = new LinkedList<>();
		integerListB.insert(18);
		integerListB.insert(10);
		integerListB.insert(9);
		integerListB.insert(6);
		integerListB.insert(3);
		System.out.println(integerListB.traverse());
		KarumanchiQuestions<Integer> questions = integerListA;
		//System.out.println(questions.mergeTwoSortedList(integerListA, integerListB).traverse());
		
		questions.mergeTwoSortedListRecursively(integerListA, integerListB);
		
	}
	
	private static void reverseListInPairRecursively() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(6);
		integerList.insert(5);
		integerList.insert(4);
		integerList.insert(3);
		integerList.insert(2);
		integerList.insert(1);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.reverseListInPairRecursively();
		System.out.println(integerList.traverse());
	}
	
	
	private static void reverseListInPairIteratively() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
        integerList.insert(6);
		integerList.insert(5);
		integerList.insert(4);
		integerList.insert(3);
		integerList.insert(2);
		integerList.insert(1);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.reverseListInPairIteratively();
		System.out.println(integerList.traverse());
	}
	
	private static void splitCircularListIntoTwoEqualCircularList() {
		LinkedList<Integer> integerList = new LinkedList<>();
		Node<Integer> node9 = new Node<>(9);
		Node<Integer> node0 = new Node<>(0);
		
		Node<Integer> node8 = new Node<>(8);
		
		integerList.insert(node9);
		integerList.insert(node8);
		integerList.insert(7);
        integerList.insert(6);
		integerList.insert(5);
		integerList.insert(4);
		integerList.insert(3);
		integerList.insert(2);
		integerList.insert(1);
		integerList.insert(node0);
		
		node8.setNext(node0);
		
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.splitCircularListIntoTwoEqualCircularList();
		
	}
}
