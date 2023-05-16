package ru.skypro.homework12.exceptions;

public class SalaryNegativeException extends RuntimeException {
    public SalaryNegativeException(String message) {
        super(message);
    }
}
