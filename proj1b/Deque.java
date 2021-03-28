/** Deque Interface */
public interface Deque<T> {

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item);

    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item);

    /** Returns true if deque is empty, false otherwise. */
    public default boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size();

    /** Prints the items in the deque from first to last,
     *  separated by a space. Once all the items have been printed,
     *  a newline is printed.
     */
    public void printDeque();

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns null.
     */
    public T removeFirst();

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns null.
     */
    public T removeLast();

    /** Gets the item at the given index(zero-indexed).
     *  If no item exists, return null.
     */
    public T get(int index);
}

