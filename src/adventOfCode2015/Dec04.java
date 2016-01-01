package adventOfCode2015;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// This is the template used to solve all of the advent of code puzzles.
// Code specific to the challenge is listed below the line of ********
public class Dec04 {

	public static void main(String[] args) {
		(new Dec04()).run();
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

	String prefix;
	
	MessageDigest md = null;

	private void setUpCommonObjects() {

		prefix = inStrings.get(0);
		
		// A message digest is used to generate an MD5 encryption.
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
			System.exit(0);
		}
		
	}
	
	private void part1() {

		System.out.println(getNumberStartsWith("00000"));

	}

	private void part2() {

		System.out.println(getNumberStartsWith("000000"));

	}
	
	private int getNumberStartsWith(String startsWith) {
		
		int n = 0; 
		
		while(true) {
			
			// Try all numeric suffixes until one works in order to 
			// find the smallest number
			String b = prefix + n;

			// Get the MD5 encoding of the string
			byte[] md5 = md.digest(b.getBytes());

			// Convert to hex.  The byte array contains binary data
			// and we are looking for a number of hex characters.
			String hex = bytesToHex(md5);
			
			if (hex.startsWith(startsWith)) { break; }
			
			n++;
		}
		
		return n;
	}
	
	
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for ( int j = 0; j < bytes.length; j++ ) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}

}
