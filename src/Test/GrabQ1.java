package Test;

import java.io.*;
import java.util.*;

class MyCode {
    public static void main (String[] args) {
        int n = 3;
        List<String> result = getAllPar(n);
        for(String s : result)
            System.out.println(s);
    }

    public static List<String> getAllPar(int n){
        List<String> ans = new ArrayList<>();
        ans.add("");
        return helper(0, n, ans);
    }

    public static List<String> helper(int curr, int n, List<String> ans){
        if(curr == n)
            return ans;

        List<String> result = new ArrayList<>();
        for(int i = 0; i < ans.size(); i ++){
            String ori = ans.get(i);
            if(!search(result, "(" + ori + ")")){
                //System.out.println("Include");
                result.add("(" + ori + ")");
            }
            if(!search(result, "()" + ori)){
                //System.out.println("left");
                result.add("()" + ori);
            }
            if(!search(result, ori + "()")){
                //System.out.println("right");
                result.add(ori + "()");
            }
        }

        return helper(curr + 1, n, result);
    }

    public static boolean search(List<String> list, String s){
        if(list.size() == 0)
            return false;

        for(int i = 0; i < list.size(); i ++){
            if(s.equals(list.get(i)))
                return true;
        }
        return false;
    }
}



// Your last Go code is saved below:
// //题目3
// //输入一个正数n，输出n组括号所有符合逻辑的组合情况。
// //1 ()
// (()), ()()
// //2 ()(),(())
// ((),()), ()()(), ((())), (())()
// //3 ()()(),()(()),(())(),((())),(()())
