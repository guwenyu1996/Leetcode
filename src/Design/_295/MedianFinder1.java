package Design._295;

import java.util.PriorityQueue;

class MedianFinder1 {
    private PriorityQueue<Integer> maxHeap;
    private PriorityQueue<Integer> minHeap;

    public MedianFinder1() {
        maxHeap = new PriorityQueue<>((n1, n2) -> Integer.compare(n2, n1));
        minHeap = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if(maxHeap.size() >= minHeap.size()){
            maxHeap.add(num);
            minHeap.add(maxHeap.poll());
        }else{
            minHeap.add(num);
            maxHeap.add(minHeap.poll());
        }

    }

    public double findMedian() {
        if(maxHeap.size() == minHeap.size())
            return ((double)maxHeap.peek() + minHeap.peek()) / 2;
        else
            return minHeap.peek();
    }
}
