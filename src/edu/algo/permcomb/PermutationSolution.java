package edu.algo.permcomb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * <pre>
 * https://introcs.cs.princeton.edu/java/23recursion/
 * 
 *Efficient Algorithm:
 * BR HEAPS ALGO : 
 * https://en.wikipedia.org/wiki/Heap%27s_algorithm
 * https://www.baeldung.com/java-array-permutations
 * 
 * 
 * 
 * SJT :
 * </pre>
 * 
 */
public class PermutationSolution implements PermutationQuestion {

	public static void main(String[] args) {

		// INSTANCE.printPermutationOfStringByFixingPos("abc","");

		// INSTANCE.printPermutationOfArrayByFixingPos("abc".toCharArray());

		// INSTANCE.printPermuationOfArrayByFixingInput("abc".toCharArray());

		// INSTANCE.printPermutationOfArrayUsingSwap1ByFixingPos("abc".toCharArray());

		// INSTANCE.printPermutationOfArrayUsingSwap2ByFixingPos("abc".toCharArray());

		// INSTANCE.printNextLexicographicPermutation("12345");
		// INSTANCE.printNextLexicographicPermutation("12354");
		// INSTANCE.printNextLexicographicPermutation("54321");

		// INSTANCE.printKthLexicographicPermutation("12345", 67);
		// INSTANCE.printKthLexicographicPermutation("12345", 1);

		// INSTANCE.printAllPermutationsInLexicographicOrder1("12345");
		// INSTANCE.printAllPermutationsInLexicographicOrder2("12345");
		// INSTANCE.printAllPermutationsInLexicographicOrder3("12345");

		// System.out.println(INSTANCE.getStairPathPermutation(7, 1, 2, 3));
		// INSTANCE.printStairPathPermutation(7, new int[] { 1, 2, 3 }, "");

		// INSTANCE.printPermutationOfItemInArrayByFixingInput(4, "ab");
		// INSTANCE.printPermutationOfItemIn2DArrayByFixingInput(2, 2, "ab");

		// INSTANCE.printPermutationOfItemInArrayByFixingPos(4, "aab");

		// INSTANCE.printPermutationOfItemIn2DArrayByFixingPosition(2,3,"aaabbc");

		// INSTANCE.printPermutationOfItemInArrayByHandlingDuplicateAndFixingInput(5,
		// "abaca");

		// INSTANCE.printPermutationOfItemIn2DElongatedArrayByFixingInput(2, 3, "abcd");
		// INSTANCE.printPermutationOfItemIn2DElongatedArrayByFixingPos(2, 3, "abcd");

		// INSTANCE.printNQueenPermutationByFixingPos(4);

		INSTANCE.printNQueenPermutationByFixingInput(4);

	}

	/**
	 * print n! permutation of the characters of the string (in order)
	 * 
	 * Time Complexity : @See "permuteArray1"
	 * 
	 * Space Complexity : At each node of tree we are creating one output string. In
	 * a single path we have n nodes. Total paths = n! Space complexity of
	 * output-string = (n string)* (n! path) = O(n*n!) Similarly, Space complexity
	 * of input-string = O(n*n!)
	 * 
	 * Total Space Complexity = O(n*n!)
	 * 
	 * <pre>
	 * Alogorithm approach: Fixing position and taking input as options at each level.
	 * First level  : At p1 position we are trying all the input options
	 * Second level : At p2 position we are trying all the remaining input options
	 * 
	 * </pre>
	 */
	@Override
	public void printPermutationOfStringByFixingPos(String input, String output) {

		if (input.isEmpty()) {
			System.out.println(output);
			return;
		}
		for (int i = 0; i < input.length(); i++) {
			printPermutationOfStringByFixingPos(input.substring(0, i) + input.substring(i + 1),
					output + input.charAt(i));

		}

	}

	@Override
	public void printPermutationOfArrayByFixingPos(char[] input) {

		printPermutationOfArrayByFixingPos(input, new char[input.length], 0);

	}

	/**
	 * print n! permutation of the characters of array (in order)
	 * 
	 * Time Complexity : @See "permuteArray1"
	 * 
	 * Space Complexity : extra space for output array O(n)
	 * 
	 * <pre>
	 * Alogorithm approach: Fixing position and taking input as options at each level.
	 * First level  : At p1 position we are trying all the input options
	 * Second level : At p2 position we are trying all the remaining input options
	 * 
	 * We visit next level via recusion invocation and since position is getting increased at
	 *  next level, so we pass position as input param.
	 * 
	 * Question: Input array is getting rest to original_state by backtracking, but why do we not
	 * resetting output array back to original_state ?
	 * Becuase output is getting printed at leaf-level on the path of euler_tour and before printing
	 * the next output euler_tour comes back to root of the respective branch where we are overriding 
	 * the required position in output array.
	 * </pre>
	 * 
	 * 
	 * 
	 * 
	 */
	private void printPermutationOfArrayByFixingPos(char[] input, char[] output, int posToFix) {

		if (posToFix == output.length) {
			System.out.println(String.valueOf(output));
			return;
		}

		for (int i = 0; i < input.length; i++) {

			if (input[i] == Character.MIN_VALUE) {
				continue;
			}

			char ch = input[i];
			output[posToFix] = ch;
			// on next level, to get the remaining input
			input[i] = Character.MIN_VALUE;
			printPermutationOfArrayByFixingPos(input, output, posToFix + 1);
			// to try all the input options
			input[i] = ch;

		}
	}

	@Override
	public void printPermutationOfArrayUsingSwap1ByFixingPos(char[] input) {

		printPermutationOfArrayUsingSwap1ByFixingPos(input, 0);
	}

	/**
	 * Strategy: permutation generation by swapping the first-element with other
	 * elements(left to right)
	 * 
	 * 
	 * Approximate Time Complexity calculation : O(n*n!) Depth of the tree is n, so
	 * to reach any of the leaf node we need to traverse n steps. And there are n!
	 * leaf nodes.
	 * 
	 * Space Complexity : O(1)
	 * 
	 * 
	 * Accurate Time Complexity calculation : Let's assume each recursive method
	 * invocation represents 1 unit of task. Then total number of method invocation
	 * represents the time-complexity.
	 * 
	 * Total number of leaf-nodes = n! = n!/1; Total number of nodes at 1 level
	 * below leaf-nodes = n!/2 = n!/1*2 Total number of nodes at 2 level below
	 * leaf-nodes = (n!/2)/3 = n!/1*2*3 Total number of nodes at 3 level below
	 * leaf-nodes = ((n!/2)/3)4 = n!/1*2*3*4
	 * 
	 * So, total number of nodes in the tree = n! + n!/1*2 + n!/1*2*3 + n!/1*2*3*4 +
	 * .. + n!/n! = n!(1 + 1/1*2 + 1/1*2*3 + 1/1*2*3*4 + .. +1/n!) =~ n!(1+1) = 2n!
	 * 
	 * 
	 * <pre>
	 * Alogorithm approach: Fixing position and taking input as options at each level.
	 * First level  : At p1 position we are trying all the input options
	 * Second level : At p2 position we are trying all the remaining input options
	 * </pre>
	 * 
	 */
	private void printPermutationOfArrayUsingSwap1ByFixingPos(char[] input, int posToFix) {

		// Single element at last position is fixed in itself, so we can avoid self-swap
		// at last position by checking (position == input.length - 1) instead of
		// (position == input.length)
		if (posToFix == input.length - 1) {
			System.out.println(String.valueOf(input));
			return;
		}

		for (int i = posToFix; i < input.length; i++) {
			swap(input, posToFix, i);
			printPermutationOfArrayUsingSwap1ByFixingPos(input, posToFix + 1);
			swap(input, posToFix, i);
		}

	}

	@Override
	public void printPermutationOfArrayUsingSwap2ByFixingPos(char[] input) {
		printPermutationOfArrayUsingSwap2ByFixingPos(input, input.length);
	}

	/**
	 * Strategy: permutation generation by swapping the last-element with other
	 * elements(right to left)
	 * 
	 * 
	 * print n! permutation of the characters of array (not in order)
	 * 
	 * @param posToFix is length of the input
	 * 
	 *                 Time Complexity : O(n*n!) Depth of the tree is n, so to reach
	 *                 any of the leaf node we need to traverse n steps. And there
	 *                 are n! leaf nodes.
	 * 
	 *                 Space Complexity : O(1)
	 * 
	 *                 <pre>
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
	 *                 </pre>
	 * 
	 */
	private void printPermutationOfArrayUsingSwap2ByFixingPos(char[] input, int posToFix) {

		// when n reaches to 0th index of input array
		if (posToFix == 1) {
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
		for (int i = 0; i < posToFix; i++) {

			swap(input, i, posToFix - 1);
			printPermutationOfArrayUsingSwap2ByFixingPos(input, posToFix - 1);

			// We are reverse swapping so that we can explore the other branch of the same
			// input, i.e. back-tracing

			swap(input, i, posToFix - 1);
		}
	}

	/**
	 * Permutation cyclic notation and even odd permutation:
	 * https://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm
	 * https://introcs.cs.princeton.edu/java/23recursion/JohnsonTrotter.java.html
	 * 
	 * 
	 * 
	 * Even-Odd permutation: <br/>
	 * -------------------------
	 *
	 * <pre>
	 * 1 2 3 4 5 Original
	 *
	 * 3 4 2 1 5 A Permutation
	 *
	 * Cycle: 1-->3-->2-->4-->1
	 * Cycle: 5
	 *
	 * Cyclical Notation: (1 3 2 4) (5)
	 *
	 * Cyclical Notation using Transposition: (1 3) (3 2) (2 4)
	 *
	 * Explanation: We get (1 3) (3 2) (2 4) (5), but we can omit 5 as it is at same position.
	 *
	 * If cyclical notation using transposition contains even groups then it
	 * is called even permutation otherwise odd permutation.
	 *
	 * Johnson trotter algorithm can be used to generate even-odd permutation. For more details
	 * refer {@link https://en.wikipedia.org/wiki/Steinhaus%E2%80%93Johnson%E2%80%93Trotter_algorithm}
	 *
	 * </pre>
	 *
	 * SJT Algorithm: <br/>
	 * -----------------
	 *
	 * It is a minimal change algorithm wherein any two successive permutations are
	 * obtained from each other by a single adjacent transposition (adjacent swaps).
	 *
	 * Time Complexity: It can be implemented to run in O(n!)
	 *
	 * Jargons in SJT Algorithm: <br/>
	 * -------------------------------
	 *
	 * Directed Integer: Each element has associated direction, left or right.
	 *
	 * Mobile: A directed integer is said to be mobile if it is greater than its
	 * immediate neighbor in the direction it is pointing to
	 *
	 * Pseudo Code: <br/>
	 * -----------------
	 *
	 * <pre>
	 * While permutation p has a mobile element:
	 * --- 1) Find the largest mobile element 'k'
	 * --- 2) Swap 'k' with the adjacent element it is pointing to.
	 * --- 3) Reverse direction of all elements greater than 'k'
	 * --- 4) Post reverse the input array represents a new permutation.
	 * </pre>
	 *
	 *
	 */
	@Override
	public void printPermutationUsingSJT1(int[] permutation) {

		final int[] direction = new int[permutation.length];
		System.out.println(Arrays.toString(permutation));
		// we are assuming permutation array contains all the elements in increasing
		// order.
		Arrays.fill(direction, -1);

		int largestMobileIdx = findLargestMobileElementIdx(permutation, direction);
		// until we have mobile element
		while (largestMobileIdx != -1) {
			int directedNeighbourIdx = largestMobileIdx + direction[largestMobileIdx];

			int largestMobileElement = permutation[largestMobileIdx];

			swap(permutation, direction, largestMobileIdx, directedNeighbourIdx);

			reverseDirection(permutation, direction, largestMobileElement);

			System.out.println(Arrays.toString(permutation));

			largestMobileIdx = findLargestMobileElementIdx(permutation, direction);
		}

	}

	@Override
	public void printPermutationUsingSJT2(int[] permutation) {
		// TODO: first need to understand inverse permutation,(google, youtube)
		// TODO:
		// https://introcs.cs.princeton.edu/java/23recursion/JohnsonTrotter.java.html

	}

	private int findLargestMobileElementIdx(int[] permutation, int[] direction) {

		int largestMobileIdx = -1;

		for (int i = 0; i < permutation.length; i++) {

			int directedNeighbourIdx = direction[i] + i;
			// check if the directedNeighbourIdx is valid or not
			if (directedNeighbourIdx < 0 || directedNeighbourIdx >= permutation.length) {
				continue;
			}

			// mobile element
			if (permutation[i] > permutation[directedNeighbourIdx]) {
				// largest mobile index
				if (largestMobileIdx == -1 || permutation[i] > permutation[largestMobileIdx]) {
					largestMobileIdx = i;
				}
			}

		}

		return largestMobileIdx;

	}

	private void swap(int[] permutation, int[] direction, int i, int j) {
		int temp = permutation[i];
		permutation[i] = permutation[j];
		permutation[j] = temp;

		temp = direction[i];
		direction[i] = direction[j];
		direction[j] = temp;

	}

	private void reverseDirection(int[] permutation, int[] direction, int largestMobileElement) {
		for (int i = 0; i < permutation.length; i++) {
			if (permutation[i] > largestMobileElement)
				direction[i] = -direction[i];

		}

	}

	/**
	 * <pre>
	 * 
	 *Efficient Algorithms:
	 *1. BR HEAPS ALGO : 
	 * https://en.wikipedia.org/wiki/Heap%27s_algorithm
	 * https://www.baeldung.com/java-array-permutations
	 * 
	 *2. 2SJT :
	 * </pre>
	 * 
	 * TODO : BR heap sequential and parallel
	 * 
	 */
	@Override
	public void printPerumutationUsingBRHeap1(int[] permutation) {
		// TODO Auto-generated method stub

	}

	@Override
	public void printPerumutationUsingBRHeap2(int[] permutation) {
		// TODO Auto-generated method stub

	}

	/**
	 * <pre>
	 *  Question:
	 * Generate permutations with only non repeatable adjacent swaps allowed.
	 * 
	 * Given a string on length N. You can swap only the adjacent elements and each
	 * element can be swapped at most once. Given these rules, find the no of
	 * permutations of the string that can be generated after performing the swaps
	 * as mentioned.
	 * 
	 *  Allowed rules to generate next permutation:
	 *  1. multiple non-duplicate adjacent swaps allowed
	 *  
	 *Solution:
	 * Step1 : generate all the pairs
	 * Step2: generate all the 2^n sets of pair i.e powerset.  
	 *    A:  If generated set pairs are non-overlapping then print the permutation.
	 *    B: If generated set contains any overlapping pair then discard the permutation.
	 *       Because it does not represent adjacent swaps.
	 * 
	 * </pre>
	 */
	@Override
	public void permutationsWithAdjacentNonRepeatableSwaps1(String input) {

		List<String> pairs = IntStream.range(1, input.length())
				.mapToObj(i -> "" + input.charAt(i - 1) + input.charAt(i)).collect(Collectors.toList());
		System.out.println("pairs:" + pairs);
		traversePowerSet(input, pairs, new ArrayList<>());

	}

	/**
	 * <pre>
	 *   
	 *Solution: 
	 *Include Step: 
	 * We will pick two non-overlapping adjacent elements at a time as swap pair.
	 *
	 * Exclude Step: exclude single element. 
	 * In 12345, we can create swap pair 23 by excluding 1 So we should not exclude in pair.
	 *
	 * </pre>
	 * 
	 * @see nonRepetableAdjSwapPerm.pdf
	 */
	@Override
	public void permutationsWithAdjacentNonRepeatableSwaps2(String input) {
		nonDupAdjSwapsPermUsingIncExc(input.toCharArray(), 0);

	}

	private void nonDupAdjSwapsPermUsingIncExc(char[] input, int index) {

		/**
		 * why do we need the condition "(index + 1) >= input.length" ? If during the
		 * include-exclude counting, we left with just single element, then we need to
		 * terminate the include-exclude process because single element cannot form a
		 * valid swap pair.
		 * 
		 * 
		 **/

		if (index >= input.length || (index + 1) >= input.length) {
			// input is exhausted, print the output
			System.out.println(String.valueOf(input));
			return;
		}

		/**
		 * include step
		 * 
		 */
		swap(input, index, index + 1);
		nonDupAdjSwapsPermUsingIncExc(input, index + 2);
		// backtrack because we need the same input for two branches of the
		// recursion-tree.
		swap(input, index, index + 1);

		/**
		 * exclude step
		 * 
		 */

		nonDupAdjSwapsPermUsingIncExc(input, index + 1);
	}

	/**
	 * Here instead of Pascal Identity(i.e. Include-Exclude) will use Pascal
	 * Identity Expansion of Exclude terms into Include Terms.
	 * 
	 * This is more efficient than include exclude based solution. i.e. question1B
	 */
	@Override
	public void permutationsWithAdjacentNonRepeatableSwaps3(String input) {
		nonDupAdjSwapsPermUsingExcludeExpansion(input.toCharArray(), 0);

	}

	private void nonDupAdjSwapsPermUsingExcludeExpansion(char[] input, int index) {
		System.out.println(String.valueOf(input));
		for (int i = index; i < input.length && (i + 1) < input.length; i++) {
			swap(input, i, i + 1);
			nonDupAdjSwapsPermUsingExcludeExpansion(input, i + 2);
			swap(input, i, i + 1);
		}

	}

	@Override
	public void printNextLexicographicPermutation(String inputStr) {
		char[] input = inputStr.toCharArray();
		getNextLexicographicPermutation(input);
		System.out.println(String.valueOf(input));

	}

	@Override
	public void printKthLexicographicPermutation(String inputStr, int k) {

		List<Character> charList = inputStr.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());

		System.out.println(String.valueOf(getKthLexicographicPermutation(charList, k)));

	}

	/**
	 * <pre>
	 * Time Complexity : Time to calculate Factorial + Time to run for loop on output array * Time for input.remove()
	 *                 :    O(n)                     +            O(n) * O(n)                     
	 *                 :    O(n) + O(n^2)
	 *                 :    O(n^2)
	 * 
	 * </pre>
	 */
	private char[] getKthLexicographicPermutation(List<Character> input, int k) {
		char[] output = new char[input.size()];

		int blockSize = factorial(input.size() - 1);

		k = k - 1;

		for (int i = 0; i < output.length; i++) {
			int blockNumber = k / blockSize;

			output[i] = input.get(blockNumber);
			input.remove(blockNumber);
			if (input.size() == 0)
				break;

			k = k % blockSize;
			blockSize = blockSize / input.size();
		}
		return output;
	}

	private int factorial(int num) {
		int factorial = 1;
		for (int i = num; i > 1; i--) {
			factorial *= i;
		}
		return factorial;
	}

	/**
	 * <pre>
	 * Question: print all permutations in lexicographic order.
	 * 
	 * TimeComplexity : time complexity of Permutation.permuteString
	 *                : n*n!
	 * </pre>
	 */
	@Override
	public void printAllPermutationsInLexicographicOrder1(String inputString) {
		printPermutationOfStringByFixingPos(inputString, "");

	}

	/**
	 * <pre>
	 * Question: print all permutations in lexicographic order.
	 * 
	 * Time Complexity: n! * time complexity of getKthLexicographicPermutation
	 * 
	 *                : n!*n^2
	 * </pre>
	 */
	@Override
	public void printAllPermutationsInLexicographicOrder2(String inputStr) {

		List<Character> charList = inputStr.chars().mapToObj(ch -> (char) ch).collect(Collectors.toList());
		int fact = factorial(inputStr.length());

		for (int i = 1; i <= fact; i++) {
			System.out.println(String.valueOf(getKthLexicographicPermutation(new ArrayList<>(charList), i)));

		}
	}

	/**
	 * <pre>
	 * Question: print all permutations in lexicographic order.
	 * 
	 * Time Complexity :  n! * (time to get each next_lexicographic_permutation)
	 *                 :  n! * n
	 *                 :  O(n*n!)
	 * </pre>
	 *
	 * @param inputStr : It should be in sorted order like 1234/abcd
	 * 
	 */
	@Override
	public void printAllPermutationsInLexicographicOrder3(String inputStr) {

		char[] input = inputStr.toCharArray();

		final String LARGEST_PERM = String.valueOf(reverseCharArray(inputStr.toCharArray(), 0, inputStr.length() - 1));

		String perm = inputStr;
		System.out.println(perm);
		while (!perm.equals(LARGEST_PERM)) {
			getNextLexicographicPermutation(input);
			perm = String.valueOf(input);
			System.out.println(perm);
		}

	}

	/**
	 * next_lexicographic_permutation
	 * 
	 * <pre>
	 * Step1 : Identify Pivot Traverse the array from right side and stop at the
	 * first element which is not in ascending order. Example : 2 is pivot in
	 * 0125330 
	 * Question: Why we are traversing in ascending order from right side ?
	 * : Because of PROPERTY_1. 
	 * 
	 * Step2: Find Successor of Pivot Find the next greater
	 * digit than the Pivot among digits present in right-side of the pivot.
	 * Note : Since right side of the pivot contains all the digits in sorted order, so we
	 * can apply binary serach.
	 * 
	 * Step 3: Swap the Pivot with Successor. Because we want just next greater
	 * element which is only possiple by replacing pivot with successor.
	 * 
	 * Step4: Now reverse sort(descending order from right side) all the elements
	 * lying in right-side of pivot position. 
	 * Note : Since right side of the pivot position is already in sorted order, 
	 * so just need to reverse the element to get them in descending order.
	 * Question: Why do we sort elements lying right-side of pivot position in descending order ? : Because of PROPERTY_1.
	 * </pre>
	 * 
	 * <pre>
	 * TimeComplexity : STEP1 +  STEP2   + STEP3 + STEP4
	 *                : O(n)  +  O(logn) + O(1)  + O(n) 
	 *                : O(n)
	 * </pre>
	 */
	private void getNextLexicographicPermutation(char[] input) {

		// STEP1: find pivot
		int pivotIndex = Integer.MIN_VALUE;
		for (int i = input.length - 1; i > 0; i--) {
			if (input[i - 1] < input[i]) {
				pivotIndex = i - 1;
				break;
			}
		}

		// pivot not found, means input itself is the largest element using given
		// digits.
		if (pivotIndex == Integer.MIN_VALUE) {
			return;
		}

		// STEP2: find successor using bisect right search
		char targetValue = input[pivotIndex];
		// we are increasing the targetValue to 1, because we want to find the insertion
		// position of successor value
		targetValue += 1;

		int insertionIndexOfTargetValue = bisectRightInDescArray(input, targetValue, pivotIndex + 1, input.length);
		int pivotSuccessorIndex = insertionIndexOfTargetValue - 1;

		// STEP3 :swap pivot with its successor lying in right side
		swap(input, pivotIndex, pivotSuccessorIndex);

		// STEP4: reverse the array element between (pivotIndex, endIndex].
		reverseCharArray(input, pivotIndex + 1, input.length - 1);
	}

	/**
	 * gives rightmost insertion index of targetValue.
	 * 
	 */
	private int bisectRightInDescArray(char[] input, char targetValue, int left, int right) {

		while (left < right) {

			int mid = (left + right) / 2;

			if (input[mid] < targetValue) {

				right = mid;

			} else if (input[mid] == targetValue) {

				left = mid + 1;
			} else {
				left = mid + 1;
			}
		}

		return left;

	}

	public char[] reverseCharArray(char[] input, int startIndex, int endIndex) {

		int diff = endIndex - startIndex;
		// diff-1, avoid self-swap if any.
		for (int i = 0; i <= (diff - 1) >> 1; i++) {

			char temp = input[startIndex + i];
			input[startIndex + i] = input[endIndex - i];
			input[endIndex - i] = temp;
		}

		return input;
	}

	private void traversePowerSet(String originalStr, List<String> inputPairs, List<String> outputPairs) {

		if (inputPairs.isEmpty()) {
			char[] input = originalStr.toCharArray();
			for (String pair : outputPairs) {
				int i = originalStr.indexOf(pair);
				swap(input, i, i + 1);

			}
			System.out.println(
					"input:" + originalStr + " swap pairs:" + outputPairs + " output:" + String.valueOf(input));

			return;

		}

		// by including the pair
		List<String> clonedInputPairs = new ArrayList<>(inputPairs);
		String pair = clonedInputPairs.remove(0);
		if (!containsOveralappingPair(outputPairs, pair)) {
			List<String> clonedOutputPairs = new ArrayList<>(outputPairs);
			clonedOutputPairs.add(pair);
			traversePowerSet(originalStr, clonedInputPairs, clonedOutputPairs);
		}

		// by excluding
		traversePowerSet(originalStr, clonedInputPairs, outputPairs);

	}

	private void swap(char[] input, int i, int j) {
		char temp = input[i];
		input[i] = input[j];
		input[j] = temp;
	}

	private boolean containsOveralappingPair(List<String> outputPairs, String pair) {
		for (String e : outputPairs) {
			if (e.charAt(1) == pair.charAt(0))
				return true;

		}
		return false;
	}

	/**
	 * Find Permutation of given numbers whose sum is equal to a target_value.
	 * 
	 * Example problem statement : Find all the possible stair paths with given
	 * total number of stairs step and allowed step size. e.g. Total number of setps
	 * = 7 and at a time we can take step of size 1 or 2 or 3.
	 * 
	 * <pre>
	 * HYPOTHEISIS : printStairPathPermutation(7, [1,2,3]) => print total paths 'n'
	 * 
	 * SUBSTITUTION: 
	 * printStairPathPermutation(6, [1,2,3]) => provide all the subPaths that starts
	 * with 1
	 * 
	 * printStairPathPermutation(5, [1,2,3]) => provide all the subPaths that starts
	 * with 2
	 * 
	 * printStairPathPermutation(4, [1,2,3]) => provide all the subPaths that starts
	 * with 3
	 * 
	 * INDUCTION STRATEGY : prepend 1, 2, 3 to  paths returned by respective substitution methods.
	 * 
	 * 
	 * </pre>
	 * 
	 * TODO: Time complexity :
	 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
	 */
	@Override
	public List<String> getStairPathPermutation(int targetValue, int... allowedSteps) {
		// targetValue becomes negative for invalid path
		if (targetValue < 0) {
			return List.of();

		}
		// targetValue becomes 0 for valid end of path
		if (targetValue == 0) {
			return List.of("");
		}

		List<String> paths = new ArrayList<>();
		for (int i = 0; i < allowedSteps.length; i++) {
			List<String> ipaths = getStairPathPermutation(targetValue - allowedSteps[i], allowedSteps);

			for (String path : ipaths) {
				paths.add(allowedSteps[i] + path);
			}
		}
		return paths;
	}

	/**
	 * <pre>
	 * 
	 * STRATEGY: We pick all the options i.e. allowedSteps as branch of recursive_tree at each level 
	 * and maintain the following two : 
	 * 1. solution path so far by appending the option
	 * 2. remaing targetValue ie.(targetValue - option)
	 * 
	 * HYPOTHEISIS : printStairPathPermutation(7,[1,2,3], "") : provide all the
	 * allowed paths
	 * 
	 * SUBSTITUTION: 
	 * printStairPathPermutation(6,[1,2,3], "1") :  add option "1" to solution path and 
	 * then explore the remaining using substitution.
	 * 
	 * printStairPathPermutation(5,[1,2,3], "2") : add option "2" to solution path and 
	 * then explore the remaining using substitution.
	 * 
	 *printStairPathPermutation(4,[1,2,3], "3") :add option "3" to solution path and 
	 * then explore the remaining using substitution.
	 * 
	 * INDUCTION: 1st part of any path is appended to path_so_far by main code and remaining part
	 * is appended to path_so_far by  substitution step.
	 * </pre>
	 * 
	 * TODO: Time complexity :
	 * https://www.geeksforgeeks.org/count-ways-reach-nth-stair/
	 * 
	 * @param path represents the path formed so far.
	 */
	@Override
	public void printStairPathPermutation(int targetValue, int[] allowedSteps, String path) {
		if (targetValue < 0)
			return;
		if (targetValue == 0) {
			System.out.println(path);
			return;
		}
		for (int i = 0; i < allowedSteps.length; i++) {
			printStairPathPermutation(targetValue - allowedSteps[i], allowedSteps, path + allowedSteps[i]);
		}
	}

	@Override
	public void printPermuationOfArrayByFixingInput(char[] input) {

		printPermuationsOfArrayByFixingInput(input, 0, new char[input.length]);

	}

	/**
	 * <pre>
	 * Alogorithm approach: Fixing input and taking position as options at each level.
	 * First level  : for 0th index input, we try all the positions as option
	 * Second level : for 1th index input, we try all the remaining positions as option
	 * 
	 * We visit next level via recusion invocation and since input  is getting changed at
	 *  next level, so we pass input_index as method param.
	 *  
	 * How to track the remaining position for next level :
	 * 1.Either place postions in list, and remove the used positions.
	 * 2.Use the output-array as postions tracker, and at next level only use the 
	 * un-occupied positions.
	 * </pre>
	 * 
	 */
	private void printPermuationsOfArrayByFixingInput(char[] input, int idx, char[] output) {
		if (idx == input.length) {
			System.out.println(String.valueOf(output));
			return;
		}
		for (int pos = 0; pos < output.length; pos++) {
			// only use the un-occupied positions at next level
			if (output[pos] == Character.MIN_VALUE) {

				output[pos] = input[idx];
				printPermuationsOfArrayByFixingInput(input, idx + 1, output);
				// before trying next option, we need to remove the previous option
				output[pos] = Character.MIN_VALUE;

			}

		}
	}

	/**
	 * <pre>
	 * print premutation of 'r'  distinct items by arranging them on 'n' positions.
	 * where r<=n
	 * 
	 * 
	 * WHEN item.length() < postionCount THEN some positions will be empty in output array
	 * INFERENCE : 'empty' is acting as special input.
	 *  
	 * emptyCount = positionCount - item.length()
	 * 
	 * 
	 * </pre>
	 */
	@Override
	public void printPermutationOfItemInArrayByFixingPos(int positionCount, String item) {

		int emptyCount = positionCount - item.length();
		Map<Character, Integer> input = new LinkedHashMap<>();
		input.put('_', emptyCount);
		for (char c : item.toCharArray()) {
			input.computeIfPresent(c, (k, v) -> v + 1);
			input.computeIfAbsent(c, k -> 1);

		}

		printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(input, new char[positionCount], 0);
	}

	/**
	 * 
	 * print premutation of 'r' distinct items by arranging them on 'n' positions.
	 * 
	 * @param item          : item String represents r chars
	 * @param positionCount : represents 'n' positions
	 * 
	 */
	@Override
	public void printPermutationOfItemInArrayByFixingInput(int positionCount, String item) {

		char[] output = new char[positionCount];
		Arrays.fill(output, '_');
		printPermutationofItemInArrayByFixingInput(item, output);

	}

	private void printPermutationofItemInArrayByFixingInput(String input, char[] output) {

		if (input.isEmpty()) {
			System.out.println(String.valueOf(output));
			return;
		}
		for (int pos = 0; pos < output.length; pos++) {
			if (output[pos] == '_') {
				output[pos] = input.charAt(0);
				String remainingInput = input.substring(1);
				printPermutationofItemInArrayByFixingInput(remainingInput, output);
				output[pos] = '_';
			}
		}
	}

	/**
	 * 
	 * print premutation of 'r' distinct items by arranging them in 2D-array.
	 * 
	 * @param item : item String represents r chars
	 * @param rows : row count
	 * @parm cols : column count
	 * 
	 */
	@Override
	public void printPermutationOfItemIn2DArrayByFixingInput(int rows, int cols, String item) {

		char[][] output = new char[rows][cols];
		for (int i = 0; i < rows; i++) {
			Arrays.fill(output[i], '_');

		}

		printPermutationofItemIn2DArrayByFixingInput(item, output);
	}

	private void printPermutationofItemIn2DArrayByFixingInput(String input, char[][] output) {
		if (input.isEmpty()) {
			printMatrix(output);
			return;
		}
		for (int row = 0; row < output.length; row++) {
			for (int col = 0; col < output[0].length; col++) {
				// at next level we only need to try with un-occupied positions
				if (output[row][col] == '_') {
					output[row][col] = input.charAt(0);
					String remainingInput = input.substring(1);
					printPermutationofItemIn2DArrayByFixingInput(remainingInput, output);
					output[row][col] = '_';
				}
			}
		}

	}

	private void printMatrix(char[][] matrix) {
		System.out.println();
		for (int row = 0; row < matrix.length; row++) {
			System.out.println(Arrays.toString(matrix[row]));
		}

	}

	@Override
	public void printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(int positionCount, String item) {

		int emptyCount = positionCount - item.length();
		Map<Character, Integer> input = new LinkedHashMap<>();
		input.put('_', emptyCount);
		for (char c : item.toCharArray()) {
			input.computeIfPresent(c, (k, v) -> v + 1);
			input.computeIfAbsent(c, k -> 1);

		}

		printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(input, new char[positionCount], 0);
	}

	/**
	 * <pre>
	 *
	 *NOTE 1:
	 * This method will give us the combinations, when we try to print permutation 
	 * of identical items.
	 * 
	 * NOTE 2: input is tried as options on each level of tree. We must try the
	 * duplicate items as single option on any level of tree.
	 * 
	 *This method will also handle duplicates in input like : aabbcc
	 * 
	 * 
	 * </pre>
	 * 
	 */

	private void printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(Map<Character, Integer> inputFreqMap,
			char[] output, int posToFix) {
		if (posToFix == output.length) {
			System.out.println(String.valueOf(output));
			return;
		}
		for (Map.Entry<Character, Integer> entry : inputFreqMap.entrySet()) {
			if (entry.getValue() > 0) {
				output[posToFix] = entry.getKey();
				entry.setValue(entry.getValue() - 1);
				printPermutationOfItemInArrayByHandlingDuplicateAndFixingPos(inputFreqMap, output, posToFix + 1);
				entry.setValue(entry.getValue() + 1);
			}
		}
	}

	/**
	 * Since input_count is less than position_count, so we have to consider empty
	 * as special input.
	 * 
	 */
	@Override
	public void printPermutationOfItemIn2DArrayByFixingPosition(int rows, int cols, String item) {

		int emptyCount = rows * cols - item.length();

		Map<Character, Integer> inputFreqMap = new LinkedHashMap<>();
		inputFreqMap.put('_', emptyCount);
		for (char c : item.toCharArray()) {
			inputFreqMap.computeIfPresent(c, (k, v) -> v + 1);
			inputFreqMap.computeIfAbsent(c, k -> 1);

		}

		printPermutationOfItemIn2DArrayByHandlingDuplicateAndFixingPosition(inputFreqMap, new char[rows][cols], 0, 0);
	}

	private void printPermutationOfItemIn2DArrayByHandlingDuplicateAndFixingPosition(
			Map<Character, Integer> inputFreqMap, char[][] output, int rowPosToFix, int colPosToFix) {

		if (rowPosToFix == output.length - 1 && colPosToFix == output[0].length) {
			printMatrix(output);
			return;
		}

		if (colPosToFix == output[0].length) {
			rowPosToFix++;
			colPosToFix = 0;
		}

		for (Map.Entry<Character, Integer> entry : inputFreqMap.entrySet()) {
			if (entry.getValue() > 0) {
				output[rowPosToFix][colPosToFix] = entry.getKey();
				entry.setValue(entry.getValue() - 1);
				printPermutationOfItemIn2DArrayByHandlingDuplicateAndFixingPosition(inputFreqMap, output, rowPosToFix,
						colPosToFix + 1);
				// because at next level we want the remainingInput
				entry.setValue(entry.getValue() + 1);
			}
		}

	}

	@Override
	public void printPermutationOfItemInArrayByHandlingDuplicateAndFixingInput(int positionCount, String item) {

		char[] output = new char[positionCount];
		Arrays.fill(output, '_');

		printPermutationOfItemInArrayByHandlingDuplicateAndFixingInput(item, new HashMap<>(), output);
	}

	/**
	 * Implementation Note: If we are fixing duplicate character on any next_level,
	 * then the duplicate character can only occupy next_empty_positions of
	 * previously occupied duplicate character. It cannot occupy
	 * previous_empty_positions.
	 * 
	 */
	private void printPermutationOfItemInArrayByHandlingDuplicateAndFixingInput(String input,
			Map<Character, Integer> inputPreviousOccurrencePos, char[] output) {
		// when reached the leaf-node
		if (input.isEmpty()) {
			System.out.println(String.valueOf(output));
			return;
		}

		int pos = 0;
		int backTrackPreviousOccurrencePos = Integer.MIN_VALUE;
		char currentCh = input.charAt(0);
		if (inputPreviousOccurrencePos.containsKey(currentCh)) {
			backTrackPreviousOccurrencePos = inputPreviousOccurrencePos.get(currentCh);
			pos = inputPreviousOccurrencePos.get(currentCh) + 1;
		}

		for (; pos < output.length; pos++) {
			if (output[pos] == '_') {

				output[pos] = currentCh;
				inputPreviousOccurrencePos.put(currentCh, pos);
				printPermutationOfItemInArrayByHandlingDuplicateAndFixingInput(input.substring(1),
						inputPreviousOccurrencePos, output);
				// backtrack to previous state, so that we can try next position
				if (backTrackPreviousOccurrencePos == Integer.MIN_VALUE) {
					inputPreviousOccurrencePos.remove(currentCh);
				} else {
					inputPreviousOccurrencePos.put(currentCh, backTrackPreviousOccurrencePos);
				}

				output[pos] = '_';
			}

		}
	}

	@Override
	public void printPermutationOfItemIn2DElongatedArrayByFixingInput(int rows, int cols, String item) {

		char[][] output = new char[rows][cols];
		for (char[] c : output) {
			Arrays.fill(c, '_');
		}

		printPermutationOfItemIn2DElongatedArrayByFixingInput(item, output);
	}

	private void printPermutationOfItemIn2DElongatedArrayByFixingInput(String input, char[][] output) {
		if (input.isEmpty()) {
			printMatrix(output);
			return;
		}

		for (int pos = 0; pos < output.length * output[0].length; pos++) {
			int row = pos / output[0].length;
			int col = pos % output[0].length;
			if (output[row][col] == '_') {
				output[row][col] = input.charAt(0);
				printPermutationOfItemIn2DElongatedArrayByFixingInput(input.substring(1), output);
				output[row][col] = '_';
			}
		}

	}

	@Override
	public void printPermutationOfItemIn2DElongatedArrayByFixingPos(int rows, int cols, String item) {

		Map<Character, Integer> inputFreqMap = new LinkedHashMap<>();
		int emptyCount = rows * cols - item.length();

		inputFreqMap.put('_', emptyCount);
		for (char c : item.toCharArray()) {
			inputFreqMap.computeIfPresent(c, (k, v) -> v + 1);
			inputFreqMap.computeIfAbsent(c, k -> 1);

		}

		printPermutationOfItemIn2DElongatedArrayByFixingPos(inputFreqMap, new char[rows][cols], 0);

	}

	/**
	 * Why do we use "Map<Character, Integer> inputFreqMap" though all the inputs
	 * are distinct in 'item' String ? Because position_count is greater than
	 * input_count means we need to add empty as special inputs to match the
	 * input_count with position_count.
	 * 
	 */
	private void printPermutationOfItemIn2DElongatedArrayByFixingPos(Map<Character, Integer> inputFreqMap,
			char[][] output, int posToFix) {
		if (posToFix == output.length * output[0].length) {
			printMatrix(output);
			return;
		}

		for (Map.Entry<Character, Integer> entry : inputFreqMap.entrySet()) {

			if (entry.getValue() > 0) {
				int row = posToFix / output[0].length;
				int col = posToFix % output[0].length;

				output[row][col] = entry.getKey();
				inputFreqMap.put(entry.getKey(), entry.getValue() - 1);
				printPermutationOfItemIn2DElongatedArrayByFixingPos(inputFreqMap, output, posToFix + 1);
				inputFreqMap.put(entry.getKey(), entry.getValue() + 1);
				output[row][col] = Character.MIN_VALUE;
			}

		}

	}

	@Override
	public void printNQueenPermutationByFixingPos(int n) {
		int emptyCount = n * n - n;
		Map<Character, Integer> inputFreqMap = new LinkedHashMap<>();
		inputFreqMap.put('_', emptyCount);
		for (int i = 0; i < n; i++) {
			inputFreqMap.put((char) ('1' + i), 1);
		}
		printNQueenPermutationByFixingPos(inputFreqMap, new char[n][n], 0);
	}

	private void printNQueenPermutationByFixingPos(Map<Character, Integer> inputFreqMap, char[][] output,
			int posToFix) {

		if (posToFix == output.length * output[0].length) {
			printMatrix(output);
			return;
		}

		for (Map.Entry<Character, Integer> entry : inputFreqMap.entrySet()) {

			int row = posToFix / output[0].length;
			int col = posToFix % output[0].length;

			if (entry.getValue() > 0) {
				// when placing 'empty' input then no need to validate for valid qeen placement.
				if (entry.getKey() == '_' || CombinationSolution.isValidQueenPlacementByFixingPos(output, row, col)) {
					output[row][col] = entry.getKey();
					inputFreqMap.put(entry.getKey(), entry.getValue() - 1);
					printNQueenPermutationByFixingPos(inputFreqMap, output, posToFix + 1);
					inputFreqMap.put(entry.getKey(), entry.getValue() + 1);
					output[row][col] = Character.MIN_VALUE;
				}
			}

		}

	}

	@Override
	public void printNQueenPermutationByFixingInput(int n) {
		String input = "";
		for (int i = 1; i <= n; i++) {
			input += i;
		}
		char[][] output = new char[n][n];
		for (char[] c : output) {
			Arrays.fill(c, '_');
		}

		printNQueenPermutationByFixingInput(input, output);

	}

	private void printNQueenPermutationByFixingInput(String input, char[][] output) {
		if (input.isEmpty()) {
			printMatrix(output);
			return;
		}

		for (int pos = 0; pos < output.length * output[0].length; pos++) {
			int row = pos / output[0].length;
			int col = pos % output[0].length;
			if (output[row][col] == '_' && isValidQueenPlacementByFixingInput(output, row, col)) {
				output[row][col] = input.charAt(0);
				printNQueenPermutationByFixingInput(input.substring(1), output);
				output[row][col] = '_';
			}
		}
	}

	/**
	 * <pre>
	 * 
	 * Since we are fixing input and trying positions as option, so at a particular
	 * node of the tree we will try placling the queen on all postions(branches). At (n-1)th
	 * level, the (n-1)th queen can be at any one of the possible postions, so we need to
	 * search in all  the possible queen_moves direction when we are at nth level. 
	 *   
	 * 1.  vertical direction
	 * 2.  horizontal direction
	 * 3.  above-left-diagonal
	 * 4.  above-right-diagonal
	 * 5.  down-left-diagonal
	 * 6.  down-right-diagonal
	 * 
	 * </pre>
	 */
	private boolean isValidQueenPlacementByFixingInput(char[][] board, int row, int col) {

		// vertical
		for (int i = 0; i < board.length; i++) {
			// need to exclude current queen placement
			if (i == row)
				continue;
			if (board[i][col] != '_')
				return false;
		}

		// horizontal
		for (int i = 0; i < board[0].length; i++) {
			// need to exclude current queen placement
			if (i == col)
				continue;
			if (board[row][i] != '_')
				return false;
		}
		// above left-diagonal
		for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
			if (board[i][j] != '_')
				return false;
		}

		// above right-diagonal
		for (int i = row - 1, j = col + 1; i >= 0 && j < board.length; i--, j++) {
			if (board[i][j] != '_')
				return false;
		}

		// down left-diagonal
		for (int i = row + 1, j = col - 1; i < board.length && j >= 0; i++, j--) {
			if (board[i][j] != '_')
				return false;
		}

		// down right-diagonal
		for (int i = row + 1, j = col + 1; i < board.length && j < board[0].length; i++, j++) {
			if (board[i][j] != '_')
				return false;
		}

		return true;
	}

}
