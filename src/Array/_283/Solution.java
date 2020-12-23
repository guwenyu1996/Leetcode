package Array._283;

public class Solution {
    // My solution: two pointer
    public void moveZeroes(int[] nums) {
        int zeroPointer = 0;

        for(int i = 0; i < nums.length; i ++){
            if(nums[i] == 0)
                continue;

            nums[zeroPointer] = nums[i];
            zeroPointer ++;
        }

        for(; zeroPointer < nums.length; zeroPointer ++)
            nums[zeroPointer] = 0;
    }
}
