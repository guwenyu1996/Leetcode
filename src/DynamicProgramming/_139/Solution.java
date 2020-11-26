package DynamicProgramming._139;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

}
