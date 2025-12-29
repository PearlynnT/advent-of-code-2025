package day1;

import java.nio.file.*;
import java.util.List;

public class Day1Part2 {
    public static int solve(List<String> lines) {
        int count = 0;
        int ptr = 50;
        for (String line : lines) {
            for (int i = 0; i < Integer.parseInt(line.substring(1)); i++) {
                if (line.charAt(0) == 'L') {
                    ptr--;
                } else {
                    ptr++;
                }
                if (ptr == 100) {
                    ptr = 0;
                }
                if (ptr == -1) {
                    ptr = 99;
                }
                if (ptr == 0) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day1Part2 input.txt");
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
