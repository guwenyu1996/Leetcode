package BinarySearch._1760;

public class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = Integer.MIN_VALUE;

        for(int i: nums)
            right = Math.max(right, i);


        while(left < right){
            int middle = left + (right - left) / 2;

            int count = 0;
            for(int i: nums)
                count += (i - 1) / middle;

            if(count > maxOperations)
                left = middle + 1;
            else
                right = middle;
        }
        return left;
    }
}
