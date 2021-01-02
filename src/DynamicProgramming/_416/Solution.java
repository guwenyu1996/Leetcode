package DynamicProgramming._416;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // My solution, memory limit reach
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i: nums)
            sum += i;

        if(sum % 2 == 1)
            return false;
        sum = sum / 2;

        List<Integer> result = new ArrayList<>();
        result.add(0);

        for(int i = 0; i < nums.length; i ++){
            int size = result.size();

            for(int j = 0; j < size; j ++){
                int temp = result.get(j);
                if(nums[i] + temp == sum)
                    return true;
                else
                    result.add(nums[i] + temp);
            }
        }

        return false;
    }

    // Solution 1: brute force recursion
    public boolean canPartition1(int[] nums) {
        int sum = 0;
        for(int i: nums)
            sum += i;

        if(sum % 2 == 1)
            return false;
        sum = sum / 2;

        return helper(nums, 0, sum);
    }

    public boolean helper(int[] nums, int index, int target){
        if(target < 0 || index >= nums.length)
            return false;

        if(target == 0)
            return true;

        return helper(nums, index + 1, target) || helper(nums, index + 1, target - nums[index]);
    }

    // Solution 2: sol 1 + memo
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int i: nums)
            sum += i;

        if(sum % 2 == 1)
            return false;
        sum = sum / 2;

        Boolean[][] dp = new Boolean[nums.length][sum+1];

        return helper(nums, 0, sum, dp);
    }

    public boolean helper(int[] nums, int index, int target, Boolean[][] dp){
        if(target < 0 || index >= nums.length)
            return false;

        if(target == 0){
            dp[index][target] = true;
            return true;
        }

        if(dp[index][target] != null)
            return dp[index][target];

        dp[index][target] = helper(nums, index + 1, target, dp) || helper(nums, index + 1, target - nums[index], dp);

        return dp[index][target];
    }

    // Solution 3: bottom up dynamic programming
    public boolean canPartition3(int[] nums) {
        int sum = 0;
        for(int i: nums)
            sum += i;

        if(sum % 2 == 1)
            return false;
        sum = sum / 2;

        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        dp[0][0] = true;

        for(int index = 0; index < nums.length; index ++){
            for(int j = 0; j <= sum; j ++){
                if(dp[index][j] == true){
                    dp[index + 1][j] = true;

                    if(j + nums[index] <= sum)
                        dp[index + 1][j + nums[index]] = true;
                }
            }
        }

        return dp[nums.length][sum];
    }

    // Solution 4: simplify sol 3
    public boolean canPartition4(int[] nums) {
        int sum = 0;
        for(int i: nums)
            sum += i;

        if(sum % 2 == 1)
            return false;
        sum = sum / 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        for(int index = 0; index < nums.length; index ++){
            boolean[] temp = new boolean[sum + 1];
            for(int j = 0; j <= sum; j ++){
                if(dp[j] == true){
                    temp[j] = true;
                    if(j + nums[index] <= sum)
                        temp[j + nums[index]] = true;
                }
            }
            dp = temp;
        }

        return dp[sum];
    }
}
