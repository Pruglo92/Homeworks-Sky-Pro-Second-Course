package ru.skypro.homework9.service;

import ru.skypro.homework9.entity.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName, Integer department, Integer salary);

    Employee getEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Map<Integer, Employee> getAllEmployee();
}
