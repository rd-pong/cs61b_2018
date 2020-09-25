package synthesizer;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> testRing = new ArrayRingBuffer<>(7);
        testRing.enqueue(1);
        testRing.enqueue(2);
        testRing.enqueue(3);
        testRing.dequeue();
        testRing.dequeue();
        testRing.enqueue(4);
        testRing.enqueue(5);
        testRing.enqueue(6);
        testRing.enqueue(7);
        testRing.enqueue(8);
        testRing.enqueue(9);
        testRing.dequeue();
        testRing.dequeue();
        testRing.dequeue();
        testRing.dequeue();
        testRing.dequeue();
        testRing.dequeue();
        testRing.enqueue(1);
        testRing.enqueue(2);
        System.out.println(testRing.peek());
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
