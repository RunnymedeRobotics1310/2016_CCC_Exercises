package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec08 {

	public static void main(String[] args) {
		(new Dec08()).run();
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

		int length1 = 0;
		int length2 = 0;

		for (String s: inStrings) {
			length1 += s.length();
			length2 += getUnquotedLength(s);
		}

		System.out.println(length1-length2);
	}

	private void part2() {

		int length1 = 0;
		int length2 = 0;

		for (String s: inStrings) {
			length1 += s.length();
			length2 += getEncodedLength(s);
		}

		System.out.println(length2-length1);

	}

	private int getUnquotedLength(String s) {

		char [] chars = s.toCharArray();

		int length = 0;

		for (int i=0; i<chars.length; i++) {
			char c = chars[i];

			// If the character is a quote then do not 
			// count it.
			if (c == '"') {
				continue;
			}

			// If the character is \, then count one for 
			// the next character, and either skip one
			// or 3 additional characters depending on whether
			// the \ indicates a single escaped character or 
			// a hex encoded character.
			if (c == '\\') {
				length++;
				if (chars[i+1] == 'x') {
					i += 3;
					continue;
				}
				i += 1;
				continue;
			}
			
			length++;
		}

		return length;
	}

	private int getEncodedLength(String s) {

		char [] chars = s.toCharArray();

		int length = 2;  // start and end quotes for an empty string.

		for (int i=0; i<chars.length; i++) {
			char c = chars[i];

			// add extra characters to escape \ and ".
			if (c == '"' || c == '\\') {
				length+=2;
				continue;
			}

			length++;
		}

		return length;
	}
}
