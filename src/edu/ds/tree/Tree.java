package edu.ds.tree;

import java.util.List;

public interface Tree<T> {
	
	void insert(T data);

    void delete(T data);

    T getMaxElement();

    T getMinElement();
    
    int size();
    
    int size(T data);

    int height();

    boolean isEmpty();

    List<T> preorderTraverse();

    List<T> inorderTraverse();

    List<T> postorderTraverse();
    
    List<T> bfsTraversal();
    
    boolean contains(T data);
    
}
