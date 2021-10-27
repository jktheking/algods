package edu.algo.numbertheory;


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
public class DoubleCounting {

	private static final DoubleCounting INSTANCE = new DoubleCounting();

	private int selectionCounter = 1;
	private int recursionBranchCounter = 1;
	

	public static void main(String[] args) {

		 INSTANCE.printPowerSet("12345");
		//INSTANCE.nCkUsingPascalIdentity(3, "abcde", "");


	}

	public void printPowerSet(String inputString) {
		powerSetUsingPascalIdentity(inputString, "");
		//powerSetUsingPascalIdentityExpansion(inputString, "");

	}

	/**
	 * 
	 * Total no. of method invocation is equal to total no. of nodes in the tree
	 * formed. 2^0 + 2^1 + 2^2 + ---+2^n = 2^(n+1) - 1 . i.e O(2^n)
	 * 
	 * C(n,k) = C(n-1,k-1) + C(n-1,k)
	 * 
	 * 
	 */
	private void powerSetUsingPascalIdentity(String inputString, String output) {

		//breaks the recursion
		if (inputString.length() == 0) {
			System.out.println(selectionCounter++ + ":" + output);
			return;
		}
		
		
		//recursionBranchCounter can be used to correlate time-complexity 
		//System.out.println(recursionBranchCounter++);
		// by including
		powerSetUsingPascalIdentity(inputString.substring(1), output + inputString.substring(0, 1));
		// by excluding
		powerSetUsingPascalIdentity(inputString.substring(1), output);

	}

	/**
	 * C(n,k) = C(n-1,k-1) + C(n-1,k) 
	 * 
	 * Expansion is based on recursive expansion of exclude-term C(n-1, k)
	 * 
	 * C(n,k) = C(n-1,k-1) + C(n-2,k-1) + C(n-3,k-1) + ... C(k-1, k-1)
	 * 
	 *    All terms in RHS represents include terms.
	 * 
	 * */
    private static void powerSetUsingPascalIdentityExpansion(String input, String output) {
        System.out.println(output);
        for (int i = 0; i < input.length(); i++)
        	powerSetUsingPascalIdentityExpansion(input.substring(i + 1), output + input.charAt(i));
    }  
	
	
	
	
	
	/*
	 * prints all the possible combination for a given length
	 */
	public void printAllSetOfGivenLength(final int length, String input) {
		// both the methods has same O(n)
		nCkUsingPascalIdentity(length, input, "");
		//nCkUsingPascalIdentity(length, input, "");
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

		//recursionBranchCounter can be used to correlate time-complexity 
		 System.out.println(recursionBranchCounter++);
		 
		nCkUsingPascalIdentity(deziredLength, input.substring(1), output + input.substring(0, 1));
		nCkUsingPascalIdentity(deziredLength, input.substring(1), output);

	}

	/**
	 * Time complexity : n+ (n-1) + (n-2) +..+1 = n*(n+1)/2 = O(n^2)
	 * 
	 * [a,b,c,d]
	 * 
	 * At i=0; all possible combinations including 'a'; and at other subsequent iteration of 'i'
	 * all possible combinations excluding a.
	 * 
	 * At i=1; all possible combinations including 'b'; and at other subsequent iteration of 'i'
	 * all possible combinations excluding b.
	 * 
	 * 
	 * 
	 */
	private void nC2UsingLoop(String input) {

		char[] inpuArr = input.toCharArray();

		for (int i = 0; i < inpuArr.length; i++) {

			for (int j = i + 1; j < inpuArr.length; j++) {

				System.out.println("" + inpuArr[i] + inpuArr[j]);
			}

		}

	}

	private void nCkUsingPascalIdentity1(final int desizedLength, String input, String output) {

	
//		if (input.length() == 0) {
//			System.out.println(counter++ + ":" + output);
//			return;
//		}
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

	/*
	 * prints all size of string including the given character
	 * 
	 * 
	 */
	public void printAllSetByIncludingAGivenCharacterOfTheInput(int charIndex, String inputString) {

		char charcterToInclude = inputString.charAt(charIndex);

		final String newInputExcludingChar = inputString.substring(0, charIndex) + inputString.substring(charIndex + 1);

		// calling the powerSet method by including the given character
		powerSetUsingPascalIdentity(newInputExcludingChar, String.valueOf(charcterToInclude));

	}

}
