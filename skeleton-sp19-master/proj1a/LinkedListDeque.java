public class LinkedListDeque<T> {
    private class TNode {
        public T item;
        public TNode next;
        public TNode prev;

        public TNode(T info, TNode n, TNode p) {
            item = info;
            next = n;
            prev = p;
        }

    }
    private TNode sentinel;
    private int size;
    // create an empty linked list deque
    public LinkedListDeque() {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }
    
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new TNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;

        for (int i = 0; i < other.size(); i++) {
            addLast((T) other.get(i));
        }
    }
    
    public void addFirst(T item) {
        TNode first = new TNode(item, sentinel.next, sentinel);
        sentinel.next.prev = first;
        sentinel.next = first;
        size++;
    }

    public void addLast(T item) {
        TNode last = new TNode(item, sentinel, sentinel.prev);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        TNode iter = sentinel;
        for (iter = sentinel; iter.next != sentinel; iter = iter.next) {
            System.out.print(iter.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            T first_item = sentinel.next.item;
            sentinel.next.next.prev = sentinel;
            sentinel.next = sentinel.next.next;
            size--;
            return first_item;
        }    
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        else {
            T last_item = sentinel.prev.item;
            sentinel.prev.prev.next = sentinel;
            sentinel.prev = sentinel.prev.prev;
            size--;
            return last_item;
        }
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        TNode iter = sentinel.next;
        while (index > 0) {
            iter = iter.next;
            index--;
        }
        return iter.item;
    }

    private T getRecursive(int index, TNode start) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (index == 0) {
            return start.item;
        }
        return getRecursive(index - 1, start.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel.next);
    }

}