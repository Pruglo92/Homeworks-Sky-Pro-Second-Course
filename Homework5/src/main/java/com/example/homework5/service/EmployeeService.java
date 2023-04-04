package com.example.homework5.service;

import com.example.homework5.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto addEmployee(String firstName, String lastName);

    EmployeeDto getEmployee(String firstName, String lastName);

    EmployeeDto deleteEmployee(String firstName, String lastName);

    List<EmployeeDto> getAllEmployee();
}
