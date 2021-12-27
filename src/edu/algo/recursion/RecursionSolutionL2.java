package edu.algo.recursion;

import java.util.Arrays;

public class RecursionSolutionL2 implements RecursionQuestionL2 {

	public static void main(String[] args) {
		INSTANCE.printAbbreviation1("abcd");
		INSTANCE.printAbbreviation2("abcd");
	}

	@Override
	public void printAbbreviation1(String input) {
		char[] output = new char[input.length()];
		Arrays.fill(output, '_');
		printAbbreviationUsingPIE(input.toCharArray(), output, 0);
		
	}
	
	/**
	 * 
	 *STEP1 :Need to create powerset with empty_placeholder '_'.
	 *
	 *STEP2: While printing we need to count the  empty_placeholder.
	 * */
	private void printAbbreviationUsingPIE(char[] input, char[] output, int posToFix) {
		// logic to print the abbreviation
		String print = "";
		int emptyCounter = 0;
		for (char ch : output) {
			if (ch == '_') {
				emptyCounter++;
			} else {
				print = emptyCounter == 0 ? print + ch : print + emptyCounter + ch;
				emptyCounter = 0;
			}
		}
		print = emptyCounter == 0 ? print : print + emptyCounter;
		System.out.println(print);

		// core logic
		while (posToFix < output.length) {
			int currentPos = posToFix;
			output[currentPos] = input[currentPos];
			printAbbreviationUsingPIE(input, output, ++posToFix);
			output[currentPos] = '_';
		}

	}

	@Override
	public void printAbbreviation2(String input) {
		printAbbreviationUsingPIE(input.toCharArray(), "", 0);
	}

	
    /**
     *
     *Refer : AbbrerivationUsingPIE_L2.pdf 
     * 
     * */
	private void printAbbreviationUsingPIE(char[] input, String output, int posToFix) {
        //adding end_empty_count to print string. 
		System.out.println((input.length - posToFix) == 0 ? output : output + (input.length - posToFix));

		int currentPosToFix = posToFix;
	
		while (posToFix < input.length) {
			//we are adding before_empty_count to output for next level.
			String newOutput = output;
			if (posToFix - currentPosToFix != 0) {
				newOutput = newOutput + (posToFix - currentPosToFix);
			}
			newOutput = newOutput + input[posToFix];

			printAbbreviationUsingPIE(input, newOutput, ++posToFix);

		}

	}

}
