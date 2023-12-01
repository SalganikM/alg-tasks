package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import static ru.leetcode.TaskDifficulty.MEDIUM;

public class PartitioningIntoMinimumNumberOfDeciBinaryNumbers extends LeetcodeTaskSolution<Integer, String> {
    public static void main(String[] args) {
        PartitioningIntoMinimumNumberOfDeciBinaryNumbers impl = new PartitioningIntoMinimumNumberOfDeciBinaryNumbers();
        impl.test();
    }

    @Override
    public Integer solution(String n) {
        return getBiggestDigitInString(n);
    }

    private int getBiggestDigitInString(String n) {
        return n.chars().max().getAsInt() - 48;
    }

    @Override
    public String getTestData() {
        return "27346209830709182346";
    }

    @Override
    public Integer getExpectedResult() {
        return 9;
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return MEDIUM;
    }

    public String getUrl() {
        return "https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/";
    }
}
