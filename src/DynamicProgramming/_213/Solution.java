package DynamicProgramming._213;

public class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        else if(nums.length == 1)
            return nums[0];

        int[][] dp = new int[2][nums.length];
        dp[0][0] = nums[0];
        dp[0][1] = Math.max(nums[0], nums[1]);
        dp[1][1] = nums[1];

        for(int i = 2; i < nums.length - 1; i ++){
            dp[0][i] = Math.max(dp[0][i-2] + nums[i], dp[0][i-1]);
            dp[1][i] = Math.max(dp[1][i-2] + nums[i], dp[1][i-1]);
        }

        if(nums.length >= 3){
            dp[0][nums.length-1] = dp[0][nums.length-2];
            dp[1][nums.length-1] = Math.max(dp[1][nums.length - 3] + nums[nums.length-1], dp[1][nums.length-2]);
        }

        return Math.max(dp[0][nums.length-1], dp[1][nums.length-1]);
    }

    // Solution 1: dynamic programming
    public int rob1(int[] nums) {
        if(nums.length == 0)
            return 0;
        else if(nums.length == 1)
            return nums[0];

        int t1 = helper(nums, 0, nums.length - 2);
        int t2 = helper(nums, 1, nums.length - 1);

        return Math.max(t1, t2);
    }

    public int helper(int[] nums, int start, int end){
        if(start == end)
            return nums[start];

        int rob = nums[start], notRob = 0;
        for(int i = start + 1; i <= end; i ++){
            int temp = rob;
            rob = notRob + nums[i];
            notRob = Math.max(temp, notRob);
        }

        return Math.max(rob, notRob);
    }
}
