package edu.algo.recursion;

public interface RecursionQuestionL2 {
	
	RecursionQuestionL2 INSTANCE = new RecursionSolutionL2();
	
	/**
	 * <pre>
	 * Print powerset by placing 1 for characters which are absent. If 1 appears in
	 * consecutive order then need to be summed up.
	 * 
	 * Sample Input:  abc
	 * Sample Output: abc, ab1, a1c, a2, 1bc, 1b1, 2c, 3
	 * Power set    : abc, ab_, a_c, a__, _bc, _b_, __c, ___  
	 *              : 000, 001, 010, 011, 100, 101, 110, 111  
	 *</pre>                
	 * */
	void printAbbreviation1(String input);
	void printAbbreviation2(String input);

}
