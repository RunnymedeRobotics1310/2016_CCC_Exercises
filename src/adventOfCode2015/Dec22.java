package adventOfCode2015;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//This is the template used to solve all of the advent of code puzzles.
//Code specific to the challenge is listed below the line of ********
public class Dec22 {

	public static void main(String[] args) {
		(new Dec22()).run();
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
	int bossHitpoints;
	int bossDamage;
	
	boolean hard = false;

	private void setUpCommonObjects() {
		
		String [] s = inStrings.get(0).split(" ");
		
		bossHitpoints = Integer.valueOf(s[2]);

		s = inStrings.get(1).split(" ");
		
		bossDamage = Integer.valueOf(s[1]);
		
	}
	
	private void part1() {

		hard = false;
		System.out.println(getMinManaVictory(true, 50, 500, bossHitpoints, 0, 0, 0, -1));
		
	}

	private void part2() {

		hard = true;
		System.out.println(getMinManaVictory(true, 50, 500, bossHitpoints, 0, 0, 0, -1));

	}


	//*************************************************************************

	
	/*
	Magic Missile costs 53 mana. It instantly does 4 damage.
	Drain costs 73 mana. It instantly does 2 damage and heals you for 2 hit points.
	Shield costs 113 mana. It starts an effect that lasts for 6 turns. While it is active, your armor is increased by 7.
	Poison costs 173 mana. It starts an effect that lasts for 6 turns. At the start of each turn while it is active, it deals the boss 3 damage.
	Recharge costs 229 mana. It starts an effect that lasts for 5 turns. At the start of each turn while it is active, it gives you 101 new mana.
	*/
	
	private int getMinManaVictory(boolean playerTurn, int playerHp, int playerMana, int bossHp, int shieldTurns, int poisonTurns, 
			int rechargeTurns, int maxManaSpend) {
		
		int minMana = -1;
		
		// Resolve any effects at the start of the turn.
		int newPlayerMana = playerMana;
		int newBossHp = bossHp;
		int newPlayerHp = playerHp;
		
		// If hard, the player loses one hit point every turn
		if (hard) {
			newPlayerHp -= 1;
			if (newPlayerHp <= 0) { return -1; } // the player loses
		}
		
		// If poison is in effect, the boss loses 3 hp.
		if (poisonTurns > 0) {
			newBossHp -= 3;
			if (newBossHp <= 0) { 
				return 0; 
			}  // If the boss is dead, no more mana is spent
		}
		
		// If Recharge is in effect, then add mana to the player
		if (rechargeTurns > 0) { 
			newPlayerMana += 101;
		}
		
		int newPoisonTurns   = poisonTurns > 0   ? poisonTurns-1   : 0;
		int newShieldTurns   = shieldTurns > 0   ? shieldTurns-1   : 0;
		int newRechargeTurns = rechargeTurns > 0 ? rechargeTurns-1 : 0;
		
		// If this is the bosses turn, then take a hit.
		if (!playerTurn) {
			int damage = bossDamage;
			if (newShieldTurns > 0) {
				damage -= 7;
				if (damage < 1) { damage = 1; }
			}
			newPlayerHp -= damage;
			if (newPlayerHp <= 0) {
				return -1; // player loses
			}
			// continue game
			return getMinManaVictory(true, newPlayerHp, newPlayerMana, newBossHp, newShieldTurns, newPoisonTurns, newRechargeTurns, 
					maxManaSpend);
		}
		
		// Cut off this path if a shorter path was found.
		if (maxManaSpend >= 0) { 
			if (maxManaSpend < 53) { 
				return -1; 
			}
		}
		
		// Magic Missile costs 53 mana. It instantly does 4 damage.
		
		// If the player has < 53 mana, they lose 
		if (newPlayerMana < 53) { return -1; }
		
		// Try casting magic missile for 4 damage.
		if (newBossHp <= 4) { 
			return 53; 
		}  // You kill the boss! This is the minimum cost spell you can cast
		
		// Boss is not dead
		int mana = getMinManaVictory(false, newPlayerHp, newPlayerMana-53, newBossHp-4, newShieldTurns, newPoisonTurns, newRechargeTurns, 
				maxManaSpend < 0 ? -1 : maxManaSpend-53);
		
		if (mana >= 0 && (mana+53 < minMana || minMana == -1)) {
			minMana = mana+53;
		}
		
		if (minMana > 0  && (minMana < maxManaSpend || maxManaSpend < 0)) {
			maxManaSpend = minMana;
		}
		
		// Try casting drain
		if (maxManaSpend >= 0 && maxManaSpend < 73) { return minMana; } // stop looking down this path because a shorter path was found.
		
		if (newPlayerMana < 73) { return minMana; } // you cannot cast more spells
		
		mana = getMinManaVictory(false, newPlayerHp+2, newPlayerMana-73, newBossHp-2, newShieldTurns, newPoisonTurns, newRechargeTurns, 
				maxManaSpend < 0 ? -1 : maxManaSpend-73);

		if (mana >= 0 && (mana+73 < minMana || minMana == -1)) {
			minMana = mana+73;
		}
		
		if (minMana > 0  && (minMana < maxManaSpend || maxManaSpend < 0)) {
			maxManaSpend = minMana;
		}

		// If shield is not active, then try casting shield
		if (maxManaSpend >= 0 && maxManaSpend < 113) { return minMana; } // stop looking down this path because a shorter path was found.
		if (newPlayerMana < 113) { return minMana; } // you cannot cast more spells
		if (newShieldTurns == 0) {
			
			mana = getMinManaVictory(false, newPlayerHp, newPlayerMana-113, newBossHp, 6, newPoisonTurns, newRechargeTurns,
					maxManaSpend < 0 ? -1 : maxManaSpend-113);
			
			if (mana >= 0 && (mana+113 < minMana || minMana == -1)) {
				minMana = mana+113;
			}
			
		}

		if (minMana > 0  && (minMana < maxManaSpend || maxManaSpend < 0)) {
			maxManaSpend = minMana;
		}
		
		// If Poison is not active, then try casting Poison
		if (maxManaSpend >= 0 && maxManaSpend < 173) { return minMana; } // stop looking down this path because a shorter path was found.
		if (newPlayerMana < 173) { return minMana; } // you cannot cast more spells
		if (newPoisonTurns == 0) {
			
			mana = getMinManaVictory(false, newPlayerHp, newPlayerMana-173, newBossHp, newShieldTurns, 6, newRechargeTurns,
					maxManaSpend < 0 ? -1 : maxManaSpend-173);
			
			if (mana >= 0 && (mana+173 < minMana || minMana == -1)) {
				minMana = mana+173;
			}
			
		}

		if (minMana > 0  && (minMana < maxManaSpend || maxManaSpend < 0)) {
			maxManaSpend = minMana;
		}
		
		// If Recharge is not active, then try casting Recharge
		if (maxManaSpend >= 0 && maxManaSpend < 229) { return minMana; } // stop looking down this path because a shorter path was found.
		if (newPlayerMana < 229) { return minMana; } // you cannot cast more spells
		if (newRechargeTurns == 0) {
			
			mana = getMinManaVictory(false, newPlayerHp, newPlayerMana-229, newBossHp, newShieldTurns, newPoisonTurns, 5,
					maxManaSpend < 0 ? -1 : maxManaSpend-229);
			
			if (mana >= 0 && (mana+229 < minMana || minMana == -1)) {
				minMana = mana+229;
			}
			
		}
		
		// Return the minimum of casting all of the spells.
		return minMana;
		
	}
}
