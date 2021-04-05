package edu.algods.stack;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class StackKarumanchiSolutions<T extends Comparable<T>> implements StackKarumanchiQuestions<T> {

	public static final Map<String, String> SYMBOLS_MAP = constructSymbolsMap();

	private static final String SINGLE_SPACE = " ";

	public static final Pattern UNICODE_SINGLE_SPACE = Pattern.compile("(?U)\s", Pattern.UNICODE_CHARACTER_CLASS);
	public static final Pattern UNICODE_MULTI_SPACE = Pattern.compile("(?U)\s+", Pattern.UNICODE_CHARACTER_CLASS);

	private final boolean isArrayBasedStack;

	public StackKarumanchiSolutions(boolean isArrayBasedStack) {
		this.isArrayBasedStack = isArrayBasedStack;
	}

	public <S extends Comparable<S>> Stack<S> newStackInstance() {
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

		String[] inputExpression = UNICODE_MULTI_SPACE.split(expression);
		System.out.println(Arrays.toString(inputExpression));

		Stack<String> stack = newStackInstance();

		for (String str : inputExpression) {

			final String input = str.trim();

			if (SymbolPair.isSymbol(input)) {

				SymbolPair symbolPair = SymbolPair.partOf(input);

				// if input is opening symbol then push to the stack
				if (symbolPair.isOpen(input)) {
					stack.push(input);
					// if input is closing then pop and compare if the popped is corresponding
					// opening symbol or not
				} else if (symbolPair.isClose(input)) {

					if (stack.isEmpty()) {
						return false;
					}

					String poppedSymbol = stack.pop();
					System.out.println(
							String.format("popped_opening_symbol:%s, input_closing_symbol:%s", poppedSymbol, input));
					if (!symbolPair.isOpen(poppedSymbol))
						return false;
				}
			}

		}
		// if stack is empty means, expression is balanced
		return stack.isEmpty();
	}

	@Override
	public String convertInfixToPostfix(String infixExpression) {

		String[] tokens = UNICODE_MULTI_SPACE.split(infixExpression);

		StringBuilder postfixExp = new StringBuilder();

		Stack<String> stack = newStackInstance();

		for (String t : tokens) {
			final String token = t.trim();

			Optional<Operator> operator = Operator.getIfPresent(token);

			Optional<SymbolPair> symbolPair = SymbolPair.getIfPresent(token);

			// if token is operand append the token to postfixExp
			if (operator.isEmpty() && symbolPair.isEmpty()) {

				postfixExp.append(token).append(SINGLE_SPACE);
				// if token is symbol
			} else if (symbolPair.isPresent()) {

				// if token symbol is closing symbol
				if (symbolPair.get().isClose(token)) {

					String popped_operator = stack.pop();
					// pop and append stack_operator to postfixExp until the opening parenthesis is
					// popped

					// stack contains only operator or opening symbol; so no need to check for if
					// the symbol is opening symbol or not
					while (!stack.isEmpty() && !SymbolPair.isSymbol(popped_operator)) {
						postfixExp.append(popped_operator).append(SINGLE_SPACE);
						popped_operator = stack.pop();
					}

				} else {

					// if token is opening parenthesis we cannot compare the precedence with
					// operator, so cannot pop the operator as well. Thus simply push the opening
					// symbol to stack
					stack.push(token);

				}

				// if token is operator
			} else if (operator.isPresent()) {
				// pop and append stack_operator to postfix_exp until lower_precedence_operator
				// OR opening_parenthesis OR EMPTY_STACK encountered

				while (!stack.isEmpty() && doesStackHaveHigherPrecedenceOperator(stack.peek(), operator.get()))
					postfixExp.append(stack.pop()).append(SINGLE_SPACE);

				stack.push(token);
			}

		}

		while (!stack.isEmpty())
			postfixExp.append(stack.pop()).append(SINGLE_SPACE);

		return postfixExp.toString();
	}

	private boolean doesStackHaveHigherPrecedenceOperator(String stack_operator_str, Operator operator) {
		Optional<Operator> stackOperator = Operator.getIfPresent(stack_operator_str);

		return stackOperator.isPresent() && stackOperator.get().getPrecedence() >= operator.getPrecedence();

	}

	@Override
	public double evaluatePostfixExpression(String postFixExpression) {

		String[] tokens = UNICODE_MULTI_SPACE.split(postFixExpression.trim());

		Stack<Double> stack = newStackInstance();

		for (String t : tokens) {

			final String token = t.trim();

			Optional<Operator> opContainer = Operator.getIfPresent(token);

			// if operand
			if (opContainer.isEmpty()) {
				stack.push(Double.valueOf(token));
			} else {

				Operator operator = opContainer.get();

				stack.push(evaluatePostfix(operator, stack));
			}

		}
		return stack.pop();
	}

	@Override
	public double infixEvalautionInSinglePass(String infixExpression) {

		String[] tokens = UNICODE_MULTI_SPACE.split(infixExpression.trim());

		Stack<Double> operandStack = newStackInstance();
		Stack<String> operatorStack = newStackInstance();

		for (String t : tokens) {

			String token = t.trim();

			Optional<Operator> opContainer = Operator.getIfPresent(token);

			Optional<SymbolPair> symbolContainer = SymbolPair.getIfPresent(token);

			if (isOperand(opContainer, symbolContainer)) {
				operandStack.push(Double.valueOf(token));

			} else if (symbolContainer.isPresent()) {

				if (symbolContainer.get().isOpen(token)) {
					operatorStack.push(token);
				} else {
					// if closing symbol
					String poppedOperator = operatorStack.pop();

					// operator stack only contains the opening symbol so no need to check if the
					// symbol is opening or not
					while (!operatorStack.isEmpty() && !SymbolPair.isSymbol(poppedOperator)) {
						operandStack.push(evaluatePostfix(Operator.operator(poppedOperator), operandStack));
						poppedOperator = operatorStack.pop();
					}

				}

			} else if (opContainer.isPresent()) {
				while (!operatorStack.isEmpty()
						&& doesStackHaveHigherPrecedenceOperator(operatorStack.peek(), opContainer.get())) {
					operandStack.push(evaluatePostfix(Operator.operator(operatorStack.pop()), operandStack));
				}

				operatorStack.push(token);
			}

		}

		while (!operatorStack.isEmpty()) {
			operandStack.push(evaluatePostfix(Operator.operator(operatorStack.pop()), operandStack));
		}

		return operandStack.pop();
	}

	private Double evaluatePostfix(Operator operator, Stack<Double> operandStack) {

		return switch (operator.getType()) {
		case BINARY -> {
			yield operator.evaluate(operandStack.pop(), operandStack.pop());
		}
		case UNARY -> {
			yield operator.evaluate(operandStack.pop());
		}
		case TERNARY -> {
			yield operator.evaluate(operandStack.pop(), operandStack.pop(), operandStack.pop());
		}
		};

	}

	private Double evaluatePrefix(Operator operator, Stack<Double> operandStack) {

		return switch (operator.getType()) {
		case BINARY -> {
			Double operand1 = operandStack.pop();
			Double operand2 = operandStack.pop();
			yield operator.evaluate(operand2, operand1);
		}
		case UNARY -> {
			yield operator.evaluate(operandStack.pop());
		}
		case TERNARY -> {
			Double operand1 = operandStack.pop();
			Double operand2 = operandStack.pop();
			Double operand3 = operandStack.pop();
			yield operator.evaluate(operand3, operand2, operand1);
		}
		};

	}

	private boolean isOperand(Optional<Operator> opContainer, Optional<SymbolPair> symbolContainer) {
		return opContainer.isEmpty() && symbolContainer.isEmpty();
	}

	@Override
	public String infixToPrefixUsingPostFix(String infixExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String infixToPrefixUsingTwoStack(String infixExpression) {
		String[] tokens = UNICODE_MULTI_SPACE.split(infixExpression.trim());

		Stack<String> operandStack = newStackInstance();
		Stack<String> operatorStack = newStackInstance();

		for (String t : tokens) {

			final String token = t.trim();

			Optional<SymbolPair> symbolContainer = SymbolPair.getIfPresent(token);
			Optional<Operator> opContainer = Operator.getIfPresent(token);

			if (isOperand(opContainer, symbolContainer)) {
				operandStack.push(token);
			} else if (symbolContainer.isPresent()) {

				if (symbolContainer.get().isOpen(token)) {
					operatorStack.push(token);
				} else {

					String poppedOperator = operatorStack.pop();

					// operator stack only contains the opening symbol so no need to check if the
					// symbol is opening or not
					while (!operatorStack.isEmpty() && !SymbolPair.isSymbol(poppedOperator)) {
						operandStack.push(preparePrefixOperand(Operator.operator(poppedOperator), operandStack));
						poppedOperator = operatorStack.pop();
					}
				}

				// token is operator
			} else if (opContainer.isPresent()) {
				while (!operatorStack.isEmpty()
						&& doesStackHaveHigherPrecedenceOperator(operatorStack.peek(), opContainer.get())) {
					operandStack.push(preparePrefixOperand(Operator.operator(operatorStack.pop()), operandStack));
				}

				operatorStack.push(token);

			}

		}

		while (!operatorStack.isEmpty()) {
			operandStack.push(preparePrefixOperand(Operator.operator(operatorStack.pop()), operandStack));

		}
		return operandStack.pop();
	}

	private String preparePrefixOperand(Operator operator, Stack<String> operandStack) {

		return switch (operator.getType()) {
		case BINARY -> {
			String operand2 = operandStack.pop();
			String operand1 = operandStack.pop();
			yield operator.getOperator() + SINGLE_SPACE + operand1 + SINGLE_SPACE + operand2;
		}

		case UNARY -> {
			yield operator.getOperator() + operandStack.pop();
		}

		case TERNARY -> {
			String operand3 = operandStack.pop();
			String operand2 = operandStack.pop();
			String operand1 = operandStack.pop();
			yield operator.getOperator() + operand1 + operand2 + operand3;
		}

		};
	}

	@Override
	public double evaluatePrefixExpression(String prefixEvalulation) {
		String[] tokens = UNICODE_MULTI_SPACE.split(prefixEvalulation.trim());

		Stack<Double> operandStack = newStackInstance();

		for (int i = tokens.length - 1; i >= 0; i--) {

			final String token = tokens[i];

			Optional<Operator> opContainer = Operator.getIfPresent(token);

			// if token is operand push to operandStack
			if (opContainer.isEmpty()) {
				operandStack.push(Double.valueOf(token));
			} else {
				operandStack.push(evaluatePrefix(opContainer.get(), operandStack));
			}

		}

		return operandStack.pop();
	}

	@Override
	public String postFixToInfix(String postFixExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String postFixToPrefix(String postFixExpression) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean checkIfGivenStringIsPalindromeUsingStack() {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Hypothesis:
	 * 
	 * reverseStackUsingRecursion([a,b,c,d,e]) --> SOLHYPO:[e,d,c,b,a]
	 * 
	 * top:= e;
	 * 
	 * reverseStackUsingRecursion[a,b,c,d] --> SOLSUB:[d,c,b,a]
	 * 
	 * Induction: SOLHYPO:[e,d,c,b,a] --> equivalent insert e at bottom of SOLSUB:
	 * [d,c,b,a]
	 * 
	 */
	@Override
	public void reverseStackUsingRecursion(Stack<T> inputStack) {

		// base case
		if (inputStack.isEmpty())
			return;

		T top = inputStack.pop();

		// hypothesis substitution
		reverseStackUsingRecursion(inputStack);

		// post recursion : inputStack becomes solutionStack
		insertAtBottom(inputStack, top);

	}

	/**
	 * Hypothesis:
	 * 
	 * insertAtBottom([d,c,b,a], e) --> SOLHYPO:[e,d,c,b,a]
	 * 
	 * top:= a;
	 * 
	 * 
	 * reverseStackUsingRecursion([d,c,b],e) --> SOLSUB:[e,d,c,b]
	 * 
	 * Induction: SOLHYPO:[e,d,c,b,a] ==> SOLSUB:[e,d,c,b] push 'a' i.e. top
	 * 
	 */
	private void insertAtBottom(Stack<T> inputStack, T top) {

		// base case: when inputStack is empty, insert the top
		if (inputStack.isEmpty()) {
			inputStack.push(top);
			return;
		}

		T tempTop = inputStack.pop();

		insertAtBottom(inputStack, top);

		// post recursion inputStack becomes solutionStack
		inputStack.push(tempTop);
	}

	// O(n^2)
	@Override
	public int[] findImmediateGreaterInRightUsingBurteForce(int[] input) {

		int[] result = new int[input.length];
		Arrays.fill(result, -1);

		for (int i = 0; i < input.length; i++) {
			for (int j = i + 1; j < input.length; j++) {
				if (input[i] < input[j]) {
					result[i] = input[j];
					break;
				}

			}

		}
		return result;
	}
	// [5, 0, 0, 4, 3, 7, 6, 9]
	// input 5, [0, 0, 4, 3, 7, 6, 9] --> [7]
	// input 0, [0, 4, 3, 7, 6, 9] --> [4]

	public void findImmediateGreaterInRightUsingRecursion(int first, int[] rightArr, int start, int end, int[] result) {

		result[start - 1] = calculateGreaterInArrUsingRec(first, rightArr, start, end);

		if (start > end) {
			return;
		}

		findImmediateGreaterInRightUsingRecursion(rightArr[start], rightArr, start + 1, end, result);
	}

	private int calculateGreaterInArrUsingRec(int first, int[] rightArr, int start, int end) {
		if (start > end) {
			return -1;
		}

		if (rightArr[start] > first)
			return rightArr[start];

		return calculateGreaterInArrUsingRec(first, rightArr, start + 1, end);

	}

	// 5 0 0 4 3 7 6 9
	@Override
	public int[] findImmediateGreaterInRightUsingStack(int[] input) {

		int[] result = new int[input.length];

		Stack<Integer> stack = newStackInstance();

		for (int i = input.length - 1; i >= 0; i--) {

			while (!stack.isEmpty() && stack.peek().intValue() <= input[i]) {
				stack.pop();
			}
			result[i] = stack.isEmpty() ? -1 : stack.peek();

			stack.push(input[i]);
		}

		return result;
	}

	/*
	 * 
	 * 0----->-----i------->--------input.length
	 * 
	 * 0--<-j--<--i
	 * 
	 */
	// 5 0 0 4 3 7 6 9
	// sol:-1, 5, 5, 5, 4, -1, 7, -1
	@Override
	public int[] findImmediateGreaterInLeftUsingBurteForce(int[] input) {

		int[] result = new int[input.length];
		Arrays.fill(result, -1);

		for (int i = 0; i < input.length; i++) {

			for (int j = i - 1; j >= 0; j--) {
				if (input[j] > input[i]) {
					result[i] = input[j];
					break;
				}

			}
		}

		return result;
	}

	@Override
	public int[] findImmediateGreaterInLeftUsingStack(int[] input) {

		int[] result = new int[input.length];

		Stack<Integer> stack = newStackInstance();

		for (int i = 0; i < input.length; i++) {

			while (!stack.isEmpty() && stack.peek().intValue() <= input[i]) {
				stack.pop();
			}
			result[i] = stack.isEmpty() ? -1 : stack.peek();

			stack.push(input[i]);
		}

		return result;
	}

	/*
	 * 
	 * 0----->-----i------->--------input.length
	 * 
	 * 0--<-j--<--i
	 * 
	 * 
	 * prob: 5 0 0 4 3 7 6 9
	 * 
	 * sol: 1,1,1,3,1,6,1,8
	 * 
	 * O(n^2)
	 * 
	 */

	@Override
	public int[] findingSpanUsingBruteForce(int[] input) {

		int[] result = new int[input.length];

		for (int i = 0; i < input.length; i++) {

			int j = i - 1;

			for (; j >= 0 && input[j] < input[i]; j--)
				;

			// span = i-j
			result[i] = i - j;
		}

		return result;
	}

	/*
	 * 
	 * 0----->-----i------->--------input.length
	 * 
	 * 0--<-j--<--i
	 * 
	 * 
	 * prob: 5 0 0 4 3 7 6 9
	 * 
	 * sol: 1,1,1,3,1,6,1,8
	 * 
	 * 
	 */
	@Override
	public int[] findingSpanUsingStack(int[] input) {

		int[] result = new int[input.length];

		Stack<Integer> stack = newStackInstance();

		for (int i = 0; i < input.length; i++) {

			while (!stack.isEmpty() && input[stack.peek().intValue()] < input[i]) {
				stack.pop();

			}
			// -1 is for taking care of empty_stack
			int peekIndex = stack.isEmpty() ? -1 : stack.peek();
			result[i] = i - peekIndex;
			stack.push(i);
		}

		return result;
	}

	
	@Override
	public int[][] findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(int[] input) {
		System.out.println("solLeft:" + Arrays.toString(findIndexOfImmediateSmallerInLeftUsingStack(input)));
		System.out.println("solRight:" + Arrays.toString(findIndexOfImmediateSmallterInRightUsingStack(input)));
		// solutionArr[0] = ImmediateSmallerInLeft
		// solutionArr[1] = ImmediateSmallerInRight
		int[][] solutionArr = new int[2][input.length];

		Stack<Integer> indexStack = newStackInstance();
		for (int i = 0; i < input.length; i++) {

			//since there is no consecutive duplicates in input so no need for >= condition.
			while (!indexStack.isEmpty() && input[indexStack.peek().intValue()] > input[i]) {
				int poppedElementIndex = indexStack.pop();
				// set the immediate smaller right array
				int immidateSmallerInRightForPoppedElemnt = i;
				solutionArr[1][poppedElementIndex] = immidateSmallerInRightForPoppedElemnt;

				int immidateSmallerInLeftForPoppedElemnt = indexStack.isEmpty() ? -1 : indexStack.peek();
				solutionArr[0][poppedElementIndex] = immidateSmallerInLeftForPoppedElemnt;

			}

			indexStack.push(i);

		}

	
		while (!indexStack.isEmpty()) {

			int poppedElementIndex = indexStack.pop();

			int immidateSmallerInRightForPoppedElemnt = input.length;
			solutionArr[1][poppedElementIndex] = immidateSmallerInRightForPoppedElemnt;
			
			int immidateSmallerInLeftForPoppedElemnt = indexStack.isEmpty() ? -1 : indexStack.peek();
			solutionArr[0][poppedElementIndex] = immidateSmallerInLeftForPoppedElemnt;
			
		}

		return solutionArr;
	}
	
	
	@Override
	public int[][] findISLAndISRUsingSingleStack(int[] input) {
		System.out.println("solLeft:" + Arrays.toString(findIndexOfImmediateSmallerInLeftUsingStack(input)));
		System.out.println("solRight:" + Arrays.toString(findIndexOfImmediateSmallterInRightUsingStack(input)));
		// solutionArr[0] = ImmediateSmallerInLeft
		// solutionArr[1] = ImmediateSmallerInRight
		int[][] solutionArr = new int[2][input.length];

		Stack<Integer> indexStack = newStackInstance();
		for (int i = 0; i < input.length; i++) {

			// this can be anything to record the equal element's index
			Stack<Integer> equalElementIndexStack = newStackInstance();

			//here condition cannot be >=, otherwise it will break the ISR for consecutive duplicates
			while (!indexStack.isEmpty() && input[indexStack.peek().intValue()] > input[i]) {

				int poppedElementIndex = indexStack.pop();
				// set the immediate smaller right array
				int immidateSmallerInRightForPoppedElemnt = i;
				solutionArr[1][poppedElementIndex] = immidateSmallerInRightForPoppedElemnt;

				// for immediate smaller left array if we found equal-elements then we need to
				// consider
				// the bottom-most equal-element's immediate-smaller-left for all the other
				// equals.
				if (!indexStack.isEmpty() && input[poppedElementIndex] == input[indexStack.peek()]) {
					equalElementIndexStack.push(poppedElementIndex);
					continue;
				}

				int immidateSmallerInLeftForPoppedElemnt = indexStack.isEmpty() ? -1 : indexStack.peek();
				solutionArr[0][poppedElementIndex] = immidateSmallerInLeftForPoppedElemnt;

				while (!equalElementIndexStack.isEmpty()) {
					int equalElementIndex = equalElementIndexStack.pop();
					solutionArr[0][equalElementIndex] = immidateSmallerInLeftForPoppedElemnt;
				}

			}

			indexStack.push(i);

		}

		// this can be anything to record the equal element's index
		Stack<Integer> equalElementIndexStack = newStackInstance();
		while (!indexStack.isEmpty()) {

			int poppedElementIndex = indexStack.pop();

			int immidateSmallerInRightForPoppedElemnt = input.length;
			solutionArr[1][poppedElementIndex] = immidateSmallerInRightForPoppedElemnt;

			if (!indexStack.isEmpty() && input[poppedElementIndex] == input[indexStack.peek()]) {
				equalElementIndexStack.push(poppedElementIndex);
				continue;
			}

			int immidateSmallerInLeftForPoppedElemnt = indexStack.isEmpty() ? -1 : indexStack.peek();
			solutionArr[0][poppedElementIndex] = immidateSmallerInLeftForPoppedElemnt;
			while (!equalElementIndexStack.isEmpty()) {
				int equalElementIndex = equalElementIndexStack.pop();
				solutionArr[0][equalElementIndex] = immidateSmallerInLeftForPoppedElemnt;
			}
		}

		return solutionArr;
	}

	private int[] findIndexOfImmediateSmallerInLeftUsingStack(int[] input) {

		int[] result = new int[input.length];

		Stack<Integer> stack = newStackInstance();

		for (int i = 0; i < input.length; i++) {

			while (!stack.isEmpty() && input[stack.peek().intValue()] >= input[i]) {
				stack.pop();
			}
			result[i] = stack.isEmpty() ? -1 : stack.peek();

			stack.push(i);
		}

		return result;
	}

	private int[] findIndexOfImmediateSmallterInRightUsingStack(int[] input) {

		int[] result = new int[input.length];

		Stack<Integer> stack = newStackInstance();

		for (int i = input.length - 1; i >= 0; i--) {

			while (!stack.isEmpty() && input[stack.peek().intValue()] >= input[i]) {
				stack.pop();
			}
			result[i] = stack.isEmpty() ? input.length : stack.peek();

			stack.push(i);
		}

		return result;
	}

	/*
	 * Just find the maxArea in left array of each element, and it will work.
	 * 
	 * 
	 * 3,2,5,6,1,4,4
	 * 
	 */
	@Override
	public int maxAreaHistogramUsingBruteForce(int[] histogram) {

		int maxArea = Integer.MIN_VALUE;

		for (int i = 0; i < histogram.length; i++) {

			// always re-initialize the min-height with the current height of histogram, and
			// go
			// for area comparison in left array
			int minHeight = histogram[i];

			for (int j = i; j >= 0; j--) {

				minHeight = Math.min(minHeight, histogram[j]);

				maxArea = Math.max(maxArea, (i - j + 1) * minHeight);
			}

		}

		return maxArea;
	}

	/***
	 * if(stack.isEmpty OR current_element >= to stack_top){
	 * 
	 * push current_index in stack.
	 * 
	 * }else{
	 * 
	 * pop the stack until stack_got_empty or encounter smaller_element than current
	 * element
	 * 
	 * for-each popped element calculate the area of region considering each popped
	 * element as the 'minimum bar': MAX_RECTANGULAR_AREA_FOR_A_GIVEN_BAR=
	 * HEIGHT_OF_THE_BAR*(INDEX_OF_SMALLER_BAR_IN_RIGHT -
	 * INDEX_OF_SMALLER_BAR_IN_LEFT - 1)
	 * 
	 * after for-each finished: push current_index in stack.
	 * 
	 * }
	 * 
	 * 
	 * NOTE: stack always keeps element in increasing order.
	 */
	@Override
	public int maxAreaHistogramUsingJudgeAlgo1(int[] histogram) {

		Stack<Integer> indexStack = newStackInstance();
		int maxArea = Integer.MIN_VALUE;
		int i = 0;
		for (; i < histogram.length; i++) {

			if (indexStack.isEmpty() || histogram[i] >= histogram[indexStack.peek()]) {
				indexStack.push(i);
			} else {
				while (!indexStack.isEmpty() && histogram[indexStack.peek()] > histogram[i]) {
					// we calculate the area by assuming popped bar as min bar.
					int minBarIndex = indexStack.pop();
					final int SMALLER_BAR_IN_RIGHT = i;
					// NOTE: stack always keeps element in increasing order.
					final int SMALLER_BAR_IN_LEFT = indexStack.isEmpty() ? -1 : indexStack.peek();
					int runningArea = histogram[minBarIndex] * (SMALLER_BAR_IN_RIGHT - SMALLER_BAR_IN_LEFT - 1);
					maxArea = Math.max(maxArea, runningArea);
				}

				// now push the current element's index
				indexStack.push(i);
			}
		}

		while (!indexStack.isEmpty()) {
			int minBarIndex = indexStack.pop();
			final int SMALLER_BAR_IN_RIGHT = i;
			final int SMALLER_BAR_IN_LEFT = indexStack.isEmpty() ? -1 : indexStack.peek();
			int runningArea = histogram[minBarIndex] * (SMALLER_BAR_IN_RIGHT - SMALLER_BAR_IN_LEFT - 1);
			maxArea = Math.max(maxArea, runningArea);
		}

		return maxArea;
	}

	@Override
	public int maxAreaHistogramUsingJudgeAlgo2(int[] histogram) {

		Stack<Integer> indexStack = newStackInstance();
		int maxArea = Integer.MIN_VALUE;
		int i = 0;
		for (; i < histogram.length; i++) {

			if (indexStack.isEmpty() || histogram[i] >= histogram[indexStack.peek()]) {
				indexStack.push(i);
			} else {
				// we calculate the area by assuming popped bar as min bar.
				int minBarIndex = indexStack.pop();
				final int SMALLER_BAR_IN_RIGHT = i;
				final int SMALLER_BAR_IN_LEFT = indexStack.isEmpty() ? -1 : indexStack.peek();
				int runningArea = histogram[minBarIndex] * (SMALLER_BAR_IN_RIGHT - SMALLER_BAR_IN_LEFT - 1);
				maxArea = Math.max(maxArea, runningArea);

				i--;
			}
		}

		while (!indexStack.isEmpty()) {
			int minBarIndex = indexStack.pop();
			final int SMALLER_BAR_IN_RIGHT = i;
			final int SMALLER_BAR_IN_LEFT = indexStack.isEmpty() ? -1 : indexStack.peek();
			int runningArea = histogram[minBarIndex] * (SMALLER_BAR_IN_RIGHT - SMALLER_BAR_IN_LEFT - 1);
			maxArea = Math.max(maxArea, runningArea);
		}

		return maxArea;
	}

	@Override
	public int maxAreaHistogramUsingImmediateSmallerInLeftAndRight(int[] histogram) {

		int[] ISL = findIndexOfImmediateSmallerInLeftUsingStack(histogram);
		int[] ISR = findIndexOfImmediateSmallterInRightUsingStack(histogram);
		System.out.println("ISL: " + Arrays.toString(ISL));
		System.out.println("ISR:" + Arrays.toString(ISR));

		int maxArea = 0;
		for (int i = 0; i < histogram.length; i++) {
			maxArea = Math.max(maxArea, (ISR[i] - ISL[i] - 1) * histogram[i]);
		}
		return maxArea;
	}

	// Logic: compare i and i-1
	@Override
	public String removeAdjacentDuplicates1(String inputString) {
		int i = 1;
		while (i < inputString.length()) {
			if (inputString.charAt(i) == inputString.charAt(i - 1)) {
				inputString = inputString.substring(0, i - 1) + inputString.substring(i + 1);
				i--;
			} else {
				i++;
			}
		}

		return inputString;
	}

	// Logic: compare i and i-1
	// mississippi
	@Override
	public String removeAdjacentDuplicates2(String inputString) {
		final char SOH = '\u0001';
		char[] chars = inputString.toCharArray();
		int consecutiveDuplicateCount = 1;

		for (int i = 1; i < chars.length; i++) {
			if (chars[i] == chars[i - consecutiveDuplicateCount]) {
				chars[i] = SOH;
				chars[i - consecutiveDuplicateCount] = SOH;
				consecutiveDuplicateCount += 2;
				continue;
			}
			consecutiveDuplicateCount = 1;
		}
		return String.valueOf(chars).replace(String.valueOf(SOH), "");
	}

	// mississippi
	// Time Complexity: O(n). Space Complexity: O(n) as
	@Override
	public String removeAdjacentDuplicatesUsingStack1(String inputString) {

		char input[] = inputString.toCharArray();

		Stack<Character> stack = newStackInstance();

		for (int i = 0; i < input.length; i++) {
			if (!stack.isEmpty() && stack.peek().equals(input[i])) {
				stack.pop();
			} else {
				// duplicate characters are not getting pushed.
				stack.push(input[i]);
			}

		}

		StringBuilder strBuilder = new StringBuilder();
		while (!stack.isEmpty()) {
			strBuilder.insert(0, stack.pop());
		}
		return strBuilder.toString();
	}

	/*
	 * //mississippi [m,i,s,s,i,s,s,i,p,p,i]
	 * 
	 * 
	 * In-place stack in char array
	 * 
	 * Time Complexity: O(n). Space Complexity: O(1) as
	 * 
	 */
	@Override
	public String removeAdjacentDuplicatesUsingStack2(String input) {
		char[] chars = input.toCharArray();
		int stackPointer = -1;
		int i = 0;
		int len = chars.length;

		while (i < len) {

			// if stack is not empty and stack.peek is equal to current-char
			if (stackPointer != -1 && chars[stackPointer] == chars[i]) {

				// skip the current-char until non-duplicate found
				while (i < len && chars[stackPointer] == chars[i]) {
					i++;
				}
				// pop the duplicate-char from stack
				stackPointer--;

			} else {
				// push on to the stack if either stack is empty or stack.peek != current-char
				stackPointer++;
				chars[stackPointer] = chars[i];
				i++;
			}
		}
		return String.valueOf(chars, 0, stackPointer + 1);
	}

	

}