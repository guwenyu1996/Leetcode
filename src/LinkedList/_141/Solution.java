package LinkedList._141;

import LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {

    // Solution 1: hashset
    public boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();

        while(head != null){
            if(set.contains(head))
                return true;
            else{
                set.add(head);
                head = head.next;
            }
        }
        return false;
    }

    public boolean hasCycle2(ListNode head) {
        if(head == null)
            return false;

        ListNode fast = head.next, slow = head;
        while(slow != fast){
            if(fast == null || fast.next == null)
                return false;
            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
