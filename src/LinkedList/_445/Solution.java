package LinkedList._445;

import LinkedList.ListNode;

public class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);
        int carry = 0;

        ListNode head = null;
        while(l1 != null || l2 != null){
            int x = 0, y = 0;
            if(l1 != null) x = l1.val;
            if(l2 != null) y = l2.val;
            ListNode curr = new ListNode((x + y + carry) % 10);
            carry = (x + y + carry) / 10;

            curr.next = head;
            head = curr;

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(carry == 1){
            ListNode curr = new ListNode(1);
            curr.next = head;
            head = curr;
        }

        return head;
    }

    public ListNode reverse(ListNode node){
        ListNode prev = null, curr = node;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;

            prev = curr;
            curr = next;
        }
        return prev;
    }
}
