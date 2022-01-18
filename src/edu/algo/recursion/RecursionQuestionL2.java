package edu.algo.recursion;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * </pre>
	 */
	void printAbbreviation1(String input);

	void printAbbreviation2(String input);

	/**
	 * You are given a list of words, and allowed set of alphabets(might be
	 * repeating) and score of every alphabet from a to z.
	 * 
	 * 2. You have to find the maximum score of any valid set of words formed by
	 * using the given alphabets.
	 * 
	 * 3. A word can not be used more than one time.
	 * 
	 * 4. Each alphabet can be used only once.
	 * 
	 * 5. It is not necessary to use all the given alphabets.
	 * 
	 */
	int scoreBased01MaxKnapsack(List<String> input, Map<Character, Integer> allowedCharFreqency,
			Map<Character, Integer> score);

	int josephusProbelm(int n, int k);

	void printNumbersInLexicographicOrderUptoN(int n);

	int getMaxGoldFromIsLandsOnMaze(int[][] maze);

	/**
	 * Following are the rules of Sudoku for a player.
	 * 
	 * 1. In all 9 sub matrices 3×3 the elements should be 1-9, without repetition.
	 * 
	 * 2. In all rows there should be elements between 1-9 , without repetition.
	 * 
	 * 3. In all columns there should be elements between 1-9 , without repetition.
	 * 
	 */
	void solveSudoku(int[][] sudokuPuzzle);

	int[][] generateSudoku1(int countOfMissingElements);

	int[][] generateSudoku2(int countOfMissingElements);

	void solveCrossWord(char[][] crossWordPuzzle, List<String> input);

	/**
	 * 1. You are given three strings s1, s2 and s3.
	 * 
	 * 2. First two are supposed to add and form third. s1 + s2 = s3
	 * 
	 * 3. You have to map each individual character to a digit, so that the above
	 * equation holds true.
	 * 
	 * Constraint: 1 <= length of s1,s2,s3 <= 10
	 * 
	 * 4.Each character must be mapped with a number between 0 to 9. Each number can
	 * be used once only. For example - if 9 is mapped to 'a', then it cannot be
	 * mapped to any other letter. There will be one to one mapping..
	 */
	void solveCryptArithmeticPuzzle(String s1, String s2, String s3);

	/**
	 * <pre>
	 * 1. You are given an integer n, which represents n friends numbered from 1 to n.
	 * 2. Each one can remain single or can pair up with some other friend.
	 * 3. You have to print all the configurations in which friends can remain single or can be paired up.
	 * 4. We cannot make 2 different permutations like 1-2 and 2-1. Only 1-2 is valid.
	 * </pre>
	 */
	void printFriendsPairing(int friends);

	/**
	 * 1. You are given two integers n and k, where n represents number of elements
	 * and k represents number of subsets.
	 * 
	 * 2. You have to partition n elements in k subsets and print all such
	 * configurations.
	 * 
	 */
	void printKpartitionSubsets1(int n, int k);

	void printKpartitionSubsets2(int n, int k);

	void printPalindromicPermuation(String input);

	/**
	 * 1. You are given a string of length n.
	 * 
	 * 2. You have to partition the given string in such a way that every partition
	 * is a palindrome.
	 */
	void printPalindormicPartition(String input);

	/**
	 * 1. You are given an array of n distinct integers.
	 * 
	 * 2. You have to divide these n integers into k non-empty subsets such that sum
	 * of integers of every subset is same.
	 * 
	 * 3. If it is not possible to divide, then print "-1".
	 * 
	 */
	void printKpartitionEqualSumSubsets(int[] input, int k);

	/**
	 * 1. You are given a string and a pattern.
	 * 
	 * 2. You've to check if the string is of the same structure as pattern without
	 * using any regular expressions.
	 * 
	 * input: mzaddytzaddy
	 * 
	 * pattern: abcb
	 **/
	void printPatternMappings(String input, String pattern);

	/**
	 * 1. You are given n space separated strings, which represents a dictionary of
	 * words.
	 * 
	 * 2. You are given another string which represents a sentence.
	 * 
	 * 3. You have to print all possible sentences from the string, such that words
	 * of the sentence are present in dictionary.
	 * 
	 */
	void wordBreakByFixingInput1(String input, Set<String> dictionary);

	void wordBreakByFixingInput2(String input, Set<String> dictionary);

	/**
	 * 1. You are given a string, which represents an expression having only opening
	 * and closing parenthesis.
	 * 
	 * 2. You have to remove minimum number of parenthesis to make the given
	 * expression valid.
	 * 
	 * 3. If there are multiple answers, you have to print all of them.
	 * 
	 */
	void printExpressionByRemovingMinimumInvalidParenthesis1(String expression);

	void printExpressionByRemovingMinimumInvalidParenthesis2(String expression);

	/**
	 * 1. You are given a string which represents digits of a number.
	 * 
	 * 2. You have to create the maximum number by performing at-most k swap
	 * operations on its digits.
	 * 
	 */
	void printLargestNumberPossibleAfterKSwaps(int number, int k);

	/***
	 * TUG OF WAR
	 * 
	 * 1. You are given an array of n integers.
	 * 
	 * 2. You have to divide these n integers into 2 subsets such that the
	 * difference of sum of two subsets is as minimum as possible.
	 * 
	 * 3. If n is even, both set will contain exactly n/2 elements. If is odd, one
	 * set will contain (n-1)/2 and other set will contain (n+1)/2 elements.
	 * 
	 * 3. If it is not possible to divide, then print "-1".
	 * 
	 */
	void print2PartitionMinimumSumSubsets(int[] nums);

	/**
	 * 1. You are given a word (may have one character repeat more than once).
	 * 
	 * 2. You are given an integer k.
	 * 
	 * 3. You are required to generate and print all ways you can select k distinct
	 * characters out of the word.
	 * 
	 */
	void printKdistinctSelection(String input, int k);

	/**
	 * 1. You are given a word (may have one character repeat more than once).
	 * 
	 * 2. You are given an integer k.
	 * 
	 * 3. You are required to generate and print all k length words (of distinct
	 * chars) by using chars of the word.
	 */
	void printKdistinctArrangementWithoutCharRepitition(String input, int k);

	/**
	 * 1. You are given a word (may have one character repeat more than once).
	 * 
	 * 2. You are given an integer k.
	 * 
	 * 3. You are required to generate and print all k length words by using chars
	 * of the word.
	 * 
	 **/
	void printKdistinctArrangementWithCharRepitition(String input, int k);

	/**
	 * 1. You are given a number n, representing the count of coins.
	 * 
	 * 2. You are given n numbers, representing the denominations of n coins.
	 * 
	 * 3. You are given a number "amt".
	 * 
	 * 4. You are required to calculate and print the combinations of the n coins
	 * (non-duplicate) using which the amount "amt" can be paid.
	 * 
	 */
	void printCoinChangeCombinationWithoutDuplication(int[] coins, int amount);

	/**
	 * 1. You are given a number n, representing the count of coins.
	 * 
	 * 2. You are given n numbers, representing the denominations of n coins.
	 * 
	 * 3. You are given a number "amt".
	 * 
	 * 4. You are required to calculate and print the combinations of the n coins
	 * (same coin can be used again any number of times) using which the amount
	 * "amt" can be paid.
	 * 
	 */
	void printCoinChangeCombinationWithDuplication1(int[] coins, int amount);
	
	void printCoinChangeCombinationWithDuplication2(int[] coins, int amount);

}
