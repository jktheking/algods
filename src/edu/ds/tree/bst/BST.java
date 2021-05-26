package edu.ds.tree.bst;

import java.util.List;

import edu.ds.tree.Tree;
import edu.ds.tree.bst.BinarySearchTree.BSTNode;

public interface BST<T extends Comparable<T>> extends Tree<T> {

	void insert(T data);

	void delete(T data);

	T getMaxElement();

	T getMinElement();

	int size();

	int size(T data);

	int height();

	boolean isEmpty();

	List<T> preorderTraverse();

	List<T> inorderTraverse();

	List<T> postorderTraverse();

	List<T> bfsTraversal();

	boolean contains(T data);

	BSTNode<T> getRoot();
	void setRoot(BSTNode<T> root);

}
