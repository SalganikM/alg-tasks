package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import java.util.HashSet;
import java.util.Set;

import static ru.leetcode.TaskDifficulty.MEDIUM;

public class GenerateParentheses extends LeetcodeTaskSolution<Set<String>, Integer> {
    public static void main(String[] args) {
        GenerateParentheses impl = new GenerateParentheses();
        impl.test();
    }

    @Override
    public Set<String> solution(Integer n) {
        Set<String> result = new HashSet<>();
        generateParentheses(result, new StringBuilder(), 2 * n, 0, n);
        return result;
    }

    private void generateParentheses(Set<String> results, StringBuilder currentResult, int bracketsAvailable, int rightBracketsAvailable, int leftBracketsAvailable) {
        if (bracketsAvailable == 0) {
            results.add(currentResult.toString());
            return;
        }

        if (rightBracketsAvailable == 0) {
            currentResult.append('(');
            generateParentheses(results, currentResult, bracketsAvailable - 1, rightBracketsAvailable + 1, leftBracketsAvailable - 1);
        } else if (leftBracketsAvailable == 0) {
            currentResult.append(')');
            generateParentheses(results, currentResult, bracketsAvailable - 1, rightBracketsAvailable - 1, leftBracketsAvailable);
        } else {
            StringBuilder secondResult = new StringBuilder(currentResult);
            currentResult.append('(');
            secondResult.append(')');
            generateParentheses(results, currentResult, bracketsAvailable - 1, rightBracketsAvailable + 1, leftBracketsAvailable - 1);
            generateParentheses(results, secondResult, bracketsAvailable - 1, rightBracketsAvailable - 1, leftBracketsAvailable);
        }
    }

    @Override
    public Integer getTestData() {
        return 3;
    }

    @Override
    public Set<String> getExpectedResult() {
        return Set.of("((()))", "(()())", "(())()", "()(())", "()()()");
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return MEDIUM;
    }

    public String getUrl() {
        return "https://leetcode.com/problems/generate-parentheses/";
    }
}
