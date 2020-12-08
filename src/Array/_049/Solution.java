package Array._049;

import java.util.*;

public class Solution {
    // Solution 0: my solution
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        for(String s: strs){
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String newS = new String(charArray);

            if(map.containsKey(newS)){
                result.get(map.get(newS)).add(s);
            }else{
                map.put(newS, result.size());
                result.add(new ArrayList<>(Arrays.asList(s)));
            }
        }
        return result;
    }

    // Solution 1: improved solution 0
    public List<List<String>> groupAnagrams1(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for(String s: strs){
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String newS = new String(charArray);

            if(map.containsKey(newS))
                map.get(newS).add(s);
            else
                map.put(newS, new ArrayList<>(Arrays.asList(s)));

        }
        return new ArrayList<>(map.values());
    }

    // Solution 2: categorize by count
    public List<List<String>> groupAnagrams2(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        int[] alphabet = new int[26];

        for(String str: strs){
            Arrays.fill(alphabet, 0);

            char[] charArray = str.toCharArray();
            for(char c: charArray)
                alphabet[c - 'a'] ++;

            StringBuilder sb = new StringBuilder();
            for(int i: alphabet){
                sb.append("#");
                sb.append(i);
            }

            if(map.containsKey(sb.toString()))
                map.get(sb.toString()).add(str);
            else
                map.put(sb.toString(), new ArrayList<>(Arrays.asList(str)));
        }

        return new ArrayList<>(map.values());
    }
}
