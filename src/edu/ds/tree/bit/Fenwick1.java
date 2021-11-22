package edu.ds.tree.bit;

import java.util.Arrays;

import edu.algo.algointro.BitwiseUtils;

/**
 * Binary Indexed tree implementation using one-based indexing
 * 
 * i' = i - BitwiseUtils.lowestOneBitMask(i) : calculates the next lower-index
 * till which the current index is responsible to hold the sum. Lower-index i'
 * does not contain upper-index i for storing the partial sum.
 *
 * i' = i + BitwiseUtils.lowestOneBitMask(i) : calculates the next upper-index
 * which is responsible to include the current element in its sum. Applying this
 * recursively we can keep finding the next upper-index which is also
 * responsible for 'i' which is like climbing the stairs.
 * 
 * 
 * Responsibilities of indexes in 1-based Fenwick tree
 * 
 * 1. odd-index is responsible only for itself. It means at odd index in fenwick
 * tree we will find the original element itself taking 1-based transformation
 * on input array.
 * 
 * 2. all indexes of type 2^n, are responsible for all the indexes upto this
 * point starting from 1. It means this index contains prefix sum of input-array
 * upto this point.
 * 
 * 3. all the even indexes are responsible for upto (lower-index + 1) that comes
 * by dropping the lastSetBit of this index.
 * 
 * 
 * Tree Traversal :
 * 
 * Downward Traversal : By dropping lowest-one-bit position. Next index in
 * traversal will always be even.
 * 
 *Upward Traversal : By adding 1 to lowest-one-bit position. Next index in traversal
 *will always be even.
 *
 *Note: Lower-odd-index is contained in immediate upper-even-index.
 * 
 * 
 */
public class Fenwick1 implements BIT<Integer> {

	int[] bit = null;

	Fenwick1(int[] input) {
		bit = new int[input.length + 1];
		buildTreeInNlogNTimeComplexity(input);
		// buildTreeInNTimeComplexity(input);

	}
	
	//Empty fenwick tree
	public Fenwick1(int length){
		bit = new int[length + 1];
	}
	

	/**
	 * TimeComplexity : n * (time-complexity-of-pointUpdate) = n*log(n).
	 * 
	 */
	private void buildTreeInNlogNTimeComplexity(int[] input) {
		for (int i = 0; i < input.length; i++) {
			pointUpdate(i, input[i]);
		}
	}

	/**
	 * 
	 * responsibility sum calculation using prefix sum.
	 * 
	 */
	private void buildTreeInNTimeComplexity(int[] input) {

		int[] prefixSum = new int[input.length + 1];

		for (int i = 1; i <= input.length; i++) {
            
			//for loop starts at 1
			int ithInput = input[i - 1];
			
			prefixSum[i] = prefixSum[i - 1] + ithInput;

			int lower_index = i - BitwiseUtils.lowestOneBitMask(i);
			bit[i] = prefixSum[i] - prefixSum[lower_index];
		}
	}

	/**
	 * 
	 * {@inheritDoc}
	 * 
	 * operation : "index + BitwiseUtils.lowestOneBitMask(index)" helps traverse
	 * upward on BIT-tree stairs. Topmost stair of BIT : last-index-of-BIT; This
	 * represents a transitive traversal that traverses up the indexes who are
	 * responsible for holding the given index.
	 * 
	 * 
	 * Time Complexity : log(n)
	 * 
	 * Explanation : Total number of bits required to represent a number is log(n).
	 * And since we are just traversing the bit positions of 'n' so it is log(n).
	 * 
	 * 
	 */
	@Override
	public void pointUpdate(int zeroBasedIndex, Integer delta) {
		// transform the zeroBasedIndex to OneBasedIndex i.e. 0th element of
		// actual-array is considered as 1th element
		int i = zeroBasedIndex + 1;
		while (i < bit.length) {
			bit[i] = bit[i] + delta;
			i = i + BitwiseUtils.lowestOneBitMask(i);
		}

	}

	/**
	 * range [4,7) : 4 is inclusive and 7 is exclusive
	 * 
	 */
	@Override
	public Integer rangeQuery(int ql, int qr) {
		/*
		 * qr-1 : because qr is exclusive
		 * 
		 * why ql-1 ? because bitSum(ql) gives prefixSum upto ql index, so if we do
		 * "bitSum(qr-1) - bitSum(ql)", ql will not be included in this range
		 * calculation.
		 */
		return bitSum(qr - 1) - bitSum(ql - 1);
	}
	
	
	@Override
	public Integer pointQuery(int index) {
		return bitSum(index);
	}


	/**
	 * This method takes actual zero-based input array index and zeroBasedIndex is
	 * inclusive
	 * 
	 * Traversing downward on bit-tree stairs. Bottom-most stair : 1th index of bit.
	 * 
	 * TimeComplexity : log(n) Explanation : Total number of bits required to
	 * represent a number is log(n). And since we are just traversing the bit
	 * positions of 'n' so it is log(n).
	 * 
	 */
	private int bitSum(int zeroBasedIndex) {
		// transform the zeroBasedIndex to OneBasedIndex i.e. 0th element of
		// actual-array is considered as 1th element
		int i = zeroBasedIndex + 1;

		int sum = 0;
		while (i > 0) {
			sum += bit[i];
			i = i - BitwiseUtils.lowestOneBitMask(i);
		}

		return sum;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Fenwick1 [bit=").append(Arrays.toString(bit)).append("]");
		return builder.toString();
	}

	


}
