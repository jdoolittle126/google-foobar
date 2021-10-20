package com.jonathandoolittle.foobar;

import java.util.LinkedList;
import java.util.List;

/**
 * 
 * My level 3 challenge from Google Foobar!
 * This challenge was really difficult, and took
 * me a lot of research to fully understand and 
 * grasp. I tried to document my logic here 
 * so others can learn!
 * 
 * <pre> 
 *    Solution.solution(200);
 * </pre>
 *
 * @author Jonathan Doolittle
 * @version 0.1 - Sep 28, 2021
 *
 */
public class Solution {
	
	// ******************************
	// Public methods
	// ******************************
	
	public static int solution(int n) {
		// We know you need at least 5 bricks
		// to make anything more than 1 staircase
		if(n < 5) {
			return 1;
		}
		
		// We shouldn't get anything above 200, but
		// if we do, don't waste time computing it
		if(n > 200) {
			return -1;
		}
		
		/* Distinct parts can be represented as the coefficient of 
		 * the nth term of the product of the sequence (1 + x^k) for 
		 * k = 1 -> inf.
		 * 
		 * Example: (1+x^1) * (1+x^2) * (1+x^3) * (1+x^4) * ...
		 * If we wanted to solve for the number of distinct partitions
		 * of 4, we would multiply out up to k = 4 like this (terms above):
		 * 
		 * 1 + x + x^2 + 2x^3 + 2x^4 + 2x^5 + 2x^6 + 2x^7 + x^8 + x^9 + x^10
		 * 0   1   2      3      4      5      6      7     8     9     10
		 * 
		 * If we look at term 4, the coefficient is 2, meaning there are 
		 * 2 distinct partitions (4, 1+3)
		 */
		
		List<Integer> coefficients = new LinkedList<>();
		
		// This is always 1, because the sequence has a constant
		coefficients.add(1);
		
		for(int k = 1; k <= n + 1; k++) {

			// Indicates the slice in the list of values that will be
			// just copied over (coefficients we have already calculated)
			int offset = coefficients.size() - k;
			
			coefficients.addAll(coefficients.subList(offset, offset+k));
			
			/*
			 * Remaining coefficients can be calculated by adding them to
			 * the previous coefficient that was k terms away. Think of it
			 * like this:
			 * 
			 * This is k = 3 expanded
			 * 1 + x + x^2 + 2x^3 + x^4 + x^5 + x^6
			 * 0   1   2      3     4     5     6
			 * 
			 * This is k = 4 expanded
			 * 1 + x + x^2 + 2x^3 + 2x^4 + 2x^5 + 2x^6 + 2x^7 + x^8 + x^9 + x^10
			 * 0   1   2      3      4      5      6      7     8     9     10
			 *  
			 * Moving from k = 3 to k = 4 (multiplying k=3 by (1+x^4)),
			 * coefficients 0-3 are not impacted, because they are < k and therefore
			 * cannot be divided to make k. Multiplying the x^4 term by the constant 
			 * gives a second x^2, multiplying x^4 * x gives another x^5 and so on.
			 * Each coefficient is directly impacted by the coefficient k units to the
			 * left of it. We apply this from right to left, so coefficient are not
			 * prematurely manipulated.
			 */
			for(int t = offset-1; t >= 0; t--) {
				coefficients.set(k+t, coefficients.get(k+t) + coefficients.get(t));
			}
			
			/*
			 * Since the we know the coefficient for the nth
			 * term won't be impacted by higher-power coefficients,
			 * I cut them out to improve the runtime of the algorithm
			 */
			if(coefficients.size() > n) {
				coefficients = coefficients.subList(0, n+1);
			}
		}
		
		// In each instance, the valid partition
		// p = p + 0 was accounted for. Since
		// stairs need at least 2 partitions, we
		// must account for this.
		return coefficients.get(n) - 1;
	}
	
	/**
	 * Test drive our solution
	 */
	public static void main(String[] args) {
		System.out.println(Solution.solution(6));
		System.out.println(Solution.solution(200));
	}
	
}