package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec21 {

	public static void main(String[] args) {
		(new Dec21()).run();
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

	List<Integer> containers = new ArrayList<Integer>();

	int bossHitpoints;
	
	class Player {
		
		int hitpoints;
		int damage;
		int armor;
		
		Player(int hitpoints, int damage, int armor) {
			this.hitpoints = hitpoints;
			this.damage = damage;
			this.armor = armor;
		}
	}
	
	Player boss;
	Player player;
	
	private void setUpCommonObjects() {

		setupInventory();
		
		String [] s = inStrings.get(0).split(" ");
		bossHitpoints = Integer.valueOf(s[2]);

		s = inStrings.get(1).split(" ");
		int damage = Integer.valueOf(s[1]);
		
		s = inStrings.get(2).split(" ");
		int armor = Integer.valueOf(s[1]);
		
		boss   = new Player(bossHitpoints, damage, armor);

		player = new Player(100, 0, 0);
	}

	private void part1() {

		System.out.println(getMinWinCost());
		
	}

	private void part2() {

		System.out.println(getMaxLoseCost());
	}


	//*************************************************************************

	// Inventory
	/*
	Weapons:    Cost  Damage  Armor
	Dagger        8     4       0
	Shortsword   10     5       0
	Warhammer    25     6       0
	Longsword    40     7       0
	Greataxe     74     8       0

	Armor:      Cost  Damage  Armor
	Leather      13     0       1
	Chainmail    31     0       2
	Splintmail   53     0       3
	Bandedmail   75     0       4
	Platemail   102     0       5

	Rings:      Cost  Damage  Armor
	Damage +1    25     1       0
	Damage +2    50     2       0
	Damage +3   100     3       0
	Defense +1   20     0       1
	Defense +2   40     0       2
	Defense +3   80     0       3
	*/
	
	private class Tool {
		int cost;
		int damage;
		int armor;
		Tool(int cost, int damage, int armor) {
			this.cost = cost;
			this.damage = damage;
			this.armor = armor;
		}
		
		public String toString() { return "C:" + cost + " D:" + damage + " A:" + armor; }
	}
	
	List<Tool> weapons = new ArrayList<Tool>();
	List<Tool> armors  = new ArrayList<Tool>();
	List<Tool> rings   = new ArrayList<Tool>();
	
	private void setupInventory() {
		
		weapons.add(new Tool( 8, 4, 0));
		weapons.add(new Tool(10, 5, 0));
		weapons.add(new Tool(25, 6, 0));
		weapons.add(new Tool(40, 7, 0));
		weapons.add(new Tool(74, 8, 0));
		
		armors.add(new Tool(  0, 0, 0));
		armors.add(new Tool( 13, 0, 1));
		armors.add(new Tool( 31, 0, 2));
		armors.add(new Tool( 53, 0, 3));
		armors.add(new Tool( 75, 0, 4));
		armors.add(new Tool(102, 0, 5));

		rings.add(new Tool(  0, 0, 0));
		rings.add(new Tool(  0, 0, 0));
		rings.add(new Tool( 25, 1, 0));
		rings.add(new Tool( 50, 2, 0));
		rings.add(new Tool(100, 3, 0));
		rings.add(new Tool( 20, 0, 1));
		rings.add(new Tool( 40, 0, 2));
		rings.add(new Tool( 80, 0, 3));
	}

	private int getMinWinCost() {
		
		int minWinCost = 100000;
		
		// The player must choose exactly one weapon
		for (Tool weapon: weapons) {
			
			// The player can choose one armour (one of the armours is 0 to indicate no armour)
			for (Tool armor: armors) {
				
				// The player can choose up to two rings (two empty rings are added to the ring list)
				for (int i=0; i<rings.size()-1; i++) {
					for (int j=i+1; j<rings.size(); j++) {
						
						Tool ring1 = rings.get(i);
						Tool ring2 = rings.get(j);
						
						int cost = weapon.cost + armor.cost + ring1.cost + ring2.cost;
						
						// Don't play if the total cost is more than the least cost win
						if (cost > minWinCost) {
							continue;
						}
						// Set up player
						player.hitpoints = 100;
						player.armor  = 0;
						player.damage = 0;
						addTool(player, weapon);
						addTool(player, armor);
						addTool(player, ring1);
						addTool(player, ring2);
						
						boss.hitpoints = bossHitpoints;
						
						if (winBattle()) {
							minWinCost = cost;
						}
					}
				}
			}
		}
		return minWinCost;
	}
	
	private int getMaxLoseCost() {
		
		int maxLoseCost = 0;
		
		// The player must choose exactly one weapon
		for (Tool weapon: weapons) {
			
			// The player can choose one armour (one of the armours is 0 to indicate no armour)
			for (Tool armor: armors) {
				
				// The player can choose up to two rings (two empty rings are added to the ring list)
				for (int i=0; i<rings.size()-1; i++) {
					for (int j=i+1; j<rings.size(); j++) {
						
						Tool ring1 = rings.get(i);
						Tool ring2 = rings.get(j);
						
						int cost = weapon.cost + armor.cost + ring1.cost + ring2.cost;
						
						// Don't play if the total cost is less than the max lose
						if (cost < maxLoseCost) {
							continue;
						}
						// Set up player
						player.hitpoints = 100;
						player.armor  = 0;
						player.damage = 0;
						addTool(player, weapon);
						addTool(player, armor);
						addTool(player, ring1);
						addTool(player, ring2);
						
						boss.hitpoints = bossHitpoints;
						
						if (!winBattle()) {
							maxLoseCost = cost;
						}
					}
				}
			}
		}
		return maxLoseCost;
	}
	
	private void addTool(Player player, Tool tool) {
		
		player.damage += tool.damage;
		player.armor  += tool.armor;
	}
	
	private boolean winBattle() {
		
		while (true) {
			
			// Player deals damage first
			int damage = player.damage - boss.armor;
			boss.hitpoints -= damage > 0 ? damage : 1;

			if (boss.hitpoints <= 0) { return true; }
			
			// Boss deals damage next
			damage = boss.damage - player.armor;
			player.hitpoints -= damage > 0 ? damage : 1;

			if (player.hitpoints <= 0) { return false; }
			
		}
	}
}
