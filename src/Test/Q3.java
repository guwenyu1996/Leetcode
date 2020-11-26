package Test;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Q3 {
    public static List<Integer> findKMin(int[] nums, int k){
        PriorityQueue<Integer> heap = new PriorityQueue<Integer> ((n1, n2) -> n2 - n1);

        for(int n: nums){
            heap.add(n);

            if(heap.size() > k)
                heap.poll();
        }

        List<Integer> list = new ArrayList<>();
        while(!heap.isEmpty())
            list.add(heap.poll());

        return list;
    }

    public static void main(String[] args){
        int[] num = new int[]{10, 12, 24, 13, 14, 1, 8};
        int k = 3;
        List<Integer> result = findKMin(num, k);
        for(Integer i: result)
            System.out.println(i);
    }
}
