package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;

import static ru.leetcode.TaskDifficulty.MEDIUM;

public class LongestPalindromicSubstring extends LeetcodeTaskSolution<String, String> {
    public static void main(String[] args) {
        LongestPalindromicSubstring impl = new LongestPalindromicSubstring();
        impl.test();
    }

    @Override
    public String solution(String s) {
        return extractLongestPalindrome(s);
    }

    private String extractLongestPalindrome(String s) {
        if (s.length() == 1) {
            return s;
        }

        s = s.replaceAll("", " ");
        int[] longestPalindromeRadioWithCenterInIndex = new int[s.length()];
        int center = 0;
        int rightmostKnownPalindromeBoundary = 0;
        String result = "";

        for (int i = 0; i < s.length(); i++) {
            if (rightmostKnownPalindromeBoundary < i) {
                rightmostKnownPalindromeBoundary = i;
                center = i;
            }

            // [left] .... [center * 2 - i] ....... [center] ....... [i] .... [right]
            // if inside our big palindrome centered in [center] we have a palindrome centered in [center * 2 - i] then we also have an equal palindrome centered in [i]
            longestPalindromeRadioWithCenterInIndex[i] = Math.min(rightmostKnownPalindromeBoundary - i, longestPalindromeRadioWithCenterInIndex[center * 2 - i]);

            while (i - longestPalindromeRadioWithCenterInIndex[i] > 0 &&
                    i + longestPalindromeRadioWithCenterInIndex[i] + 1 < s.length() &&
                    s.charAt(i - longestPalindromeRadioWithCenterInIndex[i] - 1) == s.charAt(i + longestPalindromeRadioWithCenterInIndex[i] + 1)) {
                longestPalindromeRadioWithCenterInIndex[i]++;
            }

            if (i + longestPalindromeRadioWithCenterInIndex[i] > rightmostKnownPalindromeBoundary) {
                rightmostKnownPalindromeBoundary = i + longestPalindromeRadioWithCenterInIndex[i];
                center = i;
            }

            if (longestPalindromeRadioWithCenterInIndex[i] > result.length()) {
                result = s.substring(i - longestPalindromeRadioWithCenterInIndex[i], i + longestPalindromeRadioWithCenterInIndex[i] + 1).replaceAll(" ", "");
            }
        }

        return result;
    }

    @Override
    public String getTestData() {
        return "aacabdkacaa";
    }

    @Override
    public String getExpectedResult() {
        return "aca";
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return MEDIUM;
    }

    public String getDescription() {
        return "https://leetcode.com/problems/longest-palindromic-substring/";
    }
}
