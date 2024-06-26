package edu.ds.linkedlist;

/**
 * <pre>
 * 
 * Any operation at the end of array is : O(1)
 * Any operation at the beginning( or at any specified position) which needs shifting the elements to fill the gap is: O(n)  
 * 
 * Any operation at the beginning of the singly linkedList is : O(1)
 * Any operation at the end or at any given position of the singly linkedList is : O(n),
 *  as we have to traverse the list to reach the specified location.
 *  
 *  Waste of space in Array : 0
 *  Waste of space in Singly LinkedList: O(N), for keeping the references
 *  
 * </pre>
 **/
public class Theory {

	public static void main(String[] args) {
		System.out.println(Long.SIZE);
		System.out.println(Long.hashCode(12L));
		System.out.println(Integer.hashCode(12));
		
		
		System.out.println("".hashCode());
		System.out.println("long max:"+ Long.MAX_VALUE);
		System.out.println("long max hascode"+ Long.hashCode(Long.MAX_VALUE));
		
	}
}
