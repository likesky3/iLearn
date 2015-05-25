package org.work.basic.graph;

public class NumberOfIslands {
    // DFS flood fill, O(m * n)
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int num = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] != '1')
                    continue;
                num++;
                dfs(grid, i, j, rows, cols);
            }
        }
        return num;
    }
    private void dfs(char[][] grid, int i, int j, int rows, int cols) {
        if (i < 0 || i >= rows || j < 0 || j >= cols)
            return;
        if (grid[i][j] == '1') {
            grid[i][j] = '2';
            dfs(grid, i - 1, j, rows, cols);
            dfs(grid, i, j + 1, rows, cols);
            dfs(grid, i + 1, j, rows, cols);
            dfs(grid, i, j - 1, rows, cols);
        }
    }
}
