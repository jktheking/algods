package edu.algods.stack;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public  enum SymbolPair {

	CURLY_BRACKET("{", "}"), SQUARE_BRACKET("[", "]"), ROUND_BRACKET("(", ")"), AUNGULAR_BRACKET("<", ">");

	private final String open;
	private final String close;

	private static final Map<String, SymbolPair> symbolPartToSymbolPairEmnumMap = preparePartToSymbolPairMap();

	SymbolPair(String open, String close) {
		this.open = open;
		this.close = close;
	}

	public String getOpen() {
		return open;
	}

	public String getClose() {
		return close;
	}

	public boolean isOpen(String input) {
		return open.equals(input);

	}

	public boolean isClose(String input) {
		return close.equals(input);
	}
	
	private static Map<String, SymbolPair> preparePartToSymbolPairMap() {
		return Arrays.stream(values()).flatMap(
				symbol -> Stream.of(Map.entry(symbol.getOpen(), symbol), Map.entry(symbol.getClose(), symbol)))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

	}

	public static Set<String> getSymbols() {
		return symbolPartToSymbolPairEmnumMap.keySet();
	}

	public static SymbolPair partOf(String part) {
		SymbolPair symbolPair = symbolPartToSymbolPairEmnumMap.get(part);
		if (part == null) {
			throw new IllegalArgumentException(
					"Hey part, who are you I don't know ? Please come to me with your ID.. " + part);
		}
		return symbolPair;
	}

	public static boolean isSymbol(String part) {
		return symbolPartToSymbolPairEmnumMap.containsKey(part);
	}

}