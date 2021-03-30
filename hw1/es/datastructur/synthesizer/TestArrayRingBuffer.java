package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void testEnqueue() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(10);
        arb.enqueue(10.0);
        arb.enqueue(20.0);
    }

    @Test
    public void testDequeue() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        arb.enqueue(10);
        arb.enqueue(20);
        assertEquals(10, (int) arb.dequeue());
        assertEquals(20, (int) arb.dequeue());
    }

    @Test
    public void testPeek() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(10);
        arb.enqueue(10.0);
        arb.enqueue(20.0);
        assertEquals(10, (double) arb.peek(), 0.1);
        assertEquals(10, (double) arb.peek(), 0.1);
        arb.dequeue();
        assertEquals(20, (double) arb.peek(), 0.1);
        arb.dequeue();
        //assertEquals(20, (double) arb.peek(), 0.1);
    }

}
