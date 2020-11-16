package String._006;

import java.util.ArrayList;
import java.util.List;

class Solution {
    // Solution 0: initial
    public String convert(String s, int numRows) {
        if(s.length() == 0)
            return "";
        List<List<Character>> result = new ArrayList<>();
        for(int i = 0; i < numRows; i ++)
            result.add(new ArrayList<Character> ());

        int size = 2 * (numRows - 1);
        for(int i = 0; i < s.length(); i ++){
            int mode = 0;
            if(size != 0)
                mode = i % size;
            if(mode < numRows)
                result.get(mode).add(s.charAt(i));
            else
                result.get(size - mode).add(s.charAt(i));
        }

        char[] array = new char[s.length()];
        int index = 0;
        for(int i = 0; i < numRows; i ++){
            List<Character> list = result.get(i);
            for(int j = 0; j < list.size(); j ++){
                array[index] = list.get(j);
                index ++;
            }

        }

        return String.valueOf(array);
    }

    // Solution 1: improved
    public String convert1(String s, int numRows) {
        if(numRows == 1)
            return s;

        List<StringBuilder> result = new ArrayList<>();
        for(int i = 0; i < Math.min(numRows, s.length()); i ++)
            result.add(new StringBuilder());

        int curRow = 0;
        boolean direction = false;
        for(char ch: s.toCharArray()){
            result.get(curRow).append(ch);
            if(curRow == 0 || curRow == numRows - 1)
                direction = !direction;
            curRow += (direction? 1 : -1);
        }

        StringBuilder re = new StringBuilder();
        for(StringBuilder sb : result)
            re.append(sb);

        return re.toString();
    }
}