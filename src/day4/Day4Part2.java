package day4;

import java.nio.file.*;

public class Day4Part2 {
    public static int solve(char[][] grid) {
        int count = 0;
        while (true) {
            boolean removed = false;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == '.') {
                        continue;
                    }
                    int adjRolls = 0;
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (grid[i - 1][j - 1] == '@') adjRolls++;
                    }
                    if (i - 1 >= 0) {
                        if (grid[i - 1][j] == '@') adjRolls++;
                    }
                    if (i - 1 >= 0 && j + 1 < grid[0].length) {
                        if (grid[i - 1][j + 1] == '@') adjRolls++;
                    }
                    if (j - 1 >= 0) {
                        if (grid[i][j - 1] == '@') adjRolls++;
                    }
                    if (j + 1 < grid[0].length) {
                        if (grid[i][j + 1] == '@') adjRolls++;
                    }
                    if (i + 1 < grid.length && j - 1 >= 0) {
                        if (grid[i + 1][j - 1] == '@') adjRolls++;
                    }
                    if (i + 1 < grid.length) {
                        if (grid[i + 1][j] == '@') adjRolls++;
                    }
                    if (i + 1 < grid.length && j + 1 < grid[0].length) {
                        if (grid[i + 1][j + 1] == '@') adjRolls++;
                    }
                    if (adjRolls < 4) {
                        count++;
                        grid[i][j] = '.';
                        removed = true;
                    }
                }
            }
            if (!removed) {
                break;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day4Part2 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            char[][] grid = new char[lines.size()][lines.get(0).length()];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    grid[i][j] = lines.get(i).charAt(j);
                }
            }
            int result = solve(grid);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
