package edu.ds.tree;

import edu.ds.tree.bst.BST;
import edu.ds.tree.bst.BinarySearchTree;

public class KarumanchiTest {

	private static final TreeKarumanchiQuestions<Integer> KARUMANCHI_QUESTIONS = new TreeKarumanchiSolutions<>();

	public static void main(String[] args) {

		// bottomUpLevelOrder();
		// iterativeHeight();
		// printLevelsOfTreeOnSeparateLine();
		// getDeepestNode();
		// getLeafNodesUisingRecursion();
		// getLeafNodesUisingIteration();

		// getFullNodesUsingIteration();

		// getHalfNodesUsingIteration();

		// isIdenticalTree();

		// isStructurallyIdenticalTree();

		// getDiameterBruteForceRecursion();

		// getDiameterRecursiveDP();
		// getDiameterIterativeDFS();
		//getLevelWithMaximumSum();
		//printRootToLeafNodePathsRecursively();
		//printRootToLeafNodePathsIteratively();
		
		//hasRootToLeafPathGivenSum();
		//printAllPathsWithGivenSum();
		//getSum();
		
		//getMirror();
		isMirror();

	}

	private static void bottomUpLevelOrder() {
		BST<Integer> bst = newSampleBST();

		System.out.println(bst);
		System.out.println("bottomUpLevelOrder:" + KARUMANCHI_QUESTIONS.bottomUpLevelOrder(bst));

	}

	private static void iterativeHeight() {

		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println("height:" + KARUMANCHI_QUESTIONS.iterativeHeight(bst));
	}

	private static void printLevelsOfTreeOnSeparateLine() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		KARUMANCHI_QUESTIONS.printLevelsOfTreeOnSeparateLine(bst);

	}

	private static void getDeepestNode() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println("deepestNode:" + KARUMANCHI_QUESTIONS.getDeepestNode(bst));
	}

	private static void getLeafNodesUisingRecursion() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println(KARUMANCHI_QUESTIONS.getLeafNodesUisingRecursion(bst));
	}

	private static void getLeafNodesUisingIteration() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println(KARUMANCHI_QUESTIONS.getLeafNodesUisingIteration(bst));
	}

	private static void getFullNodesUsingIteration() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println(KARUMANCHI_QUESTIONS.getFullNodesUsingIteration(bst));
	}

	private static void getHalfNodesUsingIteration() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println(KARUMANCHI_QUESTIONS.getHalfNodesUsingIteration(bst));
	}

	private static void isIdenticalTree() {
		BST<Integer> bst1 = newSampleBST();
		System.out.println(bst1);
		BST<Integer> bst2 = newSampleBST();
		bst2.insert(1098);
		System.out.println(bst2);
		System.out.println(KARUMANCHI_QUESTIONS.isIdenticalTree(bst1, bst2));

	}

	private static void isStructurallyIdenticalTree() {

		BST<Integer> bst1 = newSampleBST();
		System.out.println(bst1);
		BST<Integer> bst2 = newSampleBST();
		bst2.delete(1);
		bst2.insert(20);
		System.out.println(bst2);
		System.out.println(KARUMANCHI_QUESTIONS.isStructurallyIdenticalTree(bst1, bst2));

	}

	private static void getDiameterBruteForceRecursion() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println("diameter:" + KARUMANCHI_QUESTIONS.getDiameterBruteForceRecursion(bst));

		BST<Integer> bst1 = leftSubtreeDiameterSample();
		System.out.println(bst1);
		System.out.println("diameter:" + KARUMANCHI_QUESTIONS.getDiameterBruteForceRecursion(bst1));
	}

	private static void getDiameterRecursiveDP() {

		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println("diameter:" + KARUMANCHI_QUESTIONS.getDiameterRecursiveDP(bst));

		BST<Integer> bst1 = leftSubtreeDiameterSample();
		System.out.println(bst1);
		System.out.println("diameter:" + KARUMANCHI_QUESTIONS.getDiameterRecursiveDP(bst1));

	}

	private static void getDiameterIterativeDFS() {

		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println("diameter:" + KARUMANCHI_QUESTIONS.getDiameterIterativeDFS(bst));

		BST<Integer> bst1 = leftSubtreeDiameterSample();
		System.out.println(bst1);
		System.out.println("diameter:" + KARUMANCHI_QUESTIONS.getDiameterIterativeDFS(bst1));
	}

	private static void getLevelWithMaximumSum() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println(KARUMANCHI_QUESTIONS.getLevelWithMaximumSum(bst));
	}

	private static void printRootToLeafNodePathsRecursively() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		KARUMANCHI_QUESTIONS.printRootToLeafNodePathsRecursively(bst);
	}

	private static void printRootToLeafNodePathsIteratively() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		KARUMANCHI_QUESTIONS.printRootToLeafNodePathsIteratively(bst);
	}
	
	
	private static void hasRootToLeafPathGivenSum() {
		BST<Integer> bst1 = newSampleBST();
		System.out.println(bst1);
		System.out.println(KARUMANCHI_QUESTIONS.hasRootToLeafPathGivenSum(bst1, 279));
		
		BST<Integer> bst2 = getSumSampleTree();
		System.out.println(bst2);
		System.out.println(KARUMANCHI_QUESTIONS.hasRootToLeafPathGivenSum(bst2, 405));
	}

	
	private static void printAllPathsWithGivenSum() {
		
		BST<Integer> bst1 = newSampleBST();
		System.out.println(bst1);
		KARUMANCHI_QUESTIONS.printAllPathsWithGivenSum(bst1, 150);
		
		BST<Integer> bst2 = getSumSampleTree();
		System.out.println(bst2);
		KARUMANCHI_QUESTIONS.printAllPathsWithGivenSum(bst2, 405);
		
	}
	
	private static void getSum() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		System.out.println(KARUMANCHI_QUESTIONS.getSum(bst));
		
	}
	
	private static void getMirror() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		 KARUMANCHI_QUESTIONS.getMirror(bst);
		
		System.out.println(bst);
	}
	
	
	private static void isMirror() {
		BST<Integer> bst = newSampleBST();
		System.out.println(bst);
		KARUMANCHI_QUESTIONS.getMirror(bst);
		System.out.println("-----------mirror bst-------------------");
		System.out.println(bst);
		
		System.out.println("-----checking is mirror---");
		System.out.println(KARUMANCHI_QUESTIONS.isMirror(newSampleBST(), bst));
	}
	
	private static BST<Integer> getSumSampleTree(){
		BST<Integer> tree = new BinarySearchTree<>();
        tree.insert(200);

 

        tree.insert(190);
        tree.insert(205);

 

        tree.insert(195);
        tree.insert(203);
        tree.insert(15);
        tree.insert(400);
        
        return tree;
	}

	private static BST<Integer> leftSubtreeDiameterSample() {
		BST<Integer> tree = new BinarySearchTree<>();
		tree.insert(99);

		tree.insert(50);
		tree.insert(105);

		tree.insert(20);
		tree.insert(21);
		tree.insert(22);
		tree.insert(23);
		tree.insert(24);

		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		tree.insert(55);
		tree.insert(85);

		tree.insert(80);

		tree.insert(83);

		return tree;
	}

	private static BST<Integer> newSampleBST() {
		BST<Integer> tree = new BinarySearchTree<>();
		// level1
		tree.insert(99);

		// level2
		tree.insert(50);
		tree.insert(105);

		// level3
		tree.insert(1);
		tree.insert(75);
		tree.insert(103);
		tree.insert(110);

		// level4
		tree.insert(55);
		tree.insert(85);

		// level5
		tree.insert(80);

		/// level6
		tree.insert(83);

		return tree;

	}
}
