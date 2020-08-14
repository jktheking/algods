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
		Node<T> circularNode = detectLoopUsingHashingTechnique();
		if(circularNode != null) {
			return loopTraversalForCircular(circularNode);
		}
		return loopTraversal();
	}

	private String loopTraversal() {
		StringBuilder links = new StringBuilder("root-->");
		for (Node<T> node = root; node != null; node = node.getNext()) {
			links.append(node);
			if (node.getNext() != null) {
				links.append("-->");
			}
		}
		return links.toString();
	}
	
	
	private String loopTraversalForCircular(Node<T> circularNode) {
		
		int circularCounter = 0;
		
		StringBuilder links = new StringBuilder("root-->");
		for (Node<T> node = root; circularCounter<2; node = node.getNext()) {
			
			links.append(node);
			if (circularCounter<2) {
				links.append("-->");
			}

			if(node == circularNode) {
				circularCounter++;
			}
		}
		return links.toString();
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
	 * */
	@Override
	public Node<T> findNthNodeInSingleScanFromEndWithoutUsingSizeOfList(int n) {
		
		Node<T> tempRef;
		Node<T> solutionRef = root;
		int i;
		
		//move the tempRef by n position(considering positions not index).
		for(tempRef = root, i=0; i<n; i++, tempRef = tempRef.getNext()) {
			if(tempRef==null) {
				throw new RuntimeException("nth position  is greater than the size of the linkedList");
			}
			
		}
		
		while(tempRef!=null) {
			tempRef = tempRef.getNext();
			solutionRef = solutionRef.getNext();
		}

		return solutionRef;
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public Node<T> findNthNodeFromEndUsingSizeOfList(int n) {
		
		if(size < n) {
			throw new RuntimeException("nth position is greater than the size of the linkedList");
		}
		
		Node<T> start = root;
		int nthNodeFromEnd = size - n + 1;
		for(int i = 0; i< nthNodeFromEnd - 1; i++, start = start.getNext());
		return start;
	}

	/**
	 * {@inheritDoc}
	 * */
	@Override
	public Node<T> detectLoopUsingHashingTechnique() {
		
		Set<Node<T>> set = new HashSet<>();
		
		Node<T> start = root;
		
		while(start != null) {
			set.add(start);
			start = start.getNext();
			if(set.contains(start)) break;
		}
		
		return start;
	}



	@Override
	public Node<T> detectLoopUsingFloydCycleFinding() {
		// TODO Auto-generated method stub
		return null;
	}


}
