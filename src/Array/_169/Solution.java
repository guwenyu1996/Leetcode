package Array._169;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Solution {
    // My solution
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i: nums)
            map.put(i, map.getOrDefault(i, 0) + 1);

        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() > nums.length / 2)
                return entry.getKey();
        }

        return 0;
    }

    // Solution 1: sort
    public int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // Solution 2: random
    public int majorityElement2(int[] nums) {
        Random rand = new Random();

        while(true){
            int index = rand.nextInt(nums.length);
            if(check(nums, nums[index]))
                return nums[index];
        }
    }

    public boolean check(int[] nums, int target){
        int count = 0;
        for(int i: nums){
            if(target == i)
                count ++;
        }

        if(count > nums.length / 2)
            return true;
        else
            return false;
    }

    // Solution 3: divide and conquer
    public int majorityElement3(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    public int helper(int[] nums, int start, int end){
        if(start == end)
            return nums[start];

        int middle = start + (end-start)/2;

        int left = helper(nums, start, middle);
        int right = helper(nums, middle + 1, end);

        if(left == right)
            return left;
        else{
            if(checkOccurence(nums, start, end, left) > checkOccurence(nums, start, end, right))
                return left;
            else
                return right;
        }
    }

    public int checkOccurence(int[] nums, int start, int end, int target){
        int count = 0;
        for(int i = start; i <= end; i ++){
            if(nums[i] == target)
                count ++;
        }

        return count;
    }

    // Solution 4: Boyer-Moore
    public int majorityElement4(int[] nums) {
        int count = 0, candidate = 0;

        for(int num: nums){
            if(count == 0)
                candidate = num;

            if(num == candidate)
                count ++;
            else
                count --;
        }

        return candidate;
    }
}
