package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrailingZerosInFactorials extends CodeWarsTaskSolution<BigInteger, List<BigInteger>> {
    public static void main(String[] args) {
        TrailingZerosInFactorials impl = new TrailingZerosInFactorials();
        impl.test();
    }

    @Override
    public BigInteger solution(List<BigInteger> arg) {
        return solution(arg.get(0), arg.get(1));
    }

    public BigInteger solution(BigInteger value, BigInteger base) {
        Map<BigInteger, Long> plurals = getPluralsMap(base);
        BigInteger result = value;
        for (Map.Entry<BigInteger, Long> entry : plurals.entrySet()) {
            BigInteger currentPluralResult = BigInteger.ZERO;
            BigInteger biggestPluralGrade = entry.getKey();
            while (true) {
                if (biggestPluralGrade.compareTo(value) > 0) {
                    break;
                }
                currentPluralResult = currentPluralResult.add(value.divide(biggestPluralGrade));
                biggestPluralGrade = biggestPluralGrade.multiply(entry.getKey());
            }
            currentPluralResult = currentPluralResult.divide(BigInteger.valueOf(entry.getValue()));
            if (currentPluralResult.compareTo(result) < 0) {
                result = currentPluralResult;
            }
        }
        return result;
    }

    private Map<BigInteger, Long> getPluralsMap(BigInteger value) {
        Map<BigInteger, Long> pluralMap = new HashMap<>();
        for (BigInteger i = BigInteger.TWO; i.compareTo(value.sqrt()) <= 0; i = i.add(BigInteger.ONE)) {
            while (value.divide(i).multiply(i).equals(value)) {
                if (pluralMap.containsKey(i)) {
                    pluralMap.put(i, pluralMap.get(i) + 1);
                } else {
                    pluralMap.put(i, 1L);
                }
                value = value.divide(i);
            }
        }
        if (value.compareTo(BigInteger.ONE) > 0) {
            pluralMap.put(value, 1L);
        }
        return pluralMap;
    }

    @Override
    public List<BigInteger> getTestData() {
        return List.of(BigInteger.valueOf(15L), BigInteger.valueOf(12));
    }

    @Override
    public BigInteger getExpectedResult() {
        return BigInteger.valueOf(5);
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }
}
