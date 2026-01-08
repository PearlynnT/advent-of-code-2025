package day8;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day8Part2 {
    public static long solve(List<String> lines) {
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
        long result = 0;
        for (Connection c: connections) {
            uf.union(c.p1, c.p2);
            if (uf.getNumDisjoint() == 1) {
                result = points.get(c.p1).x * points.get(c.p2).x;
                break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day8Part2 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            long result = solve(lines);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
