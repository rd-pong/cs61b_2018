/**
 * Create generic Double-Ended Queue (Deque) using linked list.
 */
public class LinkedListDeque<T> implements Deque<T> {
    private class DequeNode {
        private DequeNode prev;
        private T item;
        private DequeNode next;

        // Constructor
        private DequeNode(DequeNode p, T i, DequeNode n) {
            prev = p;
            item = i;
            next = n;
        }

        private DequeNode() {
        }

        public boolean isEmptyNode() {
            if (this.item == null) {
                return true;
            }
            return false;

        }
    }

    // Instance variable for LinkedListDeque
    private DequeNode sentinel;
    private int size;

    // Constructor for list with first item

    /**
     * Creates an empty linked list deque.
     */
    public LinkedListDeque() {
        this.sentinel = new DequeNode(); // Is it possible to set item to null?
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        this.size = 0;
    }

    /**
     * Adds an item of type T to the front of the deque.
     *
     * @param item that wished to be packed in a node and add as node
     */
    @Override
    public void addFirst(T item) {
        DequeNode originalFirst = this.sentinel.next;
        DequeNode newNode = new DequeNode(sentinel, item, originalFirst);
        originalFirst.prev = newNode;
        this.sentinel.next = newNode;
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     *
     * @param item that wished to be packed in a node and add as node
     */
    @Override
    public void addLast(T item) {
        DequeNode originalLast = this.sentinel.prev;
        DequeNode newNode = new DequeNode(originalLast, item, this.sentinel);
        originalLast.next = newNode;
        this.sentinel.prev = newNode;
        size += 1;
    }

    /**
     * @Returns true if deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        if (this.sentinel.next == this.sentinel) {
            return true;
        }
        return false;

    }

    /**
     * @Return the number of items in the deque.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     */
    @Override
    public void printDeque() {
        DequeNode pointer = this.sentinel;
        while (!pointer.next.isEmptyNode()) {
            System.out.println(pointer.next.item);
            pointer = pointer.next;
        }
    }

    /**
     * Removes and returns the item at the front of the deque. If no such item exists, returns null.
     *
     * @return originalFirst.item
     */
    @Override
    public T removeFirst() {
        DequeNode originalFirst = this.sentinel.next;
        this.sentinel.next = originalFirst.next;
        originalFirst.next.prev = this.sentinel;
        if (!originalFirst.isEmptyNode()) {
            this.size -= 1;
        }
        return originalFirst.item;
    }

    /**
     * Removes and returns the item at the back of the deque. If no such item exists, returns null.
     *
     * @return originalLast.item
     */
    @Override
    public T removeLast() {
        DequeNode originalLast = this.sentinel.prev;
        this.sentinel.prev = originalLast.prev;
        originalLast.prev.next = this.sentinel;
        if (!originalLast.isEmptyNode()) {
            size -= 1;
        }
        return originalLast.item;
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item, and so forth. Must not alter the deque!
     *
     * @Return get node.item, if exists; null, if no such item exists
     */
    @Override
    public T get(int index) {
        DequeNode pointer = this.sentinel;
        int current_index = -1;
        while (current_index < index) {
            pointer = pointer.next;
            current_index++;
        }
        return pointer.item;
    }


    /**
     * Same as get, but uses recursion
     *
     * @Return get node.item, if exists; null, if no such item exists
     */
    private T getRecursiveHelper(DequeNode helpNode, int index) {
        if (index == 0) {
            return helpNode.item;
        } else {
            index--;
            return getRecursiveHelper(helpNode.next, index);
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(this.sentinel.next, index);
    }

    /**
     * Creates a deep copy of other.
     */
    private LinkedListDeque(LinkedListDeque other) {
        this.sentinel = new DequeNode();
        this.sentinel.prev = this.sentinel;
        this.sentinel.next = this.sentinel;
        size = 0;
        for (int i = 0; i < other.size(); i += 1) {
            addLast((T) other.get(i));
        }
    }
}
