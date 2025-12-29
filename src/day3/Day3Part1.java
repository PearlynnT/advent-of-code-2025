package day3;

import java.nio.file.*;
import java.util.List;

public class Day3Part1 {
    public static int solve(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            int max = 0;
            for (int i = 1; i < line.length(); i++) {
                for (int j = 0; j < i; j++) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(line.charAt(j));
                    sb.append(line.charAt(i));
                    int joltage = Integer.parseInt(sb.toString());
                    if (joltage > max) {
                        max = joltage;
                    }
                }
            }
            total += max;
        }
        return total;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day3Part1 input.txt");
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
