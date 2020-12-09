package Array._062;

public class Solution {
    // Solution 0: backtrack, time limit exceeds
    public int uniquePaths(int m, int n) {
        return helper(m, n, 1, 1, 0);
    }

    public int helper(int m, int n, int row, int col, int result){
        if(row == m && col == n)
            return result + 1;
        int x = 0, y = 0;

        if(row < m)
            x = helper(m, n, row + 1, col, result);
        if(col < n)
            y = helper(m, n, row, col + 1, result);

        return x + y;
    }

    // Solution 1: math
    public int uniquePaths1(int m, int n) {
        int x = m - 1, y = n - 1;
        int min = Math.min(x, y);

        long result = 1;
        for(int i = 0; i < min; i ++)
            result *= (x + y - i);
        for(int i = 1; i <= min; i ++)
            result /= i;

        return (int) result;
    }

    // Solution 2: dynamic programming
    public int uniquePaths2(int m, int n) {
        int[][] dp = new int[m][n];

        for(int i = 0; i < m; i ++)
            dp[i][0] = 1;
        for(int i = 0; i < n; i ++)
            dp[0][i] = 1;

        for(int i = 1; i < m; i ++){
            for(int j = 1; j < n; j ++)
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
        }

        return dp[m-1][n-1];
    }

    // Solution 3: simplified dynamic programming
    public int uniquePaths3(int m, int n) {
        int[][] dp = new int[2][n];

        for(int i = 0; i < n; i ++)
            dp[0][i] = 1;

        for(int i = 1; i < m; i ++){
            dp[1][0] = 1;
            for(int j = 1; j < n; j ++)
                dp[1][j] = dp[0][j] + dp[1][j-1];
            dp[0] = dp[1];
        }

        return dp[0][n-1];
    }

    // Solution 4: simplified dynamic programming
    public int uniquePaths4(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;

        for(int i = 0; i < m; i ++){
            for(int j = 1; j < n; j ++)
                dp[j] = dp[j] + dp[j-1];
        }

        return dp[n-1];
    }
}
