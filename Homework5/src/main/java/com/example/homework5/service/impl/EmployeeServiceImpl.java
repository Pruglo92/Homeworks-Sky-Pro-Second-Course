package com.example.homework5.service.impl;


import com.example.homework5.dto.EmployeeDto;
import com.example.homework5.exceptions.EmployeeAlreadyAddedException;
import com.example.homework5.exceptions.EmployeeNotFoundException;
import com.example.homework5.exceptions.EmployeeStorageIsFullException;
import com.example.homework5.repository.EmployeeRepository;
import com.example.homework5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    @Value("${very.important.constant}")
    private Integer SIZE_ARRAY; //допустим, что наш лист имеет ограниченный размер.

    @Override
    public EmployeeDto addEmployee(String firstName, String lastName) {
        var employee = new EmployeeDto(firstName, lastName);
        if (EmployeeRepository.employees.contains(employee)) {
            throw new EmployeeAlreadyAddedException("Данный сотрудник уже добавлен.");
        }
        if (EmployeeRepository.employees.size() >= SIZE_ARRAY) {
            throw new EmployeeStorageIsFullException("Отсутствует место для добавления нового сотрудника.");
        }
        EmployeeRepository.employees.add(employee);
        return employee;
    }

    @Override
    public EmployeeDto getEmployee(String firstName, String lastName) {
        return EmployeeRepository.employees.stream()
                .filter(new EmployeeDto(firstName, lastName)::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

    }

    @Override
    public EmployeeDto deleteEmployee(String firstName, String lastName) {
        if (!EmployeeRepository.employees.remove(new EmployeeDto(firstName, lastName))) {
            throw new EmployeeNotFoundException("Данный сотрудник не найден.");
        }
        return new EmployeeDto(firstName, lastName);
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return EmployeeRepository.employees;
    }
}
