package Backtracking._216;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // solution: backtracking
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        helper(result, temp, k, n, 1);
        return result;
    }

    public void helper(List<List<Integer>> result, List<Integer> temp, int k, int n, int start){
        if(n == 0){
            if(temp.size() != k)
                return;
            result.add(new ArrayList<>(temp));
        }
        else if(n < 0 || temp.size() == k)
            return;
        else{
            for(int i = start; i <= 9; i ++){
                temp.add(i);
                helper(result, temp, k, n-i, i + 1);
                temp.remove(temp.size() - 1);
            }
        }
    }
}
