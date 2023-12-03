package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;
import ru.util.Pair;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static ru.leetcode.TaskDifficulty.MEDIUM;

public class ZigzagConversion extends LeetcodeTaskSolution<String, Pair<String, Integer>> {
    public static void main(String[] args) {
        ZigzagConversion impl = new ZigzagConversion();
        impl.test();
    }

    @Override
    public String solution(Pair<String, Integer> arg) {
        return generateZigzagConversion(arg.getFirst(), arg.getSecond());
    }

    private String generateZigzagConversion(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int numRowsMinusTwo = numRows > 1 ? numRows - 2 : 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            int index = i;

            if (i == 0 || i == numRows - 1) {
                while (index < s.length()) {
                    result.append(s.charAt(index));
                    index += numRowsMinusTwo * 2 + 2;
                }
                continue;
            }

            boolean oddStep = true;
            while (index < s.length()) {
                int addition = oddStep ? (numRows - i - 1) : i;
                result.append(s.charAt(index));
                index += addition * 2;
                oddStep = !oddStep;
            }
        }
        return result.toString();
    }

    @Override
    public Pair<String, Integer> getTestData() {
        return new Pair<>("PAYPALISHIRING", 4);
    }

    @Override
    public String getExpectedResult() {
        return "PINALSIGYAHRPI";
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return MEDIUM;
    }

    public String getUrl() {
        return "https://leetcode.com/problems/generate-parentheses/";
    }
}
