package String._013;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int sum = 0;
        for(int i = 0; i < s.length(); i ++){
            int num1 = map.get(s.charAt(i));
            if(i + 1 == s.length())
                sum += num1;
            else{
                int num2 = map.get(s.charAt(i+1));
                if(num1 < num2)
                    sum -= num1;
                else
                    sum += num1;
            }
        }
        return sum;
    }

    public int romanToInt1(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);
        map.put("CM", 900);
        map.put("CD", 400);
        map.put("XC", 90);
        map.put("XL", 40);
        map.put("IX", 9);
        map.put("IV", 4);

        int sum = 0, i = 0;
        while(i < s.length()){
            if(i != s.length() - 1){
                String temp = s.substring(i, i + 2);
                if(map.containsKey(temp)){
                    sum += map.get(temp);
                    i += 2;
                    continue;
                }
            }

            sum += map.get(s.substring(i, i + 1));
            i ++;
        }

        return sum;
    }
}
