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

    // Solution 2: math
    public int leastInterval2(char[] tasks, int n) {
        int[] frequency = new int[26];
        for(char c: tasks)
            frequency[c-'A'] ++;

        int maxFre = 0;
        for(int i: frequency)
            maxFre = Math.max(i, maxFre);

        int count = 0;
        for(int i: frequency){
            if(i == maxFre)
                count++;
        }

        return Math.max((n+1)*(maxFre-1)+count, tasks.length);
    }
}
