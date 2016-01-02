package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec06 {

	public static void main(String[] args) {
		(new Dec06()).run();
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
	private class Range {
		int x1, x2, y1, y2;
	}

	private enum Operation { ON, OFF, TOGGLE }
	
	class Instruction {
		Operation operation;
		Range range;
	}
	
	List<Instruction> instructions = new ArrayList<Instruction>();
	
	int [][] lights = new int [1000] [1000];
	
	private void setUpCommonObjects() {

		for (String in: inStrings) {

			String [] s = in.split(" ");
			
			Instruction instruction = new Instruction();
			
			int pos = 0;
			
			if (s[0].equals("toggle")) {
				instruction.operation = Operation.TOGGLE;
				pos = 1;
			} else if (s[1].equals("off")) {
				instruction.operation = Operation.OFF;
				pos = 2;
			} else {
				instruction.operation = Operation.ON;
				pos = 2;
			}

			Range r = new Range();
			
			// First Pair
			String pair = s[pos];
			String [] xy = pair.split(",");
			r.x1 = Integer.valueOf(xy[0]);
			r.y1 = Integer.valueOf(xy[1]);

			// Second Pair
			pair = s[pos+2];
			xy = pair.split(",");
			r.x2 = Integer.valueOf(xy[0]);
			r.y2 = Integer.valueOf(xy[1]);

			instruction.range = r;
			
			instructions.add(instruction);
		}
		
	}
	
	private void part1() {

		System.out.println(getLitCount());
		
	}

	private void part2() {

		System.out.println(getLitCount2());

	}

	private int getLitCount() {
	
		turnOffAllLights();
		
		for (Instruction instruction: instructions) {
		
			switch(instruction.operation) {
			case TOGGLE: toggle(instruction.range); break;
			case ON:     on    (instruction.range); break;
			case OFF:    off   (instruction.range); break;
			}
		}
		
		return getLit();
	}
	
	private int getLitCount2() {
		
		turnOffAllLights();
		
		for (Instruction instruction: instructions) {
		
			switch(instruction.operation) {
			case TOGGLE: toggle2(instruction.range); break;
			case ON:     on2    (instruction.range); break;
			case OFF:    off2   (instruction.range); break;
			}
		}
		
		return getLit();
	}
	
	private void turnOffAllLights() {
		for (int i=0; i<1000; i++) {
			for (int j=0; j<1000; j++) {
				lights[i][j] = 0;
			}
		}
	}
	
	private void toggle(Range r) {
		for (int i=r.x1; i<=r.x2; i++) {
			for (int j=r.y1; j<=r.y2; j++) {
				if (lights[i][j] == 0) {
					lights[i][j] = 1;
				} else {
					lights[i][j] = 0;
				}
			}
		}
	}
	
	private void on(Range r) {
		for (int i=r.x1; i<=r.x2; i++) {
			for (int j=r.y1; j<=r.y2; j++) {
				lights[i][j] = 1;
			}
		}
	}
	
	private void off(Range r) {
		for (int i=r.x1; i<=r.x2; i++) {
			for (int j=r.y1; j<=r.y2; j++) {
				lights[i][j] = 0;
			}
		}
	}
	
	private void toggle2(Range r) {
		for (int i=r.x1; i<=r.x2; i++) {
			for (int j=r.y1; j<=r.y2; j++) {
				lights[i][j] += 2;
			}
		}
	}
	
	private void on2(Range r) {
		for (int i=r.x1; i<=r.x2; i++) {
			for (int j=r.y1; j<=r.y2; j++) {
				lights[i][j]++;
			}
		}
	}
	
	private void off2(Range r) {
		for (int i=r.x1; i<=r.x2; i++) {
			for (int j=r.y1; j<=r.y2; j++) {
				lights[i][j] --;
				if (lights[i][j] < 0) {
					lights[i][j] = 0;
				}
			}
		}
	}
	
	private int getLit() {

		int lit = 0;
		
		for (int i=0; i<1000; i++) {
			for (int j=0; j<1000; j++) {
				lit += lights[i][j];
			}
		}
		
		return lit;
	}
	
}
