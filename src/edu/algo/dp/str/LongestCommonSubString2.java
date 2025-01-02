package edu.algo.dp.str;

import java.util.Arrays;

/**
 * <pre>
 * Since it's the class of substring ,as all the problem space can be generated
 * using substring variant of subset, so we will use 'S = pcr' and will try to
 * include/exclude with contiguousPrefix on all the native cases of the problem.
 *
 * Two fundamental choices:
 * ----------------------------------------------------
 * 1. apply currentElement with running contiguousPrefix
 * 2. start the contiguousPrefix from currentElement itself
 *-------------------------------------------------------
 *
 *
 * Let's explore all possibilities of recursive decision:
 *
 * String = current_char + remaining string
 *
 * s1 = p1c1r1
 * s2 = p2c2r2
 *
 *LCS function structure definition:
 *===================================
 *Let's define the LCS as a function of s1,s2 and contiguousCount variable; LCS(contiguousCount, i, j)
 * contiguousCount: represents LCS length because of prefix of s1 and s2 respectively. i.e p1, p2
 *  i : represents current char of s1, i.e. c1
 *  j : represents current char of s2, i.e. c2
 *
 *
 * there can be 4 possibilities of decision tree :
 * 
 *  case_1. c1 and c2 both be included in the solution; LCS(r1,r2, contiguousCount + 1)
 *  - means c1 and c2 are equal
 *  
 *  case_2. c1 is included in the solution but not c2; LCS(s1, r2, 0)
 *  case_3. c2 is included in the solution but not c1; LCS(r1, s2, 0)
 *  case_4. c1 and c2 both are not included in the solution; LCS(r1, r2, 0)
 *  
 *  case_2, case_3, case_4: belongs to the situation where  current char in consideration is not 
 *  equal i.e. c1 != c2
 *
 *  case_4. would come as child in decision-tree (sub-problem)  of caseA#2 and caseA#3
 *
 *
 *   Why do we need to take contiguousCount variable ?
 *   - Because LCS can exist in part_before(p1, p2) and c1r1, c2r2 does not contain LCS.
 *
 *
 *  ========================================================
 *  BASE CASE: If any of S1 or S2 is empty then LCS will be 0.
 *
 *  if c1==c2 then
 *   include_LCS = LCS(r1,r2, contiguousCount + 1);
 *
 *   further need to calculate below two possibilities irrespective of whether c1==c2
 *
 *   excldue_C1_S1 = LCS(r1,s2, 0) -> we need to reset the contiguousCount, as substring is contiguous
 *   excldue_C2_S2 = LCS(s1,r2, 0) -> we need to reset the contiguousCount, as substring is contiguous
 *
 *   finalLCS = Max(contiguousCount, include_LCS, excldue_C1_S1, excldue_C2_S2)
 *
 *
 * </pre>
 *
 ***/

public class LongestCommonSubString2 {

	public static void main(String[] args) {

		String s1 = "yyzabcpdex";
		String s2 = "yyczxabc";

		// String s1 = "abc";
		// String s2 = "abc";

		System.out.println(recursiveLCS(s1.toCharArray(), s2.toCharArray()));
		System.out.println(tabulationLCS("ABCDGH".toCharArray(), "ACDGHR".toCharArray()));

	}

	private static int recursiveLCS(char[] s1, char[] s2) {

		return recursiveLCS1(s1, s2, 0, 0, 0);

	}

	private static int tabulationLCSPractice(char[] s1, char[] s2) {

		return 0;
	}

	private static int recursiveLCS(char[] s1, char[] s2, int i, int j, int contiguousCount) {

		if (i == s1.length || j == s2.length) {
			return contiguousCount;
		}

		// if current char of s1 and s2 are equal, means we will extend the
		// running contiguousCount

		int includeC1ExcludeC2 = 0;
		int includeC2ExcludeC2 = 0;
		if (s1[i] == s2[j]) {
			// here we can override the input contiguousCount with result, as
			// contiguousCount
			// returned from the result will be greater than the input contiguousCount
			contiguousCount = recursiveLCS(s1, s2, i + 1, j + 1, contiguousCount + 1);

		} else {
			// let's generate the solution space by
			// 1. including c1 and excluding c2
			// 2. Including c2 and excluding c1
			// also, since c1 != c2, means we are not extending the running contiguousCount
			// rather we need to reset the same
			includeC1ExcludeC2 = recursiveLCS(s1, s2, i, j + 1, 0);
			includeC2ExcludeC2 = recursiveLCS(s1, s2, i + 1, j, 0);

		}

		return Math.max(contiguousCount, Math.max(includeC1ExcludeC2, includeC2ExcludeC2));

	}

	// We can optimize one variable, by using just contiguousCount, instead of
	// includeLCS.
	private static int recursiveLCS1(char[] s1, char[] s2, int i, int j, int contiguousCount) {

		if (i == s1.length || j == s2.length) {
			return contiguousCount;
		}

		int includeLCS = 0;

		if (s1[i] == s2[j]) {
			includeLCS = recursiveLCS1(s1, s2, i + 1, j + 1, contiguousCount + 1);
		}

		int excludeLCS_S1 = recursiveLCS1(s1, s2, i + 1, j, 0);
		int excludeLCS_S2 = recursiveLCS1(s1, s2, i, j + 1, 0);

		return Math.max(Math.max(contiguousCount, includeLCS), Math.max(excludeLCS_S1, excludeLCS_S2));

	}

	private static int recursiveLCSMemo(char[] s1, char[] s2) {

		int[][][] memo = new int[s1.length][s2.length][Math.min(s1.length, s2.length)];

		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++)
				Arrays.fill(memo[i], -1);
		}

		// return recursiveLCSMemo(s1, s2, 0, 0, 0, memo);

		return 0;

	}

//	private static int recursiveLCSMemo(char[] s1, char[] s2, int i, int j, int maxLength, int[][][] cache) {
//
//	}

	/**
	 * If we see closely the recursive solution, main variables are i and j.
	 * Max(contiguousCount) is the solution, so, if we store contiguousCount in dp
	 * cell and track the max(contiguousCount) using extra variable we can get the
	 * solution with just 2d-DP.
	 * 
	 * Thus,
	 * 
	 * Meaning of dp[i][j] = contains contiguous count. We need to maintain extra
	 * variable to track the max contiguousCount.
	 *
	 */
	private static int tabulationLCS(char[] s1, char[] s2) {
		// if s1 or s2 is empty, then contiguousCount will be zero,
		// means need to put ZERO in 0th row and 0th column.

		int[][] dp = new int[s1.length + 1][s2.length + 1];

		// base case of tabulation
		for (int i = 0; i < dp[0].length; i++) {
			dp[0][i] = 0;
		}
		for (int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}

		int solution = 0;
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				if (s1[i - 1] == s2[j - 1]) {
					// 1 + previous contigouousCount, i.e contiguousCount of p1,p2
					dp[i][j] = 1 + dp[i - 1][j - 1];
					solution = Math.max(solution, dp[i][j]);

				} else if (s1[i - 1] != s2[j - 1]) {
					// reset the contiguousCount
					dp[i][j] = 0;

				}

			}
		}

		for (int i = 0; i < dp.length; i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
		return solution;

	}

}
