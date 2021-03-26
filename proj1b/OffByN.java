public class OffByN implements CharacterComparator {
    int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    /** Returns true for characters that are different by exactly N. */
    public boolean equalChars(char x, char y) {
        int diff = Math.abs(x - y);
        if (diff == N) {
            return true;
        }
        return false;
    }
}
