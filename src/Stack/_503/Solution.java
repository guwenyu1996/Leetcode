package Stack._503;

import java.util.Stack;

public class Solution {
    // My solution
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        for(int loop = 0; loop < 2; loop ++){
            for(int i = nums.length - 1; i >= 0; i --){
                while(!stack.isEmpty() && nums[stack.peek()] <= nums[i])
                    stack.pop();

                if(stack.isEmpty())
                    result[i] = -1;
                else
                    result[i] = nums[stack.peek()];

                stack.push(i);
            }
        }
        return result;
    }
}
