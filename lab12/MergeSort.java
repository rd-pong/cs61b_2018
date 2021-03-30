import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     * <p>
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /**
     * Returns a queue of queues that each contain one item from items.
     */
    private static <Item extends Comparable> Queue<Queue<Item>> makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> qOfQs = new Queue<>();
        for (Item i : items) {
            Queue<Item> itemQueue = new Queue<>();
            itemQueue.enqueue(i);
            qOfQs.enqueue(itemQueue);
        }
        return qOfQs;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     * <p>
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param q1 A Queue in sorted order from least to greatest.
     * @param q2 A Queue in sorted order from least to greatest.
     * @return A Queue containing all of the q1 and q2 in sorted order, from least to
     * greatest.
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        int q1size = q1.size();
        int q2size = q2.size();
        Queue<Item> sorted = new Queue<>();
        while (sorted.size() < q1size + q2size) {
            sorted.enqueue(getMin(q1, q2));
        }
        return sorted;
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     */
    public static <Item extends Comparable> Queue<Item> mergeSort(Queue<Item> items) {
        //
        if (items.size() <= 1) {
            return items;
        }
        // split into 2
        Queue<Item> q1 = new Queue<>();
        Queue<Item> q2 = new Queue<>();
        for (int i = 0; i < items.size() / 2; i++) {
            q1.enqueue(items.dequeue());
        }
        q2 = items;

        // merge sorted half
        Queue<Item> ret = mergeSortedQueues(mergeSort(q1), mergeSort(q2));
        return ret;
    }

    public static void main(String[] args) {
        Queue<Integer> unsort1 = new Queue<>();
        unsort1.enqueue(2);
        unsort1.enqueue(15);
        unsort1.enqueue(17);
        unsort1.enqueue(32);
        unsort1.enqueue(1);

        Queue<Integer> unsort2 = new Queue<>();
        unsort2.enqueue(17);
        unsort2.enqueue(17);
        unsort2.enqueue(19);
        unsort2.enqueue(26);
        unsort2.enqueue(41);

        Queue<Integer> unsort = new Queue<>();
        unsort.enqueue(7);
        unsort.enqueue(6);
        unsort.enqueue(5);
        unsort.enqueue(4);
        unsort.enqueue(3);
        unsort.enqueue(2);
        unsort.enqueue(1);

//        System.out.println(mergeSortedQueues(unsort1, unsort2).toString());
        Queue<Integer> sorted = mergeSort(unsort);
        System.out.println(sorted.toString());
    }
}
