package Greedy._253;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    // Solution 1: sort + Priority Queue
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0)
            return 0;

        Arrays.sort(intervals, (int[] a, int[] b) -> Integer.compare(a[0], b[0]));
        PriorityQueue<Integer> queue = new PriorityQueue();

        for(int i = 0; i < intervals.length; i ++){
            if(!queue.isEmpty() && queue.peek() <= intervals[i][0])
                queue.poll();

            queue.add(intervals[i][1]);
        }
        return queue.size();
    }

    // Solution 2: sweep line
    public int minMeetingRooms2(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for(int i = 0; i < intervals.length; i ++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);
        int count = 0, p2 = 0;

        for(int p1 = 0; p1 < intervals.length; p1 ++){
            if(start[p1] < end[p2])
                count ++;
            else
                p2 ++;
        }
        return count;
    }
}
