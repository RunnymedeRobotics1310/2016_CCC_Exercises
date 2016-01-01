package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This is the template used to solve all of the advent of code puzzles.
// Code specific to the challenge is listed below the line of ********
public class Dec01 {

	public static void main(String[] args) {
		(new Dec01()).run();
	}

	private void run() {

		readInput();

		part1();
		part2();
	}


	// Always read all of the input into an array called inStrings
	// Stop reading when a blank (empty) line is encountered.
	List<String> inStrings = new ArrayList<String>(1000);

	private void readInput() {

		Scanner in = new Scanner(System.in);

		while (true) {

			String s = in.nextLine();
			if (s.trim().length() == 0) { break; }  // Stop reading after a blank line

			inStrings.add(s.trim());
		}
		
		in.close();

	}

	//*************************************************************************
	
	private void part1() {

		System.out.println(getFloor(inStrings.get(0)));
		
	}

	private void part2() {

		System.out.println(getBasementInstruction(inStrings.get(0)));

	}

	private long getFloor(String in) {
		
		char [] instructions = in.toCharArray();
		
		long floor = 0;
		
		for (char c: instructions) {
			
			if (c == '(') { 
				floor++;
			} else {
				floor--;
			}
			
		}
		
		return floor;
	}

	private long getBasementInstruction(String in) {
		
		char [] instructions = in.toCharArray();
		
		long floor = 0;
		long instructionCount = 0;
		
		for (char c: instructions) {
			instructionCount++;
			
			if (c == '(') { 
				floor++;
			} else {
				floor--;
			}
			
			if (floor < 0) { break; }
		}
		
		return instructionCount;
	}

}
