package String._003;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    // Solution 1: brute force
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
}
