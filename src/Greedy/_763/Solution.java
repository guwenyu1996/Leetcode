package Greedy._763;

import java.util.*;

public class Solution {
    // My solution
    public List<Integer> partitionLabels(String S) {
        Map<Character, Integer> map = new HashMap<>();
        for(char c: S.toCharArray())
            map.put(c, map.getOrDefault(c, 0) + 1);

        List<Integer> result = new ArrayList<>();
        int length = 0;
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < S.length(); i ++){
            char c = S.charAt(i);
            set.add(c);
            map.put(c, map.get(c) - 1);
            length ++;

            if(check(set, map)){
                set.clear();
                result.add(length);
                length = 0;
            }
        }
        return result;
    }

    public boolean check(Set<Character> set, Map<Character, Integer> map){
        for(Character c: set){
            if(map.get(c) != 0)
                return false;
        }
        return true;
    }

    // Solution 2: greedy
    public List<Integer> partitionLabels1(String S) {
        int[] lastIndex = new int[26];
        for(int i = 0; i < S.length(); i ++)
            lastIndex[S.charAt(i) - 'a'] = i;

        List<Integer> result = new ArrayList<>();
        int length = 0, max = 0;
        for(int i = 0; i < S.length(); i ++){
            length ++;
            max = Math.max(max, lastIndex[S.charAt(i) - 'a']);

            if(max == i){
                result.add(length);
                length = 0;
            }
        }
        return result;
    }
}
