package Heap._347;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution {
    // not pass
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i ++){
            if(map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }

        map.entrySet().stream().sorted(Map.Entry.<Integer, Integer>comparingByValue());

        int[] result = new int[k];
        int i = 0;
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            if(i == k)
                break;

            result[i] = entry.getKey();
            i ++;
        }
        return result;
    }

    public static void main(String[] args){
        int[] nums = {4,1,-1,2,-1,2,3};
        int k = 2;
        int[] result = topKFrequent(nums, 2);
        for(int i: result)
            System.out.println(i);
    }

    // Solution 1: priority queue
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums.length; i ++){
            if(map.containsKey(nums[i]))
                map.put(nums[i], map.get(nums[i]) + 1);
            else
                map.put(nums[i], 1);
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>((n1, n2) -> map.get(n2) - map.get(n1));
        for(Map.Entry<Integer, Integer> entry: map.entrySet())
            queue.add(entry.getKey());

        int[] result = new int[k];
        for(int i = 0; i < k; i ++)
            result[i] = queue.poll();

        return result;
    }
}
