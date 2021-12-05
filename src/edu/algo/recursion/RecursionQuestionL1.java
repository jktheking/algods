package edu.algo.recursion;

public interface RecursionQuestionL1 {

	/**
	 * Print numbers in decreasing order from n till 1 and then in increasing order
	 * till n.
	 */
	void printDecreasingIncreasing(int n);

	/**
	 * Print factorial of numbers till n
	 */
	int factorial(int n);

	/**
	 * Print output following the euler tour.
	 */
	void eulerTourOfRecursion(int n);

	/**
	 * Calculate a to the power of p
	 */
	void printAToThePowerOfP(int a, int p);

	/**
	 * Print the steps to solve tower of hanoi problem given 'n' discs
	 */
	void printTowerOfHanoiSteps(int n);

	/**
	 * Print elements of array.
	 */
	void printElementsOfArray(int[] array);

	/**
	 * Print elements of array in reverse order.
	 */
	void printElementsOfArrayInReverse(int[] array);

	/**
	 * print max element of array.
	 */
	void printMaxElementOfArray(int[] array);

	void printFirstIndexOfOccurrence(int[] array, int data);

	void printLastIndexOfOccurrence(int[] array, int data);

	void printAllIndexOfOccurrence(int[] array, int data);
	
	void printSubsequences(String input);
	
   void printGroupCombination(String[] groups);

}
