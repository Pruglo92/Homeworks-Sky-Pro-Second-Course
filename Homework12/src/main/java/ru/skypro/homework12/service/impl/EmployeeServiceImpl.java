package ru.skypro.homework12.service.impl;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.exceptions.employeeExceptions.*;
import ru.skypro.homework12.service.EmployeeService;

import java.util.HashMap;
import java.util.Map;
@Getter
@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final Map<Integer, Employee> employees = new HashMap<>();

    private final Integer SIZE_MAP = 10; //допустим, что наша мапа имеет ограниченный размер.

    @Override   // добавляем сотрудника
    public Employee addEmployee(final String firstName, final String lastName,
                                final Integer department, final Integer salary) {
        if (!StringUtils.isAlpha(firstName.trim())) {
            throw new EmployeeIncorrectFirstNameException("Некорректное имя сотрудника.");
        }
        if (!StringUtils.isAlpha(lastName.trim())) {
            throw new EmployeeIncorrectLastNameException("Некорректная фамилия сотрудника.");
        }
        var employee = new Employee(StringUtils.capitalize(firstName.trim().toLowerCase()),
                StringUtils.capitalize(lastName.trim().toLowerCase()), department, salary);
        if (employees.containsValue(employee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен.");
        }
        if (employees.size() >= SIZE_MAP) {
            throw new EmployeeStorageIsFullException("Отсутствует место для добавления нового сотрудника.");
        }
        employees.put(employee.getId(), employee);
        return employee;
    }

    @Override   //находим сотрудника
    public Employee getEmployee(final String firstName, final String lastName) {
        return employees.values().stream()
                .filter(employee -> firstName.equalsIgnoreCase(employee.getFirstName()) &&
                        lastName.equalsIgnoreCase(employee.getLastName()))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

        //если бы мы находили сотрудника по всем параметрам
/*        return EmployeeRepository.employees.values().stream()
                .filter(new Employee(firstName, lastName, department, salary)::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));*/
    }

    @Override   //удаляем сотрудника по имени и фамилии
    public Employee deleteEmployee(final String firstName, final String lastName) {
        var employee = employees.values().stream()
                .filter(e -> firstName.equalsIgnoreCase(e.getFirstName()) &&
                        lastName.equalsIgnoreCase(e.getLastName()))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

        return employees.remove(employee.getId());
    }
        //если бы мы находили сотрудника по всем параметрам
/*        var employee = EmployeeRepository.employees.values().stream()
                .filter(new Employee(firstName, lastName, department, salary)::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));*/
  //  }

    @Override   //получаем всех сотрудников
    public Map<Integer, Employee> getAllEmployee() {
        return employees;
    }
}
