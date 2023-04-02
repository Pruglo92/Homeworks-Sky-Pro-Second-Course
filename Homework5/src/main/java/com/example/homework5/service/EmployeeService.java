package com.example.homework5.service;

import com.example.homework5.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(EmployeeDto employee);

    EmployeeDto getEmployee(EmployeeDto employee);

    EmployeeDto deleteEmployee(EmployeeDto employee);

    List<EmployeeDto> getAllEmployee();
}
