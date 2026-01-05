package day8;

public class Connection implements Comparable<Connection> {
    int p1, p2;
    double distance;

    Connection(int p1, int p2, double distance) {
        this.p1 = p1;
        this.p2 = p2;
        this.distance = distance;
    }

    @Override
    public int compareTo(Connection other) {
        return Double.compare(this.distance, other.distance);
    }
}
