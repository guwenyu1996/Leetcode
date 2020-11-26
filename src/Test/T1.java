package Test;

public class T1 {
    // Input 1..n      (n-1)
    public static void main(String[] args){
        int[] nums = {5, 1, 2, 3}; // n = 1
        // -5, -1, -2, 3
        // 1, 2, 3, 4
        // -1, -2, -3, -4 // 5
        System.out.println(findMissing(nums));

    }

    public static int findMissing(int[] nums){
        int[] result = new int[nums.length+1];

        for(int i: nums){
            result[i-1] = 1;
        }

        for(int i = 0; i < nums.length; i ++){
            if(result[i] == 0)
                return i+1;
        }

        return -1;
    }

}
