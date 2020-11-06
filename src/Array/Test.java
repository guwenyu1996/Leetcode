package Array;

import java.util.*;

class Solution {
    // Not pass: local dillema
    public int trap(int[] height) {
        int left = 0, right = 0;
        int sum = 0;

        while(right < height.length - 2){
            while(right+1 < height.length && height[right] <= height[right+1]){
                left ++;
                right ++;
            }

            while(right+1 < height.length && height[right] > height[right+1])
                right ++;

            while(right+1 < height.length && height[right] <= height[right+1])
                right ++;

            if(right < height.length){
                int x = height[left] < height[right] ? height[left] : height[right];
                for(int i = left + 1; i < right; i ++){
                    if(height[i] < x)
                        sum += (x - height[i]);
                }
            }

            left = right;
        }
        return sum;
    }

    public static void main(String[] args){
        int[] height = new int[]{4,2,0,3,2,5};
        System.out.println(new Solution().trap(height));
    }
}