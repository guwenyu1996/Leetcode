package LinkedList._086;

import LinkedList.ListNode;

public class Solution {

    // my solution
    public ListNode partition(ListNode head, int x) {
        ListNode prehead = new ListNode(-1);
        prehead.next = head;
        ListNode prev = prehead, curr = head;
        ListNode large = new ListNode(-1);
        ListNode lHead = large;

        while(curr != null){
            if(curr.val >= x){
                prev.next = curr.next;
                curr.next = null;
                lHead.next = curr;
                lHead = lHead.next;
                curr = prev.next;
            }else{
                prev = curr;
                curr = curr.next;
            }
        }

        prev.next = large.next;
        return prehead.next;
    }
}
