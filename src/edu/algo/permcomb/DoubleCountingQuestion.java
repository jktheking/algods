package edu.algo.permcomb;

import java.util.List;

public interface DoubleCountingQuestion {

	DoubleCountingQuestion INSTANCE = new DoubleCountingSolution();

	void printPowerSetUsingPascalIdentity(String inputString);

	void printPowerSetUsingPascalIdentity(List<Integer> input);

	void printPowerSetUsingPascalIdentityExpansion(String input);

	void printPowerSetUsingSubSequence(String input);

	void print_nCkUsingPascalIdentity(final int deziredLength, String input);

	void print_nC2UsingLoop(String input);

	void printPowerSetByIncludingAGivenCharOfInput(int includeCharIndex, String inputString);

}
