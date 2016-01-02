package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec07 {

	public static void main(String[] args) {
		(new Dec07()).run();
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
	private enum Operator {AND, OR, NOT, RSHIFT, LSHIFT, NONE };

	class Gate {
		String input1;
		String input2;
		Operator operator;
		int  operatorValue; // Used for LShift and RShift
		String output;
		boolean outputFound = false;
		int outputValue;

		Gate(String input1, String input2, String output, Operator operator) {
			this.input1 = input1;
			this.input2 = input2;
			this.output = output;
			this.operator = operator;
			this.operatorValue = 0;
			this.outputFound = false;
			this.outputValue = 0;
			
			// In the case of LShift and RShift, the second parameter is 
			// not a wire input, it is the number of bits to shift.
			if (operator == Operator.LSHIFT || operator == Operator.RSHIFT) {
				this.operatorValue = Integer.valueOf(input2);
				this.input2 = "";
			}
		}
	}

	List<Gate> gates = new ArrayList<Gate>(1000);

	private void setUpCommonObjects() {

		for (String in: inStrings) {
			
			String [] values = in.split(" ");
	
			// Check if this is just an assignment to an output
			if (values.length == 3) {
				gates.add(new Gate(values[0], "", values[2], Operator.NONE));
				continue;
			}
	
			// Check if this is the NOT operator
			if (values.length == 4) {
				gates.add(new Gate(values[1], "", values[3], Operator.NOT));
				continue;
			}

			// All other gates
			gates.add(new Gate(values[0], values[2], values[4], Operator.valueOf(values[1])));
		}
	}
	
	private void part1() {
		int output = getOutput("a");
		System.out.println(output);
	}

	private void part2() {

		int output = getOutput("a");
		
		// Reset all outputs
		for (Gate g : gates) {
			g.outputValue = 0;
			g.outputFound = false;
		}
		
		// Override the output b with the value a.
		for (Gate g : gates) {
			if (g.output.equals("b")) {
				g.outputValue = output;
				g.outputFound = true;
				break;
			}
		}
		
		// Rerun the simulation
		output = getOutput("a");
		System.out.println(output);

	}
	
	private boolean isNumeric(String s) {
		// from StackOverflow... return s.matches("[-+]?\\d*\\.?\\d*");
		// in this case, our numbers are a lot simpler because
		// there are no decimals or negative signs.
		return s.matches("\\d*"); 
	}


	// This recursive routine finds the output for any gate.
	private int getOutput(String output) {

		// Find the gate with this output
		Gate g = null;
		for (Gate x: gates) {
			if (x.output.equals(output)) {
				g = x;
				break;
			}
		}
		if (g == null) { throw new RuntimeException("Cannot find gate with output(" + output + ")."); }

		// If the output value for this gate is known, we are done.
		if (g.outputFound) { return g.outputValue; }

		// Find the inputs to this gate
		int input1 = 0;
		int input2 = 0;
		int outputValue = 0;

		if (g.input1.length() != 0) { 
			if (isNumeric(g.input1)) {
				input1 = Integer.valueOf(g.input1);
			} else {
				input1 = getOutput(g.input1);
			}
		}
		if (g.input2.length() != 0) { 
			if (isNumeric(g.input2)) {
				input1 = Integer.valueOf(g.input2);
			} else {
				input2 = getOutput(g.input2);
			}
		}

		// In the specifications all of the input values are 16 bit.  In Java, the 
		// default integer size is 32 bits, so we need to clear the upper 16 bits in some
		// cases to get the right answer.
		switch (g.operator) {
		case NOT:     outputValue = ~input1 & 0x0000FFFF;                        break;
		case AND:     outputValue = input1 & input2;                             break;
		case OR:      outputValue = input1 | input2;                             break;
		case LSHIFT:  outputValue = (input1 << g.operatorValue) & 0x0000FFFF;    break;
		case RSHIFT:  outputValue = (input1 >> g.operatorValue);		         break;
		case NONE:    outputValue = input1;                                      break;
		default: throw new RuntimeException("Unknown Operator");
		}

		// Save this value so that it is not re-calculated later.
		g.outputFound = true;
		g.outputValue = outputValue;
		return outputValue;
	}
}
