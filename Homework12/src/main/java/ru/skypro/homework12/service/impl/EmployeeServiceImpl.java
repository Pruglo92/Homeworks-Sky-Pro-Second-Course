package ru.skypro.homework12.service.impl;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework12.entity.Employee;
import ru.skypro.homework12.exceptions.employeeExceptions.*;
import ru.skypro.homework12.repository.EmployeeRepository;
import ru.skypro.homework12.service.EmployeeService;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    @Value("${very.important.constant}")
    private Integer SIZE_MAP; //допустим, что наша мапа имеет ограниченный размер.

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
        if (employeeRepository.getEmployees().containsValue(employee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен.");
        }
        if (employeeRepository.getEmployees().size() >= SIZE_MAP) {
            throw new EmployeeStorageIsFullException("Отсутствует место для добавления нового сотрудника.");
        }
        employeeRepository.getEmployees().put(employee.getId(), employee);
        return employee;
    }

    @Override   //находим сотрудника
    public Employee getEmployee(final String firstName, final String lastName) {
        return employeeRepository.getEmployees().values().stream()
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
    public Employee deleteEmployee(final String firstName, final String lastName) {
        var employee = employeeRepository.getEmployees().values().stream()
                .filter(e -> firstName.equalsIgnoreCase(e.getFirstName()) &&
                        lastName.equalsIgnoreCase(e.getLastName()))
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

        return employeeRepository.getEmployees().remove(employee.getId());

        //если бы мы находили сотрудника по всем параметрам
/*        var employee = EmployeeRepository.employees.values().stream()
                .filter(new Employee(firstName, lastName, department, salary)::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));*/
    }

    @Override
    public Map<Integer, Employee> getAllEmployee() {
        return employeeRepository.getEmployees();
    }
}
