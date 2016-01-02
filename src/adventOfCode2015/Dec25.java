package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec25 {

	public static void main(String[] args) {
		(new Dec25()).run();
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

		setUpCodes();

	}

	private void part1() {

		System.out.println(getCode());
		
	}

	private void part2() {

	}


	//*************************************************************************

	int codeRow;
	int codeCol;

	private void setUpCodes() {

		String [] s = inStrings.get(0).split(" ");
		
		codeRow = Integer.valueOf(s[16].substring(0,s[16].length()-1));
		codeCol = Integer.valueOf(s[18].substring(0,s[18].length()-1));

	}

	private long getCode() {

		int row = 1;
		int col = 1;
		long code = 20151125;
		
		while (true) {
	
			row--;
			col++;
			if (row == 0) {
				row = col;
				col = 1;
			}
			code = (code * 252533) % 33554393;

			if (row == codeRow && col == codeCol) { break; }
		}
		
		return code;
			
	}
}
