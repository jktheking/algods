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

		// String s = "aacabdkacaa";

		String s = "aaca";

		// String s = "abaaa";

		// String s = "abda";

		// String s = "aaa";

		System.out.println(recursiveLPS1(s.toCharArray(), 0, s.length() - 1));

		System.out.println(recursiveLPS(s.toCharArray(), 0, s.length() - 1, 0));

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
		int remainingLength = j - i - 1;
		if (s1[i] == s1[j] && (remainingLength == recursiveLPS1(s1, i + 1, j - 1))) {
			solution = 2 + remainingLength;

		} else {
			exclude_i_sol = recursiveLPS1(s1, i + 1, j);
			exclude_j_sol = recursiveLPS1(s1, i, j - 1);
		}

		return Math.max(solution, Math.max(exclude_i_sol, exclude_j_sol));
	}

	// different solution similar to longest common substring
	private static int recursiveLPS(char[] s1, int i, int j, int palindromicCount) {

		//
		if (i == j) {
			return palindromicCount + 1;
		}

		if (j < i) {
			return palindromicCount;
		}

		int incCount = 0;
		if (s1[i] == s1[j]) {

			incCount = recursiveLPS(s1, i + 1, j - 1, palindromicCount + 2);

		}

		int resi = recursiveLPS(s1, i + 1, j, 0);
		int resj = recursiveLPS(s1, i, j - 1, 0);

		return Math.max(incCount, Math.max(resi, resj));

	}

	/**
	 * <pre>
	 * Cell Meaning:
	 * dp[i][j] represents whether the substring starting at index i and ending at index j (both inclusive) is a palindrome.
	 * If the substring is a palindrome, its length will be stored; otherwise, ZERO will be stored.
	 *
	 * Problem String Representation: S = C1rC2; to obtain the tabulated solution, we first solve for smaller substrings, starting with 'r'.
	 * Initially, we consider 'r' of length 1, then length 2, and so forth.
	 *
	 * Given that 'r' is the middle part of the string representation (C1rC2) and must be solved before the complete string
	 * string is considered -- we start solving the problem from the diagonal of the matrix as a rule of thumb.
	 * - The diagonal represents r with length 1.
	 * - The next cell ahead of  the diagonal cells represent r with length 2, and so on.
	 *
	 * Note: How to calculate 'r' at a given cell S = C1rC2?
	 * The length of 'r' is j - i - 1, and its value is stored in the cell dp[i+1][j-1].
	 *
	 * Refer to the recursive solution for the final implementation:
	 *
	 * if (dp[i+1][j-1] != 0) {
	 * // r is a palindrome
	 *     dp[i][j] = 2 + dp[i+1][j-1];
	 * } else {
	 * // r is not a palindrome
	 *     dp[i][j] = 0;
	 * }
	 * </pre>
	 */
	private static String tabulationLPS(char[] s) {

		int solutionLength = 1;
		int solution_i = 0;

		int dp[][] = new int[s.length][s.length];

		// need to calculate the solution for all the string of length 1.
		for (int i = 0; i < dp.length; i++) {
			dp[i][i] = 1;
		}

		// length of string = j - i + 1
		for (int l = 2; l <= s.length; l++) {
			for (int i = 0; i <= s.length - l; i++) {

				int j = l + i - 1;

				if (s[i] == s[j]) {
					int remainingLength = j - i - 1;

					// C1rC2, when r is of length 0, i.e string is of length==2
					// palindromic length of r is 0
					if (remainingLength == 0) {
						dp[i][j] = 2;
					} else {
						int palindromicLength_r = dp[i + 1][j - 1];

						if (palindromicLength_r != 0) {

							dp[i][j] = 2 + palindromicLength_r;
						} else {
							// since r is not the palindrome, hence string c1rc2 cannot be palindrome
							dp[i][j] = 0;
						}
					}
				}

				if (solutionLength < dp[i][j]) {
					solutionLength = dp[i][j];
					solution_i = i;
				}

			}

		}

		return new String(s, solution_i, solutionLength);

	}

}
