public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return isChar(x) && isChar(y) && (x - y == 1 || x - y == -1);
    }


}
