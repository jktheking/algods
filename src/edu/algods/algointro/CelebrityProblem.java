package edu.algods.algointro;

/**
 * 
 * Problem Statement: Among n people {0, 1, 2, . . . , n - 1}, a celebrity is
 * defined as someone who is known to everyone, but who knows no one. The
 * celebrity problem is to identify the celebrity, if one exists, by asking only
 * questions of the following form: Excuse me, do you know person X?
 * 
 * How do you represent the relations?
 * 
 * By using 2 dimensional array of nXn.
 * 
 * On columns we can assume celebrity candidate, and on rows we can present the
 * corresponding celebrity condition.
 * 
 * We can have at most one celebrity, So at most one celebrity column with all
 * 1's and corresponding row with all ZERO's except of diagonal.
 * 
 */
public class CelebrityProblem {

	public static void main(String[] args) {

		// col index 2 is celebrity
		int[][] celebrityMatrix = new int[][] { //
				{ 1, 1, 1, 1, 1 }, //
				{ 1, 1, 1, 0, 0 }, //
				{ 0, 0, 1, 0, 0 }, //
				{ 1, 0, 1, 1, 0 }, //
				{ 1, 0, 1, 0, 1 } //

		};

		System.out.println("celebrity_index:" + findCelebrityByBruteForce(celebrityMatrix));
		
		System.out.println("celebrity_index:" + findCelebrityUsingTwoPointer(celebrityMatrix));

	}

	/**
	 * As we know, Celebrity Matrix contains, at most one celebrity column. For
	 * celebrity verification we should check that the column contains all 1's and
	 * corresponding rows contains all 0's except the diagonal element.
	 * 
	 * So, we will verify first-row with first-column and so on. We can take a row
	 * and verify with the row-transpose ie. column
	 * 
	 * Time complexity is n(n-1). Why n-1?? Because we are ignoring the diagonal
	 * element as we don't need to check if the celebrity knows her/him self or not.
	 * 
	 * Time Complexity: O(n^2)
	 * 
	 * Space Complexity: O(1)
	 * 
	 */

	public static int findCelebrityByBruteForce(int[][] celebrityMatrix) {

		for (int i = 0; i < celebrityMatrix.length; i++) {

			boolean eliminated = false;
			// let's check if the current i i.e. column is celebrity or not.
			for (int j = 0; j < celebrityMatrix[0].length; j++) {

				// we can ignore the verification of diagonal element as diagonal contains
				// relation between self.
				if (i == j)
					continue;

				// if on column we find 0 and corresponding row we find 1 means the
				// candidate column 'celebrityMatrix[][i]' is not a celebrity.
				if (celebrityMatrix[j][i] == 0 || celebrityMatrix[i][j] == 1) {
					eliminated = true;
					break;
				}
			}
			if (!eliminated) {
				return i;
			}
		}

		return Integer.MIN_VALUE;
	}

	/**
	 * 
	 * We exploit the following key observation:
	 * 
	 * If person A knows person B, then person A is not a celebrity;
	 * 
	 * if person A does not know person B, then person B is not a celebrity.
	 * 
	 * 
	 * As we know at most only one person can be celebrity among the people of given
	 * group. So, here Goal is to figure-out which one of the column is celebrity
	 * candidate.
	 * 
	 * Here we will check the relation of each person on column with other person on
	 * row, this will help us to figure-out candidate column
	 * 
	 * Here we will iterate over all the column indices(or person) and check its
	 * relation with next row index..
	 * 
	 * let's pick the 0-index row-person and 1-index column-person:
	 * 
	 * if 0-index row person knows 1-index column person: then
	 * celebrityMatrix[0][1]==1, in such case 0-index person cannot be celebrity,
	 * but 1-index person may be. Now in next iteration we check whether 1-index
	 * person is celebrity candidate or not by comparing with 2-index person like :
	 * if celebrityMatrix[1][2]==1 or not.
	 * 
	 * 
	 * 
	 * The elimination phase i.e finding celebrity candidate requires exactly n - 1
	 * checks, since each check reduces the size of the list by 1.
	 * 
	 * In the verification phase, we perform n - 1 checks for the person n, and also
	 * check remaining n - 1 persons once. That is n-1 check for column and n-1
	 * check for row as we are excluding the diagonal element. Thus this phase
	 * requires at the most 2(n - 1) checks, possibly fewer if n is not a celebrity.
	 * 
	 * So the total number of checks are 3(n - 1).
	 * 
	 * Time Complexity: O(n).
	 * 
	 * Space Complexity: O(1).
	 * 
	 * 
	 */
	public static int findCelebrityUsingTwoPointer(int[][] celebrityMatrix) {

		int celebrityCandidate = findCelebrityCandidateByEliminationUsingTwoPointer(celebrityMatrix);

		for (int i = 0; i < celebrityMatrix.length; i++) {

			// self index
			if (i == celebrityCandidate)
				continue;

			// if celebrity-column contains zero and celebrity-row contains 1 then eliminate
			// it
			if (celebrityMatrix[i][celebrityCandidate] == 0 || celebrityMatrix[celebrityCandidate][i] == 1) {
				return Integer.MIN_VALUE;
			}

		}

		return celebrityCandidate;
	}

	/**
	 * The elimination phase i.e finding celebrity candidate requires exactly n - 1
	 * checks, since each check reduces the size of the list by 1. Let's say we have
	 * A,B,C,D,E elements and A is the celebrity candidate then comparison will go
	 * like A--B, A--C, A--D, A--E so total comparison is n-1.
	 * 
	 * Time complexity : O(n-1)
	 */
	private static int findCelebrityCandidateByEliminationUsingTwoPointer(int[][] celebrityMatrix) {

		int personA = 0;
		int personB = 1;

		for (int i = 2; i < celebrityMatrix.length; i++) {

			// If A knows B then A cannot be celebrity as celebrity does not know anyone.
			if (celebrityMatrix[personA][personB] == 1) {

				// A got eliminated, so add next person on A
				personA = i;
			} else {
				// Since A does not know B, means B cannot be celebrity as celebrity is know by
				// everyone.

				// B got eliminated, so add next person on B
				personB = i;
			}
		}

		// whoever is getting eliminated is getting replaced by next index
		return personA == celebrityMatrix.length ? personB : personA;
	}
}
