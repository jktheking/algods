package edu.algo.permcomb;

import java.util.List;

public interface CombinationQuestion {

	CombinationQuestion INSTANCE = new CombinationSolution();

	/**
	 * Get all possible combinations that can be formed by picking atmost one char
	 * from each group.
	 * 
	 */
	List<String> getGroupCombination(String[] groups);

	void printGroupCombination(String[] groups);

	/**
	 * Print all possible combinations that can be formed by picking non-rpeated
	 * elements of input array and whose value is equal to targetValue.
	 * 
	 */
	void printTargetSumCombination(int[] input, int targetValue);

	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items at 'n' given positions.
	 * 
	 */
	void printCombinationUingIncExcByFixingPos(int positionCount, int r);
	
	/**
	 * print all the possible combinations by placing  'r' distinct items at 'n' given positions.
	 * 
	 */
	void printCombinationUsingPermutationByFixingInput(int positionCount, String item);

}
