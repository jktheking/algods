package edu.algods.graph.dfs;

import java.util.Stack;

import edu.algods.graph.Vertex;

/***
 * Explores as far as possible along each branch before backtracking.
 * 
 * Time complexity as as BFS: O(V+E)
 * 
 * Memory complexity : a bit better than BFS
 * 
 * 
 * Applications: <br>
 * 1. Topological ordering <br>
 * 
 * 2. Kosaraju algorithm for finding strongly connected components in a graph
 * which can be proved to be important for recommendation systems. <br>
 * 
 * 3. Detecting cycles (checking whether a graph is DAG or not) <br>
 * 4. Generating mazes or finding way out of mazes.
 */
public class DFS {

	public static void main(String[] args) {
		traverseGraphWithStack(Vertex.getSampleGraphWithOneMaximalClique());
		System.out.println("with recursion");
		traverseGraphWithRecursion(Vertex.getSampleGraphWithOneMaximalClique());
	}

	public static void traverseGraphWithStack(Vertex root) {

		Stack<Vertex> stack = new Stack<>();

		root.setVisited(true);
		stack.push(root);

		while (!stack.isEmpty()) {

			Vertex currentVertex = stack.pop();
			System.out.println(currentVertex);
			currentVertex.getNeighbourVertices().stream().filter(Vertex::isNotVisited).forEach(v -> {
				v.setVisited(true);
				stack.push(v);
			});

		}

	}

	public static void traverseGraphWithRecursion(Vertex root) {
		//it represents : executing  the statements after popping out of the stack
		root.setVisited(true);
		System.out.println(root);
		//calling the method recursively, means pushing on top of the stack
		root.getNeighbourVertices().stream().filter(Vertex::isNotVisited).forEach(DFS::traverseGraphWithRecursion);

	}

}
