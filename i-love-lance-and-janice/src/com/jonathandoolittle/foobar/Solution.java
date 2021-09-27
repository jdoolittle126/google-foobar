package com.jonathandoolittle.foobar;

/**
 * 
 * My level 1 challenge from Google Foobar!
 * Takes an encoded string, where only the lower
 * case letters have been swapped. Lower case letters 
 * are swapped based on the pattern [a..z] = [z..a]
 * 
 * <pre> 
 *    Solution.solution("vmxibkgrlm");
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
	 * Solves for the encoded string, in order to 
	 * bust some of my lazy minion co-workers!
	 * @param encoded The encoded minion message
	 * @return The plain-text string
	 */
	public static String solution(String encoded) {
		
		// Convert the string to an array of characters we can manipulate
		char[] encodedCharacters = encoded.toCharArray();
		
		// Iterate through the array, and only manipulate the characters
		// that are lower case, as defined by the problem statement
		for(int i = 0; i < encodedCharacters.length; i++) {
			if(Character.isLowerCase(encodedCharacters[i])) {
				// Swap lower case characters with their counterpart
				encodedCharacters[i] = swap(encodedCharacters[i]);
			}
		}
		
		return String.valueOf(encodedCharacters);
	}

	// ******************************
	// Private methods
	// ******************************
	
	/**
	 * Swaps the value of a characters based on the pattern
	 * [a..z] = [z..a]
	 * @param c The character to swap
	 * @return The corresponding value
	 */
	private static char swap(char c) {
		int offset = c - 'a';
		return (char) ('z' - offset);
	}
	
	/**
	 * Test drive our solution
	 */
	public static void main(String[] args) {
		System.out.println(Solution.solution("Yvzs! I xzm'g yvorvev Lzmxv olhg srh qly zg gsv xlolmb!!"));
		System.out.println(solution("wrw blf hvv ozhg mrtsg'h vkrhlwv?"));
	}
	
}