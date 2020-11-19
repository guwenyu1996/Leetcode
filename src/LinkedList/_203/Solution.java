package LinkedList._203;

import LinkedList.ListNode;

public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        if(head == null)
            return head;

        ListNode prehead = new ListNode(-1);
        prehead.next = head;
        ListNode curr = prehead;

        while(curr.next != null){
            if(curr.next.val == val)
                curr.next = curr.next.next;
            else
                curr = curr.next;
        }

        return prehead.next;
    }
}
