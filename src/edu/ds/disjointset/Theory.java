package edu.ds.disjointset;

public class Theory {

	/**
	 * link : https://cp-algorithms.com/data_structures/disjoint_set_union.html
	 * 
	 * https://en.wikipedia.org/wiki/Disjoint-set_data_structure
	 * 
	 * 
	 * 
	 * <pre>
	 *Disjoint sets ADT have many applications and a few of them are:
			• To represent network connectivity
			• Image processing
			• To find least common ancestor
			• To define equivalence of finite state automata
			• Kruskal’s minimum spanning tree algorithm (graph theory)
			• In game algorithms
	 * </pre>
	 * 
	 **/

	/**
	 * <pre>
	 * Tradeoffs in Implementing Disjoint Sets ADT :
	 * 
	 * Fast FIND implementation (also called Quick FIND) 
	 * 
	 * Fast UNION operation implementation (also called Quick UNION)
	 *  		1.  Fast UNION implementations (Slow FIND)
				2.  Fast UNION implementations (Quick FIND)
				3.  Fast UNION implementations with path compression
	 * 
	 * </pre>
	 * 
	 */

	/**
	 * Time-Complexity:
	 * 
	 * While there are several ways of implementing disjoint-set data structures, in
	 * practice they are often identified with a particular implementation called a
	 * disjoint-set forest. This is a specialized type of forest which performs
	 * unions and finds in near constant amortized time. To perform a sequence of m
	 * addition, union, or find operations on a disjoint-set forest with n nodes
	 * requires total time O(mα(n)), where α(n) is the extremely slow-growing
	 * inverse Ackermann function. Disjoint-set forests do not guarantee this
	 * performance on a per-operation basis. Individual union and find operations
	 * can take longer than a constant times α(n) time, but each operation causes
	 * the disjoint-set forest to adjust itself so that successive operations are
	 * faster. Disjoint-set forests are both asymptotically optimal and practically
	 * efficient.
	 * 
	 * 
	 */

}
