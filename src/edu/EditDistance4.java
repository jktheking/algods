package edu.algo.dp.str;

/**
 *
 * https://leetcode.com/problems/edit-distance/description/
 *
 * Since it's the class of subset,as all the problem space can be generated
 * using subset, so we will use 'S = cr' and will try to include/exclude with
 * all the native cases of the problem.
 *
 * <pre>
 * String structure:
 *
 * s1 = c1r1
 * s2 = c2r2
 *
 * Edit Distance Function : ED(s1, s2, i, j)
 * Note: i and j points to original string, there is no actual insertion or deletion or substitution
 *
 * if C1 == C2 && C1,C2 both are part of solution
 *
 * 0 + editDistance(s1, s2, i + 1, j + 1)
 *
 *  (C1 == C2 && C1,C2 are not part of solution) OR (C1 != C2)
 *
 * Insertion: 1 + editDistance(s1, s2, i, j + 1)
 *
 *  :post insertion of c2 at ith index, i will not move ahead
 *   as still we have r remaining chars to consider.
 *
 * Deletion: 1 + editDistance(s1, s2, i + 1, j)
 *
 *  :post deletion of c1 at ith index, s1 size increases by 1,
 *   so effectively remaining_length(r) will remain same.
 *
 * Substitution: 1 + editDistance(s1, s2, i + 1, j + 1)
 *
 * return Min(Insertion, Deletion, Substitution)
 *
 *BASE CASE:
 *---------
 *If i == s1.length() insert all remaining characters of s2.
 *If j == s2.length() delete all remaining characters of s1.
 *
 * </pre>
 *
 *
 */
public class EditDistance4 {

	public static void main(String[] args) {

	}

	private static int ed(char[] s1, char[] s2, int i, int j) {

		if (j == s2.length) {
			return s1.length - i; // Remaining characters in s1 need to be deleted
		}
		if (i == s1.length) {
			return s2.length - j; // Remaining characters in s2 need to be inserted
		}

		if (s1[i] == s2[j]) {
			// When s1[i] and s2[j] are equal, they will always be part of the optimal
			// solution. Matching characters incur no additional cost, and there is no
			// scenario where they won't participate in the solution. Even if s1 contains
			// repeated instances of characters from s2, the current pair of matching
			// characters will still contribute to the solution. So we don't need the
			// exclude calls for the case s1[i] == s2[j]
			return 0 + ed(s1, s2, i + 1, j + 1);

		}

		// We have attempted to insert the character c2 from s2 into s1.
		// After the insertion, s1 becomes "c2c1r1" and s2 remains "c2r2".
		// Now, the first characters of s1 and s2 match (both are c2).
		// We can proceed to compare the remaining parts:
		// - In s1, the remaining portion is "c1r1".
		// - In s2, the remaining portion is "r2".

		// insertion call is like include c1 and exclude c2
		int insertion = 1 + ed(s1, s2, i, j + 1);
		// deletion call is like exclude c1 and include c2
		int deletion = 1 + ed(s1, s2, i + 1, j);
		// replace call is like exclude both c1 and c2
		int replace = 1 + ed(s1, s2, i + 1, j + 1);

		return Math.min(replace, Math.min(insertion, deletion));

	}

	private static int ed1(char[] s1, char[] s2, int i, int j) {

		if (j == s2.length) {
			return s1.length - i; // Remaining characters in s1 need to be deleted
		}
		if (i == s1.length) {
			return s2.length - j; // Remaining characters in s2 need to be inserted
		}

		int incC1C2 = Integer.MAX_VALUE;
		if (s1[i] == s2[j]) {
			// When s1[i] and s2[j] are equal, they will always be part of the optimal
			// solution. Matching characters incur no additional cost, and there is no
			// scenario where they won't participate in the solution. Even if s1 contains
			// repeated instances of characters from s2, the current pair of matching
			// characters will still contribute to the solution. So we don't need the
			// exclude calls for the case s1[i] == s2[j]
			// In general exclude calls are harmless, so let's try the exclude calls
			incC1C2 = 0 + ed1(s1, s2, i + 1, j + 1);

		}

		// We have attempted to insert the character c2 from s2 into s1.
		// After the insertion, s1 becomes "c2c1r1" and s2 remains "c2r2".
		// Now, the first characters of s1 and s2 match (both are c2).
		// We can proceed to compare the remaining parts:
		// - In s1, the remaining portion is "c1r1".
		// - In s2, the remaining portion is "r2".

		// insertion call is like include c1 and exclude c2
		int insertion = 1 + ed1(s1, s2, i, j + 1);
		// deletion call is like exclude c1 and include c2
		int deletion = 1 + ed1(s1, s2, i + 1, j);
		// replace call is like exclude both c1 and c2
		int replace = 1 + ed1(s1, s2, i + 1, j + 1);

		return Math.min(incC1C2, Math.min(replace, Math.min(insertion, deletion)));

	}

	private static int edTab(char[] s1, char[] s2) {

		int[][] dp = new int[s1.length + 1][s2.length + 1];

		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {

				if (i == 0) {
					dp[i][j] = j;
				} else if (j == 0) {
					dp[i][j] = i;
				} else {

					if (s1[i - 1] == s2[j - 1]) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
					}
				}

			}
		}

		return dp[s1.length][s2.length];
	}

}
