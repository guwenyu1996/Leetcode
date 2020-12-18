package DynamicProgramming._070;

public class Solution {
    // My solution, solution 1: dynamic programming
    public int climbStairs(int n) {
        if(n == 1)
            return 1;

        int[] nums = new int[n + 1];
        nums[0] = 1;
        nums[1] = 1;
        for(int i = 2; i <= n; i ++)
            nums[i] = nums[i-1] + nums[i - 2];

        return nums[n];
    }

    // Solution 2
    public int climbStairs2(int n) {
        if(n == 1)
            return 1;

        int first = 1;
        int second = 1;
        for(int i = 2; i <= n; i ++){
            int sum = first + second;
            first = second;
            second = sum;
        }
        return second;
    }
}
