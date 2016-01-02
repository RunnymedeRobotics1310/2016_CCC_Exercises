package adventOfCode2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec17 {

	public static void main(String[] args) {
		(new Dec17()).run();
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

	List<Integer> containers = new ArrayList<Integer>();
	
	private void setUpCommonObjects() {

		parseContainers();
		
	}

	private void part1() {
		
		System.out.println(getContainerCombinations(0, 150));
		
	}

	private void part2() {

		calcMinContainerCombinations(0, 0, 150);
		System.out.println(minContainerCombinations);
		
	}


	private void parseContainers() {
		
		List<Integer> containerList = new ArrayList<Integer>();
		
		for (String in: inStrings) {

			if (in.trim().length() == 0) { continue; }

			containerList.add(Integer.valueOf(in));
		}

		// Sort the containers in descending size order.
		Collections.sort(containerList);
		
		for (Integer i: containerList) {
			containers.add(0, i);
		}
	}
	

	private int getContainerCombinations(int startIndex, int remainingLiters) {

		int combinations = 0;
		
		if (startIndex >= containers.size()) { return 0; }
		
		// get all options when this container is used
		int containerSize = containers.get(startIndex);
		
		if (containerSize == remainingLiters) {
			combinations ++;
		} else if (containerSize < remainingLiters) {
			combinations += getContainerCombinations(startIndex+1, remainingLiters-containerSize);
		}
		
		// Add all combinations when this container is not used
		combinations += getContainerCombinations(startIndex+1, remainingLiters);
		
		return combinations;
	}

	
	private int minContainerCount        = 100;
	private int minContainerCombinations = 0;
	
	private void calcMinContainerCombinations(int startIndex, int containerCount, int remainingLiters) {

		if (startIndex >= containers.size()) { return; }
		
		if (containerCount >= minContainerCount) { return; }

		
		int containerSize = containers.get(startIndex);
		
		// if this is the last container filling the containers.
		if (containerSize == remainingLiters) {

			// Check if this is the new minimum.
			if (containerCount + 1 < minContainerCount) {
				minContainerCount = containerCount + 1;
				minContainerCombinations = 0;
			}
			
			if (containerCount + 1 == minContainerCount) {
				minContainerCombinations++;
			}
		
		} else if (containerSize < remainingLiters) {
			
			calcMinContainerCombinations(startIndex+1, containerCount+1, remainingLiters-containerSize);

		}
		
		// Skip this container and continue trying the other containers.
		calcMinContainerCombinations(startIndex+1, containerCount, remainingLiters);

		return;
	}

}
