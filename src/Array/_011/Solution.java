package Array._011;

class Solution {
    public int maxArea(int[] height) {
        int max = 0;

        for(int i = 0; i < height.length; i ++){
            for(int j = i+1; j < height.length; j ++){
                int s = (height[i] > height[j])? height[j]:height[i];
                int result = s * (j-i);
                max = (result > max) ? result : max;
            }
        }

        return max;
    }

    public int maxArea1(int[] height) {
        int max = 0;

        int i = 0;
        int j = height.length - 1;
        while(i < j){
            int result = (j-i) * Math.min(height[i], height[j]);
            max = Math.max(result, max);

            if(height[i] < height[j])
                i ++;
            else
                j --;
        }

        return max;
    }
}