package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import static ru.leetcode.TaskDifficulty.MEDIUM;

public class LongestSubstringWithoutRepeatingCharacters extends LeetcodeTaskSolution<Integer, String> {
    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters impl = new LongestSubstringWithoutRepeatingCharacters();
        impl.test();
    }

    @Override
    public Integer solution(String s) {
        return getLongestSubstringWithoutRepeatingCharactersLen(s);
    }

    private int getLongestSubstringWithoutRepeatingCharactersLen(String s) {
        int[] charsLastIndexes = new int[128];
        int maxLen = 0;
        int currentLen = 0;
        for (int i = 1; i <= s.length(); i++) {
            int currentCharLastIndex = charsLastIndexes[s.charAt(i - 1)];

            if (charsLastIndexes[s.charAt(i - 1)] == 0) {
                currentLen++;
            } else {
                currentLen = Math.min(i - currentCharLastIndex, currentLen + 1);
            }

            if (currentLen > maxLen) {
                maxLen = currentLen;
            }
            charsLastIndexes[s.charAt(i - 1)] = i;
        }
        return maxLen;
    }

    @Override
    public String getTestData() {
        return "pwwkew";
    }

    @Override
    public Integer getExpectedResult() {
        return 3;
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return MEDIUM;
    }

    public String getUrl() {
        return "https://leetcode.com/problems/partitioning-into-minimum-number-of-deci-binary-numbers/";
    }
}
