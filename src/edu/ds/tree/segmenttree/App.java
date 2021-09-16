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
		
		//testMaxSubSegmentSumWithNegative();
		
		//testRangeUpdateAdditionSegmentTree();
		
		testRangeUpdateSubtractionSegmentTree();

	}

	private static void testMaxOccurrenceSegmentTree3() {

		int input1[] = { 3, 1, 4, 2, 4, 4, 4, 2 };

		RangeQueryST<Map.Entry<Integer, Integer>> tree1 = new MaxOccurenceSTImpl3(input1, true);

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
		RangeQueryST<Map.Entry<Integer, Integer>> tree2 = new MaxOccurenceSTImpl3(input2, true);
		System.out.println("MaxOccurenceSegmentTree  skewed:" + tree2);
		System.out.println("rangeQuery[0,4):" + tree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + tree2.rangeQuery(0, 6));
		System.out.println("update at 5: value 10");
		tree2.update(Map.entry(10, 1), 5);
		System.out.println("MaxOccurenceSegmentTree  skewed post update:" + tree2);
		System.out.println("rangeQuery[0,4):" + tree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):" + tree2.rangeQuery(0, 6));

		int input3[] = { 5, 4, 3, 6, 7, 9, 1, 10 };
		RangeQueryST.MaxSegmentTree<Integer> tree3 = new MaxOccurenceSTImpl3(input3, false);
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

		RangeQueryST<Integer> sumSegementTree1 = new SumSTImpl1(input1, true);
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
		RangeQueryST<Integer> sumSegementTree2 = new SumSTImpl1(input2, true);
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
		RangeQueryST.SumSegmentTree<Integer> tree = new SumSTImpl1(elements, true);

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

		RangeQueryST<Integer> sumSegementTree1 = new OptimizedSumSTImpl2(input1, false);
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
		RangeQueryST<Integer> sumSegementTree2 = new OptimizedSumSTImpl2(input2, false);
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
		RangeQueryST<MaxSubSegmentSumWithNegativeSTImpl4.Data> tree = new MaxSubSegmentSumWithNegativeSTImpl4(elements, false);
		System.out.println(tree);

		int ql = 0;
		int qr = 5;
		System.out.println("Sum in range " + ql + " - " + qr + " is: " + tree.rangeQuery(ql, qr).getSum());
		System.out.println("Max Sum in range " + ql + " - " + qr + " is: " + tree.rangeQuery(ql, qr).getMaxSum());
		
		
		System.out.println("----------initializing flat--------------------------------");
		RangeQueryST<MaxSubSegmentSumWithNegativeSTImpl4.Data> treeFlat = new MaxSubSegmentSumWithNegativeSTImpl4(elements, true);
		System.out.println(treeFlat);

		System.out.println("Sum in range " + ql + " - " + qr + " is: " + treeFlat.rangeQuery(ql, qr).getSum());
		System.out.println("Max Sum in range " + ql + " - " + qr + " is: " + treeFlat.rangeQuery(ql, qr).getMaxSum());
	}

	private static void testRangeUpdateAdditionSegmentTree() {
		int[] elements  = new int[] {2, 7, 3, 5, 6, 11, 14};
		System.out.println("input:"+Arrays.toString(elements));
		RangeUpdateST<Integer> tree = new RangeUpdateAdditionSTImpl5(elements);
		System.out.println("-----------------------original tree--------------------");
		System.out.println(tree);
		int left = 0;
		int right = 7;
		int data = 3;
		System.out.println("update:" + data +   " for range["+left+","+right+")");
		tree.update(data, left, right);
		System.out.println(tree);
		System.out.println("query at index 3: "+tree.query(3));
		
		left =3;
		right=7;
		data=5;
		System.out.println("update:" + data +   " for range["+left+","+right+")");
		tree.update(data, left, right);
		System.out.println(tree);
		System.out.println("query at index 4: "+tree.query(4));
		
		left =2;
		right=5;
		data= 1;
		System.out.println("update:" + data +   " for range["+left+","+right+")");
		tree.update(data, left, right);
		System.out.println(tree);
		System.out.println("query at index 2: "+tree.query(2));
		System.out.println("query at index 3: "+tree.query(3));
		System.out.println("query at index 4: "+tree.query(4));
		System.out.println("query at index 5: "+tree.query(5));

		
	}
	
	
	private static void testRangeUpdateSubtractionSegmentTree() {
		int[] elements  = new int[] {2, 7, 3, 5, 6, 11, 14};
		System.out.println("input:"+Arrays.toString(elements));
		RangeUpdateST<Integer> tree = new RangeUpdateSubtractionSTImpl6(elements);
		System.out.println("-----------------------original tree--------------------");
		System.out.println(tree);
		int left = 0;
		int right = 7;
		int data = 3;
		System.out.println("update:" + data +   " for range["+left+","+right+")");
		tree.update(data, left, right);
		System.out.println(tree);
		System.out.println("query at index 3: "+tree.query(3));
		
		left =3;
		right=7;
		data=5;
		System.out.println("update:" + data +   " for range["+left+","+right+")");
		tree.update(data, left, right);
		System.out.println(tree);
		System.out.println("query at index 2: "+tree.query(2));
		
		left =2;
		right=6;
		data= 1;
		System.out.println("update:" + data +   " for range["+left+","+right+")");
		tree.update(data, left, right);
		System.out.println(tree);
		System.out.println("query at index 2: "+tree.query(2));
		System.out.println("query at index 3: "+tree.query(3));
		System.out.println("query at index 4: "+tree.query(4));
		System.out.println("query at index 5: "+tree.query(5));

		
	}
	
}
