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

    @Test
    public void testIterator() {
        ArrayRingBuffer<Double> arb = new ArrayRingBuffer(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue((double) i);
        }
        arb.dequeue();
        arb.dequeue();
        arb.dequeue();
        arb.enqueue(25.0);
        for (Double a : arb) {
            System.out.println(a);
        }
    }

    @Test
    public void testEquals() {
        ArrayRingBuffer<Double> arb1 = new ArrayRingBuffer(10);
        ArrayRingBuffer<Double> arb2 = new ArrayRingBuffer(10);
        for (int i = 0; i < 10; i++) {
            arb1.enqueue((double) i);
        }
        for (int i = 0; i < 10; i++) {
            arb2.enqueue((double) i);
        }
        assertTrue(arb1.equals(arb2));

        ArrayRingBuffer<Double> arb3 = new ArrayRingBuffer(10);
        for (int i = 0; i < 10; i++) {
            arb3.enqueue((double) 2.0);
        }
        assertFalse(arb1.equals(arb3));

        assertFalse(arb1.equals("fish"));

        assertFalse(arb1.equals(null));
    }
}
