package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

import static ru.leetcode.TaskDifficulty.MEDIUM;

public class FindTheOriginalArrayOfPrefixXor extends LeetcodeTaskSolution<int[], int[]> {
    public static void main(String[] args) {
        FindTheOriginalArrayOfPrefixXor impl = new FindTheOriginalArrayOfPrefixXor();
        impl.test();
    }

    @Override
    public int[] solution(int[] xor) {
        return constructOriginalArray(xor);
    }

    private int[] constructOriginalArray(int[] xor) {
        AtomicReference<Integer> previous = new AtomicReference<>(null);
        return Arrays.stream(xor).
                map(elem -> {
                    int result =  elem;
                    if(previous.get() != null) {
                        result =  elem ^ previous.get();
                    }
                    previous.set(elem);
                    return result;
                }).toArray();
    }

    @Override
    public int[] getTestData() {
        return new int[]{5, 2, 0, 3, 1};
    }

    @Override
    public int[] getExpectedResult() {
        return new int[]{5, 7, 2, 3, 2};
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return MEDIUM;
    }

    public String getDescription() {
        return "https://leetcode.com/problems/find-the-original-array-of-prefix-xor/";
    }
}
