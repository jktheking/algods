package edu.ds.tree.bst;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

import edu.ds.tree.bst.BinarySearchTree.BSTNode;

public class BinarySearchTree<T extends Comparable<T>> implements BST<T> {

	public static final class BSTNode<T extends Comparable<T>> {
		T data;
		BSTNode<T> left;
		BSTNode<T> right;

		BSTNode(T data) {
			this.data = data;
		}

		public T getData() {
			return data;
		}

		public void setData(T data) {
			this.data = data;
		}

		public BSTNode<T> getLeft() {
			return left;
		}

		public void setLeft(BSTNode<T> left) {
			this.left = left;
		}

		public BSTNode<T> getRight() {
			return right;
		}

		public void setRight(BSTNode<T> right) {
			this.right = right;
		}

		public boolean isLeafNode() {
			return this.left == null && this.right == null;
		}

		public boolean isFullNode() {
			return this.left != null && this.right != null;
		}

		/**
		 * The operators &, ^, and | are bitwise operators when the operands are
		 * primitive integral types. They are logical operators when the operands are
		 * boolean, and their behaviour in the latter case is specified. See the section
		 * 15.22.2 of the Java Language Specification for details.
		 * 
		 * {@link https://docs.oracle.com/javase/specs/jls/se14/html/jls-15.html#jls-15.22.2}
		 * 
		 */
		public boolean isHalfNode() {
			return (this.left != null) ^ (this.right != null);

		}

		@Override
		public String toString() {
			return data.toString();
		}

	}

	private BSTNode<T> root;
	private int size;

	@Override
	public void insert(T data) {
		if (root == null) {
			root = new BSTNode<>(data);
			size++;
			return;
		}
		if (insert(data, root))
			size++;

	}

	private boolean insert(T data, BSTNode<T> node) {

		final int comp = data.compareTo(node.getData());

		if (comp == 0) {
			// duplicate element do nothing;
			return false;
		} else if (comp < 0) {
			if (node.getLeft() == null) {
				node.setLeft(new BSTNode<>(data));
			} else {
				insert(data, node.getLeft());
			}

		} else {
			if (node.getRight() == null) {
				node.setRight(new BSTNode<>(data));
			} else {
				insert(data, node.getRight());
			}
		}
		return true;
	}

	@Override
	public void delete(T data) {

		if (isEmpty())
			return;

		// root = recursiveDelete(root, data);
		root = iterativeDelete(root, data);

	}

	private BSTNode<T> iterativeDelete(BSTNode<T> root, T data) {

		BSTNode<T> parent = null;
		BSTNode<T> node = root;

		boolean deleteRoot = false;
		if (data.compareTo(node.getData()) == 0) {
			deleteRoot = true;
		}

		while (node != null) {

			final int comp = data.compareTo(node.getData());
			if (comp < 0) {
				parent = node;
				node = node.getLeft();

			} else if (comp > 0) {

				parent = node;
				node = node.getRight();

			} else if (comp == 0) {

				// node to be deleted found

				/**
				 * CASE 1: node to be deleted is leaf node
				 */
				if (node.isLeafNode()) {
					size--;

					if (deleteRoot) {
						return null;
					}

					if (parent.getLeft() == node)
						parent.setLeft(null);
					if (parent.getRight() == node)
						parent.setRight(null);
					node = null;

				}
				/**
				 * CASE 2: node to be deleted has only one subtree left or right.
				 */
				else if (node.getLeft() == null) {
					// node to be deleted has right-subtree
					size--;

					if (deleteRoot) {
						return node.getRight();
					}

					if (parent.getLeft() == node)
						parent.setLeft(node.getRight());
					if (parent.getRight() == node)
						parent.setRight(node.getRight());
					node = null;
				} else if (node.getRight() == null) {
					// node to be deleted has left-subtree
					size--;
					if (deleteRoot) {
						return node.getLeft();
					}

					if (parent.getLeft() == node)
						parent.setLeft(node.getLeft());
					if (parent.getRight() == node)
						parent.setRight(node.getLeft());
					node = null;

				} else {
					/**
					 * CASE 3: node to be deleted has both subtree left and right. <br>
					 * Convert the CASE3 into either CASE1 or CASE2, by preserving data of
					 * successor/predecessor node in node_to_be_deleted
					 * 
					 */
					// find predecessor
					BSTNode<T> predecessor = node.getLeft();
					parent = node;
					while (predecessor.getRight() != null) {
						parent = predecessor;
						predecessor = predecessor.getRight();

					}

					// case 3 got converted into case 1 or case 2, so no need to delete the root
					if (deleteRoot) {
						deleteRoot = false;
					}

					node.setData(predecessor.getData());

					predecessor.setData(data);
					node = predecessor;
				}

			}
		}

		return root;
	}

	private BSTNode<T> recursiveDelete(BSTNode<T> node, T data) {

		if (node == null)
			return null;

		int comp = data.compareTo(node.getData());

		if (comp == 0) {
			// item to be deleted found.
			/**
			 * CASE 1: node to be deleted is leaf node
			 */
			if (node.isLeafNode()) {
				size--;
				return null;
			}

			/**
			 * CASE 2: node to be deleted has only one subtree left or right.
			 */

			if (node.getLeft() == null) {
				size--;
				// node to be deleted has right-subtree
				BSTNode<T> rightSubtree = node.getRight();
				node = null;
				return rightSubtree;

			}

			if (node.getRight() == null) {
				size--;
				// node to be deleted has left-subtree
				BSTNode<T> leftSubtree = node.getLeft();
				node = null;
				return leftSubtree;

			}

			/**
			 * CASE 3: node to be deleted has both subtree left and right. <br>
			 * Convert the CASE3 into either CASE1 or CASE2, by preserving data of
			 * successor/predecessor node in node_to_be_deleted
			 * 
			 */

			/*
			 * Node<T> predecessorNode = getPredecessor(node);
			 * 
			 * preserve the value of predecessor in node
			 * 
			 * node.setData(predecessorNode.getData());
			 * 
			 * node.setLeft(delete(node.getLeft(), predecessorNode.getData()));
			 */

			BSTNode<T> successorNode = getSuccessor(node);
			node.setData(successorNode.getData());
			node.setRight(recursiveDelete(node.getRight(), successorNode.getData()));

		} else if (comp < 0) {
			node.setLeft(recursiveDelete(node.getLeft(), data));
		} else if (comp > 0) {
			node.setRight(recursiveDelete(node.getRight(), data));
		}

		// this return is important to maintain the reference of root node post delete
		return node;
	}

	/**
	 * max in left-subtree:
	 * 
	 * predecessor node can have only left-child, because max itself is right-child.
	 */
	private BSTNode<T> getPredecessor(BSTNode<T> node) {
		return max(node.getLeft());

	}

	/**
	 * min in right-subtree:
	 * 
	 * successor node can have only right-child, because min itself is left-child.
	 */
	private BSTNode<T> getSuccessor(BSTNode<T> node) {
		return min(node.getRight());
	}

	/**
	 * max : go all right till leaf node
	 * 
	 */
	@Override
	public T getMaxElement() {
		return isEmpty() ? null : max(root).getData();
	}

	private BSTNode<T> max(BSTNode<T> node) {
		return node.getRight() == null ? node : max(node.getRight());

	}

	/**
	 * min : go all left till leaf node
	 * 
	 */
	@Override
	public T getMinElement() {
		return isEmpty() ? null : min(root).getData();
	}

	private BSTNode<T> min(BSTNode<T> node) {
		return node.getLeft() == null ? node : min(node.getLeft());

	}

	@Override
	public int size() {
		return size;
	}

	/**
	 * If tree is balanced going down to all left will give the actual height.
	 * 
	 * If tree is not balanced then :
	 * 
	 */
	@Override
	public int height() {
		return isEmpty() ? 0 : height(root);

	}

	/**
	 * Height of leaf_node = 0.
	 * 
	 * Height of non_leaf_node = 1 + max(leftTree, rightTree)
	 * 
	 * To make the code concise we have taken height of null as -1.
	 * 
	 */
	private int height(BSTNode<T> node) {

		if (node == null) {
			return -1;
		}
		return 1 + Math.max(height(node.getLeft()), height(node.getRight()));
	}

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public boolean contains(T data) {
		return contains(root, data);
	}

	private boolean contains(BSTNode<T> node, T data) {
		if (node == null)
			return false;

		final int comp = data.compareTo(node.getData());
		if (comp == 0) {
			return true;
		} else if (comp < 0) {
			return contains(node.getLeft(), data);
		} else {
			return contains(node.getRight(), data);
		}

	}

	@Override
	public BSTNode<T> getRoot() {
		return root;
	}

	@Override
	public void setRoot(BSTNode<T> root) {
		this.root = root;
	}

	@Override
	public List<T> preorderTraverse() {
		List<T> accumulator = new ArrayList<>();
		recursivePreorder(accumulator, root);
		return accumulator;
	}

	private void recursivePreorder(List<T> accumulator, BSTNode<T> node) {
		if (node == null)
			return;
		accumulator.add(node.getData());
		recursivePreorder(accumulator, node.getLeft());
		recursivePreorder(accumulator, node.getRight());

	}

	/**
	 * preorder : NLR ; stack push order : RLN
	 * 
	 */
	private void iterativePreorder(List<T> accumulator, BSTNode<T> node) {
		if (node == null)
			return;
		Deque<BSTNode<T>> stack = new ArrayDeque<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			BSTNode<T> snode = stack.pop();
			accumulator.add(snode.getData());
			// need to push right first as stack is LIFO
			if (snode.getRight() != null)
				stack.push(snode.getRight());
			if (snode.getLeft() != null)
				stack.push(snode.getLeft());
		}

	}

	@Override
	public List<T> inorderTraverse() {
		List<T> accumulator = new ArrayList<>();
		// recursiveInorder(accumulator, root);
		iterativeInorderAllLeft(accumulator, root);
		return accumulator;
	}

	private void recursiveInorder(List<T> accumulator, BSTNode<T> node) {
		if (node == null)
			return;
		recursiveInorder(accumulator, node.getLeft());
		accumulator.add(node.getData());
		recursiveInorder(accumulator, node.getRight());

	}

	/**
	 * INORDER : L-N-R
	 * 
	 * <pre>
	 * High level algo :
	 * STEP1: Go all left
	 * STEP2: print the leftmost node(L)
	 * STEP3: Backtrack to parent(N) node and print the parent
	 * 
	 * STEP4: repeat step1 to step3 for right(R) child of parent.
	 * 
	 * Low level algo:
	 * We need to push the nodes on stack while going all left because we are backtracking to 
	 * parent in next step.
	 * 
	 * 
	 * </pre>
	 * 
	 * 
	 */
	private void iterativeInorderAllLeft(List<T> accumulator, BSTNode<T> node) {
		Deque<BSTNode<T>> stack = new ArrayDeque<>();

		while (node != null || !stack.isEmpty()) {

			// STEP1 : Go all left and add to stack
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}

			// STEP2 pop and print the leftmost node.
			// STEP3 backtrack to parent node and print it. Runtime call will backtrack to
			// parent node in next iteration after processing the leftmost node as leftmost
			// node is leaf(node.getRight() return null)
			node = stack.pop();
			accumulator.add(node.getData());

			// STEP4 : repeat step1 and step2 for right-subtree.
			node = node.getRight();

		}

	}

	@Override
	public List<T> postorderTraverse() {
		List<T> accumulator = new ArrayList<>();
		// recursivePostOrder(accumulator, root);
		// iterativePostOrderAllLeft(accumulator, root);
		iterativePostOrderNRL(accumulator, root);
		return accumulator;
	}

	private void recursivePostOrder(List<T> accumulator, BSTNode<T> node) {
		if (node == null)
			return;
		recursivePostOrder(accumulator, node.getLeft());
		recursivePostOrder(accumulator, node.getRight());
		accumulator.add(node.getData());

	}

	/**
	 * POSTORDER : L-R-N
	 * 
	 * <pre>
	 * High level algo :
	 * STEP1: Go all left
	 * STEP2: print the leftmost node(L)
	 * STEP3: Backtrack to parent using stack.peek()
	 *  STEP3A: IF there is no right-child or right-child is already visited 
	 *          then pop and print the node.
	 *  STEP3B: ELSE parent has un-visited right-child  
	 *          then repeat STEP1 to STEP3 for right(R) child of parent.
	 * 
	 * 
	 * Low level algo:
	 * We need to push the nodes on stack while going all left because we are backtracking to 
	 * parent in next step.
	 * 
	 * 
	 * </pre>
	 * 
	 * 
	 */
	private void iterativePostOrderAllLeft(List<T> accumulator, BSTNode<T> node) {
		Deque<BSTNode<T>> stack = new ArrayDeque<>();
		BSTNode<T> previous = null;
		while (node != null || !stack.isEmpty()) {
			// STEP1 : Go all left and add to stack
			while (node != null) {
				stack.push(node);
				node = node.getLeft();
			}
			node = stack.peek();
			// either there is no right-child or right-child is already visited
			if (node.getRight() == null || node.getRight() == previous) {
				accumulator.add(node.getData());
				previous = node;
				// set node=null to avoid re-push to stack.
				node = null;
				// discard the stack-top
				stack.pop();
			} else {
				node = node.getRight();
			}
		}
	}

	/**
	 * Post-order traversal is LRN so stack insertion order is NRL
	 * 
	 * <pre>
	 * STEP 1:  push root node on stack.
	 * STEP 2:  backtrack using stack.peek()
	 *  STEP2A: IF stack.peek() isleafNode() or isVisitedParent()
	 *  	then print and pop the stack
	 *  STEP2B: ELSE
	 *  	push right and left on stack.
	 * 
	 * </pre>
	 * 
	 */
	private void iterativePostOrderNRL(List<T> accumulator, BSTNode<T> node) {
		Deque<BSTNode<T>> stack = new ArrayDeque<>();
		// push root node.
		stack.push(node);

		BSTNode<T> previous = null;

		while (!stack.isEmpty()) {

			node = stack.peek();

			if (node.isLeafNode() || isVisitedParent(node, previous)) {
				accumulator.add(node.getData());
				previous = node;
				// discard the stack-top
				stack.pop();
			} else {
				if (node.getRight() != null)
					stack.push(node.getRight());

				if (node.getLeft() != null)
					stack.push(node.getLeft());
			}
		}
	}

	private boolean isVisitedParent(BSTNode<T> node, BSTNode<T> previous) {
		return previous != null && (node.getLeft() == previous || node.getRight() == previous);
	}

	@Override
	public List<T> bfsTraversal() {
		List<T> list = new ArrayList<>();
		Queue<BSTNode<T>> queue = new ArrayDeque<>();

		if (root != null) {
			queue.offer(root);
		}

		while (!queue.isEmpty()) {
			BSTNode<T> node = queue.poll();

			list.add(node.getData());

			if (node.getLeft() != null)
				queue.offer(node.getLeft());
			if (node.getRight() != null)
				queue.offer(node.getRight());
		}

		return list;
	}

	@Override
	public int size(T data) {

		BSTNode<T> node = searchNode(root, data);
		/*
		 * // by counting the size of accumulator in inorder traversal List<T>
		 * accumulator = new ArrayList<>(); recursiveInorder(accumulator, node); return
		 * accumulator.size();
		 */

		return size(node);

	}

	/**
	 * 
	 * Calculate the size of left and right subtrees recursively, add 1 (current
	 * node) and return to its parent.
	 * 
	 * size_of_left_subtree + 1 + size_of_right_subtree
	 * 
	 * 
	 */
	private int size(BSTNode<T> node) {
		if (node == null)
			return 0;

		return size(node.getLeft()) + 1 + size(node.getRight());

	}

	private BSTNode<T> searchNode(BSTNode<T> node, T data) {
		if (node == null)
			return null;

		final int comp = data.compareTo(node.getData());
		if (comp == 0) {
			return node;
		} else if (comp < 0) {
			return searchNode(node.getLeft(), data);
		} else {
			return searchNode(node.getRight(), data);
		}
	}

	@Override
	public String toString() {
		System.out.println();
		print(root);
		return "";
	}

	/**
	 * Print a tree
	 *
	 * @param root tree root node
	 */
	public void print(BSTNode<T> root) {
		List<List<String>> lines = new ArrayList<List<String>>();

		List<BSTNode<T>> level = new ArrayList<>();
		List<BSTNode<T>> next = new ArrayList<>();

		level.add(root);
		int nn = 1;

		int widest = 0;

		while (nn != 0) {
			List<String> line = new ArrayList<String>();

			nn = 0;

			for (BSTNode<T> n : level) {
				if (n == null) {
					line.add(null);
					next.add(null);
					next.add(null);
				} else {
					String aa = n.data.toString();
					line.add(aa);
					if (aa.length() > widest)
						widest = aa.length();

					next.add(n.left);
					next.add(n.right);

					if (n.left != null)
						nn++;
					if (n.right != null)
						nn++;
				}
			}

			if (widest % 2 == 1)
				widest++;

			lines.add(line);

			List<BSTNode<T>> tmp = level;
			level = next;
			next = tmp;
			next.clear();
		}

		int perpiece = lines.get(lines.size() - 1).size() * (widest);
		for (int i = 0; i < lines.size(); i++) {
			List<String> line = lines.get(i);
			int hpw = (int) Math.floor(perpiece / 2f) - 1;

			if (i > 0) {
				for (int j = 0; j < line.size(); j++) {

					// split node
					char c = ' ';
					if (j % 2 == 1) {
						if (line.get(j - 1) != null) {
							c = (line.get(j) != null) ? '┴' : '┘';
						} else {
							if (j < line.size() && line.get(j) != null)
								c = '└';
						}
					}
					System.out.print(c);

					// lines and spaces
					if (line.get(j) == null) {
						for (int k = 0; k < perpiece - 1; k++) {
							System.out.print(" ");
						}
					} else {

						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? " " : "─");
						}
						System.out.print(j % 2 == 0 ? "┌" : "┐");
						for (int k = 0; k < hpw; k++) {
							System.out.print(j % 2 == 0 ? "─" : " ");
						}
					}
				}
				System.out.println();
			}

			// print line of numbers
			for (int j = 0; j < line.size(); j++) {

				String f = line.get(j);
				if (f == null)
					f = "";
				int gap1 = (int) Math.ceil(perpiece / 2f - f.length() / 2f);
				int gap2 = (int) Math.floor(perpiece / 2f - f.length() / 2f);

				// a number
				for (int k = 0; k < gap1; k++) {
					System.out.print(" ");
				}
				System.out.print(f);
				for (int k = 0; k < gap2; k++) {
					System.out.print(" ");
				}
			}
			System.out.println();

			perpiece /= 2;
		}
	}

}
