package String._567;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // My solution: sliding windows
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < s1.length(); i ++){
            char c = s1.charAt(i);
            if(map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        int left = 0, right = 0;
        while(right < s2.length()){
            char r = s2.charAt(right);
            if(map.containsKey(r))
                map.put(r, map.get(r) - 1);
            right ++;

            while(check(map)){
                if(right - left == s1.length())
                    return true;

                char l = s2.charAt(left);
                if(map.containsKey(l))
                    map.put(l, map.get(l) + 1);
                left ++;
            }
        }

        return false;
    }

    public boolean check(Map<Character, Integer> map){
        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            if(entry.getValue() > 0)
                return false;
        }
        return true;
    }
}
