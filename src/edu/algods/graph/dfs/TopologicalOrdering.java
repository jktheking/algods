package edu.algods.graph.dfs;

/**<pre>
 * Topological ordering exists for the directed graph with no cycle. i.e for DAG (directed  acyclic graph).
 * 
 * Topological ordering of a directed graph is a linear ordering of its vertices such that for every directed edge
 * UV from vertex U to vertex V, U comes before V in the ordering.
 * 
 * Any DAG has at least one topological order.
 * 
 * Topological sort has linear time complexity. 
 * 
 * It is important in project management. 
 * 
 * Hamiltonian Path: it is a path in directed or undirected graph that visits each vertex exactly once.
 * 
 * If a Hamiltonian Path exists the topological sort order is unique, no other order respects the edges of the
 * path. Conversely, if a topological sort order does not form a Hamiltonina path, the DAG will have two or more valid
 * topological ordering.
 * 
 * Finding Hamiltonian Path is NP Complete problem, but we can decide whether such a path exist in linear time.
 * 
 * </pre>
 * 
 * 
 * <pre>
 * Applications:
 * 1. Dependency management in software engineering(Maven, gradle ant)
 * 2. At universities constructing the syllabus.
 * 
 * </pre>
 * */
public class TopologicalOrdering {

}
