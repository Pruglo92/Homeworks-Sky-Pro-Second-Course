package ru.skypro.homework12.service;

import ru.skypro.homework12.entity.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Integer getDepartmentSalary(Integer department);

    Integer getDepartmentMaxSalary(Integer department);

    Integer getDepartmentMinSalary(Integer department);

    Employee getEmployeeWithMinSalary(Integer department);

    Employee getEmployeeWithMaxSalary(Integer department);

    Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(Integer department);

    Map<Integer, List<Employee>> getAllEmployeesByAllDepartment();
}
