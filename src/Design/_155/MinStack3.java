package Design._155;

import java.util.Stack;

class MinStack3 {

    private Stack<Integer> nums;
    private Stack<int[]> minNum;

    /** initialize your data structure here. */
    public MinStack3 () {
        nums = new Stack<Integer> ();
        minNum = new Stack<> ();
    }

    public void push(int x) {
        nums.push(x);
        if(minNum.isEmpty() || minNum.peek()[0] > x)
            minNum.push(new int[]{x, 1});
        else{
            int[] arr = minNum.pop();
            arr[1] ++;
            minNum.push(arr);
        }
    }

    public void pop() {
        int temp = nums.pop();
        int[] arr = minNum.pop();
        arr[1] --;
        if(arr[1] != 0)
            minNum.push(arr);
    }

    public int top() {
        return nums.peek();
    }

    public int getMin() {
        return minNum.peek()[0];
    }
}
