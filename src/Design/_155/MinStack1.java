package Design._155;

import java.util.Stack;

class MinStack1 {
    private Stack<int[]> stack;

    /** initialize your data structure here. */
    public MinStack1() {
        stack = new Stack<>();
    }

    public void push(int x) {
        if(stack.isEmpty())
            stack.push(new int[]{x, x});
        else{
            int temp = stack.peek()[1];
            stack.push(new int[]{x, Math.min(x, temp)});
        }
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}