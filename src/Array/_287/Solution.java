package Array._287;

import java.util.Arrays;

public class Solution {
    // My solution
    public int findDuplicate(int[] nums) {
        int[] count = new int[nums.length];

        for(int i: nums){
            if(count[i] == 0)
                count[i] = 1;
            else
                return i;
        }
        return 0;
    }

    // Solution 1: sort
    public int findDuplicate1(int[] nums) {
        Arrays.sort(nums);

        for(int i = 0; i <= nums.length - 2; i ++){
            if(nums[i] == nums[i+1])
                return nums[i];
        }
        return -1;
    }

    // Solution 2: binary search
    public int findDuplicate2(int[] nums) {
        int left = 1, right = nums.length - 1;

        while(left < right){
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int i: nums){
                if(i <= mid)
                    count ++;
            }

            if(count > mid)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
