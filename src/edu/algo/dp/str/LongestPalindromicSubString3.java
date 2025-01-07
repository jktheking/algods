package edu.algo.dp.str;

/**
 * <pre>
 *
 * <a href=
 *      "https://leetcode.com/problems/longest-palindromic-substring">leetcode</a>
 * 
 *  Note: For order of n solution check: "Manacher’s Algorithm" 
 *
 * String structure: c1rc2, this format can cover whole problem string,
 * lps in begning, lps in middle, lps in end.
 * LPS function structure: LPS(i,j)
 * where i points to c1 and j points to c2
 *
 * Case1: When c1==c2
 *
 *  there can be 4 decision situations:
 *  caseA#1. c1 and c2 both are part of the solution, then r has to be palindrome.
 *
 *      lps = 2 + r.length;  provided that LPS(r) is equal to r.length
 *
 *  caseA#2. c1 is included in the solution but not c2 =>  0 + LPS(c1r) even though c1==c2.
 *  caseA#3. c2 is included in the solution but not c1 => 0 + LPS(rc2) even though c1==c2
 *  caseA#4. c1 and c2 both are not included in the solution => 0 + LPS(r) even though c1==2.
 *
 *  caseA#4. will come as sub-solution of caseA#2 and caseA#3.
 *
 * Case2: When c1!=c2
 *
 *  caseB#1. c1 is included in the solution but not c2 => 0 + LPS(c1r)
 *  caseB#2. c2 is included in the solution but not c1 => 0 + LPS(rc2)
 *  caseB#3. c1 and c2 both are not included in the solution => 0 + LPS(r)
 *
 * caseB#1, caseB#2, caseB#3 are duplicates
 *
 * </pre>
 */

public class LongestPalindromicSubString3 {

	public static void main(String[] args) {

		// String s = "BAABBBBB";

		String s = "aacabdkacaa";

		// String s = "abaaa";

		// String s = "abda";

		// String s = "aaa";

		System.out.println(recursiveLPS1(s.toCharArray(), 0, s.length() - 1));

		System.out.println(recursiveLPS3(s.toCharArray(), 0, s.length() - 1, 0));

		System.out.println(tabulationOnLPS3_wrongImplementation(s.toCharArray()));

		System.out.println(tabulationLPS(s.toCharArray()));

	}

	/**
	 *
	 * LPS(i,j) i starts at 0 and j starts at end
	 *
	 * Base Cases are extreme conditions:
	 * 
	 * 1. i==j; then LPS ==1; when string is of length 1
	 *
	 * j< i: when j crosses the i; meaning no characters between the indices i,j
	 * which results in a length of 0.
	 *
	 *
	 *
	 */
	private static int recursiveLPS1(char[] s1, int i, int j) {
		if (i == j) {
			return 1;
		}

		if (j < i) {
			return 0;
		}

		int solution = 0;
		int exclude_i_sol = 0;
		int exclude_j_sol = 0;

		/*
		 * currentString = c1rc2; i points to c1 j points to c2
		 *
		 * remaingString = r
		 *
		 * string_length_formula = (j - i + 1)
		 *
		 * remainingString_length = (j - i + 1) - 2; 2 is for c1, c2
		 * 
		 * Here with caseA (s1[i] == s1[j]): we have merged the condition
		 * "remainingLength == recursiveLPS1(s1, i + 1, j - 1))" ; so exclude calls of
		 * caseA is getting executed by else-section.
		 *
		 */

		if (s1[i] == s1[j]) {

			int remainingLength = j - i - 1;
			// if LPS(r) != r it means c1 and c2 together are not participating in the
			// solution,means exclude calls to be invoked.
			if (remainingLength == recursiveLPS1(s1, i + 1, j - 1)) {
				solution = 2 + remainingLength;
			} else {
				exclude_i_sol = recursiveLPS1(s1, i + 1, j);
				exclude_j_sol = recursiveLPS1(s1, i, j - 1);
			}

		} else {
			exclude_i_sol = recursiveLPS1(s1, i + 1, j);
			exclude_j_sol = recursiveLPS1(s1, i, j - 1);
		}

		return Math.max(solution, Math.max(exclude_i_sol, exclude_j_sol));
	}

	// optimized form
	private static int recursiveLPS2(char[] s1, int i, int j) {
		if (i == j) {
			return 1;
		}

		if (j < i) {
			return 0;
		}

		int solution = 0;
		int exclude_i_sol = 0;
		int exclude_j_sol = 0;

		int remainingLength = j - i - 1;

		// if LPS(r) != r it means c1 and c2 together are not participating in the
		// solution,
		// means exclude calls to be invoked.
		if (s1[i] == s1[j] && (remainingLength == recursiveLPS2(s1, i + 1, j - 1))) {
			solution = 2 + remainingLength;
		} else {
			exclude_i_sol = recursiveLPS2(s1, i + 1, j);
			exclude_j_sol = recursiveLPS2(s1, i, j - 1);
		}
		return Math.max(solution, Math.max(exclude_i_sol, exclude_j_sol));
	}

	// different solution similar to longest common substring
	private static int recursiveLPS3(char[] s1, int i, int j, int palindromicCount) {

		//
		if (i == j) {
			return palindromicCount + 1;
		}

		if (j < i) {
			return palindromicCount;
		}

		int incCount = 0;
		if (s1[i] == s1[j]) {
			/*
			 * Important Note: For the scenario, where even though c1 == c2, and they do not
			 * contribute to the solution because the 'r' of c1rc2 is not a palindrome.
			 * Example: For the string s = "aacabdkacaa", the substring r = 'bdk' is not a
			 * palindrome. As a result, the palindromicCount is getting reset to ZERO by the
			 * exclude calls outside this if-block to get the recursion work correctly.
			 * 
			 * If we attempt to directly translate this logic into a tabulation approach by
			 * storing palindromicCount in a dp cell, the invocation-call mapping would fail
			 * to address cases where c1 == c2, but c1 and c2 are not part of the solution.
			 * This limitation arises because the tabulation approach does not allow us to
			 * correctly override dp[i][j] with ZERO in such cases.
			 * 
			 * To fix this, we must explicitly enforce the condition that 'r' must be a
			 * palindrome (i.e., LPS(r) == r) to ensure correctness.
			 * 
			 * 
			 */
			incCount = recursiveLPS3(s1, i + 1, j - 1, palindromicCount + 2);

		}

		int resi = recursiveLPS3(s1, i + 1, j, 0);
		int resj = recursiveLPS3(s1, i, j - 1, 0);

		return Math.max(incCount, Math.max(resi, resj));

	}

	/**
	 * recursiveLPS3(char[] s1, int i, int j, int palindromicCount)
	 *
	 * We will store palindromicCount at dp[i][j]; which as per recursiveLPS3
	 * represents whether the substring starting at index i and ending at index j
	 * (both inclusive) is a palindrome. If the substring is a palindrome, its
	 * length will be stored; otherwise, ZERO will be stored.
	 * 
	 */

	private static int tabulationOnLPS3_wrongImplementation(char[] s) {

		/*
		 * Let's understand the indices and their relationship for diagonal traversal:
		 * 
		 * Note: string.length == dp.length == dp[0].length
		 * 
		 * - At the centre-diagonal (0th diagonal) gap between indices i and j is ZERO
		 * (i.e., i == j). It increases/decreases by one if we traverse right/left
		 * respectively.
		 * 
		 * j is derived from diagonal-index (j = i +/- diagonal-index)
		 * 
		 * - Length of diagonal is derived from dp.length ==> diagonal_length =
		 * (dp.length - diagonal-index)
		 * 
		 * Number of diagonals between center to corner = dp.length
		 * 
		 * - The length of the substring represented by a diagonal is given by
		 * (diagonal-index + 1)
		 * 
		 * 
		 */

		int[][] dp = new int[s.length][s.length];

		// base case if (i == j), fill 0th diagonal
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}

		int solMax = Integer.MIN_VALUE;
		// we will fill the table diagonally and will fill upper half
		// length of string = j - i + 1

		for (int d = 1; d < dp.length; d++) {
			// i is for traversing along the length of the diagonal
			for (int i = 0; i < dp.length - d; i++) {

				int j = i + d;

				/**
				 * This code will fail in cases where c1 == c2, but they do not contribute to
				 * the solution because the substring 'r' in c1rc2 is not a palindrome.
				 * 
				 * Example: For the string s = "aacabdkacaa", the substring r = 'bdk' is not a
				 * palindrome. However, the current logic keeps adding 2 to the count without
				 * verifying whether 'r' is a palindrome. Additionally, unlike the recursive
				 * solution where exclude calls allow us to reset dp[i][j] to ZERO, the
				 * tabulation approach has no mechanism to do this.
				 * 
				 * To fix this issue, we must include a palindromic check for the substring 'r'
				 * to ensure the solution handles such cases correctly.
				 */
				if (s[i] == s[j]) {
					dp[i][j] = 2 + dp[i + 1][j - 1];

					solMax = Math.max(solMax, dp[i][j]);

				} else {
					dp[i][j] = 0;
				}

			}
		}

		return solMax;
	}

	/**
	 * dp[i][j] : store LPS if the string is palindrome else store ZERO. refer
	 * SubStringsOn2DMatrix.pdf for further explanations.
	 * 
	 * Note: s.length == dp.length == dp[0].length
	 */
	private static String tabulationLPS(char[] s) {

		int solutionLength = 1;
		int solution_i = 0;

		int dp[][] = new int[s.length][s.length];

		// fill the 0th diagonal and first diagonal as base case
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}
		// 1th diagonal C1rC2: r is ZERO for first diagonal because it is substring of
		// 2 chars.
		// so LPS will just depend on C1 and C2 not on r.
		for (int i = 0; i < dp.length - 1; i++) {
			int j = i + 1;
			dp[i][j] = s[i] == s[j] ? 2 : 0;

			if (solutionLength < dp[i][j]) {
				solutionLength = dp[i][j];
				solution_i = i;
			}
		}

		for (int d = 2; d < dp.length; d++) {
			for (int i = 0; i < dp.length - d; i++) {

				int j = i + d;

				// we need to go back 2 diagonals from current diagonal-index
				// we are adding 1 to get the length as diagonal is index-based.
				int r = (d - 2) + 1;

				if (s[i] == s[j] && dp[i + 1][j - 1] == r) {

					dp[i][j] = r + 2;
				} else {
					dp[i][j] = 0;
				}

				if (solutionLength < dp[i][j]) {
					solutionLength = dp[i][j];
					solution_i = i;
				}

			}

		}

		return new String(s, solution_i, solutionLength);

	}

	private static String tabulationLPSBoolean(char[] s) {

		int solutionLength = 1;
		int solution_i = 0;

		boolean dp[][] = new boolean[s.length][s.length];

		// fill the 0th diagonal and first diagonal as base case
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = true;
		}
		// 1th diagonal C1rC2: r is ZERO for first diagonal because it is substring of
		// 2 chars.
		// so LPS will just depend on C1 and C2 not on r.
		for (int i = 0; i < dp.length - 1; i++) {
			int j = i + 1;
			dp[i][j] = s[i] == s[j] ? true : false;

			if (dp[i][j]) {
				solutionLength = 2;
				solution_i = i;
			}
		}

		for (int d = 2; d < dp.length; d++) {
			for (int i = 0; i < dp.length - d; i++) {

				int j = i + d;

				// we need to go back 2 diagonals from current diagonal-index
				// we are adding 1 to get the length as diagonal is index-based.
				int r = (d - 2) + 1;

				if (s[i] == s[j] && dp[i + 1][j - 1]) {

					dp[i][j] = true;
				} else {
					dp[i][j] = false;
				}

				if (dp[i][j]) {
					solutionLength = r + 2;
					solution_i = i;
				}

			}

		}

		return new String(s, solution_i, solutionLength);

	}

}
