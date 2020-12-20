package Stack._394;

import java.util.Stack;

public class Solution {

    // My Solution: stack
    public String decodeString(String s) {
        Stack<String> stack = new Stack<>();
        StringBuilder builder = new StringBuilder();
        char[] chararray = s.toCharArray();
        builder.append(chararray[0]);
        int type =  (Character.isDigit(chararray[0]))? 1:2;

        for(int i = 1; i < s.length(); i ++){
            if(chararray[i] == ']'){
                String str = builder.toString();
                while(!stack.isEmpty()){
                    String temp = stack.pop();
                    if(temp.matches("[0-9]+")){
                        str = str.repeat(Integer.parseInt(temp));
                        break;
                    }else
                        str = temp + str;
                }
                stack.push(str);
                builder = new StringBuilder();
            }else if(chararray[i] == '['){
                stack.push(builder.toString());
                builder = new StringBuilder();
                type = 0;
            }else{
                int temp = (Character.isDigit(chararray[i]))? 1:2;
                if(type == 0 || temp == type){
                    builder.append(chararray[i]);
                    type = temp;
                }else{
                    stack.push(builder.toString());
                    builder = new StringBuilder();
                    builder.append(chararray[i]);
                    type = temp;
                }
            }
        }

        if(builder.length() != 0)
            stack.push(builder.toString());

        String str = "";
        while(!stack.isEmpty()){
            String temp = stack.pop();
            if(temp.matches("[0-9]+")){
                str = str.repeat(Integer.parseInt(temp));
                stack.push(str);
                str = "";
            }else
                str = temp + str;
        }
        return str;
    }

    // Solution 1: stack
    public String decodeString1(String s) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < s.length(); i ++){
            char c = s.charAt(i);

            if(c == ']'){
                String str = "";
                while(stack.peek() != '[')
                    str = stack.pop() + str;

                String number = "";
                stack.pop();
                while(!stack.isEmpty() && Character.isDigit(stack.peek()))
                    number = stack.pop() + number;
                int num = Integer.parseInt(number);

                for(int k = 0; k < num; k ++){
                    for(int j = 0; j < str.length(); j ++)
                        stack.push(str.charAt(j));
                }

            }else{
                stack.push(c);
            }
        }

        String str = "";
        while(!stack.isEmpty())
            str = stack.pop() + str;

        return str;
    }
}
