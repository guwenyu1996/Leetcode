package Design._155;

import java.util.LinkedList;
import java.util.Stack;

// My solution, not pass
class MinStack {
    private Stack<Integer> stack;
    private LinkedList<Integer> list;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        list = new LinkedList<>();
    }

    public void push(int x) {
        stack.push(x);
        int i = 0;
        for(; i < list.size(); i ++){
            if(list.get(i) > x)
                break;
        }
        list.add(i, x);
    }

    public void pop() {
        int top = stack.pop();
        for(int i = 0; i < list.size(); i ++){
            if(list.get(i).equals(top)){
                list.remove(i);
                break;
            }
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return list.getFirst();
    }
}

