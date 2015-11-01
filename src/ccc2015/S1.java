package sampleProblems;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class S1 {

	Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		S1 s1 = new S1();
		s1.run();
	}
	
	//*************************************************
	
	List<Integer> intLs;

	public void run() {
		
		int k = new Integer(input.nextLine());
		
		intLs = new ArrayList<Integer>(k);
		
		for (int i=0; i<k; i++) {
			
			int n = new Integer(input.nextLine());
			
			if (n == 0 ) {
				intLs.remove(intLs.size()-1);
			} else {
				intLs.add(n);
			}

		}
		
		int sum = 0;
		for (Integer n:intLs) {
			sum += n;
		}
		
		System.out.println(sum);
	
	}
}
