package synthesizer;

public abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {
    protected int fillCount;
    protected int capacity;

    public int capacity() {
        return this.capacity;
    }

    public int fillCount() {
        return this.fillCount;
    }

    /* The following are inherited from BoundedQueue, so you should not
    to declare these explicitly in your AbstractBoundedQueue.java file.
    public boolean isEmpty()
    public boolean isFull()
    public abstract T peek();
    public abstract T dequeue();
    public abstract void enqueue(T x);*/
}
