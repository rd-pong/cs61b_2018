import org.junit.Test;

import java.util.Deque;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        /* Original line be like: Deque d = palindrome.wordToDeque("persiflage");
         * but sometime encountered: "不兼容类型：java.lang.Deque无法转换为String"
         * 这是因为String类是java中本就存在的类，所以自己起类名的时候不要起java中已有的类名
         * 解决方案: Deque<Character> d = palindrome.wordToDeque("persiflage");*/
        Deque d = palindrome.wordToDeque("persiflage");

        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        assertEquals(true, palindrome.isPalindrome("a"));
        assertEquals(true, palindrome.isPalindrome("racecar"));
        assertEquals(true, palindrome.isPalindrome("noon"));
        assertEquals(false, palindrome.isPalindrome("horse"));
        assertEquals(false, palindrome.isPalindrome("rancor"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertEquals(false, palindrome.isPalindrome("Aa"));
    }

    @Test
    public void testNewIsPalindrome() {
        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("", obo));
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("zyzy", obo));
        assertTrue(palindrome.isPalindrome("yyxz", obo));
        assertTrue(palindrome.isPalindrome("yyyxz", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("xyz", obo));
        assertFalse(palindrome.isPalindrome("aa", obo));
        assertFalse(palindrome.isPalindrome("zxzx", obo));
        assertEquals(true, palindrome.isPalindrome("flake", obo));
    }
}
