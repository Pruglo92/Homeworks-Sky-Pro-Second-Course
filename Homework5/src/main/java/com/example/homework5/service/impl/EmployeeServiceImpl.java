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
    public EmployeeDto addEmployee(EmployeeDto employee) {
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
    public EmployeeDto getEmployee(EmployeeDto employee) {
        return EmployeeRepository.employees.stream()
                .filter(employee::equals)
                .findAny()
                .orElseThrow(() -> new EmployeeNotFoundException("Данный сотрудник не найден."));

    }

    @Override
    public EmployeeDto deleteEmployee(EmployeeDto employee) {
        if (!EmployeeRepository.employees.remove(employee)) {
            throw new EmployeeNotFoundException("Данный сотрудник не найден.");
        }
        return employee;
    }

    @Override
    public List<EmployeeDto> getAllEmployee() {
        return EmployeeRepository.employees;
    }
}
