package day2;

import java.nio.file.*;

public class Day2Part1 {
    public static long solve(String[] ranges) {
        long result = 0;
        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long i = start; i <= end; i++) {
                int len = Long.toString(i).length();
                if (len % 2 != 0) {
                    continue;
                }
                if (Long.toString(i).substring(0, len / 2).equals(Long.toString(i).substring(len / 2))) {
                    result += i;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day2Part1 input.txt");
            return;
        }

        try {
            String input = Files.readString(Path.of(args[0]));
            String[] ranges = input.split(",");
            long result = solve(ranges);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
