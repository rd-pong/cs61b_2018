public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

//    /**
//     * Resize the deque.
//     */
//    private void resize(int capacity) {
//        T[] newDeque = (T[]) new Object[capacity];
//        int oldIndex = nextFirst + 1; // the index of the first item in original deque
//        for (int newIndex = 0; newIndex < size; newIndex++) {
//            newDeque[newIndex] = items[oldIndex];
//            oldIndex = oldIndex + 1;
//        }
//        items = newDeque;
//        nextFirst = capacity - 1; // since the new deque is starting from true 0 index.
//        nextLast = size;
//    }

    // method 'expand' and 'shrink' are combined to resize
    public void expand(int capacity) {
        T[] a = (T[]) new Object[capacity];
        System.arraycopy(this.items, 0, a, 0, nextLast);
        System.arraycopy(this.items, nextFirst, a, capacity - (items.length - this.nextFirst), items.length - nextFirst);
        this.nextFirst = capacity - (items.length - nextFirst);
        this.items = a;

    }

    // method 'expand' and 'shrink' are combined to resize
    public void shrink(int capacity) {
        T[] a = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            a[i] = this.get(i);
        }
        this.items = a;
        this.nextFirst = capacity - 1;
        this.nextLast = size;
    }

    public void addFirst(T item) {
        if (nextLast == nextFirst) {
            // nextLast reaches nextFirst, expand array
            shrink(items.length * 2);
            // change array properties
            this.items[nextFirst] = item;
            this.nextFirst -= 1;
            this.size += 1;
        } else if (nextFirst == 0) {
            // fill the feed in item as last of the items[] list
            items[nextFirst] = item;
            size += 1;
            // then change nextLast to the start of items[] list
            this.nextFirst = items.length - 1;
        } else {
            items[nextFirst] = item;
            nextFirst -= 1;
            size += 1;
        }
    }

    public void addLast(T item) {
        if (nextLast == nextFirst) {
            // nextLast reaches nextFirst, expand array
            shrink(items.length * 2);
            // change array properties
            this.items[nextLast] = item;
            this.nextLast += 1;
            this.size += 1;
        } else if (nextLast == items.length - 1) {
            // fill the feed in item as last of the items[] list
            items[nextLast] = item;
            size += 1;
            // then change nextLast to the start of items[] list
            this.nextLast = 0;
        } else {
            items[nextLast] = item;
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

    public T removeFirst() {
        size -= 1;
        T x = (T) new Object();
        if (nextFirst == items.length - 1) {
            x = items[0];
            items[0] = null;
            this.nextFirst = 0;
        } else {
            x = items[nextFirst + 1];
            items[nextFirst + 1] = null;
            this.nextFirst += 1;
        }
        // usage factor check
        double usageRatio = (double) this.size / this.items.length;
        if (usageRatio < 0.25) {
            shrink((int) (items.length / 2));
        }
        return x;
    }

    public T removeLast() {
        size -= 1;
        T x = (T) new Object();
        if (nextLast == 0) {
            x = items[items.length - 1];
            items[items.length - 1] = null;
            this.nextLast = items.length - 1;
        } else {
            x = items[nextLast - 1];
            items[nextLast - 1] = null;
            this.nextLast -= 1;
        }
        // usage factor check
        double usageRatio = (double) this.size / this.items.length;
        if (usageRatio < 0.25) {
            shrink((int) (items.length / 2));
        }
        return x;
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


}
