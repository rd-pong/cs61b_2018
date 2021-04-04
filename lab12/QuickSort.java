import edu.princeton.cs.algs4.Queue;

public class QuickSort {
    /**
     * Returns a new queue that contains the given queues catenated together.
     * <p>
     * The items in q2 will be catenated after all of the items in q1.
     */
    private static <Item extends Comparable> Queue<Item> catenate(Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> catenated = new Queue<Item>();
        for (Item item : q1) {
            catenated.enqueue(item);
        }
        for (Item item : q2) {
            catenated.enqueue(item);
        }
        return catenated;
    }

    /**
     * Returns a random item from the given queue.
     */
    private static <Item extends Comparable> Item getRandomItem(Queue<Item> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        Item pivot = null;
        // Walk through the queue to find the item at the given index.
        for (Item item : items) {
            if (pivotIndex == 0) {
                pivot = item;
                break;
            }
            pivotIndex--;
        }
        return pivot;
    }

    /**
     * Partitions the given unsorted queue by pivoting on the given item.
     *
     * @param unsorted A Queue of unsorted items
     * @param pivot    The item to pivot on
     * @param less     An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are less than the given pivot.
     * @param equal    An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are equal to the given pivot.
     * @param greater  An empty Queue. When the function completes, this queue will contain
     *                 all of the items in unsorted that are greater than the given pivot.
     */
    private static <Item extends Comparable> void partition(
            Queue<Item> unsorted, Item pivot,
            Queue<Item> less, Queue<Item> equal, Queue<Item> greater) {
        // Your code here!
        for (Item i : unsorted) {
            if (i.compareTo(pivot) < 0) {
                less.enqueue(i);
            } else if (i.compareTo(pivot) > 0) {
                greater.enqueue(i);
            } else {
                equal.enqueue(i);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> quickSort(Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }

        // Your code here!
        Queue<Item> partLess = new Queue<>();
        Queue<Item> partEqual = new Queue<>();
        Queue<Item> partGreater = new Queue<>();

        //random pivot
        Item pivot = getRandomItem(items);

        // partition on random pivot
        partition(items, pivot, partLess, partEqual, partGreater);

        // concate(quicksort(partLess, partEqual, partGreater))
        items = catenate(catenate(quickSort(partLess), partEqual), quickSort(partGreater));
        return items;
    }

    public static void main(String[] args) {
        Queue<Integer> unsort = new Queue<>();
        unsort.enqueue(7);
        unsort.enqueue(6);
        unsort.enqueue(5);
        unsort.enqueue(4);
        unsort.enqueue(3);
        unsort.enqueue(2);
        unsort.enqueue(1);
        unsort.enqueue(11);
        unsort.enqueue(12);
        unsort.enqueue(13);

        Queue<Integer> partLess = new Queue<>();
        Queue<Integer> partEqual = new Queue<>();
        Queue<Integer> partGreater = new Queue<>();
        partition(unsort, 4, partLess, partEqual, partGreater);
        System.out.println(partLess);
        System.out.println(partEqual);
        System.out.println(partGreater);

        Queue<Integer> sorted = quickSort(unsort);
        System.out.println(sorted.toString());
    }
}
