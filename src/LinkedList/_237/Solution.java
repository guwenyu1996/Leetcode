package LinkedList._237;

import LinkedList.ListNode;

public class Solution {

    // My solution
    public void deleteNode(ListNode node) {
        ListNode curr = node, prev= null;

        while(curr.next != null){
            curr.val = curr.next.val;
            prev = curr;
            curr = curr.next;
        }

        prev.next = null;
    }
}
