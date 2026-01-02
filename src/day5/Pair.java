package day5;

public class Pair implements Comparable<Pair> {
    long start;
    long end;

    Pair(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public int compareTo(Pair other) {
        return Long.compare(this.start, other.start);
    }
}
