package edu.numerical.method.basic;

/**
 * 
 * Accuracy: the closeness of a measured value to the known value.
 * 
 * Precision: it is independent of accuracy, its a measure of statistical variability. 
 * 
 * example : original value of  pi_original = 3.14159265..
 * 
 * le'ts say we calculated the value of pi using some method and got value as: 
 * pi_calculated = 3.142345678
 * 
 * Question: what is accuracy? here 3 digit, as only 3.14 is common in both original and calculated
 * Question: what is precision? 10 digits, because the pi_calculated contains 10 digits.
 * 
 * */


/**
 * Note: Float has accuracy of 6-7 digit
 *       Double has accuracy of 14-15 digit   
 * */
public class PrecisionAndAccuracy {

	
	/**
	 * RoundingError:
	 * 
	 * Floating number like 1/3=0.3333... needs infinite number of digits for its representation, but
	 * java only provide 32 or 64 bit. So results into rounding error.
	 * 
	 * 
	 * 
	 * */
	
	public static void main(String...str) {
		
		/**
		 * scenario : 0.1 *100 = 10, in real-world mathematics,
		 * 
		 * or if we add 0.1 hundred times it should be the same 10.
		 * **/
		
		
		final double constant_10 = 10;
		
		double sum = 0;
		
		for(int i=0; i<100; i++) {
			sum = sum + 0.1;
		}
		System.out.println("sum:"+sum);
		
		if(constant_10 == sum) {
			System.out.println("sum is equal to 10");
		}else {
			System.out.println("sum 10 is not equal constant 10");
		}
		
		
		//So, how to compare the floating point number:
        final double epsilon = 0.000001;
        if(Math.abs(constant_10 - sum) < epsilon) {
        	System.out.println("the two floating point numbers are equal");
        }
		
        
        //so, summation of 0.1 +0.1+ 0.1 is not equal to 0.3
         System.out.println((0.1+0.1+0.1)==0.3);
       
	}
}
