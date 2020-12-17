package LinkedList._138;

import java.util.HashMap;
import java.util.Map;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = next;
        this.random = random;
    }

    public Node(int _val,Node _next,Node _random) {
        val = _val;
        next = _next;
        random = _random;
    }
}

public class Solution {
    // Solution 1: recursion
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();

        return helper(head, map);
    }

    public Node helper(Node current, Map<Node, Node> map){
        if(current == null)
            return null;

        Node node = null;
        if(map.containsKey(current)){
            node = map.get(current);
            return node;
        }

        node = new Node(current.val, null, null);
        map.put(current, node);

        node.random = helper(current.random, map);
        node.next = helper(current.next, map);

        return node;
    }

    // Solution 2: iteration with O(n) space
    public Node copyRandomList1(Node head) {
        if(head == null)
            return null;

        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        Node newNode = new Node(current.val);
        map.put(current, newNode);

        while(current != null){
            if(current.random != null)
                newNode.random = helper1(current.random, map);
            if(current.next != null)
                newNode.next = helper1(current.next, map);

            current = current.next;
            newNode = newNode.next;
        }
        return map.get(head);
    }

    public Node helper1(Node node, Map<Node, Node> map){
        if(map.containsKey(node))
            return map.get(node);
        else{
            Node copy = new Node(node.val);
            map.put(node, copy);
            return copy;
        }
    }
}
