package edu.algo.sort.inversecount;

import java.util.Arrays;

import edu.ds.tree.bit.BIT;
import edu.ds.tree.bit.Fenwick1;

/**
 * An inversion is defined as a pair a[i], a[j] such that a[i] > a[j] and i < j.
 * 
 * Inversion Count for an array indicates – how far (or close) the array is from
 * being sorted.
 * 
 * If array is already sorted then inversion count is 0. If array is sorted in
 * reverse order that inversion count is the maximum
 */
public class InversionCount {

	public static void main(String[] args) {

		int input[] = { 4, 5, 3, 2, 7, 7, 8, 1 };

		System.out.println("total inversion count bruteForce1:" + bruteForce1(input));
		System.out.println(
				"total inversion count bruteForceUsingFrequencyArray2:" + bruteForceUsingFrequencyArray2(input));
		System.out.println("total inversion count usingFenwickTreePrefixSum3:" + usingFenwickTreePrefixSum3(input));

		System.out.println("total inversion count usingMergeSort4:" + usingMergeSort4(input));

	}

	/**
	 * 
	 * Approach 1 : count greater_elements inside left_array of current position.
	 * 
	 * Approach 2 : count smaller_elements inside right_array of current position.
	 * 
	 * 
	 * Here we have used Approach 1
	 * 
	 * Time Complexity : O(n^2)
	 * 
	 */
	private static int bruteForce1(int input[]) {

		int inversionCount = 0;
		for (int i = 0; i < input.length; i++) {
			for (int j = i - 1; j >= 0; j--) {
				if (input[j] > input[i])
					inversionCount++;
			}
			// System.out.println("current inversion count:" + inversionCount + " at given
			// element:" + input[i]);
		}
		return inversionCount;
	}

	/**
	 * <pre>
	 * Approach 1 : count greater_elements inside left_array of current position.
	 * 
	 * 
	 * frequencyCount array : keeps the occurrence count of element similar to
	 * counting_sort.
	 * 
	 *  FREQUENCY_COUNT_ARRAY : startIndex---------------f---------------endIndex  
	 *   
	 *   f represents index of FREQUENCY_COUNT_ARRAY and f == input[i]
	 *   startIndex == 0;
	 *   endIndex == MAX(input) + 1;
	 * 
	 * greater_elements inside left_array of current position => 
	 * can be counted by counting the values in 
	 * FREQUENCY_COUNT_ARRAY starting from the index f = input[i] + 1  till endIndex.
	 * 
	 *  Diagrammatically => frequency_count in range {f,endIndex} =>  f---------------endIndex
	 *                                       
	 *  
	 * 
	 * frequency count from a given index 'f' can be calculated by using range-query on
	 * prefix-sum of frequencyCount array.
	 * 
	 * &#64;see "usingFenwickTreePrefixSum3"
	 * 
	 * Time Complexity : O(n^2)
	 * 
	 * </pre>
	 */
	private static int bruteForceUsingFrequencyArray2(int input[]) {

		int maxElement = Arrays.stream(input).max().getAsInt();

		int frequencyCount[] = new int[maxElement + 1];

		int inversionCount = 0;
		for (int i = 0; i < input.length; i++) {
			inversionCount = inversionCount + countFrequency(input[i] + 1, frequencyCount);

			// mark the frequency of current element.
			frequencyCount[input[i]] += 1;
		}

		return inversionCount;

	}

	/**
	 * 
	 * Time Complexity : O(n)
	 */
	private static int countFrequency(int startIndex, int frequencyCount[]) {

		int count = 0;
		for (int f = startIndex; f < frequencyCount.length; f++) {
			count += frequencyCount[f];
		}
		return count;
	}

	/**
	 * Approach 1 : count greater_elements inside left_array of current position.
	 * 
	 * 
	 * frequency count from a given index 'f' can be calculated by using range-query
	 * on prefix-sum of frequencyCount array.
	 * 
	 * FREQUENCY_COUNT_ARRAY : startIndex---------------f---------------endIndex
	 * 
	 * frequency_count in range {f,endIndex} = prefix_sum_till_endIndex MINUS
	 * prefix_sum_till_f.
	 * 
	 * Range Prefix sum can be calculated in logn using SEGMENT_TREE or
	 * FENWICK_TREE.
	 * 
	 * 
	 * Time Complexity : O(nlogn) :
	 * 
	 * 
	 */
	private static int usingFenwickTreePrefixSum3(int input[]) {

		int maxElement = Arrays.stream(input).max().getAsInt();

		// we want maxElement as last index in fenwick tree, so maxElement + 1.
		final int BIT_SIZE = maxElement + 1;
		BIT<Integer> fenwickTree = new Fenwick1(BIT_SIZE);

		int inversionCount = 0;

		for (int i = 0; i < input.length; i++) {

			inversionCount = inversionCount + fenwickTree.rangeQuery(input[i] + 1, BIT_SIZE);

			// mark the frequency of current element.
			fenwickTree.pointUpdate(input[i], 1);
		}

		return inversionCount;

	}

	/**
	 * TimeComplexity using mergeSort  = O(nlogn)
	 * 
	 * */
	private static int usingMergeSort4(int input[]) {
		int inverseCountHolder[] = new int[1];
		System.out.println("before merge:" + Arrays.toString(input));
		mergeSort(input, 0, input.length - 1, inverseCountHolder);
		System.out.println("after merge:" + Arrays.toString(input));
		return inverseCountHolder[0];
	}

	private static void mergeSort(int input[], int l, int r, int inverseCountHolder[]) {
		if (l >= r) {
			return;
		}

		int m = (l + r) / 2;
		mergeSort(input, l, m, inverseCountHolder); // for left half
		mergeSort(input, m + 1, r, inverseCountHolder); // for right half

		merge(input, l, m, r, inverseCountHolder);

	}

	/**
	 * 
	 * inversion-count is calculated during merge process.
	 * 
	 * leftArr : inputArr in the range of {l,m} is considered as leftArr in merge
	 * process.
	 * 
	 * rightArr : inputArr in the range of {m+1,r} is considered as rightArr in
	 * merge process.
	 * 
	 * Definition of inversion: i<j and leftArr[i]>rightArr[j]
	 * 
	 * When during merge process leftArr[i]>rightArr[j] then this right[j] element
	 * is smaller than all the next upcoming elements in left-array. So
	 * inversion-count should be increased by number-of-elements in left-array
	 * starting from i to till end-of-left-array.
	 * 
	 * number-of-elements in left-array starting from i to till end-of-left-array =
	 * (m-i+1)
	 * 
	 */
	private static void merge(int[] input, int l, int m, int r, int inverseCountHolder[]) {

		int temp[] = new int[r - l + 1];
		int t = 0;
		int i = l, j = m + 1;

		while (i <= m && j <= r) {

			if (input[i] <= input[j]) {
				temp[t] = input[i];
				i++;

			} else {
				temp[t] = input[j];
				j++;
				// number-of-elements in left-array starting from i to till end-of-left-array =
				// (m-i+1)
				inverseCountHolder[0] += m - i + 1;

			}
			t++;
		}

		while (i <= m) {
			temp[t] = input[i];
			i++;
			t++;
		}

		while (j <= r) {
			temp[t] = input[j];
			j++;
			t++;
		}

		System.arraycopy(temp, 0, input, l, temp.length);

	}

}
