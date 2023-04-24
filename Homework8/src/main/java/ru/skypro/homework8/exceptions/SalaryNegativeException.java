package ru.skypro.homework8.exceptions;

public class SalaryNegativeException extends RuntimeException {
    public SalaryNegativeException(String message) {
        super(message);
    }
}
