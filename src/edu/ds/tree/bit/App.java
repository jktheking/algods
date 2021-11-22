package edu.ds.tree.bit;

import java.util.Arrays;

public class App {

	public static void main(String[] args) {

		int[] input = { 5, 7, 3, 2, 4, 6, 3, 1 };

		testPointUpdateRangeQuery(input);

	}

	private static void testPointUpdateRangeQuery(int[] input) {

		BIT<Integer> bit = new Fenwick1(input);
		System.out.println("input:" + Arrays.toString(input));
		System.out.println(bit);

		System.out.println("range query[2,5):"+bit.rangeQuery(2, 5));
		System.out.println("point query 3:"+bit.pointQuery(3));
		
		System.out.println("point update at index 3 value: 4");
		bit.pointUpdate(3, 4);
		
		System.out.println(bit);
		System.out.println("range query[2,5):"+bit.rangeQuery(2, 5));
		System.out.println("point query 3:"+bit.pointQuery(3));
	}

	private static void floatingMedianQuestion() {
		/**
		 * <pre>
		 * SRM 310 – FloatingMedian
		 * 
		 * Problem Statement: There is an array consisting of n cards. Initially,
		 * each card is put on the table with its face down. 
		 * 
		 * There are two queries:
		 * 
		 * Update (i j) (switch the side of each card from index i to index j, inclusive –-
		 * each card with face down becomes with face up; each card with face up becomes
		 * with face down)
		 * 
		 * Query(i) (output 0 if the i-th card is face down, otherwise output 1)
		 * 
		 * 
		 * Solution: 
		 * face-down --> represented with 0
		 * face-up   --> represented with 1
		 * 
		 * Construct fenwick tree with initial array which contains all zero.
		 * 
		 * </pre>
		 */
	}

}
