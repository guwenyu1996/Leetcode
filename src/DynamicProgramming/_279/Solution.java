package DynamicProgramming._279;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    // My solution: dynamic programming
    public int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for(int i = 1; i <= n; i ++){
            if(i * i <= n)
                squares.add(i * i);
            else
                break;
        }

        int[] dp = new int[n + 1];
        for(int i = 1; i <= n; i ++){
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < squares.size(); j ++){
                int temp = squares.get(j);
                if(temp > i)
                    break;
                min = Math.min(min, dp[i-temp] + 1);
            }
            dp[i] = min;
        }

        return dp[n];
    }

    // Solution 2: greedy
    Set<Integer> set = new HashSet<>();

    public int numSquares2(int n) {
        for(int i = 1; i * i <= n; i ++)
            set.add(i*i);

        for(int i = 1; i <= n; i ++){
            if(helper(n, i))
                return i;
        }
        return n;
    }

    public boolean helper(int n, int count){
        if(count == 1){
            return set.contains(n);
        }

        for(Integer num: set){
            if(num > n)
                continue;

            if(helper(n-num, count - 1))
                return true;
        }
        return false;
    }
}
