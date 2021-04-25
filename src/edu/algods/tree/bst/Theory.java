package edu.algods.tree.bst;

/**
 * Tree is a variant of graph where there exists only one way from root to any
 * other node.
 * 
 * If there are several ways to get to a given node: it's not a tree rather a
 * typical graph.
 * 
 * Time complexity of operations on tree are quite predictable in most case
 * O(logn).
 * 
 * Height of the tree, h: the length of the path from the root to the deepest
 * node in the tree.
 * 
 * We should keep the height of the tree at a minimum which is h=logn
 * 
 * 
 * BINARY SEARCH TREE:<br>
 * Smallest Node: go to the left as far as possible.<br>
 * Largest Node: go to the right as far as possible.<br>
 * 
 * 
 * DELETE IN BINARY SEARCH TREE: 3 possible cases:<br>
 * --------------------------------------------------<br>
 * CASE1: The node we want to delete is a leaf node<br>
 * CASE2: The node we want to delete has a single child<br>
 * CASE3: The node we want to delete has 2 children
 * 
 * CASE3: The node we want to delete has 2 children<br>
 * Here we need to swap the node to be deleted with PREDECESSOR or
 * SUCCESSOR.<br>
 * This swap will convert the CASE3 into either CASE1 or CASE2.<br>
 * 
 * PREDECESSOR : The largest node in left sub-tree is called as PREDECESSOR of
 * the given node.
 * 
 * SUCCESSOR : The smallest node in right sub-tree is called as SUCCESSOR of the
 * given node.
 * 
 * 
 * Traversal in BST<br>
 * -----------------<br>
 * 
 * IN-ORDER TRAVERSAL : recursively traverse LEFT_SUBTREE + ROOT_NODE +
 * RIGHT_SUBTREE <br>
 * -In-order traversal provides the sorted list.
 * 
 * PRE-ORDER TRAVERSAL : recursively traverse ROOT_NODE + LEFT_SUBTREE +
 * RIGHT_SUBTREE
 * 
 * POST-ORDER TRAVERSAL : recursively traverse LEFT_SUBTREE + RIGHT_SUBTREE +
 * ROOT_NODE
 * 
 * 
 * 
 * BST time and space complexity:<br>
 * ---------------------------------<br>
 * 
 * space complexity : average & worst case : O(n)
 * 
 * Time complexity:<br>
 * Average case for : INSERT, DELETE, SEARCH : O(logn) <br>
 * Worst case for : INSERT, DELETE, SEARCH : O(logn) <br>
 * 
 * If the tree becomes unbalanced, the operations running times can be reduces
 * to O(n) in the worst case. That's why it is important to keep a tree as
 * balanced as possible.
 * 
 * 
 */
public class Theory {

}
