package Array;

import java.util.*;

class Solution {
    public void nextPermutation(int[] nums) {
        boolean found = false;
        for(int i = nums.length -2; i >= 0; i --){
            int flag = Integer.MAX_VALUE;
            int index = i;
            for(int j = i + 1; j < nums.length; j ++){
                if(nums[j] > nums[i] && nums[j] < flag){
                    flag = nums[j];
                    index = j;
                }
            }

            if(index != i){
                for(int j = i + 1; j < nums.length; j ++)
                    nums[j] = nums[j-1];
                nums[i] = flag;
                found = true;
                break;
            }
        }

        if(!found){
            int middle = (nums.length + 1) / 2;
            for(int i = 0; i < middle; i ++){
                int temp = nums[i];
                nums[i] = nums[nums.length - 1 - i];
                nums[nums.length - 1 - i] = temp;
            }
        }
    }

    public static void main(String[] args){
        int[] nums = new int[] {1, 3, 2};
        new Solution().nextPermutation(nums);

        for(int i = 0; i < nums.length; i ++)
            System.out.println(nums[i]);
    }
}
