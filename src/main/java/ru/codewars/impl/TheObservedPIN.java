package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.util.*;

public class TheObservedPIN extends CodeWarsTaskSolution<Set<String>, String> {
    private static final Map<Character, Set<Character>> adjacentMap = getAdjacentMap();

    public static void main(String[] args) {
        TheObservedPIN impl = new TheObservedPIN();
        impl.test();
    }

    @Override
    public Set<String> solution(String value) {
        Set<String> result = new HashSet<>();
        recursiveIterateThroughPossibleResults(result, value, value.length(), 0);
        return result;
    }

    private void recursiveIterateThroughPossibleResults(Set<String> results, String currentValue, int len, int currentLen) {
        if (currentLen == len) {
            results.add(currentValue);
            return;
        }
        Set<Character> adjacentChars = adjacentMap.get(currentValue.charAt(currentLen));
        for (char c : adjacentChars) {
            StringBuilder newValue = new StringBuilder(currentValue);
            newValue.replace(currentLen, currentLen + 1, String.valueOf(c));
            recursiveIterateThroughPossibleResults(results, newValue.toString(), len, currentLen + 1);
        }
    }

    private static Map<Character, Set<Character>> getAdjacentMap() {
        return Map.of('1', Set.of('1', '2', '4'),
                '2', Set.of('1', '2', '3', '5'),
                '3', Set.of('2', '3', '6'),
                '4', Set.of('1', '4', '5', '7'),
                '5', Set.of('2', '4', '5', '6', '8'),
                '6', Set.of('3', '5', '6', '9'),
                '7', Set.of('4', '7', '8'),
                '8', Set.of('5', '7', '8', '9', '0'),
                '9', Set.of('6', '8', '9'),
                '0', Set.of('0', '8'));
    }

    @Override
    public String getTestData() {
        return "369";
    }

    @Override
    public Set<String> getExpectedResult() {
        return Set.of("236", "238", "239", "256", "258", "259", "266", "268", "269",
                "296", "298", "299", "336", "338", "339", "356", "358", "359", "366", "368",
                "369", "396", "398", "399", "636", "638", "639", "656", "658", "659", "666",
                "668", "669", "696", "698", "699");
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }
}
