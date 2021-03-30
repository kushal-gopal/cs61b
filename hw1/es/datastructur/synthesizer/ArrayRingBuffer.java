package es.datastructur.synthesizer;
import java.util.Iterator;

/**
 * Invariants : First item in the buffer is at 'first' variable.
 * Last item is at 'last' - 1.
 * Next item to be enqueued is at 'last' variable.
 */
public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    @Override
    /* Returns the size of the buffer. */
    public int capacity() {
        return rb.length;
    }

    @Override
    /* Returns the numbers of items in the buffer. */
    public int fillCount() {
        return fillCount;
    }

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    @Override
    /*
      Adds x to the end of the ring buffer. If there is no room, then
      throw new RuntimeException("Ring buffer overflow").
     */
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last = (last + 1) % rb.length;
    }

    @Override
    /*
      Dequeue oldest item in the ring buffer. If the buffer is empty, then
      throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T toReturn = rb[first];
        first = (first + 1) % rb.length;
        fillCount -= 1;
        return toReturn;
    }

    @Override
    /*
      Return oldest item, but don't remove it. If the buffer is empty, then
      throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    /** Private class which implements Iterator methods. */
    private class ArrayRingBufferIterator implements Iterator<T> {
        int pos;

        public ArrayRingBufferIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < fillCount;
        }

        @Override
        public T next() {
            T toReturn = rb[(pos + first) % rb.length];
            pos += 1;
            return toReturn;
        }
    }

    /* Returns an iterator for ArrayRingBuffer. */
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
    // TODO: When you get to part 4, implement the needed code to support
    //       iteration and equals.

    @Override
    /* Return true if both the array ring buffers contain same values. */
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (this.fillCount != o.fillCount || this.capacity() != o.capacity()) {
            return false;
        }
        for (int i = 0; i < this.fillCount; i++) {
            int pos1 = (this.first + i) % this.capacity();
            int pos2 = (o.first + i) % this.capacity();
            if (!this.rb[pos1].equals(o.rb[pos2])) {
                return false;
            }
        }
        return true;
    }
}
