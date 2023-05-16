package ru.skypro.homework12.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.exceptions.employeeExceptions.EmployeeNotFoundException;
import ru.skypro.homework12.repository.EmployeeRepository;
import ru.skypro.homework12.service.DepartmentService;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeRepository employeeRepository;

    public void getDepartmentSalary(Integer department) {        //сумма затрат на зарплату по отделу
        var sum = employeeRepository.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(x -> x.getDepartment() == department)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
        System.out.println("Сумма затрат на зарплату по отделу: " + sum);
    }

    @Override   //сотрудник с минимальной зарплатой в отделе
    public Employee getEmployeeWithMinSalary(final Integer department) {
        return employeeRepository.getEmployees().values().stream()
                .filter(employee -> employee != null && department.equals(employee.getDepartment()))
                .min(Comparator.comparing(Employee::getSalary))
                .stream()
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));
    }

    @Override   //сотрудник с максимальной зарплатой в отделе
    public Employee getEmployeeWithMaxSalary(final Integer department) {
        return employeeRepository.getEmployees().values().stream()
                .filter(employee -> employee != null && department.equals(employee.getDepartment()))
                .max(Comparator.comparing(Employee::getSalary))
                .stream()
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));
    }

    @Override   //Все сотрудники отдела
    public Map<Integer, List<Employee>> getAllEmployeesOfTheDepartment(final Integer department) {
        return employeeRepository.getEmployees().values().stream()
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
        return employeeRepository.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
