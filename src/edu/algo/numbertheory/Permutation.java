package edu.algo.numbertheory;


/**
 * https://introcs.cs.princeton.edu/java/23recursion/
 * 
 * 
 * TODO : kth permutation
 * */

public class Permutation {

	public static void main(String[] args) {

		String input = "abc";
		// permuteString("abc", "");
		// permuteArray("abc".toCharArray(), new char[3], 0);

		permuteArray1(input.toCharArray(), 0);
		// permuteArray2(input.toCharArray(), input.length());

	}

	/**
	 * print n! permutation of the characters of the string (in order)
	 * 
	 * Time Complexity : O(n*n!) Depth of the tree is n, so to reach any of the leaf
	 * node we need to traverse n steps. And there are n! leaf nodes.
	 * 
	 * Space Complexity : At each node of tree we are creating one output string. In
	 * a single path we have n nodes. Total paths = n! Space complexity of
	 * output-string = (n string)* (n! path) = O(n*n!) Similarly, Space complexity
	 * of input-string = O(n*n!)
	 * 
	 * Total Space Complexity = O(n*n!)
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	private static void permuteString(String input, String output) {

		if (input.isEmpty()) {
			System.out.println(output);
			return;
		}
		for (int i = 0; i < input.length(); i++) {
			permuteString(input.substring(0, i) + input.substring(i + 1), output + input.charAt(i));

		}

	}

	/**
	 * print n! permutation of the characters of array (in order)
	 * 
	 * Time Complexity : O(n*n!)
	 * 
	 * Space Complexity : extra space for output array O(n)
	 * 
	 */
	private static void permuteArray(char[] input, char[] output, int outPos) {

		if (outPos == output.length) {
			System.out.println(String.valueOf(output));
			return;
		}

		for (int i = 0; i < input.length; i++) {
			if (input[i] == Character.MIN_VALUE) {
				continue;
			}

			char ch = input[i];
			output[outPos] = ch;
			input[i] = Character.MIN_VALUE;

			permuteArray(input, output, outPos + 1);

			input[i] = ch;

		}
	}

	/**
	 * Strategy: permutation generation by swapping the first-element with other elements(left to right) 
	 * 
	 * 
	 * Time Complexity : O(n*n!) Depth of the tree is n, so to reach any of
	 *          the leaf node we need to traverse n steps. And there are n! leaf
	 *          nodes.
	 * 
	 *          Space Complexity : O(1)
	 * */
	private static void permuteArray1(char[] input, int position) {

		// Single element at last position is fixed in itself, so we can avoid self-swap
		// at last position by checking (position == input.length - 1) instead of
		// (position == input.length)
		if (position == input.length - 1) {
			System.out.println(String.valueOf(input));
			return;
		}

		for (int i = position; i < input.length; i++) {
			swap(input, position, i);
			permuteArray1(input, position + 1);
			swap(input, position, i);
		}

	}

	/**
	 * Strategy: permutation generation by swapping the last-element with other elements(right to left) 
	 * 
	 * 
	 * print n! permutation of the characters of array (not in order)
	 * 
	 * @param n is length of the input
	 * 
	 *          Time Complexity : O(n*n!) Depth of the tree is n, so to reach any of
	 *          the leaf node we need to traverse n steps. And there are n! leaf
	 *          nodes.
	 * 
	 *          Space Complexity : O(1)
	 * 
	 *          <pre>
	 * 
	 * Strategy: permutation generation by swapping the last-element with other elements(right to left)         
	 *          
	 *  input : [a,b,c]
	 *  
	 *    At p3 : we can place all 3 chars at p3
	 *    At p2 : we can place remaining 2 chars at p2
	 *    At p1 : we can place last remaining char at p1.
	 *    
	 *    
	 *  Swap-operation fixes the char at any given position. 
	 *  
	 *  Example fixing the position P3 in [a,b,c]
	 *   
	 *    --swap c with c
	 *    --swap c with b
	 *    --swap c with a
	 *  
	 * 
	 * Same strategy need to be called for fixing the position P1 and P2.
	 * 
	 * 
	 *          </pre>
	 * 
	 */
	private static void permuteArray2(char[] input, int n) {

		// when n reaches to 0th index of input array
		if (n == 1) {
			System.out.println(String.valueOf(input));
			return;
		}

		/**
		 * Here to fix any position say P3 in [a, b, c]: order of swap operation is
		 * 
		 * --swap c with a
		 * 
		 * --swap c with b
		 * 
		 * --swap c with c
		 * 
		 */
		for (int i = 0; i < n; i++) {

			swap(input, i, n - 1);
			permuteArray2(input, n - 1);

			// We are reverse swapping so that we can explore the other branch of the same
			// input, i.e. back-tracing

			swap(input, i, n - 1);
		}
	}

	private static void swap(char[] input, int i, int j) {

		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;

	}
	
	

}
