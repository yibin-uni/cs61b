public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
    }

    public ArrayDeque(ArrayDeque other) {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 3;
        nextLast = 4;
        for (int i = 0; i < size; i++) {
            addLast((T) other.get(i));
        }
    }

    /** compute the index "before" the current position in a circular ArrayDeque */
    public int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }
    /** compute the index "after" the current position in a circular ArrayDeque */
    public int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private void resize(int capacity) {
        T[] myarray = (T[]) new Object[capacity];
        int start = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            myarray[i] = items[start];
            start = plusOne(start);
        }
        nextFirst = capacity - 1;
        nextLast = size;
        items = myarray;
    }

    public void addFirst(T info) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = info;
        nextFirst = minusOne(nextFirst);
        size++;
    }

    public void addLast(T info) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = info;
        nextLast = plusOne(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            int start = plusOne(nextFirst);
            System.out.print(items[start]);
            System.out.print(" ");
            start = plusOne(start);
        }
        System.out.println();
    }

    public boolean isSparse() {
        double size = this.size;
        double length = items.length;
        double usage = size/length;
        return items.length >= 16 && usage <= 0.25;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[plusOne(nextFirst)];
        items[plusOne(nextFirst)] = null;
        size--;
        nextFirst = plusOne(nextFirst);
        if (isSparse()) {
            resize(items.length / 2);
        }
        return first;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[minusOne(nextLast)];
        items[minusOne(nextLast)] = null;
        size--;
        nextLast = minusOne(nextLast);
        if (isSparse()) {
            resize(items.length / 2);
        }
        return last;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[index];
    }

}
