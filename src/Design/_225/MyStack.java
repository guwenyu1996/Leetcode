package Design._225;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {

    Queue<Integer> q1;
    Queue<Integer> q2;
    int top;

    /** Initialize your data structure here. */
    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        q1.add(x);
        top = x;
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        int size = q1.size();

        while(size > 1){
            if(size == 2)
                top = q1.peek();
            q2.add(q1.poll());
            size --;
        }

        int result = q1.poll();

        Queue<Integer> temp = q1;
        q1 = q2;
        q2 = temp;

        return result;
    }

    /** Get the top element. */
    public int top() {
        return top;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q1.isEmpty();
    }
}
