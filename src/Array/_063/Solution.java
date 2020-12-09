package Array._063;

public class Solution {
    // My solution: dynamic programming
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length, col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];

        dp[0][0] = (obstacleGrid[0][0] == 0)? 1: 0;
        for(int i = 1; i < col; i ++){
            if(obstacleGrid[0][i] == 0 && dp[0][i-1] == 1)
                dp[0][i] = 1;
        }

        for(int i = 1; i < row; i ++){
            if(obstacleGrid[i][0] == 0 && dp[i-1][0] == 1)
                dp[i][0] = 1;

            for(int j = 1; j < col; j ++){
                if(obstacleGrid[i][j] == 0)
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }

        return dp[row-1][col-1];
    }
}
