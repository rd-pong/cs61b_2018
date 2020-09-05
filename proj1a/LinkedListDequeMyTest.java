public class LinkedListDequeMyTest {
    public static void addFirstTest() {
        LinkedListDeque<String> testLLDeque = new LinkedListDeque<>();
        testLLDeque.addFirst("Item 4");
        testLLDeque.addFirst("Item 3");
        testLLDeque.addFirst("Item 2");
        testLLDeque.addFirst("Item 1");
        testLLDeque.printDeque();
    }

    public static void addLastTest() {
        LinkedListDeque<String> testLLDeque = new LinkedListDeque<>();
        testLLDeque.addLast("Item 1");
        testLLDeque.addLast("Item 2");
        testLLDeque.addLast("Item 3");
        testLLDeque.addLast("Item 4");
        testLLDeque.printDeque();
    }

    public static void isEmptyTest() {
        LinkedListDeque<String> testLLDeque = new LinkedListDeque<>();
        System.out.println(testLLDeque.isEmpty()); // true
        testLLDeque.printDeque();

        testLLDeque.addLast("item 1");
        System.out.println(testLLDeque.isEmpty()); // false
        testLLDeque.printDeque();
    }

    public static void removeLastTest() {
        LinkedListDeque<String> testLLDeque = new LinkedListDeque<>();
        testLLDeque.addLast("Item 1");
        testLLDeque.addLast("Item 2");
        testLLDeque.addLast("Item 3");
        testLLDeque.addLast("Item 4");
        testLLDeque.printDeque();

        testLLDeque.removeFirst();
        testLLDeque.printDeque();
        testLLDeque.removeLast();
        testLLDeque.printDeque();
        testLLDeque.removeLast();
        testLLDeque.printDeque();
    }

    public static void getTest() {
        LinkedListDeque<String> testLLDeque = new LinkedListDeque<>();
        testLLDeque.addLast("Item 1");
        testLLDeque.addLast("Item 2");
        testLLDeque.addLast("Item 3");
        testLLDeque.addLast("Item 4");
        System.out.println(testLLDeque.get(0));
        System.out.println(testLLDeque.get(1));
        System.out.println(testLLDeque.get(3));
        System.out.println(testLLDeque.get(4));
    }

    public static void getRecursiveTest() {
        LinkedListDeque<String> testLLDeque = new LinkedListDeque<>();
        testLLDeque.addLast("Item 1");
        testLLDeque.addLast("Item 2");
        testLLDeque.addLast("Item 3");
        testLLDeque.addLast("Item 4");
        System.out.println(testLLDeque.getRecursive(0));
        System.out.println(testLLDeque.getRecursive(1));
        System.out.println(testLLDeque.getRecursive(3));
        System.out.println(testLLDeque.getRecursive(4));
    }

    public static void main(String[] args) {
        // addFirstTest();
        // addLastTest();
        // isEmptyTest();
        // removeLastTest();
        getTest();
        System.out.println();
        getRecursiveTest();
    }

}