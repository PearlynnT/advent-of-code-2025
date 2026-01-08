package day9;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.TreeSet;

public class Day9Part2 {
    public static long solve(List<String> lines) {
        List<Point> redTiles = new ArrayList<>();
        TreeSet<Integer> xTs = new TreeSet<>();
        TreeSet<Integer> yTs = new TreeSet<>();
        for (String line: lines) {
            String[] parts = line.split(",");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            redTiles.add(new Point(x, y));
            xTs.add(x);
            yTs.add(y);
        }
        List<Integer> sortedX = new ArrayList<>(xTs);
        List<Integer> sortedY = new ArrayList<>(yTs);
        HashMap<Integer, Integer> xHm = new HashMap<>();
        HashMap<Integer, Integer> yHm = new HashMap<>();
        for (int i = 0; i < sortedX.size(); i++) {
            xHm.put(sortedX.get(i), i * 2 + 1);
        }
        for (int i = 0; i < sortedY.size(); i++) {
            yHm.put(sortedY.get(i), i * 2 + 1);
        }
        int numCols = sortedX.size() * 2 + 1;
        int numRows = sortedY.size() * 2 + 1;
        int[][] grid = new int[numRows][numCols];
//        long[][] cellArea = new long[height][width];
        int[] colWidths = new int[numCols];
        int[] rowHeights = new int[numRows];
        colWidths[0] = 1; // padding
        for (int i = 0; i < sortedX.size(); i++) {
            int v = i * 2 + 1;
            colWidths[v] = 1;
            if (i < sortedX.size() - 1) {
                colWidths[v + 1] = sortedX.get(i + 1) - sortedX.get(i) - 1;
            } else {
                colWidths[v + 1] = 1; // trailing padding
            }
        }
        rowHeights[0] = 1; // padding
        for (int i = 0; i < sortedY.size(); i++) {
            int v = i * 2 + 1;
            rowHeights[v] = 1;
            if (i < sortedY.size() - 1) {
                rowHeights[v + 1] = sortedY.get(i + 1) - sortedY.get(i) - 1;
            } else {
                rowHeights[v + 1] = 1; // trailing padding
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = redTiles.get(i);
            Point p2 = redTiles.get((i + 1) % lines.size());
            int c1 = xHm.get(p1.x);
            int r1 = yHm.get(p1.y);
            int c2 = xHm.get(p2.x);
            int r2 = yHm.get(p2.y);
            for (int r = Math.min(r1, r2); r <= Math.max(r1, r2); r++) {
                for (int c = Math.min(c1, c2); c <= Math.max(c1, c2); c++) {
                    grid[r][c] = 1;
                }
            }
        }

        // Flood Fill
        Queue<int[]> q = new LinkedList<>();
        grid[0][0] = 2;
        q.add(new int[]{0, 0});
        int[][] adj = { {1, 0}, {-1, 0}, {0, 1}, {0, -1} };
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int i = 0; i < adj.length; i++) {
                int adjR = curr[0] + adj[i][0];
                int adjC = curr[1] + adj[i][1];
                if (adjR >= 0 && adjR < numRows && adjC >= 0 && adjC < numCols) {
                    if (grid[adjR][adjC] == 0) {
                        grid[adjR][adjC] = 2;
                        q.add(new int[]{adjR, adjC});
                    }
                }
            }
        }

        // Prefix Sum 2D
        long[][] prefixSum = new long[numRows][numCols];
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                int value = grid[r][c] != 2 ? rowHeights[r] * colWidths[c] : 0;
                if (r > 0) {
                    value += prefixSum[r - 1][c];
                }
                if (c > 0) {
                    value += prefixSum[r][c - 1];
                }
                if (r > 0 && c > 0) {
                    value -= prefixSum[r - 1][c - 1];
                }
                prefixSum[r][c] = value;
            }
        }
        long largestArea = 0;
        for (int i = 0; i < lines.size(); i++) {
            Point p1 = redTiles.get(i);
            for (int j = i + 1; j < lines.size(); j++) {
                Point p2 = redTiles.get(j);
                int left = Math.min(p1.x, p2.x);
                int right = Math.max(p1.x, p2.x);
                int top = Math.min(p1.y, p2.y);
                int bottom = Math.max(p1.y, p2.y);
                long area = (long) (right - left + 1) * (bottom - top + 1);
                int cMin = xHm.get(left);
                int cMax = xHm.get(right);
                int rMin = yHm.get(top);
                int rMax = yHm.get(bottom);
                long sum = prefixSum[rMax][cMax]
                        - prefixSum[rMin - 1][cMax]
                        - prefixSum[rMax][cMin - 1]
                        + prefixSum[rMin - 1][cMin - 1];
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
