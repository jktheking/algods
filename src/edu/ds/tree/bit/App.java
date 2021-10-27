package edu.ds.tree.bit;

import java.util.Arrays;

public class App {

	public static void main(String[] args) {
		
		int[] input = {5, 7, 3, 2, 4, 6};
		
		BinaryIndexedTree<Integer> bit = new Fenwick1(input);
		System.out.println("input:"+Arrays.toString(input));
		System.out.println(bit);
		
		System.out.println(bit.rangeQuery(2, 5));
		
	}
}
