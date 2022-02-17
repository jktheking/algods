package edu.algo.dp;

public class DPLevel1Solution implements DPLevel1Question {

	public static void main(String[] args) {
		INSTANCE.printFibonacciUsingMemoization(8);
	}

	@Override
	public void printFibonacciUsingMemoization(int n) {

		System.out.println(getFibonacciUsingMemoization(n, new int[n + 1]));
	}

	private int getFibonacciUsingMemoization(int n, int[] solCache) {

		if (n == 0)
			return 0;

		if (n == 1)
			return 1;

		if (solCache[n] != 0) {
			return solCache[n];
		}

		return solCache[n] = getFibonacciUsingMemoization(n - 1, solCache)
				+ getFibonacciUsingMemoization(n - 2, solCache);

	}

}
