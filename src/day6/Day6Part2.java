package day6;

import java.math.BigInteger;
import java.nio.file.*;
import java.util.List;

public class Day6Part2 {
    public static BigInteger solve(List<String> lines) {
        BigInteger total = BigInteger.ZERO;
        int lenLongest = 0;
        for (String line : lines) {
            lenLongest = Math.max(lenLongest, line.length());
        }
        String symbol = null;
        BigInteger ans = BigInteger.ZERO;
        for (int i = 0; i < lenLongest; i++) {
            boolean isDigit = false;
            if (i < lines.get(lines.size() - 1).length()) {
                if (lines.get(lines.size() - 1).charAt(i) == '+') {
                    symbol = "+";
                    ans = BigInteger.ZERO;
                } else if (lines.get(lines.size() - 1).charAt(i) == '*') {
                    symbol = "*";
                    ans = BigInteger.ONE;
                }
            }
            BigInteger value = BigInteger.ZERO;
            for (int j = 0; j < lines.size() - 1; j++) {
                if (i < lines.get(j).length()) {
                    if (Character.isDigit(lines.get(j).charAt(i))) {
                        isDigit = true;
                        value = value.multiply(BigInteger.TEN).add(BigInteger.valueOf((lines.get(j).charAt(i) - '0')));
                    }
                }
            }
            if (isDigit) {
                if (symbol.equals("+")) {
                    ans = ans.add(value);
                } else {
                    ans = ans.multiply(value);
                }
            } else {
                total = total.add(ans);
            }
        }
        total = total.add(ans);
        return total;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java Day6Part2 input.txt");
            return;
        }

        try {
            var lines = Files.readAllLines(Path.of(args[0]));
            BigInteger result = solve(lines);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
