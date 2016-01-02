package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec18 {

	public static void main(String[] args) {
		(new Dec18()).run();
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
	
	boolean [][] lights = new boolean[100][100];

	private void setUpCommonObjects() {

		setUpLights();
	}

	private void part1() {

		for (int i=0; i<100; i++) {
			lights = animate(lights);
		}

		System.out.println(getLitCount(lights));
		
	}

	private void part2() {

		setUpLights();
		lightCorners();
		for (int i=0; i<100; i++) {
			lights = animate(lights);
			lightCorners();
		}

		System.out.println(getLitCount(lights));
		
	}

	//*************************************************************************

	private void setUpLights() {
		
		int row = -1;
		for (String in: inStrings) {

			row++;
			char [] c = in.toCharArray();
			
			for (int i=0; i<100; i++) {
				if (c[i] == '#') {
					lights[row][i] = true;
				} else {
					lights[row][i] = false;
				}
			}
			
		}

	}

	private void lightCorners() {
		lights[0][0] = true;
		lights[0][99] = true;
		lights[99][0] = true;
		lights[99][99] = true;
	}
	
	private boolean[][] animate(boolean [] [] curLights) {
		
		boolean [][] nextLights = new boolean[100][100];
		
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				
				// Get the lit neighbours
				int litNeighbours = 0;
				for (int k=(i==0?0:i-1); k<(i==99?100:i+2); k++) {
					for (int l=(j==0?0:j-1); l<(j==99?100:j+2); l++) {
						
						if (k==i && l==j) { continue; } // dont count the current light as a neighbour
						
						if (curLights[k][l]) { litNeighbours++; }
						
					}
				}
				
				// Adjust the light based on the lit neighbours
				if (curLights[i][j]) {
					
					if (litNeighbours == 2 || litNeighbours == 3) {
						nextLights[i][j] = true;
					} else {
						nextLights[i][j] = false;
					}
					
				} else {
					
					if (litNeighbours == 3) {
						nextLights[i][j] = true;
					} else {
						nextLights[i][j] = false;
					}
				}
			}
		}
		
		return nextLights;
	}

	private int getLitCount(boolean [] [] lights) {
		
		int count = 0;
		
		for (int i=0; i<100; i++) {
			for (int j=0; j<100; j++) {
				if (lights[i][j]) {
					count++;
				}
			}
		}
		
		return count;
	}

}
