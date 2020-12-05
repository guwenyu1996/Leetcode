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

    // Solution 3: backtrack + enum
    public boolean canJump3(int[] nums) {
        State[] memo = new State[nums.length];
        for(int i = 0; i < nums.length - 1; i ++)
            memo[i] = State.UNKOWN;
        memo[nums.length - 1] = State.GOOD;

        return helper(nums, 0, memo);

        //return (memo[0] == State.GOOD)? true : false;
    }

    public boolean helper(int[] nums, int curr, State[] memo){
        if(memo[curr] != State.UNKOWN)
            return (memo[curr] == State.GOOD)? true : false;

        int maxStep = Math.min(curr + nums[curr], nums.length - 1);
        for(int i = maxStep; i >= curr + 1; i --){
            if(helper(nums, i, memo)){
                memo[curr] = State.GOOD;
                return true;
            }
        }

        memo[curr] = State.BAD;
        return false;
    }

    // Solution 4: dynamic programming bottom down
    public boolean canJump4(int[] nums) {
        State[] state = new State[nums.length];
        for(int i = 0; i < nums.length - 1; i ++)
            state[i] = State.UNKOWN;
        state[nums.length - 1] = State.GOOD;

        for(int i = nums.length - 2; i >= 0; i --){
            int maxStep = Math.min(i + nums[i], nums.length - 1);

            for(int j = maxStep; j >= i + 1; j --){
                if(state[j] == State.GOOD){
                    state[i] = State.GOOD;
                    break;
                }
            }
        }

        return (state[0] == State.GOOD)? true : false;
    }


}

enum State{
    GOOD, BAD, UNKOWN
}
