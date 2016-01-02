package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec12 {

	public static void main(String[] args) {
		(new Dec12()).run();
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
		
		System.out.println(getTotal(s));
		
	}

	private void part2() {
		
		String s = inStrings.get(0);
		
		System.out.println(getTotalNoRed(s));
		
	}


	private int getTotal(String s) {
		
		int total = 0;

		char [] chars = s.toCharArray();
		
		boolean negative = false;
		int value = 0;
		for (int i=0; i<chars.length; i++) {
			
			if (chars[i] == '-') {
				negative = true;
				continue;
			}
			
			if (chars[i] >= '0' && chars[i] <= '9') {
				value = value * 10 + ((int) chars[i] - (int) '0');
				continue;
			}
			
			total += negative ? value * -1 : value;
			
			negative = false;
			value = 0;
		}
		
		return total;
	}

	private int pos = 0;
	
	private int getTotalNoRed(String s) {
		
		char [] chars = s.toCharArray();
		
		// Since the object starts with '[' it is an array
		// of objects, so get the total of the array.
		return getArrayTotal(chars);
		
	}
	
	private int getArrayTotal(char [] chars) {
		
		boolean negative = false;
		int value = 0;
		int arrayValue = 0;
		
		while (true) {
			
			pos++;
			// add all the numbers in the array.
			if (chars[pos] == '{') {
				arrayValue += getObjectTotal(chars);
			}
			
			if (chars[pos] == '[') {
				arrayValue += getArrayTotal(chars);
			}
			
			if (chars[pos] == '-') {
				negative = true;
				continue;
			}
			
			if (chars[pos] >= '0' && chars[pos] <= '9') {
				value = value * 10 + ((int) chars[pos] - (int) '0');
				continue;
			}
			
			arrayValue += negative ? value * -1 : value;
			
			negative = false;
			value = 0;

			if (chars[pos] == ']') {
				pos++;
				break;
			}
			
		}
		
		return arrayValue;

	}
	
	private int getObjectTotal(char [] chars) {
		
		boolean negative = false;
		int value = 0;
		int objectValue = 0;

		boolean red = false;
		
		while (true) {
			
			pos++;
			// add all the numbers in the array.
			if (chars[pos] == '{') {
				objectValue += getObjectTotal(chars);
			}
			
			if (chars[pos] == '[') {
				objectValue += getArrayTotal(chars);
			}
			
			if (chars[pos] == '-') {
				negative = true;
				continue;
			}
			
			if (chars[pos] >= '0' && chars[pos] <= '9') {
				value = value * 10 + ((int) chars[pos] - (int) '0');
				continue;
			}
			
			objectValue += negative ? value * -1 : value;
			
			negative = false;
			value = 0;
			
			if (!red) {
				if (pos < chars.length-2) {
					if (chars[pos] == 'r' && chars[pos+1] == 'e' && chars[pos+2] == 'd') {
						red = true;
					}
				}
			}

			if (chars[pos] == '}') {
				pos++;
				break;
			}
			
		}
		
		if (red) { return 0; }
		
		return objectValue;

	}
}
