package edu.algods.stack;


public class KarumanchiTest {
	
	public static void main(String[] args) {
		
		
		checkIfSymbolsAreBalanced();
	}
	
	
	
	private static void checkIfSymbolsAreBalanced() {
		StackKarumanchiQuestions<String> stackQuestions = new StackKarumanchiSolutions<>(false);
		final String expression = "( ( a + b * { x + y } ) + <  a - b > )";
		System.out.println("\nis given expression balanced ? : "+stackQuestions.checkIfSymbolsAreBalanced(expression));
		
		
	}

}
