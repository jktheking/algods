package edu.ds.disjointset;

public class App {

	public static void main(String[] args) {
		
		
		DisjointSet<Integer> rankAndPathCompression = new FastUnionRankAndPathCompression5(); 
		
		rankAndPathCompression.makeSet(10);
		
		System.out.println("rankAndPathCompression:"+rankAndPathCompression.toString());
		
		rankAndPathCompression.union(3, 7);
		rankAndPathCompression.union(4, 6);
		System.out.println("rankAndPathCompression:"+rankAndPathCompression.toString());
		

		System.out.println(rankAndPathCompression.find(4));
		System.out.println(rankAndPathCompression.find(3));
		rankAndPathCompression.union(4, 3);
		
		System.out.println(rankAndPathCompression.find(4));
		System.out.println(rankAndPathCompression.find(3));

		System.out.println("rankAndPathCompression:"+rankAndPathCompression.toString());
		
				
	}
}
