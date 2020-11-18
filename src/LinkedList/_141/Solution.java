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

}
