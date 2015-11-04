package ccc2015;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class J4 {

	class Info {
		char action;
		int number;
		
		Info(String s) {
			action = s.charAt(0);
			number = Integer.valueOf(s.substring(1).trim());
		}
	}
	
	public static void main(String[] args) {
		J4 problem = new J4();
		problem.run();
	}
	
	//*************************************************
	
	Scanner input = new Scanner(System.in);
	
	List<Info> infoList; 
	List<Integer> friendList;

	public void run() {
		
		readInput();
		
		printSolution();
	}
	
	private void addToFriends(int friend) {
		
		for (int i=0; i<friendList.size(); i++) {
			if (friendList.get(i)==friend) {
				return;
			}
		}
		friendList.add(friend);
	}
	
	private void printSolution() {
		
		Collections.sort(friendList);
		
		for (int i=0; i<friendList.size(); i++) {
			
			int friend = friendList.get(i);
			
			int time = 0;
			boolean countTime = false;
			for (int j=0; j<infoList.size(); j++) {
				Info info = infoList.get(j);
				if (info.action =='W') {
					if (countTime) {
						time += info.number-1;
					}
				} else {
					if (countTime) { time++; }
					if (info.number== friend) {
						countTime = !countTime;
					}
				}
			}
			if (countTime) {
				time = -1;
			}
			System.out.println("" + friend + " " + time);
		}
	}
	
	private void readInput() {
		
		// Read the first line
		int m = Integer.valueOf(input.nextLine());
		
		// Create lists of the appropriate size.
		infoList = new ArrayList<Info>(m);
		friendList = new ArrayList<Integer>(m);
		
		for (int i=0; i<m; i++) {
			String s = input.nextLine();
			Info info = new Info(s);
			infoList.add(info);
			if (info.action != 'W') {
				addToFriends(info.number);
			}
		}
	}
}
