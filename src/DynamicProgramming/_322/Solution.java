package DynamicProgramming._322;

public class Solution {
    // my solution
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];

        for(int money = 1; money <= amount; money ++){
            int temp = Integer.MAX_VALUE;

            for(int coin: coins){
                if(money >= coin && dp[money - coin] >= 0)
                    temp = Math.min(temp, dp[money - coin] + 1);
            }

            if(temp != Integer.MAX_VALUE)
                dp[money] = temp;
            else
                dp[money] = -1;
        }
        return dp[amount];
    }
}
