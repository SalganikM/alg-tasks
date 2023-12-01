package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import static ru.leetcode.TaskDifficulty.EASY;

public class ThousandSeparator extends LeetcodeTaskSolution<String, Integer> {
    public static void main(String[] args) {
        ThousandSeparator impl = new ThousandSeparator();
        impl.test();
    }

    @Override
    public String solution(Integer n) {
        char[] chars = String.valueOf(n).toCharArray();
        if (chars.length < 4) {
            return new String(chars);
        }

        int currentModule = 3 - chars.length % 3 == 3 ? 0 : 3 - chars.length % 3;
        int i = 0;
        StringBuilder result = new StringBuilder();
        while (i < chars.length) {
            if (currentModule == 3) {
                currentModule = 0;
                result.append('.');
            }
            result.append(chars[i]);
            currentModule++;
            i++;
        }
        return result.toString();
    }

    @Override
    public Integer getTestData() {
        return 123456;
    }

    @Override
    public String getExpectedResult() {
        return "123.456";
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return EASY;
    }

    @Override
    public String getUrl() {
        return "https://leetcode.com/problems/thousand-separator/description/";
    }
}
