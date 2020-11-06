package Tree._096;

class Solution {
    // dynamic programming
    public int numTrees(int n) {
        int[] G = new int[n+1];
        G[0] = 1;
        G[1] = 1;

        if(n < 2)
            return 1;

        for(int i = 2; i <= n; i ++){
            int num = 0;
            for(int j = 1; j <= i; j ++)
                num += G[j-1] * G[i-j];
            G[i] = num;
        }

        return G[n];
    }
}