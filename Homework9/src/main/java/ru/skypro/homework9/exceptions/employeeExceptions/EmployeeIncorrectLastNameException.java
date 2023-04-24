package ru.skypro.homework9.exceptions.employeeExceptions;

public class EmployeeIncorrectLastNameException extends RuntimeException {
    public EmployeeIncorrectLastNameException(String message) {
        super(message);
    }
}
