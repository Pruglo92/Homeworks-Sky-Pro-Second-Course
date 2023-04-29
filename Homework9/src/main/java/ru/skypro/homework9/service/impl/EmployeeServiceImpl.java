package ru.skypro.homework9.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework9.entity.Employee;
import ru.skypro.homework9.exceptions.employeeExceptions.*;
import ru.skypro.homework9.repository.EmployeeRepository;
import ru.skypro.homework9.service.EmployeeService;

import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${very.important.constant}")
    private Integer SIZE_MAP; //допустим, что наша мапа имеет ограниченный размер.

    @Override   // добавляем сотрудника
    public Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) {
        if (!StringUtils.isAlpha(firstName.trim())) {
            throw new EmployeeIncorrectFirstNameException("Некорректное имя сотрудника.");
        }
        if (!StringUtils.isAlpha(lastName.trim())) {
            throw new EmployeeIncorrectLastNameException("Некорректная фамилия сотрудника.");
        }
        var employee = new Employee(StringUtils.capitalize(firstName.trim().toLowerCase()),
                StringUtils.capitalize(lastName.trim().toLowerCase()), department, salary);
        if (EmployeeRepository.employees.containsValue(employee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен.");
        }
        if (EmployeeRepository.employees.size() >= SIZE_MAP) {
            throw new EmployeeStorageIsFullException("Отсутствует место для добавления нового сотрудника.");
        }
        EmployeeRepository.employees.put(employee.getId(), employee);
        return employee;
    }

    @Override   //находим сотрудника
    public Employee getEmployee(String firstName, String lastName) {
        return EmployeeRepository.employees.values().stream()
                .filter(e -> firstName.equalsIgnoreCase(e.getFirstName()) &&
                        lastName.equalsIgnoreCase(e.getLastName()))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

        //если бы мы находили сотрудника по всем параметрам
/*        return EmployeeRepository.employees.values().stream()
                .filter(new Employee(firstName, lastName, department, salary)::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));*/
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        var employee = EmployeeRepository.employees.values().stream()
                .filter(e -> firstName.equalsIgnoreCase(e.getFirstName()) &&
                        lastName.equalsIgnoreCase(e.getLastName()))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

        return EmployeeRepository.employees.remove(employee.getId());

        //если бы мы находили сотрудника по всем параметрам
/*        var employee = EmployeeRepository.employees.values().stream()
                .filter(new Employee(firstName, lastName, department, salary)::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));*/
    }

    @Override
    public Map<Integer, Employee> getAllEmployee() {
        return EmployeeRepository.employees;
    }
}
