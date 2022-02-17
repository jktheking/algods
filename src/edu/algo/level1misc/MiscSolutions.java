package edu.algo.level1misc;

public class MiscSolutions implements MiscQuestions {
	
	public static void main(String[] args) {
		System.out.println(Integer.toString(634, 36));
		System.out.println(INSTANCE.decimalToAnyBase(634, 8));
		System.out.println(INSTANCE.anyBaseToDecimal(1172, 8));
		
	}

	@Override
	public String decimalToAnyBase(int decimalNum, int base) {
		
		if(base<10) {
			return String.valueOf(decimalToAnyBaseLessThan10(decimalNum, base));
		}
		return decimalToAnyBaseGreaterThan10(decimalNum, base);
	}
	
	private int decimalToAnyBaseLessThan10(int decimalNum, int base) {
		int newBaseNumber = 0;
		 int tenPowers = 1;
		 while(decimalNum != 0) {
			 
			int digit = decimalNum % base; 	
			decimalNum /=base;
			
			newBaseNumber += digit*tenPowers;
			tenPowers = tenPowers*10;
		 }
		 
		 return newBaseNumber;
	}
	/**
	 * Max base supported is 36. (0-9-A-Z)
	 * 
	 * */
	private String decimalToAnyBaseGreaterThan10(int decimalNum, int base) {
		String newBaseNumber = "";
		
		if(base > 36) return newBaseNumber;
		
		 while(decimalNum != 0) {
			 
			int digit = decimalNum % base; 	
			decimalNum /=base;
			
			if(digit>9) {
				char alphabetChar =  (char)('A' + (digit - 10));
				newBaseNumber = alphabetChar + newBaseNumber;
				
			}else {
				newBaseNumber = digit+newBaseNumber;
			}
		 }
		 
		 return newBaseNumber;
	}

	@Override
	public int anyBaseToDecimal(int number, int base) {
	
		int decimalNumber = 0;
		int powerOfBase = 1;
		while(number !=0) {
			
			int digit = number%10;
			decimalNumber = decimalNumber + digit*powerOfBase;
			
			number = number/10;
			powerOfBase = powerOfBase*base;
		}
		
		return decimalNumber;
	}
	
	
	

}
