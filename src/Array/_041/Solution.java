package Array._041;

class Solution {
    public int firstMissingPositive(int[] nums) {
        boolean flag = false;
        for(int i: nums){
            if(i == 1){
                flag = true;
                break;
            }
        }

        if(!flag)
            return 1;

        for(int i = 0; i < nums.length; i ++){
            if(nums[i] <= 0 || nums[i] > nums.length)
                nums[i] = 1;
        }

        for(int i = 0; i < nums.length; i ++){
            if(nums[Math.abs(nums[i]) - 1] > 0)
                nums[Math.abs(nums[i]) - 1] *= -1;
        }

        for(int i = 0; i < nums.length; i ++){
            if(nums[i] > 0)
                return i + 1;
        }

        return nums.length + 1;
    }
}
