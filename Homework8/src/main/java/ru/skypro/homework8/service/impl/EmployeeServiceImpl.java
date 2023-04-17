package ru.skypro.homework8.service.impl;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.skypro.homework8.entity.Employee;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeAlreadyAddedException;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeNotFoundException;
import ru.skypro.homework8.exceptions.employeeExceptions.EmployeeStorageIsFullException;
import ru.skypro.homework8.repository.EmployeeRepository;
import ru.skypro.homework8.service.EmployeeService;

import java.util.Map;
import java.util.Objects;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${very.important.constant}")
    private Integer SIZE_MAP; //допустим, что наша мапа имеет ограниченный размер.

    @Override   // добавляем сотрудника
    public Employee addEmployee(String firstName, String lastName, Integer department, Integer salary) {
        var employee = new Employee(firstName, lastName, department, salary);
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
                .filter(employee -> Objects.equals(employee.getFirstName(), firstName) &&
                        Objects.equals(employee.getLastName(), lastName))
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
                .filter(e -> Objects.equals(e.getFirstName(), firstName) &&
                        Objects.equals(e.getLastName(), lastName))
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
