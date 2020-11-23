package DynamicProgramming._53;

public class Solution {
    // Solution 1: greedy
    public int maxSubArray(int[] nums) {
        int currSum = nums[0], maxSum = nums[0];

        for(int i = 1; i < nums.length; i ++){
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }

        return maxSum;
    }

    // Solution 2:
}
