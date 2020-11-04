package Array._026;

class Solution {
    // not pass, dont need to switch elements
    public int removeDuplicates(int[] nums) {
        int i = 0, j = 1;

        while(j < nums.length){
            while(j + 1 < nums.length && nums[j] == nums[i])
                j ++;

            if(j < nums.length){
                int temp = nums[i+1];
                nums[i+1] = nums[j];
                nums[j] = temp;
            }

            if(j != nums.length - 1){
                i ++;
                j ++;
            }else
                break;

        }
        return i + 1;
    }

    // pass, two pointers
    public int removeDuplicates1(int[] nums) {
        int i = 0;

        for(int j = 0; j < nums.length; j ++){
            if(nums[i] != nums[j]){
                i ++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
