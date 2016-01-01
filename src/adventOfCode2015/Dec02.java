package adventOfCode2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// This is the template used to solve all of the advent of code puzzles.
// Code specific to the challenge is listed below the line of ********
public class Dec02 {

	public static void main(String[] args) {
		(new Dec02()).run();
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

		System.out.println(getWrappingFeet());

	}

	private void part2() {

		System.out.println(getRibbonTotal());

	}

	private long getRibbonTotal() {

		long ribbonTotal = 0;
		
		for (String in: inStrings) {
			
			// Split each row into dimensions and put the 
			// dimensions in a list so that you can sort them
			ArrayList<Integer> dims = new ArrayList<Integer>();
			
			String [] sides = in.split("x");
			
			for (String side: sides) {
				dims.add(Integer.valueOf(side));
			}

			// Sort the dimensions from smallest to largest
			Collections.sort(dims);
	
			long ribbon = 0;
	
			ribbon = 2 * (dims.get(0) + dims.get(1));
			ribbon += dims.get(0) * dims.get(1) * dims.get(2);

			ribbonTotal += ribbon;
		}

		return ribbonTotal;
	}

	private long getWrappingFeet() {

		long totalWrapping = 0;
		
		for (String in: inStrings) {
			
			// Split each row into dimensions and put the 
			// dimensions in a list so that you can sort them
			ArrayList<Integer> dims = new ArrayList<Integer>();
			
			String [] sides = in.split("x");
			
			for (String side: sides) {
				dims.add(Integer.valueOf(side));
			}

			// Sort the dimensions from smallest to largest
			Collections.sort(dims);
	
			long wrapping = 0;
	
			wrapping  = 3 * dims.get(0) * dims.get(1); // 3 * adds one small side extra
			wrapping += 2 * dims.get(0) * dims.get(2);
			wrapping += 2 * dims.get(1) * dims.get(2);
	
			totalWrapping += wrapping;
		}
		
		return totalWrapping;
	}
	

}
