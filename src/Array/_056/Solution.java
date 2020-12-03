package Array._056;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]);

        for(int i = 1; i < intervals.length; i ++){
            int[] last = result.get(result.size() - 1);
            if(last[1] < intervals[i][0])
                result.add(intervals[i]);
            else
                last[1] = Math.max(last[1], intervals[i][1]);
        }

        return result.toArray(new int[result.size()][]);
    }
}
