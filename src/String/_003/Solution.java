package String._003;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    // Solution 0: brute force
    public int lengthOfLongestSubstring(String s) {
        int max = 0;
        for(int i = 0; i < s.length(); i ++){
            for(int j = i + 1; j <= s.length(); j ++){
                if(checkUnique(s, i, j))
                    max = Math.max(max, j - i);
            }
        }
        return max;
    }

    public boolean checkUnique(String s, int start, int end){
        Set<Character> set = new HashSet<>();
        for(int i = start; i < end; i ++){
            Character ch = s.charAt(i);
            if(set.contains(ch))
                return false;
            else
                set.add(ch);
        }
        return true;
    }

    // Solution 1: sliding windows
    public int lengthOfLongestSubstring1(String s) {
        if(s.length() == 0)
            return 0;

        Set<Character> set = new HashSet<>();
        int i = 0, j = 1;
        set.add(s.charAt(0));
        int max = 1;

        while(i <= j && j < s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j ++;
                max = Math.max(max, set.size());
            }else{
                set.remove(s.charAt(i));
                i ++;
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring2(String s) {
        Set<Character> set = new HashSet<>();
        int i = 0, j = 0;
        int max = 0;

        while(i <= j && j < s.length()){
            if(!set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j ++;
                max = Math.max(max, set.size());
            }else{
                set.remove(s.charAt(i));
                i ++;
            }
        }
        return max;
    }

    // Solution 2: optimized sliding window
    public int lengthOfLongestSubstring3(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        int max = 0;

        while(i <= j && j < s.length()){
            if(map.containsKey(s.charAt(j)))
                i = Math.max(i, map.get(s.charAt(j))+1);

            map.put(s.charAt(j), j);
            max = Math.max(max, j-i+1);
            j ++;
        }
        return max;
    }
}
