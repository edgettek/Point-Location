/*
 * Node
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

import java.util.ArrayList;

public class Node {
	
	//instance variables
	Point p1;
	Point p2;
	Node leftChild;
	Node rightChild;
	Node parent;
	
	static ArrayList<Node> externalNodes = new ArrayList<Node>();
	
	
	public void insert(Point point1, Point point2) {
		
		//if the line is completely on one side of the Node-line
		if(ccw(point1, p1, p2).equals(ccw(point2, p1, p2))) {
			//if the points are all to the "left" of the insertion line
			if(ccw(point1, p1, p2).equals("COUNTERCLOCKWISE")) {
				if(leftChild == null) {
					Node newNode = new Node();
					newNode.p1 = point1;
					newNode.p2 = point2;
					leftChild = newNode;
					newNode.parent = this;
				}
				else{
					leftChild.insert(point1, point2);
				}
			}
			else {
				//if the points are all to the "right" of the insertion line
				if(rightChild == null) {
					Node newNode = new Node();
					newNode.p1 = point1;
					newNode.p2 = point2;
					rightChild = newNode;
					newNode.parent = this;
				}
				else{
					rightChild.insert(point1, point2);
				}
			}
		}
		else {
			//if the points are on different sides of the insertion line
			if(!ccw(point1, p1, p2).equals(ccw(point2, p1, p2))) {
				//find the point of insertion
				Point intersection = intersection(point1, point2, p1, p2);
				
				//if point1 is on the left of the insertion line
				if(ccw(point1, p1, p2).equals("COUNTERCLOCKWISE")) {
					
					if(leftChild != null) {
						leftChild.insert(point1, intersection);
					}
					else {
							
						Node newNode = new Node();
						newNode.p1 = point1;
						newNode.p2 = intersection;
						leftChild = newNode;
						newNode.parent = this;
					}
					
					if(rightChild != null) {
						rightChild.insert(intersection, point2);
					}
					else {
							
						Node newNode = new Node();
						newNode.p1 = intersection;
						newNode.p2 = point2;
						rightChild = newNode;
						newNode.parent = this;
					}
				}
				else {
					//if point1 is on the right of insertion line
					if(rightChild != null) {
						rightChild.insert(intersection, point1);
					}
					else {
							
						Node newNode = new Node();
						newNode.p1 = intersection;
						newNode.p2 = point1;
						rightChild = newNode;
						newNode.parent = this;
					}
					
					
					if(leftChild != null) {
						leftChild.insert(point2, intersection);
					}
					else {
						
						Node newNode = new Node();
						newNode.p1 = point2;
						newNode.p2 = intersection;
						leftChild = newNode;
						newNode.parent = this;
					}
					
				}
			}
		}
	}
	
	//method that determines the point of intersection of two lines
	public static Point intersection(Point Line1P1, Point Line1P2, Point Line2P1, Point Line2P2) {
		
		//coordinates of Line1
		double x1 = Line1P1.x;
		double y1 = Line1P1.y;
		double x2 = Line1P2.x;
		double y2 = Line1P2.y;
		
		//coordinates of Line2
		double x3 = Line2P1.x;
		double y3 = Line2P1.y;
		double x4 = Line2P2.x;
		double y4 = Line2P2.y;
		
		double slope1 = (y2-y1)/(x2-x1);
		double yIntercept1 = y1-(slope1*x1);
		
		double slope2 = (y4-y3)/(x4-x3);
		double yIntercept2 = y3-(slope2*x3);
		
		if(slope1 == slope2) {
			return null;
		}
		else {
			
			double xIntersect = (yIntercept2 - yIntercept1)/(slope1-slope2);
			
			double yIntersect = (slope1 * xIntersect) + yIntercept1;
			
			return new Point(xIntersect, yIntersect);
		}	
	}
	
	//method that finds the line separating two test points that are not in the same region
	public Node findLine(Point p1, Point p2) {
		
		Node temp = this;
		
		// *** POINT 1 TRAVERSAL ***
		
		//array list to store the traversal path from the root to the external node for POINT1
		ArrayList<Node> point1Traversal = new ArrayList<Node>();
		
		while(temp.p1 != null) {
			
			if(ccw(p1, temp.p1, temp.p2).equals("COUNTERCLOCKWISE")) {
				point1Traversal.add(temp);
				temp = temp.leftChild;

			}
			else {
				point1Traversal.add(temp);
				temp = temp.rightChild;
			}
		}
		
		Node temp2 = this;
		
		// *** POINT 2 TRAVERSAL ***
		
		//array list to store traversal path from root to external node for POINT 2
		ArrayList<Node> point2Traversal = new ArrayList<Node>();
		
		while(temp2.p2 != null) {
			
			if(ccw(p2, temp2.p1, temp2.p2).equals("COUNTERCLOCKWISE")) {
				point2Traversal.add(temp2);
				temp2 = temp2.leftChild;

			}
			else {
				point2Traversal.add(temp2);
				temp2 = temp2.rightChild;
			}
		}
		
		Node temp3 = null;
		
		//finds the 1st node at which the traversals differ
		for(int i = 0; i < Math.min((int) point1Traversal.size(),(int) point2Traversal.size()); i++) {
			
			if(!point1Traversal.get(i).equals(point2Traversal.get(i))) {
				temp3 = point2Traversal.get(i);
			}
		}
		
		//if the traversals don't differ until the parent node of the two regions, then get the last node of the traversal
		if(temp3 == null) {
			
			if(point1Traversal.size() < point2Traversal.size()) {
				temp3 = point1Traversal.get(point1Traversal.size()-1);
			}
			else {
				temp3 = point1Traversal.get(point2Traversal.size()-1);
			}
		}
		
		//return the node
		return temp3;
		
	}
	
	//return the external node that represents the region containing the test point
	public Node lookup(Point p0) {
		
		if(p1 == null) {
			return this;
		}
		
		if(ccw(p0, p1, p2).equals("COUNTERCLOCKWISE")) {
			return leftChild.lookup(p0);
		}
		else {
			return rightChild.lookup(p0);
		}
	}
	
	//count the number of "blank" external nodes
	public int countRegions() {
		
		if(leftChild == null && rightChild == null) {
			return 1;
		}
		else {
			return rightChild.countRegions() + leftChild.countRegions();
		}	
	}
	
	//print the tree (used during testing)
	public void printTree() {
		System.out.println(p1 + ", " + p2);
		
		if(leftChild != null) {
			leftChild.printTree();
		}
		
		if(rightChild != null) {
			rightChild.printTree();
		}
	}
	
	//appends "blank" nodes onto the external nodes of the final search tree 
	//these blank nodes represent the regions of the unit square
	public void finalizeTree() {
		
		Node temp;
		
		if(p1 == null){
			return;
		}
		
		if(p1 != null && leftChild == null) {
			Node newNode = new Node();
			newNode.leftChild = null;
			newNode.rightChild = null;
			leftChild = newNode;
			newNode.parent = this;
			temp = newNode;
			externalNodes.add(temp);
		}
		else {
			leftChild.finalizeTree();
		}
		
		if(p1 != null && rightChild == null) {
			Node newNode = new Node();
			newNode.leftChild = null;
			newNode.rightChild = null;
			rightChild = newNode;
			newNode.parent = this;
			temp = newNode;
			externalNodes.add(temp);
		}
		else {
			rightChild.finalizeTree();
		}	
	}
	
	//counts the external path length
	public static int externalPathLength() {
		
		int count = 0;
		
		Node temp;
		
		//array list externalNodes contains all the external nodes of the tree
		
		for(Node current : externalNodes) {
			
			temp = current;
			
			while(temp.parent != null) {
				count++;
				temp = temp.parent;
			}
		}
		return count;
	}
	
	//method that calculates if a point is to the "left" or to the "right" of the insertion line
	public static String ccw(Point p0, Point p1, Point p2) {
		double slope;
		
		//if the line is not vertical
		if(p2.x - p1.x != 0) {
			slope = (p2.y - p1.y)/ (p2.x - p1.x);
			double a = slope * -1;
			double b = 1.0;
			double c = slope * p1.x - p1.y;
			double x1 = p0.x;
			double y1 = p0.y;
			double check = a * x1 + b * y1 +c;
			
			if(check > 0) {
				return "COUNTERCLOCKWISE";
			}
			else {
				if(check < 0) {
					return "CLOCKWISE";
				}
				else {
					return "COLINEAR";
				}
			}	
		}
		else {
			//if the line is vertical, set the slope to the greatest possible integer
			slope = Integer.MAX_VALUE;
			double a = slope * -1;
			double b = 1.0;
			double c = slope * p1.x - p1.y;
			double x1 = p0.x;
			double y1 = p0.y;
			double check = a * x1 + b * y1 +c;
			
			if(check > 0) {
				return "COUNTERCLOCKWISE";
			}
			else {
				if(check < 0) {
					return "CLOCKWISE";
				}
				else {
					return "COLINEAR";
				}
			}	
		}
	   
	}
}
