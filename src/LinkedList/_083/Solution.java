package LinkedList._083;

import LinkedList.ListNode;

public class Solution {

    // Solution 1: iteration
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        if(curr == null)
            return curr;

        while(curr.next != null){
            if(curr.val != curr.next.val)
                curr = curr.next;
            else
                curr.next = curr.next.next;
        }

        return head;
    }
}
