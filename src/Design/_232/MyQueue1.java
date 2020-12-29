package Design._232;

import java.util.Stack;

class MyQueue1 {
    Stack<Integer> tail;
    Stack<Integer> head;
    int front;

    /** Initialize your data structure here. */
    public MyQueue1() {
        tail = new Stack<>();
        head = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        if(tail.isEmpty())
            front = x;

        tail.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(head.isEmpty()){
            while(!tail.isEmpty())
                head.push(tail.pop());
        }
        return head.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(!head.isEmpty())
            return head.peek();
        else
            return front;
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return tail.isEmpty() && head.isEmpty();
    }
}
