public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dChar = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            dChar.addLast(word.charAt(i));
        }
        return dChar;
    }

    public boolean isPalindrome(String word) {
        String reverse = "";
        for (int i = word.length() - 1; i >= 0; i--) {
            reverse += word.charAt(i);
        }
        return reverse.equals(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int len = word.length();
        if (len == 0 || len == 1) {
            return true;
        }
        for (int i = 0; i < len / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(len - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
