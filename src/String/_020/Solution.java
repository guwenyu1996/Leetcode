package String._020;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    // Solution 1: stack
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        for(int i = 0; i < s.length(); i ++){
            if(map.containsValue(s.charAt(i)))
                stack.push(s.charAt(i));
            else{
                if(stack.isEmpty() || map.get(s.charAt(i)) != stack.pop())
                    return false;
            }
        }
        if(!stack.isEmpty())
            return false;
        else
            return true;
    }
}
