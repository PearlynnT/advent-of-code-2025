package day9;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day9Part1 {
    public static long solve(List<String> lines) {
        List<Long> areas = new ArrayList<>();
        for (int i = 0; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            int x1 = Integer.parseInt(parts[0]);
            int y1 = Integer.parseInt(parts[1]);
            for (int j = i + 1; j < lines.size(); j++) {
                parts = lines.get(j).split(",");
                int x2 = Integer.parseInt(parts[0]);
                int y2 = Integer.parseInt(parts[1]);
                areas.add((long) (Math.abs(x2 - x1) + 1) * (Math.abs(y2 - y1) + 1));
            }
        }
        areas.sort(Comparator.reverseOrder());
        return areas.get(0);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day9Part1 input.txt");
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
