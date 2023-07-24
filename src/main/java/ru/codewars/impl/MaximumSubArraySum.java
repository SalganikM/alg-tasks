package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

public class MaximumSubArraySum extends CodeWarsTaskSolution<Integer, int[]> {
    public static void main(String[] args) {
        MaximumSubArraySum impl = new MaximumSubArraySum();
        impl.test();
        impl.simpleSpeedTestWithRepetitions(1000000);
    }

    @Override
    public Integer solution(int[] array) {
        int currentSum = 0;
        int resultSum = 0;
        for (int j : array) {
            currentSum += j;
            if (resultSum < currentSum) {
                resultSum = currentSum;
            }
            if (currentSum < 0) {
                currentSum = 0;
            }
        }
        return resultSum;
    }

    @Override
    public int[] getTestData() {
        return new int[]{-21, -21, 29, 14, -25, -10, 9,
                1, 9, -25, -11, -14, -1, -27, -4, 2, -3, 7, -7, -17, 9, 1, 10, 25, -29, 6, -
                21, -7, 25, 0, 23, -4, -10, -19, -23, 18, -22, 3, -7, -23, -22, -1, -23, -10,
                19, 10, -22, 19, -16, 17};
    }

    @Override
    public Integer getExpectedResult() {
        return 48;
    }

    @Override
    public int getTaskLvl() {
        return 5;
    }

    private int primitiveSolution(int[] arr) {
        int result = 0;
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (j == i) {
                    sum = arr[i];
                    if (result < sum) result = sum;
                    continue;
                }
                sum = sum + arr[j];
                if (result < sum) result = sum;
                if (j == arr.length - 1) sum = 0;
            }
        }
        return result;
    }
}
