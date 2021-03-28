public class ArrayDeque<T> implements Deque<T> {
    private T[] items;   
    private int nextFirst;
    private int nextLast;
    private int size;
    private static final int RFACTOR = 2; // Resizing factor 
    private double usageRatio;

    /** Creates an empty deque. */
    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    /** Returns the number of items in the deque. */
    public int size() {
        return size;
    }

    @Override
    /* Get the item of the given index. 
    *  Returns null if no such item exists.
    */
    public T get(int index) {
        if (size == 0) {
            return null;
        }
        int i = plusOne(nextFirst);
        while (index != 0) {
            i = plusOne(i);
            index -= 1;
        }
        return items[i];
    }

    /** Helper method to calculate index minus one. */
    private int minusOne(int index) {
        if (index == 0) {
            return items.length - 1;
        } else {
            return index - 1;
        }
    }

    /** Helper method to calculate index plus one. */
    private int plusOne(int index) {
        if (index == items.length - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    @Override
    /** Prints the items in the array deque, separted by space. */
    public void printDeque() {
        int i = plusOne(nextFirst);
        for (int j = 0; j < size; j++) {
            System.out.print(items[i] + " ");
            i = plusOne(i);
        }
        System.out.println();
    }

    /** Resizes the underlying array to the  target capacity. */
    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int j = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[j];
            j = plusOne(j);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    @Override
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * RFACTOR);
        }
        items[nextLast] = x;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    @Override
    /** Removes and returns the item at the back of deque.
     *  Returns null if no such item exits.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        int first = plusOne(nextFirst);
        T x = items[first];
        items[first] = null;
        nextFirst = first;
        size -= 1;

        usageRatio = ((double) size) / items.length;
        if (size >= 8 &&  usageRatio < 0.25) {
            resize(items.length / RFACTOR);
        }

        return x;
    }

    @Override
    /** Removes and returns the item at the back of deque.
     *  Returns null if no such item exits.
     */ 
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        int last = minusOne(nextLast);
        T x = items[last];
        items[last] = null;
        nextLast = last;
        size -= 1;

        usageRatio = ((double) size) / items.length;
        if (size >= 8 && usageRatio < 0.25) {
            resize(items.length / RFACTOR);
        }

        return x;
    }
}


