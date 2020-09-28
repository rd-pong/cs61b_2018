package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        // first, last, and fillCount should all be set to 0.
        // this.capacity should be set appropriately. Note that the local variable
        // here shadows the field we inherit from AbstractBoundedQueue, so
        // you'll need to use this.capacity to set the capacity.
        this.first = 0;
        this.last = 0;
        this.fillCount = 0;
        this.capacity = capacity;
        this.rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            // TODO: exception 1
            throw new RuntimeException("Ring buffer overflow");
        } else {
            this.fillCount++;

            if (this.last < this.rb.length - 1) {
                // if the list is empty before enqueue, then this.last should not change,
                this.rb[this.last] = x;
                this.last++;
            } else if (this.last == this.rb.length - 1) {
                this.rb[this.last] = x;
                this.last = 0;
            } else {
                System.out.println("Something wrong with enqueue");
            }
        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (this.isEmpty()) {
            // TODO: exception 2
            throw new RuntimeException("Ring buffer underflow");
        } else {
            this.fillCount--;
            T returnBuff = rb[this.first];
            if (this.first < this.rb.length - 1) {
                this.rb[this.first] = null;
                this.first++;
            } else if (this.first == this.rb.length - 1) {
                this.rb[this.first] = null;
                this.first = 0;
            } else {
                System.out.println("Something wrong with dequeue");
            }
            return returnBuff;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        return this.rb[this.first];
    }

    public static void main(String[] args) {

    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
