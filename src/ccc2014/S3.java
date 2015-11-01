package ccc2014;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

/*
Problem S3: The Geneva Confection

Problem Description
In order to ensure peace and prosperity for future generations, the United Nations is creating the
world’s largest candy. The ingredients must be taken in railway cars from the top of a mountain
and poured into Lake Geneva. The railway system goes steeply from the mountaintop down to the
lake, with a T-shaped branch in the middle as shown below.
Right now, each of the N ingredients is in its own railway car. Each railway car is assigned a positive
integer from 1 to N. The ingredients must be poured into the lake in the order 1, 2, 3, . . . , N
but the railway cars are lined up in some random order. The difficulty is that, because of the especially
heavy gravity today, you can only move cars downhill to the lake, or sideways on the branch
line. Is it still possible to pour the ingredients into the lake in the order 1, 2, 3, . . . , N ?

For example, if the cars were in the order 2, 3, 1, 4, we can slide these into the lake in order as
described below:
• Slide car 4 out to the branch
• Slide car 1 into the lake
• Slide car 3 out to the branch
• Slide car 2 into the lake
• Slide car 3 from the branch into the lake
• Slide car 4 from the branch into the lake

Input Specification
The first line will contain the number T (1 <= T <= 10) which is the number of different tests that
will be run. Each test has the form of an integer N (1 <= N <= 100 000) on the first line of the test,
followed by a list of the N cars listed from top to bottom. The cars will always use the numbers
from 1 to N in some order.

Output Specification
For each test, output one line which will contain either Y (for “yum”) if the recipe can be completed,
and N otherwise.
 */
/**
 * Problem S3: The Geneva Confection
 * 
 * http://www.cemc.uwaterloo.ca/contests/computing/2014/stage%201/seniorEn.pdf
 */
public class S3 {

	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		S3 puzzle = new S3();
		puzzle.run();
	}

	// *************************************************

	// Solve the problem using decks
	// ArrayDeque is used with Deques(decks) similarly to ArrayLists and Lists
	private Deque<Integer> mountain;
	private Deque<Integer> branch;

	// Use an output list to buffer the output of each problem until the end.
	private List<String> outputList;
	
	public void run() {

		int t = new Integer(input.nextLine());
		
		outputList = new ArrayList<String>(t);

		// For each of the puzzles, read the input and solve.
		// Add the outputs to an output list and print them all solutions at the end.
		for (int i = 0; i < t; i++) {
			readSubPuzzleInput();
			String solution = solveSubPuzzle();
			outputList.add(solution);
		}
		
		for (String solution: outputList) {
			System.out.println(solution);
		}
	}

	private void readSubPuzzleInput() {

		int n = new Integer(input.nextLine());

		mountain = new ArrayDeque<Integer>(n);
		branch   = new ArrayDeque<Integer>(n);

		for (int i = 0; i < n; i++) {
			mountain.push(new Integer(input.nextLine()));
		}
	}

	private String solveSubPuzzle() {

		// Capture the total number of ingredients before starting to pull cars off the mountain
		int totalIngredients = mountain.size();
		
		for (int ingredient = 1; ingredient <= totalIngredients; ingredient++) {

			// Look to see if the ingredient is on the branch
			if (!branch.isEmpty()) {
				
				// peek looks at the first ingredient without pulling 
				// it off the branch.
				if (branch.peek() == ingredient) {
					branch.pop();
					continue;
				}
			}

			// See if it is on the mountain
			boolean ingredientFound = false;
			while (!mountain.isEmpty()) {
				int i = mountain.pop();
				if (i == ingredient) {
					ingredientFound = true;
					break;
				}
				// If it is not the right ingredient then push it onto the branch.
				branch.push(i);
			}
			
			if (ingredientFound) {
				continue;
			}

			// If the ingredient is not found as the first element of the branch,
			// nor on the mountain, then the recipe cannot be completed. 
			return "N";
		}
		
		// If all of the ingredients were dumped in the lake, then 
		// the recipe was completed.
		return "Y";
	}
}
