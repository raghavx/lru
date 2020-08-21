class LRUCache {
    
    Map<Integer,Node> cache;
    int capacity, size;
    Node head, tail;
    
    public LRUCache(int capacity) {
        this.cache = new HashMap<Integer, Node>();
        this.capacity = capacity;
        this.size=0;
        this.head = new Node();
        this.tail = new Node();
        
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = this.cache.get(key);
        if(Objects.nonNull(node)){
            moveToHead(node);
            return node.value;
        }
        return -1;
    }
    
    public void put(int key, int value) {
       Node node = this.cache.get(key);
        if(node == null){
            node = new Node();
            node.key = key;
            node.value = value;
            this.cache.put(key,node);
            addNode(node);
            size++;
            if(size > capacity){
                Node tail = popTail();
                cache.remove(tail.key);
                size--;
            }
        }else{
            node.value = value;
            moveToHead(node);
        }
    }
    
    void addNode(Node node){
        // add the node just after head 
        node.prev = head;
        node.next = head.next;
        
        head.next.prev = node;
        head.next = node;
        
        
    }
    
    void removeNode(Node node){
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }
    
    void moveToHead(Node node){
        removeNode(node);
        addNode(node);
    }
    
    Node popTail(){
        Node n = tail.prev;
        removeNode(n);
        return n;
    }
}

class Node{
    int key, value;
    Node prev, next;
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
