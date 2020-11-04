package Array._016;

import java.util.Arrays;

class Solution {
    // Solution 1: two pointer
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int diff = Integer.MAX_VALUE;
        for(int i = 0; i < nums.length - 2; i ++){
            if(i > 0 && nums[i] == nums[i-1])
                continue;

            int complement = target - nums[i];
            int start = i + 1;
            int end = nums.length - 1;

            while(start < end){
                int sum = nums[i] + nums[start] + nums[end];

                if(Math.abs(target-sum) < Math.abs(diff)){
                    diff = target-sum;
                }

                if(nums[start] + nums[end] < complement){
                    start ++;
                }else{
                    end --;
                }
            }
        }

        return target-diff;

    }
}
