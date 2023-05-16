package ru.skypro.homework12.service;

import ru.skypro.homework12.entity.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getEmployeeWithMinSalary(Integer department);

    Employee getEmployeeWithMaxSalary(Integer department);

    Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(Integer department);

    Map<Integer, List<Employee>> getAllEmployeesByAllDepartment();
}
