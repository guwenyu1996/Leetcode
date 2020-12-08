package String._022;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // Solution 1: brute force
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generateAll(new char[n*2], 0, result);
        return result;
    }

    public void generateAll(char[] current, int pos, List<String> ans){
        if(pos == current.length){
            if(check(current)){
                ans.add(new String(current));
            }
        }else{
            current[pos] = '(';
            generateAll(current, pos + 1, ans);
            current[pos] = ')';
            generateAll(current, pos + 1, ans);
        }

    }

    public boolean check(char[] s){
        int balance = 0;
        for(int i = 0; i < s.length; i ++){
            switch(s[i]){
                case '(':{
                    balance ++;
                    break;
                }
                case ')':{
                    balance --;
                    break;
                }
            }
            if(balance < 0)
                return false;
        }
        return (balance == 0)? true : false;
    }

    // Solution 2: backtracking
    public List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        helper("", 0, 0, n, result);
        return result;
    }

    public void helper(String s, int left, int right, int n, List<String> ans){
        if(left + right == 2*n){
            ans.add(s);
            return;
        }

        if(left < n)
            helper(s + "(", left + 1, right, n, ans);
        if(right < left)
            helper(s+ ")", left, right + 1, n, ans);
    }

    // Solution 3: dynamic programming
    public List<String> generateParenthesis3(int n) {
        List<List<String>> result = new ArrayList<>();
        result.add(new ArrayList<>(Arrays.asList("")));
        result.add(new ArrayList<>(Arrays.asList("()")));

        for(int i = 2; i <= n; i ++){
            List<String> temp = new ArrayList<>();
            for(int j = 0; j < i; j ++){
                List<String> left = result.get(j);
                List<String> right = result.get(i - j - 1);
                for(String str1: left){
                    for(String str2: right)
                        temp.add("(" + str1 + ")" + str2);
                }
            }
            result.add(temp);
        }
        return result.get(n);
    }
}
