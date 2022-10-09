import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class LRUCache {

    class Solution2 extends LinkedHashMap<Integer,Integer>{
        int capacity;
        Solution2(int capacity){
            super(capacity,0.75f,true);
            this.capacity = capacity;
        }

        public int get(int key){
            return super.getOrDefault(key,-1);
        }

        public void set(int key,int value){
            super.put(key,value);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry<Integer,Integer> eldest) {
            return this.capacity < this.size();
        }
    }

    class  Node{
        int key;
        int value;
        Node pre;
        Node next;
        Node(){

        }

        Node(int key, int value){
            this.key = key;
            this.value = value;
        }
    }

    class Solution{
        int capacity;
        int size;
        Map<Integer,Node> map;
        Node head;
        Node tail;
        Solution(int capacity){
            this.capacity = capacity;
            this.size = 0;
            map = new HashMap<>();
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key){
            Node node = map.get(key);
            if(node == null){
                return -1;
            }
            move2Head(node);
            return node.value;
        }

        public void set(int key,int value){
            Node node = map.get(key);
            if(node == null){
                node = new Node(key,value);
                map.put(key,node);
                add2Head(node);
                size++;
                if(size > capacity){
                    removeTail();
                    size--;
                }
            }else{
                node.value = value;
                map.put(key,node);
                move2Head(node);
            }
        }

        public void move2Head(Node node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
            add2Head(node);
        }

        public void add2Head(Node node) {
            node.next = head.next;
            node.pre = head;
            head.next.pre = node;
            head.next = node;
        }

        public Node removeTail(){
            Node node = tail.pre;
            node.pre.next = tail;
            tail.pre = node.pre;
            return node;
        }

    }
}
