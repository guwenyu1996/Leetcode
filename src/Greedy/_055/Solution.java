package Greedy._055;

public class Solution {
    // Solution 1: backtracking, time limit exceeds
    public boolean canJump(int[] nums) {
        return helper(nums, 0);
    }

    public boolean helper(int[] nums, int curr){
        if(curr == nums.length - 1)
            return true;

        int maxStep = Math.min(curr + nums[curr], nums.length);
        for(int i = curr + 1; i <= maxStep; i ++){
            if(helper(nums, i))
                return true;
        }

        return false;
    }

    // Solution 2: backtracking + memory
    public boolean canJump2(int[] nums) {
        boolean[] check = new boolean[nums.length];
        return helper(nums, 0, check);
    }

    public boolean helper(int[] nums, int curr, boolean[] check){
        if(curr == nums.length - 1)
            return true;

        int maxStep = Math.min(curr + nums[curr], nums.length);
        for(int i = curr + 1; i <= maxStep; i ++){
            if(check[i])
                continue;

            if(helper(nums, i, check))
                return true;
        }

        check[curr] = true;
        return false;
    }
}
