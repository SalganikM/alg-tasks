package ru.codewars.impl;

import ru.codewars.CodeWarsTaskSolution;

public class ShortestKnightPath extends CodeWarsTaskSolution<Integer, String> {
    public static void main(String[] args) {
        ShortestKnightPath impl = new ShortestKnightPath();
        impl.test();
    }

    @Override
    public Integer solution(String arg) {
        String[] splitArg = arg.split(",");
        return knight(splitArg[0], splitArg[1]);
    }

    // actually brute force
    public int knight(String start, String finish) {
        int dY = Math.abs(start.charAt(0) - finish.charAt(0));
        int dX = Math.abs(start.charAt(1) - finish.charAt(1));

        //board corner case
        if (dY == 1 && dX == 1 && (start + finish).matches(".*?(a1|h1|a8|h8).*")) {
            return 4;
        }

        //Special cases
        if (dY + dX == 1) {
            return 3;
        }
        if (dY == 2 && dX == 2) {
            return 4;
        }
        if (dY == 1 && dX == 1) {
            return 2;
        }
        if (dY == 3 && dX == 3) {
            return 2;
        }
        if (dY == 6 && dX == 6) {
            return 4;
        }
        if (dY == 7 && dX == 5 || dY == 5 && dX == 7) {
            return 4;
        }
        if (dY == 7 && dX == 0 || dY == 0 && dX == 7) {
            return 5;
        }
        if (dY == 4 && dX == 5 || dY == 5 && dX == 4) {
            return 3;
        }


        int sum = dX + dY;
        if ((sum % 3 == 0) && (dX != 2 * dY) && (dY != 2 * dX)) {
            return sum / 3 + 2;
        }
        return sum / 3 + sum % 3;
    }

    @Override
    public String getTestData() {
        return "a1,f7";
    }

    @Override
    public Integer getExpectedResult() {
        return 5;
    }

    @Override
    public int getTaskLvl() {
        return 4;
    }
}
