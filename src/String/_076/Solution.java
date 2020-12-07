package String._076;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // My solution v0: wrong
    public static String minWindow(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();
        int start = 0, end = 0, maxCount = Integer.MAX_VALUE;

        for(int i = 0; i < t.length(); i ++)
            count.put(t.charAt(i), 0);

        int left = 0, right = 0;
        while(right < s.length() && left <= right){
            while(right < s.length() && !count.containsKey(s.charAt(right)))
                right ++;

            if(right < s.length())
                count.put(s.charAt(right), count.get(s.charAt(right)) + 1);
            if(checkAll(count)){
                if(right - left + 1 < maxCount){
                    end = right;
                    start = left;
                    maxCount = right - left + 1;
                }

                if(count.containsKey(s.charAt(left)))
                    count.put(s.charAt(left), count.get(s.charAt(left)) - 1);
                while(left < s.length() && !count.containsKey(s.charAt(left)))
                    left ++;
                count.put(s.charAt(left), count.get(s.charAt(left)) + 1);
                if(checkAll(count)){
                    if(right - left + 1 < maxCount){
                        end = right;
                        start = left;
                        maxCount = right - left + 1;
                    }
                }
            }

            left ++;
            right ++;
        }

        return s.substring(start, end + 1);
    }

    public static boolean checkAll(Map<Character, Integer> count){
        for(Map.Entry<Character, Integer> entry: count.entrySet()){
            if(entry.getValue() < 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args){
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(minWindow(s,t));
    }

    // My soluton 1: slide windows, pass
    public String minWindow1(String s, String t) {
        Map<Character, Integer> count = new HashMap<>();
        int start = 0, end = 0, maxCount = Integer.MAX_VALUE;

        for(int i = 0; i < t.length(); i ++){
            if(count.containsKey(t.charAt(i)))
                count.put(t.charAt(i), count.get(t.charAt(i)) + 1);
            else
                count.put(t.charAt(i), 1);
        }

        int left = 0, right = 0;
        boolean found = false;
        while(right < s.length()){
            while(!checkAll1(count) && right < s.length()){
                if(count.containsKey(s.charAt(right)))
                    count.put(s.charAt(right), count.get(s.charAt(right)) - 1);
                right ++;
            }

            while(checkAll1(count)){
                found = true;
                if(count.containsKey(s.charAt(left)))
                    count.put(s.charAt(left), count.get(s.charAt(left)) + 1);
                left ++;
            }

            if(right - left + 2 < maxCount){
                end = right;
                start = left - 1;
                maxCount = right - left + 2;
            }
        }

        if(found)
            return s.substring(start, end);
        else
            return "";
    }

    public boolean checkAll1(Map<Character, Integer> count){
        for(Map.Entry<Character, Integer> entry: count.entrySet()){
            if(entry.getValue() > 0)
                return false;
        }
        return true;
    }

    // Solution 1: sliding window offical
    public String minWindow3(String s, String t) {
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0; i < t.length(); i ++){
            char c = t.charAt(i);
            if(map.containsKey(c))
                map.put(c, map.get(c) + 1);
            else
                map.put(c, 1);
        }

        int left = 0, right = 0;
        int start = 0, len = Integer.MAX_VALUE;
        while(right < s.length()){
            // Move right
            char c = s.charAt(right);
            right ++;
            if(map.containsKey(c))
                map.put(c, map.get(c) - 1);

            // Move left
            while(checkAll(map)){
                if(right - left < len){
                    start = left;
                    len = right - left;
                }

                char l = s.charAt(left);
                if(map.containsKey(l))
                    map.put(l, map.get(l) + 1);
                left ++;
            }
        }

        if(len != Integer.MAX_VALUE)
            return s.substring(start, start + len);
        else
            return "";
    }
}
