package test.l150;

import java.util.HashMap;
import java.util.Map;

public class L76MinimumWindowSubstring {
	
	
	//Input: s = "ADOBECODEBANC", t = "ABC"
	
	public static void main(String[] args) {
		
		String input = "A";
		String t = "A";
		
		String solution = "";
		
		//frequency map for t
		Map<Character, Integer> tFreq = new HashMap<>();
		for(int i=0; i<t.length() ; i++) {
			tFreq.put(t.charAt(i), tFreq.getOrDefault(t.charAt(i), 0) + 1);
		}
		
		int windowMatchCount = 0;
		int desiredFrqCount = t.length();
		
		
		//window pointers ; 
		//acquire pointer start condition: with the increment of input string and to form the possible solution
		//acquire pointer end condition: with the end of input
		
		//release pointer start condition: when we reached the possible solution,
		//we need to search for desired solution, by releasing the acquired element 
		
		//release pointer end condition: while releasing the element in window, when the possible solution condition breached. 
	
		int release = 0;
		
		boolean  isToAcquire = true;
	
		
		Map<Character, Integer> windowFreq =  new HashMap<>();
		
		for(int i=0; i<input.length(); i++) {
			//acquire the character in window to reach a possible solution
			//prepare window frequency map to form the possible solution
			 
			if(isToAcquire) {
				char acquiredChar = input.charAt(i);
				//check if current char is not present in the t string then acquire blindly
				if(tFreq.containsKey(acquiredChar)) {
					
					//update the frequency of the window, and check for possible solution
					 windowFreq.put(acquiredChar, windowFreq.getOrDefault(acquiredChar, 0) + 1);
					 
					 if(windowFreq.get(acquiredChar) <= tFreq.get(acquiredChar)) {
                        //increase the total windowMatchCount 
						 windowMatchCount++;
					 }
					 
					 //once window contains all the element of t string, we need to stop the acquire phase
					 if(windowMatchCount == desiredFrqCount) {
						 isToAcquire = false;
					 }
				}	
			}
			
			
			
			//release phase
			if(!isToAcquire) {
				
				for(int j = release; j <= i && windowMatchCount == desiredFrqCount ; j++) { 
					//collect the solution
					String  interMediateSol = input.substring(j, i + 1);
					if( solution.isEmpty() ||  interMediateSol.length() < solution.length()) {
						solution = interMediateSol;
					}
					
					
				    char releasedChar  = input.charAt(j);
				    //if the released char does not belong to the t string, release it blindly
				    if(tFreq.containsKey(releasedChar)) {
				    	
				    	if(windowFreq.get(releasedChar) == 1) {
				    		windowFreq.remove(releasedChar);
				    	}else {
				    		windowFreq.put(releasedChar, windowFreq.get(releasedChar) - 1);
				    	}
				    	
				    	
				    	//check if to end the release phase
				    	if(windowFreq.getOrDefault(releasedChar,0) < tFreq.getOrDefault(releasedChar, 0)) {
				   
				    		windowMatchCount--;
				    		release = j+1;
				    		isToAcquire = true;
				    	}
				    	 
				    	
				    }
				}
				 
			 }	
			
		}
		
		System.out.println(solution);
	}

}
