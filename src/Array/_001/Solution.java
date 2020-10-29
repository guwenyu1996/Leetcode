package Array._001;

import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for(int i = 0; i < nums.length; i ++){
            for(int j = i + 1; j < nums.length; j ++){
                if(nums[i] + nums[j] == target)
                    return new int[] {i, j};
            }
        }

        return null;
    }

    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i ++){
            //map.put(i, target - nums[i]);
            map.put(target - nums[i], i);
        }

        for(int i = 0; i < nums.length; i ++){
            if(map.containsKey(nums[i]) && map.get(nums[i])!=i){
                int index = map.get(nums[i]);
                return new int[]{i, index};
            }
        }

        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i < nums.length; i ++){
            //map.put(i, target - nums[i]);
            if(map.containsKey(nums[i]) && i!=map.get(nums[i]))
                return new int[]{i, map.get(nums[i])};

            map.put(target - nums[i], i);
        }
        return null;
    }
}