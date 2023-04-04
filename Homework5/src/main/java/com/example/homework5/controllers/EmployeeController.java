package com.example.homework5.controllers;

import com.example.homework5.dto.EmployeeDto;
import com.example.homework5.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping(value = "/add")
    public EmployeeDto addEmployee(@RequestParam String firstName,
                                   @RequestParam String lastName) {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(value = "/find")
    public EmployeeDto findEmployee(@RequestParam String firstName,
                                    @RequestParam String lastName) {
        return employeeService.getEmployee(firstName, lastName);
    }

    @GetMapping(value = "/remove")
    public EmployeeDto removeEmployee(@RequestParam String firstName,
                                      @RequestParam String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping(value = "/findAll")
    public List<EmployeeDto> findAllEmployee() {
        return employeeService.getAllEmployee();
    }
}
