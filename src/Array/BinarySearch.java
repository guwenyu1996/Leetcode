package Array;

public class BinarySearch {
    public int binarySearch(int[] nums, int target){
        int left = 0, right = nums.length - 1;

        while(left <= right){
            int middle = left + (right - left)/2;
            if(nums[middle] == target)
                return middle;
            else if(nums[middle] > target)
                right = middle - 1;
            else
                left = middle + 1;
        }

        return -1;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1, 3, 4, 5, 6};
        System.out.println(new BinarySearch().binarySearch(nums, 5));
    }


}
