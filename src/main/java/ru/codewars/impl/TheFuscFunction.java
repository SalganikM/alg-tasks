package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

import java.math.BigInteger;

public class TheFuscFunction extends CodeWarsTaskSolution<BigInteger, BigInteger> {
    public static void main(String[] args) {
        TheFuscFunction impl = new TheFuscFunction();
        impl.test();
        impl.simpleSpeedTestWithRepetitions(10000);
    }

    @Override
    public BigInteger solution(BigInteger n) {
        BigInteger a = BigInteger.ONE;
        BigInteger b = BigInteger.ZERO;
        for (int i = 0; i < n.bitLength(); i++)
            if (n.testBit(i))
                b = b.add(a);
            else
                a = a.add(b);
        return b;
    }

    @Override
    public BigInteger getTestData() {
        BigInteger twoPThous = BigInteger.valueOf(2).pow(1000);
        return twoPThous.add(BigInteger.valueOf(9007199254740991L));
    }

    @Override
    public BigInteger getExpectedResult() {
        return BigInteger.valueOf(50245);
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }
}
