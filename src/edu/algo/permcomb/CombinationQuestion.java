package edu.algo.permcomb;

public interface CombinationQuestion {

	CombinationQuestion INSTANCE = new CombinationSolution();
	
	/**
	 * Get all possible combinations that can be formed by picking atmost one char
	 * from each group.
	 * 
	 * Example ['abc' ]
	 * 
	 * 
	 */
	void printGroupCombination(String[] groups);
}
