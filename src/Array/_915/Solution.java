package Array._915;

public class Solution {
    public int partitionDisjoint(int[] A) {
        int[] leftMax = new int[A.length];
        int[] rightMin = new int[A.length];

        leftMax[0] = A[0];
        for(int i = 1; i < A.length; i ++)
            leftMax[i] = Math.max(leftMax[i-1], A[i]);
        rightMin[A.length - 1] = A[A.length - 1];
        for(int i = A.length - 2; i >= 0; i --)
            rightMin[i] = Math.min(rightMin[i+1], A[i]);

        for(int i = 0; i < A.length - 1; i ++){
            if(leftMax[i] <= rightMin[i+1])
                return i + 1;
        }

        return -1;
    }
}
