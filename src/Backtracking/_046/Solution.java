package Backtracking._046;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {

    // Solution 1: backtracking
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        helper(result, temp, set, nums);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> temp, Set<Integer> set, int[] nums){
        if(temp.size() == nums.length){
            result.add(new ArrayList<>(temp));
            return;
        }

        for(int i = 0; i < nums.length; i ++){
            if(!set.contains(nums[i])){
                temp.add(nums[i]);
                set.add(nums[i]);
                helper(result, temp, set, nums);
                temp.remove(temp.size() - 1);
                set.remove(nums[i]);
            }
        }

    }
}
