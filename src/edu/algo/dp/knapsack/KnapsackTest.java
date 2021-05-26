package edu.algo.dp.knapsack;

public class KnapsackTest {

	public static void main(String[] args) {
		zeroOneKnapsackWithRecursion();
	}

	private static void zeroOneKnapsackWithRecursion() {

		int weight[] = { 1, 3, 4, 5 };
		int value[] = { 1, 4, 5, 7 };
		int knapsackCapacity = 7;
		// expected output is 9
		int maxProfit = ZeroOneKnapsack.zeroOneKnapsackWithRecursion(weight, value, weight.length, knapsackCapacity);
		System.out.println(maxProfit);
	}

}
