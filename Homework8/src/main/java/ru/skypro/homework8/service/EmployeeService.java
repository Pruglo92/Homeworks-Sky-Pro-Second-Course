package ru.skypro.homework8.service;

import ru.skypro.homework8.entity.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    Employee getEmployeeWithMinSalary(int department);

    Employee getEmployeeWithMaxSalary(Integer department);

    Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(int department);

    Map<Integer, List<Employee>> getAllEmployeesByAllDepartment();
}
