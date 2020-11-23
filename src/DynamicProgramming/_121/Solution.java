package DynamicProgramming._121;

public class Solution {
    // Solution 1: dynamic programming
    public int maxProfit(int[] prices) {
        int diff = 0, small = Integer.MAX_VALUE;

        for(int i = 0; i < prices.length; i ++){
            if(prices[i] < small)
                small = prices[i];
            else if(prices[i] - small > diff)
                diff = prices[i] - small;
        }
        return diff;
    }
}
