package edu.algo.arraystring;

public class ArrayAndStringSolutions implements ArrayAndStringProblems {

	public static void main(String[] args) {

		testIsWordVerifiableTypedUsingFaultyKeyboard();
	}

	private static void testIsWordVerifiableTypedUsingFaultyKeyboard() {
		
		System.out.println(INSTANCE.isWordVerifiableTypedUsingFaultyKeyboard("baabbccccd", "aabbcc"));
		
		System.out.println(INSTANCE.isWordVerifiableTypedUsingFaultyKeyboard("aaabbccccd", "aabbcc"));
		
		System.out.println(INSTANCE.isWordVerifiableTypedUsingFaultyKeyboard("aaabbcccc", "aabbcc"));
	}

	/**
	 * Example: actual : aabbcc typed: aaabbccccd
	 * 
	 */
	@Override
	public boolean isWordVerifiableTypedUsingFaultyKeyboard(String typedWord, String actualWord) {

		if (typedWord.length() < actualWord.length())
			return false;

		char previousActualChar = Character.MIN_VALUE;
		
		for (int i = 0, j = 0; i < typedWord.length(); i++) {

			if (j < actualWord.length() &&  typedWord.charAt(i) == actualWord.charAt(j)) {
				previousActualChar = actualWord.charAt(j);
				j++;
				continue;

			}

			if (typedWord.charAt(i) != previousActualChar) {
				return false;
			}

		}
		return true;

	}

}
