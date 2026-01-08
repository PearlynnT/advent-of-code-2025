package day9;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _Day9Part2 {
    public static long solve(List<String> lines) {
        List<Point> redTiles = new ArrayList<>();
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE;
        int minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;
        for (String line: lines) {
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            redTiles.add(new Point(x, y));
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        int width = (maxX - minX) + 3;
        int height = (maxY - minY) + 3;
        int[][] grid = new int[height][width];
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = redTiles.get(i);
            Point p2 = redTiles.get((i + 1) % lines.size());
            int x1 = p1.x - minX + 1;
            int y1 = p1.y - minY + 1;
            int x2 = p2.x - minX + 1;
            int y2 = p2.y - minY + 1;
            if (x1 == x2) {
                for (int j = Math.min(y1, y2); j <= Math.max(y1, y2); j++) {
                    grid[j][x1] = 1;
                }
            } else {
                for (int j = Math.min(x1, x2); j <= Math.max(x1, x2); j++) {
                    grid[y1][j] = 1;
                }
            }
        }

        // Flood Fill
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[height][width];
        visited[0][0] = true;
        grid[0][0] = 2;
        q.add(new Point(0, 0));
        int[][] adj = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        while (!q.isEmpty()) {
            Point curr = q.poll();
            for (int i = 0; i < adj.length; i++) {
                int adjX = curr.x + adj[i][0];
                int adjY = curr.y + adj[i][1];
                if (adjX >= 0 && adjX < width && adjY >= 0 && adjY < height) {
                    if (!visited[adjY][adjX] && grid[adjY][adjX] != 1) {
                        visited[adjY][adjX] = true;
                        grid[adjY][adjX] = 2;
                        q.add(new Point(adjX, adjY));
                    }
                }
            }
        }

        // Prefix Sum 2D
        int[][] prefixSum = new int[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int value = grid[i][j] != 2 ? 1 : 0;
                if (i > 0) {
                    value += prefixSum[i - 1][j];
                }
                if (j > 0) {
                    value += prefixSum[i][j - 1];
                }
                if (i > 0 && j > 0) {
                    value -= prefixSum[i - 1][j - 1];
                }
                prefixSum[i][j] = value;
            }
        }
        long largestArea = 0;
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = redTiles.get(i);
            int x1 = p1.x - minX + 1;
            int y1 = p1.y - minY + 1;
            for (int j = i + 1; j < lines.size(); j++) {
                Point p2 = redTiles.get(j);
                int x2 = p2.x - minX + 1;
                int y2 = p2.y - minY + 1;
                int left = Math.min(x1, x2);
                int right = Math.max(x1, x2);
                int top = Math.min(y1, y2);
                int bottom = Math.max(y1, y2);
                long area = (long) (right - left + 1) * (bottom - top + 1);
                long sum = prefixSum[bottom][right]
                        - prefixSum[top - 1][right]
                        - prefixSum[bottom][left - 1]
                        + prefixSum[top - 1][left - 1];
                if (area == sum) {
                    if (sum > largestArea) {
                        largestArea = sum;
                    }
                }
            }
        }
        return largestArea;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day9Part2 input.txt");
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
