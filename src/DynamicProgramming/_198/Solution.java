package DynamicProgramming._198;

public class Solution {
    // My solution : dynamic programming
    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;

        int[][] dp = new int[2][nums.length];

        dp[0][0] = nums[0];

        for(int i = 1; i < nums.length; i ++){
            dp[0][i] = dp[1][i-1] + nums[i];
            dp[1][i] = Math.max(dp[0][i-1], dp[1][i-1]);
        }

        return Math.max(dp[0][nums.length - 1], dp[1][nums.length - 1]);
    }

    public int rob1(int[] nums) {
        if(nums.length == 0)
            return 0;

        int notRob = 0, rob = nums[0];
        int lastRob = 0;

        for(int i = 1; i < nums.length; i ++){
            lastRob = rob;
            rob = notRob + nums[i];
            notRob = Math.max(lastRob, notRob);
        }

        return Math.max(rob, notRob);
    }

    public int rob2(int[] nums) {
        if(nums.length == 0)
            return 0;
        else if(nums.length == 1)
            return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i = 2; i < nums.length; i ++)
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);

        return dp[nums.length - 1];
    }
}
