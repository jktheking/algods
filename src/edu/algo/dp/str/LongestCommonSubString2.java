package edu.algo.dp.str;

import java.util.Arrays;

/**
 * <pre>
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
 * caseA: c1 == c2
 * ==================================
 * there can be 4 possibilities of decision tree :
 *  caseA#1. c1 and c2 both be included in the solution; LCS(r1,r2, contiguousCount + 1)
 *  caseA#2. c1 is included in the solution but not c2; LCS(s1, r2, 0)
 *  caseA#3. c2 is included in the solution but not c1; LCS(r1, s2, 0)
 *  caseA#4. c1 and c2 both are not included in the solution; LCS(r1, r2, 0)
 *
 *  caseA#4. would come as child in decision-tree (sub-problem)  of caseA#2 and caseA#3
 *
 *   If we think thoroughly in the context of longest common substring then all the possibilities
 *   are valid caseA#1, caseA#2, caseA#3, caseA#4.
 *
 *   Why do we need to take contiguousCount variable ?
 *   - Because LCS can exist in part_before(p1, p2) and c1r1, c2r2 does not contain LCS.
 *
 *
 *
 *  caseB:c1 != c2
 *  ====================================
 *  caseB#1. c1 is included in the solution but not c2; LCS(s1, r2, 0)
 *  caseB#2. c2 is included in the solution but not c1;  LCS(r1, s2, 0)
 *  caseB#3. c1 and c2 both are not included in the solution; LCS(r1, r2, 0)
 *
 *  caseB#3. would come as sub-problem possibility of caseB#1 and caseB#2

 *
 *  In case of caseB all possibilities caseB#1, caseB#2, caseB#3 are valid.
 *
 *
 *  Now, we need to pick the max among  caseA#1, caseA#2, caseA#3, caseA#4, caseB#1, caseB#2, caseB#3.
 *  If we merge all the above cases together then the final formulation will be as follows:
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

		return recursiveLCS(s1, s2, 0, 0, 0);

	}

	// We can optimize one variable, by using just contiguousCount, instead of
	// includeLCS.
	private static int recursiveLCS(char[] s1, char[] s2, int i, int j, int contiguousCount) {

		if (i == s1.length || j == s2.length) {
			return contiguousCount;
		}

		int includeLCS = 0;

		if (s1[i] == s2[j]) {
			includeLCS = recursiveLCS(s1, s2, i + 1, j + 1, contiguousCount + 1);
		}

		int excludeLCS_S1 = recursiveLCS(s1, s2, i + 1, j, 0);
		int excludeLCS_S2 = recursiveLCS(s1, s2, i, j + 1, 0);

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
	 *
	 * Meaning of dp[i][j] = contains contiguous count. We need to maintain extra
	 * variable to track the max contiguousCount.
	 *
	 */
	private static int tabulationLCS(char[] s1, char[] s2) {

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
