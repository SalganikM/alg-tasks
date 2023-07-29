package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

public class BinomialExpansion extends CodeWarsTaskSolution<String, String> {
    public static void main(String[] args) {
        BinomialExpansion impl = new BinomialExpansion();
        System.out.println(impl.solution("(-n-12)^5"));
        impl.test();
    }

    // (ax + b) ^ n
    @Override
    public String solution(String expr) {
        long a = extractA(expr);
        long b = extractB(expr);
        long n = extractN(expr);
        char x = extractX(expr, a);
        if (n == 0) {
            return "1";
        }
        StringBuilder result = new StringBuilder();
        for (long i = n; i >= 0; i--) {
            String k = getKef(a, i, b, n - i);
            if (k.equals("0") || k.equals("-0")) {
                continue;
            }
            result.append(k);
            result.append(getXInGrade(x, i));
        }
        return result.toString();
    }

    private long extractA(String expr) {
        String a = expr.split("[a-zA-Z]")[0].substring(1);
        if (a.length() == 0) {
            return 1;
        }
        if (a.equals("-")) {
            return -1;
        }
        if (a.contains("-")) {
            long result = Long.parseLong(a.substring(1));
            return -result;
        }
        return Long.parseLong(a);
    }

    private long extractB(String expr) {
        String afterX = expr.split("[a-zA-Z]")[1];
        String b = afterX.substring(0, afterX.indexOf(")"));
        if (b.contains("-")) {
            long result = Long.parseLong(b.substring(1));
            return -result;
        }
        return Long.parseLong(b);
    }

    private long extractN(String expr) {
        return Long.parseLong(expr.substring(expr.indexOf("^") + 1));
    }

    private char extractX(String expr, long a) {
        if (a == 1) {
            return expr.charAt(1);
        }
        if (a == -1) {
            return expr.charAt(2);
        }
        return expr.split("\\d+")[1].charAt(0);
    }

    private String getKef(long a, long gradeA, long b, long gradeB) {
        long result = 1;
        result *= getCNK(gradeA, gradeA + gradeB);
        result *= Math.pow(a, gradeA);
        result *= Math.pow(b, gradeB);
        if (result == 1 && gradeA != 0) {
            if (gradeB != 0) {
                return "+";
            }
            return "";
        }
        if (result == -1 && gradeA != 0) {
            return "-";
        }
        if (result > 0 && gradeB != 0) {
            return "+" + result;
        }
        return String.valueOf(result);
    }

    private String getXInGrade(char x, long n) {
        if (n == 0) {
            return "";
        }
        if (n == 1) {
            return String.valueOf(x);
        }
        return x + "^" + n;
    }

    private long getCNK(long k, long n) {
        long result = 1;
        for (long i = 0; i < k; i++) {
            result *= (n - i);
            result /= (1 + i);
        }
        return result;
    }

    @Override
    public String getTestData() {
        return "(-2k+3)^3";
    }

    @Override
    public String getExpectedResult() {
        return "-8k^3+36k^2-54k+27";
    }

    @Override
    public int getTaskLvl() {
        return 3;
    }
}
