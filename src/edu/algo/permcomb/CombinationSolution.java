package edu.algo.permcomb;

import java.util.ArrayList;
import java.util.List;

public class CombinationSolution implements CombinationQuestion {

	public static void main(String[] args) {
		INSTANCE.printGroupCombination(new String[] { "abc", "fg", "hijk" });

	}

	@Override
	public void printGroupCombination(String[] groups) {

		System.out.println(getGroupCombination(groups, 0));

	}

	/**
	 * Get all possible combinations that can be formed by picking atmost one char
	 * from each group.
	 * 
	 * Example ['abc' 'fg', 'hijk']; a single combination contains 3 char one from
	 * each group. e.g. afh, afi
	 * 
	 * Total number of combinations : It is repersented by cartesian product of each
	 * group.
	 * 
	 * Total number of combinations : g1 * g2 * g3 * .. *gn; g represents element
	 * count in the respective group.
	 * 
	 * Example ['abc' 'fg', 'hijk'] : total combinations : 3 * 2 * 4 = 24
	 * 
	 * <pre>
	 * HYPOTHESIS : getGroupCombination([g1, g2, g3]) => total  g1*g2*g3 combinations
	 * 
	 * SUBSTITUTION: collate the group g2, g3; getGroupCombination([g2, g3]) => total  g2*g3 combinations
	 * 
	 * INDUCTION strategy: need to map each element of g1 group, with each element returned
	 * by SUBSTITUTION step.
	 * 
	 * </pre>
	 * 
	 * TimeComplexity : O(g1*g2*g3*..*gn)
	 * 
	 */
	private List<String> getGroupCombination(String[] groups, int idx) {

		if (idx == groups.length) {
			return List.of("");
		}
		List<String> collatedGroupCombs = getGroupCombination(groups, idx + 1);
		char[] firstGroupChars = groups[idx].toCharArray();

		List<String> result = new ArrayList<>();
		for (char c : firstGroupChars) {
			for (String comb : collatedGroupCombs) {
				result.add(c + comb);
			}
		}
		return result;

	}

}
