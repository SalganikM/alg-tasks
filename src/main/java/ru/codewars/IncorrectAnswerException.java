package ru.codewars;

public class IncorrectAnswerException extends RuntimeException {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
