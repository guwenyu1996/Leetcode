package Greedy._252;

import java.util.Arrays;

public class Solution {
    // Solution 1: brute force
    public boolean canAttendMeetings(int[][] intervals) {
        for(int i = 0; i < intervals.length; i ++){
            for(int j = i + 1; j < intervals.length; j ++){
                // method 1
                if((intervals[i][0] <= intervals[j][0] && intervals[i][1] > intervals[j][0]) ||
                        (intervals[j][0] <= intervals[i][0] && intervals[j][1] > intervals[i][0]))
                    return false;

                // method 2: min(end) > max(start)
                if(Math.min(intervals[i][1], intervals[j][1]) >
                        Math.max(intervals[i][0], intervals[j][0]))
                    return false;
            }
        }
        return true;
    }

    // Solution 2: sort
    public boolean canAttendMeetings1(int[][] intervals) {
        Arrays.sort(intervals, (int[] a, int[] b) -> Integer.compare(a[1], b[1]));

        for(int i = 1; i < intervals.length; i ++){
            if(intervals[i][0] < intervals[i-1][1])
                return false;
        }
        return true;
    }
}
