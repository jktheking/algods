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
	void printCombinationUsingPascalIdentityByFixingPos(int positionCount, int r);

	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items at 'n' given positions.
	 * 
	 */
	void printCombinationUsingPascalIdentityExpansionByFixingPos(int positionCount, int r);

	
	/**
	 * @param input can contain duplicate chars
	 * 
	 * */
	void printCombinationByUsingPIEAndHandlingDuplicates(String input, int r);
	
	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items at 'n' given positions.
	 * 
	 */
	void printCombinationUsingPermutationByFixingPos(int positionCount, int r);

	/**
	 * print all the possible combinations by placing 'r' distinct items at 'n'
	 * given positions.
	 * 
	 */
	void printCombinationUsingPermutationByFixingInput(int positionCount, String item);

	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items in 2D array.
	 * 
	 * @param r : number of identical items
	 * @parm rows : row count
	 * @parm cols : column count
	 * 
	 */
	void printCombinationIn2DArrayUsingPIEByFixingPos(int rows, int cols, int r);

	/**
	 * print all the possible combinations by applying permutation/arrangement of
	 * 'r' identical items in 2D-array by elongating the 2D-array in 1D positions
	 * 
	 * Note: Elongating 2D-positions in 1D-positions make sense when we want to fix
	 * the position. Becuase position will be passed as method parameter. And it is
	 * easy to reach the end of 1D-array recursively by incrementing the 'posToFix'
	 * method parameter than to reach the end cell of 2D-array recusively by
	 * synchronizing and incrementing the two method parameters 'rowPosToFix' &
	 * 'colPosToFix'.
	 * 
	 * 
	 * @param r : number of identical items
	 * @parm rows : row count
	 * @parm cols : column count
	 * 
	 */
	void printCombinationIn2DElongatedArrayUsingPIEByFixingPos(int rows, int cols, int r);
	
	void printNQueenCombinationUsingPIEByFixingPos(int n);
}
