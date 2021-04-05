package edu.algods.stack;

public interface StackKarumanchiQuestions<T extends Comparable<T>> {

	<S extends Comparable<S>> Stack<S> newStackInstance();

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

	/**
	 * Problem-2
	 * 
	 * NOTES: For all sort of conversion infix-to-postfix or infix-to-prefix or vice
	 * versa, order of operands remain same.
	 * 
	 * ALGO STEPS: - need only one stack <br>
	 * - need to push only operator and opening brace'(' <br>
	 * 
	 * A --traverse the input expression : <br>
	 * if(token is operand){ <br>
	 * append in postfix_exp <br>
	 * 
	 * }else if( token is closing parenthesis){ <br>
	 * pop and append stack_operator to postfix_exp until the opening parenthesis is
	 * popped
	 * 
	 * }else if(token is operator or opening parenthesis){ <br>
	 * 
	 * 1. pop and append stack_operator to postfix_exp until
	 * lower_precedence_operator OR opening_parenthesis OR EMPTY_STACK encountered
	 * <br>
	 * 
	 * 2. push the token to stack
	 * 
	 * } <br>
	 * 
	 * B --post end of traversal of input expression pop and append stack_operators
	 * to postfix_exp until EMPTY_STACK
	 * 
	 * 
	 */
	String convertInfixToPostfix(String infixExpression);

	/**
	 * NOTE: when we go for conversion to postfix we place operator on stack. And
	 * when we go for evaluation we place operand on stack
	 * 
	 * Q3 - Evaluate postfix expression.
	 *
	 * Algorithm:
	 *
	 * 1 Scan the Postfix string from left to right. <br>
	 * 2 Initialize an empty stack.<br>
	 * 3 Repeat steps 4 and 5 till all the characters are scanned.<br>
	 * 4 If the scanned character is an operand, push it onto the stack.<br>
	 * 5 If the scanned character is an operator, and if the operator is a unary
	 * operator, then pop an element from the stack. If the operator is a binary
	 * operator, then pop two elements from the stack. After popping the elements,
	 * apply the operator to those popped elements. Let the result of this operation
	 * be retVal onto the stack.<br>
	 * 6 After all characters are scanned, we will have only one element in the
	 * stack.<br>
	 * 7 Return top of the stack as result.<br>
	 *
	 */

	double evaluatePostfixExpression(String postFixExpression);

	/**
	 * Problem-4 Dijkstra Shunting Yard Algorithm
	 * 
	 * Problem-4 Can we evaluate the infix expression with stacks in one pass?
	 * 
	 * Using 2 stacks we can evaluate an infix expression in 1 pass without
	 * converting to postfix.
	 * 
	 * Solution : can we sloved in one pass using two stacks:
	 * 
	 * One for operator and one for operand.
	 * 
	 * Q4 - Evaluate the infix expression with stacks in one pass.
	 *
	 * Algorithm: Dijkstra's Shunting Yard Algorithm
	 *
	 * a) for each character 't' in the input string <br>
	 *
	 * b) If character is operand push on the operand stack, if character is (, push
	 * on the operator stack. <br>
	 *
	 * c) Else if character is operator <br>
	 *
	 * c1) While the top of the operator stack is not of smaller precedence than
	 * this character.<br>
	 * c2) Pop operator from operator stack. <br>
	 * c3) Pop two operands (op1 and op2) from operand stack. <br>
	 * c4) Store op1 op op2 on the operand stack back to c1. <br>
	 *
	 * d) Else if character is ), do the same as c2 - c4 till you encounter (.
	 *
	 * e) Else (no more character left to read):
	 *
	 * e1) Pop operators untill operator stack is not empty. <br>
	 * e2) Pop top 2 operands and push op1 op op2 on the operand stack. <br>
	 *
	 * f) return the top value from operand stack.
	 */
	double infixEvalautionInSinglePass(String infixExpression);

	/**
	 * 1. Reverse the infixExpression string; for braces : reverse opening with
	 * closing 2. Now convert the reversed_infixExpression to postfix_expression 3.
	 * Reverse the postfix_expression to get prefix_expression
	 */
	String infixToPrefixUsingPostFix(String infixExpression);

	/**
	 * Algorithm:
	 *
	 * a) Create an operator stack and operand stack.
	 *
	 * b) for each character 't' in the input string <br>
	 *
	 * ... b1) if 't' is an opening parenthesis <br>
	 * ............ push onto the operator stack <br>
	 *
	 * ... b2) else if 't' is closing parenthesis<br>
	 * ............ pop operator from operator stack and corresponding number of
	 * ............ operands from operand stack and push (operator+operands) to
	 * ............ operand stack until opening parenthesis is popped. Don't push
	 * ............ the parenthesis. <br>
	 *
	 * ... b3) else if 't' is an operator <br>
	 * ............ pop from operator stack and corresponding number of operands
	 * ............ from operand stack and push (operator+operands) to operand stack
	 * ............ until one of lower precedence than 't' is encountered or the
	 * ............ stack is empty. Push 't' onto the operator stack.
	 *
	 * ... b4) else (-----if t is operand (only operands will come to else)) <br>
	 * ............ push the operand to the operand stack.
	 *
	 * c) pop from operator stack and corresponding number of operands from operand
	 * stack and push (operator+operands) to operand stack.
	 * 
	 * pop the result from operand stack
	 */
	String infixToPrefixUsingTwoStack(String infixExpression);

	/**
	 * 1. Scan the input string from end
	 * 
	 * 2. and other steps will remain same as postfixEvaluation
	 */
	double evaluatePrefixExpression(String prefixEvalulation);

	/**
	 * Algorithm: Only one stack (operand stack) is required for postfix to infix
	 * 
	 * Step 1: for each token in postfix string
	 * 
	 * if token is operand push to operand stack <br>
	 * 
	 * else if token is operator, form the infix string corresponding to the
	 * operator inside the braces()
	 * 
	 * pop the result from operand stack
	 */
	String postFixToInfix(String postFixExpression);

	/**
	 * Algorithm: Only one stack (operand stack) is required for postfix to prefix
	 * 
	 * Step 1: for each token in postfix string
	 * 
	 * if token is operand push to operand stack <br>
	 * 
	 * else if token is operator, form the prefix string corresponding to the
	 * operator
	 * 
	 * pop the result from operand stack
	 */
	String postFixToPrefix(String postFixExpression);

	/**
	 * PROBLEM 8 to 10 divide the string in two equal halves.
	 * 
	 * push first half in stack
	 * 
	 * pop and compare the element of stack with second-half character
	 */
	boolean checkIfGivenStringIsPalindromeUsingStack();

	/**
	 * Problem 11
	 * 
	 */
	void reverseStackUsingRecursion(Stack<T> input);

	/**
	 * Problem 22: STOCK_SPAN
	 * 
	 * Prerequisite : Find immediate greater value in right - brute-force
	 * 
	 */
	int[] findImmediateGreaterInRightUsingBurteForce(int[] input);

	/**
	 * Problem 22: STOCK_SPAN
	 * 
	 * Prerequisite : Find immediate greater value in right - using stack
	 * 
	 * 
	 * Q1 : Why do we need a stack to improvise BruteForce Solution ?
	 * 
	 * Pattern : Towards right array of candidate element, we have a search space.
	 * This search-space is sort of depth-first and simulates back-tracking.
	 * 
	 * So we need a explicit stack.
	 * 
	 * Observation: In brute-force solution we may have two for loops and index of
	 * inner loop(j) may depend on index of outer for loop(i);
	 * 
	 * Q2: Why do we need to scan the input from end ?
	 * 
	 * For example 5 0 0 4 3 7 6 9 ; let's say candidate is '4' Now we need to
	 * search the solution in right arr [3,7,6,9]. For stack based search we should
	 * have element pushed in stack as [9,6,7,3] so that first element of right
	 * arr[3,7,6,9] should be at top. i.e '3'.
	 * 
	 * 
	 */
	int[] findImmediateGreaterInRightUsingStack(int[] input);

	void findImmediateGreaterInRightUsingRecursion(int first, int[] rightArr, int start, int end, int[] result);

	/**
	 * Problem 22: STOCK_SPAN
	 * 
	 * Prerequisite : Find immediate greater value in left - brute-force
	 * 
	 */
	int[] findImmediateGreaterInLeftUsingBurteForce(int[] input);

	/**
	 * Problem 22: STOCK_SPAN
	 * 
	 * Prerequisite : Find immediate greater value in left - using stack
	 * 
	 * 
	 * Q1 : Why do we need a stack to improvise BruteForce Solution ?
	 * 
	 * Pattern : Towards left array of candidate element, we have a search space.
	 * This search-space is sort of depth-first and simulates back-tracking.
	 * 
	 * So we need a explicit stack.
	 * 
	 * Observation: In brute-force solution we may have two for loops and index of
	 * inner loop(j) may depend on index of outer for loop(i);
	 * 
	 * Q2: Why do we not need to scan the input from end ?
	 * 
	 * For example 5 0 0 4 3 7 6 9 ; let's say candidate is '4' Now we need to
	 * search the solution in left arr [5,0,0]. For stack based search we should
	 * have element pushed in stack as [5,0,0] so that first element of left
	 * arr[5,0,0] should be at top. i.e '0'.
	 * 
	 * 
	 */
	int[] findImmediateGreaterInLeftUsingStack(int[] input);

	/**
	 * Problem 22: STOCK_SPAN
	 * 
	 */
	int[] findingSpanUsingBruteForce(int[] input);

	/**
	 * Problem 22: STOCK_SPAN
	 * 
	 */
	int[] findingSpanUsingStack(int[] input);

	/**
	 * PRE-REQUISITE for max-area-histogram using JUDGE_ALGO.
	 * 
	 * * The implemented algorithm will work to calculate
	 * Immediate_Smaller_In_Left(ISL) and Immediate_Smaller_In_Right(ISR) using
	 * Single_Stack(let's call It JUDGE_STACK) in all the cases except of
	 * Consecutive-Duplicates and Effectively-Consecutive-Duplicates cases.
	 * 
	 * 
	 * A: Consecutive_Duplicates : [2,4,4,5] : 4,4 is consecutive duplicate
	 * 
	 * B: Effectively_Consecutive_Duplicate :
	 * 
	 * If between two duplicate elements, there exists no smaller element then they
	 * can be called effectively_consecutive_duplicate elements because in
	 * JUDGE_STACK they will appear one after the other at some point in the
	 * calculations.
	 * 
	 * Example 1. [3,4,5,3] : between two 3's all the numbers are larger than 3.<br>
	 * JUDGE_STACK progression:<br>
	 * [3,4,5,3] ==> [3] --> [3,4] --> [3,4,5] -->[3,4] --> [3,3] ==> effectively
	 * consecutive duplicate '3'
	 * 
	 * Example 2. [2,4,5,3,2] : between two 2's all the numbers are greater than 2.
	 * <br>
	 * JUDGE_STACK progression:<br>
	 * [2, 4, 5, 3, 2] ==> [2] --> [2,4] --> [2,4,5] --> [2,4] -->[2,3] --> [2,2]
	 * ==> effectively consecutive '2'
	 * 
	 * 3. Non Effectively_Consecutive_Duplicate : [2,4,5,3,1,2] : between two 2's
	 * one of the number is smaller than 2 i.e 1.<br>
	 * JUDGE_STACK progression:<br>
	 * [2, 4, 5, 3, 1, 2] ==> [2] --> [2,4] --> [2,4,5] --> [2,4] --> [2] --> [2,3]
	 * --> [2] --> [] --> [1] --> [1, 2]
	 * 
	 * This algorithm will work for Non-Effectively_Consecutive_Duplicates
	 * [2,4,5,3,1,2] as well.
	 * 
	 * Note: In case of Consecutive_Duplicate and Effectively_Consecutive_Duplicate, each duplicate element will share the same 
	 * Immediate_Smaller_In_Left & Immediate_Smaller_In_Right. 
	 * 
	 * 
	 * 
	 *
	 */
	int[][] findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(int[] input);

	/**
	 * pre-requisite for max-area-histogram using Judge algo
	 * 
	 */
	int[][] findISLAndISRUsingSingleStack(int[] input);

	int maxAreaHistogramUsingBruteForce(int[] histogram);

	/**
	 * *NOTE: stack always keeps element in increasing order.
	 * 
	 * 1) Create a stack to store indexes of array.<br>
	 *
	 * 2) for each element in histogram array <br>
	 *
	 * 2.1) If stack is empty or current value is greater than or equal to element
	 * at stack.top index, then push current index to stack. <br>
	 *
	 * else <br>
	 *
	 * 2.2) Pop the stack until either the stack becomes empty or element for top of
	 * stack index is greater than current element. <br>
	 * 
	 * 2.2.1) For each popped element calculate the area of region considering each
	 * popped element as the 'minimum bar'<br>
	 * 
	 * MAX_RECTANGULAR_AREA_FOR_A_GIVEN_BAR=
	 * HEIGHT_OF_THE_CURRENT_MIN_BAR*(INDEX_OF_SMALLER_BAR_IN_RIGHT -
	 * INDEX_OF_SMALLER_BAR_IN_LEFT - 1)
	 * 
	 * <br>
	 * maxArea = Math.max(maxArea, runningArea);
	 *
	 * 3) Now, for remaining indexes in the stack, perform step no 2.2 until stack
	 * becomes empty.
	 */

	int maxAreaHistogramUsingJudgeAlgo1(int[] histogram);

	int maxAreaHistogramUsingJudgeAlgo2(int[] histogram);

	int maxAreaHistogramUsingImmediateSmallerInLeftAndRight(int[] histogram);

	String removeAdjacentDuplicates1(String input);

	String removeAdjacentDuplicates2(String input);

	String removeAdjacentDuplicatesUsingStack1(String input);

	String removeAdjacentDuplicatesUsingStack2(String input);

}
