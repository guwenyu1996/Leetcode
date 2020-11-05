package Array._034;

class Solution {
    public int[] searchRange(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right){
            int middle = left + (right - left)/2;

            if(nums[middle] == target){
                left = middle;
                right = middle;
                while(left > 0 && nums[left-1] == target)
                    left --;
                while(right < nums.length -1 && nums[right+1] == target)
                    right ++;

                return new int[]{left, right};

            }else if(nums[middle] > target)
                right = middle - 1;
            else
                left = middle + 1;
        }

        return new int[]{-1, -1};
    }
}