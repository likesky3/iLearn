package org.work.leetcode;

import java.util.HashMap;

public class E095_IsValidSudoku {
	public boolean isValidSudoku2(char[][] board) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		// check each row
		for (int row = 0; row < 9; row++) {
			for (int col = 0; col < 9; col++) {
				Character c = board[row][col];
				if (c != '.' && map.containsKey(c)) {
					return false;
				} else {
					map.put(c, 1);
				}
			}
		}

		// check each column
		map.clear();
		for (int col = 0; col < 9; col++) {
			for (int row = 0; row < 9; row++) {
				Character c = board[row][col];
				if (c != '.' && map.containsKey(c)) {
					return false;
				} else {
					map.put(c, 1);
				}
			}
		}

		// check each 3*3 area

		for (int row = 0; row < 7; row += 3) {
			for (int col = 0; col < 7; col += 3) {
				map.clear();
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						Character c1 = board[row + i][col + j];
						if (c1 != '.' && map.containsKey(c1)) {
							return false;
						} else {
							map.put(c1, 1);
						}
					}
				}
			}
		}

		return true;
	}

	public boolean isValidSudoku(char[][] board) {
		int[] dup = new int[10];

		// check each row
		for (int row = 0; row < 9; row++) {
			for (int i = 0; i < 10; i++)
				dup[i] = 0;
			for (int col = 0; col < 9; col++) {
				Character c = board[row][col];
				if (c == '.'){
					dup[0]++;
					continue;
				}
				int hash = c - '0';
				if (dup[hash] == 1) {
					return false;
				} else {
					++dup[hash];
				}
			}
		}

		// check each column
		for (int col = 0; col < 9; col++) {
			for (int i = 0; i < 10; i++)
				dup[i] = 0;
			for (int row = 0; row < 9; row++) {
				Character c = board[row][col];
				if (c == '.'){
					dup[0]++;
					continue;
				}
				int hash = c - '0';
				if (dup[hash] == 1) {
					return false;
				} else {
					++dup[hash];
				}
			}
		}

		// check each 3*3 area

		for (int row = 0; row < 7; row += 3) {
			for (int col = 0; col < 7; col += 3) {
				for (int i = 0; i < 10; i++)
					dup[i] = 0;
				for (int i = 0; i < 3; i++) {
					for (int j = 0; j < 3; j++) {
						Character c = board[row + i][col + j];
						if (c == '.'){
							dup[0]++;
							continue;
						}
						int hash = c - '0';
						if (dup[hash] == 1) {
							return false;
						} else {
							++dup[hash];
						}
					}
				}
			}
		}

		return true;
	}
}
