package synthesizer;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Tests the ArrayRingBuffer class.
 *
 * @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void queueTest() {
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
        testRing.enqueue(1);
        testRing.enqueue(2);
        System.out.println(testRing.peek());
    }

    @Test
    public void iteratorTest() {
        ArrayRingBuffer<Integer> intList = new ArrayRingBuffer<>(10);
        intList.enqueue(0);
        intList.enqueue(1);
        intList.enqueue(2);
        intList.enqueue(3);
        intList.enqueue(4);
        intList.enqueue(5);
        intList.enqueue(6);
        intList.enqueue(7);
        intList.enqueue(8);
        intList.enqueue(9);
        Iterator<Integer> seer = intList.iterator();
        while (seer.hasNext()) {
            System.out.println(seer.next());
        }
    }

    /**
     * Calls tests for ArrayRingBuffer.
     */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
