package day8;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8Part1 {
    public static int solve(List<String> lines) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            points.add(new Point(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), i));
        }
        List<Connection> connections = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = points.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Point p2 = points.get(j);
                double distance = p1.distanceTo(p2);
                connections.add(new Connection(i, j, distance));
            }
        }
        Collections.sort(connections);
        UnionFind uf = new UnionFind(points.size());

        // modify this value
        int numConnections = 1000;
        for (int i = 0; i < numConnections; i++) {
            Connection c = connections.get(i);
            if (uf.find(c.p1) == uf.find(c.p2)) {
                continue;
            }
            uf.union(c.p1, c.p2);
        }
        List<Integer> circuitSizes = uf.getComponentSizes();
        Collections.sort(circuitSizes, Collections.reverseOrder());
        int result = 1;
        for (int i = 0; i < 3; i++) {
            result *= circuitSizes.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day8Part1 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            int result = solve(lines);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
