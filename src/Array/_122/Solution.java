package Array._122;

public class Solution {
    // Solution 1: peak - valley
    public int maxProfit(int[] prices) {
        int i = 0;
        int valley = prices[0], peak = prices[0];
        int result = 0;

        while(i < prices.length - 1){
            while(i + 1 < prices.length && prices[i] >= prices[i + 1])
                i ++;
            valley = prices[i];
            while(i + 1 < prices.length && prices[i] < prices[i + 1])
                i ++;
            peak = prices[i];
            result += (peak - valley);
        }

        return result;
    }

    // Solution 2: simplify solution 1
    public int maxProfit1(int[] prices) {
        int sum = 0;
        for(int i = 0; i < prices.length - 1; i ++){
            sum += Math.max(0, prices[i+1] - prices[i]);
        }
        return sum;
    }
}
