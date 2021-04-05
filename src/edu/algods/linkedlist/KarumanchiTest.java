package edu.algods.linkedlist;

public class KarumanchiTest {

	public static void main(String[] args) {

		// findNthNodeFromEnd();
		// detectLoopUsingHashingTechnique();

		// detectLoopUsingFloydCycleFinding();

		// findLenghtOfTheLoopUsingFloydCycleFinding();

		// insertInSortedLinkedList();

		// reverseLinkList();
		   //reverseLinkListRecursively();
		
		reverseLinkedListWithAlternateRecurssion();

		// findIntersectionNodeOfTwoListUsingBruteForce();
		// findIntersectionNodeOfTwoListUsingStack();

		// findIntersectionNodeUsingDistanceDiffTechnique();

		// middleElementOfLinkedList();
		// printListFromEndUsingRecursion();

		// isLinkedListEven();
		// mergeTwoSortedList();
		// reverseListInPairRecursively();

		// reverseListInPairIteratively();

		// splitCircularListIntoTwoEqualCircularList();

		// isPalindrome();

		// reverseInBlockOfKNodes();

		// getJosephPoint();

		// cloneListWithRandomPointer();

		// findLastModularNode();

		// findCeiledFractionalNodeUsingPointerIncrement();
		// findCeiledFractionalNodeUsingModuloDivison();

		//findSquareRootNode();
		//summationForEqualSizedList();
		//summationForUnEqualSizedList();
		//listReorder();
		//getCommonElementList();
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
	
	
	private static void reverseLinkedListWithAlternateRecurssion() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(15);
		integerList.insert(11);
		integerList.insert(9);
		integerList.insert(8);
		integerList.insert(7);
		integerList.insert(5);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.reverseLinkedListWithAlternateRecurssion();
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

	private static void middleElementOfLinkedList() {
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

	private static void printListFromEndUsingRecursion() {
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
		// System.out.println(questions.mergeTwoSortedList(integerListA,
		// integerListB).traverse());

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

	private static void isPalindrome() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(1);
		integerList.insert(2);
		integerList.insert(3);
		integerList.insert(4);
		integerList.insert(3);
		integerList.insert(2);
		integerList.insert(1);
		KarumanchiQuestions<Integer> questions = integerList;
		System.out.println(integerList.traverse());
		questions.isListPalindrome();
	}

	private static void reverseInBlockOfKNodes() {
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
		questions.reverseInBlockOfKNodes(0);
		System.out.println(integerList.traverse());
	}

	private static void getJosephPoint() {
		LinkedList<Character> charList = new LinkedList<>();

		Node<Character> T = new Node<>(Character.valueOf('T'));
		Node<Character> A = new Node<>(Character.valueOf('A'));

		charList.insert(T);
		//		charList.insert(Character.valueOf('S'));
		//		charList.insert(Character.valueOf('R'));
		//		charList.insert(Character.valueOf('Q'));
		//		charList.insert(Character.valueOf('P'));
		//		charList.insert(Character.valueOf('O'));
		//		charList.insert(Character.valueOf('N'));
		//		charList.insert(Character.valueOf('M'));
		//		charList.insert(Character.valueOf('L'));
		//		charList.insert(Character.valueOf('K'));
		//		charList.insert(Character.valueOf('J'));
		//		charList.insert(Character.valueOf('I'));
		//		charList.insert(Character.valueOf('H'));
		//		charList.insert(Character.valueOf('G'));
		//		charList.insert(Character.valueOf('F'));
		charList.insert(Character.valueOf('E'));
		charList.insert(Character.valueOf('D'));
		charList.insert(Character.valueOf('C'));
		charList.insert(Character.valueOf('B'));
		charList.insert(A);

		T.setNext(A);
		KarumanchiQuestions<Character> questions = charList;
		System.out.println(charList.traverse());
		questions.getJosephPoint(1);

	}

	private static void cloneListWithRandomPointer() {

		Node<Character> A = new Node<>(Character.valueOf('A'));
		Node<Character> B = new Node<>(Character.valueOf('B'));
		Node<Character> C = new Node<>(Character.valueOf('C'));
		Node<Character> D = new Node<>(Character.valueOf('D'));
		Node<Character> E = new Node<>(Character.valueOf('E'));
		Node<Character> F = new Node<>(Character.valueOf('F'));

		A.setRandomNext(D);
		B.setRandomNext(E);
		C.setRandomNext(F);
		D.setRandomNext(A);
		E.setRandomNext(B);
		F.setRandomNext(B);

		LinkedList<Character> charList = new LinkedList<>();

		charList.insert(F);
		charList.insert(E);
		charList.insert(D);
		charList.insert(C);
		charList.insert(B);
		charList.insert(A);

		KarumanchiQuestions<Character> questions = charList;
		System.out.println(charList.traverse());
		System.out.println("-----------cloned list-----");
		questions.cloneListWithRandomPointer();
	}

	private static void findLastModularNode() {
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
		System.out.println(questions.findLastModularNode(9));
	}

	private static void findCeiledFractionalNodeUsingPointerIncrement() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(14);
		integerList.insert(13);
		integerList.insert(12);
		integerList.insert(11);
		integerList.insert(10);
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
		System.out.println(questions.findCeiledFractionalNodeUsingPointerIncrement(5));
	}

	private static void findCeiledFractionalNodeUsingModuloDivison() {
		LinkedList<Integer> integerList = new LinkedList<>();
		integerList.insert(14);
		integerList.insert(13);
		integerList.insert(12);
		integerList.insert(11);
		integerList.insert(10);
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
		System.out.println(questions.findCeiledFractionalNodeUsingModuloDivison(5));
	}

	private static void findSquareRootNode() {
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
		System.out.println(questions.findSquareRootNode());
	}

	private static void summationForEqualSizedList() {
		LinkedList<Integer> digitList1 = new LinkedList<>();
		digitList1.insert(5);
		digitList1.insert(4);
		digitList1.insert(7);
		digitList1.insert(2);
		digitList1.insert(9);
		
		LinkedList<Integer> digitList2 = new LinkedList<>();
		digitList2.insert(9);
		digitList2.insert(4);
		digitList2.insert(6);
		digitList2.insert(3);
		digitList2.insert(8);
		
		KarumanchiQuestions<Integer> questions = digitList1;
		System.out.println(digitList1.traverse());
		System.out.println(digitList2.traverse());
		List<Integer> resultList = questions.summationForEqualSizedList(digitList1, digitList2);
		System.out.println(resultList.traverse());
		

	}
	
	private static void summationForUnEqualSizedList() {
		LinkedList<Integer> digitList1 = new LinkedList<>();
		
		digitList1.insert(6);
		digitList1.insert(8);
		digitList1.insert(5);
		digitList1.insert(4);
		digitList1.insert(7);
//		digitList1.insert(2);
//		digitList1.insert(9);
		
		LinkedList<Integer> digitList2 = new LinkedList<>();
		digitList2.insert(9);
		digitList2.insert(4);
		digitList2.insert(6);
		digitList2.insert(3);
		digitList2.insert(8);
		
		KarumanchiQuestions<Integer> questions = digitList1;
		System.out.println(digitList1.traverse());
		System.out.println(digitList2.traverse());
		List<Integer> resultList = questions.summationForUnEqualSizedList(digitList1, digitList2);
		System.out.println(resultList.traverse());
		

	}
	
	private static void listReorder() {
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
		System.out.println(questions.listReorder().traverse());
	}
	
	private static void getCommonElementList() {

		LinkedList<Integer> integerList1 = new LinkedList<>();
		integerList1.insert(11);
		integerList1.insert(9);
		integerList1.insert(7);
		integerList1.insert(5);
		integerList1.insert(4);
		integerList1.insert(2);
		
		LinkedList<Integer> integerList2 = new LinkedList<>();
		
		integerList2.insert(15);
		integerList2.insert(11);
		integerList2.insert(10);
		integerList2.insert(7);
		integerList2.insert(7);
		integerList2.insert(7);
		integerList2.insert(6);
		integerList2.insert(4);
		integerList2.insert(3);
		integerList2.insert(1);
		integerList2.insert(1);
		
		KarumanchiQuestions<Integer> questions = integerList1;
		System.out.println(integerList1.traverse());
		System.out.println(integerList2.traverse());
		List<Integer> resultList = questions.getCommonElementList(integerList1, integerList2);
		System.out.println(resultList.traverse());
		

	
		
	}

}
