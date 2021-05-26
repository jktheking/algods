package edu.ds.stack;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public enum Operator {

	MULTIPLICATION("*", Type.BINARY, 12) {
		@Override
		public double evaluate(double... operand) {
			
			return operand[1] * operand[0];
		}
	},

	DIVISION("/", Type.BINARY, 12) {
		@Override
		public double evaluate(double... operand) {
			return operand[1] / operand[0];
		}
	},

	MODULUS("%", Type.BINARY, 12) {
		@Override
		public double evaluate(double... operand) {
			return operand[1] % operand[0];
		}
	},

	ADDITION("+", Type.BINARY, 11) {
		@Override
		public double evaluate(double... operand) {
			return operand[1] + operand[0];
		}
	},

	SUBSTRACTION("-", Type.BINARY, 11) {
		@Override
		public double evaluate(double... operand) {
			
			return operand[1] -  operand[0];
		}
	};
	
	public abstract double  evaluate(double... operand);

	private final static Map<String, Operator> stringToEnumMap = prepareStringToOperatorMap();

	private final String operator;
	private final Type type;
	private final int precedence;

	private static Map<String, Operator> prepareStringToOperatorMap() {
		return Arrays.stream(values()).collect(Collectors.toMap(Operator::getOperator, op -> op));
	}
	
	
	public static boolean isOperator(String token) {
		
		return stringToEnumMap.containsKey(token.trim());
	}
	
	
	
	public static Optional<Operator> getIfPresent(String token) {
	return  Optional.ofNullable(stringToEnumMap.get(token));
		
	}
	
	public static Operator operator(String token) {
		Operator operator =  stringToEnumMap.get(token);
		if (operator == null) {
			throw new IllegalArgumentException(
					"Hey token, who are you I don't know ? Please come to me with your ID.. " + token);
		}
		return operator; 
	}

	private Operator(String operator, Type type, int precedence) {
		this.operator = operator;
		this.type = type;
		this.precedence = precedence;
	}

	public String getOperator() {
		return operator;
	}

	public Type getType() {
		return type;
	}

	public int getPrecedence() {
		return precedence;
	}

	public static enum Type {
		UNARY, BINARY, TERNARY;
	}

}
