package ru;

import lombok.extern.java.Log;

import java.lang.reflect.Array;

@Log
public abstract class TaskSolution<ResultType, ArgType> {
    public abstract ResultType solution(ArgType arg);

    public abstract ArgType getTestData();

    public abstract ResultType getExpectedResult();

    public String getUrl() {
        return "There are no description for this task";
    }

    public void test() {
        ResultType expectedResult = getExpectedResult();
        ResultType obtainedResult = solution(getTestData());
        if (expectedResult.getClass().isArray()) {
            if (!arraysEquals(expectedResult, obtainedResult)) {
                throwIncorrectAnswerException(expectedResult, obtainedResult);
            }
            return;
        }
        if (!expectedResult.equals(obtainedResult)) {
            throwIncorrectAnswerException(expectedResult, obtainedResult);
        }
    }

    private boolean arraysEquals(ResultType expectedResult, ResultType obtainedResult) {
        for (int i = 0; i < Array.getLength(expectedResult); i++) {
            if (!Array.get(expectedResult, i).equals(Array.get(obtainedResult, i))) {
                return false;
            }
        }
        return true;
    }

    private void throwIncorrectAnswerException(ResultType expectedResult, ResultType obtainedResult) {
        String message = String.format("Expected result: %s, obtained result: %s", expectedResult.toString(), obtainedResult.toString());
        throw new IncorrectAnswerException(message);
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

