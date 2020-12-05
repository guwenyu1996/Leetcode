package Stack._496;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Solution {
    // Solution 1: brute force
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for(int i = 0; i < nums1.length; i ++){
            boolean found = false, flag = false;

            for(int j = 0; j < nums2.length; j ++){
                if(nums2[j] == nums1[i]){
                    found = true;
                    continue;
                }

                if(found && nums2[j] > nums1[i]){
                    result[i] = nums2[j];
                    flag = true;
                    break;
                }
            }

            if(!found || !flag)
                result[i] = -1;
        }
        return result;
    }

    // Solution 2: brute force + hashmap
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums2.length; i ++)
            map.put(nums2[i], i);

        for(int i = 0; i < nums1.length; i ++){
            boolean found = false;

            for(int j = map.get(nums1[i]) + 1; j < nums2.length; j ++){
                if(nums2[j] > nums1[i]){
                    result[i] = nums2[j];
                    found = true;
                    break;
                }
            }

            if(!found)
                result[i] = -1;
        }
        return result;
    }

    // Solution 3: monotonous stack
    public int[] nextGreaterElement3(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap();

        for(int i = 0; i < nums2.length; i ++){
            if(stack.isEmpty()){
                stack.push(nums2[i]);
            }else{
                while(!stack.isEmpty() && stack.peek() < nums2[i])
                    map.put(stack.pop(), nums2[i]);
                stack.push(nums2[i]);
            }
        }

        int[] result = new int[nums1.length];
        for(int i = 0; i < nums1.length; i ++){
            if(map.containsKey(nums1[i]))
                result[i] = map.get(nums1[i]);
            else
                result[i] = -1;
        }
        return result;
    }
}
