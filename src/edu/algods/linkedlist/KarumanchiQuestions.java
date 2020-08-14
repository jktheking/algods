package edu.algods.linkedlist;

public interface KarumanchiQuestions<T extends Comparable<T>> {

	/**<pre>
	 * Problem-2 Find nth node from the end of a Linked List.
	 * Problem-5 Can we solve Problem-2 in one scan?
	 * 
	 * Efficient Approach:
	 * If size is less than nth node then throw error.
	 * Take two references, tempRef and solutionRef. 
	 * Initially tempRef and solutionRef both points to the start of the list.
	 * Now move the tempRef by n position, after that move both the pointer by one, 
	 *<b> when tempRef reaches to the end of the list, solutionRef reaches to the nth node. </b>
	 * 
	 * Lenght of the list = L
	 *  ------------------------------------------
	 *  ----------------------------> n position by tempRef
	 *  ------------>solutionRef      -------------> tempRef at end
	 *  
	 *  SolutionNode = L-n +1 
	 * </pre>
	 * */
	
	Node<T> findNthNodeInSingleScanFromEndWithoutUsingSizeOfList(int n); 
	
	/**
	 * SolutionNode = L-n +1; 
	 * */
	Node<T> findNthNodeFromEndUsingSizeOfList(int n); 
	
	
	/***
	 * Problem-6 Check whether the given linked list is either NULL-terminated or ends in a cycle.
	 * 
	 * Problem-7 Can we use the hashing technique for solving Problem-6?
	 * */
	Node<T> detectLoopUsingHashingTechnique();
	
	/***
	 * <Pre>
	 * Problem-9 Can we solve the Problem-6 in O(n)? Using memory less approach
	 * 
	 * QA: Why  do slow and fast pointers meet each other when there is a loop ? 
	 * 
	 * QB :But how does starting the slow pointer from the beginning of the linked list while keeping the fast pointer at the meeting place, and 
	 * then moving both one step at a time, make them meet at the starting point of the cycle? 
	 * 
	 * Solution assumptions for mathematical induction: 
	 * 
	 * 1.  Length from root_node(head) to loop_start_node = M
	 * 2.  Length of Loop = L
	 * 3.  Length of loop_start_node  to meeting_point_node = K
	 * 
	 * proof :
	 *  Distance travelled by slow pointer till metting_point = M + K
	 *  Distance travelled by fast pointer till meeting_point = M+nL+K(visualized in diagram), where n is loop count
	 *  
	 *  Since loop represents periodic movement, loop count(n) can be ignored.
	 *  
	 *   Distanace travelled by fast pointer is = 2*(distance travelled by slow pointer)
	 *     M + nL + K  = 2*(M+K)
	 *     Let's ignore n,
	 *     M+L+k = 2*(M+K)
	 *     After solving it,  M = L-K,
	 *     
	 *    If fast pointer starts at meeting_point_node and travels one step at a time, it will cover L-K = M distance, 
	 *    and if slow pointer starts at root_node and travels one step at a time, will cover M distance so both will meet at
	 *    loop_start_node.
	 * 
	 * 
	 * 
	 * */
	Node<T> detectLoopUsingFloydCycleFinding();
}
