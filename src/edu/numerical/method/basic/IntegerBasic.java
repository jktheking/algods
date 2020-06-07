package edu.numerical.method.basic;

public class IntegerBasic {

	public static void main(String[] args) {
		/***
		 * trick to find the 2's complement, scan from LSB side to MSB side, fix the
		 * first bit which has 1 value, and invert the remaining.
		 * 
		 * eg. 10 = 00000000000000000000000000001010 fix the first value while scaning
		 * from LSB side, i.e. LSB '10' will remain as it is and rest will be inverted
		 * 
		 * -10 = 11111111111111111111111111110110
		 */
		System.out.println("10:" + Integer.toBinaryString(10));
		System.out.println("-10:" + Integer.toBinaryString(-10));
		System.out.println( 0xff);
		
	}

}
