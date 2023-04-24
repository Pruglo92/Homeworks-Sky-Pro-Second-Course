package ru.skypro.homework8.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework8.entity.Employee;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeNotFoundException;
import ru.skypro.homework8.repository.EmployeeRepository;
import ru.skypro.homework8.service.DepartmentService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {


    @Override   //сотрудник с минимальной зарплатой в отделе
    public Employee getEmployeeWithMinSalary(Integer department) {
        return EmployeeRepository.employees.values().stream()
                .filter(employee -> employee != null && department.equals(employee.getDepartment()))
                .min(Comparator.comparing(Employee::getSalary))
                .stream()
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

    }

    @Override   //сотрудник с максимальной зарплатой в отделе
    public Employee getEmployeeWithMaxSalary(Integer department) {
        return EmployeeRepository.employees.values().stream()
                .filter(employee -> employee != null && department.equals(employee.getDepartment()))
                .max(Comparator.comparing(Employee::getSalary))
                .stream()
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));
    }

    @Override   //Все сотрудники отдела
    public Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(Integer department) {
        return EmployeeRepository.employees.values().stream()
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.groupingBy(Employee::getDepartment));


    }

    /**
     * Данный метод выведет на экран всех сотрудников для каждого отдела в порядке возрастания отдела.
     * За зависимость взяты уникальные номера отделов, имеющиеся у всех сотрудников, так как отдел сотруднику
     * присваивается после прохождения валидации в конструкторе.
     */
    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByAllDepartment() {
        return EmployeeRepository.employees.values().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
