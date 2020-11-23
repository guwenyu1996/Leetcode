package DFS._200;

public class Solution {
    // Solution 1: dfs
    public int numIslands(char[][] grid) {
        int count = 0;
        for(int row = 0; row < grid.length; row ++){
            for(int col = 0; col < grid[0].length; col ++){
                if(grid[row][col] == '1'){
                    dfs(grid, row, col);
                    count ++;
                }

            }
        }
        return count;
    }

    public void dfs(char[][] grid, int row, int col){
        grid[row][col] = '2';

        if(row + 1 < grid.length && grid[row + 1][col] == '1')
            dfs(grid, row + 1, col);
        if(row - 1 >= 0 && grid[row - 1][col] == '1')
            dfs(grid, row - 1, col);
        if(col + 1 < grid[0].length && grid[row][col + 1] == '1')
            dfs(grid, row, col + 1);
        if(col - 1 >= 0 && grid[row][col - 1] == '1')
            dfs(grid, row, col - 1);
    }





}
