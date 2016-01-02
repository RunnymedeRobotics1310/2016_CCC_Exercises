package adventOfCode2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec24 {

	public static void main(String[] args) {
		(new Dec24()).run();
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
	List<Integer> packages = new ArrayList<Integer>();
	int totalWeight = 0;
	int balance;

	private void setUpCommonObjects() {

		setUpPackages();

	}

	private void part1() {

		balance = totalWeight / 3;
		System.out.println(getMinEntanglement());
		
	}

	private void part2() {

		balance = totalWeight / 4;
		System.out.println(getMinEntanglement());
	}


	//*************************************************************************

	private void setUpPackages() {
		
		for (String in: inStrings) {
			packages.add(Integer.valueOf(in));
		}
		
		// Calculate the balance point
		for (Integer p: packages) {
			totalWeight += p;
		}
		
		// Sort the packages in descending order
		Collections.sort(packages, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
			
		});
	}

	private long getMinEntanglement() {

		long minEntanglement = -1;
		
		List<List<Integer>> balancedLists = 
				getBalancedSubLists(packages, new ArrayList<Integer>(), null);
		
		// Find the minimum length list
		int minLength = 1000;
		for (List<Integer> b: balancedLists) {
			if (b.size() < minLength) {
				minLength = b.size();
			}
		}
		
		// find the minimum entanglement
		for (List<Integer> b: balancedLists) {
			if (b.size() == minLength) {
				
				System.out.println(b);
				
				long entanglement = 1;
				for (Integer p: b) {
					entanglement *= p;
				}
				if (entanglement < minEntanglement || minEntanglement == -1) {
					minEntanglement = entanglement;
				}
			}
		}
		
		return minEntanglement;
	}
	
	private List<List<Integer>> getBalancedSubLists(List<Integer> packages, List<Integer> loaded, 
			Integer start) {
		
		List<List<Integer>> balancedSubLists = new ArrayList<List<Integer>>();

		List<Integer> unloaded = new ArrayList<Integer>();
		boolean skip = true;
		for (Integer p: packages) {
			// Skip all packages up to the start
			if (start != null && skip) {
				if (p != start) {
					continue;
				}
				skip = false;
			}
			if (!loaded.contains(p)) {
				unloaded.add(p);
			}
		}
		
		// Calculate the current loaded weight
		int weight = 0;
		for (Integer p: loaded) {
			weight += p;
		}
		
		// Make a list of unloaded packages that fit in the balance
		int remainingWeight = balance - weight;
		
		// Make sublists for all of the unloaded packages
		for (Integer p : unloaded) {
			
			// if this package does not fit, then continue
			if (p > remainingWeight) { continue; }
			
			// If this package completes a balance, then add it to the list
			if (p == remainingWeight) {
				List<Integer> balanced = new ArrayList<Integer>();
				for (Integer l : loaded) {
					balanced.add(l);
				}
				balanced.add(p);
				balancedSubLists.add(balanced);
				continue;
			}
			
			// Add this to the loaded list and continue;
			loaded.add(p);
			List<List<Integer>> balancedLists = getBalancedSubLists(packages, loaded, p);
			loaded.remove(p);
			
			for (List<Integer> b: balancedLists) {
				balancedSubLists.add(b);
			}
			
		}
		
		return balancedSubLists;
	}

}
