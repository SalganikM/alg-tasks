package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class AlphabeticAnagrams extends CodeWarsTaskSolution<BigInteger, String> {
    public static void main(String[] args) {
        AlphabeticAnagrams impl = new AlphabeticAnagrams();
        impl.test();
    }

    @Override
    public BigInteger solution(String word) {
        BigInteger result = BigInteger.ZERO;
        char[] chars = word.toCharArray();
        Map<Character, Integer> charCounts = getCharCounts(chars);
        for (char c : chars) {
            for (char aChar : charCounts.keySet()) {
                if (aChar < c) {
                    Map<Character, Integer> charCountsWithoutAChar = new HashMap<>(charCounts);
                    if (charCountsWithoutAChar.get(aChar) == 1) {
                        charCountsWithoutAChar.remove(aChar);
                    } else {
                        charCountsWithoutAChar.put(aChar, charCountsWithoutAChar.get(aChar) - 1);
                    }
                    result = result.add(getNumberOfPossibleCombinationForSymbols(charCountsWithoutAChar));
                }
            }
            if (charCounts.get(c) == 1) {
                charCounts.remove(c);
            } else {
                charCounts.put(c, charCounts.get(c) - 1);
            }
        }
        return result.add(BigInteger.ONE);
    }

    private Map<Character, Integer> getCharCounts(char[] chars) {
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char aChar : chars) {
            if (!charCounts.containsKey(aChar)) {
                charCounts.put(aChar, 1);
                continue;
            }
            charCounts.put(aChar, charCounts.get(aChar) + 1);
        }
        return charCounts;
    }

    private BigInteger getNumberOfPossibleCombinationForSymbols(Map<Character, Integer> charCounts) {
        int total = 0;
        for (int i : charCounts.values()) {
            total += i;
        }
        BigInteger result = getFactorial(total);
        for (int i : charCounts.values()) {
            if (i > 1) {
                result = result.divide(getFactorial(i));
            }
        }
        return result;
    }

    private BigInteger getFactorial(int n) {
        BigInteger result = BigInteger.ONE;
        while (n > 1) {
            result = result.multiply(BigInteger.valueOf(n));
            n--;
        }
        return result;
    }

    @Override
    public String getTestData() {
        return "BOOKKEEPER";
    }

    @Override
    public BigInteger getExpectedResult() {
        return BigInteger.valueOf(10743);
    }

    @Override
    public int getTaskLvl() {
        return 3;
    }
}
