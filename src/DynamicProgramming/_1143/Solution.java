package DynamicProgramming._1143;

public class Solution {
    // Solution 1: dynamic programming
    public int longestCommonSubsequence(String text1, String text2) {

        int[][] state = new int[text1.length()][text2.length()];

        char ch = text2.charAt(0);
        for(int i = 0; i < text1.length(); i ++){
            if(text1.substring(0, i+1).contains(ch+""))
                state[i][0] = 1;
            else
                state[i][0] = 0;
        }

        ch = text1.charAt(0);
        for(int i = 0; i < text2.length(); i ++){
            if(text2.substring(0, i+1).contains(ch+""))
                state[0][i] = 1;
            else
                state[0][i] = 0;
        }

        for(int i = 1; i < text1.length(); i ++){
            for(int j = 1; j < text2.length(); j ++){
                if(text1.charAt(i) == text2.charAt(j))
                    state[i][j] = state[i-1][j-1] + 1;
                else
                    state[i][j] = Math.max(state[i-1][j], state[i][j-1]);
            }
        }

        return state[text1.length()-1][text2.length()-1];
    }

    // solution 2: space optimized dynamic programming
    public int longestCommonSubsequence1(String text1, String text2) {
        if(text2.length() < text1.length()){
            String temp = text1;
            text1 = text2;
            text2 = temp;
        }

        int[] previous = new int[text1.length() + 1];
        int[] current = new int[text1.length() + 1];

        for(int i = 1; i <= text2.length(); i ++){
            for(int j = 1; j <= text1.length(); j ++){
                if(text2.charAt(i-1) == text1.charAt(j-1))
                    current[j] = previous[j-1] + 1;
                else
                    current[j] = Math.max(current[j-1], previous[j]);
            }

            previous = current;
        }

        return current[text1.length()];

    }

}
