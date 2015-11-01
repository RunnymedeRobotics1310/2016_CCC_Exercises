package sampleProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class S3 {

	Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		S3 puzzle = new S3();
		puzzle.run();
	}

	// *************************************************

	private List<Integer> mountain;
	private List<Integer> branch;

	public void run() {

		int t = new Integer(input.nextLine());

		for (int i = 0; i < t; i++) {
			readInput();
			System.out.println(solve());
		}
	}

	private void readInput() {

		int n = new Integer(input.nextLine());

		mountain = new ArrayList<Integer>(n);
		branch = new ArrayList<Integer>(n);

		for (int i = 0; i < n; i++) {
			mountain.add(new Integer(input.nextLine()));
		}
	}

	private String solve() {

		int size = mountain.size();
		for (int ingredient = 1; ingredient <= size; ingredient++) {

			// Look to see if the ingredient is on the branch
			if (!branch.isEmpty()) {
				if (branch.get(branch.size() - 1) == ingredient) {
					branch.remove(branch.size() - 1);
					continue;
				}
			}

			// See if it is on the mountain
			boolean ingredientFound = false;
			while (!mountain.isEmpty()) {
				if (mountain.get(mountain.size() - 1) == ingredient) {
					ingredientFound = true;
					mountain.remove(mountain.size() - 1);
					break;
				}
				branch.add(mountain.remove(mountain.size() - 1));
			}
			
			if (ingredientFound) {
				continue;
			}

			return "N";
		}
		
		return "Y";
	}
}
