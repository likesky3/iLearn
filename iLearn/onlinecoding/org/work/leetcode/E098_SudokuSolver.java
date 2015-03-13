package org.work.leetcode;

/*1 每个backtracking的题目，最好都有独立判断isValid的程序，这样架构清楚。
 * 同时，valid判断函数在这里可以稍微研究一下。
 * 只要当前要判断的位置上的数值和本行没有重复，本列没有重复，九宫格没有重复就可以。一旦重复立即返回，减少判断次数。

2 backtracking的递归函数，怎么能没有返回值呢？！
因为要判断递归的方案正确与否，所以这里的递归一定是有返回值的
（除非是combination那种没有正确错误概念的backtracking）！

3 可以考虑“先放置，再判断”的方案。比如这里，首先判断当前位置是否为空，如果为空，那么放置一个元素，
检查它是否正确。如果正确，就继续进行下面的递归（也就是第29行 isValid&&solveSudoku的作用）。
当函数返回错误之后，将刚刚的数值变为空，再进行下一次尝试即可。

4 所有的方案（k从1到9）完毕之后，应该返回错误，这个是不应该被忽略的。

5 最后一点需要注意的是，当i,j循环完毕之后，第36行应该返回true。
这里实际上是最终/最底层的一次循环，表明已经解出了sudoku，返回true！切记切记，最终情况！
*/
public class E098_SudokuSolver {

	public static void main(String[] args) {

	}
	
	public void solveSudoku(char[][] board) {
		doSolveSudoku(board);
	}
	
	public boolean doSolveSudoku(char[][] board) {
        // Start typing your Java solution below
        // DO NOT write main() function
		for(int i = 0; i < 9; i++){
			for(int j = 0; j < 9; j++){
				if(board[i][j] == '.'){
					for(int k = 1; k < 10; k++){
						board[i][j] = (char) ('0' + k);
						if(isValid(board, i, j) && doSolveSudoku(board))
							return true;
						else{
							board[i][j] = '.';
						}
					}
					return false;
				}
			}
		}
		
		return true;
    }
	
	//验证给定的坐标点上的数字是否和本行，本列，所在九宫格中已填的数字重复
	private boolean isValid(char[][] board, int x, int y){
		//检测所在行
		for(int i = 0; i < 9; i++){
			if(i != x && board[i][y] == board[x][y])
				return false;
		}
		//检测所在列
		for(int j = 0; j < 9; j++){
			if(j != y && board[x][j] == board[x][y])
				return false;
		}
		
		//检测所在九宫格
		int ibase = (x / 3) * 3;
		int jbase = (y / 3) * 3;
		for(int i = ibase; i < ibase + 3; i++ ){
			for(int j = jbase; j < jbase + 3; j++){
				if(i != x && j != y && board[i][j] == board[x][y])
					return false;
			}
		}
		return true;
	}

}
