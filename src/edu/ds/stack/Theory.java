package edu.ds.stack;

public class Theory {

	/**
	 * APPLICATIONS OF STACK
	 * 
	 * 1. In stack-oriented programming languages 2. Graph algorithms: depth-first
	 * search can be implemented with stacks(or with recursion) 3. Finding
	 * Euler-Cycles in a graph 4.Finding strongly connected components in a graph
	 * 
	 * 
	 * Balancing of symbols Infix-to-postfix conversion Evaluation of postfix
	 * expression Implementing function calls (including recursion) Finding of spans
	 * (finding spans in stock markets, refer to Problems section) Page-visited
	 * history in a Web browser [Back Buttons] Undo sequence in a text editor
	 * Matching Tags in HTML and XML
	 * 
	 */

	/**
	 * Dijkstra's SHUNTING-YARD algorithm or DIJKSTRA'S INTERPRETER
	 * 
	 * A method for parsing mathematical expressions
	 * 
	 * The shunting-yard algorithm is stacked-based : one stack for the numbers and
	 * second stack is for the operators.
	 * 
	 * The shunting-yard algorithm has been later generalized into
	 * operator-precedence parsing.
	 * 
	 * Intuition -> this is something like an interpreter, it can read source code
	 * line by line and process it accordingly.
	 * 
	 * 
	 */

	/**
	 * CLASSESS OF PROBLEMS:
	 * 
	 * CLASS A:
	 * 
	 * 1. Nearest Greater in Left
	 * 
	 * 2. Nearest Greater in Right | Next Largest Element
	 * 
	 * 3. Nearest Smaller in Left |  Nearest Smaller Element
	 * 
	 * 4. Nearest Smaller in Right
	 * 
	 * Class A- Application
	 * 
	 * 5. Stock Span Problem
	 * 
	 * 6. Maximum Area of Histogram
	 * 
	 * 7. Max Area of Rectangle in Binary Matrix
	 * 
	 * CLASS MISC:
	 * 
	 * 8. Rain water Trapping
	 * 
	 * 9. Implementing a MIN Stack(with and without extra-space)
	 * 
	 * 10. Implementing stack using heap
	 * 
	 * 11. The celebrity problem
	 * 
	 * 12. Longest valid Parenthesis
	 * 
	 * 13. Iterative Tower of Hanoi
	 * 
	 * 
	 **/

	/**
	 * <pre>
	 * 
	 * Rule of thumb to see if we can solve the problem with stack or not:
	 * 
	 * 
	 * if we have o(n^2) for loop for brute-force, means nested for loop where inner
	 * for-loop is linearly dependent on index of outer for loop like below, in such cases
	 * we may write a improvised solution using stack. 
	 * These kind of for loop simulates the back-tracing, that's why stack can be used.
	 *
	 *
	 * <0----------------i-----------------n>
	 *
	 * 0-->--j-->--i ; i->--j-->-n
	 * 
	 * 0--<--j--<--i ; i-<--j--<-n
	 * 
	 * 
	 *
	 * 
	 *  for(i=0; i<n; i++)
	 *    for(j=0; j<i; j++);
	 *  
	 *  
	 *  for(i=0; i<n; i++)
	 *    for(j=i; j<n; j++);
	 *  
	 *  
	 *  for(i=0; i<n; i++)
	 *    for(j=i; j=>0; j--);
	 *  
	 *  
	 *  for(i=0; i<n; i++)
	 *    for(j=n; j=>i; j--);
	 * 
	 * 
	 * 
	 * 
	 * </pre>
	 * 
	 */
	void RuleOfThumb() {

	}
}
