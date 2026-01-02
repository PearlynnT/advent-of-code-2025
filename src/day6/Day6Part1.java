package day6;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class Day6Part1 {
    public static long solve(List<List<String>> cols) {
        long total = 0;
        for (List<String> col : cols) {
            String symbol = col.get(col.size() - 1);
            long ans = Integer.parseInt(col.get(0));
            for (int i = 1; i < col.size() - 1; i++) {
                if (symbol.equals("+")) {
                    ans += Integer.parseInt(col.get(i));
                } else {
                    ans *= Integer.parseInt(col.get(i));
                }
            }
            total += ans;
        }
        return total;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day6Part1 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            List<List<String>> cols = new ArrayList<>();
            for (String line : lines) {
                String[] items = line.trim().split("\\s+");
                for (int i = 0; i < items.length; i++) {
                    if (cols.size() <= i) {
                        cols.add(new ArrayList<>());
                    }
                    cols.get(i).add(items[i]);
                }
            }
            long result = solve(cols);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
