package Array._075;

public class Solution {
    // Solution 0: quick sort
    public void sortColors0(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    public void quickSort(int[] nums, int start, int end){
        if(start >= end || end < 0)
            return;

        int pivot = nums[start];
        int left = start, right = end;
        while(left < right){
            while(left < right && nums[right] >= pivot)
                right --;

            while(left < right && nums[left] <= pivot)
                left ++;

            swap(nums, left, right);
        }

        swap(nums, start, left);
        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    public void swap(int nums[], int a, int b){
        int i = nums[a];
        nums[a] = nums[b];
        nums[b] = i;
    }

    // Solution 1: count
    public void sortColors1(int[] nums) {
        int[] count = new int[3];

        for(int i: nums)
            count[i] ++;

        int curr = 0;
        for(int i = 0; i < 3; i ++){
            for(int j = 0; j < count[i]; j ++){
                nums[curr] = i;
                curr ++;
            }
        }
    }

    // Solution 2: one pass
    public void sortColors2(int[] nums) {
        int left = 0, right = nums.length - 1;
        int curr = 0;

        while(curr <= right){
            if(nums[curr] == 1){
                curr ++;
            }else if(nums[curr] == 2){
                swap(nums, curr, right);
                right --;
            }else{
                swap(nums, curr, left);
                left ++;
                curr ++;
            }
        }
    }
}
