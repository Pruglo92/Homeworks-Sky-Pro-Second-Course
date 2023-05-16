package ru.skypro.homework12.exceptions.employeeExceptions;

public class EmployeeIncorrectLastNameException extends RuntimeException {
    public EmployeeIncorrectLastNameException(String message) {
        super(message);
    }
}
