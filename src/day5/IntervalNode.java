package day5;

public class IntervalNode {
    long start;
    long end;
    long maxEnd;
    IntervalNode left, right;

    IntervalNode(long start, long end) {
        this.start = start;
        this.end = end;
        this.maxEnd = end;
        left = right = null;
    }
}
