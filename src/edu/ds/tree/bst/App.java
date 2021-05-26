package edu.ds.tree.bst;

import edu.ds.tree.Tree;

public class App {

	public static void main(String[] args) {

		
		//height();
		//bfsTraversal();
		//getMaxElement();
		//getMinElement();
		//dfsTraversal();
		//delete(105);
		size();
	}
	
	private static void height() {
		
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);

		System.out.println(tree.height());
	}
	
	private static void bfsTraversal() {
		
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);

		System.out.println(tree.bfsTraversal());
	}
	
	private static void getMinElement() {

		
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);

		System.out.println(tree.getMinElement());
	
	}
	
	
	private static void getMaxElement() {

		
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);

		System.out.println(tree.getMaxElement());
	
	}
	
	private static void  dfsTraversal() {

		
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);

		System.out.println("pre:"+tree.preorderTraverse());
		System.out.println("in:"+tree.inorderTraverse());
		System.out.println("post:"+tree.postorderTraverse());
	
	}
	
	private static void delete(int data) {
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);
		
		System.out.println("before:"+tree);
		System.out.println("size before:"+tree.size());
		System.out.println("data to be deleted :"+data);
		tree.delete(data);
		System.out.println("after:"+tree);
		System.out.println("size after:"+tree.size());

	}
	
	private static void size() {
		Tree<Integer> tree = new BinarySearchTree<>();
		//level1
		tree.insert(99);
		
		//level2
		tree.insert(50);
		tree.insert(105);

		//level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		//level4
		tree.insert(55);
		tree.insert(85);

		//level5
		tree.insert(80);

		///level6
		tree.insert(83);
		
		System.out.println(tree);
		System.out.println(tree.size(50));
	}

}
