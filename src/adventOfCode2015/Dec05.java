package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec05 {

	public static void main(String[] args) {
		(new Dec05()).run();
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

		System.out.println(getNiceCount());
		
	}

	private void part2() {

		System.out.println(getNiceCount2());

	}
	


	private int getNiceCount() {
		
		int niceCount = 0;
		for (String in: inStrings) {
			
			if (isNice(in)) { niceCount++; }
			
		}

		return niceCount;
	}
	
	private int getNiceCount2() {
		
		int niceCount = 0;
		for (String in: inStrings) {
			
			if (isNice2(in)) { niceCount++; }
			
		}

		return niceCount;
	}
	
	public static boolean isNice(String s) {
		
		//ab, cd, pq, or xy are not nice
		if (s.contains("ab")) { return false; }
		if (s.contains("cd")) { return false; }
		if (s.contains("pq")) { return false; }
		if (s.contains("xy")) { return false; }

		int vowels = 0;
		boolean doubles = false;
		
		char [] chars = s.toCharArray();
		
		for (int i=0; i<chars.length; i++) {
			
			char c = chars[i];
			
			if (!doubles && i < chars.length-1) {
				if (c == chars[i+1]) {
					doubles = true;
				}
			}
			
			if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				vowels++;
			}
		}
		
		if (vowels >=3 && doubles) { return true; }
		
		return false;
	}

	public static boolean isNice2(String s) {
		
		boolean twoLetterRepeats = false;
		boolean oneLetterRepeats = false;
		
		char [] chars = s.toCharArray();
		
		for (int i=0; i<chars.length; i++) {
			
			if (!twoLetterRepeats && i < chars.length-3) {
				// Get the string representation of the next characters
				// starting at position i
				String a = String.valueOf(chars, i, 2);
				// Look for a copy of the two characters after these two
				// characters.
				if (s.indexOf(a, i+2) > 0) {
					twoLetterRepeats = true;
				}
			}

			// Look for a repeat of a character with exactly one character
			// between.
			if (!oneLetterRepeats && i < chars.length-2) {
				if (chars[i] == chars[i+2]) {
					oneLetterRepeats = true;
				}
			}
		}
		
		if (twoLetterRepeats && oneLetterRepeats) { return true; }
		
		return false;
	}
}
