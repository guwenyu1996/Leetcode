package Backtracking._078;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Solution 1: dynamic programming
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for(int i = 0; i < nums.length; i ++){
            int size = result.size();
            for(int j = 0; j < size; j ++){
                List<Integer> temp = new ArrayList<>(result.get(j));
                temp.add(nums[i]);
                result.add(temp);
            }
        }
        return result;
    }

    // Solution 2: backtracking
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        List<Integer> temp = new ArrayList<>();
        helper(result, temp, nums, 0);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> temp, int[] nums, int start){
        if(start == nums.length)
            return;

        for(int i = start; i < nums.length; i ++){
            temp.add(nums[i]);
            result.add(new ArrayList<>(temp));
            helper(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}
