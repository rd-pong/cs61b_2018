public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> charDeque = new ArrayDeque<>();
        int strLength = word.length();
        for (int i = 0; i < strLength; i++) {
            charDeque.addLast(word.charAt(i));
        }
        return charDeque;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private static boolean isPalindromeHelper(Deque<Character> deque) {
        while (deque.size() > 1) {
            return deque.removeLast() == deque.removeFirst() && isPalindromeHelper(deque);
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> d = wordToDeque(word);
        return isPalindromeHelper(d, cc);
    }

    private static boolean isPalindromeHelper(Deque<Character> deque, CharacterComparator cc) {
        while (deque.size() > 1) {
            return cc.equalChars(deque.removeLast(), deque.removeFirst()) && isPalindromeHelper(deque, cc);
        }
        return true;
    }

}
