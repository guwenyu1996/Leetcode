package Design._155;

import java.util.Stack;

class MinStack2 {

    private Stack<Integer> nums;
    private Stack<Integer> minNum;

    /** initialize your data structure here. */
    public MinStack2() {
        nums = new Stack<Integer> ();
        minNum = new Stack<Integer> ();
    }

    public void push(int x) {
        nums.push(x);
        if(minNum.isEmpty() || minNum.peek() >= x)
            minNum.push(x);
    }

    public void pop() {
        int temp = nums.pop();
        if(minNum.peek().equals(temp))
            minNum.pop();
    }

    public int top() {
        return nums.peek();
    }

    public int getMin() {
        return minNum.peek();
    }
}