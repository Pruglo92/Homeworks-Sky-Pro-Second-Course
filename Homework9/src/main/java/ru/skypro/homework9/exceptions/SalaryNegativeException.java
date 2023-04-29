package ru.skypro.homework9.exceptions;

public class SalaryNegativeException extends RuntimeException {
    public SalaryNegativeException(String message) {
        super(message);
    }
}
