package adventOfCode2015;

import java.util.Scanner;

public class Dec01 {

	public static void main(String[] args) {
		(new Dec01()).solve();
	}

	private void solve() {
		
		Scanner in = new Scanner(System.in);
		
		String s = in.nextLine();
		
		long answer;
		
		// answer = part1(s);
		
		answer = part2(s);
		
		System.out.println(answer);
		
		in.close();
	}
	
	private long part1(String s) {
		
		char [] instructions = s.toCharArray();
		
		long floor = 0;
		
		for (char c: instructions) {
			
			if (c == '(') { 
				floor++;
			} else {
				floor--;
			}
			
		}
		
		return floor;
	}

	private long part2(String s) {
		
		char [] instructions = s.toCharArray();
		
		long floor = 0;
		long instructionCount = 0;
		
		for (char c: instructions) {
			instructionCount++;
			
			if (c == '(') { 
				floor++;
			} else {
				floor--;
			}
			
			if (floor < 0) { break; }
		}
		
		return instructionCount;
	}

}
