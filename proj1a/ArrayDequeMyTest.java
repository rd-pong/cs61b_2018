public class ArrayDequeMyTest {
    public static void addTest() {
        ArrayDeque<String> testADeque = new ArrayDeque<>();
        System.out.println(testADeque.isEmpty());
        testADeque.addLast("1");
        System.out.println(testADeque.isEmpty());
        testADeque.addLast("2");
        testADeque.addLast("3");
        testADeque.addLast("4");
        testADeque.addLast("5");
        testADeque.addLast("6");
        testADeque.addLast("7");
        testADeque.addLast("8");
        testADeque.addLast("9");
        testADeque.addLast("10");
        testADeque.addLast("11");
        testADeque.addLast("l2");
        testADeque.addLast("l3");
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
        testADeque.printDeque();

    }

    public static void main(String[] args) {
        addTest();
    }
}
