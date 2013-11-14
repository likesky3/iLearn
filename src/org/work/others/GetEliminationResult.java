package org.work.others;

import org.junit.experimental.max.MaxCore;

public class GetEliminationResult {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String[] matrix = {"1 1 2 4", "2 3 3 3", "2 2 3 5", "3 3 3 3", "1 2 3 4"};
		GetEliminationResult(matrix, 4, 5, 3, 3);
	}

	static String[] GetEliminationResult(String[] matrixString,
			int numOfColumn, int numOfRow, int hitXIndex, int hitYIndex) {
		//convert matrixString to char[][] board
		char[][] board = new char[numOfRow][numOfColumn];
		char[] tmp;
		for(int i = 0; i < numOfRow; i++){
			tmp = matrixString[i].toCharArray();
			int k = 0;
			for(int j = 0; j < numOfColumn; j++){
				board[i][j] = tmp[k];
				k += 2;
			}
		}
		
		//DFS to clear
		dfs(board, hitXIndex, hitYIndex, board[hitXIndex][hitYIndex]);
		
		//adjust board column by column according to gravity law
		for(int j = 0; j < numOfColumn; j++){
			for(int i = 0; i < numOfRow; i++){
				char curr = board[i][j];
				if(curr == '0'){
					int k = i - 1;
					for(; k >= 0 && board[k][j] != '0'; k--)
						board[k + 1][j] = board[k][j];
					board[k + 1][j] = curr;  
				}
			}
		}
		
		//convert to output
		StringBuilder line = new StringBuilder();
		for(int i = 0; i < numOfRow; i++){
			line.delete(0, line.length());
			for(int j = 0; j < numOfColumn; j++){
				line.append(board[i][j]);
				line.append(' ');
			}
			line.deleteCharAt(line.length() - 1);
			matrixString[i] = line.toString();
			System.out.println(matrixString[i]);
		}
		return matrixString;
	}
	
	private static int[] dx = {-1,0,0,1};  
	private static int[] dy = {0,-1,1,0};  
	public static void dfs(char[][] board, int x, int y, char hitNum){
		board[x][y] = '0';
		int x2 = 0, y2 = 0;
		int rows = board.length;
		int cols = board[0].length; 
		for(int i = 0; i < 4; i++){
			x2 = x + dx[i];
			y2 = y + dy[i];
			if ((x2 >= 0 && x2 < rows && y2 >= 0 && y2 < cols) && board[x2][y2] == hitNum)
				dfs(board, x2, y2, hitNum);
		}
	}

}
