package org.work.basic;

public class SudokuSolver {

	public static void main(String[] args) {
		SudokuSolver obj = new SudokuSolver();
		char[] r0 = new String("..9748...").toCharArray();
		// for(int i = 0; i < 9; i++)
		// System.out.print(r0[i]);
		char[] r1 = new String("7........").toCharArray();
		char[] r2 = new String(".2.1.9...").toCharArray();
		char[] r3 = new String("..7...24.").toCharArray();
		char[] r4 = new String(".64.1.59.").toCharArray();
		char[] r5 = new String(".98...3..").toCharArray();
		char[] r6 = new String("...8.3.2.").toCharArray();
		char[] r7 = new String("........6").toCharArray();
		char[] r8 = new String("...2759..").toCharArray();

		char[][] board = {r0, r1, r2, r3, r4, r5, r6, r7, r8};

		obj.solveSudoku(board);

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(board[i][j] + "\t");
			}
			System.out.println();
		}

	}

	boolean[][] rows = null;
	boolean[][] cols = null;
	boolean[][] blocks = null;

	public void solveSudoku(char[][] board) {
		rows = new boolean[9][9];
		cols = new boolean[9][9];
		blocks = new boolean[9][9];
		 initialWork(board);
//		solve2(board, 0);
		 solve(board, 0, 0);
	}
	
	private void print(boolean[][] flag){
		int m = flag.length;
		int n = flag[0].length;
		for(int i = 0; i < m; i++){
			for(int j = 0; j < n; j++){
				System.out.print(flag[i][j] + "\t\t");
			}
			System.out.println();
		}
		
	}
	public boolean solve2(char[][] board, int index) {
		//skip filled cell
		while (index < 81 && board[index / 9][index % 9] != '.')
			index++;
		if (index == 81) //whole board has been filled
			return true;
		
		int row = index / 9;
		int col = index % 9;
		int blockID = row - row % 3 + col / 3;

		//try to fill the cell with 1 - 9 
		for (int c = 0; c < 9; c++) {
			if (rows[row][c] || cols[col][c] || blocks[blockID][c])
				continue;
			board[row][col] = (char) (c + '1');
			rows[row][c] = cols[col][c] = blocks[blockID][c] = true;
			if (solve2(board, index + 1)) {
				return true;
			}
			board[row][col] = '.';
			rows[row][c] = cols[col][c] = blocks[blockID][c] = false;

		}

		return false;
	}

	public boolean solve(char[][] board, int curri, int currj) {
		int nexti = curri;
		int nextj = currj;
		while (nexti < 9 && board[nexti][nextj] != '.') {
			if (nextj == 8) {
				nexti = nexti + 1;
				nextj = 0;
			} else
				nextj++;
		}
		if (nexti == 9 && nextj == 0)
			return true;

		curri = nexti;
		currj = nextj;
		int blockID = curri - curri % 3 + currj / 3;
		int c = board[curri][currj] - '1';

		for (c = 0; c < 9; c++) {
//			if (rows[curri][c]  ||  cols[currj][c] || blocks[blockID][c])
//				continue;
//			if (rows[curri][c] == cols[currj][c] == blocks[blockID][c] == false) {
			if (!rows[curri][c] && !cols[currj][c] && !blocks[blockID][c]) {
				board[curri][currj] = (char) (c + '1');
				rows[curri][c] = cols[currj][c] = blocks[blockID][c] = true;
				// set next cell
				if (solve(board, curri, currj)) {
					return true;
				}
				// restore
				board[curri][currj] = '.';
				rows[curri][c] = cols[currj][c] = blocks[blockID][c] = false;
				
			}
		}

		return false;
	}

	public void initialWork(char[][] board) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] != '.') {
					int c = board[i][j] - '1';
					rows[i][c] = cols[j][c] = blocks[i - i % 3 + j / 3][c] = true;
				}
			}
		}
	}
}
