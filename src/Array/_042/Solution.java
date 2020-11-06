package Array._042;

import java.util.Stack;

public class Solution {
    // Not pass: local dillema
    public int trap0(int[] height) {
        int sum = 0;
        for(int i = 0; i < height.length; i ++){
            int left_max = 0, right_max = 0;

            for(int left = 0; left < i; left ++){
                if(height[left] > left_max)
                    left_max = height[left];
            }

            for(int right = height.length - 1; right > i; right --){
                if(height[right] < right_max)
                    right_max = height[right];
            }

            int t = Math.min(left_max, right_max);
            if(t > height[i])
                sum += (height[i] - t);
        }

        return sum;
    }

    // Solution 1: brute force
    public int trap(int[] height) {
        int sum = 0;
        for(int i = 0; i < height.length; i ++){
            int left_max = 0, right_max = 0;

            for(int left = 0; left < i; left ++){
                if(height[left] > left_max)
                    left_max = height[left];
            }

            for(int right = height.length - 1; right > i; right --){
                if(height[right] > right_max)
                    right_max = height[right];
            }

            int t = Math.min(left_max, right_max);
            if(t > height[i])
                sum += (t - height[i]);
        }

        return sum;
    }

    // Solution 2: dynamic programming
    public int trap2(int[] height) {
        int[] left_max = new int[height.length];
        for(int i = 1; i < height.length; i ++)
            left_max[i] = Math.max(left_max[i-1], height[i-1]);

        int[] right_max = new int[height.length];
        for(int i = height.length - 2; i > 0; i --)
            right_max[i] = Math.max(right_max[i+1], height[i+1]);

        int sum = 0;
        for(int i = 0; i < height.length; i ++){
            int t = Math.min(left_max[i], right_max[i]);
            if(t > height[i])
                sum += (t - height[i]);
        }

        return sum;
    }

    // Solution 3: stack
    public int trap3(int[] height) {
        Stack<Integer> stack = new Stack<> ();
        int i = 0, sum = 0;
        while(i < height.length){
            while(!stack.isEmpty() && height[i] > height[stack.peek()]){
                int top = stack.pop();
                if(stack.isEmpty())
                    break;

                int distance = i - stack.peek() - 1;
                int bar = Math.min(height[i], height[stack.peek()]);
                if(bar > height[top])
                    sum += distance * (bar - height[top]);
            }
            stack.push(i);
            i ++;
        }
        return sum;
    }


}
