package com.jonathandoolittle.foobar;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * My level 2 challenge from Google Foobar!
 *
 * <pre> 
 *    Solution.solution(0, 1)
 * </pre>
 *
 * @author Jonathan Doolittle
 * @version 0.1 - Sep 26, 2021
 *
 */
public class Solution {
		
	/**
	 * A node for solving the puzzle
	 * that contains a list of it's own 
	 * potential moves, as well as it's 
	 * row and offset.
	 * 
	 */
	public class Node {
		int row;
		int offset;
		List<Integer> solutions;
		
		/**
		 * @param position The index of this node
		 */
		public Node(int position) {
			row = position / 8;
			offset = position % 8;
			solutions = new LinkedList<>();
			generate();
		}
		
		private void generate() {
			// All of the potential offsets
			solutions.add(asPosition(row - 2, offset - 1));
			solutions.add(asPosition(row - 2, offset + 1));
			solutions.add(asPosition(row - 1, offset - 2));
			solutions.add(asPosition(row - 1, offset + 2));
			solutions.add(asPosition(row + 1, offset - 2));
			solutions.add(asPosition(row + 1, offset + 2));
			solutions.add(asPosition(row + 2, offset - 1));
			solutions.add(asPosition(row + 2, offset + 1));
			solutions.removeIf(x -> x == -1);
		}
		
		/**
		 * @param row
		 * @param offset
		 * @return True, if this is within an 8x8 grid
		 */
		private boolean isValid(int row, int offset) {
			return (row >= 0 && row < 8 && offset >= 0 && offset < 8);
		}
		
		/**
		 * @param row
		 * @param offset
		 * @return The index, or -1 if it is outside of the grid
		 */
		private int asPosition(int row, int offset) {
			return (isValid(row, offset)) ? (row * 8) + offset : -1;
		}	
	}
	
	
	// ******************************
	// Public methods
	// ******************************
	
	public static int solution(int src, int dest) {
		
		if(src == dest) {
			return 0;
		}
		
		// So I can use the Node class
		Solution solution = new Solution();
		
		// Build the grid and therefore a reference
		// to each move
		Node[] slots = new Node[64];
		
		for(int i = 0; i < slots.length; i++) {
			slots[i] = solution.new Node(i);
		}
		
		int fromSrc = findRoute(src, dest, slots);
		int fromDest = findRoute(dest, src, slots);
		
		return Math.min(fromSrc, fromDest);

	}

	// ******************************
	// Private methods
	// ******************************
	
	/**
	 * Finds the amount of moves it will take to get from src to dest,
	 * using the behavior defined in Node
	 * @param src The starting node index
	 * @param dest The final node index
	 * @param slots The node board
	 * @return The number of hops, or -1 if the move is impossible
	 */
	private static int findRoute(int src, int dest, Node[] slots) {
		
		/*
		 * LOGIC:
		 * All of the visited nodes get saved so there is
		 * no double crossing and a chance for an infinite loop.
		 * Since moves are the same both forward and backward,
		 * a path that lands on a tile already discovered is 
		 * by nature going to be slower than the path that originally
		 * discovered it, so we can end that branch of discovery early.
		 * 
		 * Since we need to keep track of how many hops it takes
		 * to get from src to dest, we also need to keep track of each
		 * options current path. I accomplished this by keeping a queue
		 * of all of the current paths, and appending to them. The size of
		 * the first path to reach desk is therefore the shortest moveset.
		 */
		
		List<Integer> visited = new LinkedList<>();
		Queue<List<Integer>> queue = new LinkedList<>();
		
		queue.add(Arrays.asList(src));
		
		while(!queue.isEmpty()) {
			// Pop off the top item
			List<Integer> currentPath = queue.poll();
			// Get the farthest along tile and look at it's options
			int current = currentPath.get(currentPath.size()-1);
			visited.add(current);
			for(int t : slots[current].solutions) {
				// Only look at undiscovered tiles
				if(!visited.contains(t)) {		
					// Keep the current path in tact, but spawn a new one
					List<Integer> newPath = new LinkedList<>(currentPath);
					newPath.add(t);
					queue.add(newPath);
					if(t == dest) {
						return newPath.size()-1;
					}
				}
			}	
				
		}
		// No route found
		return -1;
	}
	
	
	/**
	 * Test drive our solution
	 */
	public static void main(String[] args) {
		
		System.out.println(solution(19, 36));
		
	}
	
}


