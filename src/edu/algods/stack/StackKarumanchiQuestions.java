package edu.algods.stack;

public interface StackKarumanchiQuestions<T extends Comparable<T>> {

	/**
	 * Problem-1 Discuss how stacks can be used for checking balancing of symbols.
	 * Algorithm:
	 * 
	 * a) Create a stack.
	 * 
	 * b) while (end of input is not reached) {
	 * 
	 * 1) If the character read is not a symbol to be balanced, ignore it.
	 * 
	 * 2) If the character is an opening symbol like (, [, {, push it onto the stack
	 * 
	 * 3) If it is a closing symbol like ),],}, then if the stack is empty report an
	 * error. Otherwise pop the stack.
	 * 
	 * 4) If the symbol popped is not the corresponding opening symbol, report an
	 * error.
	 * 
	 * }
	 * 
	 * c) At end of input, if the stack is not empty report an error
	 * 
	 * 
	 */
	boolean checkIfSymbolsAreBalanced(String expression);

}
