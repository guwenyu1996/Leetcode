package Math._069;

public class Solution {
    // not pass
    public int mySqrt(int x) {
        int i = 0;
        while(true){
            if(i * i == x)
                return i;

            if(i * i > x)
                return (i-1);

            i ++;
        }
    }

    // Solution 1: binary search
    public int mySqrt1(int x) {
        int left = 0, right = x;

        while(left <= right){
            int middle = left + (right - left) / 2;

            long square = (long) middle * middle;
            if(square == x)
                return middle;
            else if(square < x)
                left = middle + 1;
            else
                right = middle - 1;
        }

        return right;
    }
}
