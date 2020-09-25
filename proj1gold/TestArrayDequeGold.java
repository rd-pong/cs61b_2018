import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @ClassName TestArrayDequeGold
 * @Description TODO
 * @Author Yixiang Zhao
 * @Date 2018/7/29 7:16
 * @Version 1.0
 */
public class TestArrayDequeGold {
    @Test
    public void testArratDeque() {
        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        // addLast
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addLast(random);
            sad.addLast(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("addFirst(\n)", actual, expected, actual);
        }

        // addFirst
        for (int i = 0; i < 10; i++) {
            int random = StdRandom.uniform(100);
            ads.addFirst(random);
            sad.addFirst(random);
        }
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("addFirst(\n)", actual, expected, actual);
        }

        // removeFirst
        List<Integer> actualList = new ArrayList<>();
        List<Integer> expectedList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            actualList.add(ads.removeFirst());
            expectedList.add(sad.removeFirst());
        }
        // check correctness of the rest of the list
        for (int i = 0; i < 10; i++) {
            int actual = ads.get(i);
            int expected = sad.get(i);
            assertEquals("removeFirst()", expected, actual);
        }
        // check correctness of removed list
        for (int i = 0; i < 10; i++) {
            int actual = actualList.get(i);
            int expected = expectedList.get(i);
            assertEquals("removeFirst()", expected, actual);
        }


        // removeLast
        actualList.clear();
        expectedList.clear();
        for (int i = 0; i < 10; i++) {
            actualList.add(ads.removeLast());
            expectedList.add(sad.removeLast());
        }
        int actual = ads.size();
        int expected = sad.size();
        assertEquals("removeLast()", expected, actual);
        for (int i = 0; i < 10; i++) {
            assertEquals("removeLast()", expectedList.get(i), actualList.get(i));
        }

    }


//    @Test
//    public void testArratDeque2() {
//        ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
//        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
//        int random = StdRandom.uniform(100);
//        ads.addFirst(random);
//        sad.addFirst(random);
//        assertEquals("addFirst(" + random + ")", ads.get(0), sad.get(0));
//        System.out.println("addFirst(" + random + ")");
//
//        random = StdRandom.uniform(100);
//        ads.addLast(random);
//        sad.addLast(random);
//        assertEquals("addLast(" + random + ")", ads.get(1), sad.get(1));
//        System.out.println("addLast(" + random + ")");
//
//        int actual = ads.removeFirst();
//        int expected = ads.removeFirst();
//        assertEquals("removeFirst()", actual, expected);
//        System.out.println("removeFirst()");
//
//        actual = ads.removeLast();
//        expected = sad.removeLast();
//        assertEquals("removeLast()", actual, expected);
//        System.out.println("removeLast()");
//    }


}