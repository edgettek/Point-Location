/*
 * BinarySearchTree
 * 
 * Version 1.0
 * 
 * Copyright Kyle Edgette
 * 
 * Course : CSC 172 SPRING 2015
 * 
 * Assignment : Project 03
 * 
 * Author : Kyle Edgette
 * 
 * Lab Session : Monday/Wednesday 2pm-3:15pm
 * 
 * Lab TA : TJ Stein
 * 
 * Last Revised : April 11, 2015
 * 
 */


public class BinarySearchTree {
	
	Node root;
	
	//constructor for the binary search tree
	public BinarySearchTree() {
		root = new Node();
		root.p1 = null;
		root.p2 = null;
		root.rightChild = null;
		root.leftChild = null;
		root.parent = null;
	}
	
	//calls lookup in Node class on the root of the tree
	public Node lookup(Point p0) {
		return root.lookup(p0);
	}
	
	//insert method
	//if the root is not storing a line, set the root to contain the current line
	//else call the insert method in the Node class on the root
	public void insert(Point point1, Point point2) {
		
		if(root.p1 == null && root.p2 == null) {
			root.p1 = point1;
			root.p2 = point2;
		}
		else {
			root.insert(point1, point2);
		}
	}
	
	//find the line separating the two test points
	public Node findLine(Point p1, Point p2) {
		return root.findLine(p1, p2);
	}
	
	//print the tree
	public void printTree() {
		root.printTree();
	}
	
	//append blank nodes on the leaves of the tree
	public void finalizeTree() {
		root.finalizeTree();
	}
	
	//count the blank nodes on the tree
	public int countRegions() {
		return root.countRegions();
	}

}
