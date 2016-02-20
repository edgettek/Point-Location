/*
 * Test
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	
	public static void main(String [] args) throws FileNotFoundException {
	
		//Scanning from file
		File file = new File(args[0]);
		Scanner scan = new Scanner(file);
		
		BinarySearchTree test = new BinarySearchTree();
		
		int n = scan.nextInt();
		
		double x1, y1, x2, y2;
		
		//reads the number of lines to be inserted and iterates that many times
		for(int i = 0; i < n; i++) {
			x1 = scan.nextDouble();
			y1 = scan.nextDouble();
			x2 = scan.nextDouble();
			y2 = scan.nextDouble();
			
			//checks for points outside the unit square
			if(x1<0 || x1>1 || y1<0 || y1>1 || x2<0 || x2>1 || y2<0 || y2>1) {
				System.out.println("Line #" + i + " contained a point that was located outside the unit square."
						+ " Therefore, the line was not inserted.\n");
			}
			else {
				//checks for lines that do not span the unit square
				if(((x1>0 && x1<1) && (y1>0 && y1<1)) || ((x2>0 && x2<1) && (y2>0 && y2<1))) {
					System.out.println("Line #" + i + " contained a point that was not located on the edge of the unit square."
							+ " Therefore, the line was not inserted.\n");
				}
				else {
					//inserts points
					Point p1 = new Point(x1, y1);
					Point p2 = new Point(x2, y2);
					
					test.insert(p1, p2);
				}
			}
		}
		
		//appends "blank" nodes at the end of every existing node to represent the regions in the unit square
		test.finalizeTree();
		
		//calculates external path length
		double externalPathLength = Node.externalPathLength();
		
		//counts the number of regions (i.e. external nodes)
		double regions = test.countRegions();
		
		//calculates average path length
		double averagePathLength = externalPathLength / regions;
		
		System.out.println("The current binary tree has " + regions + " external nodes (i.e. regions).");
		System.out.println("The tree's external path length is: " + externalPathLength);
		System.out.println("The average path length of the tree is: " + averagePathLength);
		
		int count = 0;
		
		//continues testing pairs of points until there are no more to test
		while(scan.hasNextLine()) {
			count++;
			x1 = scan.nextDouble();
			y1 = scan.nextDouble();
			x2 = scan.nextDouble();
			y2 = scan.nextDouble();
			
			//checks to see if the test point is outside the unit square
			if(x1<0 || x1>1 || y1<0 || y1>1 || x2<0 || x2>1 || y2<0 || y2>1) {
				System.out.println("\n\nTest pair #" + count + " was located outside the unit square."
						+ " Therefore, the points were not tested.");
			}
			else {
			
				Point p1 = new Point(x1, y1);
				Point p2 = new Point(x2, y2);
				
				Node p1Location = test.lookup(p1);
				Node p2Location = test.lookup(p2);
				
				System.out.println("\n\nAre " + p1 + " and " + p2 + " in the same region? ");
				
				if(p1Location.equals(p2Location)) {
					System.out.print("Yes.");
				}
				else {
					//finds the line separating the test points
					Node separation = test.findLine(p1, p2);
					System.out.print("No. The two points are separated by at least this line segment: " + separation.p1 + " " + separation.p2);
				}
			}	
		}
		
		scan.close();
		
	}

}
