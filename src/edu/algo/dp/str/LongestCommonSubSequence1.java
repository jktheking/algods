package edu.algo.dp.str;

import java.util.Arrays;

/**
 * <pre>
 *
 * Since it's the class of subset,as all the problem space can be generated
 * using subset, so we will use  'S = cr' and will try to include/exclude
 * with all the native cases of the problem.
 *
 * To understand decision tree cases:
 * {@link https://www.youtube.com/watch?v=lkL_--Bnxi4}
 *
 * Let's explore all possibilities of recursive decision:
 *
 * String = current_char + remaining string
 *
 * s1 = c1r1
 * s2 = c2r2
 *
 *If we apply the unit step i.e. include/exclude on c1r1 and c2r2 there can
 *be following 4 case
 *
 *  case_1. c1 and c2 both be included in the solution; 1 + LCS(r1,r2)
 *  case_2. c1 is included in the solution but not c2; 0 + LCS(s1, r2)
 *  case_3. c2 is included in the solution but not c1; 0 + LCS(r1, s2)
 *  case_4. c1 and c2 both are not included in the solution; 0 + LCS(r1, r2)
 *  
 *  
 *
 *Explanation for case_1:
 *=========================
 *   LCS(A^X, A^Y) = A^LCS(X, Y), where ^ denotes string concatenation.
 *   This means that if the first characters of two strings (c1 and c2) are equal, 
 *    i.e. if c1==c2
 *    then LCS(c1r1, c2r2) = 1 + LCS(r1, r2), where r1 and r2 are the remaining substrings of s1 and s2.
 *  
 *  
 * example string:
 *   s1 = zpabcde ;     s1 = zp[a]bcde
 *   s2 =  nayaablmdxb; s2 =  n[a]yaablmdxb
 *
 * Let's say i in pointing to marked [a] of s1, and j is pointing to marked [a] of s2.
 *   s1 = p1 + [a] + r1
 *   s2 = p2 + [a] + r2
 *
 * Let's consider the following situations:
 *
 *  Sit#1. if we find Longest common subsequence because of including part_before i.e. LCS(p1, p2)
 *    still extra 1 will be added because of suffix [a] of s1 and s2.
 *     So, final LCS = LCS(p1, p2) + 1
 *
 *  Sit#2. if we find Longest common subsequence because of including r1,r2 i.e. LCS(r2,r2)
 *  still extra 1 will be added because of prefix [a] of s1 and s2.
 *    So, final LCS = 1 +  LCS(r2,r2)
 *	
 *
 *LCS(c1r1,c2r2)  will be 1 + LCS(r1,r2) only when c1==c2.
 *
 *
 *Explanation for case_2 and case_3
 *==================================
 *Since either c1 or c2 is included in the solution, means c1 is not equal to c2.
 *Max (0 + LCS(r1, s2), 0 + LCS(r2, s1))
 *
 *
 *
 *Explanation for case_4 
 *======================
 *c1 and c2 both are not included in the solution and this can come as 
 *repeated recursive sub-problem of case_2 and case_3, so the solution 
 *from case_4 will always be less than the value returned from case_2 or case_3.
 *so we can ignore the case_4.
 *Note: {@link https://www.youtube.com/watch?v=lkL_--Bnxi4}
 *
 *
 *
 *  So, Final recursive formulation  will be as follows:
 *  ========================================================
 *  BASE CASE: If any of S1 or S2 is empty then LCS will be 0.
 *
 *  CASE A: if c1==c2 then LCS(s1,s2) = 1 + LCS(r1,r2)
 *
 *  CASE B: if c1 != c2 then LCS(s1,s2) = Max (LCS(r1, s2), LCS(r2, s1), LCS(r1, r2))
 *   here we can ignore the LCS(r1,r2), 
 *   means when c1 != c2 then LCS(s1, s2) =  Max (LCS(r1, s2), LCS(r2, s1)))
 *
 *
 * </pre>
 *
 ***/

public class LongestCommonSubSequence1 {

	public static void main(String[] args) {

		String str1 = "LAGGTAB";
		String str2 = "LGXYXAYB";

		System.out.println(recursiveLCS(str1.toCharArray(), str2.toCharArray(), 0, 0));
		System.out.println(dpLCS(str1.toCharArray(), str2.toCharArray()));

		int[][] cache = new int[str1.length()][str2.length()];
		for (int i = 0; i < cache.length; i++) {
			Arrays.fill(cache[i], -1);
		}
		recursiveLCSMemo(str1.toCharArray(), str2.toCharArray(), 0, 0, cache);

	}

	/**
	 * - The recursive cases cover the possibilities of including or excluding
	 * characters c1 and c2.
	 * 
	 * - The time complexity of this approach is O(2^min(s1.length, s2.length)) due
	 * to the exponential number of subsequences.
	 * </pre>
	 */

	private static int recursiveLCS(char[] s1, char[] s2, int i, int j) {

		// LCS of empty string is 0
		if (i == s1.length || j == s2.length) {
			return 0;
		}

		if (s1[i] == s2[j]) {
			return 1 + recursiveLCS(s1, s2, i + 1, j + 1);
		} else {
			return Math.max(recursiveLCS(s1, s2, i + 1, j), recursiveLCS(s1, s2, i, j + 1));
		}

	}

	/**
	 *
	 * The memoization table needs to store results for all possible
	 * subproblems(possible LCS) defined by the indices i and j, which can range
	 * from 0 to m for the first array and from 0 to n for the second array.
	 * Therefore, the size of the memoization table will be m x n, where m is the
	 * length of arr1 and n is the length of arr2.
	 *
	 * The lcs function uses this table to store and retrieve the results of
	 * subproblems defined by indices i and j.
	 *
	 * int[][] cache can be hashmap as well.
	 *
	 */
	private static int recursiveLCSMemo(char[] s1, char[] s2, int i, int j, int[][] cache) {

		// LCS of empty string is 0
		if (i == s1.length || j == s2.length) {
			return 0;
		}

		// Check if the result is already computed
		if (cache[i][j] != -1) {
			return cache[i][j];
		}

		if (s1[i] == s2[j]) {
			cache[i][j] = 1 + recursiveLCSMemo(s1, s2, i + 1, j + 1, cache);
		} else {

			cache[i][j] = Math.max(recursiveLCSMemo(s1, s2, i + 1, j, cache),
					recursiveLCSMemo(s1, s2, i, j + 1, cache));
		}

		return cache[i][j];

	}

	/**
	 * <pre>
	 * Let's convert the Recursive formulation to bottom-up DP table approach.
	 *
	 * In recursive solution we used two pointers i and j, i points to char of s1 and j points to
	 * char of s2. Since we have two pointers or two strings to deal with so we will use 2-d array.
	 *
	 * Meaning of storage: dp[i][j]
	 * ============================
	 *  dp[i][j] represents the LCS of the first 'i' characters of string S1 i.e. s1[0..i]
	 *  and the first j characters of string S2 i.e. s2[0..j]. That is, LCS of substring of
	 *  s1 and s2 ending at i and j respectively.
	 *
	 *
	 * The table is filled using the formula:
	 *
	 * CASE_A:if any of string is empty (since we are starting with bottom-up approach,
	 * so initially string is considered empty)
	 * dp[i][j] = 0 ; i==0 or j==0 ;
	 *
	 * CASE_B: if current chars from both the strings are equal i.e. s1[i] == s2[j]; then
	 * LCS including the current char will be 1 plus LCS already calculated before considering
	 * the current char of s1 and s2.
	 *
	 * dp[i][j] = 1 + dp[i-1][j-1]
	 *
	 * CASE_C: if current chars from both the strings are are not-equal i.e. s1[i] != s2[j];
	 * means we need to pick the max of  LCS(s1[0...i-1], s2[0..j]) &  LCS(s1[0..i], s2[0..j-1])
	 *
	 *  LCS(s1[0..i-1], s2[0..j]) is present at dp[i-1][j]
	 *  LCS(s1[0..i], s2[0..j-1]) is present at dp[i][j-1]
	 *
	 *  dp[i][j] = Max(dp[i-1][j], dp[i][j-1])
	 *
	 * </pre>
	 */
	public static int dpLCS(char[] s1, char[] s2) {

		// Note:since we are creating dp with one extra length to accommodate empty
		// string condition, so we need to take care while pointing to index of s1, s2
		int[][] dp = new int[s1.length + 1][s2.length + 1];

		// CASE_A: if either of the string is empty i.e. dp[0][j] or dp[i][0] put 0;
		// but we can ignore the CASEA in java as in java default value of int is 0
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for (int j = 0; j < dp[0].length; j++) {
			dp[0][j] = 0;
		}

		for (int i = 1; i < dp.length; i++) {

			for (int j = 1; j < dp[0].length; j++) {

				// CASE_B
				if (s1[i - 1] == s2[j - 1]) {

					dp[i][j] = 1 + dp[i - 1][j - 1];
				} else {
					// CASE_C
					dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
				}

			}
		}

		System.out.println(printLCS(s1, s2, dp));
		return dp[s1.length][s2.length];

	}

	private static String printLCS(char[] s1, char[] s2, int[][] dp) {

		StringBuilder sb = new StringBuilder();

		int i = s1.length;
		int j = s2.length;

		while (i > 0 && j > 0) {

			// since c1 and c2 are equal; and in dp tabulation we have decremented
			// both i and j; so here also we will adjust both i and j to reach to the
			// previous state i.e. previous cell which contributed to the solution
			if (s1[i - 1] == s2[j - 1]) {
				sb.append(s1[i - 1]);
				i--;
				j--;
			} else if (dp[i - 1][j] > dp[i][j - 1]) {
				// means we have come from the upper cell, so we will go back to upper cell
				i--;
			} else {
				j--;
			}

		}
		return sb.reverse().toString();
	}
}
