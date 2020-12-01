package Array._088;

public class Solution {
    // My solution / solution 1 twopointer from beginning
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] nums1copy = nums1.clone();

        int i = 0, j = 0;
        while(i < m && j < n){
            if(nums1copy[i] <= nums2[j]){
                nums1[i+j] = nums1copy[i];
                i ++;
            }else{
                nums1[i+j] = nums2[j];
                j ++;
            }
        }

        while(i < m){
            nums1[i+j] = nums1copy[i];
            i ++;
        }
        while(j < n){
            nums1[i+j] = nums2[j];
            j ++;
        }
    }

    // Solution 2: two pointer from end
    public void merge2(int[] nums1, int m, int[] nums2, int n) {
        int x = m - 1, y = n - 1;
        int curr = m + n - 1;

        while(x >= 0 && y >= 0){
            if(nums1[x] >= nums2[y]){
                nums1[curr] = nums1[x];
                x --;
            }else{
                nums1[curr] = nums2[y];
                y --;
            }
            curr --;
        }
        System.arraycopy(nums2, 0, nums1, 0, y + 1);
    }
}
