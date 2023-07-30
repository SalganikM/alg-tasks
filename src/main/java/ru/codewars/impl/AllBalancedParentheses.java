package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.util.HashSet;
import java.util.Set;

public class AllBalancedParentheses extends CodeWarsTaskSolution<Set<String>, Integer> {
    public static void main(String[] args) {
        AllBalancedParentheses impl = new AllBalancedParentheses();
        impl.test();
    }

    @Override
    public Set<String> solution(Integer n) {
        Set<String> result = new HashSet<>();
        recursiveBuildResults(result, "", n * 2, n, n, 0);
        return result;
    }

    private void recursiveBuildResults(Set<String> results, String currentResult,
                                       int remainingBracketsCount, int remainingCloseBracketsCount,
                                       int remainingOpenBracketsCount, int availableCloseBrackets) {
        if (remainingBracketsCount == 0) {
            results.add(currentResult);
            return;
        }
        if (remainingOpenBracketsCount != 0) {
            recursiveBuildResults(results, currentResult + "(", remainingBracketsCount - 1,
                    remainingCloseBracketsCount, remainingOpenBracketsCount - 1, availableCloseBrackets + 1);
        }
        if (remainingCloseBracketsCount != 0 && availableCloseBrackets != 0) {
            recursiveBuildResults(results, currentResult + ")", remainingBracketsCount - 1,
                    remainingCloseBracketsCount - 1, remainingOpenBracketsCount, availableCloseBrackets - 1);
        }
    }

    @Override
    public Integer getTestData() {
        return 4;
    }

    @Override
    public Set<String> getExpectedResult() {
        return Set.of("(((())))", "((()()))", "((())())", "((()))()", "(()(()))", "(()()())",
                "(()())()", "(())(())", "(())()()", "()((()))", "()(()())", "()(())()", "()()(())", "()()()()");
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }
}
