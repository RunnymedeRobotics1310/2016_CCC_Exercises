package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dec23 {

	public static void main(String[] args) {
		(new Dec23()).run();
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

		setUpInstructions();

	}

	private void part1() {

		System.out.println(runProgram(0, 0));
		
	}

	private void part2() {

		System.out.println(runProgram(1, 0));
		
	}


	//*************************************************************************

	private enum Operator { 
		HALF ("hlf"),
		TRIPLE ("tpl"),
		INC  ("inc"),
		JUMP ("jmp"),
		JUMP_EVEN("jie"),
		JUMP_ONE("jio");
		
		String s;
		Operator (String s) {
			this.s = s;
		}
		
		static Operator getValue(String s) {
			for (Operator o: Operator.values()) {
				if (o.s.equals(s)) {
					return o;
				}
			}
			return null;
		}
	}
	
	private enum Register {
		A ("a"),
		B ("b");
		
		String s;
		Register (String s) {
			this.s = s;
		}
		
		static Register getValue(String s) {
			if (s.length() == 2) {
				s = s.substring(0,1);
			}
			for (Register r: Register.values()) {
				if (r.s.equals(s)) {
					return r;
				}
			}
			return null;
		}
	}
	
	class Instruction {
		Operator operator;
		Register register;
		int offset;
	}
	
	List<Instruction> instructions = new ArrayList<Instruction>();
	
	
	private void setUpInstructions() {
		
		for (String in: inStrings) {

			String [] s = in.split(" ");
			if (s.length < 2) { break; }
			
			Instruction instruction = new Instruction();
			
			instruction.operator = Operator.getValue(s[0]);
			
			switch (instruction.operator) {
			case INC: 
			case HALF: 
			case TRIPLE: 
				instruction.register = Register.getValue(s[1]);
				break;
			case JUMP:
				instruction.offset = Integer.valueOf(s[1]);
				break;
			case JUMP_EVEN:
			case JUMP_ONE:
				instruction.register = Register.getValue(s[1]);
				instruction.offset = Integer.valueOf(s[2]);
			}
			
			instructions.add(instruction);
		}

	}
	
	private int runProgram(int regA, int regB) {
		
		int instructionPointer = 0;
		
		while(true) {
			
			if (instructionPointer >= instructions.size()) { break; }
			
			Instruction instruction = instructions.get(instructionPointer);
			
			switch (instruction.operator) {
			case INC:
				if (instruction.register == Register.A) {
					regA++;
				} else {
					regB++;
				}
				instructionPointer++;
				break;
			case HALF:
				if (instruction.register == Register.A) {
					regA = regA / 2;
				} else {
					regB = regB / 2;
				}
				instructionPointer++;
				break;
			case TRIPLE:
				if (instruction.register == Register.A) {
					regA = regA * 3;
				} else {
					regB = regB * 3;
				}
				instructionPointer++;
				break;
			case JUMP:
				instructionPointer += instruction.offset;
				break;
			case JUMP_EVEN:
				if (instruction.register == Register.A) {
					if (regA%2 != 0) {
						instructionPointer++;
						break; 
					}
				} else {
					if (regB%2 != 0) {
						instructionPointer++;
						break; 
					}
				}
				instructionPointer += instruction.offset;
				break;
			case JUMP_ONE:
				if (instruction.register == Register.A) {
					if (regA != 1) {
						instructionPointer++;
						break; 
					}
				} else {
					if (regB != 1) {
						instructionPointer++;
						break; 
					}
				}
				instructionPointer += instruction.offset;
				break;
			}
			
			// System.out.println("I:" + instructionPointer + " A:" + regA + " B:" + regB);
		}
		
		return regB;
	}

}
