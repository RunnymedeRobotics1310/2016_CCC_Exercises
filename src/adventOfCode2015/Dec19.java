package adventOfCode2015;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec19 {

	public static void main(String[] args) {
		(new Dec19()).run();
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
		
		// For this puzzle read one more line
		molecule = in.nextLine();

		in.close();

	}

	//*************************************************************************

	String molecule;
	
	List<Replacement> replacements = new ArrayList<Replacement>();
	
	private class Replacement {
		String in;
		String out;
	}
	
	private void setUpCommonObjects() {

		setUpChemistry();
		
	}

	private void part1() {

		System.out.println(getUniqueChemicals());
	}

	private void part2() {

		calcFewestSteps(molecule, 0);
		
		System.out.println(minReplacements);
	}


	//*************************************************************************

	private void setUpChemistry() {
		
		for (String in: inStrings) {

			String [] s = in.split(" ");

			Replacement r = new Replacement();

			r.in = s[0];
			r.out = s[2];
			
			replacements.add(r);
		}

		// Sort the replacements by chemical length from largest to smallest
		replacements.sort(new Comparator<Replacement>() {
			@Override
			public int compare(Replacement o1, Replacement o2) {
				return o2.out.length() - o1.out.length();
			}
			
		});

	}

	private int getUniqueChemicals() {
		
		List<String> chemicals = new ArrayList<String>(100000);
		
		for (Replacement r: replacements) {
			
			int start = 0;
			
			while (true) {
			
				int i = molecule.indexOf(r.in, start);
				
				if (i < 0) { break; }
				
				
				String newChemical = "";
				if (i > 0) {
					newChemical += molecule.substring(0, i);
				}
				newChemical += r.out;
				
				if (i + r.in.length() < molecule.length()) {
					newChemical += molecule.substring(i+r.in.length());
				}
				
				if (!chemicals.contains(newChemical)) {
					chemicals.add(newChemical);
				}
				
				start = i + r.in.length();
			}
			
		}
		
		return chemicals.size();
	}
	
	int minReplacements = 10000;
	
	private void calcFewestSteps(String formula, int replacementCount) {
	
		// End early if the replacement count is higher than the min so far.
		if (replacementCount > minReplacements) { return; }
		
		// If one of the replacements transforms to e, then this is the
		// last step
		// Print out the fewest steps every time.
		// NOTE: This routine did not end.  By sorting the replacements by 
		// size and printing out the minimum so far, the answer was found
		// without actually finishing the algorithm.
		for (Replacement r: replacements) {
			if (r.out.equals(formula) && r.in.equals("e")) {
				if (replacementCount + 1 < minReplacements) {
					minReplacements = replacementCount + 1;
					
					// Print out the min so far.
					System.out.println(minReplacements);
				}
				return;
			}
		}
		
		// Replace any replacements and try again
		for (Replacement r: replacements) {

			if (r.in.equals("e")) { continue; }
			
			int start = 0;

			// Replace each occurrence in the formula separately.
			while(true) {
				
				start = formula.indexOf(r.out, start);
				
				if (start < 0) { break; }
				
				// Make a new formula by using the replacement, and then
				// try again with the shorter formula.
				String subFormula = "";
				if (start != 0) {
					subFormula += formula.substring(0, start);
				}
				subFormula += r.in;
				if (start+r.out.length() <= formula.length()-1) {
					subFormula += formula.substring(start+r.out.length());
				}
				
				calcFewestSteps(subFormula, replacementCount+1);
				
				start ++;
			}
		}
		
	}
}
