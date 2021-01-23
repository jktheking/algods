package edu.algods.linkedlist;

import java.util.HashSet;
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

		final int loopLength = l / 2;

		final boolean loopOdd = l % 2 == 0 ? false : true;

		final int matrixL = 2 * (m + loopLength) - 1;
		final int matrixW = loopOdd ? 5 : 3;

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
		for(int i=1; i< matrixW; i++) {
			if (i % 2 == 0) {
				matrix[i][lastColIndex] = node.toString();
				node = node.getNext();
			} else {
				matrix[i][lastColIndex] = "|";
			}
		}

		//filling the last row of matrix as returning row, but starting at second last col
		final int lastRowIndex = matrixW - 1;
		//number of times for loop to iterate = loopRowSize - 1; as last column is already filled.
		final int loopRowSize = 2*loopLength - 1;
		
		int i = lastColIndex - 1;
		for(int j=loopRowSize-1; j>0; j--) {
			if (i % 2 == 0) {
				matrix[lastRowIndex][i] = node.toString();
				node = node.getNext();
			} else {
			 matrix[lastRowIndex][i] = "<--";
			}
			i--;
		}
		
		//filling the upgoing column from second last row, and stopping the loop at 2nd row
		//here i from previous loop is used as column index, since i has taken one extra decrement, 
		//so need to adjust the i by 1
		final int adjusted_i = i+1;
		for(int j=lastRowIndex-1; j>0; j--) {
			matrix[j][adjusted_i] = "|";
			
		}
		
	
		StringBuilder path = new StringBuilder();
		path.append("root-->").append("\n");
		//preparing the printable path
		for(int a=0; a<matrixW; a++) {
			
			if(a!=0) path.append("\n");
			
			for(int b=0; b<matrixL; b++ ) {
				if(matrix[a][b]==null) {
					//even place contains node data, and odd place contains link "--->"
					if((b&1)==0) {
						path.append(" ");
					}else {
						path.append("   ");
					}
					
				}else {
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
			int lenthCounter = 0;
			fastRef = fastRef.getNext();
			while (slowRef != fastRef) {
				fastRef = fastRef.getNext();
				lenthCounter++;
			}
			return lenthCounter;
		}

		return Integer.MIN_VALUE;
	}

}
