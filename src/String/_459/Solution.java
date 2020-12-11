package String._459;

public class Solution {
    // My solution
    public boolean repeatedSubstringPattern(String s) {
        if(s.length() <= 1)
            return false;
        int start = 0, end = 0;
        int p1 = 0, p2 = end + 1;
        boolean flag = false;
        char[] charArray = s.toCharArray();

        while(p2 < s.length()){
            if(charArray[p2] == charArray[start + p1]){
                flag = true;
                p1 = (p1 + 1) % (end - start + 1);
                p2 ++;
            }else{
                flag = false;
                p1 = 0;
                end ++;
                p2 = end + 1;
            }
        }

        if(flag && p1 == 0)
            return true;
        else
            return false;
    }

    // Solution 1: concatenation
    public boolean repeatedSubstringPattern1(String s) {
        return (s + s).substring(1, 2 * s.length() - 1).contains(s);
    }
}
