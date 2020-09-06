public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[6];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(this.items, 0, a, 0, nextLast);
        System.arraycopy(this.items, nextFirst, a, capacity - (items.length - this.nextFirst), items.length - nextFirst);
        this.nextFirst = capacity - (items.length - nextFirst);
        this.items = a;

    }

    public void addLast(T x) {
        if (nextLast == items.length - 1) {
            // fill the feed in item as last of the items[] list
            items[nextLast] = x;
            size += 1;
            // then change nextLast to the start of items[] list
            this.nextLast = 0;
        } else if (nextLast == nextFirst) {
            // nextLast reaches nextFirst, expand array
            resize(items.length * 2);
            // change array properties
            this.items[nextLast] = x;
            this.nextLast += 1;
            this.size += 1;
        } else {
            items[nextLast] = x;
            nextLast += 1;
            size += 1;
        }
    }

    public boolean isEmpty() {
        if (size <= 0)
            return true;
        else
            return false;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.println(this.get(i));
        }
    }

    public T get(int index) {
        int buffIndex = index + nextFirst + 1; // arraydeque index -->array index
        if (index < size && buffIndex < items.length) {
            return items[buffIndex];
        } else if (index < size) {
            return items[index - (items.length - 1 - nextFirst)];
        } else {
            return null;
        }

    }

    public T getLast() {
        return items[nextLast - 1];
    }

    public T removeLast() {
        T x = getLast();
        size -= 1;
        items[nextLast - 1] = null;
        this.nextLast -= 1;

        // usage factor check
        double usageRatio = (double) this.size / this.items.length;
        if (usageRatio < 0.25) {
            resize((int) (items.length / 2));
        }

        return x;
    }


//    public void addFirst(T item): Adds an item of type T to the front of the deque.
//    public boolean isEmpty(): Returns true if deque is empty, false otherwise.
//    public void printDeque(): Prints the items in the deque from first to last, separated by a space.
//    public T removeFirst(): Removes and returns the item at the front of the deque. If no such item exists, returns null.


}
