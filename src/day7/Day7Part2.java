package day7;

import java.nio.file.*;
import java.util.List;

public class Day7Part2 {
    public static long solve(List<String> lines) {
        long[][] countMat = new long[lines.size()][lines.get(0).length()];
        countMat[0][lines.get(0).length() / 2] = 1;
        countMat[1][lines.get(0).length() / 2] = 1;
        StringBuilder sb = new StringBuilder(lines.get(1));
        sb.setCharAt(lines.get(0).length() / 2, '|');
        for (int i = 2; i < lines.size(); i++) {
            lines.set(i - 1, sb.toString());
            sb = new StringBuilder(lines.get(i));
            for (int j = 0; j < lines.get(0).length(); j++) {
                if (lines.get(i).charAt(j) == '^' && lines.get(i - 1).charAt(j) == '|') {
                    if (j - 1 >= 0) {
                        sb.setCharAt(j - 1, '|');
                        countMat[i][j - 1] += countMat[i - 1][j];
                    }
                    if (j + 1 < lines.get(0).length()) {
                        sb.setCharAt(j + 1, '|');
                        countMat[i][j + 1] += countMat[i - 1][j];
                    }
                } else if (lines.get(i).charAt(j) == '.' && lines.get(i - 1).charAt(j) == '|') {
                    sb.setCharAt(j, '|');
                    countMat[i][j] += countMat[i - 1][j];
                }
            }
        }
        long count = 0;
        for (int i = 0; i < lines.get(0).length(); i++) {
            count += countMat[lines.size() - 1][i];
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day7Part2 input.txt");
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
