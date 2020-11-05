package Array;

import java.util.*;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left < right){
            int middle = left + (right - left) / 2;

            if(nums[middle] == target)
                return middle;
            else if(nums[middle] > target)
                right = middle - 1;
            else
                left = middle + 1;
        }

        if(nums[left] < target)
            return left;
        else
            return left + 1;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,3,5,6};
        System.out.println(new Solution().searchInsert(nums, 2));
    }

}