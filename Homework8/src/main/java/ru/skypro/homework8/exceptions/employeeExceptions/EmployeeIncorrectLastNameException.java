package ru.skypro.homework8.exceptions.employeeExceptions;

public class EmployeeIncorrectLastNameException extends RuntimeException {
    public EmployeeIncorrectLastNameException(String message) {
        super(message);
    }
}
