package Greedy._452;

import java.util.Arrays;

public class Solution {
    // Solution 1: greedy by start
    public int findMinArrowShots(int[][] points) {
        if(points.length == 0)
            return 0;

        Arrays.sort(points, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));
        int end = points[0][1];
        int count = 1;
        for(int i = 1; i < points.length; i ++){
            if(points[i][0] <= end)
                end = Math.min(end, points[i][1]);
            else{
                count ++;
                end = points[i][1];
            }
        }
        return count;
    }

    // Solution 2: greedy by end
    public int findMinArrowShots1(int[][] points) {
        if(points.length == 0)
            return 0;

        Arrays.sort(points, (int[] a, int[] b) -> Integer.compare(a[1], b[1]));
        int end = points[0][1];
        int count = 1;
        for(int i = 1; i < points.length; i ++){
            if(points[i][0] > end){
                count ++;
                end = points[i][1];
            }
        }
        return count;
    }
}
