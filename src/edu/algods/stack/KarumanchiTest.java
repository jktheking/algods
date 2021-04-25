package edu.algods.stack;

import java.math.BigDecimal;
import java.util.Arrays;

public class KarumanchiTest {

	public static void main(String[] args) {

		// checkIfSymbolsAreBalanced();

		// convertInfixToPostfix();

		// evaluatePostfixExpression();

		// infixEvalautionInSinglePass();

		// infixToPrefixUsingTwoStack();

		// evaluatePrefixExpression();

		// reverseStackUsingRecursion();
		
		implementStackUsingTwoQueues();

		// findImmediateGreaterInRightUsingBurteForce();

		// findImmediateGreaterInRightUsingStack();

		// findImmediateGreaterInLeftUsingBurteForce();

		// findImmediateGreaterInLeftUsingStack();

		// findingSpanUsingBruteForce();

		// findingSpanUsingStack();

		// findImmediateGreaterInRightUsingRecursion();
		// findISLAndISRUsingSingleStack();
		// findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates();
		// maxAreaHistogramUsingBruteForce();
		// maxAreaHistogramUsingJudgeAlgo1();

		// maxAreaHistogramUsingImmediateSmallerInLeftAndRight();

		// removeAdjacentDuplicates();

		// printAllPossibleSubGridsForMatrix();

		// findMaxAreaInBinaryMatrixBruteForce();

		// findTotalRainWaterTrappedOverHistogram();

		//getMinFromMinOpStackUsingExtraSpace();

		// getMinFromMinOpStackUsingConstantSpace();
	}

	private static void checkIfSymbolsAreBalanced() {
		StackKarumanchiQuestions<String> stackQuestions = new StackKarumanchiSolutions<>(false);
		final String expression = "( ( a + b * { x + y } ) + <  a - b > )";
		System.out
				.println("\nis given expression balanced ? : " + stackQuestions.checkIfSymbolsAreBalanced(expression));

	}

	private static void convertInfixToPostfix() {
		StackKarumanchiQuestions<String> stackQuestions = new StackKarumanchiSolutions<>(false);
		System.out.println(stackQuestions.convertInfixToPostfix("A * B - ( C + D ) + E"));
		System.out.println(stackQuestions.convertInfixToPostfix("( A + B * C ) * P - Q"));
		System.out.println(stackQuestions.convertInfixToPostfix("( ( A * ( B + C ) ) / D )"));
		System.out.println(stackQuestions.convertInfixToPostfix("( A * ( B + ( C / D ) ) )"));

		System.out.println(stackQuestions
				.convertInfixToPostfix("-      ( ( A ) * B - (     C + D * ( ( x - y ) + 3 - 2 ) ) + E )"));

	}

	private static void evaluatePostfixExpression() {

		StackKarumanchiQuestions<String> stackQuestions = new StackKarumanchiSolutions<>(true);
		String postfix = stackQuestions.convertInfixToPostfix("100 * 2 - 40 / 4 * ( 9 - 3 ) - 7 * ( 0 - 9  / 3 )");
		System.out.println(postfix);
		System.out.println(stackQuestions.evaluatePostfixExpression(postfix));

	}

	private static void infixEvalautionInSinglePass() {
		StackKarumanchiQuestions<String> stackQuestions = new StackKarumanchiSolutions<>(true);
		System.out.println(
				stackQuestions.infixEvalautionInSinglePass("100 * 2 - 40 / 4 * ( 9 - 3 ) - 7 * ( 0 - 9  / 3 )"));
	}

	private static void infixToPrefixUsingTwoStack() {
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(questions.infixToPrefixUsingTwoStack("( A - B / C ) * ( A / K - L )"));
		System.out.println(questions.infixToPrefixUsingTwoStack("( ( A * B ) + ( C / D ) )"));
		System.out.println(questions.infixToPrefixUsingTwoStack("( ( A * ( B + C ) ) / D )"));
		System.out.println(questions.infixToPrefixUsingTwoStack("( A * ( B + ( C / D ) ) )"));
	}

	private static void evaluatePrefixExpression() {
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		String prefixExp = questions.infixToPrefixUsingTwoStack("100 * 2 - 40 / 4 * ( 9 - 3 ) - 7 * ( 0 - 9  / 3 )");
		System.out.println(prefixExp);

		System.out.println(questions.evaluatePrefixExpression(prefixExp));

	}

	private static void reverseStackUsingRecursion() {

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		Stack<String> inputStack = questions.newStackInstance();
		inputStack.push("A");
		inputStack.push("B");
		inputStack.push("C");
		inputStack.push("D");
		System.out.println(inputStack.toString());
		questions.reverseStackUsingRecursion(inputStack);
		System.out.println(inputStack.toString());
	}

	private static void implementStackUsingTwoQueues() {

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);

		Stack<String> stack = questions.implementStackUsingTwoQueues();
		stack.push("A");
		stack.push("B");
		stack.push("C");
		stack.push("D");
		System.out.println("stack:" + stack);
		System.out.println("pop one element:" + stack.pop());
		System.out.println("stack:" + stack + " size:" + stack.size());
		System.out.println("push two elements E and F");
		stack.push("E");
		stack.push("F");
		System.out.println("stack:" + stack + " size:" + stack.size());
		System.out.println("peek:" + stack.peek());
		System.out.println("pop one element:" + stack.pop());

		System.out
				.println("pop four element:" + stack.pop() + "," + stack.pop() + "," + stack.pop() + "," + stack.pop());
		System.out.println("stack:" + stack + " size:" + stack.size());
		System.out.println("stack.isEmpty():" + stack.isEmpty());

	}

	private static void findImmediateGreaterInRightUsingBurteForce() {

		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 };
		// expected output:[7, 4, 4, 7, 7, 9, 9, -1]
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(Arrays.toString(questions.findImmediateGreaterInRightUsingBurteForce(input)));

	}

	private static void findImmediateGreaterInRightUsingStack() {
		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 };
		// expected output:[7, 4, 4, 7, 7, 9, 9, -1]
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(Arrays.toString(questions.findImmediateGreaterInRightUsingStack(input)));
	}

	private static void findImmediateGreaterInRightUsingRecursion() {
		// int[] input = new int[] { 7, 6, 9 };
		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 };
		// expected output:[7, 4, 4, 7, 7, 9, 9, -1]
		int[] result = new int[input.length];
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);

		questions.findImmediateGreaterInRightUsingRecursion(input[0], input, 1, input.length - 1, result);
		System.out.println(Arrays.toString(result));
	}

	private static void findImmediateGreaterInLeftUsingBurteForce() {
		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 };
		// output [-1, 5, 5, 5, 4, -1, 7, -1]
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(Arrays.toString(questions.findImmediateGreaterInLeftUsingBurteForce(input)));
	}

	private static void findImmediateGreaterInLeftUsingStack() {
		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 };
		// output [-1, 5, 5, 5, 4, -1, 7, -1]
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(Arrays.toString(questions.findImmediateGreaterInLeftUsingBurteForce(input)));
	}

	private static void findingSpanUsingBruteForce() {
		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 }; // output 1,1,1,3,1,6,1,8
		int[] input2 = new int[] { 6, 3, 4, 5, 2 }; // output 1,1,2,3,1

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(Arrays.toString(questions.findingSpanUsingBruteForce(input)));
		System.out.println(Arrays.toString(questions.findingSpanUsingBruteForce(input2)));
	}

	private static void findingSpanUsingStack() {
		int[] input = new int[] { 5, 0, 0, 4, 3, 7, 6, 9 }; // output 1,1,1,3,1,6,1,8
		int[] input2 = new int[] { 6, 3, 4, 5, 2 }; // output 1,1,2,3,1

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(Arrays.toString(questions.findingSpanUsingStack(input)));
		System.out.println(Arrays.toString(questions.findingSpanUsingStack(input2)));
	}

	private static void findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates() {

		int[] illegalInput1 = new int[] { 2, 4, 4, 5 }; // 4,4 consecutive duplicate
		int[] illegalInput2 = new int[] { 3, 4, 5, 3 }; // 3,3 effectively consecutive duplicate
		int[] illegalInput3 = new int[] { 2, 4, 5, 3, 2 }; // 2,2 effectively consecutive duplicate

		int[] legalInput1 = new int[] { 3, 2, 5, 6, 1, 7 }; // no duplicates
		int[] legalInput2 = new int[] { 2, 4, 5, 3, 1, 2 }; // 2,2 are duplicate but not effectively consecutive; so it
															// is legal

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);

		System.out.println("---------------------------ILLEAGAL1------------------------------------");
		int[][] illegalSolArr1 = questions.findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(illegalInput1);
		System.out.println("solLeft:" + Arrays.toString(illegalSolArr1[0]));
		System.out.println("solRight:" + Arrays.toString(illegalSolArr1[1]));

		System.out.println("---------------------------ILLEAGAL2------------------------------------");
		int[][] illegalSolArr2 = questions.findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(illegalInput2);
		System.out.println("solLeft:" + Arrays.toString(illegalSolArr2[0]));
		System.out.println("solRight:" + Arrays.toString(illegalSolArr2[1]));

		System.out.println("---------------------------ILLEAGAL3-------------------------------------");
		int[][] illegalSolArr3 = questions.findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(illegalInput3);
		System.out.println("solLeft:" + Arrays.toString(illegalSolArr3[0]));
		System.out.println("solRight:" + Arrays.toString(illegalSolArr3[1]));

		System.out.println("----------------------------LEAGAL1-------------------------------------");
		int[][] legalSolArr1 = questions.findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(legalInput1);
		System.out.println("solLeft:" + Arrays.toString(legalSolArr1[0]));
		System.out.println("solRight:" + Arrays.toString(legalSolArr1[1]));

		System.out.println("------------------------------LEAGAL2-----------------------------------");
		int[][] legalSolArr2 = questions.findISLAndISRUsingSingleStackExcludingConsecutiveDuplicates(legalInput2);
		System.out.println("solLeft:" + Arrays.toString(legalSolArr2[0]));
		System.out.println("solRight:" + Arrays.toString(legalSolArr2[1]));
		System.out.println("-----------------------------------------------------------------");

	}

	private static void findISLAndISRUsingSingleStack() {
		int[] input1 = new int[] { 3, 2, 5, 6, 1, 4, 4 };
		int[] input2 = new int[] { 1, 2, 3, 4, 1, 2, 3, 4 };
		int[] input3 = new int[] { 2, 4, 3, 4, 1, 1 };
		int[] input4 = new int[] { 4, 2, 3, 2, 1, 1 };
		int[] input5 = new int[] { 4, 2, 3, 1, 2, 0 };
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		int[][] solArr1 = questions.findISLAndISRUsingSingleStack(input1);
		System.out.println("solLeft:" + Arrays.toString(solArr1[0]));
		System.out.println("solRight:" + Arrays.toString(solArr1[1]));
		System.out.println("-----------------------------------------------------------------");
		int[][] solArr2 = questions.findISLAndISRUsingSingleStack(input2);
		System.out.println("solLeft:" + Arrays.toString(solArr2[0]));
		System.out.println("solRight:" + Arrays.toString(solArr2[1]));
		System.out.println("-----------------------------------------------------------------");
		int[][] solArr3 = questions.findISLAndISRUsingSingleStack(input3);
		System.out.println("solLeft:" + Arrays.toString(solArr3[0]));
		System.out.println("solRight:" + Arrays.toString(solArr3[1]));

		System.out.println("-----------------------------------------------------------------");
		int[][] solArr4 = questions.findISLAndISRUsingSingleStack(input4);
		System.out.println("solLeft:" + Arrays.toString(solArr4[0]));
		System.out.println("solRight:" + Arrays.toString(solArr4[1]));

		System.out.println("-----------------------------------------------------------------");
		int[][] solArr5 = questions.findISLAndISRUsingSingleStack(input5);
		System.out.println("solLeft:" + Arrays.toString(solArr5[0]));
		System.out.println("solRight:" + Arrays.toString(solArr5[1]));
	}

	private static void maxAreaHistogramUsingBruteForce() {

		int[] histogram1 = new int[] { 3, 2, 5, 6, 1, 4, 4 }; // output : 10
		int[] histogram2 = new int[] { 4, 3, 2, 1, 4, 3, 2 }; // output : 7
		int[] histogram3 = new int[] { 0, 0, 0, 0, 0 }; // output : 7
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(questions.maxAreaHistogramUsingBruteForce(histogram1));
		System.out.println(questions.maxAreaHistogramUsingBruteForce(histogram2));
		System.out.println(questions.maxAreaHistogramUsingBruteForce(histogram3));
	}

	private static void maxAreaHistogramUsingJudgeAlgo1() {

		int[] histogram1 = new int[] { 1, 2, 3, 4, 2, 3, 5, 5, 1, 7 };

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(questions.maxAreaHistogramUsingJudgeAlgo1(histogram1));
	}

	private static void maxAreaHistogramUsingImmediateSmallerInLeftAndRight() {

		int[] histogram1 = new int[] { 2, 2, 6, 6, 7, 2, 198 }; // output : 198
		int[] histogram2 = new int[] { 4, 3, 2, 1, 4, 3, 2 }; // output : 7
		int[] histogram3 = new int[] { 3, 2, 5, 6, 1, 4, 4 }; //
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(questions.maxAreaHistogramUsingImmediateSmallerInLeftAndRight(histogram3));
//		System.out.println(questions.maxAreaHistogramUsingJudgeAlgo1(histogram2));
//		System.out.println(questions.maxAreaHistogramUsingJudgeAlgo1(histogram3));
	}

	private static void removeAdjacentDuplicates() {
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(questions.removeAdjacentDuplicates1("maissiassippi"));
		System.out.println(questions.removeAdjacentDuplicates1("mississippi"));
		System.out.println(questions.removeAdjacentDuplicates1("careermonk"));

		System.out.println(questions.removeAdjacentDuplicatesUsingStack2("maissiassippi"));
		System.out.println(questions.removeAdjacentDuplicatesUsingStack2("mississippi"));
		System.out.println(questions.removeAdjacentDuplicatesUsingStack2("careermonk"));

	}

	private static void printAllPossibleSubGridsForMatrix() {

		String[][] matrix = new String[][] { { "A1", "A2", "A3" }, { "B1", "B2", "B3" }, { "C1", "C2", "C3" },
				{ "D1", "D2", "D3" } };
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		questions.printAllPossibleSubGridsForMatrix(matrix);
	}

	private static void findMaxAreaInBinaryMatrixBruteForce() {
		int[][] matrix = new int[][] { { 1, 1, 0, 0, 1, 0 }, { 0, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 0 },
				{ 0, 0, 1, 1, 0, 0 } };
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println("maxArea:" + questions.findMaxAreaInBinaryMatrixBruteForce(matrix));

	}

	private static void findTotalRainWaterTrappedOverHistogram() {
		// int[] input = {3, 0, 2, 0, 4};
		int[] input = { 9, 2, 4, 7, 5, 4, 3 };

		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println(
				"findTotalRainWaterTrappedOverHistogram1 :" + questions.findTotalRainWaterTrappedOverHistogram1(input));
		System.out.println("findTotalRainWaterTrappedOverHistogramUsingTwoPointer2 :"
				+ questions.findTotalRainWaterTrappedOverHistogramUsingTwoPointer2(input));
		System.out.println("findTotalRainWaterTrappedOverHistogramUsingIGRandIGL3 :"
				+ questions.findTotalRainWaterTrappedOverHistogramUsingIGRandIGL3(input));
		System.out.println("findTotalRainWaterTrappedOverHistogramJudge4: "
				+ questions.findTotalRainWaterTrappedOverHistogramJudge4(input));
		System.out.println("findTotalRainWaterTrappedOverHistogramJudge5: "
				+ questions.findTotalRainWaterTrappedOverHistogramJudge5(input));

	}

	private static void getMinFromMinOpStackUsingExtraSpace() {

		Stack<BigDecimal> minOpStackUsingExtraSpace = new MinOpSupportedStackUsingExtraSpace<>();
		minOpStackUsingExtraSpace.push(BigDecimal.valueOf(9));
		minOpStackUsingExtraSpace.push(BigDecimal.valueOf(5));
		minOpStackUsingExtraSpace.push(BigDecimal.valueOf(3));
		minOpStackUsingExtraSpace.push(BigDecimal.valueOf(3));
		minOpStackUsingExtraSpace.push(BigDecimal.valueOf(2));

		System.out.println(minOpStackUsingExtraSpace);
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println("minElement:" + questions.getMinFromMinOpStackUsingExtraSpace(minOpStackUsingExtraSpace));

		while (!minOpStackUsingExtraSpace.isEmpty()) {

			System.out.print(" popped element:" + minOpStackUsingExtraSpace.pop());
			BigDecimal minElement = minOpStackUsingExtraSpace.isEmpty() ? null
					: questions.getMinFromMinOpStackUsingExtraSpace(minOpStackUsingExtraSpace);
			System.out.print(" minElement:" + minElement);
			System.out.println();
		}

		System.out.println(minOpStackUsingExtraSpace);
		minOpStackUsingExtraSpace.push(BigDecimal.valueOf(7));
		System.out.println(minOpStackUsingExtraSpace);
		System.out.println(" minElement:" + questions.getMinFromMinOpStackUsingExtraSpace(minOpStackUsingExtraSpace));

	}

	private static void getMinFromMinOpStackUsingConstantSpace() {

		Stack<BigDecimal> minOpStackUsingConstantSpace = new MinOpSupportedStackUsingConstantSpace();
		minOpStackUsingConstantSpace.push(BigDecimal.valueOf(9));
		minOpStackUsingConstantSpace.push(BigDecimal.valueOf(5));
		minOpStackUsingConstantSpace.push(BigDecimal.valueOf(3));
		minOpStackUsingConstantSpace.push(BigDecimal.valueOf(3));
		minOpStackUsingConstantSpace.push(BigDecimal.valueOf(2));

		System.out.println(minOpStackUsingConstantSpace);
		StackKarumanchiQuestions<String> questions = new StackKarumanchiSolutions<>(true);
		System.out.println("minElement:" + questions.getMinFromMinOpStackUsingExtraSpace(minOpStackUsingConstantSpace));

		while (!minOpStackUsingConstantSpace.isEmpty()) {
			System.out.println("popped element:" + minOpStackUsingConstantSpace.pop() + " minElement:"
					+ questions.getMinFromMinOpStackUsingExtraSpace(minOpStackUsingConstantSpace));
		}

		System.out.println(minOpStackUsingConstantSpace);
		minOpStackUsingConstantSpace.push(BigDecimal.valueOf(7));
		System.out.println(minOpStackUsingConstantSpace);
		System.out
				.println(" minElement:" + questions.getMinFromMinOpStackUsingExtraSpace(minOpStackUsingConstantSpace));

	}

}
