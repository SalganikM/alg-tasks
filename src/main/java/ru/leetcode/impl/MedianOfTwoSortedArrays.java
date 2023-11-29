package ru.leetcode.impl;

import ru.leetcode.LeetcodeTaskSolution;
import ru.leetcode.TaskDifficulty;
import ru.util.Pair;

import static ru.leetcode.TaskDifficulty.HARD;

public class MedianOfTwoSortedArrays extends LeetcodeTaskSolution<Double, Pair<int[], int[]>> {
    public static void main(String[] args) {
        MedianOfTwoSortedArrays impl = new MedianOfTwoSortedArrays();
        impl.test();
    }

    @Override
    public Double solution(Pair<int[], int[]> arrays) {
        return calculateMedian(arrays.getFirst(), arrays.getSecond());
    }

    private double calculateMedian(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len2 > len1) {
            return calculateMedian(nums2, nums1);
        }

        int len = len1 + len2;
        int median = len / 2;
        int left = 0, right = len2;

        while (right >= left) {
            int median2 = (left + right) / 2;
            int median1 = median - median2;

            int l2 = median2 == 0 ? Integer.MIN_VALUE : nums2[median2 - 1];
            int r2 = (len2 == median2 || len2 == 0) ? Integer.MAX_VALUE : nums2[median2];
            int l1 = median1 == 0 ? Integer.MIN_VALUE : nums1[median1 - 1];
            int r1 = median1 == len1 ? Integer.MAX_VALUE : nums1[median1];

            if (l1 <= r2 && l2 <= r1) {
                return len % 2 == 0 ? ((double) (Math.max(l2, l1) + Math.min(r1, r2)) / 2) : Math.min(r1, r2);
            } else if (r2 < l1) {
                left = (left + right + 1) / 2;
            } else {
                right = (left + right) / 2;
            }
        }

        return 0;
    }

    @Override
    public Double getExpectedResult() {
        return 3.5;
    }

    @Override
    public Pair<int[], int[]> getTestData() {
        return new Pair<>(new int[]{1, 4, 5}, new int[]{2, 3, 6});
    }

    @Override
    public TaskDifficulty getTaskDifficulty() {
        return HARD;
    }

    public String getUrl() {
        return "https://leetcode.com/problems/median-of-two-sorted-arrays/";
    }
}
