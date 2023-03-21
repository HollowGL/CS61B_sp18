public class OffByN implements CharacterComparator {

    int n;
    public OffByN(int N) {
        n = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        return (isChar(x) && isChar(y)) && (x - y == n || x - y == -n);
    }
}
