package edu.algods.linkedlist;

public class TestLinkedList {

	public static void main(String[] args) {
		// testInsert();
		testInsertAfter();

		//testInsertBefore();
		
		//testRemove();
	}

	public static void testInsert() {
		List<Integer> integerList = new LinkedList<>();
		integerList.insert(4);
		integerList.insert(5);
		integerList.insert(6);
		integerList.insert(7);
		integerList.insert(8);

		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
		System.out.println(integerList.traverseRecursively());
	}

	public static void testInsertAfter() {
		List<Integer> integerList = new LinkedList<>();

		integerList.insert(7);
		integerList.insert(5);
		integerList.insert(4);
		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
		integerList.insertAfter(5, 6);
		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
	}

	public static void testInsertBefore() {
		List<Integer> integerList = new LinkedList<>();

		integerList.insert(7);
		integerList.insert(5);
		integerList.insert(4);
		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
		integerList.insertBefore(4, 3);
		integerList.insertBefore(7, 6);
		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
	}

	public static void testRemove() {

		List<Integer> integerList = new LinkedList<>();
		integerList.insert(4);
		integerList.insert(5);
		integerList.insert(6);
		integerList.insert(7);
		integerList.insert(8);

		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
		
		integerList.remove(8);
		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
		
		integerList.remove(5);
		
		System.out.println("size: " + integerList.size());
		System.out.println(integerList.traverse());
		
		
	}
}
