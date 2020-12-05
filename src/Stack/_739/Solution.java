package Stack._739;

import java.util.Stack;

public class Solution {
    // Solution 0: brute force
    public int[] dailyTemperatures(int[] T) {
        int[] result = new int[T.length];

        for(int i = 0; i < T.length; i ++){
            for(int j = i + 1; j < T.length; j ++){
                if(T[j] > T[i]){
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

    // Solution 1: stack from end
    public int[] dailyTemperatures1(int[] T) {
        int[] result = new int[T.length];
        Stack<Integer> stack = new Stack<>();

        for(int i = T.length - 1; i >= 0; i --){
            while(!stack.isEmpty() && T[stack.peek()] <= T[i])
                stack.pop();

            if(stack.isEmpty())
                result[i] = 0;
            else
                result[i] = stack.peek() - i;

            stack.push(i);
        }
        return result;
    }
}
