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
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        ListNode pA = headA, pB = headB;
        while(pA != pB){
            if(pA == null)
                pA = headB;
            else
                pA = pA.next;
            if(pB == null)
                pB = headA;
            else
                pB = pB.next;
        }
        return pA;
    }

    // Solution 3: 消除链表长度差
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(headA == null || headB == null)
            return null;

        int diff = getLength(headB) - getLength(headA);
        ListNode pA = headA, pB = headB;
        while(diff != 0){
            if(diff > 0){
                pB = pB.next;
                diff --;
            }else{
                pA = pA.next;
                diff ++;
            }
        }

        while(pB != null){
            if(pA == pB)
                break;
            else{
                pA = pA.next;
                pB = pB.next;
            }
        }

        return pB;
    }

    public int getLength(ListNode head){
        int len = 0;
        while(head != null){
            len ++;
            head = head.next;
        }
        return len;
    }
}
