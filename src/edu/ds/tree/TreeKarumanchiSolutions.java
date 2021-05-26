package edu.ds.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import edu.ds.tree.bst.BST;
import edu.ds.tree.bst.BinarySearchTree.BSTNode;

public class TreeKarumanchiSolutions<T extends Comparable<T>> implements TreeKarumanchiQuestions<T> {

	/**
	 * ALGO : Run BFS
	 */
	@Override
	public List<T> bottomUpLevelOrder(BST<T> bst) {

		if (bst.isEmpty())
			return List.of();

		BSTNode<T> root = bst.getRoot();

		Queue<BSTNode<T>> queue = new ArrayDeque<>();
		queue.offer(root);

		Deque<T> stack = new ArrayDeque<>();

		while (!queue.isEmpty()) {

			BSTNode<T> node = queue.poll();
			stack.push(node.getData());

			if (node.getRight() != null)
				queue.offer((node.getRight()));

			if (node.getLeft() != null)
				queue.offer((node.getLeft()));

		}

		return stack.stream().collect(Collectors.toList());

	}

	/**
	 * Calculating height using level in tree.
	 * 
	 */
	@Override
	public int iterativeHeight(BST<T> bst) {

		if (bst.isEmpty())
			return 0;

		BSTNode<T> root = bst.getRoot();
		Queue<BSTNode<T>> queue = new ArrayDeque<>();
		queue.offer(root);

		List<BSTNode<T>> levelNodesList = new ArrayList<>();

		// already root is added
		int level = 1;
		while (!queue.isEmpty()) {

			while (!queue.isEmpty()) {
				levelNodesList.add(queue.poll());
			}

			levelNodesList.forEach(node -> {
				if (node.getLeft() != null)
					queue.offer((node.getLeft()));

				if (node.getRight() != null)
					queue.offer((node.getRight()));
			});

			if (!queue.isEmpty()) {
				level++;
				levelNodesList.clear();
			}

		}

		return level - 1;
	}

	@Override
	public void printLevelsOfTreeOnSeparateLine(BST<T> bst) {

		if (bst.isEmpty())
			return;

		BSTNode<T> root = bst.getRoot();
		Queue<BSTNode<T>> queue = new ArrayDeque<>();
		queue.offer(root);
		List<BSTNode<T>> levelNodesList = new ArrayList<>();

		// already root is added
		int level = 0;
		while (!queue.isEmpty()) {

			while (!queue.isEmpty()) {
				levelNodesList.add(queue.poll());
			}
			System.out.println("level-" + level + " " + levelNodesList);

			levelNodesList.forEach(node -> {
				if (node.getRight() != null)
					queue.offer((node.getRight()));

				if (node.getLeft() != null)
					queue.offer((node.getLeft()));
			});

			if (!queue.isEmpty()) {
				level++;
				levelNodesList.clear();
			}

		}
	}

	@Override
	public BSTNode<T> getDeepestNode(BST<T> bst) {

		if (bst.isEmpty())
			return null;

		BSTNode<T> root = bst.getRoot();
		Queue<BSTNode<T>> queue = new ArrayDeque<>();
		queue.offer(root);

		BSTNode<T> deepestNode = null;

		while (!queue.isEmpty()) {

			deepestNode = queue.poll();

			if (deepestNode.getLeft() != null)
				queue.offer((deepestNode.getLeft()));

			if (deepestNode.getRight() != null)
				queue.offer((deepestNode.getRight()));

		}

		return deepestNode;

	}

	@Override
	public List<T> getLeafNodesUisingRecursion(BST<T> bst) {
		List<T> accumulator = new ArrayList<>();
		getLeafNodesUisingRecursion(bst.getRoot(), accumulator);
		return accumulator;
	}

	private void getLeafNodesUisingRecursion(BSTNode<T> node, List<T> accumulator) {
		if (node == null)
			return;
		if (node.isLeafNode()) {
			accumulator.add(node.getData());
		}
		getLeafNodesUisingRecursion(node.getLeft(), accumulator);
		getLeafNodesUisingRecursion(node.getRight(), accumulator);

	}

	/**
	 * We will use preorder traversal to find-out all the leaves
	 * 
	 * preorder traversal order : N LR; stack push order : RL
	 * 
	 */
	@Override
	public List<T> getLeafNodesUisingIteration(BST<T> bst) {

		if (bst.isEmpty())
			return List.of();

		List<T> accumulator = new ArrayList<>();
		Deque<BSTNode<T>> stack = new ArrayDeque<>();

		stack.push(bst.getRoot());

		while (!stack.isEmpty()) {
			BSTNode<T> node = stack.pop();
			if (node.isLeafNode())
				accumulator.add(node.getData());

			// need to push right first as stack is LIFO
			if (node.getRight() != null)
				stack.push(node.getRight());
			if (node.getLeft() != null)
				stack.push(node.getLeft());
		}

		return accumulator;
	}

	/**
	 * 
	 * We will use preorder traversal
	 */
	@Override
	public List<T> getFullNodesUsingIteration(BST<T> bst) {

		if (bst.isEmpty())
			return List.of();

		List<T> accumulator = new ArrayList<>();
		Deque<BSTNode<T>> stack = new ArrayDeque<>();

		stack.push(bst.getRoot());

		while (!stack.isEmpty()) {
			BSTNode<T> node = stack.pop();
			if (node.isFullNode())
				accumulator.add(node.getData());

			// need to push right first as stack is LIFO
			if (node.getRight() != null)
				stack.push(node.getRight());
			if (node.getLeft() != null)
				stack.push(node.getLeft());
		}

		return accumulator;
	}

	@Override
	public List<T> getHalfNodesUsingIteration(BST<T> bst) {

		if (bst.isEmpty())
			return List.of();

		List<T> accumulator = new ArrayList<>();
		Deque<BSTNode<T>> stack = new ArrayDeque<>();

		stack.push(bst.getRoot());

		while (!stack.isEmpty()) {
			BSTNode<T> node = stack.pop();
			if (node.isHalfNode())
				accumulator.add(node.getData());

			// need to push right first as stack is LIFO
			if (node.getRight() != null)
				stack.push(node.getRight());
			if (node.getLeft() != null)
				stack.push(node.getLeft());
		}

		return accumulator;
	}

	/**
	 * 
	 * We will employ preorder traversal
	 */
	@Override
	public boolean isIdenticalTree(BST<T> bst1, BST<T> bst2) {

		return isIdenticalTree(bst1.getRoot(), bst2.getRoot());

	}

	/**
	 * <pre>
	 * Algorithm:  
	 * If both trees are NULL then return true.
	 * If both trees are not  NULL, then compare data and recursively check left and right subtree
	 * structures.
	 * </pre>
	 */
	private boolean isIdenticalTree(BSTNode<T> node1, BSTNode<T> node2) {

		if (node1 == null && node2 == null)
			return true;

		if (node1 == null || node2 == null)
			return false;

		return node1.getData().compareTo(node2.getData()) == 0 && isIdenticalTree(node1.getLeft(), node2.getLeft())
				&& isIdenticalTree(node1.getRight(), node2.getRight());

	}

	@Override
	public boolean isStructurallyIdenticalTree(BST<T> bst1, BST<T> bst2) {
		return isStructurallyIdenticalTree(bst1.getRoot(), bst2.getRoot());
	}

	private boolean isStructurallyIdenticalTree(BSTNode<T> node1, BSTNode<T> node2) {

		if (node1 == null && node2 == null)
			return true;

		if (node1 == null || node2 == null)
			return false;

		return isStructurallyIdenticalTree(node1.getLeft(), node2.getLeft())
				&& isStructurallyIdenticalTree(node1.getRight(), node2.getRight());

	}

	@Override
	public int getDiameterBruteForceRecursion(BST<T> bst) {
		if (bst.isEmpty())
			return 0;
		return nodeCountOnDiameterPathBruteForce(bst.getRoot()) - 1;
	}

	/**
	 * The diameter of the binary tree is defined as the number of edges on longest
	 * path between any two leaf nodes in the tree.
	 * 
	 * <pre>
	 * CASE1 : If diameter exists in left-subtree or right-subtree 
	 * 		     
	 * 				then noOfNodesOnDiameterPath = max(noOfNodesOnLeftDiameter, noOfNodesOnRightDiameter) 
	 * 
	 * CASE2: When diameter path enters from left-subtree to right-subtree by crossing-over the node 
	 *  	  that is if diameter passes through the root i.e. spans both left-subtree and right-subtree
	 *  
	 *          	 then  noOfNodesOnDiameterPath = noOfNodesOnLeftHeight + noOfNodesOnRightHeight + 1
	 *   
	 *      
	 *    Final noOfNodesOnDiameterPath = max(CASE1, CASE2)
	 * 
	 * </pre>
	 * 
	 * Time-complexity : O(n^2) : because numberOfNodeOnHeightPath(O(n)) is getting
	 * called explicitly for each node
	 */
	private int nodeCountOnDiameterPathBruteForce(BSTNode<T> node) {

		if (node == null) {
			return 0;
		}

		int noOfNodesOnLeftHeight = nodeCountOnHeightPath(node.getLeft());
		int noOfNodesOnRightHeight = nodeCountOnHeightPath(node.getRight());

		int noOfNodesOnLeftDiameter = nodeCountOnDiameterPathBruteForce(node.getLeft());
		int noOfNodesOnRightDiameter = nodeCountOnDiameterPathBruteForce(node.getRight());

		return Math.max(Math.max(noOfNodesOnLeftDiameter, noOfNodesOnRightDiameter),
				noOfNodesOnLeftHeight + noOfNodesOnRightHeight + 1);

	}

	private int nodeCountOnHeightPath(BSTNode<T> node) {
		if (node == null) {
			return 0;
		}
		return 1 + Math.max(nodeCountOnHeightPath(node.getLeft()), nodeCountOnHeightPath(node.getRight()));
	}

	@Override
	public int getDiameterRecursiveDP(BST<T> bst) {

		if (bst.isEmpty())
			return 0;

		int nodeCountOnDiameterPath[] = new int[1];
		nodeCountOnDiameterPathDP(bst.getRoot(), nodeCountOnDiameterPath);

		return nodeCountOnDiameterPath[0] - 1;
	}

	/**
	 * ALGO STRATEGY : 1. Calculate incremental diameter using incremental height
	 * calculation. Means we need to associate diameter and height to each node.
	 * 
	 * <pre>
	 * ALGO:
	 *   CASE1: When diameter path enters from left-subtree to right-subtree by crossing-over the given node. This topology will give
	 *          the final nodeCountOnDiameterPath for the given node.
	 *    THEN
	 *    nodeCountOnDiameterPath_for_the_given_node  = nodeCountOnHeightPathOfLeftSubTree + nodeCountOnHeightPathOfRightSubTree + 1;
	 *   
	 *   CASE2: When diameter path enters into parent from given node and does not cross-over from left-subtree to right-subtree. This 
	 *   topology will give the intermediate nodeCountOnHeightPath which will be used by parent for nodeCount calculation on heightPath.
	 *      THEN 
	 *    nodeCountOnHeightPath_for_the_given_node = max(nodeCountOnHeightPathOfLeftSubTree,nodeCountOnHeightPathOfRightSubTree) + 1;
	 * 
	 * </pre>
	 * 
	 * 
	 * nodeCountOnDiameterPathDP() method name is based on method argument
	 * 'nodeCountOnDiameterPath' and is not based on return type.
	 * 
	 * Time complexity : O(n)
	 */

	private int nodeCountOnDiameterPathDP(BSTNode<T> node, int[] nodeCountOnDiameterPath) {

		// intermediate contribution by null node to parent is ZERO.
		if (node == null) {
			return 0;
		}

		int nodeCountOnHeightPathOfLeftSubTree = nodeCountOnDiameterPathDP(node.getLeft(), nodeCountOnDiameterPath);
		int nodeCountOnHeightPathOfRightSubTree = nodeCountOnDiameterPathDP(node.getRight(), nodeCountOnDiameterPath);

		nodeCountOnDiameterPath[0] = Math.max(nodeCountOnDiameterPath[0],
				nodeCountOnHeightPathOfLeftSubTree + nodeCountOnHeightPathOfRightSubTree + 1);

		// intermediate contribution by non-null node to parent helps in nodeCount
		// calculation in heightPath.
		return Math.max(nodeCountOnHeightPathOfLeftSubTree, nodeCountOnHeightPathOfRightSubTree) + 1;

	}

	@Override
	public int getDiameterIterativeDFS(BST<T> bst) {

		if (bst.isEmpty())
			return 0;

		// return nodeCountOnDiameterPathIterativePostOrderNRL(bst.getRoot()) - 1;

		return nodeCountOnDiameterPathIterativePostOrder(bst.getRoot()) - 1;
	}

	/**
	 * <pre>
	 * ALGO:
	 *   CASE1: When diameter path enters from left-subtree to right-subtree by crossing-over the given node. This topology will give
	 *          the final nodeCountOnDiameterPath for the given node.
	 *    THEN
	 *    nodeCountOnDiameterPath_for_the_given_node  = nodeCountOnHeightPathOfLeftSubTree + nodeCountOnHeightPathOfRightSubTree + 1;
	 *   
	 *   CASE2: When diameter path enters into parent from given node and does not cross-over from left-subtree to right-subtree. This 
	 *   topology will give the intermediate nodeCountOnHeightPath which will be used by parent for nodeCount calculation on heightPath.
	 *      THEN 
	 *    nodeCountOnHeightPath_for_the_given_node = max(nodeCountOnHeightPathOfLeftSubTree,nodeCountOnHeightPathOfRightSubTree) + 1;
	 * 
	 * </pre>
	 * 
	 * nodeCount on heightPath or diameterPath for a given node needs
	 * nodeCountOnHeightPathOfLeftSubTree and nodeCountOnHeightPathOfRightSubTree
	 * i.e LRN so we can pick POSTORDER DFS traversal.
	 * 
	 */
	private int nodeCountOnDiameterPathIterativePostOrder(BSTNode<T> node) {

		Map<BSTNode<T>, Integer> nodeCountOnHeightPathMap = new HashMap<>();

		Deque<BSTNode<T>> stack = new ArrayDeque<>();
		// push root node.
		stack.push(node);

		int nodeCountOnDiameterPath = 0;

		while (!stack.isEmpty()) {

			node = stack.peek();

			if (node.getLeft() != null && !nodeCountOnHeightPathMap.containsKey(node.getLeft())) {
				stack.push(node.getLeft());
			} else if (node.getRight() != null && !nodeCountOnHeightPathMap.containsKey(node.getRight())) {
				stack.push(node.getRight());
			} else {

				int nodeCountOnLeftHeight = nodeCountOnHeightPathMap.getOrDefault(node.getLeft(), 0);
				int nodeCountOnRightHeight = nodeCountOnHeightPathMap.getOrDefault(node.getRight(), 0);

				nodeCountOnHeightPathMap.put(node, Math.max(nodeCountOnLeftHeight, nodeCountOnRightHeight) + 1);

				// calculate nodeCountOnDiameter path for current node
				int nodeCountOnDiameterPathForCurrentNode = nodeCountOnLeftHeight + nodeCountOnRightHeight + 1;

				nodeCountOnDiameterPath = Math.max(nodeCountOnDiameterPath, nodeCountOnDiameterPathForCurrentNode);

				// discard the stack-top
				stack.pop();
			}

		}

		return nodeCountOnDiameterPath;

	}

	/**
	 * <pre>
	 * ALGO:
	 *   CASE1: When diameter path enters from left-subtree to right-subtree by crossing-over the given node. This topology will give
	 *          the final nodeCountOnDiameterPath for the given node.
	 *    THEN
	 *    nodeCountOnDiameterPath_for_the_given_node  = nodeCountOnHeightPathOfLeftSubTree + nodeCountOnHeightPathOfRightSubTree + 1;
	 *   
	 *   CASE2: When diameter path enters into parent from given node and does not cross-over from left-subtree to right-subtree. This 
	 *   topology will give the intermediate nodeCountOnHeightPath which will be used by parent for nodeCount calculation on heightPath.
	 *      THEN 
	 *    nodeCountOnHeightPath_for_the_given_node = max(nodeCountOnHeightPathOfLeftSubTree,nodeCountOnHeightPathOfRightSubTree) + 1;
	 * 
	 * </pre>
	 * 
	 * nodeCount on heightPath or diameterPath for a given node needs
	 * nodeCountOnHeightPathOfLeftSubTree and nodeCountOnHeightPathOfRightSubTree
	 * i.e LRN so we can pick POSTORDER DFS traversal.
	 * 
	 */
	private int nodeCountOnDiameterPathIterativePostOrderNRL(BSTNode<T> node) {

		Map<BSTNode<T>, Integer> nodeCountOnHeightPathMap = new HashMap<>();

		Deque<BSTNode<T>> stack = new ArrayDeque<>();
		// push root node.
		stack.push(node);

		int nodeCountOnDiameterPath = 0;

		BSTNode<T> previous = null;

		while (!stack.isEmpty()) {

			node = stack.peek();

			if (node.isLeafNode() || isVisitedParent(node, previous)) {

				int nodeCountOnLeftHeight = nodeCountOnHeightPathMap.getOrDefault(node.getLeft(), 0);
				int nodeCountOnRightHeight = nodeCountOnHeightPathMap.getOrDefault(node.getRight(), 0);
				nodeCountOnHeightPathMap.put(node, Math.max(nodeCountOnLeftHeight, nodeCountOnRightHeight) + 1);

				// calculate nodeCountOnDiameter path for current node
				int nodeCountOnDiameterPathForCurrentNode = nodeCountOnLeftHeight + nodeCountOnRightHeight + 1;
				nodeCountOnDiameterPath = Math.max(nodeCountOnDiameterPath, nodeCountOnDiameterPathForCurrentNode);
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

		return nodeCountOnDiameterPath;

	}

	private boolean isVisitedParent(BSTNode<T> node, BSTNode<T> previous) {
		return previous != null && (node.getLeft() == previous || node.getRight() == previous);
	}

	@Override
	public Entry<Integer, Integer> getLevelWithMaximumSum(BST<Integer> bst) {
		if (bst.isEmpty())
			return null;

		BSTNode<Integer> root = bst.getRoot();
		Queue<BSTNode<Integer>> queue = new ArrayDeque<>();
		queue.offer(root);
		List<BSTNode<Integer>> levelNodesList = new ArrayList<>();

		// already root is added
		int level = 0;
		int maxSum = 0;
		int maxSumLevel = 0;
		while (!queue.isEmpty()) {

			while (!queue.isEmpty()) {
				levelNodesList.add(queue.poll());
			}

			levelNodesList.forEach(node -> {
				if (node.getRight() != null)
					queue.offer((node.getRight()));

				if (node.getLeft() != null)
					queue.offer((node.getLeft()));
			});

			int sum = levelNodesList.stream().mapToInt(BSTNode::getData).sum();
			if (sum > maxSum) {
				maxSum = sum;
				maxSumLevel = level;
			}

			if (!queue.isEmpty()) {
				level++;
				levelNodesList.clear();
			}

		}

		return Map.entry(maxSumLevel, maxSum);
	}

	@Override
	public void printRootToLeafNodePathsRecursively(BST<T> bst) {
		printRootToLeafNodePathsRecursively(bst.getRoot(), new ArrayList<>(), 0);
	}

	/**
	 * Here we need to employ pre-order traversal because we want to print in
	 * topo-graphical order.
	 * 
	 */
	private void printRootToLeafNodePathsRecursively(BSTNode<T> node, List<T> path, int level) {

		if (node == null)
			return;

		path.add(level, node.getData());

		// when we encounter the leaf node, print the path.
		if (node.isLeafNode()) {
			IntStream.rangeClosed(0, level).forEach(l -> {
				System.out.print(path.get(l) + ",");
			});

			System.out.println();
		} else {

			++level;

			printRootToLeafNodePathsRecursively(node.getLeft(), path, level);
			printRootToLeafNodePathsRecursively(node.getRight(), path, level);
		}

	}

	@Override
	public void printRootToLeafNodePathsIteratively(BST<T> bst) {
		printRootToLeafNodePathsIteratively(bst.getRoot(), 0);
	}

	public void printRootToLeafNodePathsIteratively(BSTNode<T> node, int level) {

		if (node == null)
			return;
		Deque<Map.Entry<BSTNode<T>, Integer>> nodeLevelStack = new ArrayDeque<>();
		nodeLevelStack.push(Map.entry(node, level));

		List<T> path = new ArrayList<>();

		while (!nodeLevelStack.isEmpty()) {
			Map.Entry<BSTNode<T>, Integer> entry = nodeLevelStack.pop();
			node = entry.getKey();
			level = entry.getValue();

			path.add(level, node.getData());

			// when we encounter the leaf node, print the path.
			if (node.isLeafNode()) {
				IntStream.rangeClosed(0, level).forEach(l -> {
					System.out.print(path.get(l) + ",");
				});

				System.out.println();
			} else {

				++level;
				// need to push right first as stack is LIFO
				if (node.getRight() != null)
					nodeLevelStack.push(Map.entry(node.getRight(), level));
				if (node.getLeft() != null)
					nodeLevelStack.push(Map.entry(node.getLeft(), level));
			}

		}

	}

	/**
	 * Strategy : subtract the node value from the sum before calling its children
	 * recursively in topological order i.e. preorder
	 * 
	 */
	@Override
	public boolean hasRootToLeafPathGivenSum(BST<Integer> bst, int sum) {

		return hasRootToLeafPathGivenSum(bst.getRoot(), sum);
	}

	private boolean hasRootToLeafPathGivenSum(BSTNode<Integer> node, int sum) {

		if (node == null) {
			return sum == 0;
		}

		boolean hasSumInLeft = hasRootToLeafPathGivenSum(node.getLeft(), sum - node.getData());

		boolean hasSumInRight = hasRootToLeafPathGivenSum(node.getRight(), sum - node.getData());

		return hasSumInLeft || hasSumInRight;

	}

	@Override
	public void printAllPathsWithGivenSum(BST<Integer> bst, int sum) {
		printAllPathsWithGivenSum(bst.getRoot(), new ArrayList<>(), 0, sum);
	}

	private void printAllPathsWithGivenSum(BSTNode<Integer> node, List<Integer> path, int level, int sum) {

		if (node == null)
			return;

		sum -= node.getData();

		if (sum < 0)
			return;

		path.add(level, node.getData());

		// when we encounter the leaf node, print the path.
		// if we have to consider till leaf node, then just change the condition with
		// (isLeafNode() && sum==0)
		if (sum == 0) {
			IntStream.rangeClosed(0, level).forEach(l -> {
				System.out.print(path.get(l) + ",");
			});

			System.out.println();
		} else {

			++level;

			printAllPathsWithGivenSum(node.getLeft(), path, level, sum);
			printAllPathsWithGivenSum(node.getRight(), path, level, sum);
		}

	}

	@Override
	public int getSum(BST<Integer> bst) {
		return getSum(bst.getRoot());
	}

	/**
	 * 
	 * We can employ any of the tree traversal for getting the sum. Here we are
	 * using pre-order traversal.
	 */
	private int getSum(BSTNode<Integer> node) {
		if (node == null)
			return 0;
		return node.getData().intValue() + getSum(node.getLeft()) + getSum(node.getRight());

	}

	@Override
	public void getMirror(BST<T> bst) {

		//getMirrorRecursively(bst.getRoot());
		//getMirrorIterativelyDFS(bst.getRoot());
		 getMirrorIterativelyBFS(bst.getRoot());
	}

	/**
	 * <pre>
	 * Hypothesis : getMirrorRecursively(BSTNode<T> node)   
	 *  	Input :  leftSubtree<---Node--->rightSubtree 
	 *     Output :   MirroredTree ==>  mirroredRightSubTree<---Node--->mirroredLeftSubTree
	 *     
	 *Substitution :
	 *       mirroredLeftSubTree =  getMirrorRecursively(Node.getLeft()) 
	 *       mirroredRightSubTree =  getMirrorRecursively(Node.getRight()) 
	 *     
	 * Induction :
	 *    
	 *      node.setLeft(mirroredRightSubTree)
	 *      node.setRight(mirroredLeftSubTree)
	 * 
	 * </pre>
	 * 
	 */
	private BSTNode<T> getMirrorRecursively(BSTNode<T> node) {

		if (node == null)
			return null;

		BSTNode<T> mirroredLeftSubTree = getMirrorRecursively(node.getLeft());
		BSTNode<T> mirroredRightSubTree = getMirrorRecursively(node.getRight());

		node.setLeft(mirroredRightSubTree);
		node.setRight(mirroredLeftSubTree);

		return node;
	}

	/**<pre>
	 * Iterative : 
	 * 	Feasible DFS : pre-order and post-order         
	 *  </pre>           
	 * */
	private BSTNode<T> getMirrorIterativelyDFS(BSTNode<T> node) {
		if (node == null)
			return null;
		Deque<BSTNode<T>> stack = new ArrayDeque<>();
		stack.push(node);
		while (!stack.isEmpty()) {
			BSTNode<T> snode = stack.pop();
			//swap
			BSTNode<T> left = snode.getLeft();
			snode.setLeft(snode.getRight());
			snode.setRight(left);
			
			if (snode.getRight() != null)
				stack.push(snode.getRight());
			if (snode.getLeft() != null)
				stack.push(snode.getLeft());
		}

	   return node;
	}
	
	
	/**<pre>
	 * Iterative : BFS        
	 *  </pre>           
	 * */
	private BSTNode<T> getMirrorIterativelyBFS(BSTNode<T> root) {
		if(root == null) return null;
		 Queue<BSTNode<T>> queue = new ArrayDeque<>();
		 queue.offer(root);
		
		while (!queue.isEmpty()) {
			BSTNode<T> node = queue.poll();
			//swap
			BSTNode<T> left = node.getLeft();
			node.setLeft(node.getRight());
			node.setRight(left);

			if (node.getLeft() != null)
				queue.offer(node.getLeft());
			if (node.getRight() != null)
				queue.offer(node.getRight());
		}

		return root;
	}

	@Override
	public boolean isMirror(BST<T> bst1, BST<T> bst2) {
		return isMirror(bst1.getRoot(), bst2.getRoot());
	}
	
	private boolean isMirror(BSTNode<T> node1, BSTNode<T> node2) {
	 	
		if(node1==null && node2==null) return true;
		
	 	//if any of the single node is null then return false;
	 	if(node1==null) return false;
	 	if(node2==null) return false;
	 	
	 	//if both node1 and node2 are not null
	 	if(node1.getData().compareTo(node2.getData())!=0) {
	 		return false;
	 	}
	 	
		 	
	 	//substitution
	 	boolean isMirror1 = isMirror(node1.getLeft(),node2.getRight());
	 	boolean isMirror2 = isMirror(node1.getRight(), node2.getLeft());
	 	
	 	//induction
	 	return isMirror1 && isMirror2;
	 	
	}
	
	

}