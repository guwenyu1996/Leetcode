package Backtracking._040;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // Solution: backtrack
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        helper(result, temp, candidates, target, 0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int start){
        if(target == 0)
            result.add(new ArrayList<>(temp));
        else if(target < 0)
            return;
        else{
            for(int i = start; i < candidates.length; i ++){
                if(i > start && candidates[i] == candidates[i-1])
                    continue;

                temp.add(candidates[i]);
                helper(result, temp, candidates, target - candidates[i], i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
