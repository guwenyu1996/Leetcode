package Greedy._621;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution {
    // Solution 1: greedy
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> map = new HashMap<>();
        for(char i: tasks)
            map.put(i, map.getOrDefault(i, 0) + 1);

        List<Integer> list = new ArrayList<>(map.values());
        list.sort((a, b) -> b - a);

        int maxFre = list.get(0);
        int idleTime = (maxFre - 1) * n;
        for(int i = 1; i < list.size(); i ++)
            idleTime -= Math.min(maxFre - 1, list.get(i));

        return tasks.length + Math.max(0, idleTime);
    }
}
