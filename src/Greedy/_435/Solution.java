package Greedy._435;

import java.util.Arrays;

public class Solution {
    // Solution 1: greedy
    public int eraseOverlapIntervals(int[][] intervals) {
        if(intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (int[] a, int[] b) -> a[1] - b[1]);
        int count = 0;
        int end = intervals[0][1];
        for(int i = 1; i < intervals.length; i ++){
            if(intervals[i][0] >= end)
                end = intervals[i][1];
            else
                count ++;
        }
        return count;
    }

    // Solution 2: dynamic programming
    public int eraseOverlapIntervals1(int[][] intervals) {
        if(intervals.length  == 0)
            return 0;

        Arrays.sort(intervals, (int[] a, int[] b) -> (a[0] - b[0]));
        int[] dp = new int[intervals.length];
        dp[0] = 1;

        for(int i = 1; i < intervals.length; i ++){
            int max = 0;
            for(int j = 0; j < i; j ++){
                if(intervals[i][0] >= intervals[j][1])
                    max = Math.max(max, dp[j]);
            }
            dp[i] = max + 1;
        }

        return intervals.length - dp[intervals.length - 1];
    }

}
