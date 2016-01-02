package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec11 {

	public static void main(String[] args) {
		(new Dec11()).run();
	}

	private void run() {

		readInput();

		setUpCommonObjects();
		
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
	
	private void setUpCommonObjects() {
	}

	private void part1() {
		
		String s = inStrings.get(0);
		
		char [] chars = s.toCharArray();
		
		chars = nextPassword(chars);
		
		System.out.println(String.valueOf(chars));
		
	}

	private void part2() {
		
		String s = inStrings.get(0);
		
		char [] chars = s.toCharArray();
		
		chars = nextPassword(chars);
		chars = nextPassword(chars);
		
		System.out.println(String.valueOf(chars));
		
	}


	private char [] nextPassword(char [] chars) {
	
		while(true) {
			
			next(chars);
			// System.out.println(String.valueOf(chars));
			if (isPassword(chars)) { return chars; }
			
		}
	}
	
	private String letters = "abcdefghijklmnopqrstuvwxyz";
	
	private char [] next(char [] chars) {

		for (int i=chars.length-1; i>=0; i--) {
			
			int index = letters.indexOf(chars[i]);
			
			index++;
			
			if (index == letters.length()) {
				index = 0;
			}
			
			chars[i] = letters.charAt(index);
			
			if (index != 0) { break; }
		}
		
		return chars;
	}
	
	private boolean isPassword(char [] chars) {
		
		// Must not contain i, o or l.
		for (int i=0; i<chars.length; i++) {
			if (       chars[i] == 'i'
					|| chars[i] == 'o'
					|| chars[i] == 'l') {
				return false;
			}
		}
		
		// Must contain a sequence of 3 increasing letters.
		boolean threeLetters = false;
		
		for (int i=0; i<chars.length-2; i++) {
			
			if (   chars [i+1] == (char) ((int)chars[i]   + 1) 
				&& chars [i+2] == (char) ((int)chars[i+1] + 1) ) {
				threeLetters = true;
				break;
			}
		}
		
		if (!threeLetters) { return false; }
		
		boolean oneDouble  = false;
		boolean twoDoubles = false;
		for (int i=0; i<chars.length-1; i++) {
			
			if (chars [i+1] == chars[i]) {
				if (!oneDouble) {
					oneDouble = true;
					i++;
					continue;
				}
				twoDoubles = true;
				break;
			}
		}
		
		if (!twoDoubles) { return false; }
		
		return true;
	}
	
}
