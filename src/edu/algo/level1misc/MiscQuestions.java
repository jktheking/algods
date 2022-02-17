package edu.algo.level1misc;

public interface MiscQuestions {

	MiscQuestions INSTANCE  = new MiscSolutions();
	
	String decimalToAnyBase(int decimalNum, int base); 
	
	int anyBaseToDecimal(int number, int base);
}
