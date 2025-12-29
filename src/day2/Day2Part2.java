package day2;

import java.nio.file.*;

public class Day2Part2 {
    public static long solve(String[] ranges) {
        long result = 0;
        for (String range : ranges) {
            String[] parts = range.split("-");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            for (long i = start; i <= end; i++) {
                int len = Long.toString(i).length();
                for (int j = 1; j <= len / 2; j++) {
                    if (len % j != 0) {
                        continue;
                    }
                    int leftPtr = 0;
                    int rightPtr = j;
                    boolean isRepeated = true;
                    while (rightPtr <= len) {
                        if (!(Long.toString(i).substring(leftPtr, rightPtr).equals(Long.toString(i).substring(0, j)))) {
                            isRepeated = false;
                            break;
                        }
                        leftPtr += j;
                        rightPtr += j;
                    }
                    if (isRepeated) {
                        result += i;
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day2Part2 input.txt");
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
