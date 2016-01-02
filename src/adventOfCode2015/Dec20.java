package adventOfCode2015;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec20 {

	public static void main(String[] args) {
		(new Dec20()).run();
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

	int targetPresents;
	
	private void setUpCommonObjects() {
	
		targetPresents = Integer.valueOf(inStrings.get(0));
		
	}
	
	
	private void part1() {

		int house = 1;
		while(true) {
			int presents = getPresents(house);
			if (house%100000 == 0) {
				System.out.println("house " + house + " presents " + presents);
			}
			if (presents > targetPresents) {
				System.out.println("house " + house + " presents " + presents);
				break;
			}
			house ++;
		}
	}

	private void part2() {

		int house = 1;
		while(true) {
			int presents = getPresents2(house);
			if (house%100000 == 0) {
				System.out.println("house " + house + " presents " + presents);
			}
			if (presents > targetPresents) {
				System.out.println("house " + house + " presents " + presents);
				break;
			}
			house ++;
		}

	}


	//*************************************************************************

	private int getPresents(int house) {
		
		int presents = 0;
		
		for (int elf = 1; elf <=house; elf++ ) {
			if (house%elf == 0) {
				presents += elf * 10;
			}
		}
		return presents;
	}

	
	Map<Integer, Integer> activeElves = new HashMap<Integer, Integer>(1000000);
	
	private int getPresents2(int house) {
		
		int presents = 0;
		
		for (int elf = 1; elf <=house; elf++ ) {
			
			if (house%elf == 0) {
				
				if (activeElves.containsKey(elf)) {
					Integer visits = activeElves.get(elf);
					visits = visits - 1;
					activeElves.replace(elf, visits);
					if (visits < 1) { continue; }
				} else {
					activeElves.put(elf, 50);
					
				}
				presents += elf * 11;
			}
		}
		return presents;
	}
}
