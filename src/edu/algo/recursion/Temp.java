package edu.algo.recursion;

import java.util.ArrayList;
import java.util.List;

public class Temp {

	private static List<List<Integer>> finalList = new ArrayList<>();

	public static void main(String[] args) {
		int[] counter = new int[1];
		List<Integer> input = new ArrayList<>();
		input.add(3);
		input.add(5);
		input.add(7);
		input.add(9);
		input.add(11);
       // input.add(12);
//		input.add(13);
//		input.add(14);
		getAllCombinations(input, new ArrayList<>(), counter);
		System.out.println("counter:" + counter[0]);
		System.out.println("combination:" + finalList.size());

	}

	private static void getAllCombinations(List<Integer> input, List<Integer> temOutput, int[] counter) {

		
		
		if (temOutput.size() == 3) {
			finalList.add(temOutput);
			System.out.println(temOutput);
			return;
		}
		
		

		if (input.isEmpty()) {
			return;

		}

		// if(input.isEmpty() || input.size() + temOutput.size() < 4) { return; }

		counter[0] += 1;		

		Integer includeExcludeCandidate = input.remove(0);

		List<Integer> originalCopyOfTempOutput = new ArrayList<>(temOutput);

		// by including the candidate
		temOutput.add(includeExcludeCandidate);
		getAllCombinations(new ArrayList<>(input), new ArrayList<>(temOutput), counter);
		// by excluding the candidate
		getAllCombinations(new ArrayList<>(input), originalCopyOfTempOutput, counter);

	}
}
