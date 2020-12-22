package Array._560;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    // Solution 1: brute force
    public int subarraySum(int[] nums, int k) {
        int count = 0;
        for(int i = 0; i < nums.length; i ++){
            int sum = 0;
            for(int j = i; j < nums.length; j ++){
                sum += nums[j];
                if(sum == k)
                    count ++;
            }
        }
        return count;
    }

    // Solution 2: hashmap
    public int subarraySum2(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;
        for(int i = 0; i < nums.length; i ++){
            sum += nums[i];
            if(map.containsKey(sum - k))
                count += map.get(sum - k);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return count;
    }
}
