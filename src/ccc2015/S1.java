package ccc2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*

Problem S1: Zero That Out

Problem Description
Your boss has asked you to add up a sequence of positive numbers to determine how much money
your company made last year.
Unfortunately, your boss reads out numbers incorrectly from time to time.
Fortunately, your boss realizes when an incorrect number is read and says “zero”, meaning “ignore
the current last number.”
Unfortunately, your boss can make repeated mistakes, and says “zero” for each mistake.
For example, your boss may say “One, three, five, four, zero, zero, seven, zero, zero, six”, which
means the total is 7 as explained in the following chart:

Boss statement(s) Current numbers Explanation
“One, three, five, four” 1, 3, 5, 4 Record the first four numbers.
“zero, zero“ 1, 3 Ignore the last two numbers.
“seven” 1, 3, 7 Record the number 7 at the end of our list.
“zero, zero” 1 Ignore the last two numbers.
“six” 1, 6 We have read all numbers, and the total is 7.

At any point, your boss will have said at least as many positive numbers as “zero” statements. If
all positive numbers have been ignored, the sum is zero.
Write a program that reads the sequence of boss statements and computes the correct sum.

Input Specification
The first line of input contains the integer K (1 <= K <= 100 000) which is the number of integers
(including “zero”) your boss will say. On each of the next K lines, there will either be one integer
between 1 and 100 (inclusive), or the integer 0.

Output Specification
The output is one line, containing the integer which is the correct sum of the integers read, taking
the “zero” statements into consideration. You can assume that the output will be an integer in the
range 0 and 1 000 000 (inclusive).

*/

/**
 * Problem S1: Zero That Out
 * 
 * http://www.cemc.uwaterloo.ca/contests/computing/2015/stage%201/seniorEn.pdf
 */
public class S1 {

	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		S1 s1 = new S1();
		s1.run();
	}
	
	//*************************************************
	
	List<Integer> intLs; 

	public void run() {
		
		int k = new Integer(input.nextLine());
		
		// Create an arrayList of the right size. 
		intLs = new ArrayList<Integer>(k);
		
		for (int i=0; i<k; i++) {
			
			int n = new Integer(input.nextLine());
		
			// Remove the last element in the list if a zero was entered.
			if (n == 0 ) {
				intLs.remove(intLs.size()-1);  
			} else {
				intLs.add(n);
			}

		}
		
		int sum = 0;
		for (Integer n:intLs) {
			sum += n;
		}
		
		System.out.println(sum);
	
	}
}
