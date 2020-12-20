package DynamicProgramming._221;

public class Solution {
    // Solution 1: dynamic programming
    public int maximalSquare(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row][col];
        int flag = 0;

        for(int i = 0; i < row; i ++){
            dp[i][0] = matrix[i][0] - '0';
            flag = Math.max(flag, dp[i][0]);
        }
        for(int i = 0; i < col; i ++){
            dp[0][i] = matrix[0][i] - '0';
            flag = Math.max(flag, dp[0][i]);
        }

        for(int i = 1; i < row; i ++){
            for(int j = 1; j < col; j ++){
                if(matrix[i][j] == '1'){
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    flag = Math.max(flag, dp[i][j]);
                }
            }
        }
        return flag * flag;
    }

    // Solution 1: simplified
    public int maximalSquare1(char[][] matrix) {
        int row = matrix.length, col = matrix[0].length;
        int[][] dp = new int[row + 1][col + 1];
        int flag = 0;

        for(int i = 0; i < row; i ++){
            for(int j = 0; j < col; j ++){
                if(matrix[i][j] == '1'){
                    dp[i+1][j+1] = Math.min(Math.min(dp[i][j+1], dp[i+1][j]), dp[i][j]) + 1;
                    flag = Math.max(flag, dp[i+1][j+1]);
                }
            }
        }
        return flag * flag;
    }

}
