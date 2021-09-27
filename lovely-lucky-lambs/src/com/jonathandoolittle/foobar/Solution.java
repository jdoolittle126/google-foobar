package com.jonathandoolittle.foobar;

/**
 * 
 * My level 2 challenge from Google Foobar!
 * 
 * Takes a value of LAMBS, and splits it among
 * henchman in a way that keeps them from revolting.
 * There are two ways to do this, the most stingy way,
 * and the most generous way. The solution function
 * calculates the difference in henchman paid from 
 * these two approaches.
 *
 * <pre> 
 *    Solution.solution(143);
 * </pre>
 *
 * @author Jonathan Doolittle
 * @version 0.1 - Sep 26, 2021
 *
 */
public class Solution {
	
	// ******************************
	// Public methods
	// ******************************
	
	/**
	 * Returns the difference in henchman being paid,
	 * looking at the most generous and most stingy
	 * option!
	 * @param total_lambs The max LAMBS to distribute
	 * @return The difference between the two options
	 */
	public static int solution(int total_lambs) {
		return stingy(total_lambs) - generous(total_lambs);
	}

	// ******************************
	// Private methods
	// ******************************
	
	/**
	 * Calculates the number of henchman we can 
	 * pay using LAMBS if we are feeling generous!
	 * @param total_lambs The max LAMBS to distribute
	 * @return The number of henchmen to be paid
	 */
	private static int generous(int total_lambs) {
		
		int total = 0;
		int henchman = 0;
		
		while(total < total_lambs) {
			// Each henchman can be paid 
			// double the previous henchman's
			// pay! To avoid using Math.pow,
			// I simply use bitwise
			total += (1 << henchman++);
		}
		
		// If it's not an even match, that means
		// we went over, therefore take one away!
		if(total != total_lambs) {
			henchman--;
		}
		
		return henchman;
	}
	
	/**
	 * Calculates the number of henchman we can 
	 * pay using LAMBS if we are as stringy as possible!
	 * @param total_lambs The max LAMBS to distribute
	 * @return The number of henchmen to be paid
	 */
	private static int stingy(int total_lambs) {
		// Keep track of fib. numbers. 
		// Index 0 & 1 act are the past 2 values,
		// and index 2 is our temp slot for swapping
		int[] fib = {0, 1, 0};
		
		int total = 0;
		int henchman = 0;
		
		// The first two henchman will actually be paid 1 LAMB
		// however, since we are looking for total to exceed
		// total lamb we can just leave out the first henchman,
		// so the extra henchman++ call is accounted for
		while(total < total_lambs) {
			henchman++;
			// Calculate the next fib. number and store it
			fib[2] = fib[0] + fib[1];
			// Shift the past two values down
			fib[0] = fib[1];
			fib[1] = fib[2];
			total += fib[2];
		}
		
		return henchman;
	}
	
	
	/**
	 * Test drive our solution
	 */
	public static void main(String[] args) {
		System.out.println(solution(143));
	}
	
}
