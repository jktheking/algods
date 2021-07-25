package edu.ds.tree.segmenttree;

import java.util.Arrays;
import java.util.Map;

public class App {
	public static void main(String[] args) {

		// testSumSegmentTree();
		// System.out.println("--------------------------testOptimizedSumSegmentTree--------------");
		// testOptimizedSumSegmentTree();
		// testMaxOccurrenceSegmentTree3();

		// testSumSegmentTree();
		// testMaxOccurrenceSegmentTree3getRightBoundOfRangeInGivenQueryRangeForSumSegmentTree();

		//testMaxOccurrenceSegmentTree3();
		
		testMaxSubSegmentSumWithNegative();

	}

	private static void testMaxOccurrenceSegmentTree3() {

		int input1[] = { 3, 1, 4, 2, 4, 4, 4, 2 };

		SegmentTree<Map.Entry<Integer, Integer>> tree1 = new MaxOccurenceST3(input1, true);

		System.out.println("MaxOccurenceSegmentTree tree:" + tree1);
		System.out.println("rangeQuery[3,7):" + tree1.rangeQuery(3, 7));

		System.out.println("rangeQuery[0,8):" + tree1.rangeQuery(0, 8));
		System.out.println("update at 5: value 10");
		tree1.update(Map.entry(10, 1), 5);
		System.out.println("MaxOccurenceSegmentTree  post update:" + tree1);
		System.out.println("rangeQuery[3,7):" + tree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):" + tree1.rangeQuery(0, 8));

		///////////////////////////////////////////////////////////////////////
		int input2[] = { 3, 1, 4, 2, 4, 4 };
		SegmentTree<Map.Entry<Integer, Integer>> tree2 = new MaxOccurenceST3(input2, true);
		System.out.println("MaxOccurenceSegmentTree  skewed:" + tree2);
		System.out.println("rangeQuery[0,4):" + tree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + tree2.rangeQuery(0, 6));
		System.out.println("update at 5: value 10");
		tree2.update(Map.entry(10, 1), 5);
		System.out.println("MaxOccurenceSegmentTree  skewed post update:" + tree2);
		System.out.println("rangeQuery[0,4):" + tree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + tree2.rangeQuery(0, 6));

		int input3[] = { 5, 4, 3, 6, 7, 9, 1, 10 };
		SegmentTree.MaxSegmentTree<Integer> tree3 = new MaxOccurenceST3(input3, false);
		System.out.println("---------getFirstGreaterElement---------- " + Arrays.toString(input3));
		System.out.println(tree3);
		int data = 20;
		int ql = 2;
		int qr = 7;
		System.out.println("index of FirstGreaterElement than :" + data + " is : "
				+ tree3.getFirstGreaterElement(data, ql, qr) + " in given query range:" + ql + "," + qr);

	}

	private static void testSumSegmentTree() {

		int input1[] = { 2, 3, 5, 8, 9, 1, 4, 7 };

		SegmentTree<Integer> sumSegementTree1 = new SumST1(input1, true);
		System.out.println("sumSegment tree:" + sumSegementTree1);
		System.out.println("rangeQuery[3,7):" + sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):" + sumSegementTree1.rangeQuery(0, 8));
		System.out.println("update at 5: value 6");
		sumSegementTree1.update(6, 5);
		System.out.println("sumSegment tree post update:" + sumSegementTree1);
		System.out.println("rangeQuery[3,7):" + sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):" + sumSegementTree1.rangeQuery(0, 8));

		///////////////////////////////////////////////////////////////////////
		int input2[] = { 5, 8, 9, 1, 4, 7 };
		SegmentTree<Integer> sumSegementTree2 = new SumST1(input2, true);
		System.out.println("sumSegment tree skewed:" + sumSegementTree2);
		System.out.println("rangeQuery[0,4):" + sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + sumSegementTree2.rangeQuery(0, 6));
		System.out.println("update at 5: value 6");
		sumSegementTree2.update(6, 5);
		System.out.println("sumSegment tree skewed post update:" + sumSegementTree2);
		System.out.println("rangeQuery[0,4):" + sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + sumSegementTree2.rangeQuery(0, 6));

	}

	private static void getRightBoundOfRangeInGivenQueryRangeForSumSegmentTree() {
		int[] elements = new int[] { 10, 5, 15, 4, 9, 7, 6, 2 };
		SegmentTree.SumSegmentTree<Integer> tree = new SumST1(elements, true);

		System.out.println(tree);

		int givenSum = 28;
		System.out.println("GivenSum " + givenSum + " can be done till index: " + tree.getRightBoundOfRange(givenSum));

		int ql = 2;
		int qr = 7;
		System.out.println("GivenSum " + givenSum + " can be done till index: "
				+ tree.getRightBoundOfRange(givenSum, ql, qr) + " in range " + ql + " - " + qr);

		ql = 0;
		System.out.println("GivenSum " + givenSum + " can be done till index: "
				+ tree.getRightBoundOfRange(givenSum, ql, qr) + " in range " + ql + " - " + qr);
	}

	private static void testOptimizedSumSegmentTree() {

		int input1[] = { 2, 3, 5, 8, 9, 1, 4, 7 };

		SegmentTree<Integer> sumSegementTree1 = new OptimizedSumST2(input1, false);
		System.out.println("optimizedSumSegment tree:" + sumSegementTree1);
		System.out.println("rangeQuery[3,7):" + sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):" + sumSegementTree1.rangeQuery(0, 8));
		System.out.println("update at 5: value 6");
		sumSegementTree1.update(6, 5);
		System.out.println("optimizedSumSegment tree post update:" + sumSegementTree1);
		System.out.println("rangeQuery[3,7):" + sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):" + sumSegementTree1.rangeQuery(0, 8));

		///////////////////////////////////////////////////////////////////////
		int input2[] = { 5, 8, 9, 1, 4, 7 };
		SegmentTree<Integer> sumSegementTree2 = new OptimizedSumST2(input2, false);
		System.out.println("optimizedSumSegment tree skewed:" + sumSegementTree2);
		System.out.println("rangeQuery[0,4):" + sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + sumSegementTree2.rangeQuery(0, 6));
		System.out.println("update at 5: value 6");
		sumSegementTree2.update(6, 5);
		System.out.println("optimizedSumSegment tree skewed post update:" + sumSegementTree2);
		System.out.println("rangeQuery[0,4):" + sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + sumSegementTree2.rangeQuery(0, 6));

	}

	private static void testMaxSubSegmentSumWithNegative() {
		int[] elements = new int[] { 10, -7, 8, -5, 5, -5, 7, -15 };
		
		//int[] elements = new int[] { -10, -7, -8, -5, -5, -5, -7, -15 };
		
		System.out.println("------------------initializing max with 0-----------------------------");
		SegmentTree<MaxSubSegmentSumWithNegativeST4.Data> tree = new MaxSubSegmentSumWithNegativeST4(elements, false);
		System.out.println(tree);

		int ql = 0;
		int qr = 5;
		System.out.println("Sum in range " + ql + " - " + qr + " is: " + tree.rangeQuery(ql, qr).getSum());
		System.out.println("Max Sum in range " + ql + " - " + qr + " is: " + tree.rangeQuery(ql, qr).getMaxSum());
		
		
		System.out.println("----------initializing flat--------------------------------");
		SegmentTree<MaxSubSegmentSumWithNegativeST4.Data> treeFlat = new MaxSubSegmentSumWithNegativeST4(elements, true);
		System.out.println(treeFlat);

		System.out.println("Sum in range " + ql + " - " + qr + " is: " + treeFlat.rangeQuery(ql, qr).getSum());
		System.out.println("Max Sum in range " + ql + " - " + qr + " is: " + treeFlat.rangeQuery(ql, qr).getMaxSum());
	}

}
