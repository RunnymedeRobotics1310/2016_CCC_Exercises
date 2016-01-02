package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec15 {

	public static void main(String[] args) {
		(new Dec15()).run();
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

	private class Ingredient {
		int capacity;
		int durability;
		int flavour;
		int texture;
		int calories;
	}
	
	List<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	int [] quantities;
	
	private void setUpCommonObjects() {

		parseIngredients();
		
	}

	private void part1() {
		
		for (int i=0; i< quantities.length; i++) { quantities[i] = -1; }
		
		System.out.println(getMaxScore(quantities, 0));
		
	}

	private void part2() {
		
		System.out.println(maxCalorieScore);
	}


	private void parseIngredients() {

		for (String in: inStrings) {
			if (in.trim().length() == 0) { continue; }
			
			String [] s = in.split(" ");
			
			Ingredient i = new Ingredient();
			i.capacity   = Integer.valueOf(s[2].substring(0,s[2].length()-1));
			i.durability = Integer.valueOf(s[4].substring(0,s[4].length()-1));
			i.flavour    = Integer.valueOf(s[6].substring(0,s[6].length()-1));
			i.texture    = Integer.valueOf(s[8].substring(0,s[8].length()-1));
			i.calories   = Integer.valueOf(s[10]);
			
			ingredients.add(i);
		}
		
		quantities = new int [ingredients.size()];
	}
	

	private int maxScore = 0;
	private int maxCalorieScore = 0;
	
	private int getMaxScore(int [] quantities, int ingredient) {
		
		int used = 0;
		for (int i=0; i<quantities.length; i++) {
			if (quantities[i] > 0) { used += quantities[i]; }
		}
		
		if (ingredient == quantities.length-1) {
			
			quantities[ingredient] = 100-used;
			
			int score = getScore(quantities);
			if (score > maxScore) { maxScore = score; }

			int calorieScore = getCalorieScore(quantities);
			if (calorieScore > maxCalorieScore) { maxCalorieScore = calorieScore; }
			
			quantities[ingredient] = -1;
			return maxScore;
		}
		
		for (int i=0; i<=100-used; i++) {
			quantities[ingredient] = i;
			getMaxScore(quantities, ingredient+1);
		}
		quantities[ingredient] = -1;
		
		return maxScore;
	}
	
	private int getScore(int [] quantities) {
		
		int capacity = 0;
		int durability = 0;
		int flavour = 0;
		int texture = 0;
		
		for (int i=0; i<quantities.length; i++) {
			Ingredient ingredient = ingredients.get(i);
			capacity   += ingredient.capacity   * quantities[i];
			durability += ingredient.durability * quantities[i];
			flavour    += ingredient.flavour    * quantities[i];
			texture    += ingredient.texture    * quantities[i];
		}
		
		if (capacity < 0 || durability < 0 || flavour < 0 || texture < 0) {
			return 0;
		}
		
		return capacity * durability * flavour * texture;
	}

	private int getCalorieScore(int [] quantities) {
		
		int capacity = 0;
		int durability = 0;
		int flavour = 0;
		int texture = 0;
		int calories = 0;
		
		for (int i=0; i<quantities.length; i++) {
			Ingredient ingredient = ingredients.get(i);
			capacity   += ingredient.capacity   * quantities[i];
			durability += ingredient.durability * quantities[i];
			flavour    += ingredient.flavour    * quantities[i];
			texture    += ingredient.texture    * quantities[i];
			calories   += ingredient.calories   * quantities[i];
		}
		
		if (capacity < 0 || durability < 0 || flavour < 0 || texture < 0) {
			return 0;
		}
		
		if (calories != 500) { return 0; }
		
		return capacity * durability * flavour * texture;
	}
}
