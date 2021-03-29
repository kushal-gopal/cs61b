package es.datastructur.synthesizer;

public interface BoundedQueue<T> {

    /** Returns the size of the buffer. */
    int capacity();

    /** Returns the numbers of items in the buffer. */
    int fillCount();

    /** Adds item x to the end. */
    void enqueue(T x);

    /** Delete and returns the items from the front. */
    T dequeue();

    /** Returns the item from the front without deleting it. */
    T peek();

    /** Returns true if the buffer is empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /** Returns true if the buffer is full. */
    default boolean isFull() {
        return fillCount() == capacity();
    }
}


