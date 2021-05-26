package edu.ds.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Vertex {
	
	private final int data;
	private boolean visited;
	private final int label;
	private final List<Vertex> neighbourVertices = new ArrayList<>(0);

	public Vertex(int label) {
		super();
		this.data = label;
		this.label = data;
	}

	public boolean isVisited() {
		return visited;
	}
	
	public boolean isNotVisited() {
		return visited==false;
	}
	
	public void addNeighbourVertex(Vertex vertex) {
		this.neighbourVertices.add(vertex);
	}
	
	public void addNeighbourVertices(Vertex v[]) {
		this.neighbourVertices.addAll(Arrays.asList(v));
	}
	
	public void setVisited(boolean visited) {
		this.visited = visited;
	}
	public int getData() {
		return data;
	}
	
	public List<Vertex> getNeighbourVertices() {
		return neighbourVertices;
	}

	@Override
	public String toString() {
		return String.format("[v=%s]", label);
	}
	

	/***
	 * lables are
	 *   1
	 *  
	 * 2,3,4
	 * 
	 * 5,6,7,8
	 * 
	 * two maximal cliques are : 1,3,4 & 3,6,7
	 * 
	 * */
	public static Vertex getSampleGraph() {
		Vertex _1  =  new Vertex(1);
		Vertex _2  =  new Vertex(2);
		Vertex _3  =  new Vertex(3);
		Vertex _4  =  new Vertex(4);
		Vertex _5  =  new Vertex(5);
		Vertex _6  =  new Vertex(6);
		Vertex _7  =  new Vertex(7);
		Vertex _8  =  new Vertex(8);
		
		_1.addNeighbourVertices(new Vertex[]{_2,_3,_4});
		
		_2.addNeighbourVertices(new Vertex[]{_1,_5});
		
		_3.addNeighbourVertices(new Vertex[]{_1,_4,_6,_7});
		
		
		_4.addNeighbourVertices(new Vertex[]{_1,_3,_8});
		
		_5.addNeighbourVertex(_2);
		
		_6.addNeighbourVertices(new Vertex[]{_3,_7});
		
		_7.addNeighbourVertices(new Vertex[]{_3,_6});
		
		_8.addNeighbourVertices(new Vertex[]{_4});
		
		return _1;
	}
	
	
	
	
	/***
	 * lables are
	 *   1
	 *  
	 * 2,3,4
	 * 
	 * 5,6,7,8
	 * 
	 * one maximal cliques are :  3,6,7
	 * 
	 * */
	public static Vertex getSampleGraphWithOneMaximalClique() {
		Vertex _1  =  new Vertex(1);
		Vertex _2  =  new Vertex(2);
		Vertex _3  =  new Vertex(3);
		Vertex _4  =  new Vertex(4);
		Vertex _5  =  new Vertex(5);
		Vertex _6  =  new Vertex(6);
		Vertex _7  =  new Vertex(7);
		Vertex _8  =  new Vertex(8);
		
		_1.addNeighbourVertices(new Vertex[]{_2,_3,_4});
		
		_2.addNeighbourVertices(new Vertex[]{_1,_5});
		
		_3.addNeighbourVertices(new Vertex[]{_1,_6,_7});
		
		
		_4.addNeighbourVertices(new Vertex[]{_1,_8});
		
		_5.addNeighbourVertex(_2);
		
		_6.addNeighbourVertices(new Vertex[]{_3,_7});
		
		_7.addNeighbourVertices(new Vertex[]{_3,_6});
		
		_8.addNeighbourVertices(new Vertex[]{_4});
		
		return _1;
	}
	
	
}
