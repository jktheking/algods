package edu.algo.permcomb;

import java.util.List;

public interface DoubleCountingQuestion {

	DoubleCountingQuestion INSTANCE = new DoubleCountingSolution();

	void printPowerSetUsingPascalIdentity(String inputString);

	void printPowerSetUsingPascalIdentity(List<Integer> input);

	void printPowerSetUsingPascalIdentityExpansion1(String input);
	
	void printPowerSetUsingPascalIdentityExpansion2(char[] input);

	List<String> getPowerSetUsingSubSequence(String input);

	void print_nCkUsingPascalIdentity(final int deziredLength, String input);

	void print_nC2UsingLoop(String input);

	void printPowerSetByIncludingAGivenCharOfInput(int includeCharIndex, String inputString);

	/**
	 * print power set by placing 'i' at 'n' given positions.
	 * 
	 * */ 
	void printPowerSetUsingPascalIdentityByFixingPos(int positionCount); 
	
	/**
	 * print power set by placing 'i' at 'n' given positions.
	 * 
	 * */ 
	void printPowerSetUsingPascalIdentityExpansionByFixingPos(int positionCount);
}
