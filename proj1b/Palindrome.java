public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dChar = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dChar.addLast(word.charAt(i));
        }
        return dChar;
    }

    public boolean isPalindrome(String word) {
//        Deque<Character> dChar = new LinkedListDeque<>();
        String reverse = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reverse += word.charAt(i);
        }
        return reverse.equals(word);
    }
}
