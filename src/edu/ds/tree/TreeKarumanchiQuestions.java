package edu.ds.tree;

import java.util.List;
import java.util.Map;

import edu.ds.tree.bst.BST;
import edu.ds.tree.bst.BinarySearchTree.BSTNode;

public interface TreeKarumanchiQuestions<T extends Comparable<T>> {

	/**
	 * Problem-8 Give an algorithm for printing the level order data in reverse
	 * order. For example, the output for the below tree should be: 4 5 6 7 -- 2 3
	 * -- 1
	 * 
	 */

	List<T> bottomUpLevelOrder(BST<T> bst);

	/**
	 * Problem-11 Can we solve Problem-10 without recursion?
	 */
	int iterativeHeight(BST<T> bst);

	void printLevelsOfTreeOnSeparateLine(BST<T> bst);

	/**
	 * Problem-12 Give an algorithm for finding the deepest node of the binary tree.
	 * 
	 */

	BSTNode<T> getDeepestNode(BST<T> bst);

	/**
	 * Problem-14A Give an algorithm for finding the number of leaves in the binary
	 * tree using recursion.
	 */
	List<T> getLeafNodesUisingRecursion(BST<T> bst);

	/**
	 * Problem-14B Give an algorithm for finding the number of leaves in the binary
	 * tree without using recursion.
	 */
	List<T> getLeafNodesUisingIteration(BST<T> bst);

	/**
	 * 
	 * Problem-15 Give an algorithm for finding the number of full nodes in the
	 * binary tree without using recursion.
	 */
	List<T> getFullNodesUsingIteration(BST<T> bst);

	/**
	 * Problem-16 Give an algorithm for finding the number of half nodes (nodes with
	 * only one child) in the binary tree without using recursion.
	 * 
	 */
	List<T> getHalfNodesUsingIteration(BST<T> bst);

	/**
	 * Problem-17A : Given two binary trees, return true if they are identical
	 */
	boolean isIdenticalTree(BST<T> bst1, BST<T> bst2);

	/**
	 * Problem-17B : Given two binary trees, return true if they are structurally
	 * identical
	 */
	boolean isStructurallyIdenticalTree(BST<T> bst1, BST<T> bst2);

	/**
	 * Problem-18 Give an algorithm for finding the diameter of the binary tree. The
	 * diameter of the binary tree is defined as the length of the longest path
	 * between any two leaf nodes in the tree.
	 */
	int getDiameterBruteForceRecursion(BST<T> bst);

	int getDiameterRecursiveDP(BST<T> bst);

	int getDiameterIterativeDFS(BST<T> bst);

	/**
	 * Problem-19 Give an algorithm for finding the level that has the maximum sum
	 * in the binary tree.
	 */
	Map.Entry<Integer, Integer> getLevelWithMaximumSum(BST<Integer> bst);

	/**
	 * Problem-20 Given a binary tree, print out all its root-to-leaf paths.
	 */
	void printRootToLeafNodePathsRecursively(BST<T> bst);

	void printRootToLeafNodePathsIteratively(BST<T> bst);

	/**
	 * 
	 * Problem-21A : Check if root to leaf path sum equal to a given number
	 * 
	 */
	boolean hasRootToLeafPathGivenSum(BST<Integer> bst, int sum);

	/**
	 * 
	 * Problem-21B : Given a sum, check whether there exists a path from root to any
	 * of the nodes and print all possible paths.
	 */
	void printAllPathsWithGivenSum(BST<Integer> bst, int sum);

	/**
	 * Problem-22 Give an algorithm for finding the sum of all elements in binary
	 * tree.
	 **/
	int getSum(BST<Integer> bst);

	/**
	 * Problem-24 Give an algorithm for converting a tree to its mirror. Mirror of a
	 * tree is another tree with left and right children of all non-leaf nodes
	 * interchanged.
	 * 
	 */
	void getMirror(BST<T> bst);

	/**
	 * Problem-25 Given two trees, give an algorithm for checking whether they are
	 * mirrors of each other.
	 * 
	 */
   boolean isMirror(BST<T> bst1, BST<T> bst2);
}
