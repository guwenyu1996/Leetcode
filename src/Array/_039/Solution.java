package Array._039;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Solution 1: backtrack
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        helper(result, temp, candidates, target, 0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> temp, int[] candidates, int target, int start){
        if(target == 0){
            result.add(new ArrayList<>(temp));
            //temp.clear();
        }
        else if(target < 0){
            return;
        }
        else{
            for(int i = start; i < candidates.length; i ++){
                temp.add(candidates[i]);
                helper(result, temp, candidates, target - candidates[i], i);
                temp.remove(temp.size()-1);
            }
        }
    }
}
