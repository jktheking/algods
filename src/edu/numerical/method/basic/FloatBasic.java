package edu.numerical.method.basic;

/**
 * 
 * 3.4523*10^5
 * 
 * Here : 3.4523 = Mantisa 10 = base 5 = exponent
 * 
 * Best apporach is to keep the mantisa in the range of 0.5 to 1
 * -------------------------------------------------------------------
 * 
 * Java float is represented by 32 bit : so possible values are 2^32
 * 
 * Let's see the bits representation from MSB end i.e left end:
 * 1st MSB bit for sign : 0 for positive, 1 for negative
 * then next 8 msb bits for : exponent
 * remaining 23 bits for  : mantisa which is normalized in the range of [.5,1]
 * 
 * 
 */

/***
 * 
 * floating point number is represented as: (-1)^s * m * 2^(e-127)
 * 
 * s: represent the sign bit 0 or 1
 * here -127 is bias, Question: why to use 127 bisa? 
 * we have 8 bits to store the exponent,  Instead of storing it as signed 2's complement number,
 * it is easier to add -127 to the exponent and just store it as an unsigned number.
 * 
 * In 8 bits signed lowest value will be -127, 1 bit for sign, so total 2^7 = 127 including 0.
 * 
 * 
 * 
 * */

public class FloatBasic {

  public static void main(String...str) {
	  System.out.println("Size:"+Float.SIZE);
	  System.out.println("MIN:"+Float.MIN_VALUE);
	  System.out.println("MAX:"+Float.MAX_VALUE);
	  System.out.println("MAX Exponent:"+Float.MAX_EXPONENT);
	  System.out.println("Min Exponent:"+Float.MIN_EXPONENT);
	  
  }	
}
