package edu.algo.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Context : Binary Search on K sorted lists each of size n.
 * 
 * <pre>
 * Approach 1 : Naive, Do binary search on each list.
 * Time Complexity : k*log(n)
 * Space Complexity :k*n
 * 
 * Approach 2: Unified Binary Search: Merge all lists into one single list. 
 *             Bridge : Create a position tuple of size K against each element of unified list. 
 *             Position tuple contains index of the given element in each list.
 * 
 * Time Complexity : log(k*n) -> for binary search on unified list
 *                 : constant time to navigate to index of each list in the position tuple; 
 *                   So total time to navigate  = K
 *                 : Total time complexity : K + log(k*n) = K + logK + logn = (K + logn)   
 *                 
 * Space Complexity : size of unified list = (K*n), and against each element we have tuple of size K
 *                  : total space ; (K*n)*k = K^2n
 *                  
 *                                            
 * Approach 3: Fractional Cascading:
 *Fractional cascading is a simple technique that allows you to improve the running time of multiple binary searches, 
 *which are conducted at the same time.
 *             
 * PreProcessing and Bridge creation:  
 * 
 * STEP 1: The first step in pre-processing is to create 'K' merged lists from k original lists. 
 * These merged lists are created in bottom-up fashion. Lets denote the merged list with M[list_number] and original list with L[list_number]
 * 
 * Sorted(M[k])    = L[k]     +   {}
 * Sorted(M[k-1])  = L[k -1]  +  EVERY_ALTERNATE_ELEMENNT_OF_M[k] 
 * Sorted(M[k-2])  = L[k -2]  +  EVERY_ALTERNATE_ELEMENNT_OF_M[k-1] 
 *  .......
 * Sorted(M[0])   = L[0]      +  EVERY_ALTERNATE_ELEMENNT_OF_M[1] 
 *    
 * STEP 2: Position tuple : Need to attach 2-index position tuple as bridge to every element of merged list. 
 * First index of tuple represents position of element in L[k] list.
 * Second index of tuple represents position of element in M[k+1] list.   
 * 
 * Question : Why is the name fractional cascading ?
 * 
 * Fractional: is because we merge 1/2th fraction of every lower M[K+1] list to L[K] list. 
 * Fraction may be anything but the most optimum one is 1/2 because there would be less number of comparisons and optimum bridge traversal.
 * 
 * Cascading: is because we merge the lower list to upper list in recursive fashion.
 *
 * 
 * Merged-list is composed of two type of elements:
 * 1. Non-cascaded element : is the original-list element at the same level.
 * 2. Cascaded element : is the element from lower level merged-list.
 * 
 *Property of MERGED-LIST in terms of bridge traversal: 
 * 1. Bridge traversal via Cascaded element: it causes the control to land on the same element in lower-level-merged-list.
 * 2. Bridge traversal via Non-Cascaded element : it causes the control to land on the same/successor element in lower-level-merged-list.
 *    
 * 
 * Search Algo:
 * STEP 1:  Need to perform binary search on M[0] merged list to figure-out the  position of target_value. 
 * STEP 2: Bridge traversal to figure-out position of target value in original lists. 
 *        position tuple of target_value in M[0] => provides the position of target_value in L[0] list and position of target_value in M[1] list
 *        position tuple of target_value in M[1] => provides the position of target_value in L[1] list and position of target_value in M[2] list
 *        .....
 *   Similarly, position of target_value in all the original list can be traversed with the help of position tuple bridge.   
 *        
 *  
 *  NUMBER OF ELEMENT IN MERGED LIST 
 *  
 *   Lets calculate the number of elements in  M[0] list, by assuming each original list contains n elements 
 *   
 *  SIZE_M[0] = all_elements_of_L[0] + 1/2(elements_of_L[1]) + 1/4(elements_of_L[2]) + 1/8(elements_of_L[3]) + ...
 *  SIZE_M[0] =  n + 1/2(n) + 1/4(n) + 1/8(n) + ...
 *            =  n +n(1/2 + 1/4 +1/8 + ..)
 *            =  n +n(1)
 *            = 2n
 *  Thus, any merged list can contain at most 2n elements.          
 *                                                     
 *                                                        
 *   
 *  Time Complexity : 
 *   STEP1: binary search on M[0] =>  log(2n) = log2 + logn = logn  
 *   STEP2 :bridge traversal = K
 *   
 *   total time complexity = O(K + logn)
 *   
 *   Space Complexity : 
 *    Since each merged list size cannot grow beyond 2n, so total size of K merged list cannot grow beyond =  K*2n
 *    Since each element of merged list is associated with 2-sized tuple bridge, so the size of tuple elements for each merged list would be at max 2 * 2n = 4n.
 *    Total size of tuple elements for k merged list cannot grow beyond = K*4n.
 *    
 *   Total space complexity = 2n*k + 4n*k = 6n*k = O(nK)
 *    
 *    
 *   Question : Why do we pick  alternate elements  while forming the merged list ? Why don't we pick the total element ?
 *   Answer : By picking every other element from lower-level lists, we fill the gaps in value ranges in the original list L[i], 
 *            thus giving us a uniform spread of values across all merged lists. If we don't pick the alternate elements we may get
 *            skewed merged list and in worst case we could have time complexity of Naive approach i.e. K*log(n).
 *            
 *            
 * Applications : Computational Geometry, range searches, packet filtering etc.
 * </pre>
 * 
 * Questions format :
 *
 * TYPE1 Finding the given element in each list : there are k sorted lists of
 * numbers, and we must find in each list the given target_number
 * 
 * TYPE2 Finding Predecessor in each list : there are k sorted lists of numbers,
 * and we must find in each list the first_number less than or equal to the
 * given target_number
 *
 * TYPE3 Finding Successor in each list : there are k sorted lists of numbers,
 * and we must find in each list the first_number greater than or equal to the
 * given target_number
 *
 * 
 * 
 * 
 */
public class FractionalCascading {
	
	public interface IFractionalCascading{
		 Map<Integer, List<?>> find(int targetValue);
		 Map<Integer, List<?>>  findPredecessor(int targetValue);
		 Map<Integer, List<?>> findSuccessor(int targetValue, boolean duplicateAllowed);
	}
	
	
	

	private static final List<int[]> ORIGINAL_LIST = initializeOriginalList();

	private static final Map<Integer, Data[]> MERGED_LIST_MAP = new HashMap<>((int) (ORIGINAL_LIST.size() / 0.75));

	public static void main(String[] args) {

		prepareMergedListsWithBridge();
		IntStream.range(0, ORIGINAL_LIST.size())
				.forEach(i -> System.out.println("i:" + i + ":" + Arrays.toString(MERGED_LIST_MAP.get(i))));

		System.out.println("--ORIGINAL_LIST---------");
		IntStream.range(0, ORIGINAL_LIST.size())
				.forEach(i -> System.out.println("i:" + i + ":" + Arrays.toString(ORIGINAL_LIST.get(i))));

		 testFind();
		testSuccessor();
		
		testPredecessor();

	}

	private static void testFind() {
		System.out.println("Finding 18: " + find(18));
		System.out.println("Finding -2: " + find(-2));
		System.out.println("Finding -3: " + find(-3));
		System.out.println("Finding 0: " + find(0));
		System.out.println("Finding -45: " + find(-45));
		System.out.println("Finding 60: " + find(60));

	}
	
	private static void testPredecessor() {
		System.out.println("Finding predecessor 18: " + findPredecessor(18));
		System.out.println("Finding predecessor -2: " + findPredecessor(-2));
		System.out.println("Finding predecessor -3: " + findPredecessor(-3));
		System.out.println("Finding predecessor 0: " + findPredecessor(0));
		System.out.println("Finding predecessor -45: " + findPredecessor(-45));
		System.out.println("Finding predecessor 60: " + findPredecessor(60));

	}

	private static void testSuccessor() {

		System.out.println("Finding successor 18: " + findSuccessor(18, true));
		System.out.println("Finding successor -2: " + findSuccessor(-2, true));
		System.out.println("Finding successor -3: " + findSuccessor(-3, true));
		System.out.println("Finding successor 0: " + findSuccessor(0, true));
		System.out.println("Finding successor -45: " + findSuccessor(-45, true));
		System.out.println("Finding successor 60: " + findSuccessor(60, true));

	}

	/**
	 * Target value should lie between negative-infinity(Integer.MIN) and
	 * positive-infinity(INTEGER.MAX).i.e. targetValue = (Integer.MIN, INTEGER.MAX)
	 * where range is exclusive.
	 * 
	 */
	private static Map<Integer, List<?>> find(int targetValue) {

		Data[] mergedList0 = MERGED_LIST_MAP.get(0);

		Map<Integer, List<?>> result = new HashMap<>((int) (ORIGINAL_LIST.size() / 0.75));

		int mergedIndex = bisectLeft(targetValue, mergedList0, 0, mergedList0.length - 1);

		// bridge traversal
		for (int i = 0; i < ORIGINAL_LIST.size(); i++) {

			int[] originalList = ORIGINAL_LIST.get(i);
			Data[] mergedList = MERGED_LIST_MAP.get(i);

			int originalIndex;
			/**
			 * <pre>
			 *Property of MERGED-LIST in terms of bridge traversal: 
			 * 1. Bridge traversal via Cascaded element: it causes the control to land on the same element in lower-level-merged-list.
			 * 2. Bridge traversal via Non-Cascaded element : it causes the control to land on the same/successor element in lower-level-merged-list.
			 * 
			 * 'mergedIndex' would be either of Cascaded-element or Non-Cascaded-element. 
			 * If the mergedIndex is of Cascaded-element then  'bridge.mergedListPosition' will hold the value of same-element in lower-merged-list. 
			 * If the mergedIndex is of Non-Cascaded-element then ''bridge.mergedListPosition' will hold the value of successor-element/same-element in lower-merged-list.
			 * 
			 * Since bridge traversal causes the control to land on the same-element or successor-element in lower-level-merged-list, so we need to search the
			 * target-value at 'mergedIndex' or 'mergedIndex - 1' in lower-level-merged-list.
			 * 
			 * </pre>
			 */

			if (targetValue <= mergedList[mergedIndex - 1].value) {
				originalIndex = mergedList[mergedIndex - 1].bridge.originalListPosition;
				mergedIndex = mergedList[mergedIndex - 1].bridge.mergedListPosition;
			} else {
				originalIndex = mergedList[mergedIndex].bridge.originalListPosition;
				mergedIndex = mergedList[mergedIndex].bridge.mergedListPosition;
			}

			result.put(i, List.of(originalIndex, targetValue == originalList[originalIndex] ? true : false));

		}

		return result;
	}

	/**
	 * Target value should lie between negative-infinity(Integer.MIN) and
	 * positive-infinity(INTEGER.MAX).i.e. targetValue = (Integer.MIN, INTEGER.MAX)
	 * where range is exclusive.
	 * 
	 */
	private static	Map<Integer, List<?>>  findPredecessor(int targetValue) {

		Data[] mergedList0 = MERGED_LIST_MAP.get(0);

		Map<Integer, List<?>> result = new HashMap<>((int) (ORIGINAL_LIST.size() / 0.75));

		int mergedIndex = bisectLeft(targetValue, mergedList0, 0, mergedList0.length - 1);

		// bridge traversal
		for (int i = 0; i < ORIGINAL_LIST.size(); i++) {

			int[] originalList = ORIGINAL_LIST.get(i);
			Data[] mergedList = MERGED_LIST_MAP.get(i);

			int originalIndex;
	
			if (targetValue <= mergedList[mergedIndex - 1].value) {
				originalIndex = mergedList[mergedIndex - 1].bridge.originalListPosition;
				mergedIndex = mergedList[mergedIndex - 1].bridge.mergedListPosition;
			} else {
				originalIndex = mergedList[mergedIndex].bridge.originalListPosition;
				mergedIndex = mergedList[mergedIndex].bridge.mergedListPosition;
			}

			result.put(i, List.of("index:"+(originalIndex-1), " value:"+originalList[originalIndex-1]));

		}

		return result;
	}

	/**
	 * Target value should lie between negative-infinity(Integer.MIN) and
	 * positive-infinity(INTEGER.MAX).i.e. targetValue = (Integer.MIN, INTEGER.MAX)
	 * where range is exclusive.
	 * 
	 * 
	 * * It we want to optimize for duplicateAllowed, then the pre-processing should
	 * be done using bisectRight.
	 * 
	 * 
	 */
	private static Map<Integer, List<?>> findSuccessor(int targetValue, boolean duplicateAllowed) {

		Data[] mergedList0 = MERGED_LIST_MAP.get(0);

		Map<Integer, List<?>> result = new HashMap<>((int) (ORIGINAL_LIST.size() / 0.75));

		int mergedIndex = bisectLeft(targetValue, mergedList0, 0, mergedList0.length - 1);

		// bridge traversal
		for (int i = 0; i < ORIGINAL_LIST.size(); i++) {

			int[] originalList = ORIGINAL_LIST.get(i);
			Data[] mergedList = MERGED_LIST_MAP.get(i);

			int originalIndex;

			if (targetValue <= mergedList[mergedIndex - 1].value) {
				originalIndex = mergedList[mergedIndex - 1].bridge.originalListPosition;
				mergedIndex = mergedList[mergedIndex - 1].bridge.mergedListPosition;
			} else {
				originalIndex = mergedList[mergedIndex].bridge.originalListPosition;
				mergedIndex = mergedList[mergedIndex].bridge.mergedListPosition;
			}

			/**
			 * 
			 * It we want to optimize for duplicateAllowed, then the pre-processing should
			 * be done using bisectRight.
			 */
			if (duplicateAllowed) {
				int index = bisectRight(targetValue, originalList, originalIndex, originalList.length - 1);

				result.put(i, List.of("index:" + index, " value:" + originalList[index]));
			} else {

				if (originalList[originalIndex] <= targetValue) {
					result.put(i, List.of("index:" + originalIndex + 1, " value:" + originalList[originalIndex + 1]));
				} else {
					result.put(i, List.of("index:" + originalIndex, " value:" + originalList[originalIndex]));
				}

			}

		}

		return result;
	}

	private static List<int[]> initializeOriginalList() {
		List<int[]> input = new ArrayList<>(6);
		input.add(new int[] { Integer.MIN_VALUE, 10, 20, 30, 40, 50, 60, 70, 80, 90, 100, Integer.MAX_VALUE });
		input.add(new int[] { Integer.MIN_VALUE, 5, 15, 25, 35, 45, 55, 65, 75, 85, 95, Integer.MAX_VALUE });
		input.add(new int[] { Integer.MIN_VALUE, -7, -5, -3, -3, -3, -1, 0, 9, 18, 27, 36, Integer.MAX_VALUE });
		input.add(new int[] { Integer.MIN_VALUE, -3, 0, 3, 6, 9, 12, 15, 18, 21, 24, Integer.MAX_VALUE });
		input.add(new int[] { Integer.MIN_VALUE, 6, 12, 18, 24, 30, 36, 42, 48, 54, 60, Integer.MAX_VALUE });
		input.add(new int[] { Integer.MIN_VALUE, -45, -36, -27, -18, -9, 0, 9, 18, 27, 36, Integer.MAX_VALUE });
		return Collections.unmodifiableList(input);

	}

	private static void prepareMergedListsWithBridge() {

		// Data[] mergedList = Arrays.stream(ORIGINAL_LIST.get(ORIGINAL_LIST.size() -
		// 1))
		// .mapToObj(val -> new Data(val, null)).toArray(Data[]::new);

		int[] lastOriginalList = ORIGINAL_LIST.get(ORIGINAL_LIST.size() - 1);

		Data[] mergedList = new Data[lastOriginalList.length];
		for (int i = 0; i < mergedList.length; i++) {
			mergedList[i] = new Data(lastOriginalList[i], new Bridge(i, i));
		}
		MERGED_LIST_MAP.put(ORIGINAL_LIST.size() - 1, mergedList);

		for (int i = ORIGINAL_LIST.size() - 2; i >= 0; i--) {

			mergedList = prepareSingleMergedList(mergedList, ORIGINAL_LIST.get(i));
			MERGED_LIST_MAP.put(i, mergedList);
		}

	}

	private static Data[] prepareSingleMergedList(Data[] mergedList, int[] originalList) {

		Data[] resultMergedList = new Data[originalList.length + mergedList.length / 2];

		int leftIndexOriginalList = 0, leftIndexMergedList = 0;

		int rightIndexOriginalList = 0, rightIndexMergedList = 1, res = 0;

		while (rightIndexOriginalList < originalList.length && rightIndexMergedList < mergedList.length) {

			if (originalList[rightIndexOriginalList] <= mergedList[rightIndexMergedList].value) {

				int positionInOriginalList = rightIndexOriginalList;
				int bisectPositionInMergedList = bisectLeft(originalList[rightIndexOriginalList], mergedList,
						leftIndexMergedList, rightIndexMergedList);

				resultMergedList[res] = new Data(originalList[rightIndexOriginalList],
						new Bridge(positionInOriginalList, bisectPositionInMergedList));

				// If there are consecutive duplicates then no need to shift
				// leftIndexOriginalList index
				if (originalList[leftIndexOriginalList] != originalList[rightIndexOriginalList]) {
					leftIndexOriginalList = rightIndexOriginalList;
				}

				rightIndexOriginalList++;
			} else {

				int bisectPositionInOriginalList = bisectLeft(mergedList[rightIndexMergedList].value, originalList,
						leftIndexOriginalList, rightIndexOriginalList);
				int positionInMergedList = rightIndexMergedList;

				resultMergedList[res] = new Data(mergedList[rightIndexMergedList].value,
						new Bridge(bisectPositionInOriginalList, positionInMergedList));

				// If there are consecutive duplicates then no need to shift leftIndexMergedList
				// index
				if (mergedList[leftIndexMergedList].value != mergedList[rightIndexMergedList].value) {
					leftIndexMergedList = rightIndexMergedList;
				}
				rightIndexMergedList += 2;
			}

			res++;

		}

		while (rightIndexOriginalList < originalList.length) {
			resultMergedList[res] = new Data(originalList[rightIndexOriginalList],
					new Bridge(rightIndexOriginalList, mergedList.length));
			rightIndexOriginalList++;
			res++;
		}

		while (rightIndexMergedList < mergedList.length) {
			resultMergedList[res] = new Data(mergedList[rightIndexMergedList].value,
					new Bridge(originalList.length, rightIndexMergedList));
			rightIndexMergedList += 2;
			res++;
		}

		return resultMergedList;
	}

	/***
	 * It returns the insertion index of targetValue in mergedList. If the
	 * targetValue is present in the mergedList then returns the left-most index.
	 * 
	 * Because of the nature of bisetLeft: targetValue <= arrayValue If targetValue
	 * is not same to arrayValue it means arrayValue is immediate successor of
	 * targetValue
	 * 
	 */
	private static int bisectLeft(int targetValue, Data[] mergedList, int leftIndex, int rightIndex) {

		while (leftIndex < rightIndex) {

			int midIndex = (leftIndex + rightIndex) / 2;

			if (mergedList[midIndex].value < targetValue) {
				leftIndex = midIndex + 1;
			} else {
				rightIndex = midIndex;
			}

		}

		return leftIndex;

	}

	/***
	 * It returns the insertion index of targetValue in originalList. If the
	 * targetValue is present in the originalList then returns the left-most index.
	 * 
	 * Because of the nature of bisetLeft: targetValue <= arrayValue. If targetValue
	 * is not same to arrayValue it means arrayValue is immediate successor of
	 * targetValue
	 * 
	 * 
	 */
	private static int bisectLeft(int targetValue, int[] originalList, int leftIndex, int rightIndex) {

		while (leftIndex < rightIndex) {

			int midIndex = (leftIndex + rightIndex) / 2;

			if (originalList[midIndex] < targetValue) {
				leftIndex = midIndex + 1;
				// in case of exact match (consecutive-duplicate or unique), we need to search
				// towards left.
			} else if (originalList[midIndex] == targetValue) {
				rightIndex = midIndex;
			} else {
				rightIndex = midIndex;
			}

		}

		return leftIndex;

	}

	private static int bisectRight(int targetValue, int[] originalList, int leftIndex, int rightIndex) {

		while (leftIndex < rightIndex) {

			int midIndex = (leftIndex + rightIndex) / 2;

			// in case of exact match (consecutive-duplicate or unique), we need to search
			// towards right.
			if (originalList[midIndex] <= targetValue) {
				leftIndex = midIndex + 1;
			} else {
				rightIndex = midIndex;
			}

		}

		return leftIndex;

	}

	private static class Data {

		private final int value;
		private final Bridge bridge;

		public Data(int value, Bridge bridge) {
			super();
			this.value = value;
			this.bridge = bridge;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append(value).append(bridge);
			return builder.toString();
		}

		

	}

	private static class Bridge {

		private final int originalListPosition;
		private final int mergedListPosition;

		public Bridge(int originalListPosition, int mergedListPosition) {
			super();
			this.originalListPosition = originalListPosition;
			this.mergedListPosition = mergedListPosition;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("(").append(originalListPosition).append(",").append(mergedListPosition).append(")");
			return builder.toString();
		}

	}

}
