import org.junit.Test;

import java.util.Deque;

import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque<Character> d = palindrome.wordToDeque("persiflage");
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
}
