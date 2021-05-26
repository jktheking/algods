package edu.ds.stack;

public class StackTest {
	
	public static void main(String[] args) {
		
		Stack<Integer> integerStack = new ArrayBackedStack<>();
		integerStack.push(10);
		integerStack.push(11);
		integerStack.push(14);
		integerStack.push(13);
		
		System.out.println("size:"+integerStack.size());
		
		System.out.println("popped element:"+integerStack.pop());

		
		System.out.println("size:"+integerStack.size());
		
		System.out.println("peeked element:"+integerStack.peek());
		
		System.out.println("size:"+integerStack.size());
		
		integerStack.pop();
		integerStack.pop();
		integerStack.pop();
		integerStack.pop();
		integerStack.pop();
		
		
	}

}
