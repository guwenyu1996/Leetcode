package Array._015;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    // Two point solution
    // Not passed because time limit exceed (cannot check if array is contained in the result!)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();

        if(nums.length < 3)
            return result;
        Arrays.sort(nums);

        for(int i = 0; i <= nums.length - 2; i ++){
            int start = i + 1;
            int end = nums.length - 1;

            while(start < end){
                if(nums[start] + nums[end] == -nums[i]){
                    List<Integer> r = new ArrayList<>(Arrays.asList(nums[i], nums[start], nums[end]));
                    if(!result.contains(r))
                        result.add(r);
                    start ++;
                    continue;
                }
                else if(nums[start] + nums[end] < -nums[i]){
                    start ++;
                    continue;
                }else{
                    end --;
                    continue;
                }
            }
        }

        return result;
    }

    // Two pointer pass
    public List<List<Integer>> threeSum1(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>> ();

        if(nums.length < 3)
            return result;
        Arrays.sort(nums);

        for(int i = 0; i <= nums.length - 2; i ++){
            if(i - 1 >= 0 && nums[i-1] == nums[i])
                continue;
            int start = i + 1;
            int end = nums.length - 1;

            while(start < end){
                if(nums[start] + nums[end] == -nums[i]){
                    List<Integer> r = new ArrayList<>(Arrays.asList(nums[i], nums[start], nums[end]));
                    result.add(r);

                    do{
                        start ++;
                    }
                    while(nums[start] == nums[start - 1] && start < end);
                }
                else if(nums[start] + nums[end] < -nums[i]){
                    start ++;
                }else{
                    end --;
                }
            }
        }

        return result;
    }
}