package Array._015;

import java.util.*;

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

    // Solution 2: hashset
    public List<List<Integer>> threeSum2(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for(int i = 0; i < nums.length; i ++){
            if(i == 0 || nums[i-1] != nums[i])
                twoSum(nums, i, result);
        }
        return result;
    }

    public void twoSum(int[] nums, int n, List<List<Integer>> result){
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = n + 1; i < nums.length; i ++){
            int complement = - nums[n] - nums[i];

            if(map.containsKey(complement)){
                result.add(Arrays.asList(nums[i], nums[n], nums[map.get(complement)]));
                while(i + 1 < nums.length && nums[i] == nums[i+1])
                    i ++;
            }

            map.put(nums[i], i);
        }
    }

    public void twoSum1(int[] nums, int n, List<List<Integer>> result){
        Set<Integer> set = new HashSet<>();
        for(int i = n + 1; i < nums.length; i ++){
            int complement = - nums[n] - nums[i];

            if(set.contains(complement)){
                result.add(Arrays.asList(nums[i], nums[n], complement));
                while(i + 1 < nums.length && nums[i] == nums[i+1])
                    i ++;
            }

            set.add(nums[i]);
        }
    }
}