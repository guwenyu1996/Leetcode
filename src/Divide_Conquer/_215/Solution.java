package Divide_Conquer._215;

import java.util.PriorityQueue;

public class Solution {
    // Solution 1: heap
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        for(int n: nums){
            heap.add(n);
            if(heap.size() > k)
                heap.poll();
        }
        return heap.poll();
    }

    // Solution 2: quick sort
    public int findKthLargest1(int[] nums, int k) {
        return quicksort(nums, 0, nums.length - 1, nums.length - k);
    }

    public int quicksort(int[] nums, int low, int high, int k){
        if(low == high)
            return nums[low];

        int pivot = nums[low];
        int i = low, j = high;

        while(i < j){
            while(i < j && nums[j] >= pivot)
                j --;

            while(i < j && nums[i] <= pivot)
                i ++;

            swap(nums, i, j);
        }

        swap(nums, low, i);

        if(i == k)
            return nums[k];
        else if(i < k)
            return quicksort(nums, i + 1, high, k);
        else
            return quicksort(nums, low, i - 1, k);
    }

    public void swap(int[] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

}
