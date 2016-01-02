package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec14 {

	public static void main(String[] args) {
		(new Dec14()).run();
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

	private class Reindeer {
		int sprintSpeed;
		int sprintTime;
		int restTime;
		boolean run = false;
		int timer = 0;
		int distance = 0;
		int points = 0;
	}
	
	List<Reindeer> reindeers = new ArrayList<Reindeer>();

	private void setUpCommonObjects() {

		parseReindeer();
		
	}

	private void part1() {

		// Race for 2503 seconds.
		race(2503);
		
		System.out.println(getMaxDistance());
		
	}

	private void part2() {
		
		System.out.println(getMaxPoints());
		
	}

	private void parseReindeer() {

		for (String in: inStrings) {
			if (in.trim().length() == 0) { continue; }
			
			String [] s = in.split(" ");
			
			Reindeer r = new Reindeer();
			r.sprintSpeed = Integer.valueOf(s[3]);
			r.sprintTime = Integer.valueOf(s[6]);
			r.restTime = Integer.valueOf(s[13]);
			
			reindeers.add(r);
		}
	}
	
	private void race(int sec) {
		
		for (int i=0; i<sec; i++) {
			for (Reindeer r: reindeers) {
				r.timer--;
				if (r.timer <= 0) {
					r.run = !r.run;
					if (r.run) {
						r.timer = r.sprintTime;
					} else {
						r.timer = r.restTime;
					}
				}
				if (r.run) {
					r.distance += r.sprintSpeed;
				}
			}
			
			// At the end of each second award the distance points
			int maxDistance = getMaxDistance();
			for (Reindeer r: reindeers) {
				if (r.distance == maxDistance) {
					r.points++;
				}
			}
		}
	}
	
	private int getMaxDistance() {
		
		int maxDistance = 0;
		
		for (Reindeer r: reindeers) {
			if (r.distance > maxDistance) {
				maxDistance = r.distance;
			}
		}
		return maxDistance;
	}
	
	private int getMaxPoints() {
		
		int maxPoints = 0;
		
		for (Reindeer r: reindeers) {
			if (r.points > maxPoints) {
				maxPoints = r.points;
			}
		}
		return maxPoints;
	}
	
}
