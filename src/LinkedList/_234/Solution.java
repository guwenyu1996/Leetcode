package LinkedList._234;

import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    // Solution 1: to array
    public boolean isPalindrome(ListNode head) {
        List<Integer> list = new ArrayList<>();

        ListNode curr = head;
        while(curr != null){
            list.add(curr.val);
            curr = curr.next;
        }

        int left = 0, right = list.size() - 1;
        while(left <= right){
            if(!list.get(left).equals(list.get(right)))
                return false;

            left ++;
            right --;
        }

        return true;
    }

    // Solution 2: recursion
    public ListNode front;

    public boolean isPalindrom1(ListNode head) {
        front = head;
        return check(head);
    }

    public boolean check(ListNode head){
        if(head != null){
            if(!check(head.next))
                return false;
            if(head.val != front.val)
                return false;
            front = front.next;
        }

        return true;
    }

    // Solution 3: reverse the second half in place
    public boolean isPalindrome3(ListNode head) {

        if(head == null) return true;

        // Find half
        ListNode middle = findMiddle(head);
        // reverse second-half
        middle.next = reverse(middle.next);
        // check palindrome
        boolean result = true;
        ListNode p1 = head, p2 = middle.next;
        while(p1 != null && p2 != null){
            if(p1.val != p2.val){
                result = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // restore second half
        middle.next = reverse(middle.next);
        return result;

    }

    public ListNode findMiddle(ListNode head){
        ListNode fast = head, slow = head;
        while(fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head){
        ListNode prev = null, curr = head;

        while(curr != null){
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }


}
