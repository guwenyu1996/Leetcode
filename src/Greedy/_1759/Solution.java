package Greedy._1759;

public class Solution {
    public int countHomogenous(String s) {
        if(s.length() == 1)
            return 1;

        int sum = 1, temp = 1;
        for(int i = 1; i < s.length(); i ++){
            if(s.charAt(i) == s.charAt(i-1))
                temp++;
            else
                temp = 1;

            sum = (sum + temp) % 1_000_000_007;
        }

        return sum;
    }
}
