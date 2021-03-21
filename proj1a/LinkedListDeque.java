public class LinkedListDeque<T> {
    private class Node {
        T item;
        Node next;
        Node prev;
        Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private Node sentinel;
    private int size;

    /** Creates an empty Deque. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Creates a deep copy of other */
    /*
    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new Node(null, sentinel, sentinel);
        size = 0;
        Node p = other.sentinel;
        while (p.next != other.sentinel) {
            addFirst((T) p.next.item);
            p = p.next;
            size += 1;
        }
    }
    */

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    /** Returns true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size != 0) {
            return false;
        }
        return true;
    }

    /** Returns the numebr of items in the deque. */
    public int size() {
        return size;
    }

    /** Prints the items in the deque from first to last,
     *  spearated by a space. Once all the items have been printed,
     *  a newline is printed.
     */
    public void printDeque() {
        Node p = sentinel;
        while (p.next != sentinel) {
            System.out.print(p.next.item + " ");
            p = p.next;
        }
        System.out.println();
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }

        T removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }

        T removed = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed;
    }

    /** Gets the item at the given index(zero-indexed).
     *  Returns null, if no item exists, return null.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }

        Node p = sentinel.next;
        while (index != 0) {
            p = p.next;
            index -= 1;
        }
        return p.item;
    }

    /** Returns the item at the given index from node p */
    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(p.next, index - 1);
    }

    /** Same as get, but uses recursion. */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursive(sentinel.next, index);
    }

}
