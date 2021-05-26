package edu.ds.linkedlist;

import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class LinkedList<T extends Comparable<T>> implements List<T>, KarumanchiQuestions<T> {

	private int size = 0;
	private Node<T> root;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void insert(T data) {
		size++;
		Node<T> current = new Node<>(data);
		current.setNext(root);
		root = current;
	}

	@Override
	public void insert(Node<T> current) {
		size++;
		current.setNext(root);
		root = current;
	}

	@Override
	public void insertAtEnd(T data) {
		size++;
		Node<T> newNode = new Node<>(data);

		if (root == null) {
			root = newNode;
			return;
		}

		Node<T> temp = root;
		while (temp.getNext() != null)
			temp = temp.getNext();
		temp.setNext(newNode);

	}

	@Override
	public void insertAtEnd(Node<T> newNode) {
		size++;
		if (root == null) {
			root = newNode;
			return;
		}

		Node<T> temp = root;
		while (temp.getNext() != null)
			temp = temp.getNext();
		temp.setNext(newNode);

	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean remove(T data) {
		if (root == null)
			return false;
		// if the node to be removed is the root node
		if (root.getData().compareTo(data) == 0) {
			size--;
			root = root.getNext();
		}

		return remove(data, root, root.getNext());

	}

	private boolean remove(T data, Node<T> previousNode, Node<T> actualNode) {
		while (actualNode != null) {
			if (actualNode.getData().compareTo(data) == 0) {
				size--;
				previousNode.setNext(actualNode.getNext());
				// mark the actualNode, which we have deleted to null, so that this node will be
				// garbage collected.
				actualNode = null;
				return true;
			}

			previousNode = actualNode;
			actualNode = actualNode.getNext();
		}

		return false;
	}

	@Override
	public String traverse() {
		java.util.List<Integer> m_l = getNodeCount_M_L();
		if (!m_l.isEmpty()) {
			return loopTraversalForCircular(m_l);
		}
		return loopTraversal();
	}

	private String loopTraversal() {
		StringBuilder path = new StringBuilder();
		path.append("root-->").append("\n");
		for (Node<T> node = root; node != null; node = node.getNext()) {
			path.append(node);
			if (node.getNext() != null) {
				path.append("-->");
			}
		}
		return path.toString();
	}

	private java.util.List<Integer> getNodeCount_M_L() {
		Node<T> slowRef = root;
		Node<T> fastRef = root;

		boolean hasLoop = false;
		// fastRef will reach the end of the list first if there is no loop.
		while (fastRef != null && fastRef.getNext() != null) {
			slowRef = slowRef.getNext();
			fastRef = fastRef.getNext().getNext();

			// means there is a loop
			if (slowRef == fastRef) {
				hasLoop = true;
				break;
			}
		}

		if (hasLoop) {
			// find out the Loop node count l
			int l = 1;
			fastRef = fastRef.getNext();
			while (slowRef != fastRef) {
				fastRef = fastRef.getNext();
				l++;
			}

			// find out the M node count m
			int m = 0;
			slowRef = root;
			while (slowRef != fastRef) {
				slowRef = slowRef.getNext();
				fastRef = fastRef.getNext();
				m++;
			}

			return java.util.List.of(m, l);
		}
		return java.util.List.of();
	}

	private String loopTraversalForCircular(java.util.List<Integer> m_l) {
		// find out the parameters for printing matrix
		// let's try to store loop node as rectangle, so single dimension of loop is :
		// loopLength = L/2
		// also need to consider even and odd nodes in loop.

		int m = m_l.get(0);
		int l = m_l.get(1);

		final int loopLength = (l == 1) ? 1 : l / 2;

		final boolean loopOdd = l % 2 == 0 ? false : true;

		final int matrixL = 2 * (m + loopLength) - 1;
		final int matrixW = (l == 1) ? 2 : loopOdd ? 5 : 3;

		String matrix[][] = new String[matrixW][matrixL];

		Node<T> node = root;

		// filling first row of matrix
		for (int i = 0; i < matrixL; i++) {
			if (i % 2 == 0) {
				matrix[0][i] = node.toString();
				node = node.getNext();
			} else {
				matrix[0][i] = "-->";

			}
		}

		// filling last column of the matrix,starting at second row
		final int lastColIndex = matrixL - 1;
		for (int i = 1; i < matrixW; i++) {
			if (i % 2 == 0) {
				matrix[i][lastColIndex] = node.toString();
				node = node.getNext();
			} else {
				matrix[i][lastColIndex] = "|";
			}
		}

		// filling the last row of matrix as returning row, but starting at second last
		// col
		final int lastRowIndex = matrixW - 1;
		// number of times for loop to iterate = loopRowSize - 1; as last column is
		// already filled.
		final int loopRowSize = 2 * loopLength - 1;

		int i = lastColIndex - 1;
		for (int j = loopRowSize - 1; j > 0; j--) {
			if (i % 2 == 0) {
				matrix[lastRowIndex][i] = node.toString();
				node = node.getNext();
			} else {
				matrix[lastRowIndex][i] = "<--";
			}
			i--;
		}

		// filling the upgoing column from second last row, and stopping the loop at 2nd
		// row
		// here i from previous loop is used as column index, since i has taken one
		// extra decrement,
		// so need to adjust the i by 1
		final int adjusted_i = i + 1;
		for (int j = lastRowIndex - 1; j > 0; j--) {
			matrix[j][adjusted_i] = "|";

		}

		StringBuilder path = new StringBuilder();
		path.append("root-->").append("\n");
		// preparing the printable path
		for (int a = 0; a < matrixW; a++) {

			if (a != 0)
				path.append("\n");

			for (int b = 0; b < matrixL; b++) {
				if (matrix[a][b] == null) {
					// even place contains node data, and odd place contains link "--->"
					if ((b & 1) == 0) {
						path.append(" ");
					} else {
						path.append("   ");
					}

				} else {
					path.append(matrix[a][b]);

				}
			}

		}
		return path.toString();
	}

	@Override
	public String traverseRecursively() {
		StringBuilder links = new StringBuilder("root-->");
		recursiveTraversal(links, root);
		return links.toString();
	}

	private void recursiveTraversal(StringBuilder links, Node<T> node) {
		if (node == null) {
			return;
		}
		links.append(node);
		if (node.getNext() != null) {
			links.append("-->");
		}
		recursiveTraversal(links, node.getNext());

	}

	@Override
	public boolean insertAfter(T search, T data) {
		Node<T> current = root;
		while (current != null) {
			if (current.getData().compareTo(search) == 0) {
				size++;
				Node<T> actual = new Node<>(data);
				actual.setNext(current.getNext());
				current.setNext(actual);
				return true;
			}

			current = current.getNext();
		}

		return false;
	}

	@Override
	public boolean insertBefore(T search, T data) {

		if (root == null)
			return false;

		// if search node is the root node, means to insert at the beginning of the list
		if (root.getData().compareTo(search) == 0) {
			insert(data);
			return true;
		}

		// we need to compare the search node with next node, as we want to insert
		// before the search node, so we need to hold reference for both previousNode
		// and serachCandidateNode(let's call as actualNode)
		return insertBefore(search, data, root, root.getNext());

	}

	private boolean insertBefore(T search, T data, Node<T> previousNode, Node<T> actualNode) {
		while (actualNode != null) {
			if (actualNode.getData().compareTo(search) == 0) {
				size++;

				Node<T> newNode = new Node<>(data);
				newNode.setNext(actualNode);

				previousNode.setNext(newNode);

				return true;
			}
			previousNode = actualNode;
			actualNode = actualNode.getNext();
		}
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node<T> findNthNodeInSingleScanFromEndWithoutUsingSizeOfList(int n) {

		Node<T> tempRef;
		Node<T> solutionRef = root;
		int i;

		// move the tempRef by n position(considering positions not index).
		for (tempRef = root, i = 0; i < n; i++, tempRef = tempRef.getNext()) {
			if (tempRef == null) {
				throw new RuntimeException("nth position  is greater than the size of the linkedList");
			}

		}

		while (tempRef != null) {
			tempRef = tempRef.getNext();
			solutionRef = solutionRef.getNext();
		}

		return solutionRef;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node<T> findNthNodeFromEndUsingSizeOfList(int n) {

		if (size < n) {
			throw new RuntimeException("nth position is greater than the size of the linkedList");
		}

		Node<T> start = root;
		int nthNodeFromEnd = size - n + 1;
		for (int i = 0; i < nthNodeFromEnd - 1; i++, start = start.getNext())
			;
		return start;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Node<T> detectLoopUsingHashingTechnique() {

		Set<Node<T>> set = new HashSet<>();

		Node<T> start = root;

		while (start != null) {
			set.add(start);
			start = start.getNext();
			if (set.contains(start))
				break;
		}

		return start;
	}

	@Override
	public Node<T> detectLoopUsingFloydCycleFinding() {
		if (root == null) {
			System.out.println("no loop as root is null!");
			return null;
		}

		Node<T> slowRef = root;
		Node<T> fastRef = root;
		do {

			// move fastRef by 2 but safely, if there is no loop fastRef will visit the end
			// of list first.
			if (fastRef.getNext() == null || fastRef.getNext().getNext() == null) {
				System.out.println("there is no loop");
				return null;
			}

			fastRef = fastRef.getNext().getNext();

			slowRef = slowRef.getNext();

		} while (slowRef != fastRef);

		// loop detected, and slowRef and fastRef are at meeting point.

		// move slowRef to start of the list
		slowRef = root;
		while (slowRef != fastRef) {
			slowRef = slowRef.getNext();
			fastRef = fastRef.getNext();
		}

		return slowRef;
	}

	@Override
	public int findLenghtOfTheLoopUsingFloydCycleFinding() {

		// first detect the loop, as given in detectLoopUsingFloydCycleFinding() method,
		// but this time using different approach
		if (root == null) {
			System.out.println("no loop as root is null!");
			return Integer.MIN_VALUE;
		}

		Node<T> slowRef = root;
		Node<T> fastRef = root;

		boolean hasLoop = false;
		// loop detection
		for (; fastRef != null && fastRef.getNext() != null;) {
			slowRef = slowRef.getNext();
			fastRef = fastRef.getNext().getNext();
			if (slowRef == fastRef) {
				hasLoop = true;
				break;
			}
		}

		// Loop length= M+K
		if (hasLoop) {
			int lenthCounter = 1;
			fastRef = fastRef.getNext();
			while (slowRef != fastRef) {
				fastRef = fastRef.getNext();
				lenthCounter++;
			}
			return lenthCounter;
		}

		return Integer.MIN_VALUE;
	}

	@Override
	public boolean insertInSortedLinkedList(T data) {
		Node<T> newNode = new Node<>(data);
		// if the list is null or element to be inserted is less than root element
		if (root == null || root.getData().compareTo(data) >= 0) {
			insert(newNode);
			return true;
		}
		Node<T> ref = root;
		Node<T> previousRef = null;
		for (; ref != null && ref.getData().compareTo(data) <= 0; previousRef = ref, ref = ref.getNext())
			;
		newNode.setNext(ref);
		previousRef.setNext(newNode);

		return true;

	}

	@Override
	public void reverseLinkList() {
		if (root == null || root.getNext() == null)
			return;

		Node<T> reversedList = null;
		Node<T> originalList = root;

		while (originalList != null) {
			Node<T> temp = originalList;
			originalList = originalList.getNext();

			// kind of insert before: temp is getting inserted before the reversedList
			temp.setNext(reversedList);

			reversedList = temp;

		}
		root = reversedList;
	}

	@Override
	public void reverseLinkListRecursively() {
		root = reverseLinkedListRecursively(root);
	}

	// Original Input List: 1 2 3 4 5 6
	//
	// reverse(1-->2-->3-->4-->5-->6)
	// | nextNode = 2-->3-->4-->5-->6
	// | element = 1-->NULL
	// |
	// | reverse(2-->3-->4-->5-->6)
	// | | nextNode = 3-->4-->5-->6
	// | | element = 2-->NULL
	// | |
	// | | reverse(3-->4-->5-->6)
	// | | | nextNode = 4-->5-->6
	// | | | element = 3-->NULL
	// | | |
	// | | | reverse(4-->5-->6)
	// | | | | nextNode = 5-->6
	// | | | | element = 4-->NULL
	// | | | |
	// | | | | reverse(5-->6)
	// | | | | | nextNode = 6-->NULL
	// | | | | | element = 5 --> NULL
	// | | | | |
	// | | | | | reverse(6)
	// | | | | | | return 6
	// | | | | | |
	// | | | | | remainingList = 6
	// | | | | | nextNode = 6 --> 5
	// | | | | |
	// | | | | remainingList = 6 --> 5
	// | | | | nextNode = 6 --> 5 --> 4
	// | | | |
	// | | | remainingList = 6 --> 5
	// | | | nextNode = 6 --> 5 --> 4 --> 3
	// | | |
	// | | remainingList = 6 --> 5
	// | | nextNode = 6 --> 5 --> 4 --> 3 --> 2
	// | |
	// | remainingList = 6 --> 5
	// | nextNode = 6 --> 5 --> 4 --> 3 --> 2 --> 1
	// _________________________________________________________________________
	//
	// → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → → →
	// → → → → → → → → →
	// ↓
	// Node<T> nextNode = current.getNextNode(); ↓
	// current.setNextNode(null); ↓
	// _____ _____ _____ _____ _____ _____ ↓
	// | | | | | | | | | | | | ↓
	// | 1 | ---> | 2 | ---> | 3 | ---> | 4 | ---> | 5 | ---> | 6 | ---> NULL ↓
	// |_____| |_____| |_____| |_____| |_____| |_____| ↓
	// | /| /| /| /| /| ↓
	// | / | / | / | / | / | ↓
	// | / | / | / | / | / | ↓
	// | / | / | / | / | / | ↓
	// cur, next cur, next cur, next cur, next cur, next cur ↓
	// ↓
	// nextNode.setNextNode(current); ↓
	// ↓
	// ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ← ←
	// ← ← ← ← ← ← ← ← ← ←

	// reversedList reference keeps the reference of last node at u-turn

	/*
	 * Hypothesize STEP: reverseLinkedListRecursively([a,b,c,d,e]) --> [e,d,c,b,a]
	 * Node<T> Node<T> reverseLinkedListRecursively(Node<T> firstNode)
	 * 
	 * 
	 * Node<T> firstNode; -- a is first node
	 * 
	 * Node<T> secondNode = firstNode.getNext() -- b is second node
	 * 
	 * BASE CONDITION STEP : smallest state of the list, where reverse of element is
	 * same.
	 * 
	 * 
	 * 
	 * Substitution STEP: OutPutList = reverseLinkedListRecursively([b,c,d,e]) -->
	 * [e,d,c,b]
	 *
	 * Induction STEP :
	 * 
	 * if we insert 'a' at end of output_list means we are done.. Two approaches:
	 * 
	 * 1.iterate the outputlist and append 'a' at end
	 * 
	 * 2nd approach : keep the reference of 'a' and 'b' and then set b.next(a)
	 * 
	 * secondNode.setNext(firstNode)
	 * 
	 * 
	 * 
	 */

	// This recursion is divide and conquer:
	// divide phase will divide each element to single node
	private Node<T> reverseLinkedListRecursively(Node<T> firstNode) {

		// reverse of null is null
		if (firstNode == null)
			return null;

		// reverse of single element is element itself
		// helps u-turn
		if (firstNode.getNext() == null)
			return firstNode;

		// divide the list into two parts, first_node and rest_of_the_list
		Node<T> restOfTheList = firstNode.getNext();
		firstNode.setNext(null);

		// call reverse for the rest_of_the_list and keep the reference of reversed of
		// rest_of_the_list
		Node<T> reversedList = reverseLinkedListRecursively(restOfTheList);

		// conquer part where we are re-composing the solution
		// link rest_of_the_list to the firstNode.
		restOfTheList.setNext(firstNode);

		return reversedList;

	}

	@Override
	public void reverseLinkedListWithAlternateRecurssion() {

		reverseLinkedListWithAlternateRecurssion(root);
	}

	/*
	 * HYPOTHESIS reverseLinkedListWithAlternateRecurssion([A, B, C, D,E])
	 * -->SOLHYPO: E,D,C,B,A
	 * 
	 * BASE: if last element, then assign to root.(root= last_element)
	 * 
	 * reverseLinkedListWithAlternateRecurssion([B, C, D,E]) -->SOLSUB: E,D,C,B
	 * reverseLinkedListWithAlternateRecurssion(current.getNext())
	 * 
	 * Induction: diff: if we append A to B then,
	 * 
	 * SOLHYPO: E,D,C,B,A and >SOLSUB: E,D,C,B will become same i.e we got the
	 * solution
	 * 
	 * Get the next i.e 'B' from 'current' i.e. 'A' then set B.next(A)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */

	//// pass reference to A,B,C,D,E
	private void reverseLinkedListWithAlternateRecurssion(Node<T> current) {

		if (current == null)
			return;

		if (current.getNext() == null) {
			root = current;
			return;
		}
		// pass reference to B,C,D,E
		reverseLinkedListWithAlternateRecurssion(current.getNext());

		// current represents A, so curent.getNext() represents B
		// and our goal is to set, B.next as A
		Node<T> next = current.getNext();
		current.setNext(null);
		next.setNext(current);

	}

	@Override
	public void findIntersectionNodeOfTwoListUsingBruteForce(Node<T> startB) {
		Node<T> startA = root;
		intersection: while (startA != null) {
			Node<T> temp = startB;

			while (temp != null) {
				if (temp == startA) {
					System.out.println("intersection point found at:" + startA);
					break intersection;
				}
				temp = temp.getNext();
			}

			startA = startA.getNext();
		}

	}

	@Override
	public void findIntersectionNodeOfTowListUsingStack(Node<T> startB) {
		Deque<Node<T>> stackA = new java.util.LinkedList<>();
		Deque<Node<T>> stackB = new java.util.LinkedList<>();

		for (Node<T> nodeA = root; nodeA != null; nodeA = nodeA.getNext())
			stackA.push(nodeA);
		for (Node<T> nodeB = startB; nodeB != null; nodeB = nodeB.getNext())
			stackB.push(nodeB);

		Node<T> intersectionNode = null;

		while (stackA.peek() == stackB.peek() && !stackA.isEmpty() && !stackB.isEmpty()) {
			intersectionNode = stackA.pop();
			stackB.pop();
		}

		System.out.println("intersection Node:" + intersectionNode);

	}

	@Override
	public void findIntersectionNodeUsingSearchTechnique() {
		// TODO Auto-generated method stub

	}

	@Override
	public void findIntersectionNodeUsingDistanceDiffTechnique(List<T> listB) {
		List<T> longerList = this;
		List<T> shorterList = listB;
		if (this.size < listB.size()) {
			longerList = listB;
			shorterList = this;
		}

		final int lengthDiff = Math.abs(this.size - listB.size());

		// iterate the bigggerList till diff.
		Node<T> longerListNode = longerList.getRootNode();
		for (int i = 0; i < lengthDiff; i++) {
			longerListNode = longerListNode.getNext();
		}

		Node<T> shorterListNode = shorterList.getRootNode();
		while (longerListNode != shorterListNode) {
			shorterListNode = shorterListNode.getNext();
			longerListNode = longerListNode.getNext();
		}
		System.out.println("Intersection Node:" + shorterListNode);

	}

	@Override
	public Node<T> getRootNode() {
		return root;
	}

	@Override
	public Node<T> middleElementOfLinkedList() {

		if (root == null) {
			return null;
		}

		Node<T> slowRef = root;
		Node<T> fastRef = root;

		// fastRef.getNext().getNext() != null : takes care of even.
		while (fastRef.getNext() != null && fastRef.getNext().getNext() != null) {

			fastRef = fastRef.getNext().getNext();
			slowRef = slowRef.getNext();

		}
		return slowRef;
	}

	@Override
	public void printListFromEndUsingRecursion() {
		Node<T> temp = root;
		printListFromEnd(temp);
	}

	private void printListFromEnd(Node<T> node) {
		if (node == null)
			return;
		printListFromEnd(node.getNext());
		System.out.print(node);
		System.out.print("-->");
	}

	@Override
	public void isLinkedListEven() {

		Node<T> fast = root;
		while (fast != null && fast.getNext() != null) {
			fast = fast.getNext().getNext();
		}

		// even=> num%2==0
		if (fast == null) {
			System.out.println("EVEN");
		} else {
			// odd=> num%2==1
			System.out.println("ODD");
		}

	}

	@Override
	public List<T> mergeTwoSortedList(List<T> lista, List<T> listb) {
		Node<T> a = lista.getRootNode();
		Node<T> b = listb.getRootNode();

		List<T> mergedList = new LinkedList<>();

		while (a != null && b != null) {

			if (a.getData().compareTo(b.getData()) <= 0) {
				mergedList.insertAtEnd(a.getData());
				a = a.getNext();
			} else if (a.getData().compareTo(b.getData()) > 0) {
				mergedList.insertAtEnd(b.getData());
				b = b.getNext();
			}

		}

		while (a != null) {
			mergedList.insertAtEnd(new Node<T>(a.getData()));
			a = a.getNext();
		}

		while (b != null) {
			mergedList.insertAtEnd(new Node<T>(b.getData()));
			b = b.getNext();
		}

		return mergedList;

	}

	@Override
	public void mergeTwoSortedListRecursively(List<T> a, List<T> b) {
		Node<T> merged = recurseAndMergeTwoSortedList1(a.getRootNode(), b.getRootNode());

		while (merged != null) {
			System.out.print(merged);
			System.out.print("-->");
			merged = merged.getNext();

		}
	}

	/*
	 * INPUT A=2,5,8 B=1,3,9,11
	 * 
	 *
	 * Hypothesis: mergeRec(A,B) = 1,2,3,5,8,9,11
	 * 
	 *
	 *
	 * SUBSTITUTION STEP: Call with one less Element
	 * 
	 * MergedNode = null;
	 * 
	 * recursionOutput = null;
	 * 
	 * if(A1<=B1) {
	 * 
	 * MergeCandidate = A1;
	 * 
	 * recursionOutput = mergeRec(A-1,B) = 2,3,5,8,9,11
	 * 
	 * }else{
	 * 
	 * MergeCandidate = B1;
	 *
	 * recursionOutput = mergeRec(A,B-1) = 2,3,5,8,9,11
	 * 
	 * }
	 * 
	 * INDUCTION STEP: MergeCandidate.Next(recursionOutput);
	 * 
	 * MergeCandidate;
	 * 
	 * 
	 */
	private Node<T> recurseAndMergeTwoSortedList1(Node<T> a, Node<T> b) {

		Node<T> mergeCandidate = null;
		Node<T> recursionOutput = null;

		// return remaining b
		if (a == null)
			return b;

		// return remaining a
		if (b == null)
			return a;

		if (a.getData().compareTo(b.getData()) <= 0) {
			mergeCandidate = new Node<>(a.getData());
			recursionOutput = recurseAndMergeTwoSortedList1(a.getNext(), b);
		} else {
			mergeCandidate = new Node<>(b.getData());
			recursionOutput = recurseAndMergeTwoSortedList1(a, b.getNext());

		}
		mergeCandidate.setNext(recursionOutput);
		return mergeCandidate;
	}

	private Node<T> recurseAndMergeTwoSortedList2(Node<T> a, Node<T> b) {

		Node<T> mergeCandidate = null;

		// return remaining b
		if (a == null)
			return b;

		// return remaining a
		if (b == null)
			return a;

		if (a.getData().compareTo(b.getData()) <= 0) {
			mergeCandidate = new Node<>(a.getData());
			mergeCandidate.setNext(recurseAndMergeTwoSortedList2(a.getNext(), b));

		} else {
			mergeCandidate = new Node<>(b.getData());
			mergeCandidate.setNext(recurseAndMergeTwoSortedList2(a, b.getNext()));

		}

		return mergeCandidate;
	}

	@Override
	public void reverseListInPairRecursively() {
		root = reverseListInPairRecursively(root);

	}

	/*
	 * HYPOTHESIS: reverseListInPairRecursively(1 → 2 → 3 → 4 → X) =
	 * root-->2-1-4-3-X;
	 * 
	 * 
	 * SUBSTITUTION: pair =1->2;
	 * 
	 * revPair=reverse the current pair //2->1
	 * 
	 * sol = reverseListInPairRecursively(3 → 4 → X) = 4-3-X;
	 * 
	 * 
	 * Induction: revPair.next.setNext(sol)
	 * 
	 * return revPair;
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private Node<T> reverseListInPairRecursively(Node<T> currentNode) {
		// base case
		if (currentNode == null) {
			return null;
		}

		// base case
		// reverse of single node is the node itself
		if (currentNode.getNext() == null) {

			return currentNode;
		}

		Node<T> nextPair = currentNode.getNext().getNext();

		Node<T> revPair = currentNode.getNext();
		revPair.setNext(currentNode);
		// currentNode.setNext(null);

		Node<T> sol = reverseListInPairRecursively(nextPair);

		revPair.getNext().setNext(sol);

		return revPair;

	}

	// optimized one
	private Node<T> reverseListInPairRecursively1(Node<T> currentNode) {

		if (currentNode == null)
			return null;

		if (currentNode.getNext() == null)
			return currentNode;

		Node<T> nextPair = currentNode.getNext().getNext();
		Node<T> revPair = currentNode.getNext();
		revPair.setNext(currentNode);

		currentNode.setNext(reverseListInPairRecursively(nextPair));

		return revPair;

	}

	// 1--2--3---4---5
	// 2--1--4---3--5
	@Override
	public void reverseListInPairIteratively() {

		Node<T> currentNode = root;
		root = root.getNext();
		Node<T> next2next = null;

		while (currentNode != null && currentNode.getNext() != null) {

			Node<T> next = currentNode.getNext();
			next2next = currentNode.getNext().getNext();

			next.setNext(currentNode);

			// linking between pairs e.g (2--1)->(4--3)
			if (next2next == null || next2next.getNext() == null) {
				currentNode.setNext(next2next);
			} else {
				currentNode.setNext(next2next.getNext());
			}

			currentNode = next2next;

		}

	}

	@Override
	public void splitCircularListIntoTwoEqualCircularList() {

		Node<T> refSpeed1X = root;
		Node<T> refSpeed2X = root;
		// find middle element : refSpeed1X will reach to middle element
		while (refSpeed2X.getNext() != root && refSpeed2X.getNext().getNext() != root) {
			refSpeed2X = refSpeed2X.getNext().getNext();
			refSpeed1X = refSpeed1X.getNext();
		}

		// in case of even fast-pointer will not point to end node
		if (refSpeed2X.getNext().getNext() == root) {
			refSpeed2X = refSpeed2X.getNext();
		}

		Node<T> list2StartRef = refSpeed1X.getNext();

		refSpeed1X.setNext(root);
		refSpeed2X.setNext(list2StartRef);

		List<T> list1 = new LinkedList<>();
		list1.insertAtEnd(root);
		System.out.println(list1.traverse());

		List<T> list2 = new LinkedList<>();
		list2.insertAtEnd(list2StartRef);
		System.out.println(list2.traverse());

	}

	@Override
	public void isListPalindrome() {

		Node<T> slowRef = root;
		Node<T> fastRef = root;

		// fastRef.getNext().getNext() != null : takes care of even.
		while (fastRef.getNext() != null && fastRef.getNext().getNext() != null) {
			fastRef = fastRef.getNext().getNext();
			slowRef = slowRef.getNext();

		}

		// this is just to figure out if the list is even or odd
		if (fastRef.getNext() == null) {
			System.out.println("linked list is odd!!!");
		} else if (fastRef.getNext().getNext() == null) {
			System.out.println("linked list is even!!!");
		}

		Node<T> reversed2ndHalf = reverseLinkedListRecursively(slowRef.getNext());
		Node<T> firstHalf = root;
		boolean isPalindrome = true;
		while (reversed2ndHalf != null) {
			if (firstHalf.getData().compareTo(reversed2ndHalf.getData()) != 0) {
				isPalindrome = false;
				break;
			}
			firstHalf = firstHalf.getNext();
			reversed2ndHalf = reversed2ndHalf.getNext();
		}
		System.out.println("palindrome:" + isPalindrome);
	}

	@Override
	public void reverseInBlockOfKNodes(int k) {
		
		if (k == 0 || k == 1 || k > size) {
			return;
		}
		
		int blockNodeCounter = 1;
		int blockCounter = 1;
		Node<T> reversedHead = null;
		Node<T> head = root;

		Node<T> currentBlockStart = head;
		Node<T> previousBlockStart = null;

		final int TOTAL_NUMBER_OF_BLOCKS = size / k;
		while (head != null && blockCounter <= TOTAL_NUMBER_OF_BLOCKS) {

			Node<T> temp = head;
			head = head.getNext();

			// insert before
			temp.setNext(reversedHead);
			reversedHead = temp;

			if (blockNodeCounter == k) {

				// when it's the first block reversal
				if (previousBlockStart == null) {
					root = reversedHead;
				} else {
					previousBlockStart.setNext(reversedHead);
				}

				// re-initialize
				previousBlockStart = currentBlockStart;
				currentBlockStart = head;
				reversedHead = null;
				blockNodeCounter = 1;

				blockCounter++;

			} else {
				blockNodeCounter++;
			}

		}

		// means last block is smaller than block size
		if (size % k != 0) {
			previousBlockStart.setNext(currentBlockStart);
		}

	}

	/**
	 * For eliminationPosition = 1, has not been taken care.
	 */
	@Override
	public void getJosephusPoint(int eliminationPosition) {
		if (eliminationPosition <= 1) {
			return;
		}
		Node<T> currentNode = root;
		while (currentNode.getNext() != currentNode) {
			for (int i = 1; i < eliminationPosition - 1; i++)
				currentNode = currentNode.getNext();

			System.out.println("dropped node:" + currentNode.getNext());
			currentNode.setNext(currentNode.getNext().getNext());
			currentNode = currentNode.getNext();
		}

		System.out.println("current Node:" + currentNode.getData());
	}

	
	
	
	/**
	 * 
	 * 
	 * */
	@Override
	public void getJosephusPointUsingRecursion(int eliminationPosition) {
		

	}
	
	
	
	
	@Override
	public void cloneListWithRandomPointer() {

		Map<Node<T>, Node<T>> keyOriginalValueCloned = new HashMap<>();
		Node<T> node = root;

		// create the new cloned list with next pointer and prepare the map
		Node<T> previousClonedNode = null;
		while (node != null) {
			Node<T> clonedNode = new Node<T>(node.getData());
			if (previousClonedNode != null) {
				previousClonedNode.setNext(clonedNode);
			}
			keyOriginalValueCloned.put(node, clonedNode);

			previousClonedNode = clonedNode;
			node = node.getNext();

		}

		keyOriginalValueCloned.forEach((nodde, clonedNode) -> {
			clonedNode.setRandomNext(keyOriginalValueCloned.get(nodde.getRandomNext()));
		});

		Node<T> printCloned = keyOriginalValueCloned.get(root);
		while (printCloned != null) {
			System.out.println("cloned-->next: " + printCloned + "cloned-->random: " + printCloned.getRandomNext());
			printCloned = printCloned.getNext();
		}
	}

	@Override
	public void deleteGivenNodeUsingIteration(Node<T> deleteNode) {

		Node<T> node = root;

		// deletNode == root node
		if (deleteNode == node) {
			root = node.getNext();
		}

		while (node != null) {
			if (deleteNode == node.getNext()) {
				node.setNext(node.getNext().getNext());
			}

			node = node.getNext();
		}

	}

	@Override
	public void deleteGivenNodeWithoutUsingIteration(Node<T> deleteNodePointer) {

		if (deleteNodePointer.getNext() != null) {
			deleteNodePointer.setData(deleteNodePointer.getNext().getData());
			deleteNodePointer.setNext(deleteNodePointer.getNext().getNext());
		} else {
			System.out.println("node cannot be deleted as the next node is null");
		}

	}

	@Override
	public Node<T> findLastModularNode(int modularConstant_K) {

		Node<T> node = root;
		Node<T> modularNode = null;
		int n = 1;
		while (node != null) {

			if (n % modularConstant_K == 0)
				modularNode = node;

			n++;
			node = node.getNext();
		}

		return modularNode;
	}

	@Override
	public Node<T> findCeiledFractionalNodeUsingPointerIncrement(int k) {

		if (k == 0)
			return null;

		Node<T> divisionBlockNode = root;
		int i = 1;

		for (Node<T> node = root; node.getNext() != null; node = node.getNext()) {

			if (i == k) {

				divisionBlockNode = divisionBlockNode.getNext();

				i = 1;

				continue;
			}

			i++;
		}

		return divisionBlockNode;
	}

	@Override
	public Node<T> findCeiledFractionalNodeUsingModuloDivison(int k) {

		if (k == 0)
			return null;

		Node<T> divisionBlockNode = root;
		int i = 1;

		for (Node<T> node = root; node.getNext() != null; node = node.getNext()) {

			if (i % k == 0)
				divisionBlockNode = divisionBlockNode.getNext();

			i++;
		}

		return divisionBlockNode;
	}

	@Override
	public Node<T> findSquareRootNode() {
		Node<T> squareRootNode = null;
		Node<T> node = root;

		for (int length = 1, squareRoot = 1; node != null; node = node.getNext()) {

			if (squareRoot * squareRoot == length) {
				squareRootNode = squareRootNode == null ? root : squareRootNode.getNext();
				squareRoot++;
			}
			length++;
		}
		return squareRootNode;
	}

	@Override
	public List<Integer> summationForEqualSizedList(List<Integer> digitList1, List<Integer> digitList2) {

		Node<Integer> node1 = digitList1.getRootNode();
		Node<Integer> node2 = digitList2.getRootNode();

		List<Integer> resultList = new LinkedList<>();

		int carry = summationForEqualSizedList(node1, node2, resultList);
		if (carry != 0) {
			resultList.insert(carry);
		}

		return resultList;
	}

	private int summationForEqualSizedList(Node<Integer> node1, Node<Integer> node2, List<Integer> resultList) {

		if (node1 == null && node2 == null)
			return 0;

		// recursive call
		int carry = summationForEqualSizedList(node1.getNext(), node2.getNext(), resultList);

		int sum = node1.getData().intValue() + node2.getData().intValue() + carry;

		System.out.println();
		System.out.print(String.format("%d +  %d + %d = %d", node1.getData().intValue(), node2.getData().intValue(),
				carry, sum));

		int resultDigit = 0;
		if (sum > 9) {
			resultDigit = sum - 10;
			carry = 1;
		} else {
			resultDigit = sum;
			carry = 0;

		}
		System.out.print(String.format("::> resultDigit:%d, carry:%d", resultDigit, carry));
		System.out.println();

		resultList.insert(resultDigit);
		return carry;

	}

	@Override
	public List<Integer> summationForUnEqualSizedList(List<Integer> digitList1, List<Integer> digitList2) {

		List<Integer> shortList = digitList1.size() < digitList2.size() ? digitList1 : digitList2;
		final int diff = Math.abs(digitList1.size() - digitList2.size());

		for (int i = 0; i < diff; i++)
			shortList.insert(0);

		return summationForEqualSizedList(digitList1, digitList2);

	}

	@Override
	public List<T> listReorder() {
		Node<T> middleElement = middleElementOfLinkedList();
		Node<T> secondHalf = middleElement.getNext();
		middleElement.setNext(null);
		Node<T> reveresedNode = reverseLinkedListRecursively(secondHalf);

		List<T> resultList = new LinkedList<>();

		for (Node<T> node = root; node != null; node = node.getNext()) {

			resultList.insertAtEnd(new Node<T>(node.getData()));
			if (reveresedNode != null) {
				resultList.insertAtEnd(new Node<T>(reveresedNode.getData()));
				reveresedNode = reveresedNode.getNext();
			}

		}

		return resultList;
	}

	@Override
	public List<T> getCommonElementList(List<T> list1, List<T> list2) {

		Node<T> node1 = list1.getRootNode();
		Node<T> node2 = list2.getRootNode();
		List<T> resultList = new LinkedList<>();

		while (node1 != null && node2 != null) {
			if (node1.getData().compareTo(node2.getData()) == 0) {
				resultList.insertAtEnd(node1.getData());
				node1 = node1.getNext();
				node2 = node2.getNext();

			} else if (node1.getData().compareTo(node2.getData()) < 0) {
				node1 = node1.getNext();
			} else {
				node2 = node2.getNext();
			}
		}
		return resultList;
	}

}
