package edu.algo.dp.str;

/**
 * Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of
 * s1 and s2.
 *
 * An interleaving of two strings s and t is a configuration where s and t are
 * divided into n and m substrings respectively, such that:
 *
 * s = s1 + s2 + ... + sn t = t1 + t2 + ... + tm |n - m| <= 1 The interleaving
 * is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
 * Note: a + b is the concatenation of strings a and b.
 *
 * Example 1:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" Output: true
 * Explanation: One way to obtain s3 is: Split s1 into s1 = "aa" + "bc" + "c",
 * and s2 into s2 = "dbbc" + "a". Interleaving the two splits, we get "aa" +
 * "dbbc" + "bc" + "a" + "c" = "aadbbcbcac". Since s3 can be obtained by
 * interleaving s1 and s2, we return true.
 *
 * Example 2:
 *
 * Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc" Output: false
 * Explanation: Notice how it is impossible to interleave s2 with any other
 * string to obtain s3.
 *
 * Example 3:
 *
 * Input: s1 = "", s2 = "", s3 = "" Output: true
 *
 *
 *
 */

/**
 * 
 * 
 * <pre>
 *How to solve the comparison based DP problems?
 *
 *Rule of Thumb: "COMBINATIONS_ON_COMPARISONS"
 *Apply all the possible combinations using include/exclude on problem comparisons. 
 *
 *
 *
 *First define the recursive solution --> the memomization --> then tabulation
 *
 *Steps involved:
 *===============================================================================
 *
 *STEP1:Drawing PROBLEM-COMPARISION-SPACE-SPAN
 *
 *First we need to figure-out the "problem-comparision-space-span" on inputs given in the problem 
 *Here we can try comparison options from brute-force to figure-out problem-comparision-space-span
 *and recursive function definition.
 *===============================================================================
 * s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 *  s1 = c1r1
 *  s2 = c2r2
 *  s3 = c3r3
 *
 * this kind of string structure covers all the problem space.
 *
 *=====================================================================
 * STEP2: Define RECURSION-FUNCTION-DEFINATION
 *
 * Define the recursive function as per the "problem-space-span"
 * =====================================================================
 *
 * here: IRL_STR(s1, s2, s3, i, j, k)
 *
 *
 *================================================================================
 *
 *STEP3: Defining CASES as per PROBLEM-COMPARISION-SPACE-SPAN
 *
 *need to figure-out the cases available in problem statement by just
 *moving through the PROBLEM-COMPARISION-SPACE-SPAN.
 *================================================================================
 *
 *Here if we scan chars of s1 or s2 with respect to s3 we can traverse
 *the whole problem space. Since the length of s3 is equal to s1 + s2; so
 *s3 must contains all the characters of s1 and s2.
 *
 * CASE1: c3 == c1
 *   or
 * CASE2: c3 == c2
 *
 * Also, define the base cases
 *================================================================================
 *STEP4: RECURSION-AND-CASE-BINDING
 *
 *Bind cases with SUB-RECURSION-INVOCATION to create the solution space
 *=================================================================================
 *
 * recursion and case binding example:
 *
 * CASE1: c3 == c1 and its recursion binding for solution space
 *
 * if c3 == c1 and c1 & c3 participates in the solution, then
 * there will two situations:
 *
 *  1. SUB-RECURSION-INVOCATION  will participate in the solution
 *  2. SUB-RECURSION-INVOCATION will not participate in the solution
 *
 *  if(c3 == c1 && IRL_STR(s1, s2, s3, i+1, j, k+1)) {
 *     // c3 == c1 and c1 & c3 participates in the solution
 *     //SUB-RECURSION-INVOCATION also participates in the solution
 *
 *
 *  }
 *
 *   if(c3 == c1  && !IRL_STR(s1, s2, s3, i+1, j, k+1)){
 *     // c3 == c1 and c1 & c3 participates in the solution
 *     //SUB-RECURSION-INVOCATION does not participate in the solution
 *   }
 *
 *
 *=========================================================================
 *STEP 6: Remove derived parameters from RECURSION-FUNCTION-DEFINATION
 *==========================================================================
 * IRL_STR(s1, s2, s3, i, j, k)
 *
 * eg. here 'k' can be derived from i and j so we can totally ignore the k.
 *    k = i + j; as length of s3 is s1 + s2.
 *
 *    IRL_STR(s1, s2, s3, i, j)
 *
 *
 *===================================================================
 *STEP 7: MEMOIZATION-SOLUTION
 *===================================================================
 * In case of memoization we will have as many unique sub-problems
 * as many possible values are for variable parameters in RECURSION-FUNCTION-DEFINATION
 *
 * Number of variable parameters conforms with the memoization table, if we have
 * two variable parameters then we will need 2D memoization table
 *
 *here i & j are variables;  means s1.length * s2.length will be the dimension of
 *memoization table
 *
 *
 *
 *======================================
 *Step 8: TABULATION-SOLUTION
 *======================================
 *Tabulation table filling conforms the RECURSION-AND-CASE-BINDING
 *Since in general we need to fill the table for the smaller problem first so,
 *we need to use the base cases as extra input on table.
 *
 *
 *
 *Way of generating the whole problem space(Pascal identity or natural way of counting)
 *=========================================
 * 1. Subset space : s = cr
 *    By including the current char 'c' as part of the solution
 *    By excluding the current char 'c' and creating the solution without 'c'
 *
 * 2. Substring/SubArray : pcr
 *
 *    By including the current char 'c' as part of the solution,
 *     additionally we need to take care of contiguousPrefix.
 *    By excluding the current char 'c' and creating the solution without 'c',
 *      additionally we need to take care of contiguousPrefix.
 *   This is effectively:
 *    - apply currentElement with running contiguousPrefix
 *    - start the contiguousPrefix from currentElement itself
 *
 * </pre>
 **/
public class InterleavingString0 {

	public static void main(String[] args) {

	}

	public static boolean ILSRec(char[] s1, char[] s2, char[] s3, int i, int j) {

		int k = i + j;

		if (k == s3.length && i == s1.length && j == s2.length) {
			return true;
		}

//		this base-case can be ignored by placing condition to avoid ArrayIndexOutofBound exception while making recursive call
//		if (i == s1.length && j < s2.length) {
//          remaining s2 should be equal to remaining s3
//		}

//		// base-case to be ignored
//		if (j == s2.length && i < s1.length) {
//             remaining s1 should be equal to remaining s3
//		}

		// include call for i and exclude call for j
		if ((i < s1.length && k < s3.length) && (s3[k] == s1[i] && ILSRec(s1, s2, s3, i + 1, j))) {
			return true;
		}

		// include call for j and exclude call for i
		if ((j < s2.length && k < s3.length) && (s3[k] == s2[j] && ILSRec(s1, s2, s3, i, j + 1))) {
			return true;
		}

		return false;

	}

	// all the unique ILSMemo(i,j) can be saved into a memo table of size i*j
	public static boolean ILSMemo(char[] s1, char[] s2, char[] s3, int i, int j, Boolean[][] memo) {

		if (memo[i][j] != null) {
			return memo[i][j];
		}

		int k = i + j;

		if (k == s3.length && i == s1.length && j == s2.length) {
			memo[i][j] = true;
			return memo[i][j];
		}

		// solution of call ILSMemo(i,j) is calculated using following two expression
		// and is dependent on stored solution ILSMemo(i+1,j) OR ILSMemo(i, j+1)
		if ((i < s1.length && k < s3.length) && (s3[k] == s1[i] && ILSMemo(s1, s2, s3, i + 1, j, memo))) {
			memo[i][j] = true;
			return memo[i][j];
		}

		if ((j < s2.length && k < s3.length) && (s3[k] == s2[j] && ILSMemo(s1, s2, s3, i, j + 1, memo))) {
			memo[i][j] = true;
			return memo[i][j];
		}

		memo[i][j] = false;
		return memo[i][j];

	}

	/**
	 * Steps Used
	 * 
	 * STEP1: create recursive expression and combine the expression with problem
	 * cases for solution expression
	 * 
	 * STEP2: provide extreme values to input to generate base cases example s1
	 * empty, s2 empty, s3 empty and their combination
	 * 
	 * STEP3: Use base cases to fill the base-row rows and base-col
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public static boolean ILSTabulation(char[] s1, char[] s2, char[] s3) {

		boolean[][] dp = new boolean[s1.length + 1][s2.length + 1];

		if (s1.length + s2.length != s3.length)
			return false;

		// s1 and s2 and s3 all are empty
		dp[0][0] = true;

		// when s1 so-far is empty, then s3 so-far needs to be equal to s2 so-far, for
		// solution so-far to be true
		for (int j = 1; j < dp[0].length; j++) {
			dp[0][j] = s2[j - 1] == s3[j - 1] && dp[0][j - 1];

		}

		// when s2 so-far is empty, then s3 so-far needs to be equal to s1 so-far, for
		// solution so-far to be true
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = s1[i - 1] == s3[i - 1] && dp[i - 1][0];

		}

		// solution of call ILS(i,j) is calculated using following two expression
		// and is dependent on stored solution ILS(i - 1,j) OR ILS(i, j-1)
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				int k = i + j;

				if (s1[i - 1] == s3[k - 1] && dp[i - 1][j]) {
					dp[i][j] = true;
				}

				if (s2[j - 1] == s3[k - 1] && dp[i][j - 1]) {
					dp[i][j] = true;
				}
			}
		}
		// return the last cell
		return dp[s1.length][s2.length];

	}

}
