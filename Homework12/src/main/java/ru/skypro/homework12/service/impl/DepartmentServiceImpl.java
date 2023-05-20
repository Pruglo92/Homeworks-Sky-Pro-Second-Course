package ru.skypro.homework12.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.exceptions.departmentExceptions.DepartmentNotFoundException;
import ru.skypro.homework12.service.DepartmentService;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeServiceImpl employeeService;

    @Override   //сумма затрат на зарплату по отделу
    public Integer getDepartmentSalary(final Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
    }

    @Override   //максимальная зарплата по отделу
    public Integer getDepartmentMaxSalary(final Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .mapToInt(Employee::getSalary)
                .max()
                .orElseThrow(() -> new DepartmentNotFoundException("Отсутствуют сотрудники в данном отделе"));
    }

    @Override   //минимальная зарплата по отделу
    public Integer getDepartmentMinSalary(final Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(employee -> Objects.equals(employee.getDepartment(), department))
                .mapToInt(Employee::getSalary)
                .min()
                .orElseThrow(() -> new DepartmentNotFoundException("Отсутствуют сотрудники в данном отделе"));
    }

    @Override   //Все сотрудники отдела
    public List<Employee> getAllEmployeesOfTheDepartment(final Integer department) {
        return employeeService.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .filter(employee -> employee.getDepartment() == department)
                .toList();
    }

    /**
     * Данный метод выведет на экран всех сотрудников для каждого отдела в порядке возрастания отдела.
     * За зависимость взяты уникальные номера отделов, имеющиеся у всех сотрудников, так как отдел сотруднику
     * присваивается после прохождения валидации в конструкторе.
     */
    @Override
    public Map<Integer, List<Employee>> getAllEmployeesByAllDepartment() {
        return employeeService.getEmployees().values().stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
