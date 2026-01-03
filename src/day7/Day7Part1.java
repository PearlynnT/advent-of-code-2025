package day7;

import java.nio.file.*;
import java.util.List;

public class Day7Part1 {
    public static int solve(List<String> lines) {
        StringBuilder sb = new StringBuilder(lines.get(1));
        sb.setCharAt(lines.get(0).length() / 2, '|');
        int count = 0;
        for (int i = 2; i < lines.size(); i++) {
            lines.set(i - 1, sb.toString());
            sb = new StringBuilder(lines.get(i));
            for (int j = 0; j < lines.get(0).length(); j++) {
                if (lines.get(i).charAt(j) == '^' && lines.get(i - 1).charAt(j) == '|') {
                    count++;
                    if (j - 1 >= 0) {
                        sb.setCharAt(j - 1, '|');
                    }
                    if (j + 1 < lines.get(0).length()) {
                        sb.setCharAt(j + 1, '|');
                    }
                } else if (lines.get(i).charAt(j) == '.' && lines.get(i - 1).charAt(j) == '|') {
                    sb.setCharAt(j, '|');
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day7Part1 input.txt");
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
