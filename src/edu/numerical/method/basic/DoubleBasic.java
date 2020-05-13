package edu.numerical.method.basic;


/***
 * 
 * Java Double is represented by 64 bit : so possible values are 2^64
 * 
 * Let's see the bits representation from MSB end i.e left end:
 * 1st MSB bit for sign : 0 for positive, 1 for negative
 * then next 11 msb bits for : exponent
 * remaining 52 bits for  : mantisa which is normalized in the range of [.5,1]
 * 
 * 
 * Double  number is represented as: (-1)^s * m * 2^(e-1023)
 * 
 * s: represent the sign bit 0 or 1
 * here -1023 is bias, Question: why to use 1023 bisa? 
 * we have 11 bits to store the exponent,  Instead of storing it as signed 2's complement number,
 * it is easier to add -1023 to the exponent and just store it as an unsigned number.
 * 
 * In 11 bits signed lowest value will be -1023, 1 bit for sign, so total 2^10 = 1023 including 0.
 * 
 * 
 * 
 * */
public class DoubleBasic {

	public static void main(String... str) {
		
		 System.out.println("Size:"+Double.SIZE);
		  System.out.println("MIN:"+Double.MIN_VALUE);
		  System.out.println("MAX:"+Double.MAX_VALUE);
		  System.out.println("MAX Exponent:"+Double.MAX_EXPONENT);
		  System.out.println("Min Exponent:"+Double.MIN_EXPONENT);
	}
}
