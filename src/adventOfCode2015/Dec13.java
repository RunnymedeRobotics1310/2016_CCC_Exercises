package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec13 {

	public static void main(String[] args) {
		(new Dec13()).run();
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

	List<String> guests; 
	
	int [][] happinessGrid;

	private void setUpCommonObjects() {

		makeGuestList();
		makeHappinessGrid();
		
	}

	private void part1() {

		List<Integer> placedGuests = new ArrayList<Integer>();
		
		System.out.println(getMaxHappiness(placedGuests));
		
	}

	private void part2() {

		// Add yourself to the happiness grid by adding a row and col
		// to the grid.
		int [] [] happinessGrid2 = new int [happinessGrid.length+1][happinessGrid.length+1];
		
		for (int i=0; i< happinessGrid2.length; i++) {
			for (int j=0; j< happinessGrid2.length; j++) {
				happinessGrid2[i][j] = 0;
			}
		}
		for (int i=0; i< happinessGrid.length; i++) {
			for (int j=0; j< happinessGrid.length; j++) {
				happinessGrid2[i][j] = happinessGrid[i][j];
			}
		}
		
		// Add me to the list.  The last row and col are both 0 because
		// there is no happiness.
		guests.add("me");

		happinessGrid = happinessGrid2;
		
		List<Integer> placedGuests = new ArrayList<Integer>();
		
		System.out.println(getMaxHappiness(placedGuests));
		
	}


	private void makeGuestList() {
		
		guests = new ArrayList<String>(inStrings.size());
		
		for (String in : inStrings) {
			if (in.trim().length() == 0) { continue; }
			
			String [] inParsed = in.split(" ");
			
			if (!guests.contains(inParsed[0])) {
				guests.add(inParsed[0]);
			}
		}
	}

	private void makeHappinessGrid() {

		happinessGrid = new int [guests.size()][guests.size()];
		
		for (String in : inStrings) {
			if (in.trim().length() == 0) { continue; }

			String [] inParsed = in.split(" ");
			
			int guest1 = guests.indexOf(inParsed[0]);
			int guest2 = guests.indexOf(inParsed[10].substring(0, inParsed[10].length()-1));
			
			int happiness = Integer.valueOf(inParsed[3]);
			
			if (inParsed[2].equals("lose")) {
				happiness = happiness * -1;
			}
			
			happinessGrid[guest1][guest2] = happiness;
		}

	}
	
	private int getMaxHappiness(List<Integer> placedGuests) {
		
		// Get the list of remaining guests
		List<Integer> remainingGuests = new ArrayList<Integer>(guests.size());
		
		for (int i=0; i<guests.size(); i++) {
			if (placedGuests.contains(i)) { continue; }
			remainingGuests.add(i);
		}
		
		if (remainingGuests.size() == 1) {
			int guest = remainingGuests.get(0);
			return    happinessGrid[guest][placedGuests.get(placedGuests.size()-1)] // effect of previous guest
					+ happinessGrid[placedGuests.get(placedGuests.size()-1)][guest] // effect on previous guest
					+ happinessGrid[guest][placedGuests.get(0)]   // effect of guest 0
					+ happinessGrid[placedGuests.get(0)][guest];  // effect on guest 0
		}
		
		// Calculate the maximum effect for each for each of the guests in the list of remaining guests.
		int maxHappiness = 0;
		for (Integer guest: remainingGuests) {
			
			// add the guest to the guest list and calculate the happiness
			placedGuests.add(guest);
			
			int happiness = getMaxHappiness(placedGuests);
			
			placedGuests.remove(guest);
			
			if (placedGuests.size() > 0) {
				happiness += happinessGrid[guest][placedGuests.get(placedGuests.size()-1)] 
						+ happinessGrid[placedGuests.get(placedGuests.size()-1)][guest];
			}
			
			if (happiness > maxHappiness) {
				maxHappiness = happiness;
			}
		}
		
		return maxHappiness;
	}
	
	
}
