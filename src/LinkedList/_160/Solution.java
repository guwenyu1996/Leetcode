package LinkedList._160;

import LinkedList.ListNode;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    // Solution 1: hashset
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        ListNode m = headA, n = headB;
        Set<ListNode> set = new HashSet<ListNode>();
        while(m != null){
            set.add(m);
            m = m.next;
        }

        while(n != null){
            if(set.contains(n))
                return n;
            n = n.next;
        }

        return null;
    }

    // Solution 2: two pointers
    
}
