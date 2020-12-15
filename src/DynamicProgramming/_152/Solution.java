package DynamicProgramming._152;

public class Solution {
    // my solution: dynamic programming
    public int maxProduct(int[] nums) {
        int[] max = new int[nums.length];
        int[] min = new int[nums.length];
        max[0] = nums[0];
        min[0] = nums[0];

        for(int i = 1; i < nums.length; i ++){
            if(nums[i] < 0){
                max[i] = Math.max(min[i-1] * nums[i], nums[i]);
                min[i] = Math.min(max[i-1] * nums[i], nums[i]);
            }
            else{
                max[i] = Math.max(max[i-1] * nums[i], nums[i]);
                min[i] = Math.min(min[i-1] * nums[i], nums[i]);
            }
        }

        int result = Integer.MIN_VALUE;
        for(int i: max){
            if(i > result)
                result = i;
        }

        return result;
    }

    // Solution 1: dynamic programming simplified
    public int maxProduct1(int[] nums) {
        int max_far = nums[0];
        int min_far = nums[0];
        int result = nums[0];
        for(int i = 1; i < nums.length; i ++){
            int temp = Math.max(max_far * nums[i], Math.max(min_far * nums[i], nums[i]));
            min_far = Math.min(max_far * nums[i], Math.min(min_far * nums[i], nums[i]));
            max_far = temp;

            result = Math.max(max_far, result);
        }

        return result;
    }
}
