package edu.algods.dp.knapsack;

/**
 * 0/1 Knapsack Identification:
 * 
 * 1. Optimal aspects need to be found like max, min etc
 * 
 * 2. Choices are available like to pick or not
 * 
 * Above both conditions are typical DP condition.
 * 
 * Sample:
 * 
 * weight[] : {1,3,4,5}
 * 
 * value[] : {1,4,5,7}
 * 
 * kanpsackCapacity : 7
 * 
 */
public class ZeroOneKnapsack {

	/*
	 * Hypothesis : zeroOneKnapsackWithRecursion(weight,value,itemCount,
	 * knapsackCapacity) --> maxProfit
	 * 
	 * Here:output 'maxProfit' needs calculation and is not so obvious.
	 * 
	 * IN GENERAL : We can think BASE_CONDITION as smallest valid input.
	 * 
	 * Base condition: can be deduced as per choice diagram, refer
	 * ZeroOneKnapsackChoiceDiagram.pdf
	 * 
	 * Base condition: weight_array_empty OR knapsack_capacity is zero then MAX
	 * profit is zero.
	 * 
	 * 
	 */
	public static int zeroOneKnapsackWithRecursion(int weight[], int value[], int itemCount, int knapsackCapacity) {

		// base condition
		if (knapsackCapacity == 0 || itemCount == 0) {
			return 0;
		}

		// as per Pascal's double counting choice diagram if current weight is greater
		// than capacity,
		// means we cannot include the item
		if (weight[itemCount - 1] > knapsackCapacity) {
			return zeroOneKnapsackWithRecursion(weight, value, itemCount - 1, knapsackCapacity);

		} else if (weight[itemCount - 1] <= knapsackCapacity) {

			final int profitByIncludingCurrentItem = value[itemCount - 1] + zeroOneKnapsackWithRecursion(weight, value,
					itemCount - 1, knapsackCapacity - weight[itemCount - 1]);

			final int profixByExcludingCurrentItem = zeroOneKnapsackWithRecursion(weight, value, itemCount - 1,
					knapsackCapacity);

			return Math.max(profitByIncludingCurrentItem, profixByExcludingCurrentItem);
		}
		return Integer.MIN_VALUE;

	}

}
