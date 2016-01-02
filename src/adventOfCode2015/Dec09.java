package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec09 {

	public static void main(String[] args) {
		(new Dec09()).run();
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
	List<String> destinations = new ArrayList<String>(100);
	
	int [][] distances;

	private void setUpCommonObjects() {

		makeDistanceChart();

	}

	private void part1() {

		System.out.println(calcMinDistance());
	}

	private void part2() {

		System.out.println(calcMaxDistance());
	}

	private void makeDistanceChart() {

		// Make a list of destination strings.  
		// The number of unique destinations determines the size of the 
		// distance chart, and the index in the list determines the
		// index in the distances array.
		
		for (String in: inStrings) {
			
			String [] strings = in.split(" ");
			
			if (!destinations.contains(strings[0])) {
				destinations.add(strings[0]);
			}
			
			if (!destinations.contains(strings[2])) {
				destinations.add(strings[2]);
			}
		}
		
		distances = new int [destinations.size()][destinations.size()];
		
		for (int i=0; i<distances.length; i++) {
			for (int j=0; j<distances.length; j++) {
				distances[i][j] = 0;
			}
		}
		
		for (String in: inStrings) {
			
			String [] strings = in.split(" ");
			
			int start = destinations.indexOf(strings[0]);
			int end   = destinations.indexOf(strings[2]);
			int dist  = Integer.valueOf(strings[4]);
			
			distances[start][end] = dist;
			distances[end][start] = dist;
		}
		
	}
	
	private int calcMinDistance() {
		
		List<Integer> visited = new ArrayList<Integer>(distances.length);

		int minDistance = -1;

		for (int i=0; i<distances.length; i++) {
			int dist = calcMinDistance(i, visited);
			if (minDistance == -1 || dist < minDistance) {
				minDistance = dist;
			}
		}
		
		return minDistance;
	}
	
	// This recursive routine calculates the minimum distance
	private int calcMinDistance(int place, List<Integer> visited) {

		// Get a list of possible next places
		List<Integer> nextPlaces = new ArrayList<Integer>(distances.length);
		for (int i=0; i<distances.length; i++) {
			if (i == place) { continue; }
			if (visited.contains(i)) {
				continue;
			}
			nextPlaces.add(i);
		}

		if (nextPlaces.size() == 0) { throw new RuntimeException("No next place available, visited " + visited); }
		
		// If there is only one place, then that is the distance
		if (nextPlaces.size() == 1) {
			return distances[place][nextPlaces.get(0)];
		}
		
		visited.add(place);
		
		int minDistance = -1;
		
		for (int nextPlace: nextPlaces) {
			
			int dist = calcMinDistance(nextPlace, visited);
			if (minDistance == -1 || dist + distances[place][nextPlace] < minDistance) {
				minDistance = dist + distances[place][nextPlace];
			}
		}
		
		visited.remove(visited.indexOf(place));
		
		return minDistance;
	}
	
	private int calcMaxDistance() {
		
		List<Integer> visited = new ArrayList<Integer>(distances.length);

		int maxDistance = -1;

		for (int i=0; i<distances.length; i++) {
			int dist = calcMaxDistance(i, visited);
			if (maxDistance == -1 || dist > maxDistance) {
				maxDistance = dist;
			}
		}
		
		return maxDistance;
	}
	
	private int calcMaxDistance(int place, List<Integer> visited) {

		// Get a list of possible next places
		List<Integer> nextPlaces = new ArrayList<Integer>(distances.length);
		for (int i=0; i<distances.length; i++) {
			if (i == place) { continue; }
			if (visited.contains(i)) {
				continue;
			}
			nextPlaces.add(i);
		}

		if (nextPlaces.size() == 0) { throw new RuntimeException("No next place available, visited " + visited); }
		
		// If there is only one place, then that is the distance
		if (nextPlaces.size() == 1) {
			return distances[place][nextPlaces.get(0)];
		}
		
		visited.add(place);
		
		int maxDistance = -1;
		
		for (int nextPlace: nextPlaces) {
			
			int dist = calcMaxDistance(nextPlace, visited);
			if (maxDistance == -1 || dist + distances[place][nextPlace] > maxDistance) {
				maxDistance = dist + distances[place][nextPlace];
			}
		}
		
		visited.remove(visited.indexOf(place));
		
		return maxDistance;
	}
	
}
