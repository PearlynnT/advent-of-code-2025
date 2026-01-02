package day5;

public class IntervalTree {
    IntervalNode root;

    public IntervalTree() {
        root = null;
    }

    void insert(long start, long end) {
        root = insertRec(root, start, end);
    }

    private IntervalNode insertRec(IntervalNode node, long start, long end) {
        if (node == null) {
            return new IntervalNode(start, end);
        }

        if (start < node.start) {
            node.left = insertRec(node.left, start, end);
        } else {
            node.right = insertRec(node.right, start, end);
        }

        node.maxEnd = Math.max(node.maxEnd, end);
        return node;
    }

    boolean contains(long value) {
        return containsRec(root, value);
    }

    private boolean containsRec(IntervalNode node, long value) {
        if (node == null) {
            return false;
        }

        if (value >= node.start && value <= node.end) {
            return true;
        }

        if (node.left != null && node.left.maxEnd >= value) {
            return containsRec(node.left, value);
        }

        return containsRec(node.right, value);
    }
}
