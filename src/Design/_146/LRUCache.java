package Design._146;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    class Node{
        int key;
        int value;
        private Node prev;
        private Node next;

        public Node(int k, int val){
            key = k;
            value = val;
        }
    }

    class DoubleList{
        private Node head, tail;
        private int size;

        public DoubleList(){
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addTail(Node node){
            tail.prev.next = node;
            node.prev = tail.prev;
            tail.prev = node;
            node.next = tail;
            size ++;
        }

        public Node removeHead(){
            if (head.next == tail)
                return null;
            Node first = head.next;
            removeNode(first);
            return first;
        }

        public void removeNode(Node node){
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size --;
        }

        public int getSize(){
            return size;
        }
    }

    private DoubleList list;
    private Map<Integer, Node> map;
    private int capacity;

    public LRUCache(int capacity) {
        list = new DoubleList();
        map = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        if(map.containsKey(key)){
            makeRecently(key);
            return map.get(key).value;
        }
        else
            return -1;
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            Node node = map.get(key);
            list.removeNode(node);
            map.remove(node.key);

            node = new Node(key, value);
            list.addTail(node);
            map.put(key, node);
        }else{
            if(list.getSize() >= capacity){
                Node head = list.removeHead();
                map.remove(head.key);
            }

            Node node = new Node(key, value);
            list.addTail(node);
            map.put(key, node);
        }
    }

    public void makeRecently(int key){
        Node node = map.get(key);
        list.removeNode(node);
        list.addTail(node);
    }
}

