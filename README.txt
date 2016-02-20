README

Contact Information:

Name: Kyle Edgette
Class: CSC 172 SPRING 2015
Lab Session: Monday/Wednesday 2pm
TA Name: TJ Stein
Assignment Number: Project 03


Description:

A JAVA program that utilizes a binary tree data structure to determine relative point location 
in the unit square. The program reads a set of lines (represented by two points) from a file
and builds a binary search tree based on the lines. Then using the tree, the file reads sets of points
and determines if they are in the same region or if there is a line separating them. If they are not
in the same region, the program prints out one of the lines separating the points. The number of
external nodes (i.e. regions in the unit square separated by lines), as well as the average path
length of the tree, is also printed. The binary tree is built by comparing existing lines in the tree
to lines being inserted, by calculating the both endpoints of the "insertion" line are on the same side
or different sides of the established line. If the endpoints are on different sides, an intersection method
determines the intersection of the lines and splits the "insertion" line into segments and then continues inserting
the segments.

Discuss how the number of external nodes and average path length grow:

MyTest1, MyHorizontalLineTest, MyTest3, and MyTest2 insert 4, 5, 6, and 7 lines, respectively. The number of external nodes
are 8, 14, 18, and 22, respectively. The average path lengths are 3.25, 4.0, 4.39, and 5.045, respectively. Because different
lines will intersect in different ways, and therefore create a varying number of regions in the unit square, the best 
conclusion I can give is that as the number of lines inserted increases, so will the number of external nodes and average
path lengths.

The program accurately handles: endpoints of lines not in the unit square, insertion lines that do not completely
span the unit square, test points that are not in the unit square, and horizontal lines.

The program does not accurately handle: vertical lines, more than two lines intersecting at the same point.


Files Handed In:

1. Point.java is used to create Point objects to store the endpoints of lines being inserted
2. Node.java is the building block for the binary search tree. Nodes consist of two Points,
a leftChild Node, a rightChild Node, and a parent Node. The class also contains the insert() method
and several helper methods, in addition to the methods that find the line segment that separates two points.
3. BinarySearchTree.java contains a constructor for building the tree, as well as many methods that call the
methods of the Node class on the tree's root.
4. Test.java contains the main method that is the engine of the program. The main method reads from file
the number of lines being inserted, the endpoints of those lines, and then the points that are being located
relative to each other.
5. MyTest1.txt is my first test case. Four lines are inserted and four sets of points are located.
6. DegenerateTest1.txt contains the same four lines and four search points as MyTest1.txt in addition to 
two special lines and one special set of test points. One line does not completely span the unit square
and another contains points outside the unit square. The set of test points contains a point outside the unit
square.
7. MyTest2.txt contains 7 new lines and 3 test sets of points.
8. MyHorizontalLineTest.txt contains 5 lines, 2 of which are horizontal, and 4 sets of test points.
9. MyVerticalLineTest.txt contains 3 lines, one of which is vertical, and 3 sets of test points. This test demonstrates
that the program does not accurately handle vertical lines. There should be 7 regions in the unit square, but
the program only calculates 6 regions.
10. MultipleIntersectionsTest.txt contains 3 lines that all intersect at the same point and 2 sets of test points.
11. Results of Test Cases contains five .txt files containing the programs output for each test case described above
 
 
Instructions on Running the Program:
 
 In the command line, change directories until you are in the correct directory. Please note, the program
takes the name of the input file as a command line argument. The file must contain, in this order, the number
of lines to be inserted, then the endpoints of the lines to be inserted, and then the sets of test points. Then:

Compile:

javac *.java

Run:

java Test MyTest1.txt


 
 