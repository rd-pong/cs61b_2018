public class ArrayDequeMyTest {
    public static void addTest() {
        ArrayDeque<Integer> testADeque = new ArrayDeque<>();

        testADeque.addLast(1);
        testADeque.addLast(2);
        testADeque.addLast(3);
        testADeque.addLast(4);
        testADeque.addLast(5);
        testADeque.addLast(6);
        testADeque.addLast(7);
        testADeque.addLast(8);
        testADeque.addLast(9);
        testADeque.addLast(10);
        testADeque.addLast(11);
        testADeque.addLast(12);
        testADeque.addLast(13);
        testADeque.printDeque();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.removeLast();
        testADeque.addFirst(3);
        testADeque.addFirst(4);
        testADeque.removeLast();
        testADeque.removeFirst();
        testADeque.removeLast();
        testADeque.removeFirst();
    }

    public static void main(String[] args) {
        addTest();
    }
}
