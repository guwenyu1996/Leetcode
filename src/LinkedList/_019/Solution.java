package LinkedList._019;

import LinkedList.ListNode;

public class Solution {

    // My solution: recursion
    public int count = 0;

    public ListNode removeNthFromEnd(ListNode head, int n) {
        return helper(head, n);
    }

    public ListNode helper(ListNode node, int n){
        if(node == null)
            return null;

        ListNode temp = helper(node.next, n);
        count ++;

        if(count == n)
            return temp;
        else{
            node.next = temp;
            return node;
        }
    }

    // Solution 1: two pass
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        int length = 0;
        ListNode curr = head;
        while(curr != null){
            length ++;
            curr = curr.next;
        }

        ListNode prehead = new ListNode(-1);
        prehead.next = head;
        int temp = length - n;
        curr = prehead;
        while(temp != 0){
            temp --;
            curr = curr.next;
        }
        curr.next = curr.next.next;
        return prehead.next;
    }

    // Solution 2: one pass
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode prehead = new ListNode(-1);
        prehead.next = head;

        ListNode fast = prehead, slow = prehead;

        int num = n + 1;
        while(num != 0){
            fast = fast.next;
            num --;
        }

        num = n;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return prehead.next;
    }

}
