public class Palindrome {

    /** Returns a Deque of the given String word. */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> wordDeque = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i++) {
            wordDeque.addLast(word.charAt(i));
        }
        return wordDeque;
    }

    /** Returns true if the word is a palindrome. */
    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        int dequeSize = wordDeque.size();
        for (int i = 0; i < (dequeSize / 2); i++) {
            if (wordDeque.removeFirst() != wordDeque.removeLast()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> wordDeque = wordToDeque(word);
        int dequeSize = wordDeque.size();
        for (int i = 0; i < (dequeSize / 2); i++) {
            if (!cc.equalChars(wordDeque.removeFirst(), wordDeque.removeLast())) {
                return false;
            }
        }
        return true;
    }
}

