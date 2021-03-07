package edu.algods.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StackKarumanchiSolutions<T extends Comparable<T>> implements StackKarumanchiQuestions<T> {

	public static final Map<String, String> SYMBOLS_MAP = constructSymbolsMap();

	private final boolean isArrayBasedStack;


	public StackKarumanchiSolutions(boolean isArrayBasedStack) {
		this.isArrayBasedStack = isArrayBasedStack;
	}

	private <S extends Comparable<S>> Stack<S> newStackInstance() {
		return isArrayBasedStack ? new ArrayBackedStack<>() : new LinkedListBackedStack<>();
	}

	private static Map<String, String> constructSymbolsMap() {
		Map<String, String> symbolsMap = new HashMap<>();
		symbolsMap.put(")", "");
		symbolsMap.put("{", "}");
		symbolsMap.put("[", "]");

		return symbolsMap;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean checkIfSymbolsAreBalanced(String expression) {

		String[] inputExpression = expression.split(" ");
		System.out.println(Arrays.toString(inputExpression));

		Stack<String> stack = newStackInstance();

		for (String str : inputExpression) {
			
			final String input = str.trim();
			
			if(SymbolPair.isSymbol(input)) {
				
				SymbolPair symbolPair = SymbolPair.partOf(input);
				
				//if input  is opening symbol then push to the stack
				if(symbolPair.isOpen(input)) {
					stack.push(input);
				//if input is closing then pop and compare if the popped is corresponding opening symbol or not
				}else if(symbolPair.isClose(input)){
					
					if(stack.isEmpty()) {
						return false;
					}
					
					String poppedSymbol = stack.pop();
					System.out.println(String.format("popped_opening_symbol:%s, input_closing_symbol:%s", poppedSymbol, input));
					if(!symbolPair.isOpen(poppedSymbol)) return false;
				}
			}

		}
        //if stack is empty means, expression is balanced
		return stack.isEmpty();
	}

}
