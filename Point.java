/*
 * Point
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

public class Point {
	
	double x;
	double y;
	
	//constructor
	public Point(double x1, double y1) {
		x = x1;
		y = y1;
	}
	
	//toString method
	public String toString() {
		return "(" + x + ", " + y + ")";
	}

}
