package SlidingWindows._239;

import java.util.Deque;
import java.util.LinkedList;

public class Solution {
    // Solution 1: sliding window + deque
    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new LinkedList<>();
        int[] result = new int[nums.length - k + 1];

        for(int i = 0; i < nums.length; i ++){
            while(!deque.isEmpty() && nums[deque.peekLast()] < nums[i])
                deque.pollLast();
            deque.addLast(i);

            if(deque.peekFirst() < i - k + 1)
                deque.pollFirst();

            if(i >= k - 1)
                result[i - k + 1] = nums[deque.peekFirst()];
        }
        return result;
    }
}
