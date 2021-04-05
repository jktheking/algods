package edu.algods.linkedlist.revision;

public interface KarumanchiQuestions<T extends Comparable<T>> {

	/**
	 * <pre>
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
	 */

	Node<T> findNthNodeInSingleScanFromEndWithoutUsingSizeOfList(int n);




	/***
	 * <Pre>
	 * Problem-9 Can we solve the Problem-6 in O(n)? Using memory less approach
	 * 
	 * QA: Why do slow and fast pointers meet each other when there is a loop ?
	 * 
	 * QB :But how does starting the slow pointer from the beginning of the linked
	 * list while keeping the fast pointer at the meeting place, and then moving
	 * both one step at a time, make them meet at the starting point of the cycle?
	 * 
	 * Solution assumptions for mathematical induction:
	 * 
	 * 1. Length from root_node(head) to loop_start_node = M
	 * 
	 * 2. Length of Loop = L
	 * 
	 * 3. Length of loop_start_node to meeting_point_node = K
	 * 
	 * proof : Distance travelled by slow pointer till metting_point = M + K
	 * Distance travelled by fast pointer till meeting_point = M+nL+K(visualized in
	 * diagram), where n is loop count
	 * 
	 * Since loop represents periodic movement, loop count(n) can be ignored.
	 * 
	 * Distanace travelled by fast pointer is = 2*(distance travelled by slow
	 * pointer) M + nL + K = 2*(M+K) Let's ignore n, M+L+k = 2*(M+K) After solving
	 * it, M = L-K,
	 * 
	 * If fast pointer starts at meeting_point_node and travels one step at a time,
	 * it will cover L-K = M distance, and if slow pointer starts at root_node and
	 * travels one step at a time, will cover M distance so both will meet at
	 * loop_start_node.
	 * 
	 * 
	 * 
	 */
	Node<T> detectLoopUsingFloydCycleFinding();

	/**
	 * Problem 14: Detect length of the Loop
	 */
	int findLenghtOfTheLoopUsingFloydCycleFinding();

	/**
	 * Problem 15: Insert a node in a sorted linked list.
	 */
	boolean insertInSortedLinkedList(T data);

	/**
	 * Problem 16.A
	 * 
	 * Remove the element from original list one by one and prepare a reversed list
	 * by placing the new element at beginning of reversed list
	 */
	void reverseLinkList();

	/**
	 * Problem 16.B
	 */
	void reverseLinkListRecursively();

	/**
	 * Problem 17.A Suppose there are two singly linked lists both of which
	 * intersect at some point and become a single linked list. The head or start
	 * pointers of both the lists are known, but the intersecting node is not known.
	 * Also, the number of nodes in each of the lists before they intersect is
	 * unknown and may be different in each list. List1 may have n nodes before it
	 * reaches the intersection point, and List2 might have m nodes before it
	 * reaches the intersection point where m and n may be m = n,m < n or m > n.
	 * Give an algorithm for finding the merging point.
	 * 
	 * SolutionA: is to compare every node pointer in the first list with every
	 * other node pointer in the second list by which the matching node pointers
	 * will lead us to the intersecting node. But, the time complexity in this case
	 * will be O(mn) which will be high.
	 * 
	 * SolutionB: keep the smaller list in hash-set, and iterate the bigger list and
	 * check contains at each node.
	 */
	void findIntersectionNodeOfTwoListUsingBruteForce(Node<T> startB);

	/**
	 * Problem 20
	 */
	void findIntersectionNodeOfTowListUsingStack(Node<T> startB);

	/**
	 * TODO: Problem 21 to 22 Searching Technique
	 */
	void findIntersectionNodeUsingSearchTechnique();

	/**
	 * Problem 23
	 */
	void findIntersectionNodeUsingDistanceDiffTechnique(List<T> listB);

	/**
	 * Problem 24 to 27
	 */
	Node<T> middleElementOfLinkedList();

	/**
	 * Problem 28
	 */
	void printListFromEndUsingRecursion();

	/**
	 * Problem 29: for even 2x pointer.GETNEXT will point to NULL at end.
	 * 
	 */
	void isLinkedListEven();

	/**
	 * Problem 31 Iterative
	 */
	List<T> mergeTwoSortedList(List<T> a, List<T> b);

	/**
	 * Problem 31 Recursively
	 */
	void mergeTwoSortedListRecursively(List<T> a, List<T> b);

	/**
	 * Problem-32 Reverse the linked list in pairs. If you have a linked list that
	 * holds 1 → 2 → 3 → 4 → X, then after the function has been called the linked
	 * list would hold 2 → 1 → 4 → 3 → X.
	 */
	void reverseListInPairRecursively();

	/**
	 * Problem-32 Reverse the linked list in pairs. If you have a linked list that
	 * holds 1 → 2 → 3 → 4 → X, then after the function has been called the linked
	 * list would hold 2 → 1 → 4 → 3 → X.
	 */
	void reverseListInPairIteratively();

	/** TODO: PROBLEM 33 and PROBLEM 34 */

	/**
	 * Problem 35 : use slow and fast-pointer to figure out the mid node.
	 * 
	 */

	void splitCircularListIntoTwoEqualCircularList();

	/**
	 * Problem 37: check if the linked list is palindrome or not For even 2x pointer
	 * will point to NULL at end.
	 * 
	 */

	void isListPalindrome();

	/**
	 * Problem-38: For a given K value (K > 0) reverse blocks of K nodes in a list.
	 * Example: Input: 1 2 3 4 5 6 7 8 9 10. Output for different K values: For K =
	 * 2: 2 1 4 3 6 5 8 7 10 9 For K = 3: 3 2 1 6 5 4 9 8 7 10 For K = 4: 4 3 2 1 8
	 * 7 6 5 9 10
	 */
	void reverseInBlockOfKNodes(int k);

	/**
	 * Problem 40: Joseph Circle
	 */
	void getJosephPoint(int eliminationPosition);

	/**
	 * Problem 41:
	 */
	void cloneListWithRandomPointer();

	/**
	 * Problem 43:
	 */
	void deleteGivenNodeUsingIteration(Node<T> node);

	/**
	 * Problem 43: By shifting the data, first shift the data of next node to
	 * current node, now delete the next node
	 */
	void deleteGivenNodeWithoutUsingIteration(Node<T> node);

	/**
	 * Problem 46: Given a singly linked list, write a function to find the last
	 * element from the beginning whose n%k == 0, where n is the number of elements
	 * in the list and k is an integer constant. For example, if n = 19 and k = 3
	 * then we should return 18th node.
	 * 
	 * Note: For this problem the value of n is not known in advance.
	 */
	Node<T> findLastModularNode(int modularConstant_K);

	/**
	 * Problem 48: logic think link list as block of k nodes if k=3, and n=14 
	 * 
	 * Ceilof(14/3) = 5 
	 * ------------------------------------------|
	 * 
	 * |1,2,3 4,5,6 7,8,9 10,11,12 13,14
	 * 
	 * | ------------------------------------------|
	 *  
	 * Here we have 5 blocks and each block contains k element i.e. 3
	 * 
	 * Q*K+ R = n; Since we are taking Ceil we can ignore R so Q*K = n
	 * 
	 * we have to find the Q ?
	 * 
	 * Solution1 : increment the Q by one after each block(k node) traversal.
	 * 
	 * 
	 */
	Node<T> findCeiledFractionalNodeUsingPointerIncrement(int k);

	/**
	 * Problem 48: logic think link list as block of k nodes if k=3, and n=14 Ceil
	 * of(14/3) = 5 ------------------------------------------| |1,2,3 4,5,6 7,8,9
	 * 10,11,12 13,14| ------------------------------------------| Here we have 5
	 * blocks and each block contains k element i.e. 3
	 * 
	 * Q*K+ R = n; Since we are taking ceil we can ignore R so Q*K = n
	 * 
	 * we have to find the Q ?
	 * 
	 * Solution2 : 1st block represent first modulo when i%k==0, 2nd block represent
	 * 2nd modulo when i%k==0
	 * 
	 * Similarly, nth block represent nth modulo when i%k==0
	 * 
	 */
	Node<T> findCeiledFractionalNodeUsingModuloDivison(int k);

	/**
	 * X*X=List length 1*1=1 2*2=4 3*3=9 4*4=16 5*5=25 till X*X is either the exact
	 * match or greater than the list length
	 * 
	 */
	Node<T> findSquareRootNode();

	/**
	 * Problem-53 Given two linked lists, each list node with one integer digit, add
	 * these two linked lists. The result should be stored in the third linked list.
	 * Also note that the head node contains the most significant digit of the
	 * number.
	 */
	List<Integer> summationForEqualSizedList(List<Integer> digitList1, List<Integer> digitList2);

	/**
	 * Problem-53 Given two linked lists, each list node with one integer digit, add
	 * these two linked lists. The result should be stored in the third linked list.
	 * Also note that the head node contains the most significant digit of the
	 * number.
	 */
	List<Integer> summationForUnEqualSizedList(List<Integer> digitList1, List<Integer> digitList2);

	/**
	 * Problem-55 Given a list, List1 = {A1, A2, . . . An–1; An) with data, reorder
	 * it to {A1,An,A2,An–1} without using any extra space.
	 * 
	 * Solution: Half the list using Flyod algorithm i.e. slow and fast pointer
	 * Reverse the second half merge inplace
	 */
	List<T> listReorder();

	/**
	 * Problem-56 Given two sorted linked lists, given an algorithm for the printing
	 * common elements of them.
	 * 
	 * Efficient solution: apply merge on two sorted list.
	 */
	List<T> getCommonElementList(List<T> list1, List<T> list2);
}
