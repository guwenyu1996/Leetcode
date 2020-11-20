package LinkedList._002;

import LinkedList.ListNode;

public class Solution {
    // Solution 0: fail
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int result = listToInt(l1) + listToInt(l2);
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;

        String str = String.valueOf(result);
        for(int i = str.length() - 1; i >= 0; i --){
            ListNode curr = new ListNode(Character.getNumericValue(str.charAt(i)));
            prev.next = curr;
            prev = curr;
        }

        return prehead.next;

    }

    public int listToInt(ListNode list){
        int sum = 0, x = 1;

        while(list != null){
            sum += list.val * x;
            x *= 10;
            list = list.next;
        }

        return sum;
    }

    // Solution 1:
    public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        int flag = 0;

        while(l1 != null && l2 != null){
            ListNode curr = null;
            int sum = l1.val + l2.val + flag;
            if(sum >= 10){
                flag = 1;
                curr = new ListNode(sum - 10);
            }else{
                flag = 0;
                curr = new ListNode(sum);
            }
            prev.next = curr;
            prev = curr;
            l1 = l1.next;
            l2 = l2.next;
        }

        while(l1 != null){
            ListNode curr = null;
            int sum = l1.val + flag;
            if(sum >= 10){
                flag = 1;
                curr = new ListNode(sum - 10);
            }else{
                flag = 0;
                curr = new ListNode(sum);
            }
            prev.next = curr;
            prev = curr;
            l1 = l1.next;
        }

        while(l2 != null){
            ListNode curr = null;
            int sum = l2.val + flag;
            if(sum >= 10){
                flag = 1;
                curr = new ListNode(sum - 10);
            }else{
                flag = 0;
                curr = new ListNode(sum);
            }
            prev.next = curr;
            prev = curr;
            l2 = l2.next;
        }

        if(flag == 1)
            prev.next = new ListNode(1);

        return prehead.next;
    }

    // Solution 1 simplified
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode prehead = new ListNode(-1);
        ListNode prev = prehead;
        int flag = 0;

        while(l1 != null || l2 != null){
            int x = ((l1 == null)? 0 : l1.val);
            int y = ((l2 == null)? 0 : l2.val);
            ListNode curr = null;
            int sum = x + y + flag;
            if(sum >= 10){
                flag = 1;
                curr = new ListNode(sum - 10);
            }else{
                flag = 0;
                curr = new ListNode(sum);
            }
            prev.next = curr;
            prev = curr;
            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;
        }

        if(flag == 1)
            prev.next = new ListNode(1);

        return prehead.next;
    }
}
