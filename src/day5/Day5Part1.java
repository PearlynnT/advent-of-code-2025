package day5;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Day5Part1 {
    public static int solve(List<String> freshIdRanges, List<String> availableIds) {
        IntervalTree tree = new IntervalTree();
        for (String range : freshIdRanges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            tree.insert(start, end);
        }
        int count = 0;
        for (String id : availableIds) {
            if (tree.contains(Long.parseLong(id))) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day5Part1 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            List<String> freshIdRanges = new ArrayList<>();
            List<String> availableIds = new ArrayList<>();
            int i = 0;
            while (!lines.get(i).isEmpty()) {
                freshIdRanges.add(lines.get(i));
                i++;
            }
            i++;
            while (i < lines.size()) {
                availableIds.add(lines.get(i));
                i++;
            }
            int result = solve(freshIdRanges, availableIds);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
