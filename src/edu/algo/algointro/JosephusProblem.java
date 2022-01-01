package edu.algo.algointro;

/**
 * Q40 - Josephus Circle: N people have decided to elect a leader by arranging
 * themselves in a circle and eliminating every Mth person around the circle,
 * closing ranks as each person drops out. Find which person will be the last
 * one remaining (with rank 1).
 */
public class JosephusProblem {

	public static void main(String[] args) {

		int[] persons = new int[] { 1, 2, 3, 4 };

		// works when k = 2
		josephusBitwise1(persons);
		josephusBitwise2(persons);

		// will work for any value of k.
		int k = 2;
		josephusCircleElimination1(persons, k);
		josephusCircleElimination2(persons, k);
		josephusCircleElimination3(persons, k);

		System.out.println(Integer.toBinaryString(-1));

		System.out.println(Integer.numberOfLeadingZeros(Integer.MAX_VALUE));
	}

	/**
	 * 
	 * @param n the number of people standing in the circle
	 * @return the safe position who will survive the execution f(N) = 2L + 1 where
	 *         N = 2^M + L and 0 <= L < 2^M
	 */
	private static void josephusBitwise1(int[] persons) {
		int n = persons.length;
		// find value of L for the equation
		int valueOfL = n - Integer.highestOneBit(n);
		int safePosition = 2 * valueOfL + 1;

		System.out.println("Safe person: " + persons[safePosition] + " is at position: " + safePosition);
	}

	private static void josephusBitwise2(int[] persons) {
		int n = persons.length;
		int position = ~Integer.highestOneBit(n * 2) & ((n << 1) | 1);

		System.out.println("Safe person: " + persons[position] + " is at position: " + position);
	}

	private static void josephusCircleElimination1(int[] persons, int k) {
		int safePosition = 0;
		for (int i = 1; i <= persons.length; ++i)
			safePosition = (safePosition + k) % i;

		safePosition = safePosition + 1;

		System.out.println("Safe person: " + persons[safePosition] + " is at position: " + safePosition);
	}

	private static void josephusCircleElimination2(int[] persons, int k) {
		int safePosition = recursiveJosephus(persons.length, k);
		System.out.println("Safe person: " + persons[safePosition] + " is at position: " + safePosition);
	}

	/**
	 * <pre>
	 * Assumption: n strats with 0.
	 * Hypothesis :  recursiveJosephus(n=5,k=3) ==> j5
	 * SUBSTITUTION: recursiveJosephus(n=4,k=3) ==> j4
	 * INDUCTION: deriving j5 using j4; see the pdf JosephusProblem.pdf
	 * 
	 * </pre>
	 * */
	public static int recursiveJosephus(int n, int k) {
		if(n==1) return 0;
		
		int j_nMinus1 = recursiveJosephus(n - 1, k);
		
		int j_n = (j_nMinus1 + k)%n;
		
		return  j_n;
	}

	private static void josephusCircleElimination3(int[] persons, int k) {
		int safePosition = recursiveJosephus1(persons.length, k);
		System.out.println("Safe person: " + persons[safePosition] + " is at position: " + safePosition);
	}

	private static int recursiveJosephus1(int n, int k) {
		if (n == 1)
			return 0;
		if (k == 1)
			return n - 1;
		if (k > n)
			return (recursiveJosephus1(n - 1, k) + k) % n;
		int cnt = n / k;
		int res = recursiveJosephus1(n - cnt, k);
		res -= n % k;
		if (res < 0)
			res += n;
		else
			res += res / (k - 1);
		return res;
	}

}