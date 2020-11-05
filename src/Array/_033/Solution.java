package Array._033;

class Solution {
    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    // stackoverflow error: recursion
    public int binarySearch(int[] nums, int left, int right, int target){
        if(left > right || (left == right && target != nums[left]))
            return -1;

        int middle = left + (right - left)/2;

        if(nums[middle] == target)
            return middle;

        if(nums[left] < nums[middle]){
            if(nums[middle] > target && target > nums[left])
                return binarySearch(nums, left, middle, target);
            else
                return binarySearch(nums, middle, right, target);
        }else{
            if(target < nums[right] && target > nums[middle])
                return binarySearch(nums, middle, right, target);
            else
                return binarySearch(nums, left, middle, target);
        }
    }

    public int search1(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while(left <= right){
            int middle = left + (right - left)/2;

            if(nums[middle] == target)
                return middle;
            else if(nums[middle] >= nums[left]){
                if(nums[left] <= target && target <= nums[middle])
                    right = middle - 1;
                else
                    left = middle + 1;
            }else{
                if(nums[middle] <= target && target <= nums[right])
                    left = middle + 1;
                else
                    right = middle - 1;
            }

        }
        return -1;
    }
}
