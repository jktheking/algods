package edu.ds.graph.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import edu.ds.graph.Vertex;

/**
 * Running time complexity is linear: O(V+E)
 * 
 * Memory complexity is not good as we have to store lots of references. So DFS
 * is generally preferred over BFS, which has better Memory complexity.
 * 
 * <br>
 * Applications:
 * 1. Constructs a shortest path: Dijkstra algorithm does a BFS if all the edge weights are equal to 1. 
 * <br>
 * 2. Web-crawler
 * 
 **/
public class BFS {

	public static void main(String[] args) {

		traverseGraph(Vertex.getSampleGraph());

	}

	public static void traverseGraph(Vertex root) {

		Queue<Vertex> queue = new LinkedList<>();
		// before enqueue operation, mark the node as visited
		root.setVisited(true);
		queue.add(root);

		while (!queue.isEmpty()) {

			Vertex currentVertex = queue.remove();
			// print the vertex at each dequeue operation
			System.out.println(currentVertex);

			List<Vertex> neighbourVertices = currentVertex.getNeighbourVertices();
			/*
			 * Before enqueuing to the queue, check if the vertex is already visited, if so
			 * no need to enqueue it again. Thus, we are only enqueuing the vertex once to
			 * avoid the dequeue of the same node more than once as we are printing the node
			 * at each dequeue operation.
			 */

			neighbourVertices.stream().filter(Vertex::isNotVisited).forEach(v -> {
				// before each enqueue operation, mark the node as visited to avoid enqueuing of
				// duplicate node because we are printing the node at every dequeue operation
				v.setVisited(true);
				queue.add(v);
			});

		}

	}

}
