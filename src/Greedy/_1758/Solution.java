package Greedy._1758;

public class Solution {
    // My solution
    public int minOperations(String s) {
        char c1 = '0';
        char c2 = '1';
        int count1 = 0, count2 = 0;

        for(char i: s.toCharArray()){
            if(i != c1) count1 ++;
            if(i != c2) count2 ++;

            c1 = alternateChar(c1);
            c2 = alternateChar(c2);
        }

        return Math.min(count1, count2);
    }

    public char alternateChar(char x){
        if(x == '0')
            return '1';
        else
            return '0';
    }


    // Improved
    public int minOperations1(String s) {
        char c1 = '0';
        char c2 = '1';
        int count1 = 0, count2 = 0;
        int index = 0;

        for(char i: s.toCharArray()){
            if(i - '0' != index % 2)
                count1 ++;
            if(i - '0' != (index + 1) % 2)
                count2 ++;

            index ++;
        }
        return Math.min(count1, count2);
    }
}
