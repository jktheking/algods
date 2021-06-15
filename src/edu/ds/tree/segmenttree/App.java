package edu.ds.tree.segmenttree;

public class App {
	public static void main(String[] args) {
		//testSumSegmentTree();
		System.out.println("--------------------------testOptimizedSumSegmentTree--------------");
		testOptimizedSumSegmentTree();
	}
	
	
	private static void testSumSegmentTree() {
		
		int input1[] = {2,3,5,8,9,1,4,7};
		
		
		SegmentTree<Integer> sumSegementTree1 = new SumSegmentTree1(input1,true);
		System.out.println("sumSegment tree:"+sumSegementTree1);
		System.out.println("rangeQuery[3,7):"+sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):"+sumSegementTree1.rangeQuery(0, 8));
		System.out.println("update at 5: value 6");
		sumSegementTree1.update(6, 5);
		System.out.println("sumSegment tree post update:"+sumSegementTree1);
		System.out.println("rangeQuery[3,7):"+sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):"+sumSegementTree1.rangeQuery(0, 8));
		
		///////////////////////////////////////////////////////////////////////
		int input2[] = {5,8,9,1,4,7};
		SegmentTree<Integer> sumSegementTree2 = new SumSegmentTree1(input2,true);
		System.out.println("sumSegment tree skewed:"+sumSegementTree2);
		System.out.println("rangeQuery[0,4):"+sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):"+sumSegementTree2.rangeQuery(0, 6));
		System.out.println("update at 5: value 6");
		sumSegementTree2.update(6, 5);
		System.out.println("sumSegment tree skewed post update:"+sumSegementTree2);
		System.out.println("rangeQuery[0,4):"+sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):"+sumSegementTree2.rangeQuery(0, 6));
		
		
	}
	
	
private static void testOptimizedSumSegmentTree() {
		
		int input1[] = {2,3,5,8,9,1,4,7};
		
		
		SegmentTree<Integer> sumSegementTree1 = new OptimizedSumSegmentTree2(input1,false);
		System.out.println("optimizedSumSegment tree:"+sumSegementTree1);
		System.out.println("rangeQuery[3,7):"+sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):"+sumSegementTree1.rangeQuery(0, 8));
		System.out.println("update at 5: value 6");
		sumSegementTree1.update(6, 5);
		System.out.println("optimizedSumSegment tree post update:"+sumSegementTree1);
		System.out.println("rangeQuery[3,7):"+sumSegementTree1.rangeQuery(3, 7));
		System.out.println("rangeQuery[0,8):"+sumSegementTree1.rangeQuery(0, 8));
		
		///////////////////////////////////////////////////////////////////////
		int input2[] = {5,8,9,1,4,7};
		SegmentTree<Integer> sumSegementTree2 = new OptimizedSumSegmentTree2(input2,false);
		System.out.println("optimizedSumSegment tree skewed:"+sumSegementTree2);
		System.out.println("rangeQuery[0,4):"+sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):"+sumSegementTree2.rangeQuery(0, 6));
		System.out.println("update at 5: value 6");
		sumSegementTree2.update(6, 5);
		System.out.println("optimizedSumSegment tree skewed post update:"+sumSegementTree2);
		System.out.println("rangeQuery[0,4):"+sumSegementTree2.rangeQuery(0, 4));
		System.out.println("rangeQuery[0,6):"+sumSegementTree2.rangeQuery(0, 6));
		
		
	}

}
