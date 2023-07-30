package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NextBiggerNumberWithSameDigits extends CodeWarsTaskSolution<Long, Long> {
    public static void main(String[] args) {
        NextBiggerNumberWithSameDigits impl = new NextBiggerNumberWithSameDigits();
        impl.test();
    }

    @Override
    public Long solution(Long arg) {
        String number = arg.toString();
        char[] digits = number.toCharArray();
        List<Character> digitsAfterSwapPoint = new ArrayList<>();
        for (int i = digits.length - 2; i >= 0; i--) {
            digitsAfterSwapPoint.add(digits[i + 1]);
            if (digits[i] < digits[i + 1]) {
                digitsAfterSwapPoint.add(digits[i]);
                return buildResult(digits, i, digitsAfterSwapPoint);
            }
        }
        return -1L;
    }

    private Long buildResult(char[] digits, int swapPoint, List<Character> digitsAfterSwapPoint) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < swapPoint; i++) {
            resultString.append(digits[i]);
        }
        digitsAfterSwapPoint.sort(Comparator.naturalOrder());
        for (int i = 0; i < digitsAfterSwapPoint.size(); i++) {
            if (digitsAfterSwapPoint.get(i) > digits[swapPoint]) {
                resultString.append(digitsAfterSwapPoint.get(i));
                digitsAfterSwapPoint.remove(i);
                break;
            }
        }
        for (char c : digitsAfterSwapPoint) {
            resultString.append(c);
        }
        return Long.valueOf(resultString.toString());
    }

    @Override
    public Long getTestData() {
        return 10990L;
    }

    @Override
    public Long getExpectedResult() {
        return 19009L;
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }
}
