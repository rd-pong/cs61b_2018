public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int N) {
        this.N = N;
    }

    @Override
    public boolean equalChars(char a, char b) {
        return a == b + this.N || a == b - this.N;
    }


}
