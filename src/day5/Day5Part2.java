package day5;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day5Part2 {
    public static long solve(List<String> ranges) {
        List<Pair> rangesLst = new ArrayList<>();
        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            rangesLst.add(new Pair(start, end));
        }
        Collections.sort(rangesLst);
        long count = 0;
        long currStart = rangesLst.get(0).start;
        long currEnd = rangesLst.get(0).end;
        for (int i = 1; i < rangesLst.size(); i++) {
            if (rangesLst.get(i).start <= currEnd) {
                currEnd = Math.max(currEnd, rangesLst.get(i).end);
            } else {
                count += (currEnd - currStart + 1);
                currStart = rangesLst.get(i).start;
                currEnd = rangesLst.get(i).end;
            }
        }
        count += (currEnd - currStart + 1);
        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day5Part2 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            List<String> ranges = new ArrayList<>();
            int i = 0;
            while (!lines.get(i).isEmpty()) {
                ranges.add(lines.get(i));
                i++;
            }
            long result = solve(ranges);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
