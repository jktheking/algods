package edu.algo.permcomb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * links:
 * https://hmkcode.com/calculate-find-all-possible-combinations-of-an-array-using-java/
 * 
 * 
 * 
 * 
 * https://introcs.cs.princeton.edu/java/23recursion/Combinations.java.html
 * 
 * https://introcs.cs.princeton.edu/java/23recursion/Comb2.java.html
 * 
 * https://introcs.cs.princeton.edu/java/23recursion/CombinationsK.java.html
 * 
 * https://introcs.cs.princeton.edu/java/23recursion/PermutationsK.java
 * 
 * 
 * https://introcs.cs.princeton.edu/java/23recursion/
 * 
 * ---baledung
 * https://baeldung-cn.com/java-combinatorial-algorithms
 * https://www.baeldung.com/java-combinations-algorithm
 * https://www.baeldung.com/java-array-permutations
 * 
 * https://www.baeldung.com/cs/array-generate-all-permutations
 * 
 * https://www.baeldung.com/java-power-set-of-a-set
 * 
 * 
 * */

/**
 * DOUBLE COUNTING OR INCLUDE_EXCLUDE COUNTING
 * 
 * It is one of the important proof technique.
 * 
 * Idea : count the same same thing in two different way. <br>
 * 
 * Pascal's identity : nCk = n-1Ck-1 + n-1Ck <br>
 * 
 * Suppose we have a group of n people and we'd like to choose a committee of k
 * members. Means there are nCk ways of doing this. Let's pick out one
 * particular person from the group of n people and called her 'Chakarini'.
 * There are n-1Ck-1 committees that include 'Chakarini' because after we
 * included 'Chakarini' there are 'k-1' remaining committee members to choose
 * out of 'n-1' remaining peoples.
 * 
 * Similarly, there are n-1Ck committees that exclude 'Chakarini' because all k
 * committee members must be chosen out of the remaining n-1 people. <br>
 * 
 * <b> Thus, the total number of committee is equal to the number of committees
 * that include 'Chakrini' plus that exclude 'Chakarini' </b>
 * 
 * <p>
 * Double Counting to Binomial Theorem: <b> (a + b)<sup>n</sup> =
 * &Sigma;<sup>n</sup><sub>k=0</sub>&nbsp;<sup>n</sup>C<sub>k</sub>&nbsp;a<sup>(n-k)</sup>b<sup>k</sup>
 * </b> <br>
 * 
 * <u> Binomial coefficient or Binomial theorem for (1 + 1)^n :</u><br>
 * nC0 +nC1 + nC2 + ---- + nCn = (1 +1)^n = 2^n <br>
 *
 * 2^n : represents the subsets possibles from set of 'n' elements. So, Let's
 * count the total number of subsets of this group of 'n' people. <br>
 * nC0 : represents the total subset of size 0<br>
 * nC2 : represents the total subset of size 2<br>
 * similarly: nCn : represents the total subset of size n<br>
 * 
 * So, if we sum up all the subsets of size 0,1,2,3,4..n we will get total
 * number of subsets i.e 2^n. <br>
 * 
 * We can also count subsets by looking at each person one at a time. Each
 * person can be either be included or excluded in the subset(i.e. there are two
 * possibilities for each person).<br>
 * 
 * so. 2 * 2 * 2 * .... 2 = 2^n <br>
 * 
 * Let's see the include-exclude binomial counting for 3 persons 2^3=8<br>
 * 
 * A B C <br>
 * 0 0 0 =>{}<br>
 * 1 0 0 =>{A}<br>
 * 0 1 0 =>{B}<br>
 * 0 0 1 =>{C}<br>
 * 1 1 0 =>{A,B}<br>
 * 1 0 1 =>{A,C}<br>
 * 0 1 1 =>{B,C}<br>
 * 1 1 1 =>{A,B,C}<br>
 * 
 * 
 * 
 * 
 * 
 * 
 */
public class DoubleCountingSolution implements DoubleCountingQuestion {

	private int selectionCounter = 1;
	private int recursionBranchCounter = 1;

	public static void main(String[] args) {

		// INSTANCE.nCkUsingPascalIdentity(3, "abcde", "");

		INSTANCE.printPowerSetUsingPascalIdentityExpansion2("abc".toCharArray());

		// INSTANCE.printPowerSetUsingPascalIdentityByFixingPos(3);
		//INSTANCE.printPowerSetUsingPascalIdentityExpansionByFixingPos(3);

	}

	@Override
	public void printPowerSetUsingPascalIdentity(String inputString) {
		printPowerSetUsingPascalIdentity(inputString, "");
	}

	/**
	 * 
	 * Accurate calculation for Time Complexity : If we assume one-unit of task
	 * being done at each method invocation. Since there are 2^n leaf-nodes, so
	 * internal-nodes = (2^n -1) So, Time-complexity = O(2^n + 2^n -1) = O(2^(n+1)
	 * -1) = O(2^(n+1))
	 * 
	 * 
	 * Approximate calculation for Time Complexity: To generate one leaf node we
	 * need to traverse 'n' steps. And total number of leaf-nodes are 2^n so,
	 * Time-Complexity = O(n*2^n)
	 * 
	 */
	private void printPowerSetUsingPascalIdentity(String inputString, String output) {

		// breaks the recursion
		if (inputString.length() == 0) {
			System.out.println(selectionCounter++ + ":" + output);
			return;
		}

		// recursionBranchCounter can be used to correlate time-complexity
		// System.out.println(recursionBranchCounter++);
		// by including
		printPowerSetUsingPascalIdentity(inputString.substring(1), output + inputString.charAt(0));
		// by excluding
		printPowerSetUsingPascalIdentity(inputString.substring(1), output);

	}

	@Override
	public void printPowerSetUsingPascalIdentity(List<Integer> input) {
		printPowerSetUsingPascalIdentity(input, new ArrayList<>());

	}

	/**
	 * Total no. of method invocation is equal to total no. of nodes in the tree
	 * formed. 2^0 + 2^1 + 2^2 + ---+2^n = 2^(n+1) - 1 . i.e O(2^n)
	 */
	private void printPowerSetUsingPascalIdentity(List<Integer> input, List<Integer> tempOutput) {
		if (input.isEmpty()) {
			System.out.println(tempOutput);
			return;
		}

		Integer includeExcludeCandidate = input.remove(0);
		List<Integer> copyOfTempOutput = new ArrayList<>(tempOutput);

		// by including the candidate
		tempOutput.add(includeExcludeCandidate);
		printPowerSetUsingPascalIdentity(new ArrayList<>(input), new ArrayList<>(tempOutput));

		// by excluding the candidate
		printPowerSetUsingPascalIdentity(new ArrayList<>(input), copyOfTempOutput);
	}

	@Override
	public void printPowerSetUsingPascalIdentityExpansion1(String input) {
		printPowerSetUsingPascalIdentityExpansion1(input, "");
	}

	/**
	 * 
	 * 
	 * C(n,k) = C(n-1,k-1) + C(n-1,k)
	 * 
	 * Expansion is based on recursive expansion of exclude-term C(n-1, k)
	 * 
	 * C(n,k) = C(n-1,k-1) + C(n-2,k-1) + C(n-3,k-1) + ... C(k-1, k-1)
	 * 
	 * All terms in RHS represents include terms.
	 * 
	 * 
	 * As per the pascalIdentityExpansion.pdf, we are getting output printed at each
	 * recursive invocation of method, and there are 2^n outputs, so 2^n recursive
	 * invocation of method. Thus, time-complexity is O(2^n)
	 * 
	 * This method is more efficient than Include-Exclude based method
	 * "powerSetUsingPascalIdentity". As in Include-Exclude based method all the
	 * outputs are printed at leaf-level.
	 */
	private void printPowerSetUsingPascalIdentityExpansion1(String input, String output) {
		System.out.println(output);
		for (int i = 0; i < input.length(); i++)
			printPowerSetUsingPascalIdentityExpansion1(input.substring(i + 1), output + input.charAt(i));
	}

	@Override
	public void printPowerSetUsingPascalIdentityExpansion2(char[] input) {
		char[] output = new char[input.length];
		Arrays.fill(output, '_');
		printPowerSetUsingPascalIdentityExpansion2(input, 0, output);
	}

	/**
	 * <pre>
	 * Postion is used for both :
	 *
	 * 1. fixing at tree levels and for 
	 * 
	 * 2. trying as options.
	 * 
	 * 3. Note: input elemnts are hard-attached with respective postion.
	 * </pre>
	 */
	private void printPowerSetUsingPascalIdentityExpansion2(char[] input, int posToFix, char[] output) {
		System.out.println(Arrays.toString(output));
		while (posToFix < input.length) {
			int currentPos = posToFix;
			output[currentPos] = input[currentPos];
			posToFix = posToFix + 1;
			printPowerSetUsingPascalIdentityExpansion2(input, posToFix, output);
			output[currentPos] = '_';
		}

	}

	/**
	 * <pre>
	 * 
	 * Given abc: a, b, c, ab, bc, ac, abc , "" are valid subsequences. ca, cb are
	 * not becuase these are not in the chars order of input.
	 * 
	 * Total subsequences : is nothing but power-set formed by chars of input string
	 * in occurrence order.
	 * 
	 * Assumption : abc ; first_operand = 'a', remaining collated operand 'bc'
	 * 
	 * Hypothesis: powerSetUsingSubSequence(input="abc") : a, b, c, ab, bc, ac, abc , ""
	 * Substitution: powerSetUsingSubSequence(input="bc") : b, c, bc, ""
	 * 
	 * Induction: 
	 * Include step: include char 'a' as prefix with the result of substitution
	 * Exclude step: exclude the char 'a' as prefix with the result of substituton
	 * 
	 * Base Case : when input becomes empty, subsequence is empty string.
	 * 
	 * Time Complexity: subsequence creation starts at nth recursive invocation.
	 * nth     recursion invoctioan :  for-loop iterates 2^0 times 
	 * (n-1)th recursion invoctioan :  for-loop iterates 2^1 times 
	 * (n-2)th recursion invoctioan :  for-loop iterates 2^2 times
	 * (n-3)th recursion invoctioan :  for-loop iterates 2^3 times
	 * 
	 * Total for-loop iterations : 2^0 + 2^1 + 2^2 + .. + 2^(n-1)  = 2^(n-1+1) -1 = 2^n -1
	 * Time complexity excluding method invocation cost : O(2^n -1) = O(2^n)
	 * 
	 * Time complexity including method invocation cost :we have total (n+1) method invocation ; 
	 *  so total time complexity : (n+1)  + 2^n -1  =  n + 2^n  = O(2^n)
	 * </pre>
	 * 
	 * @see : Substring of abc : a, b, c, ab, bc, abc ; total_number_of_subsequece:
	 *      n(n+1)/2
	 */
	@Override
	public List<String> getPowerSetUsingSubSequence(String input) {

		if (input.isEmpty()) {
			return List.of("");
		}

		char ch = input.charAt(0);
		List<String> resultOfSubstitution = getPowerSetUsingSubSequence(input.substring(1));

		List<String> result = new ArrayList<>();
		for (String ss : resultOfSubstitution) {
			// include ch
			result.add(ch + ss);
			// exclude ch
			result.add(ss);
		}
		return result;

	}

	@Override
	public void print_nCkUsingPascalIdentity(final int deziredLength, String input) {
		nCkUsingPascalIdentity(deziredLength, input, "");
	}

	/**
	 * Time Complexity : nC0 + nC1 +nC2 + nC3 + ...nCi
	 * 
	 * where n = input size ; i = deziredLength
	 * 
	 * 
	 * <pre>
	 * 
	 * If n=2, then nC0 + nC1 +nC2 = 1 +  n(n+1)2  = O(n^2)
	 * 
	 * We can also use 2 for-loops to count the combination of 2.
	 * 
	 * @See {@link #nC2UsingLoop(String)}
	 * 
	 * </pre>
	 */
	private void nCkUsingPascalIdentity(final int deziredLength, String input, String output) {

		if (output.length() == deziredLength) {
			System.out.println(selectionCounter++ + ":" + output);
			return;
		}

		if (input.length() == 0) {
			return;
		}

		// recursionBranchCounter can be used to correlate time-complexity
		System.out.println(recursionBranchCounter++);

		nCkUsingPascalIdentity(deziredLength, input.substring(1), output + input.substring(0, 1));
		nCkUsingPascalIdentity(deziredLength, input.substring(1), output);

	}

	/**
	 * Time complexity : n+ (n-1) + (n-2) +..+1 = n*(n+1)/2 = O(n^2)
	 * 
	 * [a,b,c,d]
	 * 
	 * At i=0; all possible combinations including 'a'; and at other subsequent
	 * iteration of 'i' all possible combinations excluding a.
	 * 
	 * At i=1; all possible combinations including 'b'; and at other subsequent
	 * iteration of 'i' all possible combinations excluding b.
	 * 
	 * 
	 * 
	 */
	@Override
	public void print_nC2UsingLoop(String input) {

		char[] inpuArr = input.toCharArray();

		for (int i = 0; i < inpuArr.length; i++) {

			for (int j = i + 1; j < inpuArr.length; j++) {

				System.out.println("" + inpuArr[i] + inpuArr[j]);
			}

		}

	}

	private void nCkUsingPascalIdentity1(final int desizedLength, String input, String output) {

		// if (input.length() == 0) {
		// System.out.println(counter++ + ":" + output);
		// return;
		// }
		if (output.length() == desizedLength) {
			System.out.println(selectionCounter++ + ":" + output);
			return;
		}

		final int requiredLength = desizedLength - output.length();

		// means output has got desired number of characters, so calling include will
		// make the output-length greater than desired. Thus include should not be
		// called
		if (requiredLength == 0) {
			nCkUsingPascalIdentity1(desizedLength, input.substring(1), output);
		} else if (requiredLength > 0) {

			if (input.length() == requiredLength) {
				// if input length is equal to required length then only include can make it to
				// desired output and exclude cannot make it to desired output.
				nCkUsingPascalIdentity1(desizedLength, input.substring(1), output + input.substring(0, 1));
			} else if (input.length() > requiredLength) {

				// if input-length is greater than requiredLength, then both include and exclude
				// can make it to desired output.
				nCkUsingPascalIdentity1(desizedLength, input.substring(1), output + input.substring(0, 1));
				nCkUsingPascalIdentity1(desizedLength, input.substring(1), output);
			}

		}

	}

	@Override
	public void printPowerSetByIncludingAGivenCharOfInput(int includeCharIndex, String inputString) {

		char charcterToInclude = inputString.charAt(includeCharIndex);

		final String newInputExcludingChar = inputString.substring(0, includeCharIndex)
				+ inputString.substring(includeCharIndex + 1);

		// calling the powerSet method by including the given character
		printPowerSetUsingPascalIdentity(newInputExcludingChar, String.valueOf(charcterToInclude));

	}

	/**
	 * print power set by placing 'i' at 'n' given positions.
	 * 
	 */
	@Override
	public void printPowerSetUsingPascalIdentityByFixingPos(int positionCount) {
		printPowerSetUsingPascalIdentityByFixingPosition(positionCount, "", 0);
	}

	private void printPowerSetUsingPascalIdentityByFixingPosition(int positionCount, String output, int posToFix) {

		if (positionCount == posToFix) {
			System.out.println(output);
			return;
		}
		// exclude
		printPowerSetUsingPascalIdentityByFixingPosition(positionCount, output + "_", posToFix + 1);
		// include
		printPowerSetUsingPascalIdentityByFixingPosition(positionCount, output + "i", posToFix + 1);

	}

	@Override
	public void printPowerSetUsingPascalIdentityExpansionByFixingPos(int positionCount) {
		char[] output = new char[positionCount];
		Arrays.fill(output, '_');
		printPowerSetUsingPascalIdentityExpansionByFixingPos(output, 0);
	}

	/**
	 * Position is used for both 1. fixing at levels, and for 2. options to try.
	 * 
	 * 1. Position is getting fixed at each level, so passed as method argument.
	 * 
	 * 2. position is also getting used as options so is iterated in while loop.
	 */
	private void printPowerSetUsingPascalIdentityExpansionByFixingPos(char[] output, int posToFix) {
		System.out.println(String.valueOf(output));
		while (posToFix < output.length) {
			int currentPos = posToFix;
			output[currentPos] = 'i';
			printPowerSetUsingPascalIdentityExpansionByFixingPos(output, ++posToFix);
			output[currentPos] = '_';
		}
	}

}
