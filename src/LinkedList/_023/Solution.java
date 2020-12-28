package LinkedList._023;

import LinkedList.ListNode;

import java.util.PriorityQueue;

public class Solution {
    // my solution: priority queue
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0)
            return null;

        PriorityQueue<ListNode> queue = new PriorityQueue<>((n1, n2) -> (n1.val - n2.val));
        ListNode prehead = new ListNode(-1);
        ListNode curr = prehead;

        for(ListNode first: lists){
            if(first != null)
                queue.add(first);
        }

        while(queue.size() != 0){
            ListNode temp = queue.poll();
            if(temp.next != null)
                queue.add(temp.next);

            curr.next = temp;
            curr = temp;
        }

        return prehead.next;
    }

    // Solution 1: merge list one by one
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode ans = null;
        for(ListNode list: lists)
            ans = merge2Lists(list, ans);

        return ans;
    }

    public ListNode merge2Lists(ListNode listA, ListNode listB){
        ListNode prehead = new ListNode(-1);
        ListNode curr = prehead;

        while(listA != null && listB != null){
            if(listA.val < listB.val){
                curr.next = listA;
                listA = listA.next;
            }else{
                curr.next = listB;
                listB = listB.next;
            }

            curr = curr.next;
        }

        if(listA != null)
            curr.next = listA;
        else
            curr.next = listB;

        return prehead.next;
    }


    // Solution 2: divide and conquer
    public ListNode mergeKLists2(ListNode[] lists) {
        if(lists.length == 0)
            return null;

        return merge(lists, 0, lists.length - 1);
    }

    public ListNode merge(ListNode[] lists, int start, int end){
        if(start == end)
            return lists[start];

        int mid = start + (end - start) / 2;

        ListNode p1 = merge(lists, start, mid);
        ListNode p2 = merge(lists, mid + 1, end);

        return merge2Lists(p1, p2);
    }


}
