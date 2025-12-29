package day3;

import java.nio.file.*;
import java.util.List;

public class Day3Part2 {
    public static long solve(List<String> lines) {
        long total = 0;
        for (String line : lines) {
            int[] idxArr = new int[12];
            int largestIdx = -1;
            for (int i = 0; i < 12; i++) {
                int max = 0;
                for (int j = largestIdx + 1; j <= line.length() - 12 + i; j++) {
                    if ((line.charAt(j) - '0') > max) {
                        max = line.charAt(j) - '0';
                        largestIdx = j;
                    }
                }
                idxArr[i] = largestIdx;
            }
            for (int i = 0; i < 12; i++) {
                total += (line.charAt(idxArr[i]) - '0') * (long) Math.pow(10, 11 - i);
            }
        }
        return total;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day3Part2 input.txt");
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
