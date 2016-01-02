package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec10 {

	public static void main(String[] args) {
		(new Dec10()).run();
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
	
	String seeAndSay;

	private void setUpCommonObjects() {
	}

	private void part1() {
		
		seeAndSay = inStrings.get(0);
		
		for (int i=0; i<40; i++) {
			seeAndSay = seeAndSay(seeAndSay);
			//System.out.println(seeAndSay); // print the strings
		}
		
		System.out.println(seeAndSay.length());
		
	}

	private void part2() {
		
		for (int i=0; i<10; i++) {
			seeAndSay = seeAndSay(seeAndSay);
		}
		
		System.out.println(seeAndSay.length());
		
	}


	private String seeAndSay(String s) {

		char [] chars = s.toCharArray();
		
		// Use a stringBuilder to do much, much faster building of strings.
		// The string cannot get more than twice as long as the original string.
		StringBuilder sb = new StringBuilder(s.length()*2);
		
		char curChar = chars[0];
		int curCount = 0;
		
		for (int i=0; i<chars.length; i++) {
			if (chars[i] == curChar) {
				curCount++;
				continue;
			} else {
				sb.append(curCount);
				sb.append(curChar);
				curChar = chars[i];
				curCount = 1;
			}
		}
		
		sb.append(curCount);
		sb.append(curChar);
		
		return sb.toString();
	}
}
