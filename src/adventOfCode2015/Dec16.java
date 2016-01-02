package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec16 {

	public static void main(String[] args) {
		(new Dec16()).run();
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

	private class Sue {
		int [] attributes = new int[10];
		Sue () {
			for (int i=0; i<10; i++) {
				attributes[i] = -1;
			}
		}
	}
	
	List<Sue> sues = new ArrayList<Sue>();

	List<String> attributes = new ArrayList<String>();

	Sue giftSue = new Sue();
	
	private void setUpCommonObjects() {

		parseSues();

		// Set up the Gifting Sue
		giftSue.attributes[attributes.indexOf("children:")] = 3;
		giftSue.attributes[attributes.indexOf("cats:")]     = 7;
		giftSue.attributes[attributes.indexOf("samoyeds:")] = 2;
		giftSue.attributes[attributes.indexOf("pomeranians:")] = 3;
		giftSue.attributes[attributes.indexOf("akitas:")] = 0;
		giftSue.attributes[attributes.indexOf("vizslas:")] = 0;
		giftSue.attributes[attributes.indexOf("goldfish:")] = 5;
		giftSue.attributes[attributes.indexOf("trees:")] = 3;
		giftSue.attributes[attributes.indexOf("cars:")] = 2;
		giftSue.attributes[attributes.indexOf("perfumes:")] = 1;

	}

	private void part1() {
		
		System.out.println(getMatchingSue(giftSue));
		
	}

	private void part2() {

		System.out.println(getRealMatchingSue(giftSue));
		
	}


	//*************************************************************************

	private void parseSues() {
		
		attributes.clear();
		attributes.add("children:");
		attributes.add("cats:");
		attributes.add("samoyeds:");
		attributes.add("pomeranians:");
		attributes.add("akitas:");
		attributes.add("vizslas:");
		attributes.add("goldfish:");
		attributes.add("trees:");
		attributes.add("cars:");
		attributes.add("perfumes:");

		for (String in: inStrings) {
			if (in.trim().length() == 0) { continue; }
			
			String [] s = in.split(" ");
			
			Sue sue = new Sue();

			sue.attributes[attributes.indexOf(s[2])] = Integer.valueOf(s[3].substring(0,  s[3].length()-1));
			sue.attributes[attributes.indexOf(s[4])] = Integer.valueOf(s[5].substring(0,  s[5].length()-1));
			sue.attributes[attributes.indexOf(s[6])] = Integer.valueOf(s[7]);
			
			sues.add(sue);
		}
		
	}

	private int getMatchingSue(Sue sue) {
		
		for (Sue s: sues) {
			
			boolean found = true;
			
			for (int i=0; i<attributes.size(); i++) {
				
				if (s.attributes[i] == -1) { continue; }
				
				if (s.attributes[i] != sue.attributes[i]) {
					found = false;
					break;
				}
			}
			
			if (found) {
				return sues.indexOf(s) + 1;
			}
		}
		
		return -1;
	}

	private int getRealMatchingSue(Sue sue) {
		
		List<Integer> greaterIndexes = new ArrayList<Integer>();
		List<Integer> lesserIndexes  = new ArrayList<Integer>();
		
		greaterIndexes.add(attributes.indexOf("cats:"));
		greaterIndexes.add(attributes.indexOf("trees:"));
		
		lesserIndexes.add(attributes.indexOf("pomeranians:"));
		lesserIndexes.add(attributes.indexOf("goldfish:"));
		
		for (Sue s: sues) {
			
			boolean found = true;
			
			for (int i=0; i<attributes.size(); i++) {
				
				if (s.attributes[i] == -1) { continue; }
				
				if (greaterIndexes.contains(i)) {
					if (s.attributes[i] <= sue.attributes[i]) {
						found = false;
						break;
					}
				} else if (lesserIndexes.contains(i)) {
					if (s.attributes[i] >= sue.attributes[i]) {
						found = false;
						break;
					}
				} else if (s.attributes[i] != sue.attributes[i]) {
					found = false;
					break;
				}
			}
			
			if (found) {
				return sues.indexOf(s) + 1;
			}
		}
		
		return -1;
	}

}
