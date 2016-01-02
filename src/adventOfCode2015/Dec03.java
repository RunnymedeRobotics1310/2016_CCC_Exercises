package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This is the template used to solve all of the advent of code puzzles.
// Code specific to the challenge is listed below the line of ********
public class Dec03 {

	public static void main(String[] args) {
		(new Dec03()).run();
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

		System.out.println(getHouseCount(inStrings.get(0)));
		
	}

	private void part2() {

		System.out.println(getHouseCount2(inStrings.get(0)));

	}

	private long getHouseCount(String in) {
		
		// Save all of the addresses that are visited, and then
		// just return the length of the list.
		List<Long> addresses = new ArrayList<Long>(100000);
		
		char [] instructions = in.toCharArray();
		
		int x = 0;
		int y = 0;
		
		addresses.add(getAddress(x, y));
		
		for (char c: instructions) {

			switch(c) {
			case '^': y++; break;
			case 'v': y--; break;
			case '>': x++; break;
			case '<': x--; break;
			}
			
			long address = getAddress(x,y);
			
			if (!addresses.contains(address)) {
				addresses.add(address);
			}
		}
		
		return addresses.size();
	}

	private long getHouseCount2(String in) {
		
		// Save all of the addresses that are visited, and then
		// just return the length of the list.
		List<Long> addresses = new ArrayList<Long>(100000
				);
		
		char [] instructions = in.toCharArray();
		
		int x = 0;
		int y = 0;
		
		int robotX = 0;
		int robotY = 0;
		
		addresses.add(getAddress(x, y));
		
		for (int i=0; i<instructions.length; i++) {
			
			char santaInstruction = instructions[i];

			switch(santaInstruction) {
			case '^': y++; break;
			case 'v': y--; break;
			case '>': x++; break;
			case '<': x--; break;
			}
			
			long address = getAddress(x,y);
			
			if (!addresses.contains(address)) {
				addresses.add(address);
			}

			i++;
			if (i>=instructions.length) { break; }
			
			char robotInstruction = instructions[i];

			switch(robotInstruction) {
			case '^': robotY++; break;
			case 'v': robotY--; break;
			case '>': robotX++; break;
			case '<': robotX--; break;
			}
			
			address = getAddress(robotX,robotY);
			
			if (!addresses.contains(address)) {
				addresses.add(address);
			}
		}
		
		return addresses.size();
	}

	private Long getAddress(int x, int y) {
		
		// Create some way of making unique addresses from 
		// an xy coordinate.
		
		// I chose the formula x*1000000+y.
		// This should be unique for our purposes.
		return x*1000000L + y;
	}
}
