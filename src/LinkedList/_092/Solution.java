package LinkedList._092;

import LinkedList.ListNode;

public class Solution {
    // My Solution
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode prehead = new ListNode(-1);
        prehead.next = head;

        ListNode preStart = prehead;
        for(int i = 0; i < m-1; i ++)
            preStart = preStart.next;

        ListNode prev = preStart.next;
        ListNode curr = prev.next, next = null;
        prev.next = null;
        for(int i = n - m; i > 0; i --){
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        preStart.next.next = curr;
        preStart.next = prev;

        return prehead.next;
    }



}
