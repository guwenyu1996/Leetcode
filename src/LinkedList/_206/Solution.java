package LinkedList._206;

import LinkedList.ListNode;

public class Solution {

    // Solution 1: iteration
    public ListNode reverseList(ListNode head) {
        ListNode curr = head, next = null, prev= null;

        while(curr != null){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // Solution 2: recursion
    public ListNode reverseList1(ListNode head) {
        if(head == null || head.next == null)
            return head;

        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }
}
