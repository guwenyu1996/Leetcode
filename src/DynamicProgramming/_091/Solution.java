package DynamicProgramming._091;

public class Solution {
    // Solution 1: dynamic programming
    public int numDecodings(String s) {
        if(s.length() == 0)
            return 0;
        if(s.charAt(0) == '0')
            return 0;

        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= s.length(); i ++){
            int temp = Integer.parseInt(s.substring(i - 2, i));
            if(temp >= 10 && temp <= 26)
                dp[i] += dp[i-2];

            if(s.charAt(i-1) != '0')
                dp[i] += dp[i-1];
        }

        return dp[s.length()];
    }
}
