package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import java.util.HashSet;
import java.util.Set;

import static ru.leetcode.TaskDifficulty.HARD;
import static ru.leetcode.TaskDifficulty.MEDIUM;

public class LongestValidParentheses extends LeetcodeTaskSolution<Integer, String> {
    public static void main(String[] args) {
        LongestValidParentheses impl = new LongestValidParentheses();
        impl.test();
    }

    @Override
    public Integer solution(String n) {
        return getLongestValidParenthesesLen(n);
    }

    private int getLongestValidParenthesesLen(String parentheses) {
        char[] chars = parentheses.toCharArray();
        int longestClosedParenthesesLen = 0;
        int currentParenthesesLen = 0;
        int currentParenthesesStart = 0;
        int closedBracketsAvailable = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '(') {
                currentParenthesesLen++;
                closedBracketsAvailable++;
                continue;
            }

            if (closedBracketsAvailable == 0) {
                currentParenthesesStart = i + 1;
                longestClosedParenthesesLen = Math.max(currentParenthesesLen, longestClosedParenthesesLen);
                currentParenthesesLen = 0;
                continue;
            }

            currentParenthesesLen++;
            closedBracketsAvailable--;
        }

        int maxValidParenthesesParenthesesLen = 0;
        int currentInvertedParenthesesStart = chars.length - 1;
        int difference = 0;
        for (int i = chars.length - 1; i >= currentParenthesesStart; i--) {
            if (chars[i] == '(') {
                difference++;
            } else {
                difference--;
            }
            if (difference > 0) {
                difference = 0;
                currentInvertedParenthesesStart = i - 1;
                continue;
            }
            if (difference == 0 && (currentInvertedParenthesesStart + 1 - i) > maxValidParenthesesParenthesesLen) {
                maxValidParenthesesParenthesesLen = currentInvertedParenthesesStart + 1 - i;
            }
        }

        return Math.max(maxValidParenthesesParenthesesLen, longestClosedParenthesesLen);
    }

    @Override
    public String getTestData() {
        return "(()))())(";
    }

    @Override
    public Integer getExpectedResult() {
        return 4;
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return HARD;
    }

    public String getUrl() {
        return "https://leetcode.com/problems/longest-valid-parentheses/";
    }
}
