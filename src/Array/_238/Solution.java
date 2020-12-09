package Array._238;

import java.util.Arrays;

public class Solution {
    // brute force, time limit reach
    public int[] productExceptSelf(int[] nums) {
        int[] result = new int[nums.length];

        for(int i = 0; i < nums.length; i ++){
            int temp = 1;
            for(int j = 0; j < nums.length; j ++){
                if(i == j)
                    continue;
                temp *= nums[j];
            }
            result[i] = temp;
        }
        return result;
    }

    // Solution 1: dynamic programming
    public int[] productExceptSelf1(int[] nums) {
        int left[] = new int[nums.length];
        int right[] = new int[nums.length];

        left[0] = 1;
        for(int i = 1; i < nums.length; i ++)
            left[i] = left[i-1] * nums[i-1];
        right[nums.length - 1] = 1;
        for(int i = nums.length - 2; i >= 0; i --)
            right[i] = right[i+1] * nums[i+1];

        int answer[] = new int[nums.length];
        for(int i = 0; i < nums.length; i ++)
            answer[i] = left[i] * right[i];
        return answer;
    }

    // Solution 2: simplified dynamic programming
    public int[] productExceptSelf2(int[] nums) {
        int answer[] = new int[nums.length];
        Arrays.fill(answer, 1);

        for(int i = 1; i < nums.length; i ++)
            answer[i] = answer[i-1] * nums[i-1];

        int temp = 1;
        for(int i = nums.length - 2; i >= 0; i --){
            temp *= nums[i+1];
            answer[i] *= temp;
        }

        return answer;
    }
}
