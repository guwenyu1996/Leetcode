package String._005;

public class Solution {
    // Solution 1: brute force
    public String longestPalindrome(String s) {
        int start = 0, end = 0;
        for(int i = 0; i < s.length(); i ++){
            for(int j = i; j < s.length(); j ++){
                if(check(s, i, j)){
                    if(j - i> end - start){
                        start = i;
                        end = j;
                    }
                }
            }
        }
        return s.substring(start, end+1);
    }

    public boolean check(String s, int start, int end){
        while(start <= end){
            if(s.charAt(start) != s.charAt(end))
                return false;

            start ++;
            end --;
        }
        return true;
    }

    // Solution 2:
    public String longestPalindrome1(String s) {
        int start = 0, len = 0;

        for(int i = 0; i < s.length(); i ++){
            int odd = check1(s, i, i);
            int even = check1(s, i, i+1);

            int length = Math.max(odd, even);

            if(length > len){
                len = length;
                start = i - (length - 1)/2;
            }
        }

        return s.substring(start, start+len);
    }

    public int check1(String s, int start, int end){
        int len = 0;
        while(start - len >= 0 && end + len < s.length()){
            if(s.charAt(start-len) != s.charAt(end+len))
                break;
            len ++;
        }
        return end - start + 2*len - 1;
    }

    // Solution 3: dynamic programming
    public String longestPalindrome3(String s) {
        if(s.length() == 0)
            return "";
        boolean[][] state = new boolean[s.length()][s.length()];
        int start = 0, length = 1;

        for(int i = 0; i < s.length(); i++)
            state[i][i] = true;

        for(int j = 0; j < s.length(); j ++){
            for(int i = 0; i < j; i ++){
                if(j-i == 1)
                    state[i][j] = ((s.charAt(i) == s.charAt(j))? true : false);
                else{
                    if(s.charAt(i) == s.charAt(j) && state[i+1][j-1])
                        state[i][j] = true;
                    else
                        state[i][j] = false;
                }

                if(state[i][j] && (j-i+1) > length){
                    length = j - i + 1;
                    start = i;
                }
            }
        }

        return s.substring(start, start+length);
    }


}
