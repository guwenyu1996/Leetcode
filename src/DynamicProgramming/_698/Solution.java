package DynamicProgramming._698;

public class Solution {

    // My solution: brute force
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for(int i: nums)
            sum += i;

        if(sum % k != 0)
            return false;
        sum = sum / k;

        int[] group = new int[k];
        return helper(nums, 0, group, sum);
    }

    public boolean helper(int[] nums, int index, int[] group, int target){
        if(index == nums.length)
            return true;

        for(int i = 0; i < group.length; i ++){
            if(group[i] + nums[index] <= target){
                group[i] += nums[index];
                if(!helper(nums, index + 1, group, target))
                    group[i] -= nums[index];
                else
                    return true;
            }
        }

        return false;
    }
}
