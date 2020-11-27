package DynamicProgramming._139;

import java.util.*;

public class Solution {
    // Solution 1: brute force
    public boolean wordBreak(String s, List<String> wordDict) {
        return helper(s, new HashSet<String>(wordDict), 0);
    }

    public boolean helper(String s, Set<String> wordDict, int start){
        if(start == s.length())
            return true;

        for(int i = start + 1; i <= s.length(); i ++){
            if(wordDict.contains(s.substring(start, i)) && helper(s, wordDict, i))
                return true;
        }
        return false;
    }

    // Solution 2: optimized time complexity of sol 1
    public boolean wordBreak1(String s, List<String> wordDict) {
        return helper(s, new HashSet<String>(wordDict), 0, new Boolean[s.length()]);
    }

    public boolean helper(String s, Set<String> wordDict, int start, Boolean[] memo){
        if(start == s.length())
            return true;

        if(memo[start] != null)
            return memo[start];

        for(int i = start + 1; i <= s.length(); i ++){
            if(wordDict.contains(s.substring(start, i)) && helper(s, wordDict, i, memo)){
                memo[start] = true;
                return true;
            }

        }
        memo[start] = false;
        return false;
    }

    // Solution 3: BFS
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        Queue<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[s.length()];
        queue.add(0);

        while(!queue.isEmpty()){
            int start = queue.poll();
            if(start == s.length())
                return true;

            if(!isVisited[start]){
                for(int end = start + 1; end <= s.length(); end ++){
                    if(wordDictSet.contains(s.substring(start, end))){
                        queue.offer(end);
                    }
                }
                isVisited[start] = true;
            }
        }
        return false;
    }

    // Solution 4:



}
