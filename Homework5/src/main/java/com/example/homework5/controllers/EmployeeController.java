package com.example.homework5.controllers;

import com.example.homework5.dto.EmployeeDto;
import com.example.homework5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;


    @GetMapping(value = "/add")
    public EmployeeDto addEmployee(EmployeeDto employeeDto) {
        return employeeService.addEmployee(employeeDto);
    }

    @GetMapping(value = "/find")
    public EmployeeDto findEmployee(EmployeeDto employeeDto) {
        return employeeService.getEmployee(employeeDto);
    }

    @GetMapping(value = "/remove")
    public EmployeeDto removeEmployee(EmployeeDto employeeDto) {
        return employeeService.deleteEmployee(employeeDto);
    }

    @GetMapping(value = "/findAll")
    public List<EmployeeDto> findAllEmployee() {
        return employeeService.getAllEmployee();
    }
}
