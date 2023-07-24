package ru.codewars;

import lombok.extern.java.Log;

@Log
public abstract class CodeWarsTaskSolution<ResultType, ArgType> {
    public abstract ResultType solution(ArgType arg);

    public abstract ArgType getTestData();

    public abstract ResultType getExpectedResult();

    public abstract int getTaskLvl();

    public void test() {
        ResultType expectedResult = getExpectedResult();
        ResultType obtainedResult = solution(getTestData());
        if (!expectedResult.equals(obtainedResult)) {
            String message = String.format("Expected result: %s, obtained result: %s", expectedResult, obtainedResult);
            throw new IncorrectAnswerException(message);
        }
    }

    public void simpleSpeedTest() {
        ArgType testData = getTestData();
        long startTime = System.currentTimeMillis();
        solution(testData);
        log.info(String.format("Execution time with given test data: %d millis", System.currentTimeMillis() - startTime));
    }

    public void simpleSpeedTestWithRepetitions(long count) {
        ArgType testData = getTestData();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            solution(testData);
        }
        log.info(String.format("Execution time with given test data and %d repetitions: %d millis", count, System.currentTimeMillis() - startTime));
    }
}
