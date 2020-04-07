package edu.algods.recursion;

/**
 * 
 * Possible re-arrangements of the letters in a String
 * 
 * Logic: Recursion Using String 
 * 
 *  String currentPermutation : represents the current permutation String
 *  String rest : represents the reminder of the string to be permuted 
 * 
 * Base Condition: 
 * if (no more characters left to re-arrange i.e. rest of the string is empty){
      print currentPermutation
   }
 *  
 * Recursive invocation :
 *
 * else{
 * for (every character in the rest of the String){
 *   
 *       currentPermutation =  currentPermutation+ the character,
 *        rest = rest string minus the character
 *       invoke the permutation method with new currentPermutation and new rest string
 *     }  
 *    }
 *    
 *    -----------------Sample ------------------------
 *    method argument (current permutation, rest of the string) :
 *    
 *    Finding permutation of: abcd
 *   
 *    initial state :  ("", abcd)
 *   
 *       --------------------Permutation tree-----------------------------
 *       
 *                                       ---(abc, d) ---(abcd, "")
 *                         --(ab,cd) ---|---(abd, c)  --(abdc, "")
 *            -- (a, bcd)--|--(ac, bd)   
 *           |              -- (ad, bc)
 *  ("", abcd)--(b, acd)
 *           |
 *           -- (c, abd)
 *           |
 *            --(d, abc)
 *            
 *            
 *         At First level there are 4 recursive invocations as length of rest string is 4 :  4
 *         At Second level there are 3 recursive invocations for each first level as length of rest string is 3 : (4)*3    : (previous level)*current level
 *         At Third level there are 2 recursive invocations  for each second level as length of rest string is 2 : (4*3)*2
 *         At Fourth level there are 1 recursive invocations for each third level as length of rest string is 1   : (4*3*2)*1 
 *         At fifth level there are 0 recursive invocations for each fourth level as length of rest string is 0.
 *         
 *         total permutation : 4*3*2*1 = 24. This represents n!
 * */
public class ClassicalExhaustivePermutation {
	
	
	public static void stringRecursion(String currentPermutation, String rest) {
		
		if(rest.isEmpty()) {
			System.out.println(currentPermutation);
		}else {
			for(int i=0; i<rest.length(); i++) {
				
				// currentPermutation = currentPermutation + current character
				String newCurrentPermutation = currentPermutation+rest.charAt(i);
		        //new rest = rest minus current character
				String newRest = rest.substring(0, i)+rest.substring(i+1);
				
				stringRecursion(newCurrentPermutation, newRest);
				
			}
		}
		
	}
	
	
	public static void main(String ...args) {
		
		stringRecursion("", "abcd");
		
	}

}
